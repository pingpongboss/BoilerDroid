package edu.berkeley.cs164.boiler.util;


public interface EachCallback<T> {
	void $(int index, T object);
}