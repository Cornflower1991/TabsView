package com.cornflower1991.tabsView_lib;

import android.content.res.ColorStateList;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yexiuliang
 * @page
 * @data 2016/11/15.
 */
public class ColorStateListBuilder {

    private List<StateItem> stateItemList;

    private void checkList(){
        if(stateItemList == null){
            stateItemList = new LinkedList<StateItem>();
        }
    }

    public ColorStateListBuilder addNormalState(int color){
        checkList();
        stateItemList.add(new StateItem(new int[]{}, color));
        return this;
    }

    public ColorStateListBuilder addPressedState(int color){
        checkList();
        stateItemList.add(new StateItem(new int[]{android.R.attr.state_pressed}, color));
        return this;
    }

    public ColorStateListBuilder addCheckedState(int color){
        checkList();
        stateItemList.add(new StateItem(new int[]{android.R.attr.state_checked}, color));
        return this;
    }

    public ColorStateListBuilder addSelectedState(int color){
        checkList();
        stateItemList.add(new StateItem(new int[]{android.R.attr.state_selected}, color));
        return this;
    }

    public ColorStateListBuilder addEnabledState(int color){
        checkList();
        stateItemList.add(new StateItem(new int[]{android.R.attr.state_enabled}, color));
        return this;
    }

    public ColorStateListBuilder addCheckableState(int color){
        checkList();
        stateItemList.add(new StateItem(new int[]{android.R.attr.state_checkable}, color));
        return this;
    }

    public ColorStateList build(){
        if(stateItemList == null || stateItemList.size() == 0){
            return null;
        }

        int[][] states = new int[stateItemList.size()][];
        int[] colors = new int[stateItemList.size()];

        int w = 0;
        for(StateItem stateItem : stateItemList){
            states[w] = stateItem.states;
            colors[w] = stateItem.color;
            w++;
        }

        return new ColorStateList(states, colors);
    }

    private static class StateItem{
        private int[] states;
        private int color;

        public StateItem(int[] states, int color) {
            this.states = states;
            this.color = color;
        }
    }
}
