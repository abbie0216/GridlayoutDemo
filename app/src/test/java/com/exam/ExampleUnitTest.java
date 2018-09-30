package com.exam;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void getRandomNum_isCorrect() {
        int ran = Utility.getRandomNum(0, 10,8);
        assertTrue(ran >= 0 && ran <= 10 && ran!=8);
        ran = Utility.getRandomNum(0, 4, 1);
        assertTrue(ran >= 0 && ran <= 4 && ran!=1);
    }
}