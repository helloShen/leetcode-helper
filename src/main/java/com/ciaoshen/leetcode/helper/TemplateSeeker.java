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
// java.util
import java.util.List;
import java.util.ArrayList;
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
 * ClassLoader can promise to find the "/template" directory in classpath.
 * No matter whether it's under "src/main/resources" directory in my develop
 * environment, or in "leetcode-helper.jar".
 * 
 * @author Wei SHEN
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
