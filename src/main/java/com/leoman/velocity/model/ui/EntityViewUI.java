package com.leoman.velocity.model.ui;

import com.leoman.velocity.model.AddModel;
import com.leoman.velocity.model.EntityModel;
import com.leoman.velocity.model.ListModel;
import com.leoman.velocity.model.QueryModel;

public class EntityViewUI {

	public static final String TYPE_ADD = "add";
	public static final String TYPE_LIST = "list";

	private EntityModel entityModel;
	private ListModel listModel;
	private QueryModel queryModel;
	private AddModel addModel;

	public EntityModel getEntityModel() {
		return entityModel;
	}

	public void setEntityModel(EntityModel entityModel) {
		this.entityModel = entityModel;
	}

	public ListModel getListModel() {
		return listModel;
	}

	public void setListModel(ListModel listModel) {
		this.listModel = listModel;
	}

	public QueryModel getQueryModel() {
		return queryModel;
	}

	public void setQueryModel(QueryModel queryModel) {
		this.queryModel = queryModel;
	}

	public AddModel getAddModel() {
		return addModel;
	}

	public void setAddModel(AddModel addModel) {
		this.addModel = addModel;
	}
}
