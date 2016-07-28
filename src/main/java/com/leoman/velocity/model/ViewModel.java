package com.leoman.velocity.model;

import com.leoman.velocity.model.ui.Model;
import com.leoman.velocity.model.ui.UIWidget;

import java.util.List;

public class ViewModel implements Model{

	private List<UIWidget> list;
	
	public ViewModel(List<UIWidget> list) {
		this.list = list;
	}
	
	public List<UIWidget> getUIWidget() {
		return list;
	}

	public void setList(List<UIWidget> list) {
		this.list = list;
	}
}
