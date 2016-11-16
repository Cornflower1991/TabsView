package com.cornflower1991.tabsView_lib;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cornflower1991.tabsview.lib.R;

/**
 * @author yexiuliang
 * @page
 * @data 2016/11/15.
 */
public class ImageTabView extends FrameLayout {

    private ImageView iconImageView;
    private TextView nameTextView;
    private RedDotView redDotView;

    private String normalIconUrl;
    private String checkedIconUrl;

    public ImageTabView(Context context) {
        super(context);
        init(context);
    }

    public ImageTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_image_tab, this);

        iconImageView = (ImageView) findViewById(R.id.image_imageTabView_icon);
        nameTextView = (TextView) findViewById(R.id.text_imageTabView_name);
        redDotView = (RedDotView) findViewById(R.id.redDot_imageTabView_number);


    }

    public void setTab(String name, String normalIconUrl, String checkedIconUrl) {
        this.normalIconUrl = normalIconUrl;
        this.checkedIconUrl = checkedIconUrl;
        Glide.with(getContext()).load(isSelected() ? checkedIconUrl : normalIconUrl).into(iconImageView);
        nameTextView.setText(name);
    }

    public void setColor(int normalColor, int checkedColor) {
        ColorStateList colorStateList = new ColorStateListBuilder().addSelectedState(checkedColor).addNormalState(normalColor).build();
        nameTextView.setTextColor(colorStateList);
    }

    public void setChecked(boolean checked) {
        if (iconImageView != null) {
            if (checkedIconUrl != null) {
                Glide.with(getContext()).load(checked ? checkedIconUrl : normalIconUrl);
            }
        }

        if (nameTextView != null) {
            nameTextView.setSelected(checked);
        }
    }


    public RedDotView getRedDotView() {
        return redDotView;
    }

    public boolean isChecked() {
        return nameTextView != null && nameTextView.isSelected();
    }
}

