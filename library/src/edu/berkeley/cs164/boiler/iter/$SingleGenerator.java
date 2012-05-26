package edu.berkeley.cs164.boiler.iter;

import edu.berkeley.cs164.boiler.util.Generator;

public class $SingleGenerator<T> extends Generator<T> implements
		$ResettableIterable<T> {
	T item;

	public $SingleGenerator(T item) {
		this.item = item;
	}

	@Override
	protected void run() throws Exception {
		yield(item);
	}

	@Override
	public $ResettableIterable<T> reset() {
		return new $SingleGenerator<T>(item);
	}
}
