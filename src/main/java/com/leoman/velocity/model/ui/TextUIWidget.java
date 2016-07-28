package com.leoman.velocity.model.ui;

import com.leoman.velocity.entity.TableEntity;
import com.leoman.velocity.model.ColunmType;
import com.leoman.velocity.model.ValidateRule;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Administrator on 2016/6/22.
 */
public class TextUIWidget extends UIWidget{

    protected ValidateRule validateRule;     // 验证表达式
    protected String validateExpress;        // 自定义表达式

    public ValidateRule getValidateRule() {
        return validateRule;
    }

    public void setValidateRule(ValidateRule validateRule) {
        this.validateRule = validateRule;
    }

    public String getValidateExpress() {
        return validateExpress;
    }

    public void setValidateExpress(String validateExpress) {
        this.validateExpress = validateExpress;
    }

    @Override
    public String getWidgetText() {
        return "text";
    }

    public static TextUIWidget createTextUIWidget(TableEntity tableEntity) {
        TextUIWidget textUIWidget = new TextUIWidget();
        textUIWidget.setId(tableEntity.getC1());
        textUIWidget.setName(StringUtils.isNotBlank(tableEntity.getC3())  ? tableEntity.getC3() : tableEntity.getC2());
        textUIWidget.setDefaultValue("");
        textUIWidget.setColunmType(ColunmType.defaultText);
        textUIWidget.setRequired(tableEntity.getC5());

        switch (tableEntity.getC4()) {
            case "email" :
                textUIWidget.setValidateRule(ValidateRule.Email);
                break;
            case "phone" :
                textUIWidget.setValidateRule(ValidateRule.Mobile);
                break;
        }
        return textUIWidget;
    }
}
