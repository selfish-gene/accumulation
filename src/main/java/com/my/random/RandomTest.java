package com.my.random;

import java.util.Random;

import org.junit.Test;

public class RandomTest {
	@Test
	public void test() {
		Random r = new Random();
		for (int i = 0; i < 10; i++) {
			System.out.print(r.nextInt(10) + "\t");
		}
	}
}
