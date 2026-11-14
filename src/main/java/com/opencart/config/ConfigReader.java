package com.opencart.config;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public final class ConfigReader {

	private static final String CONFIG_FILE = "config.properties";
	private static final Properties PROPERTIES = load();

	private ConfigReader() {
	}

	public static String get(String key) {
		String value = System.getProperty(key);
		if (isBlank(value)) {
			value = System.getenv(key.toUpperCase().replace('.', '_'));
		}
		if (isBlank(value)) {
			value = PROPERTIES.getProperty(key);
		}
		if (isBlank(value)) {
			throw new IllegalStateException("Missing configuration value for: " + key);
		}
		return value.trim();
	}

	public static String baseUrl() {
		return get("base.url");
	}

	public static String gridUrl() {
		return get("grid.url");
	}

	public static String browser() {
		return get("browser");
	}

	public static ExecutionMode executionMode() {
		return ExecutionMode.from(get("platform"));
	}

	public static Duration explicitWait() {
		return Duration.ofSeconds(getInt("explicit.wait"));
	}

	public static int retryCount() {
		return getInt("retry.count");
	}

	private static int getInt(String key) {
		String value = get(key);
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new IllegalStateException("Configuration value for " + key + " must be a number: " + value, e);
		}
	}

	private static Properties load() {
		Properties properties = new Properties();
		try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
			if (input != null) {
				properties.load(input);
			}
		} catch (IOException e) {
			throw new IllegalStateException("Could not read " + CONFIG_FILE, e);
		}
		return properties;
	}

	private static boolean isBlank(String value) {
		return value == null || value.isBlank();
	}
}
