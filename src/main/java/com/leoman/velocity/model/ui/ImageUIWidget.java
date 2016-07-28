package com.leoman.velocity.model.ui;

import com.leoman.velocity.entity.TableEntity;
import org.apache.commons.lang.StringUtils;

/**
 * Created by Administrator on 2016/6/22.
 */
public class ImageUIWidget extends UIWidget{

    private boolean isMore; // 是否多选

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    @Override
    public String getWidgetText() {
        return "image";
    }

    public static ImageUIWidget createImageUIWidget(TableEntity tableEntity) {
        ImageUIWidget imageUIWidget = new ImageUIWidget();
        imageUIWidget.setId(tableEntity.getC1());
        imageUIWidget.setName(StringUtils.isNotBlank(tableEntity.getC3()) ? tableEntity.getC3() : tableEntity.getC2());
        imageUIWidget.setDefaultValue("");
        imageUIWidget.setRequired(tableEntity.getC5());
        imageUIWidget.setMore(tableEntity.getC4().equals("singular") ? true : false);
        return imageUIWidget;
    }
}
