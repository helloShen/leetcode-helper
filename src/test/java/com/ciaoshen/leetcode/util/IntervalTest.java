package com.ciaoshen.leetcode.util;

import static org.junit.Assert.*; 
import static org.hamcrest.CoreMatchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntervalTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(IntervalTest.class);
    private static Interval a;
    private static Interval b;

    /**
     * Creat 2 intervals:
     *  a = [0, 0]
     *  b = [2, 3]
     * 
     * @author Wei SHEN
     */
    @BeforeClass
    public static void setUpBeforeClass() {
        a = new Interval();         // [0, 0]
        b = new Interval(2, 3);     // [2, 3]
    }

    @Test
    public void test() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("{} start at {}", a, a.start);
            LOGGER.debug("{} end at {}", a, a.end);
            LOGGER.debug("{} start at {}", b, b.start);
            LOGGER.debug("{} end at {}", b, b.end);
        }

        assertThat(a.start, is(equalTo(0)));
        assertThat(a.end, is(equalTo(0)));
        assertThat(b.start, is(equalTo(2)));
        assertThat(b.end, is(equalTo(3)));
    
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Class Interval passed junit test!");
        }
    }
}