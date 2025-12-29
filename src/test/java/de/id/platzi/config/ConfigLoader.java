package de.id.platzi.config;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigLoader {

    private ConfigLoader() {}

    public static Config load() {
        Properties properties = new Properties();
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("config.properties not found");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }

        return new Config(
                get(properties, "baseUrl"),
                get(properties, "email"),
                get(properties, "password")
        );
    }

    public static String get(Properties properties, String key) {
        String p = System.getProperty(key);
        if (p == null || p.isBlank()) p = properties.getProperty(key);
        if (p == null || p.isBlank()) throw new IllegalStateException("Missing property: " + key);
        return p.trim();
    }
}
