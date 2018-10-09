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

import java.util.List;
import java.util.Properties;
import java.util.ArrayList;
import java.util.Arrays;

import java.io.InputStream;
import java.io.IOException;

import org.slf4j.Logger;


/**
 * ClassLoader can promise to find the "/template" directory in classpath.
 * No matter whether it's under "src/main/resources" directory in my develop
 * environment, or in "leetcode-helper.jar".
 * 
 * @author Wei SHEN
 */
class TemplateSeeker {

    // use the members of ProblemBuilder
    private static final Logger LOG = ProblemBuilder.LOGGER;
    /** TemplateSeeker is a stupid robot.
    The only thing he knows is to scan all velocity ".vm" templates under this direction.
    This is a relative path in classpath, which will be provided to Class.getResource() method. */
    private static final String TPL_DIR = "/template"; // Class.getResource() need leading slash to search from the root of classpath
    private static final String TPL_PROPS = "/conf/templates.properties";
    private static final String KEY = "templates";
    private static final String SPLITER = ",";
    

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<>();
        InputStream stream = TemplateSeeker.class.getResourceAsStream(TPL_PROPS);
        Properties props = new Properties();
        try {
            props.load(stream);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("properties = {}", props.getProperty(KEY));
        }
        String[] values = props.getProperty(KEY).split(SPLITER);
        if (LOG.isDebugEnabled()) {
            LOG.debug("property values = {}", Arrays.toString(values));
        }
        for (String value : values) {
            templates.add(TPL_DIR + "/" + value.trim());
        }
        return templates;
    }

}
