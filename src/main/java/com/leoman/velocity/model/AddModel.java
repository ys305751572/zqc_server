package com.leoman.velocity.model;

import com.leoman.velocity.model.ui.Model;
import com.leoman.velocity.model.ui.UIWidget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddModel implements Model{

	private List<UIWidget> list;
	private Map<String,List<UIWidget>> map;
	
	public AddModel(List<UIWidget> list) {
		this.list = list;
		map = new HashMap<String,List<UIWidget>>();
		for (UIWidget widget : list) {
			if(widget.getColunmType() == ColunmType.defaultText) {
				List<UIWidget> widgets = map.get("default");
				if(widgets != null) {
					widgets.add(widget);
				}
				else {
					widgets = new ArrayList<UIWidget>();
					widgets.add(widget);
					map.put("default", widgets);
				}
			}
			
			if(widget.getColunmType() == ColunmType.image) {
				List<UIWidget> widgets = map.get("image");
				if(widgets != null) {
					widgets.add(widget);
				}
				else {
					widgets = new ArrayList<UIWidget>();
					widgets.add(widget);
					map.put("image", widgets);
				}
			}
			
			if(widget.getColunmType() == ColunmType.richText) {
				List<UIWidget> widgets = map.get("richText");
				if(widgets != null) {
					widgets.add(widget);
				}
				else {
					widgets = new ArrayList<UIWidget>();
					widgets.add(widget);
					map.put("richText", widgets);
				}
			}
		}
	}
	
	public List<UIWidget> getUIWidget() {
		return list;
	}
	
	public void setUIWidget(List<UIWidget> list) {
		this.list = list;
	}
}
