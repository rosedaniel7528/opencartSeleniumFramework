package com.opencart.config;

public enum ExecutionMode {
	LOCAL, REMOTE, REMOTE_GIT;

	public static ExecutionMode from(String value) {
		if (value == null || value.isBlank()) {
			return LOCAL;
		}
		for (ExecutionMode mode : values()) {
			if (mode.name().equalsIgnoreCase(value)) {
				return mode;
			}
		}
		throw new IllegalArgumentException("Platform name entered is not supported: " + value);
	}
}
