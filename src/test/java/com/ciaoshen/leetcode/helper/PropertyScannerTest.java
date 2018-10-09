/** 
 * JUnit test
 * @author Wei SHEN
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

public class PropertyScannerTest {

    // call from slf4j facade
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyScannerTest.class);

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
