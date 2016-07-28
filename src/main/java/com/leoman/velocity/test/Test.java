package com.leoman.velocity.test;

import com.leoman.velocity.analysis.CURDCoreAnalysis;
import com.leoman.velocity.analysis.CURDDefaultUIView;
import com.leoman.velocity.analysis.CURDFileCreateAnalysis;
import com.leoman.velocity.core.CodeGenerator;
import com.leoman.velocity.file.NewFile;
import com.leoman.velocity.model.EntityModel;
import com.leoman.velocity.model.ui.EntityViewUI;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		
		String path = "F:/workspace_velocity/velocity/src/main/java/velocity/book/entity/Book.java";
		CURDCoreAnalysis util = CURDCoreAnalysis.getInstance();
		EntityModel entity = util.analysis(path);
		EntityViewUI entityViewUI = null;

		entityViewUI = CURDDefaultUIView.getDefaultEntityViewUI(entity,entityViewUI);
		String projectPath = path.substring(0, path.indexOf("/src/main/java/"));
		List<NewFile> createFiles = CURDFileCreateAnalysis.getCreateFileList(projectPath, entityViewUI);
		try {
			CodeGenerator.generateCode(createFiles, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static EntityViewUI initTestData() {

		return null;
	}
}