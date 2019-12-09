package com.simple.rpc.common.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtils {
    public static Properties getConfig(String path) {
        Properties props = null;
        try {
            props = new Properties();
            InputStream inputStream = PropertiesUtils.class.getResourceAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            props.load(bufferedReader);
            inputStream.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return props;
    }

    private PropertiesUtils(){
    }
}
