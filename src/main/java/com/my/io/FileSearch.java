package com.my.io;

import java.io.File;
import java.io.FileFilter;

public class FileSearch {
	private static int count = 0;
	
	public static void main(String[] args) {
		search("D:/");
//		search(new File("D:/"));
		System.out.println(String.format("总共有%s项", count));
	}

	/**
	 * 列出所有的文件及目录下面的文件
	 * 
	 * @param src
	 */
	public static void search(String src) {
		File srcDir = new File(src);
		File[] list = srcDir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				// 加多一个判断条件，但是不能只有这个判断条件，否则无法利用递归打开所有文件
				// 明文解释：返回 【文件是否目录的判断】或者【文件是否以.*为后缀结束的判断】
				// 变化一：显示出E盘的所有java文件
				return pathname.isDirectory() || pathname.getName().endsWith(".jsp");

				// 变化二：显示出E盘的所有大于100KB的文件 (1KB的长度是1000)
				// return pathname.isDirectory() || pathname.length() >
				// 100000000;

				// 变化三：显示出E盘的所有最后修改时间在一周内的文件
				// return pathname.isDirectory() ||
				// pathname.lastModified() > (System.currentTimeMillis() - 7 *
				// 24 * 60 * 60 * 1000);

				// 变化四：显示出C盘的所有隐藏文件
				// return pathname.isDirectory() || pathname.isHidden();
			}
		});

		if (list != null) {
			for (File file : list) {
				// 如果是文件路径，则打印
				if (file.isFile()) {
//					System.out.println(file);
//					System.out.println(file.getName());

				}
				// 如果是目录，利用递归思想继续打开
				else if (file.isDirectory()) {
					count++;
					search(file.getAbsolutePath());
					System.out.println(file.getName());
				}
			}
		}

	}
}
