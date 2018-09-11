/**
 * Generate a project skeleton for a Leetcode Problem
 *
 * @author Pixel SHEN
 */
package com.ciaoshen.leetcode.helper;
// java.io
import java.io.File;
import java.io.Writer;
import java.io.StringWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
// java.nio (to creat recursive directory)
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
// velocity
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;


public class ProblemBuilder {

    /**
     * @param args Must have 4 arguments:
     * Must have 6 arguments:
     *      1. templateDir              --> where is the velocity templates
     *      2. srcDir / 3. testDir  |
     *      4. package              |-----> problem source code / test source code directory
     *      5. problemName          |
     *      6. util                     --> leetcode common data structure
     *
     *
     * Firstly, the "package" argument is just a package name such as：
     *      > package = "com.ciaoshen.leetcode.helper"
     * transform to,
     *      > packagePath = "com/ciaoshen/leetcode/helper"
     *
     * Then construct problem source code directory,
     *      > problemDestination = [srcDir/packagePath/problemName]
     * And also, test source code directory,
     *      > testDir = [testDir/packagePath/problemName]
     *
     * Velocity templates are located in templateDir
     *
     * util is the leetcode commonly used data structure library, such as,
     *      > com.ciaoshen.leetcode.myUtils
     */
    public ProblemBuilder(String[] args) {
        if (args.length != 6) {
            throw new IllegalArgumentException("Must have 6 arguments!");
        }
        // 6 origial arguments
        templateDir = args[0];
        srcDir = args[1];
        testDir = args[2];
        pck = args[3];
        problemName = args[4];
        util = args[5];
        // construct 3 important dir
        packagePath = pck.replaceAll("\\.","/");
        problemDestination = srcDir + "/" + packagePath + "/" + problemName;
        testDestination = testDir + "/" + packagePath + "/" + problemName;
        // create Velocity engine
        ve = new VelocityEngine();
        // tell velocity where to find .vm template files
        ve.setProperty("file.resource.loader.path", templateDir);
        ve.init();
    }

    /**
     * Call writeTemplate() for each template in templateDir
     */
    public void writeTemplates() {
        File[] templates = new File(templateDir).listFiles();
        for (File t : templates) {
            writeTemplate(t);
        }
    }


    /**================= 【not public】 ====================*/

    private final String JAVA_EXP = "java";
    /**
     * Convert "Solution.vm" to "/Users/Wei/.../Solution.java"
     * 2 types of source code:
     *      1. main source code: to main.dir
     *      2. junit source code: to test.dir
     * @param f velocity template file
     @ @return absolute path of the corresponding java source file
     */
    String buildSourcePath(File f) {
        String fullFileName = f.getName(); // such as: "Solution.vm"
        String fileName = fullFileName.substring(0, fullFileName.indexOf(".")); // such as: "Solution"
        if (fileName.length() >= 4 && fileName.substring(0,4).equals("Test")) {
            return testDestination + "/" + fileName + "." + JAVA_EXP; // deploy JUnit test code under testDir
        } else {
            return problemDestination + "/" + fileName + "." + JAVA_EXP; // deploy Solutions code under mainDir
        }
    }


    // void writeTemplate(File tplFile) {
    //     System.out.println("I'm writing " + buildSourcePath(tplFile));
    // }
    /**
     * Call Velociy to fill a .vm template
     * @param tplPath absolute path of a velocity .vm template
     * @param dst absolute path where store the generated .java source file
     */
    void writeTemplate(File tplFile) {
        System.out.println("Writing " + tplFile.getName() + " ... ");
        Template t = ve.getTemplate(tplFile.getName());
        VelocityContext context = new VelocityContext();
        // assign variables
        context.put("problem", problemName);
        context.put("pck", pck);
        context.put("util", util);
        Writer sw = new StringWriter();
        t.merge(context, sw);
        Writer fw = getFileWriter(buildSourcePath(tplFile));
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



    /**
     * Read the content of a file
     * @param  path absolute path of that file
     * @return      Content of that file in an entire String
     */
    String readFile(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader r = new BufferedReader(new FileReader(new File(path)));
            String line = "";
            try {
                while ((line = r.readLine()) != null) {
                    sb.append(line);
                }
            } finally {
                r.close();
            }
        } catch (FileNotFoundException fnfe) { // new FileReader()
            throw new RuntimeException("ProblemBuilder#readFile(): File not found: <" + path + ">.");
        } catch (IOException ioe) { // r.readLine(), r.close()
            throw new RuntimeException("ProblemBuilder#readFile(): IOException while reading file: <" + path + ">.");
        }
        return sb.toString();
    }

    /**
     * Junit test need constructed absolute path
     * @return the constructed absolution path to the directory of all generated .java files
     */
    String getProblemDestination() {
        return problemDestination;
    }
    /**
     * Junit test need constructed absolute path
     * @return the constructed absolution path to the directory of all generated .java files
     */
    String getTestDestination() {
        return testDestination;
    }

    private VelocityEngine ve;

    private String templateDir;             // template directory
    private String srcDir;                  // source code directory for all leetcode problems
    private String testDir;                 // junit source code directory
    private String pck;                     // package name such as: com.ciaoshen.leetcode.helper
    private String problemName;             // name of this problem
    private String util;                    // common data structure such as: com.ciaoshen.leetcode.myUtils

    /*
     * replace the "." by "/" in package name
     *      package = com.ciaoshen.leetcode.helper
     *      packagePath = com/ciaoshen/leetcode/helper
     */
    private String packagePath;             // package path such as: com/ciaoshen/leetcode/helper
    /*
     * problem source code absolute path
     *      [srcDir/packagePath/problemName]
     */
    private String problemDestination;
    /*
     * problem junit test source code absolute path
     *      [testDir/packagePath/problemName]
     */
    private String testDestination;

    /**
     * Must have 6 arguments:
     *      1. templateDir              --> where is the velocity templates
     *      2. srcDir / 3. testDir  |
     *      4. package              |-----> problem directory = [src_dir/package_path/problem_name]
     *      5. problemName          |
     *      6. util                     --> leetcode common data structure
     *
     */
    public static void main(String[] args) {
        if (args.length != 6) {
            throw new IllegalArgumentException("Must have 4 argument!");
        }
        ProblemBuilder builder = new ProblemBuilder(args);
        builder.writeTemplates();
    }
}
