package com.leoman.velocity.file;

import org.apache.velocity.VelocityContext;
import com.leoman.velocity.model.ListModel;
import com.leoman.velocity.model.QueryModel;
import com.leoman.velocity.model.ui.EntityViewUI;
import com.leoman.velocity.util.VelocityContextUtils;
import com.leoman.velocity.util.VelocityUtil;

import java.io.IOException;
import java.text.MessageFormat;

public class ListPageNewFile extends NewFile {

	private static final String TEMPLATE_PATH = "template/list.jsp.vm";

	private QueryModel queryModel;
	private ListModel listModel;

	public ListPageNewFile(String name, String projectPath, String packageName, EntityViewUI ui) {
		super(name, projectPath, packageName);
		this.queryModel = ui.getQueryModel();
		this.listModel = ui.getListModel();
	}

	@Override
	public void process() {
		VelocityContext context = VelocityContextUtils.getVelocityContext();
		context.put("listClass", this);
		try {
			VelocityUtil.vmToFile(context, TEMPLATE_PATH, getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getPath() {
		String packageName = getPackageName().replaceAll("\\.", "/");
		
		packageName = packageName.substring(0, packageName.indexOf("/entity"));
		String targetPath = MessageFormat.format("{0}/src/main/java/{1}/{2}/{3}.jsp",
				new Object[] { this.projectPath, packageName, this.name, "list" });
		return targetPath;
	}
}
