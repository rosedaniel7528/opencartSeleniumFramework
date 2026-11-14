package com.opencart.config;

public enum Browser {
	CHROME, FIREFOX;

	public static Browser from(String name) {
		for (Browser browser : values()) {
			if (browser.name().equalsIgnoreCase(name)) {
				return browser;
			}
		}
		throw new IllegalArgumentException("Browser name entered is not supported: " + name);
	}
}
