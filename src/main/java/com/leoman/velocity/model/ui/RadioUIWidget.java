package com.leoman.velocity.model.ui;

import com.leoman.velocity.core.ParamOption;
import com.leoman.velocity.entity.DDSub;
import com.leoman.velocity.entity.TableEntity;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/6/22.
 */
public class RadioUIWidget extends UIWidget{

    private List<DDSub> list;

    public List<DDSub> getList() {
        return list;
    }

    public void setList(List<DDSub> list) {
        this.list = list;
    }

    @Override
    public String getWidgetText() {
        return "radio";
    }

    public static RadioUIWidget createRadioUIWidget(TableEntity tableEntity) {
        RadioUIWidget radioUIWidget = new RadioUIWidget();
        radioUIWidget.setId(tableEntity.getC1());
        radioUIWidget.setName(StringUtils.isNotBlank(tableEntity.getC3()) ? tableEntity.getC3() : tableEntity.getC2());
        radioUIWidget.setDefaultValue("");
        radioUIWidget.setList(tableEntity.getList());
        radioUIWidget.setRequired(tableEntity.getC5());
        return radioUIWidget;
    }
}
