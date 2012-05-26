package edu.berkeley.cs164.boiler.iter;

import edu.berkeley.cs164.boiler.util.Generator;

public class $FilterGenerator<T> extends Generator<T> implements
		$ResettableIterable<T> {
	protected $ResettableIterable<T> items;
	protected $Filter<T> filter;

	public $FilterGenerator($ResettableIterable<T> items, $Filter<T> filter) {
		this.items = items;
		this.filter = filter;
	}

	@Override
	protected void run() throws Exception {
		for (T item : items) {
			if (filter.$(item)) {
				yield(item);
			}
		}
	}

	public interface $Filter<T> {
		boolean $(T object);
	}

	@Override
	public $ResettableIterable<T> reset() {
		return new $FilterGenerator<T>(items.reset(), filter);
	}
}
