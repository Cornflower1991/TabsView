package com.cornflower1991.tabsView_lib;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cornflower1991.tabsview.lib.R;

/**
 * @author yexiuliang
 * @page
 * @data 2016/11/15.
 */
public class ImageTabPngView extends FrameLayout implements RippleLayout.OnRippleCompleteListener {

    private ImageView iconImageView;
    private TextView nameTextView;
    private RedDotView redDotView;
    private LinearLayout layoutBackground;
    private BottomTabSelected bottomTabSelected;
    private RippleLayout rippleLayout;

    private int normalIconDrawable;
    private BitmapDrawable checkedIconDrawable;
    private RectF rect;
    private Paint mPaint;
    private int position;

    public ImageTabPngView(Context context) {
        super(context);
        init(context);
    }

    public ImageTabPngView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImageTabPngView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_png_tab, this);

        iconImageView = (ImageView) findViewById(R.id.image_imageTabView_icon);
        nameTextView = (TextView) findViewById(R.id.text_imageTabView_name);
        redDotView = (RedDotView) findViewById(R.id.redDot_imageTabView_number);
        layoutBackground = (LinearLayout) findViewById(R.id.layout_imageTabView_background);
        rippleLayout = (RippleLayout) findViewById(R.id.layout_imageTabPngView_ripple);
        rippleLayout.setOnRippleCompleteListener(this);
        bottomTabSelected = (BottomTabSelected) findViewById(R.id.bottom_imageTabView_background);
        bottomTabSelected.setVisibility(GONE);


        rect = new RectF();
        rect.top = layoutBackground.getWidth() + 20;
        rect.left = layoutBackground.getWidth() + 20;
        rect.bottom = 2 * layoutBackground.getWidth() - 20;
        rect.right = 2 * layoutBackground.getWidth() - 20;
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(Color.BLACK);
        }
    }

    public void setPrimaryTab(String name, int normalIconDrawable, BitmapDrawable checkedIconDrawable) {
        this.normalIconDrawable = normalIconDrawable;
        this.checkedIconDrawable = checkedIconDrawable;

        if (isSelected()) {
            iconImageView.setBackground(checkedIconDrawable);
        } else {
            iconImageView.setBackgroundResource(normalIconDrawable);
        }
        nameTextView.setText(name);
    }

    public void setColor(int normalColor, int checkedColor) {
        ColorStateList colorStateList = new ColorStateListBuilder().addSelectedState(checkedColor).addNormalState(normalColor).build();
        nameTextView.setTextColor(colorStateList);
    }

    public void setRipplePosition(int position){
        Log.d("--ImageTabPngView---","--4--position = " + position);
        rippleLayout.setPosition(position);
    }

    public void setChecked(boolean checked, final int position, boolean isShowRipple) {
        if (iconImageView != null) {
            if (checked) {
                iconImageView.setBackground(checkedIconDrawable);
                if (isShowRipple || position == 0) {
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bottomTabSelected.setVisibility(VISIBLE);
                            bottomTabSelected.setPosition(position);
                        }
                    }, 0);
                }
            } else {
                iconImageView.setBackgroundResource(normalIconDrawable);
                bottomTabSelected.setVisibility(GONE);
            }
        }

        if (nameTextView != null) {
            nameTextView.setSelected(checked);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public RedDotView getRedDotView() {
        return redDotView;
    }

    public boolean isChecked() {
        return nameTextView != null && nameTextView.isSelected();
    }

    @Override
    public void onComplete(RippleLayout rippleView, int position) {
        Log.d("---ImageTabPngView---", "--3--position = " + position);
        setChecked(true,position,true);
    }
}

