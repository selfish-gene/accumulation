package com.my.java.util;


import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class ArraysTest {
	
	@Test
	public void test() {
		Assert.assertThat(Arrays.binarySearch(new int[]{1,5,6,9,12,23,56}, 5), is(1));
		
		Assert.assertThat(Arrays.copyOf(new int[]{1,2}, 3), is(new int[]{1,2,0}));
		
		Assert.assertThat(Arrays.equals(new int[]{}, new int[]{}), is(true));
		
		Arrays.sort(new int[]{1});
		
		
	}
}
