package com.leoman.velocity.model;

import java.util.ArrayList;
import java.util.List;

public class EntityModel {

	private String name;

	private String className;

	private String packpageName;

	private List<FieldModel> fields = new ArrayList<FieldModel>();

	public List<FieldModel> getFields() {
		return fields;
	}

	public void setFields(List<FieldModel> fields) {
		this.fields = fields;
	}

	public List<PersistenceFieldModel> getPersistenceFieldModel() {
		List<PersistenceFieldModel> models = new ArrayList();
		for (FieldModel fieldModel : getFields()) {
			if ((fieldModel instanceof PersistenceFieldModel)) {
				models.add((PersistenceFieldModel) fieldModel);
			}
		}
		return models;
	}
	
	public String getPackpageName() {
		return packpageName;
	}

	public void setPackpageName(String packpageName) {
		this.packpageName = packpageName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
