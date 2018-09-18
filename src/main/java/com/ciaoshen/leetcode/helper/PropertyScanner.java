/**
 * Given the name of properties file, return the Properties object.
 * Only PropertyScanner knows where to load ".properties".
 * 
 * @author Pixel SHEN
 */
package com.ciaoshen.leetcode.helper;

// java.util
import java.util.Properties;
import java.io.IOException;
// java.io
import java.io.InputStream;

class PropertyScanner {

    static final String CONF = "/conf";

    /**
     * @param propertyName such as: "layout.properties"
     * @return A Properties object.
     */
    static Properties load(String fileName) {
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
