package com.example.turagency.util;

import com.example.turagency.util.XssSafeUtil;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class XssSafeUtilTest {

    @Test
    public void testXssSafeString() {
        String incomeString = "<script> something bad</script>";
        String expected = " something bad";
        String actual = XssSafeUtil.xssSafeString(incomeString);
        assertEquals(actual, expected);
    }

}