package com.selfish.gene.third.party.json;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Administrator on 2017/3/6.
 */
public class FormValidatorTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getFormValidator() throws Exception {
        Map<String, Object> map = FormValidator.getFormValidator("common_validate");
        System.out.println(map);
        assertNull(FormValidator.getFormValidator(null));
    }

    @Test
    public void validateForm() throws Exception {
        Map<String, Object> form = new HashMap<>();
        form.put("name", "ab123ass");
        form.put("age", 20);
        boolean result = FormValidator.validateForm(form,"common_validate");
        assertThat(result, is(true));
    }

    @Test
    public void validateParam() throws Exception {

    }

}