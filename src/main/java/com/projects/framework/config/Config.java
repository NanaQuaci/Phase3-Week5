package com.projects.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in != null) {
                props.load(in);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        // 1. System property (highest priority)
        String sys = System.getProperty(key);
        if (sys != null) return sys;

        // 2. Environment variable
        String env = System.getenv(key);
        if (env != null) return env;

        // 3. config.properties fallback
        return props.getProperty(key);
    }

    public static boolean contains(String key) {
        return System.getProperty(key) != null
                || System.getenv(key) != null
                || props.containsKey(key);
    }
}
