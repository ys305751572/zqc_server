package com.leoman.velocity.file;

import org.apache.velocity.VelocityContext;
import com.leoman.velocity.model.EntityModel;
import com.leoman.velocity.model.ui.EntityViewUI;
import com.leoman.velocity.util.VelocityContextUtils;
import com.leoman.velocity.util.VelocityUtil;

import java.io.IOException;
import java.text.MessageFormat;

public class ControllerNewFile extends NewFile {

	private static final String TEMPLATE_PATH = "template/controllerTemplate.vm";

	private EntityModel entityModel;

	public ControllerNewFile(String name, String projectPath, String packageName, EntityViewUI entityViewUI) {
		super(name, projectPath, packageName);
		entityModel = entityViewUI.getEntityModel();
	}

	public String getPath() {
		String packageName = getPackageName().replaceAll("\\.", "/");
		packageName = packageName.substring(0, packageName.indexOf("/entity"));
		String targetPath = MessageFormat.format("{0}/src/main/java/{1}/{2}/{3}.java",
				new Object[] { this.projectPath, packageName,"controller", getName()});
		return targetPath;
	}

	@Override
	public void process() {
		
		VelocityContext context = VelocityContextUtils.getVelocityContext();
		context.put("controllerClass", this);
		try {
			VelocityUtil.vmToFile(context, TEMPLATE_PATH, getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
