package com.tangbo.test.utils;

import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


public class ReadFileUtil {

    public static File readText(String relativePath) {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:" + relativePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static String fileCastToString(InputStream inputStream) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line).append("\n");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return result.toString();
    }

    public static String readTextFileAndGetContent(String relativePath) {
        File file = readText(relativePath);
        String content = null;
        try {
            content = fileCastToString(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static Properties readPropertiesFile(String fileName) {
        Properties properties = new Properties();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            properties.load(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;

    }
}

