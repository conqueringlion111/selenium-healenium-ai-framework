package com.pullapart.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {

    private static final Properties props = new Properties();
    private static boolean loaded = false;

    private ConfigManager() {}

    public static synchronized void load() {
        if (loaded) return;
        try (InputStream in = ConfigManager.class.getClassLoader()
                .getResourceAsStream("testconfig.properties")) {
            if (in == null) {
                throw new RuntimeException("testconfig.properties not found on classpath");
            }
            props.load(in);
            loaded = true;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load testconfig.properties", e);
        }
    }

    public static String get(String key) {
        // System properties override file
        String sysVal = System.getProperty(key);
        if (sysVal != null) return sysVal;
        return props.getProperty(key);
    }

    public static boolean getBoolean(String key, boolean defaultVal) {
        String val = get(key);
        if (val == null) return defaultVal;
        return Boolean.parseBoolean(val);
    }

}
