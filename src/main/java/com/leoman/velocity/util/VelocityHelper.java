package com.leoman.velocity.util;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class VelocityHelper {
	
	private static VelocityEngine engine;

	public static VelocityEngine getVelocityEngine() {
		if (engine == null) {
//			Properties p = new Properties();
//			p.setProperty("file.resource.loader.class",
//					"forkoala.org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//			p.setProperty("file.resource.loader.path", "");
//			p.setProperty("ISO-8859-1", "UTF-8");
//			p.setProperty("input.encoding", "UTF-8");
//			p.setProperty("output.encoding", "UTF-8");
			engine = new VelocityEngine();
			engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			engine.init();
		}
		return engine;
	}
}
