package com.selfish.jene.arrays;

public class ArrayMethods {

	/**
	 * 前提是数组已经排好序
	 * 
	 * @param arr
	 *            目标数组
	 * @param key
	 *            查找的值
	 * @return key 在数组中的下标，不存在则返回-1
	 * 
	 */
	public static int binarySearch(int[] arr, int key) {
		int low = 0;// 数组开始下标为0，最低从0开始
		int high = arr.length - 1;// 数组最大下标为长度减1,
		while (low <= high) {// 满足此条件，说明数组中包含key,找不到返回-1
			int index = (low + high) / 2;// 当前查找范围居中的数
			if (key == arr[index]) {// 如果找到，则直接返回当前的下标
				return index;
			} else if (key > arr[index]) {// 如果key大于当前中间的数，则将查找范围的最低下标变为index+1
				low = index + 1;
			} else {// 如果key小于当前中间的数，则将查找范围的最高下标变为index-1
				high = index - 1;
			}
		}
		return -1;
	}

	/**
	 * 如果长度大于原来的数组长度，则多出项默认为0，否则，只赋值新长度的列项
	 * 
	 * @param arr
	 *            目标数组
	 * @param newlength
	 *            新的长度
	 * @return 返回一个以新的长度定义的数组
	 */
	public static int[] copyOf(int[] arr, int newlength) {
		if (arr == null) {
			return null;
		}
		if (newlength < 0) {
			throw new NegativeArraySizeException("长度不能是负数");
		}
		int[] newArr = new int[newlength];
		for (int i = 0; i < (arr.length < newlength ? arr.length : newlength); i++) {
			newArr[i] = arr[i];
		}
		return newArr;
	}

	/**
	 * 用来判断两个数组是否相等
	 * 
	 * @param n
	 *            一个字符串数组
	 * @param m
	 *            一个字符串数组
	 * @return 如果两个数组相等。则返回true,否则返回false
	 */
	public static boolean equalsTo(String[] n, String[] m) {
		if (n == m)// 如果2个数组本身就是一个引用，则相等
			return true;
		if (n == null || m == null)// 如果n或者m为空，则返回false
			return false;
		if (n.length != m.length)// 两个数组长度不相等，则返回false
			return false;
		/**
		 * 满足上面几个条件，那么开始比较数组中每个元素是否相等，必须下标既值相等才视为相等：
		 * 1.n中第i个元素是否为空，如果为空，那么m中第i个元素为空，才视为相等
		 * 2.n中第i个元素是否为空，如果不为空，那么m中第i个元素也不为空，并且这两个元素相等才视为相等
		 * 3.对上面两个情况取反，因为只有同时满足上述两种条件才能视为相等，所以取反返回false
		 */
		for (int i = 0; i < n.length; i++) {
			if (!(n[i] == null ? m[i] == null : n[i].equals(m[i]))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 将第一个值视为最大值，然后循环比较即可，如果发现有比它大的值，则将此值赋给最大值
	 * 
	 * @param n
	 *            目标数组，如果数组为空，则返回-1
	 * @return 返回数组的中最大值
	 */
	public static int getMax(int... n) {
		if (n == null)
			return -1;
		int max = n[0];
		for (int i = 0; i < n.length; i++) {
			if (max < n[i]) {
				max = n[i];
			}
		}
		return max;
	}

	/**
	 * 将第一个值视为最小值，然后循环比较即可，如果发现有比它小的值，则将此值赋给最小值
	 * 
	 * @param n
	 *            目标数组，如果数组为空，则返回-1
	 * @return 返回数组的中最小值
	 */
	public static int getMin(int... n) {
		if (n == null)
			return -1;
		int min = n[0];
		for (int i = 0; i < n.length; i++) {
			if (min > n[i]) {
				min = n[i];
			}
		}
		return min;
	}

	/**TODO 还是不理解
	 * 通过一趟排序要将排序的数据分隔成独立的两部分，其中一部分的所有数据都比另外一个部分的所有数据都小，然后再按此方法
	 * 对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
	 * 
	 * @param arr	目标数组
	 * @param left  左边起始位置
	 * @param right 右边结束位置
	 */
	public static void quickSort(int[] arr, int left, int right) {
		if (left >= right)
			return;
		int i = left;
		int j = right;
		int key = arr[left];
		while (i < j) {
			/***
			 * 寻找结束的条件：
			 * <li>1.找到一个小于或大于key的数字(大于是升序，小于是降序)
			 * <li>2.没有符合条件1的，并且i与j的大小没有反转
			 */
			while (i < j && arr[j] >= key) {
				j--;
			}
			arr[i] = arr[j];
			while (i < j && arr[i] <= key) {
				i++;
			}
			arr[j] = arr[i];
		}
		arr[i] = key;
		quickSort(arr, left, i - 1);
		quickSort(arr, i + 1, right);
	}

	/**
	 * 冒泡法排序是一个在数组中不断寻找最大值，并将其挪到数组最后的算法。
	 * 对于长度为n的数组，一共需要比较n-1轮，每一轮进行n-i-1次比较（i为当前比较轮数）
	 * 
	 * @param n
	 *            整形数组n
	 * @return 经过排序之后的数组
	 */
	public static void sortBublle(int[] n) {
		if (n == null)
			return;
		for (int i = 0; i < n.length; i++) {
			for (int j = 0; j < n.length - 1 - i; j++) {
				if (n[j] > n[j + 1]) {
					int temp = n[j];
					n[j] = n[j + 1];
					n[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 插入法排序：假设数组中前n位有序，将n+1为元素通过比较插入到前面适当的位置
	 * 
	 * @param n
	 *            整形数组
	 */
	public static void sortInsert(int[] n) {
		if (n == null)
			return;
		for (int i = 0; i < n.length; i++) {
			for (int j = i + 1; j < n.length; j++) {
				if (n[i] > n[j]) {// 如果第i个元素大于之后的某个元素，那么它们两个交换位置
					int temp = n[i];
					n[i] = n[j];
					n[j] = temp;
				}
			}
		}
	}

}
