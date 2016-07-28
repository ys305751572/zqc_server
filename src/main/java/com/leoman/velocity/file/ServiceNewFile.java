package com.leoman.velocity.file;

import org.apache.velocity.VelocityContext;
import com.leoman.velocity.model.EntityModel;
import com.leoman.velocity.util.VelocityContextUtils;
import com.leoman.velocity.util.VelocityUtil;

import java.io.IOException;
import java.text.MessageFormat;

public class ServiceNewFile extends NewFile {

	private static final String TEMPLATE_PATH = "template/serviceTemplate.vm";

	private EntityModel entityModel;

	public ServiceNewFile(String name, String projectPath, String packageName, EntityModel entityModel) {
		super(name, projectPath, packageName);
		this.entityModel = entityModel;
	}

	public String getPath() {
		String packageName = getPackageName().replaceAll("\\.", "/");
		packageName = packageName.substring(0, packageName.indexOf("/entity"));
		String targetPath = MessageFormat.format("{0}/src/main/java/{1}/{2}/{3}.java", new Object[] { this.projectPath,
				packageName, "service" ,getName() });
		return targetPath;
	}

	@Override
	public void process() {
		VelocityContext context = VelocityContextUtils.getVelocityContext();
		context.put("serviceClass", this);
		try {
			VelocityUtil.vmToFile(context, TEMPLATE_PATH, getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
