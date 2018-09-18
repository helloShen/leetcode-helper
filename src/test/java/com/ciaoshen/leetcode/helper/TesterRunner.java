/**
 * 单元测试引擎

 * @author Pixel SHEN
 */
package com.ciaoshen.leetcode.helper;

// java.util
import java.util.Properties;
// java.io
import java.io.FileNotFoundException;
// java.nio
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.DirectoryStream;
import java.nio.file.NotDirectoryException;
// uri
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;
// junit
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
// log4j
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

public class TesterRunner {

    private static final String SPLITER = "/";

    /** com.ciaoshen.leetcode.helper --> /com/ciaoshen/leetcode/helper */
    private static String pckNameToPath(String pck) {
        pck = pck.replaceAll("\\.",SPLITER);
        if (pck.substring(pck.length() - 1, pck.length()) != SPLITER) { // Class.getResource() method requires leading slash, such as "/com/ciaoshen/leetcode/helper"
            pck = SPLITER + pck;
        }
        return pck;
    }

    /** /com/ciaoshen/leetcode/helper --> com.ciaoshen.leetcode.helper  */
    private static String pckPathToName(String pck) {
        pck = pck.replaceAll(SPLITER, "\\.");
        return pck.substring(1, pck.length());
    }

    /** test all classes in target package */
    private static void testPackage(String pck) {
        String packagePath = pckNameToPath(pck); // in form of: "/com/ciaoshen/leetcode/helper"
        try {
            URL url = TesterRunner.class.getResource(packagePath);
            if (url == null) {
                throw new FileNotFoundException("The URL taken from \"" + pck + "\" is null!");
            }
            Path dir = Paths.get(url.toURI()); // throws URISyntaxException
            DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{class}"); // throws NotDirectoryException, IOException
            for (Path path : stream) {
                String packageName = pckPathToName(packagePath); // in form of: "com.ciaoshen.leetcode.helper"
                String classFile = path.getFileName().toString(); // in form of: "ProblemBuilder.class"
                String className = classFile.substring(0, classFile.length() - 6); // in form of : "ProblemBuilder"
                if (className.length() >= 4 && className.substring(className.length() - 4, className.length()).equals("Test")) { // JUnit test class name end with "Test", such as: "TemplateSeekerTest.class"
                    String fullName = packageName + "." + className;
                    System.out.println(fullName);
                    Class klass = Class.forName(fullName);
                    Result result = JUnitCore.runClasses(klass);
                    for (Failure failure : result.getFailures()) {
                        System.out.println(failure);
                    }
                    System.out.println("Class " + klass + " pass junit test? " + result.wasSuccessful() + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String LOG4J = "log4j.properties";

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("TestRunner: Must have 1 argument: package name.");
        }
        // Logger
        Properties log4jProps = PropertyScanner.load(LOG4J);
        PropertyConfigurator.configure(log4jProps);

        for (String pck : args) {
            System.out.println(">>>>>>>>>>>> Testing package [" + pck + "] <<<<<<<<<<<<\n");
            testPackage(pck);
        }
    }
}
