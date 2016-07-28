package com.leoman.velocity.model.ui;

import com.leoman.velocity.core.ParamOption;
import com.leoman.velocity.entity.DDSub;
import com.leoman.velocity.entity.TableEntity;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/6/22.
 */
public class SelectUIWidget extends UIWidget{

    private List<DDSub> list;

    public List<DDSub> getList() {
        return list;
    }

    public void setList(List<DDSub> list) {
        this.list = list;
    }

    @Override
    public String getWidgetText() {
        return "select";
    }

    public static SelectUIWidget createSelectUIWidget(TableEntity tableEntity) {

        SelectUIWidget selectUIWidget = new SelectUIWidget();
        selectUIWidget.setId(tableEntity.getC1());
        selectUIWidget.setName(StringUtils.isNotBlank(tableEntity.getC3()) ? tableEntity.getC3() : tableEntity.getC2());
        selectUIWidget.setDefaultValue("");
        selectUIWidget.setRequired(tableEntity.getC5());
        selectUIWidget.setList(tableEntity.getList());
        return selectUIWidget;
    }
}
