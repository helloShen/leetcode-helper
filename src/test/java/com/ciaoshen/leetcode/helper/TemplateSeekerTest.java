/** 
 * JUnit test
 * @author Wei SHEN
 */
package com.ciaoshen.leetcode.helper;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemplateSeekerTest {
    // call from slf4j facade
    private static final Logger LOGGER = LoggerFactory.getLogger(TemplateSeekerTest.class);

    @Test
    public void testGetTemplates() {
        List<String> templates = TemplateSeeker.getTemplates();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Templates = {}", templates);
        }
        List<String> answer = new ArrayList<String>(Arrays.asList(new String[]{
            "/template/Solution.vm",
            "/template/Solution1.vm",
            "/template/Solution2.vm",
            "/template/Tester.vm",
            "/template/TesterRunner.vm"
        }));
        assertThat(templates.containsAll(answer), is(true));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("TemplateSeeker.getTemplatePath() method pass junit test!");
        }
    }

}
