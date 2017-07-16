package com.example.kafkapoc.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GenderEnum {
	MALE("MALE"), FEMALE("FEMALE");

	private String value;

	GenderEnum(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static GenderEnum fromValue(String text) {
		for (GenderEnum b : GenderEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;

	}

}
