package com.leoman.velocity.model.ui;

import com.leoman.velocity.entity.TableEntity;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Administrator on 2016/6/22.
 */
public class RichUIWidget extends UIWidget{


    @Override
    public String getWidgetText() {
        return "rich";
    }

    public static RichUIWidget createImageUIWidget(TableEntity tableEntity) {
        RichUIWidget richUIWidget = new RichUIWidget();
        richUIWidget.setId(tableEntity.getC1());
        richUIWidget.setName(StringUtils.isNotBlank(tableEntity.getC3()) ? tableEntity.getC3() : tableEntity.getC2());
        richUIWidget.setDefaultValue("");
        richUIWidget.setRequired(tableEntity.getC5());
        return richUIWidget;
    }
}
