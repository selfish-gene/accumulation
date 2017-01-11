package com.selfish.jene.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
	public static void main(String[] args) {
		copyDirectory("E:/atom", "F:/atom");
	}

	/**
	 * @param src
	 *            The source file path
	 * @param des
	 *            The
	 */
	public static void copyDirectory(String src, String des) {
		File srcDir = new File(src);
		File desDir = new File(des);

		if (!srcDir.exists() || srcDir.isFile()) {
			throw new RuntimeException("The srcDir isn't exists !");
		}
		if (desDir.isFile()) {
			throw new RuntimeException("The desDir can't be a file !");
		}
		if (desDir.isDirectory() && desDir.listFiles().length != 0) {
			throw new RuntimeException("The desDir must be null ! ");
		}
		copyDirectoryRecursive(srcDir, desDir);
	}

	/**
	 * @param srcDir
	 * @param desDir
	 */
	public static void copyDirectoryRecursive(File srcDir, File desDir) {
		if (!desDir.exists())
			desDir.mkdirs();
		File[] listFiles = srcDir.listFiles();
		for (File file : listFiles) {
			String parentPath = desDir.getAbsolutePath();
			if (!parentPath.endsWith("/"))
				parentPath += "/";
			String path = parentPath + file.getName();
			if (file.isFile()) {
				copySingleFile(file, new File(path));
			} else if (file.isDirectory()) {
				copyDirectoryRecursive(file, new File(path));
			}
		}
	}

	public static void copySingleFile(File srcDir, File desDir) {
		long start = System.currentTimeMillis();
		try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcDir));
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desDir))) {
			byte[] b = new byte[1024];
			while (bis.read(b) != -1) {
				bos.write(b);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(String.format("%s耗时%dms", srcDir.getAbsolutePath(), System.currentTimeMillis() - start));
	}

}
