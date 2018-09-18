/**
 * Get the path of template under classpath, such as: "/template/Solution.vm"
 * 
 * @author Pixel SHEN
 */
package com.ciaoshen.leetcode.helper;
// java.util
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
// java.io
import java.io.FileNotFoundException;
// java.nio
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.DirectoryStream;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.NotDirectoryException;
// uri
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;

/**
 * The TemplateSeeker uses ClassLoader to load ".vm" velocity template files
 * ClassLoader can promise to find the "/template" directory in classpath.
 * No matter whether it's under "src/main/resources" directory in my develop
 * environment, or in "leetcode-helper.jar".
 */
class TemplateSeeker {

    /** TemplateSeeker is a stupid robot.
    The only thing he knows is to scan all velocity ".vm" templates under this direction.
    This is a relative path in classpath, which will be provided to Class.getResource() method. */
    private static final String TPL_DIR = "/template"; // Class.getResource() need leading slash to search from the root of classpath

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<>();
        try {
            URL url = TemplateSeeker.class.getResource(TPL_DIR);
            if (url == null) throw new FileNotFoundException();
            Path dir = Paths.get(url.toURI()); // throws URISyntaxException
            DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.{vm}"); // throws NotDirectoryException, IOException
            for (Path path : stream) {
                templates.add(TPL_DIR + "/" + path.getFileName().toString());
            }
        } catch (FileNotFoundException | NotDirectoryException ex) {
            System.out.println("ERR: TemplateSeeker: " + TPL_DIR + " is not a directory.");
            ex.printStackTrace();
        } catch (URISyntaxException use) {
            System.out.println("ERR: TemplateSeeker: URI can not be convert to Path.");
            use.printStackTrace();
        } finally {
            return templates;
        }
    }

}
