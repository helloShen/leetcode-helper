/**
 * MIT License
 *
 * Copyright (c) 2018 Wei SHEN 

 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.ciaoshen.leetcode.helper;

// java.util
import java.util.Properties;
// JUnit & hamcrest
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
// slf4j
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * JUnit test
 * @author Wei SHEN
 */
public class PropertyScannerTest {

    // call from slf4j facade
    private static final Logger LOGGER = LoggerFactory.getLogger(TesterRunner.class);

    @Test
    public void testLoad() {
        String layout = "layout.properties";
        Properties props = PropertyScanner.load(layout);
        String src = props.getProperty("src");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("src = {}", src);
        }
        String srcAnswer = "src/main/java";
        assertThat(src, is(equalTo(srcAnswer)));

        String log4j = "log4j.properties";
        props = PropertyScanner.load(log4j);
        String target = props.getProperty("log4j.appender.stdout");
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("log4j.appender.stdout = {}", target);
        }
        String targetAnswer = "org.apache.log4j.ConsoleAppender";
        assertThat(target, is(equalTo(targetAnswer)));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("PropertyScanner.load() method pass junit test!");
        }
    }

}
