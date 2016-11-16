package com.cornflower1991.tabsView_lib.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * @author yexiuliang
 * @page
 * @data 2016/11/15.
 */
public class DimenUtils {
    public static float px2dp(Context context, int px) {
        return (float) px / context.getResources().getDisplayMetrics().scaledDensity + 0.5f;
    }

    public static float dp2sp(Context context, int dip) {
        return px2dp(context, dp2px(context, dip));
    }

    public static int dp2px(Context context, int dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5);
    }

    public static int dp2px(Context context, float dpValue) {
        return (int) ((dpValue * context.getResources().getDisplayMetrics().density) + 0.5);
    }

    public static float unit2px(Context context, int unit, float value) {
        return TypedValue.applyDimension(unit, value, context.getResources().getDisplayMetrics());
    }
}
