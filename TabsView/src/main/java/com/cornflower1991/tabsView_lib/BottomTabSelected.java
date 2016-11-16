package com.cornflower1991.tabsView_lib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.cornflower1991.tabsView_lib.utils.ColorUtil;


/**
 * @author yexiuliang
 * @page
 * @data 2016/11/15.
 */
public class BottomTabSelected extends View {

    private Paint paint;
    private int position;
    RectF rectF;
    public BottomTabSelected(Context context) {
        super(context);
        init();
    }

    public BottomTabSelected(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BottomTabSelected(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        if (paint == null){
            paint = new Paint();
            paint.setColor(ColorUtil.setColorAlpha(26, SkinManager.getSkin(getContext()).getPrimaryColor()));
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        }
        if (rectF == null){
            rectF = new RectF();
        }
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (position == 0 ){
            rectF.top = 0;
            rectF.left = getMeasuredWidth() - getMeasuredWidth() / 2;
            rectF.bottom = getMeasuredHeight();
            rectF.right = getMeasuredWidth();
            canvas.drawArc(rectF,0,90,true,paint);
            canvas.drawArc(rectF,0,-90,true,paint);
            rectF.set(0,0,getMeasuredWidth() - getMeasuredWidth() / 4,getMeasuredHeight());
            canvas.drawRect(rectF,paint);
            return;
        }
        if (position == 4){
            rectF.top = 0;
            rectF.left = 0;
            rectF.bottom = getMeasuredHeight();
            rectF.right = getMeasuredWidth() / 2;
            canvas.drawArc(rectF,90,180,true,paint);
            rectF.set(getMeasuredWidth() / 4,0,getMeasuredWidth(),getMeasuredHeight());
            canvas.drawRect(rectF,paint);
            return;
        }
        rectF.set(0,0,getMeasuredWidth() / 2,getMeasuredHeight());
        canvas.drawArc(rectF,90,180,true,paint);
        rectF.set(getMeasuredWidth() - getMeasuredWidth() / 2,0,getMeasuredWidth(),getMeasuredHeight());
        canvas.drawArc(rectF,0,90,true,paint);
        canvas.drawArc(rectF,0,-90,true,paint);
        rectF.set(getMeasuredWidth() / 4,0,getMeasuredWidth() - getMeasuredWidth() / 4,getMeasuredHeight());
        canvas.drawRect(rectF,paint);

    }
}
