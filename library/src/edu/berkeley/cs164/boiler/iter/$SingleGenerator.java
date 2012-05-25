package edu.berkeley.cs164.boiler.iter;

import edu.berkeley.cs164.boiler.util.Generator;

public class $SingleGenerator<T> extends Generator<T> {
	T item;

	public static <T> $GeneratorFactory<T> getFactory(final T item) {
		return new $GeneratorFactory<T>() {

			@Override
			public Generator<T> createGenerator() {
				return new $SingleGenerator<T>(item);
			}

		};
	}

	public $SingleGenerator(T item) {
		this.item = item;
	}

	@Override
	protected void run() throws Exception {
		yield(item);
	}
}
