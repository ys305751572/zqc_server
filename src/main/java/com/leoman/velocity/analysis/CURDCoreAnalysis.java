package com.leoman.velocity.analysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.leoman.velocity.model.EntityModel;
import com.leoman.velocity.model.FieldModel;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CURDCoreAnalysis{

	private static final Logger logger = LoggerFactory.getLogger(CURDCoreAnalysis.class);
	private Map<String, EntityModel> entityMap = new HashMap<String, EntityModel>();

	public static CURDCoreAnalysis getInstance() {
		return new CURDCoreAnalysis();
	}

	public EntityModel analysis(String srcJava) {
//		String srcJava = srcPath.substring(srcPath.indexOf("/src/main/java") + 1 + "/src/main/java".length(),
//				srcPath.lastIndexOf(".java"));
//		srcJava = srcJava.replaceAll("/", ".");

		if (this.entityMap.containsKey(srcJava)) {
			return (EntityModel) this.entityMap.get(srcJava);
		}
		logger.info("==============对" + srcJava + "进行建模工作============");
		EntityModel entity = new EntityModel();
		this.entityMap.put(srcJava, entity);

//		File file = new File(srcPath);
		ClassLoader classLoad = null;
		Class<?> classEntity = null;
		try {
//			URL url = file.toURI().toURL();
//			classLoad = new URLClassLoader(new URL[] { url });
			classLoad = Thread.currentThread().getContextClassLoader();
			classEntity = classLoad.loadClass(srcJava);

			createModel(classEntity,entity);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		entity.setPackpageName(classEntity.getPackage().getName());
		entity.setName(classEntity.getSimpleName());
		entity.setClassName(classEntity.getName());
		logger.info("==============对" + srcJava + "进行建模完成=============");
		return entity;
	}

	private void createModel(Class<?> classEntity, EntityModel entity) {
		List<Class> classList = new ArrayList<Class>();
		classList.add(classEntity);

		Class superClass = classEntity.getSuperclass();
		while (superClass != null) {
			classList.add(superClass);
			superClass = superClass.getSuperclass();
		}
		for (int i = 0; i < classList.size(); i++) {
			Class _class = classList.get(i);
			Field[] fields = _class.getDeclaredFields();

			for (Field field : fields) {
				boolean isStatic = Modifier.isStatic(field.getModifiers());
				boolean isSerial = "serialVersionUID".equals(field.getName());
				boolean isTransient = Modifier.isTransient(field.getModifiers());
				if( !isStatic && !isSerial && !isTransient) {
					FieldModel fieldModel = new FieldModel(field.getName(),field.getType().getName());
					entity.getFields().add(fieldModel);
				}
			}
		}
	}
}
