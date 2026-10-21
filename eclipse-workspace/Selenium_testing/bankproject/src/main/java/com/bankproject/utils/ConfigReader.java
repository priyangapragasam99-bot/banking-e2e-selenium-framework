package com.bankproject.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static final Properties properties = new Properties();

	// C:\Users\HP\eclipse-workspace\Selenium_testing\bankproject\src\test\resource

	static {
		try (FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/test/resource/config.properties")) {

			properties.load(fis);

		} catch (IOException e) {
			throw new RuntimeException("Unable to load config.properties", e);
		}
	}

	public static String getProperty(String key) {
		String value = properties.getProperty(key);

		if (value == null || value.trim().isEmpty()) {
			throw new IllegalArgumentException("Missing configuration property: " + key);
		}

		return value.trim();
	}

	public static int getIntProperty(String key) {
		return Integer.parseInt(getProperty(key));
	}

}
