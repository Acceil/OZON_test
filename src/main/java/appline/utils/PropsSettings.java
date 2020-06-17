package appline.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropsSettings {
    private final Properties properties = new Properties();
    private static PropsSettings INSTANCE = null;

    private PropsSettings() {
        try {
            String param = System.getProperty("prop");
            properties.load(new FileInputStream(new File("src/test/resources/"
                    + (param == null ? "chrome" : param) + ".properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropsSettings getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PropsSettings();
        }
        return INSTANCE;
    }

    public Properties getProperties() {
        return properties;
    }
}

