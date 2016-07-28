package com.leoman.velocity.util;

import org.apache.velocity.VelocityContext;

public class VelocityContextUtils {
	
	private static VelocityContext context;

	public static synchronized VelocityContext getVelocityContext() {
		if (context == null) {
			context = new VelocityContext();
		}
		return context;
	}
}
