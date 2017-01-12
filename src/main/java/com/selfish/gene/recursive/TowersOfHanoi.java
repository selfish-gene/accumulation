package com.selfish.gene.recursive;

public class TowersOfHanoi {
	private static int countIf = 0;
	private static int countElse = 0;
	private static int count = 0;

	public static void main(String[] args) {
		int n = 3;
		hanoi(n, 'A', 'B', 'C');
		System.out.println("countIf:" + countIf);
		System.out.println("countElse:" + countElse);
	}

	public static void hanoi(int n, char A, char B, char C) {
		if (n < 0) {
			return;
		}
		if (n == 1) {
			System.out.println(String.format("第 %d次执行if:   Movesheet %d from %c to %c ", ++count, n, A, C));
			countIf++;
		} else {
			hanoi(n - 1, A, C, B);
			System.out.println(String.format("第 %d次执行else: Movesheet %d from %c to %c", ++count, n, A, C));
			hanoi(n - 1, B, A, C);
			countElse++;
		}
	}

}
