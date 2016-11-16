package com.cornflower1991.tabsView_lib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.NinePatchDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.cornflower1991.tabsView_lib.utils.DimenUtils;
import com.cornflower1991.tabsview.lib.R;

/**
 * @author yexiuliang
 * @page
 * @data 2016/11/15.
 */
public class RedDotView extends View {

    private NinePatchDrawable redDot;
    private TextPaint numberPaint;
    private String number;
    private int fontSpacing;
    private int fontHeight;
    private int lengthen;
    private boolean showRedDot;
    private Paint redDotPaint;

    public RedDotView(Context context) {
        this(context, null);
    }

    public RedDotView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RedDotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        redDot = ((NinePatchDrawable) context.getResources().getDrawable(R.drawable.update_number_big_bk));
        numberPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        numberPaint.setTextSize(DimenUtils.dp2px(context, 10));
        numberPaint.setTextAlign(Paint.Align.CENTER);
        numberPaint.setColor(Color.WHITE);

        if (isInEditMode()) {
            showRedDot = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!TextUtils.isEmpty(number)) {
            int redDotWidth = redDot.getIntrinsicWidth();
            int redDotHeight = redDot.getIntrinsicHeight();

            int left = (getWidth() - redDotWidth) / 2;
            int top = (getHeight() - redDotHeight) / 2;

            //设置红点的偏移量
            left += DimenUtils.dp2px(getContext(), 10);
            top -= DimenUtils.dp2px(getContext(), 12);

            redDot.setBounds(left, top, left + redDotWidth + lengthen, top + redDotHeight);
            redDot.draw(canvas);

            canvas.drawText(number, left + (lengthen + redDotWidth) / 2, top + redDotHeight / 2 - fontHeight / 2, numberPaint);
        } else if (showRedDot) {
            int redDotWidth = redDot.getIntrinsicWidth();
            int redDotHeight = redDot.getIntrinsicHeight();

            int left = (getWidth() - redDotWidth) / 2;
            int top = (getHeight() - redDotHeight) / 2;

            if (redDotPaint == null) {
                redDotPaint = new Paint();
                redDotPaint.setColor(Color.parseColor("#F43530"));
                redDotPaint.setStrokeCap(Paint.Cap.ROUND);
                redDotPaint.setAntiAlias(true);
            }
            canvas.drawCircle(left + redDotWidth, top - 5
                    , DimenUtils.dp2px(getContext(), 5f), redDotPaint);
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
        if (number.length() > 0) {
            fontSpacing = (int) (numberPaint.measureText(number) / number.length());
            fontHeight = (int) (numberPaint.descent() + numberPaint.ascent());
            lengthen = (number.length() - 1) * fontSpacing;
        }
        invalidate();
    }

    public void setShowRedDot(boolean showRedDot) {
        this.showRedDot = showRedDot;
        postInvalidate();
    }
}

