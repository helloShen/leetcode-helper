package com.ciaoshen.leetcode.util;

import java.util.Deque;
import java.util.List;
import java.util.LinkedList;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NestedIntegerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NestedInteger.class);

    @BeforeClass
    public static void setUpBeforeClass() {
        
    }

    /**
     * Test:
     * 1. 2 constructor
     * 2. add()
     * 3. isInteger()
     * 4. getInteger()
     * 5. getList()
     */
    @Test
    public void test1() {
        String str1 = "324";
        String str2 = "[123,[456,[789]]]";

        NestedInteger ni1 = deserialize(str1);
        NestedInteger ni2 = deserialize(str2);

        String actual1 = ni1.toString();
        String actual2 = ni2.toString();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Actual1 = {}", actual1);
            LOGGER.debug("Expected1 = {}", str1);
            LOGGER.debug("Actual2 = {}", actual2);
            LOGGER.debug("Expected2 = {}", str2);
        }

        assertThat(actual1, is(equalTo(str1)));
        assertThat(actual2, is(equalTo(str2)));

        boolean isInteger1 = ni1.isInteger();
        boolean isInteger2 = ni2.isInteger();
        int num = ni1.getInteger();
        List<NestedInteger> list1 = ni1.getList();
        List<NestedInteger> list2 = ni2.getList();


        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("isInteger1 = {}, it should be {}", isInteger1, true);
            LOGGER.debug("isInteger2 = {}, it should be {}", isInteger2, false);
            LOGGER.debug("num = {}", num);
            LOGGER.debug("list1 is null? {}", (list1 == null));
            LOGGER.debug("list2 is null? {}", (list2 == null));
        }

        assertThat(isInteger1, is(true));
        assertThat(isInteger2, is(false));
        assertThat(num, is(equalTo(324)));
        assertThat((list1 == null), is(true));
        assertThat((list2 == null), is(false));

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Class NestedInteger passed junit test!");
        }
    }

    private int sign;
    private Integer num;
    private Deque<NestedInteger> stack;
    
    private void init() {
        sign = 1;
        num = null;
        stack = new LinkedList<NestedInteger>();
    }

    private NestedInteger deserialize(String s) {
        init();
        NestedInteger dummy = new NestedInteger();
        NestedInteger curr = dummy;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                NestedInteger newNI = new NestedInteger();
                curr.add(newNI);
                stack.push(curr);
                curr = newNI;
            } else if (c == ']' || c == ',') {
                if (num != null) {
                    curr.add(new NestedInteger(num * sign));
                    num = null;
                    sign = 1;
                }
                if (c == ']') {
                    curr = stack.pop();
                }
            } else if (c =='-') {
                num = 0;
                sign = -1;
            } else { // is digit
                if (num == null) {
                    num = 0;
                }
                num = num * 10 + (c - '0');
            }
        }
        if (num != null) { // dump
            curr.add(new NestedInteger(num * sign));
        }
        return dummy.getList().get(0);
    }

}