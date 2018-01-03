package ru.vsu.utils;

import ru.vsu.ASTPrinter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurator {
    public static String getPrinterFile() {
        return ASTPrinter.class.getClassLoader().getResource("js/" + getProperties().getProperty("printer")).getFile();
    }

    public static String getInterpreterFile() {
        return ASTPrinter.class.getClassLoader().getResource("js/" + getProperties().getProperty("interpreter")).getFile();
    }

    public static String getCompilerFile() {
        return ASTPrinter.class.getClassLoader().getResource("js/" + getProperties().getProperty("compiler")).getFile();
    }

    private static Properties getProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            String filename = "config.properties";
            input = ASTPrinter.class.getClassLoader().getResourceAsStream(filename);
            prop.load(input);

            return prop;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
