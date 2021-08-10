package com.muhammed.sabry.musalaapi.helper;

import java.util.Arrays;

public class AddressValidator {
	public static boolean isValidAddress(String address) {
		try {
			String[] parts = address.split("\\.");
			if (parts.length != 4) {
				return false;
			}
			return Arrays.stream(parts).noneMatch(part -> Integer.parseInt(part) > 255 || Integer.parseInt(part) < 0);
		} catch (Exception e) {
			return false;
		}
	}
}
