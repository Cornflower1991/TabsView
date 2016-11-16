package com.cornflower1991.tabsView_lib;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.StringDef;

import com.example.textdrawable.drawable.TextDrawable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yexiuliang
 * @page
 * @data 2016/11/15.
 */
public class FontDrawable extends TextDrawable {


    public static final String TAB_GAME_SELECTED_1007 = "\ue935";
    public static final String TAB_GAME_1006 = "\ue936";
    public static final String TAB_HOME_SELECTED_1005 = "\ue925";
    public static final String TAB_HOME_1004 = "\ue926";
    public static final String TAB_SOFTWARE_SELECTED_1009 = "\ue91b";
    public static final String TAB_SOFTWARE_1008 = "\ue91c";

    public FontDrawable(Context context, @Icon String icon) {
        super(context);
        setTypeface(getIconsTypeface(context));
        setTextColor(SkinManager.getSkin(context).getPrimaryColor());
        setText(icon);
    }

    @Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD})
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAB_SOFTWARE_SELECTED_1009, TAB_SOFTWARE_1008, TAB_HOME_SELECTED_1005,
            TAB_HOME_1004, TAB_GAME_SELECTED_1007, TAB_GAME_1006,})
    public @interface Icon {
    }

    private static Typeface iconsTypeface;

    public static Typeface getIconsTypeface(Context context) {
        if (iconsTypeface == null) {
            synchronized (FontDrawable.class) {
                if (iconsTypeface == null) {
                    iconsTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/icons.ttf");
                }
            }
        }
        return iconsTypeface;
    }

    public static
    @Icon
    String valueOfId(int id) {
        switch (id) {
            case 1004:
                return FontDrawable.TAB_HOME_1004;
            case 1005:
                return FontDrawable.TAB_HOME_SELECTED_1005;
            case 1006:
                return FontDrawable.TAB_GAME_1006;
            case 1007:
                return FontDrawable.TAB_GAME_SELECTED_1007;
            case 1008:
                return FontDrawable.TAB_SOFTWARE_1008;
            case 1009:
                return FontDrawable.TAB_SOFTWARE_SELECTED_1009;
        }
        return null;
    }
}
