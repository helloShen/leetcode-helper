/**
 * Generate a project skeleton for a Leetcode Problem
 *
 * @author Pixel SHEN
 */
package com.ciaoshen.leetcode.helper;

// java.util
import java.util.Properties;
import java.util.List;
// java.io
import java.io.InputStream;
import java.io.File;
import java.io.Writer;
import java.io.StringWriter;
// import java.io.FileReader;
import java.io.FileWriter;
// import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
// java.nio (to creat recursive directory)
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
// java.net
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;
// velocity
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
// log4j
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

public class ProblemBuilder {

    /** ProblemBuilder knows only the name of the required properties files.
     * It doesn't know where to find them, but PropertyScanner knows. */
    private static final String LAYOUT = "layout.properties";
    private static final String LOG4J = "log4j.properties";

    private static final String SRC = "src";
    private static final String TEST_SRC = "test.src";
    private static final String PROBLEM = "problem";
    private static final String PACKAGE = "pck";
    private static final String UTIL = "util";

    private static final String JAVA_EXP = "java";

    // project directory layout extracted from layout.properties
    String src;             // relative source directory
    String testSrc;         // relative junit source directory

    // need 4 input arguments
    String root;            // args[0]: user working directory where "build.xml" locate (absolute path)
    String problemName;     // args[1]: problem name
    String pck;             // args[2]: package name
    String util;            // args[3]: common data structure library

    // construct absolute path
    String pckPath;         // replace "." by "/", which looks like "com/ciaoshen/leetcode/helper"
    String solutionDir;     // solution skeleton directory: [root/src/pckPath/problemName]
    String testDir;         // junit test skeleton directory: [root/testSrc/pckPath/problemName]

    // outer tools
    VelocityEngine ve;

    /**
     * @param args
     * Must have 4 arguments:
     *      1. root           // user working directory (where build.xml locate)
     *      2. problemName    // problem name such as: two_sum
     *      3. pck            // problem package name such as: com.ciaoshen.leetcode
     *      4. util           // leetcode data structure library such as: com.ciaoshen.leetcode.util
     */
    public ProblemBuilder(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Must have 4 arguments!");
        }
        // load 4 input arguments
        root = args[0];
        problemName = args[1];
        pck = args[2];
        util = args[3];

        // PropertyScanner scan properties for ProblemBuilder
        Properties layoutProps = PropertyScanner.load(LAYOUT);
        src = layoutProps.getProperty(SRC);
        testSrc = layoutProps.getProperty(TEST_SRC);

        // construct absolute dir
        pckPath = pck.replaceAll("\\.","/"); // package name with '/' substituted for '.'
        solutionDir = root + "/" + src + "/" + pckPath + "/" + problemName;
        testDir = root + "/" + testSrc + "/" + pckPath + "/" + problemName;

        // create Velocity engine
        ve = new VelocityEngine();

        /** load velocity template from classpath */
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();
    }

    /**
     * New version call TemplateSeeker, which load template by ClassLoader
     * Fill all templates under "/template" directory
     */
    public void writeTemplates() {
        List<String> templates = TemplateSeeker.getTemplates();
        for (String template : templates) {
            writeTemplate(template);
        }
    }

    /**
     * Call Velociy to fill a .vm template
     * @param tplPath absolute path of a velocity .vm template
     * @param dst absolute path where store the generated .java source file
     */
    void writeTemplate(String path) {
        System.out.println("Writing " + path + " ... ");
        Template t = ve.getTemplate(path);
        VelocityContext context = new VelocityContext();
        // assign variables
        context.put(PROBLEM, problemName);
        context.put(PACKAGE, pck);
        context.put(UTIL, util);
        Writer sw = new StringWriter();
        t.merge(context, sw);
        Writer fw = getFileWriter(buildSourcePath(path));
        try {
            fw.write(sw.toString());
            fw.flush();
            fw.close();
            sw.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Convert "/template/Solution.vm" to "/Users/Wei/.../Solution.java"
     * 2 types of source:
     *      1. solution source: to solutionDir
     *      2. junit source: to testDir
     * @param f velocity template file
     @ @return absolute path of the corresponding java source file
     */
    String buildSourcePath(String path) {
        String fullFileName = path.substring(path.lastIndexOf('/') + 1, path.length()); // such as: "Solution.vm"
        String fileName = fullFileName.substring(0, fullFileName.indexOf(".")); // such as: "Solution"
        if (fileName.length()>= 4 && fileName.substring(0,4).equals("Test")) {
            return testDir + "/" + fileName + "." + JAVA_EXP; // deploy JUnit test code under testDir
        } else {
            return solutionDir + "/" + fileName + "." + JAVA_EXP; // deploy Solutions code under mainDir
        }
    }

    /**
     * Get a FileWriter decorated by BufferedWriter (using java.nio)
     * @param  path absolute path of that file
     * @return      A FileWriter decorated by BufferedWriter
     */
    Writer getFileWriter(String path) {
        Path directory = Paths.get(path.substring(0, path.lastIndexOf("/"))); // eliminate filename
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

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Must have 4 argument!");
        }
        // Logger
        Properties log4jProps = PropertyScanner.load(LOG4J);
        PropertyConfigurator.configure(log4jProps);
        // ProblemBuilder
        ProblemBuilder builder = new ProblemBuilder(args);
        builder.writeTemplates();
    }
}
