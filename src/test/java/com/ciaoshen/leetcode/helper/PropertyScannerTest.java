package com.ciaoshen.leetcode.helper;

// java.util
import java.util.Properties;
/** JUnit & hamcrest */
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

public class PropertyScannerTest {

    @Test
    public void testLoad() {
        String layout = "layout.properties";
        Properties props = PropertyScanner.load(layout);
        String src = props.getProperty("src");
        System.out.println("src = " + src);
        String srcAnswer = "src/main/java";
        assertThat(src, is(equalTo(srcAnswer)));

        String log4j = "log4j.properties";
        props = PropertyScanner.load(log4j);
        String target = props.getProperty("log4j.appender.A1");
        System.out.println("log4j.appender.A1 = " + target);
        String targetAnswer = "org.apache.log4j.ConsoleAppender";
        assertThat(target, is(equalTo(targetAnswer)));

        System.out.println("PropertyScanner.load() method pass junit test!");
    }

}
