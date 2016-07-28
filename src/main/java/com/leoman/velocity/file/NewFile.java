package com.leoman.velocity.file;

public abstract class NewFile {

	protected String name;
	protected String projectPath;
	protected String packageName;

	public abstract void process();

	public abstract String getPath();
	
	public NewFile(String name,String projectPath,String packageName) {
		this.name = name;
		this.packageName = packageName;
		this.projectPath = projectPath;
	}
	
	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	
}
