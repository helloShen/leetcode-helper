/**
 * ProblemBuilder单元测试

 * @author Pixel SHEN
 */
package com.ciaoshen.leetcode.helper;
/** JUnit */
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
/** slf4j */
import org.slf4j.impl.StaticLoggerBinder;
import org.slf4j.LoggerFactory;
/** commons-lang3 */
import org.apache.commons.lang3.StringUtils;
/** java.io */
import java.io.File;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
/** java.nio.file */
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProblemBuilderTest {

    public ProblemBuilderTest() {
        builder = new ProblemBuilder(ARGS);
        assertNotNull(builder);
        assertEquals("src/main/java/com/ciaoshen/leetcode/two_sum", builder.getProblemDestination());
        assertEquals("src/test/java/com/ciaoshen/leetcode/two_sum", builder.getTestDestination());
        System.out.println("ProblemBuilder constructor pass JUnit test!");
        System.out.println("ProblemBuilder#getProblemDestination() method pass JUnit test!");
        System.out.println("ProblemBuilder#getTestDestination() method pass JUnit test!");
    }
    @Test
    public void testBuildSourcePath() {
        String tplPath1 = "src/main/resources/Solution.vm";
        File tplFile1 = new File(tplPath1);
        String srcPath1 = builder.buildSourcePath(tplFile1);
        assertEquals("src/main/java/com/ciaoshen/leetcode/two_sum/Solution.java", srcPath1);
        String tplPath2 = "src/main/resources/TestRunner.vm";
        File tplFile2 = new File(tplPath2);
        String srcPath2 = builder.buildSourcePath(tplFile2);
        assertEquals("src/test/java/com/ciaoshen/leetcode/two_sum/TestRunner.java", srcPath2);
        System.out.println("ProblemBuilder#buildSourcePath() method pass JUnit test!");
    }
    @Test
    public void testWriteTemplates() {
        builder.writeTemplates();
        String answer = builder.readFile("src/main/java/com/ciaoshen/leetcode/two_sum/Solution.java");
        assertEquals("/", answer.substring(0, answer.indexOf("*")));
        System.out.println("ProblemBuilder#writeTemplates() method pass JUnit test!");
    }
    @Test(expected = Test.None.class) /* no exception expected */
    public void testGetFileWriter() {
        Writer fw = builder.getFileWriter(W_TEST);
        assertNotNull(fw);
        String input = "Junit test of getFileWriter() method.";
        try {
            fw.write(input, 0, input.length());
            fw.flush();
            String content = builder.readFile(W_TEST);
            assertEquals(input, content);
            fw.close();
            deleteRecursiveDirectory(new File(W_TEST_DIR));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println("ProblemBuilder#getWriter() method pass JUnit test!");
    }
    /** recursively delete a directory and all its sub directories */
    private void deleteRecursiveDirectory(File file) {
        File[] subs = file.listFiles();
        if (subs != null) {
            for (File sub : subs) {
                deleteRecursiveDirectory(sub);
            }
        }
        file.delete();
    }
    @Test
    public void testReadFile() {
        String content = builder.readFile(R_TEST);
        assertEquals("Hello Ronald!", content);
        System.out.println("ProblemBuilder#readFile() method pass JUnit test!");
    }

    /**==================== 【 private 】 =========================*/
    /** 根目录 */
    private final String ROOT = "/Users/Wei/github/leetcode/helper";
    /** 构造ProblemBuilder的4个必要参数 */
    private final String TPL_DIR = "src/main/resources";
    private final String SRC_DIR = "src/main/java";
    private final String TEST_DIR = "src/test/java";
    private final String PCK = "com.ciaoshen.leetcode";
    private final String PROB = "two_sum";
    private final String UTIL = "com.ciaoshen.leetcode.myUtils";
    private final String[] ARGS = new String[]{TPL_DIR, SRC_DIR, TEST_DIR, PCK, PROB, UTIL};
    /** 测试FileReader的文本文件 */
    private final String RES_DIR = "src/test/resources";
    private final String R_TEST = RES_DIR + "/reader_test.txt";
    private final String W_TEST = RES_DIR + "/a/b/c/d/e/writer_test.txt";
    private final String W_TEST_DIR = RES_DIR + "/a";
    /** 内置ProlbemBuilder */
    private ProblemBuilder builder;
}
