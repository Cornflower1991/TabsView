package com.cornflower1991.tabsView_lib;

import android.content.Context;

/**
 * @author yexiuliang
 * @page
 * @data 2016/11/15.
 */
public class SkinManager {


    private static SkinManager currentSkin;
    private int mPrimaryColor = 0xFF009bf5;


    private SkinManager() {

    }

    private static void initSkin(Context context) {
        if (currentSkin == null) {
            currentSkin = new SkinManager();
        }
    }

    public static SkinManager getSkin(Context context) {
        initSkin(context);
        return currentSkin;
    }


    public int getPrimaryColor() {
        return mPrimaryColor;
    }

    public void setPrimaryColor(int primaryColor) {
        mPrimaryColor = primaryColor;
    }
}
