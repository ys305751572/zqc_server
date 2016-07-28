package com.leoman.velocity.analysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.leoman.velocity.file.*;
import com.leoman.velocity.model.EntityModel;
import com.leoman.velocity.model.ui.EntityViewUI;

import java.util.ArrayList;
import java.util.List;

public class CURDFileCreateAnalysis {

	private static final Logger logger = LoggerFactory.getLogger(CURDFileCreateAnalysis.class);

	public static List<NewFile> getCreateFileList(String projectPath, EntityViewUI entityUI) {
		List<NewFile> newFiles = new ArrayList<NewFile>();
		createDaoImplFile(projectPath, entityUI, entityUI.getEntityModel(), newFiles);
		createServiceFile(projectPath, entityUI, entityUI.getEntityModel(), newFiles);
		createServiceImplFile(projectPath, entityUI, entityUI.getEntityModel(), newFiles);
		createControllerFile(projectPath, entityUI, entityUI.getEntityModel(), newFiles);
		
		// ==========================================================================
		createListPage(projectPath, entityUI, entityUI.getEntityModel(), newFiles);
		createAddPage(projectPath, entityUI, entityUI.getEntityModel(), newFiles);
//		createDetailPage(projectPath, entityUI, entityUI.getEntityModel(), newFiles);
		return newFiles;
	}
	
//	private static void createDetailPage(String projectPath, EntityViewUI entityUI, EntityModel entityModel,
//			List<NewFile> newFiles) {
//		DetailPageNewFile detailNewFile = new DetailPageNewFile("detail",projectPath, entityModel.getPackpageName(),entityUI);
//		newFiles.add(detailNewFile);
//	}

	private static void createAddPage(String projectPath, EntityViewUI entityUI, EntityModel entityModel,
			List<NewFile> newFiles) {
		AddPageNewFile addPageNewFile = new AddPageNewFile("add",projectPath, entityModel.getPackpageName(),entityUI);
		newFiles.add(addPageNewFile);
	}

	private static void createListPage(String projectPath, EntityViewUI entityUI, EntityModel entityModel,
			List<NewFile> newFiles) {
		ListPageNewFile controllerNewFile = new ListPageNewFile("list",projectPath, entityModel.getPackpageName(),entityUI);
		newFiles.add(controllerNewFile);
	}

	private static void createControllerFile(String projectPath, EntityViewUI entityUI, EntityModel entityModel,
			List<NewFile> newFiles) {
		ControllerNewFile controllerNewFile = new ControllerNewFile(entityModel.getName() + "Controller", projectPath, entityModel.getPackpageName(), entityUI);
		newFiles.add(controllerNewFile);
	}

	private static void createDaoImplFile(String projectPath, EntityViewUI entityUI, EntityModel entityModel,
			List<NewFile> newFiles) {
		DaoNewFile daoNewFile = new DaoNewFile(entityModel.getName() + "Dao", projectPath, entityModel.getPackpageName(), entityModel);
		newFiles.add(daoNewFile);
	}

	private static void createServiceFile(String projectPath, EntityViewUI entityUI, EntityModel entityModel,
			List<NewFile> newFiles) {
		ServiceNewFile serviceNewFile = new ServiceNewFile(entityModel.getName() + "Service", projectPath, entityModel.getPackpageName(), entityModel);
		newFiles.add(serviceNewFile);
	}

	private static void createServiceImplFile(String projectPath, EntityViewUI entityUI, EntityModel entity,
			List<NewFile> newFiles) {
		ServiceImplNewfile serviceImplNewfile = new ServiceImplNewfile(entity.getName() + "ServiceImpl", projectPath, entity.getPackpageName(), entity, entityUI);
		newFiles.add(serviceImplNewfile);
	}
}
