/**
 * JUnit unit test
 *
 * @author Pixel SHEN
 */
package com.ciaoshen.leetcode.helper;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
/** JUnit & hamcrest */
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;


public class TemplateSeekerTest {

    @Test
    public void testGetTemplates() {
        List<String> templates = TemplateSeeker.getTemplates();
        System.out.println("Templates = " + templates);
        List<String> answer = new ArrayList<String>(Arrays.asList(new String[]{
            "/template/Solution.vm",
            "/template/Solution1.vm",
            "/template/Solution2.vm",
            "/template/Tester.vm",
            "/template/TesterRunner.vm"
        }));
        assertThat(templates.containsAll(answer), is(true));
        System.out.println("TemplateSeeker.getTemplatePath() method pass junit test!");
    }

}
