package com.opencart.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonDataReader {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	private JsonDataReader() {
	}

	public static List<HashMap<String, String>> read(String resourcePath) throws IOException {
		try (InputStream input = JsonDataReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
			if (input == null) {
				throw new FileNotFoundException("Test data file not found on classpath: " + resourcePath);
			}
			return MAPPER.readValue(input, new TypeReference<List<HashMap<String, String>>>() {
			});
		}
	}

	public static Object[][] asDataProvider(List<HashMap<String, String>> rows) {
		Object[][] data = new Object[rows.size()][1];
		for (int i = 0; i < rows.size(); i++) {
			data[i][0] = rows.get(i);
		}
		return data;
	}
}
