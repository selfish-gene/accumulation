package com.my.life;

public class TelByJava {
	public static void main(String[] args) {
		int[] arr = new int[] { 9, 3, 0, 2, 6, 1, 5 };
		int[] index = new int[] { 5, 1, 6, 0, 1, 5, 1, 2, 3, 3, 4 };
		String tel = "";
		for (int i : index) {
			tel += arr[i];
		}
		System.out.println("联系方式：" + tel);
	}
}
