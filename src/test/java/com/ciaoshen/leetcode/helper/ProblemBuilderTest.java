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

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.Writer;
import java.io.BufferedReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * JUnit test
 * @author Wei SHEN
 */
public class ProblemBuilderTest {

    // 4 arguments provided by user
    private static final String BASE_DIR = "/Users/Wei/github/leetcode-helper";
    private static final String PROBLEM_NAME = "two_sum";
    private static final String PACKAGE_NAME = "com.ciaoshen.leetcode";
    private static final String UTIL = "com.ciaoshen.leetcode.util";
    private static final String MEMBERS = "int add(int a, int b) {}";

    // resources directory
    private static final String TEST_RES_DIR = "src/test/resources";

    // objet to be tested
    private static ProblemBuilder builder;
    // call from slf4j facade
    private static final Logger LOGGER = LoggerFactory.getLogger(ProblemBuilderTest.class);

    /** Execute once before any of the test methods in this class. */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        String[] args = new String[]{BASE_DIR, PROBLEM_NAME, PACKAGE_NAME, UTIL, MEMBERS};
        builder = new ProblemBuilder(args);
    }

    @Test
    public void testConstructor() {
        // 4 input arguments
        assertThat(builder.root, is(equalTo(BASE_DIR)));
        assertThat(builder.problemName, is(equalTo(PROBLEM_NAME)));
        assertThat(builder.pck, is(equalTo(PACKAGE_NAME)));
        assertThat(builder.util, is(equalTo(UTIL)));
        assertThat(builder.members, is(equalTo(MEMBERS)));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("builder.root = {}", builder.root);
            LOGGER.debug("builder.problemName = {}", builder.problemName);
            LOGGER.debug("builder.pck = {}", builder.pck);
            LOGGER.debug("builder.util = {}", builder.util);
            LOGGER.debug("builder.members = {}", builder.members);
        }

        // 2 source dir extracted from property files
        String expectedSrc = "src/main/java";
        assertThat(builder.src, is(equalTo(expectedSrc)));
        String expectedTestSrc = "src/test/java";
        assertThat(builder.testSrc, is(equalTo(expectedTestSrc)));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("builder.src = {}", builder.src);
            LOGGER.debug("builder.testSrc = {}", builder.testSrc);
        }

        // construct absolute path
        String expectedPckPath = "com/ciaoshen/leetcode";
        assertThat(builder.pckPath, is(equalTo(expectedPckPath)));
        String expectedSolutionDir = BASE_DIR + "/" + expectedSrc + "/" + expectedPckPath + "/" + PROBLEM_NAME;
        assertThat(builder.solutionDir, is(equalTo(expectedSolutionDir)));
        String expectedTestDir = BASE_DIR + "/" + expectedTestSrc + "/" + expectedPckPath + "/" + PROBLEM_NAME;
        assertThat(builder.testDir, is(equalTo(expectedTestDir)));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("builder.pckPath = {}", builder.pckPath);
            LOGGER.debug("builder.solutionDir = {}", builder.solutionDir);
            LOGGER.debug("builder.testDir = {}", builder.testDir);
        }
    }

    @Test
    public void testBuildSourcePath() {
        String tplPath1 = "/template/Solution.vm";
        String srcPath1 = builder.buildSourcePath(tplPath1);
        assertThat(srcPath1, is(equalTo(builder.solutionDir + "/Solution.java")));
        String tplPath2 = "/template/TestRunner.vm";
        String srcPath2 = builder.buildSourcePath(tplPath2);
        assertThat(srcPath2, is(equalTo(builder.testDir + "/TestRunner.java")));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Built absolute source path = {}", srcPath1);
            LOGGER.debug("Built absolute source path = {}", srcPath2);
            LOGGER.debug("ProblemBuilder#buildSourcePath() method pass JUnit test!");
        }
    }

    @Test
    public void testGetFileWriter() {
        String wFile = TEST_RES_DIR + "/a/b/c/d/e/writer_test.txt";
        String wFileDir = TEST_RES_DIR + "/a";
        Writer fw = builder.getFileWriter(wFile);
        assertNotNull(fw);
        deleteRecursiveDirectory(new File(wFileDir));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ProblemBuilder#getWriter() method pass JUnit test!");
        }
    }

    @Test
    public void testWriteTemplates() {
        builder.writeTemplates();
        String answer = readFile(builder.solutionDir + "/Solution.java");
        assertThat("/", is(equalTo(answer.substring(0, answer.indexOf("*")))));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ProblemBuilder#writeTemplates() method pass JUnit test!");
        }
    }

    /**======================================== useful tools ============================================= */

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
