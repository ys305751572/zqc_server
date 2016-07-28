package com.leoman.velocity.model;

public class FieldModel {
	private String name;
	private String type;

	public FieldModel(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public FieldModel() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return this.type;
	}

	public String getNameToFirstLetterUpper() {
		return this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
	}

	public String getSimpleType() {
		return this.type.substring(this.type.lastIndexOf(".") + 1, this.type.length());
	}
}
