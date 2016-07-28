package com.leoman.velocity.model.ui;

import com.leoman.velocity.model.ColunmType;

public abstract class UIWidget {

    protected String id;                     // id
    protected String name;                   // 字段名字
    protected String defaultValue;           // 默认值
    protected ColunmType colunmType;         // 字段类型  defualtText,image,richText
    protected boolean required;              // 是否必填

    public abstract String getWidgetText();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public ColunmType getColunmType() {
        return colunmType;
    }

    public void setColunmType(ColunmType colunmType) {
        this.colunmType = colunmType;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
