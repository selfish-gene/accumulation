package com.selfish.gene.arrays;

import java.util.Arrays;

public class MyArraysTest {

	public static void main(String[] args) {
		int[] a = new int[] { 1, 5, 45, 12, 36, 6 };
		ArrayMethods.quickSort(a, 0, a.length-1);
		System.out.println(Arrays.toString(a));
	}

}
