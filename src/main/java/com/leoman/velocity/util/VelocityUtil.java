package com.leoman.velocity.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.*;

public class VelocityUtil {

	private static final String ENCODING = "UTF-8";

	public static void vmToFile(VelocityContext context, String templatePath, String path) throws IOException {
		File parent = new File(path).getParentFile();
		File file = new File(path);
		if (!parent.exists()) {
			parent.mkdirs();
		}
		if (!file.exists()) {
			file.createNewFile();
		}
		VelocityEngine velocity = VelocityHelper.getVelocityEngine();
		Template template = velocity.getTemplate(templatePath, ENCODING);
		FileOutputStream fos = new FileOutputStream(path);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, ENCODING));
		template.merge(context, writer);
		writer.close();
	}
}
