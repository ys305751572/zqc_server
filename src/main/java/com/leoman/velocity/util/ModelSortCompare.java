package com.leoman.velocity.util;

import com.leoman.velocity.model.ui.UIWidget;

import java.util.Comparator;

/**
 * Created by Administrator on 2016/7/18.
 */
public class ModelSortCompare implements Comparator<UIWidget>{
    @Override
    public int compare(UIWidget o1, UIWidget o2) {
        Integer o1type = o1.getColunmType().getType();
        Integer o2type = o2.getColunmType().getType();

        if(o1type > o2type) {
            return 1;
        }
        else if(o1type < o2type){
            return -1;
        }
        return 0;
    }
}
