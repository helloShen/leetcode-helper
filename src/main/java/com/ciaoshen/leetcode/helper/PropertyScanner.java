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

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;

/**
 * Given the name of properties file, return the Properties object.
 * 
 * @author Wei SHEN
 */
public class PropertyScanner {

    // use the members of ProblemBuilder
    private static final Logger LOG = ProblemBuilder.LOGGER;
    // only PropertyScanner knows where to load ".properties".
    static final String CONF = "/conf";


    /**
     * @param propertyName such as: "layout.properties"
     * @return A Properties object.
     */
    public static Properties load(String fileName) {
        String fullName = CONF + "/" + fileName;
        InputStream stream = PropertyScanner.class.getResourceAsStream(fullName);
        Properties props = new Properties();
        try {
            props.load(stream);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return props;
    }
}