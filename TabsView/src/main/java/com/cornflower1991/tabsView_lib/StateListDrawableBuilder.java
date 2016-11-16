package com.cornflower1991.tabsView_lib;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import java.util.LinkedList;
import java.util.List;

/**
 * @author yexiuliang
 * @page
 * @data 2016/11/15.
 */
public class StateListDrawableBuilder {
    private List<StateItem> stateItemList;

    private void checkList(){
        if(stateItemList == null){
            stateItemList = new LinkedList<StateItem>();
        }
    }

    public StateListDrawableBuilder addNormalState(Drawable drawable){
        checkList();
        stateItemList.add(new StateItem(new int[]{}, drawable));
        return this;
    }

    public StateListDrawableBuilder addPressedState(Drawable drawable){
        checkList();
        stateItemList.add(new StateItem(new int[]{android.R.attr.state_pressed}, drawable));
        return this;
    }

    public StateListDrawableBuilder addCheckedState(Drawable drawable){
        checkList();
        stateItemList.add(new StateItem(new int[]{android.R.attr.state_checked}, drawable));
        return this;
    }

    public StateListDrawableBuilder addSelectedState(Drawable drawable){
        checkList();
        stateItemList.add(new StateItem(new int[]{android.R.attr.state_selected}, drawable));
        return this;
    }

    @SuppressWarnings("unused")
    public StateListDrawableBuilder addEnabledState(Drawable drawable){
        checkList();
        stateItemList.add(new StateItem(new int[]{android.R.attr.state_enabled}, drawable));
        return this;
    }

    public StateListDrawableBuilder addDisabledFalseState(Drawable drawable){
        checkList();
        stateItemList.add(new StateItem(new int[]{-android.R.attr.state_enabled}, drawable));
        return this;
    }

    @SuppressWarnings("unused")
    public StateListDrawableBuilder addCheckableState(Drawable drawable){
        checkList();
        stateItemList.add(new StateItem(new int[]{android.R.attr.state_checkable}, drawable));
        return this;
    }

    public StateListDrawable build(){
        if(stateItemList == null || stateItemList.size() == 0){
            return null;
        }

        StateListDrawable stateListDrawable = new StateListDrawable();
        for(StateItem stateItem : stateItemList){
            stateListDrawable.addState(stateItem.state, stateItem.drawable);
        }

        return stateListDrawable;
    }

    private static class StateItem{
        private int[] state;
        private Drawable drawable;

        public StateItem(int[] state, Drawable drawable) {
            this.state = state;
            this.drawable = drawable;
        }
    }
}
