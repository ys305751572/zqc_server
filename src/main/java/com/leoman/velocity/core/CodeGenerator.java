package com.leoman.velocity.core;

import org.apache.velocity.VelocityContext;
import com.leoman.velocity.file.NewFile;
import com.leoman.velocity.model.EntityModel;
import com.leoman.velocity.util.VelocityContextUtils;

import java.util.List;

public class CodeGenerator {
	
//	public void generateCode(String javaPath) throws Exception {
//		CURDCoreAnalysis util = CURDCoreAnalysis.getInstance();
//		EntityModel entity = util.analysis(javaPath);
//		VelocityContext context = VelocityContextUtils.getVelocityContext();
//		context.put("entity", entity);
//		EntityViewUI entityViewUI = CURDDefaultUIView.getDefaultEntityViewUI(entity);
//		List<NewFile> createFiles = CURDFileCreateAnalysis.getCreateFileList(util.getProject(), entityViewUI);
//		for (NewFile newFile : createFiles) {
//			newFile.process();
//		}
//	}
//
//	public static List<NewFile> getGenerateFiles(EntityViewUI entityViewUI, MavenProject project) {
//		VelocityContext context = VelocityContextUtils.getVelocityContext();
//		context.put("entity", entityViewUI.getEntityModel());
//		List<NewFile> createFiles = CURDFileCreateAnalysis.getCreateFileList(project, entityViewUI);
//		return createFiles;
//	}

	public static void generateCode(List<NewFile> createFiles,EntityModel entity)
			throws Exception {
		VelocityContext context = VelocityContextUtils.getVelocityContext();
		context.put("entity", entity);
		for (NewFile newFile : createFiles) {
			newFile.process();
		}
	}
}
