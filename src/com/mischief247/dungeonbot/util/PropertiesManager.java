package com.mischief247.dungeonbot.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {
    public static String token = "";
    private static final String fileName = "dungeonbot.properties";
    private static final Properties properties = new Properties();
    public static void load() throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            boolean created = file.createNewFile();
            if (!created) {
                throw new IOException();
            }
        }

        InputStream inputStream = new FileInputStream(fileName);
        properties.load(inputStream);
        token = properties.getProperty("Token", "!");
    }

    public static void save() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);

            try {
                properties.store(fileOutputStream, "");
            } catch (IOException io) {
                io.printStackTrace();
            }
        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}
