package edu.berkeley.cs164.boiler.util;

import java.util.Iterator;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;

/**
 * Provides a Python-style Generator in Java. Subclass this class and implement
 * the run method in subclass. Use {@link Generator#yield(Object)} to return a
 * value and {@link Generator#yield()} to receive a value. This class uses
 * Preconditions and AbstractIterator classes from the Google Collections
 * library.
 * 
 * @author Abhinav Sarkar
 */
public abstract class Generator<T> implements Iterable<T>{

	private final int generatorNormalExitTimeout;

	private final int generatorAbnormalExitTimeout;

	private final SynchronousQueue<T> queue = new SynchronousQueue<T>();

	private final ExecutorService executorService = Executors
			.newSingleThreadExecutor(new DaemonThreadFactory());

	private volatile Throwable throwable = null;

	private final AtomicBoolean started = new AtomicBoolean(false);

	private final CyclicBarrier barrier = new CyclicBarrier(2);

	private final Iterator<T> iterator = new AbstractIterator<T>() {
		@Override
		public T computeNext() {
			T next = Generator.this.get();
			return next != null ? next : endOfData();
		}
	};

	/**
	 * Creates a Generator with defaults.
	 */
	public Generator() {
		this(1, 2);
	}

	/**
	 * Creates a Generator.
	 * 
	 * @param generatorNormalExitTimeout
	 *            Timeout in seconds for the generator thread when the generator
	 *            exits normally, either by finishing the run or by throwing a
	 *            {@link GeneratorExit} exception.
	 * @param generatorAbnormalExitTimeout
	 *            Timeout in seconds for the generator thread when the generator
	 *            exits abnormally by throwing any exception other than
	 *            {@link GeneratorExit} exception.
	 */
	public Generator(final int generatorNormalExitTimeout,
			final int generatorAbnormalExitTimeout) {
		this.generatorNormalExitTimeout = generatorNormalExitTimeout;
		this.generatorAbnormalExitTimeout = generatorAbnormalExitTimeout;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	public final Iterator<T> iterator() {
		return iterator;
	}

	/**
	 * Gets a value from the generator. The generator must call
	 * {@link Generator#yield(Object)} to return this value inside the
	 * {@link Generator#run()} method.
	 * 
	 * @return The value yielded by the generator.
	 */
	public final T get() {
		try {
			return next();
		} catch (Throwable e) {
			executorService.shutdownNow();
			if (!(e instanceof GeneratorExit)) {
				throw new RuntimeException(e);
			} else {
				return null;
			}
		}
	}

	/**
	 * Sends a value to the generator. The generator must call
	 * {@link Generator#yield()} to receive this value inside the
	 * {@link Generator#run()} method.
	 * 
	 * @param sent
	 *            The value sent to the generator.
	 */
	public void send(final T sent) {
		try {
			next(Preconditions.checkNotNull(sent));
		} catch (Throwable e) {
			executorService.shutdownNow();
			if (e instanceof GeneratorExit) {
				return;
			} else {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Stops the generator and frees up the background thread.
	 */
	public final void stop() {
		executorService.shutdownNow();
	}

	/**
	 * Call this method inside {@link Generator#run()} method to yield a value.
	 * 
	 * @param result
	 *            Value to be yielded.
	 * @throws InterruptedException
	 * @throws BrokenBarrierException
	 */
	protected final void yield(final T result) throws InterruptedException,
			BrokenBarrierException {
		barrier.await();
		queue.put(result);
	}

	/**
	 * Call this method inside {@link Generator#run()} method to receive a
	 * value.
	 * 
	 * @return The value sent to generator using {@link Generator#send(Object)}
	 *         method.
	 * @throws InterruptedException
	 * @throws BrokenBarrierException
	 */
	protected final T yield() throws InterruptedException,
			BrokenBarrierException {
		barrier.await();
		return queue.take();
	}

	private T next() throws Throwable {
		initialize();
		return queue.take();
	}

	private void next(final T sent) throws Throwable {
		initialize();
		queue.put(sent);
	}

	private void initialize() throws Throwable {
		if (executorService.isShutdown()) {
			if (throwable != null) {
				throw new IllegalStateException("Generator has exited",
						throwable);
			} else {
				throw new IllegalStateException("Generator has exited");
			}
		}
		if (!started.get()) {
			executorService.execute(new Runnable() {
				public void run() {
					try {
						Generator.this.run();
						throw new GeneratorExit();
					} catch (Exception e) {
						Generator.this.throwable = e;
						try {
							if (e instanceof GeneratorExit) {
								barrier.await(generatorNormalExitTimeout,
										TimeUnit.SECONDS);
							} else {
								barrier.await(generatorAbnormalExitTimeout,
										TimeUnit.SECONDS);
							}
						} catch (InterruptedException e1) {
							return;
						} catch (BrokenBarrierException e1) {
							return;
						} catch (TimeoutException e1) {
							executorService.shutdownNow();
						}
					}
				}
			});
			started.set(true);
		}

		barrier.await();
		if (throwable != null) {
			throw throwable;
		}
	}

	/**
	 * Implement this method inside the subclass to provide the logic for the
	 * generator.
	 * 
	 * @throws Exception
	 */
	protected abstract void run() throws Exception;

	private static final class DaemonThreadFactory implements ThreadFactory {
		private final ThreadFactory defaultThreadFactory = Executors
				.defaultThreadFactory();

		public Thread newThread(final Runnable r) {
			Thread thread = defaultThreadFactory.newThread(r);
			thread.setDaemon(true);
			return thread;
		}
	}

	/**
	 * The Exception thrown to signal the exit of the generator.
	 * 
	 * @author Abhinav Sarkar
	 */
	public static class GeneratorExit extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}

}