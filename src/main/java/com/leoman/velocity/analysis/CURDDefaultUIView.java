package com.leoman.velocity.analysis;

import com.leoman.velocity.model.*;
import com.leoman.velocity.model.ui.EntityViewUI;

public class CURDDefaultUIView {
	
	public static EntityViewUI getDefaultEntityViewUI(EntityModel entity, EntityViewUI entityViewUI) {
		entityViewUI.setEntityModel(entity);
		return entityViewUI;
	}
}
