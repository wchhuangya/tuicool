package com.ch.wchhuangya.dzah.tuicool;

import com.ch.wchhuangya.dzah.tuicool.util.TimeUtil;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testTimestamp() throws Exception {
        System.out.println("testTimestamp: 当前的时间戳是——" + TimeUtil.getTimestamp(1));
        assertNotNull("hsd");
    }
}