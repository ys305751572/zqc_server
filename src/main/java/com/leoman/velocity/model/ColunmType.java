package com.leoman.velocity.model;

/**
 * 字段类型
 * @author yesong
 *
 */
public enum ColunmType {

	defaultText("defaultText",0),    // 默认输入框
	image("image",1),          // 图片
	richText("richText",2),       // 富文本
	select("select",2),         // 下拉框
	radio("radio",2),          // 单选框
	checkbox("checkbox",2),       // 复选框
	date("date",2);            // 日期

	private ColunmType(String name,Integer type) {
		this.name = name;
		this.type = type;
	}

	private String name;
	private Integer type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
