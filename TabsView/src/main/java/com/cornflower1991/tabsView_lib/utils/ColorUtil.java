package com.cornflower1991.tabsView_lib.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.cornflower1991.tabsView_lib.SkinManager;

import java.util.Random;

/**
 * @author yexiuliang
 * @page
 * @data 2016/11/15.
 */
public class ColorUtil {
    public static final int[] BASIC_RANDOM_COLORS = new int[]{
            Color.parseColor("#007a87"), Color.parseColor("#7b0051"), Color.parseColor("#1b67ac"), Color.parseColor("#638962")};

    public static final int desaturation(int color, float rate) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[1] = hsv[1] * rate;
        return Color.HSVToColor(hsv);
    }

    public static final int desaturation(int color) {
        return desaturation(color, 0.8f);
    }

    public static final int darken(int color, float rate) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] = hsv[2] * rate;
        return Color.HSVToColor(hsv);
    }

    public static final int setColorAlpha(int alpha,int color){
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        return Color.HSVToColor(alpha,hsv);
    }


    //改变图片颜色为当前主题色
    public static Bitmap changeBitmapColor(Context context, Bitmap bitmap){

        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getHeight(),bitmap.getConfig());
        Canvas canvas = new Canvas(newBitmap);
        Paint mPaint = new Paint();
        int skin = SkinManager.getSkin(context).getPrimaryColor();
        float mRed = Color.red(skin);
        float mGreen = Color.green(skin);
        float mBlue = Color.blue(skin);
        float[] src = new float[]{
                0, 0, 0, 0, mRed,
                0, 0, 0, 0, mGreen,
                0, 0, 0, 0, mBlue,
                0, 0, 0, 1, 0};
        ColorMatrix matrix = new ColorMatrix();
        matrix.set(src);
        mPaint.setColorFilter(new ColorMatrixColorFilter(src));
        canvas.drawBitmap(bitmap,new Matrix(),mPaint);
        return newBitmap;
    }

    public static final int darken(int color) {
        return darken(color, 0.8f);
    }

    public static Bitmap updateBitmapColor(Bitmap bitmap, int r, int g, int b) {
        if (bitmap != null) {
            Bitmap tmp = bitmap.copy(Bitmap.Config.ARGB_8888,
                    true);
            Canvas c = new Canvas(tmp);
            Paint mPaint = new Paint();
            mPaint.setAntiAlias(true);
            float[] f = { 0f, 0f, 0f, 0f, r, 0f, 0f, 0f, 0f, g, 0f, 0f, 0f,
                    0f, b, 0f, 0f, 0f, 1f, 0f };
            ColorMatrix cm = new ColorMatrix(f);
            mPaint.setColorFilter(new ColorMatrixColorFilter(cm));
            c.drawBitmap(tmp, 0, 0, mPaint);
            return tmp;
        }
        return null;
    }

    public static int randomBasicColorIndex(int indexBefore){
        int resultIndex;
        Random rand = new Random();
        resultIndex = indexBefore + rand.nextInt(BASIC_RANDOM_COLORS.length - 2) + 1;
        return resultIndex % BASIC_RANDOM_COLORS.length;
    }

    public static boolean isLight(int color){
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        return hsv[1] < 0.1f && hsv[2] > 0.9f;
    }

    private static ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {
        int[] colors = new int[] { pressed, focused, normal, focused, unable, normal };
        int[][] states = new int[6][];
        states[0] = new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled };
        states[1] = new int[] { android.R.attr.state_enabled, android.R.attr.state_focused };
        states[2] = new int[] { android.R.attr.state_enabled };
        states[3] = new int[] { android.R.attr.state_focused };
        states[4] = new int[] { android.R.attr.state_window_focused };
        states[5] = new int[] {};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }

    public static ColorStateList normalSkinColor(Context context,String pressColor){
        int[] colors = new int[] { Color.parseColor(pressColor), SkinManager.getSkin(context).getPrimaryColor() };
        int[][] states = new int[2][];
        states[0] = new int[] { android.R.attr.state_pressed };
        states[1] = new int[] {};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }

    public static ColorStateList checkedSkinColor(Context context,String normalColor){
        int[] colors = new int[] { SkinManager.getSkin(context).getPrimaryColor(),Color.parseColor(normalColor) };
        int[][] states = new int[2][];
        states[0] = new int[] { android.R.attr.state_checked};
        states[1] = new int[] {};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }
}
