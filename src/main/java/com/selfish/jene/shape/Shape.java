package com.selfish.jene.shape;

public class Shape {

	public static void main(String[] args) {
		getDimond(5);
		getUpTriangle(5);
		getDownTriangle(5);
		getUpTriangleExtension(5);
		getDownTriangleExtension(5);
	}

	public static void getDimond(int n) {
		for (int i = 0; i < n * 2 - 1; i++) {
			for (int j = 0; j < Math.abs(n - 1 - i); j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < n - Math.abs(n - 1 - i); j++) {
				if (j == 0 || j == n - Math.abs(n - 1 - i) - 1) {
					System.out.print("* ");
				} else {
					System.out.print("  ");
				}
			}
			System.out.println();
		}
	}

	public static void getUpTriangle(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j <= i; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}

	public static void getUpTriangleExtension(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - i - 1; j++) {
				System.out.print("  ");
			}
			for (int j = 0; j < i * 2 + 1; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}

	public static void getDownTriangle(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print(" ");
			}
			for (int j = 0; j < n - i; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}

	public static void getDownTriangleExtension(int n) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print("  ");
			}
			for (int j = 0; j < (n - i) * 2 - 1; j++) {
				System.out.print("* ");
			}
			System.out.println();
		}
	}

}
