package com.vl.bf;

import java.util.ArrayList;
import java.util.List;

public class BloomFilter<T> {
	private boolean[] bitArray;
	private List<HashFunction<T>> hashFunctions = new ArrayList<>();

	public BloomFilter(int n) {
		this.bitArray = new boolean[n];
	}

	public BloomFilter(int n, List<HashFunction<T>> hashFuns) {
		this.bitArray = new boolean[n];
		this.hashFunctions.addAll(hashFuns);
	}

	public void addData(T object) {
		for (HashFunction<T> fun : hashFunctions) {
			int index = Math.abs(fun.hash(object) % this.bitArray.length);
			bitArray[index] = true;
		}
	}

	public boolean isExist(T object) {
		boolean result = true;
		for (HashFunction<T> fun : hashFunctions) {
			int index = Math.abs(fun.hash(object) % this.bitArray.length);
			result &= bitArray[index];
			if (!result) {
				return false;
			}
		}
		return result;
	}
}
