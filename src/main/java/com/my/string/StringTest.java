package com.my.string;

import static org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.junit.Test;

public class StringTest {
	@Test
	public void test() {
		String str1 = "a.b.vb.f";
		int i = 1;

		Assert.assertThat(str1.indexOf("."), is(1));
		Assert.assertThat(str1.indexOf(".", 5), is(6));
		
		Assert.assertThat(str1.lastIndexOf("."), is(6));

		// 截取子字符串，返回String
		Assert.assertThat(str1.substring(0), is("a.b.vb.f"));
		Assert.assertThat(str1.substring(0), is("a.b.vb.f"));

		Assert.assertThat(String.valueOf(str1), is("a.b.vb.f"));
		Assert.assertThat(String.valueOf(i), is("1"));
		Assert.assertThat(String.valueOf(2), is("2"));
		Assert.assertThat(Integer.toString(i), is("1"));
	}
}
