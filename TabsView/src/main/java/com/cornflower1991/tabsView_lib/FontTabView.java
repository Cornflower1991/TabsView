package com.cornflower1991.tabsView_lib;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cornflower1991.tabsview.lib.R;

/**
 * @author yexiuliang
 * @page
 * @data 2016/11/15.
 */
public class FontTabView extends FrameLayout {
    private CompoundFontIconImageView iconImageView;
    private TextView nameTextView;
    private RedDotView redDotView;

    public FontTabView(Context context) {
        super(context);
        init(context);
    }

    public FontTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_font_tab, this);

        iconImageView = (CompoundFontIconImageView) findViewById(R.id.fontIcon_fontTabView_icon);
        nameTextView = (TextView) findViewById(R.id.text_fontTabView_name);
        redDotView = (RedDotView) findViewById(R.id.redDot_fontTabView_number);
    }

    public void setTab(String name, @FontDrawable.Icon String normalIcon, @FontDrawable.Icon String checkedIcon) {
        iconImageView.setFontDrawable(normalIcon, checkedIcon);
        nameTextView.setText(name);
    }

    public void setColor(int normalColor, int checkedColor) {
        iconImageView.setIconColor(normalColor, checkedColor);
        ColorStateList colorStateList = new ColorStateListBuilder().addSelectedState(checkedColor).addNormalState(normalColor).build();
        nameTextView.setTextColor(colorStateList);
    }

    public void setChecked(boolean checked) {
        if (iconImageView != null) {
            iconImageView.setChecked(checked);
        }

        if (nameTextView != null) {
            nameTextView.setSelected(checked);
        }
    }

    public RedDotView getRedDotView() {
        return redDotView;
    }

    public boolean isChecked() {
        return iconImageView != null && iconImageView.isChecked();
    }
}

