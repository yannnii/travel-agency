package com.example.turagency.util;

import com.example.turagency.util.PathManager;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PathManagerTest {

    @Test
    public void testGetProperty() {
        String expected = "/index.jsp";
        String actual = PathManager.getProperty("path.page.index");
        assertEquals(actual, expected);
    }

}