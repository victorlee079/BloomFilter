package com.vl.bf;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		int size = 10;
		List<HashFunction<String>> hashFuns = new ArrayList<>();
		hashFuns.add((val) -> {
			MessageDigest digest = null;
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			return byteArrayToInt(digest.digest(val.getBytes()));
		});
		
		hashFuns.add((val) -> {
			MessageDigest digest = null;
			try {
				digest = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			return byteArrayToInt(digest.digest(val.getBytes()));
		});

		BloomFilter<String> filter = new BloomFilter<>(size, hashFuns);
		
		for (int i = 0; i < 10; i++) {
			filter.addData("TEST" + i);
		}
		
		for (int i = 0; i < 10; i++) {
			assert filter.isExist("TEST" + i);
		}
	}

	public static int byteArrayToInt(byte[] b) {
		return b[3] & 0xFF | (b[2] & 0xFF) << 8 | (b[1] & 0xFF) << 16 | (b[0] & 0xFF) << 24;
	}

	public static byte[] intToByteArray(int a) {
		return new byte[] { (byte) ((a >> 24) & 0xFF), (byte) ((a >> 16) & 0xFF), (byte) ((a >> 8) & 0xFF),
				(byte) (a & 0xFF) };
	}
}
