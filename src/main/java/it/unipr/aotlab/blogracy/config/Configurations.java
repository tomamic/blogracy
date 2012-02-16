/*
 * Copyright (c)  2012 Enrico Franchi, Michele Tomaiuolo and University of Parma.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package it.unipr.aotlab.blogracy.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * User: enrico
 * Package: it.unipr.aotlab.blogracy.config
 * Date: 1/24/12
 * Time: 11:37 AM
 */
public class Configurations {

    public static final String BLOGRACY = "blogracy";
    private static final String PATHS_FILE = "blogracyPaths.properties";
    private static final String BLOGRACY_PATHS_STATIC = "blogracy.paths.static";
    private static final String BLOGRACY_PATHS_TEMPLATES = "blogracy.paths.templates";
    private static final String BLOGRACY_PATHS_ROOT = "blogracy.paths.root";

    static private Properties loadPathProperties() throws IOException {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        InputStream is = loader.getResourceAsStream(PATHS_FILE);

        if (is != null) {
            Properties properties = new Properties();
            properties.load(is);
            return properties;
        } else {
            return new Properties();
        }
    }

    static public PathConfig getPathConfig() {
        try {
            return new PathConfig() {
                // TODO: this should absolutely come from the outside!
                Properties pathProperties = loadPathProperties();

                @Override
                public String getStaticFilesDirectoryPath() {
                    return pathProperties.getProperty(BLOGRACY_PATHS_STATIC);
                }

                @Override
                public String getTemplatesDirectoryPath() {
                    return pathProperties.getProperty(BLOGRACY_PATHS_TEMPLATES);
                }

                @Override
                public String getRootDirectoryPath() {
                    return pathProperties.getProperty(BLOGRACY_PATHS_ROOT);
                }
            };
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}