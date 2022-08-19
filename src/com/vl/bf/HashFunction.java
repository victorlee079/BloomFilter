package com.vl.bf;

@FunctionalInterface
public interface HashFunction<T> {
	int hash(T object);
}
