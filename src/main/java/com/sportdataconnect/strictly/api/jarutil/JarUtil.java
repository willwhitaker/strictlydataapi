package com.sportdataconnect.strictly.api.jarutil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class JarUtil {

    public static BufferedReader openReaderForResource(String resourceName) {
        InputStream resourceAsStream = JarUtil.class.getResourceAsStream("/com/sportdataconnect/strictly/api/" + resourceName);
        return new BufferedReader(new InputStreamReader(resourceAsStream, Charset.forName("UTF-8")));
    }
}
