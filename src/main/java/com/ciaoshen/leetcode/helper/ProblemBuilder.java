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

import java.util.Properties;
import java.util.List;

import java.io.File;
import java.io.Writer;
import java.io.StringWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generate and deploy solution skeletons and junit test framework for leetcode problem.
 *
 * @author Wei SHEN
 */
public class ProblemBuilder {

    /** ProblemBuilder knows only the name of the required properties files.
     * It doesn't know where to find them, but PropertyScanner knows. */
    private static final String LAYOUT = "layout.properties";
    private static final String LOG4J = "log4j.properties";
    /** key names used in properties file */
    private static final String SRC = "src";
    private static final String TEST_SRC = "test.src";
    private static final String PROBLEM = "problem";
    private static final String PACKAGE = "pck";
    private static final String UTIL = "util";
    private static final String MEMBERS = "members";
    /** extension of java source file */
    private static final String JAVA_EXP = "java";

    // user provides 5 important arguments to describe a problem
    String root;            // args[0]: user working directory where "build.xml" locate (absolute path)
    String problemName;     // args[1]: problem name
    String pck;             // args[2]: package name
    String util;            // args[3]: leetcode data structure library
    String members;         // args[4]: members of a class

    // project layout configuration extracted from layout.properties
    String src;             // source directory (relative to project root)
    String testSrc;         // junit test source directory (relative to project root)

    // absolute path constructed from the above relative path
    String pckPath;         // package sub-path with "/" substitued for "."
    String solutionDir;     // problem solution source full path (absolute)
    String testDir;         // junit test source full path (absolute)

    // Velocity
    VelocityEngine ve;

    /** collect parameters */
    public ProblemBuilder(String[] args) {
        if (args.length != 5) {
            throw new IllegalArgumentException("Must have 5 arguments!");
        }
        // user provides 4 arguments
        root = args[0];             // such as: /Users/Wei/github/leetcode-helper
        problemName = args[1];      // such as: two_sum
        pck = args[2];              // such as: com.ciaoshen.leetcode
        util = args[3];             // such as: com.ciaoshen.leetcode.util
        members = args[4];          // such as: int add(int a, int b) {}
        if (LOGGER.isDebugEnabled()) {
            for (int i = 0; i < args.length; i++) {
                LOGGER.debug("arg[{}] = {}", i, args[i]);
            }
        }
        // PropertyScanner scan the other properties 
        Properties layoutProps = PropertyScanner.load(LAYOUT);
        src = layoutProps.getProperty(SRC);
        testSrc = layoutProps.getProperty(TEST_SRC);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("src = {}", src);
            LOGGER.debug("testSrc = {}", testSrc);
        }

        // construct full-directory 
        pckPath = pck.replaceAll("\\.","/"); // package sub-path with '/' substituted for '.'
        solutionDir = root + "/" + src + "/" + pckPath + "/" + problemName;
        testDir = root + "/" + testSrc + "/" + pckPath + "/" + problemName;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("pckPath = {}", pckPath);
            LOGGER.debug("solutionDir = {}", solutionDir);
            LOGGER.debug("testDir = {}", testDir);
        }

        // ClasspathResourceLoader is a simple loader that will load templates from the classpath. 
        ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
    }

    /** Velociy load and fill templates */
    public void writeTemplates() {
        List<String> templates = TemplateSeeker.getTemplates();
        if (LOGGER.isDebugEnabled()) {
            for (String template : templates) {
                LOGGER.debug("Find template {}", template);
            }
        }
        for (String template : templates) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("[leetcode-helper] fill out template --> {}", template);
            }
            Template t = ve.getTemplate(template);
            VelocityContext context = new VelocityContext();
            // assign variables
            context.put(PROBLEM, problemName);
            context.put(PACKAGE, pck);
            context.put(UTIL, util);
            context.put(MEMBERS, members);
            Writer sw = new StringWriter();
            t.merge(context, sw);
            Writer fw = getFileWriter(buildSourcePath(template));
            try {
                fw.write(sw.toString());
                fw.flush();
                fw.close();
                sw.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    /**
     * Construct full path of generated skeleton file.
     * Ex: given "/template/Solution.vm"
     * Absolute path = "/Users/Wei/github/leetcode/main/java/com/ciaoshen/leetcode/two_sum/Solution.java"
     * @param path velocity template file path (relative to classpath)
     @ @return absolute path of generated solution skeleton
     */
    String buildSourcePath(String path) {
        String fullFileName = path.substring(path.lastIndexOf("/") + 1, path.length()); 
        String fileName = fullFileName.substring(0, fullFileName.indexOf(".")); 
        if (fileName.length()>= 4 && fileName.substring(0,4).equals("Test")) {
            return testDir + "/" + fileName + "." + JAVA_EXP;
        } else {
            return solutionDir + "/" + fileName + "." + JAVA_EXP;
        }
    }

    /**
     * Get a FileWriter decorated by BufferedWriter (using java.nio)
     * @param  path absolute path of that file
     * @return A FileWriter decorated by BufferedWriter
     */
    Writer getFileWriter(String path) {
        Path directory = Paths.get(path.substring(0, path.lastIndexOf("/"))); 
        try {
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            return Files.newBufferedWriter(Paths.get(path));
        } catch (IOException ioe) { // createDirectories() && newBufferedWriter()
            ioe.printStackTrace();
            return null;
        }
    }

    // call from slf4j facade
    static final Logger LOGGER = LoggerFactory.getLogger(ProblemBuilder.class);

    public static void main(String[] args) {
        if (args.length != 5) {
            throw new IllegalArgumentException("Must have 5 arguments!");
        }
        // use log4j as Logger implementaion
        Properties log4jProps = PropertyScanner.load(LOG4J);
        PropertyConfigurator.configure(log4jProps);
        // ProblemBuilder
        ProblemBuilder builder = new ProblemBuilder(args);
        builder.writeTemplates();
    }
}
