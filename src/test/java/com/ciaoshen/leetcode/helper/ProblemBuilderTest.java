/**
 * JUnit unit test
 *
 * @author Pixel SHEN
 */
package com.ciaoshen.leetcode.helper;

// JUnit & hamcrest 
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.BeforeClass;
import org.junit.Test;
// java.io 
import java.io.File;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.IOException;
// java.nio.file 
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ProblemBuilderTest {

    // 4 arguments provided by user
    private static final String BASE_DIR = "/Users/Wei/github/leetcode-helper";
    private static final String PROBLEM_NAME = "two_sum";
    private static final String PACKAGE_NAME = "com.ciaoshen.leetcode";
    private static final String UTIL = "com.ciaoshen.leetcode.util";

    // resources directory
    private static final String TEST_RES_DIR = "src/test/resources";

    private static ProblemBuilder builder;

    /** Execute once before any of the test methods in this class. */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        String[] args = new String[]{BASE_DIR, PROBLEM_NAME, PACKAGE_NAME, UTIL};
        builder = new ProblemBuilder(args);
    }

    @Test
    public void testConstructor() {
        // 4 input arguments
        assertThat(builder.root, is(equalTo(BASE_DIR)));
        System.out.println("builder.root = " + builder.root);
        assertThat(builder.problemName, is(equalTo(PROBLEM_NAME)));
        System.out.println("builder.problemName = " + builder.problemName);
        assertThat(builder.pck, is(equalTo(PACKAGE_NAME)));
        System.out.println("builder.pck = " + builder.pck);
        assertThat(builder.util, is(equalTo(UTIL)));
        System.out.println("builder.util = " + builder.util);

        // 2 source dir extracted from property files
        String expectedSrc = "src/main/java";
        System.out.println("builder.src = " + builder.src);
        assertThat(builder.src, is(equalTo(expectedSrc)));

        String expectedTestSrc = "src/test/java";
        System.out.println("builder.testSrc = " + builder.testSrc);
        assertThat(builder.testSrc, is(equalTo(expectedTestSrc)));

        // construct absolute path
        String expectedPckPath = "com/ciaoshen/leetcode";
        System.out.println("builder.pckPath = " + builder.pckPath);
        assertThat(builder.pckPath, is(equalTo(expectedPckPath)));

        String expectedSolutionDir = BASE_DIR + "/" + expectedSrc + "/" + expectedPckPath + "/" + PROBLEM_NAME;
        System.out.println("builder.solutionDir = " + builder.solutionDir);
        assertThat(builder.solutionDir, is(equalTo(expectedSolutionDir)));

        String expectedTestDir = BASE_DIR + "/" + expectedTestSrc + "/" + expectedPckPath + "/" + PROBLEM_NAME;
        System.out.println("builder.testDir = " + builder.testDir);
        assertThat(builder.testDir, is(equalTo(expectedTestDir)));
    }

    @Test
    public void testBuildSourcePath() {
        String tplPath1 = "/template/Solution.vm";
        String srcPath1 = builder.buildSourcePath(tplPath1);
        System.out.println("Built absolute source path = " + srcPath1);
        assertThat(srcPath1, is(equalTo(builder.solutionDir + "/Solution.java")));
        String tplPath2 = "/template/TestRunner.vm";
        String srcPath2 = builder.buildSourcePath(tplPath2);
        System.out.println("Built absolute source path = " + srcPath2);
        assertThat(srcPath2, is(equalTo(builder.testDir + "/TestRunner.java")));
        System.out.println("ProblemBuilder#buildSourcePath() method pass JUnit test!");
    }

    @Test
    public void testGetFileWriter() {
        String wFile = TEST_RES_DIR + "/a/b/c/d/e/writer_test.txt";
        String wFileDir = TEST_RES_DIR + "/a";
        Writer fw = builder.getFileWriter(wFile);
        assertNotNull(fw);
        deleteRecursiveDirectory(new File(wFileDir));
        System.out.println("ProblemBuilder#getWriter() method pass JUnit test!");
    }

    @Test
    public void testWriteTemplates() {
        builder.writeTemplates();
        String answer = readFile(builder.solutionDir + "/Solution.java");
        assertThat("/", is(equalTo(answer.substring(0, answer.indexOf("*")))));
        System.out.println("ProblemBuilder#writeTemplates() method pass JUnit test!");
    }

    /**======================================== 【useful tools】 ============================================= */

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
    /**
     * Read the content of a file
     * @param  path absolute path of that file
     * @return      Content of that file in an entire String
     */
    String readFile(String path) {
        StringBuilder sb = new StringBuilder();
        Path p = Paths.get(path);
        try {
            BufferedReader br = Files.newBufferedReader(p);
            String line = "";
            try {
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            } finally {
                br.close();
            }
        } catch (IOException ioe) { // Files.newBbufferedReader(), readLine(), close()
            ioe.printStackTrace();
        }
        return sb.toString();
    }
}
