package com.cornflower1991.tabsView_lib;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.cornflower1991.tabsView_lib.utils.ColorUtil;
import com.cornflower1991.tabsview.lib.R;

/**
 * @author yexiuliang
 * @page
 * @data 2016/11/15.
 */
public class TabsView extends LinearLayout {

    private int normalColor; //未选中颜色
    private int checkedColor; //选中颜色
    private boolean broadcasting;
    private int checkedPosition = -1; //选中的index


    private View.OnClickListener tempTabClickListener;
    private DoubleClickGestureDetector doubleClickGestureDetector;

    private OnCheckedChangedListener onCheckedChangedListener;
    private OnDoubleClickTabListener onDoubleClickTabListener; //双击监听

    public TabsView(Context context) {
        super(context);
        init(context);
    }

    public TabsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @TargetApi(11)
    public TabsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(21)
    public TabsView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context) {
        setGravity(Gravity.BOTTOM);
        normalColor = Color.parseColor("#717171");
        checkedColor = SkinManager.getSkin(context).getPrimaryColor();

        tempTabClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (Integer) v.getTag(R.id.tag_position);
                Log.e("position", position + "");
                selectedTab(position);

            }
        };

        doubleClickGestureDetector = new DoubleClickGestureDetector(context, new DoubleClickGestureDetector.OnDoubleClickListener() {
            @Override
            public void onDoubleClick(View tab) {
                int position = (Integer) tab.getTag(R.id.tag_position);
                if (onDoubleClickTabListener != null) {
                    onDoubleClickTabListener.onDoubleClickTab(position);
                }
            }
        });

        if (isInEditMode()) {
            addFontTab(getContext().getString(R.string.tab_recommend), FontDrawable.TAB_HOME_1004, FontDrawable.TAB_HOME_SELECTED_1005);
            addFontTab(getContext().getString(R.string.tab_game), FontDrawable.TAB_GAME_1006, FontDrawable.TAB_GAME_SELECTED_1007);
            addFontTab(getContext().getString(R.string.tab_software), FontDrawable.TAB_SOFTWARE_1008, FontDrawable.TAB_SOFTWARE_SELECTED_1009);
        }
    }

    public FontTabView addFontTab(String name, @FontDrawable.Icon String normalIcon, @FontDrawable.Icon String checkedIcon) {
        FontTabView fontTabView = new FontTabView(getContext());
        fontTabView.setTab(name, normalIcon, checkedIcon);
        fontTabView.setColor(normalColor, checkedColor);

        fontTabView.setTag(R.id.tag_position, getChildCount());
        fontTabView.setOnClickListener(tempTabClickListener);
        fontTabView.setOnTouchListener(doubleClickGestureDetector);

        addView(fontTabView, new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));

        return fontTabView;
    }

    public ImageTabView addImageTab(String name, String normalIconUrl, String checkedIconUrl) {
        ImageTabView imageTabView = new ImageTabView(getContext());

        imageTabView.setTab(name, normalIconUrl, checkedIconUrl);
        imageTabView.setColor(normalColor, checkedColor);

        imageTabView.setTag(R.id.tag_position, getChildCount());
        imageTabView.setOnClickListener(tempTabClickListener);
        imageTabView.setOnTouchListener(doubleClickGestureDetector);

        addView(imageTabView, new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));

        return imageTabView;
    }

    public ImageTabPngView addImagePngTab(String name, int normalIconDrawable, int checkedIconDrawable) {
        ImageTabPngView imageTabPngView = new ImageTabPngView(getContext());

        Bitmap checkBitmap = BitmapFactory.decodeResource(getContext().getResources(), checkedIconDrawable);
        BitmapDrawable checkedDrawable = new BitmapDrawable(ColorUtil.changeBitmapColor(getContext(), checkBitmap));
        imageTabPngView.setPrimaryTab(name, normalIconDrawable, checkedDrawable);
        imageTabPngView.setColor(normalColor, checkedColor);

        imageTabPngView.setTag(R.id.tag_position, getChildCount());
        imageTabPngView.setOnClickListener(tempTabClickListener);
        imageTabPngView.setOnTouchListener(doubleClickGestureDetector);

        addView(imageTabPngView, new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1));

        return imageTabPngView;
    }

    public void refreshSkin() {
        checkedColor = SkinManager.getSkin(getContext()).getPrimaryColor();

        for (int w = 0, size = getChildCount(); w < size; w++) {
            View childView = getChildAt(w);
            if (childView instanceof FontTabView) {
                FontTabView tabView = (FontTabView) childView;
                tabView.setColor(normalColor, checkedColor);
            } else if (childView instanceof ImageTabView) {
                ImageTabView tabView = (ImageTabView) childView;
                tabView.setChecked(tabView.isChecked());
                tabView.setColor(normalColor, checkedColor);
            }
        }
    }

    @SuppressWarnings("unused")
    public int getCheckedPosition() {
        return checkedPosition;
    }

    public void selectedTab(int position) {
        if (position == checkedPosition || position >= getChildCount()) {
            return;
        }


        final int lastCheckedPosition = checkedPosition;
        if (lastCheckedPosition >= 0 && lastCheckedPosition < getChildCount()) {
            View lastCheckedTabView = getChildAt(lastCheckedPosition);
            if (lastCheckedTabView instanceof ImageTabPngView) {
//                ((ImageTabPngView) lastCheckedTabView).setRipplePosition(position);
                ((ImageTabPngView) lastCheckedTabView).setChecked(false, position, false);
            } else if (lastCheckedTabView instanceof ImageTabView) {
                ((ImageTabView) lastCheckedTabView).setChecked(false);
            } else if (lastCheckedTabView instanceof FontTabView) {
                ((FontTabView) lastCheckedTabView).setChecked(false);
            }
        }

        View newCheckedTabView = getChildAt(position);
        if (newCheckedTabView instanceof ImageTabPngView) {
//            ((ImageTabPngView) newCheckedTabView).setRipplePosition(position);
            ((ImageTabPngView) newCheckedTabView).setChecked(true, position, false);
        } else if (newCheckedTabView instanceof ImageTabView) {
            ((ImageTabView) newCheckedTabView).setChecked(true);
        } else if (newCheckedTabView instanceof FontTabView) {
            ((FontTabView) newCheckedTabView).setChecked(true);
        }

        checkedPosition = position;


        if (broadcasting) {
            return;
        }
        broadcasting = true;
        if (onCheckedChangedListener != null) {
            onCheckedChangedListener.onChecked(position);
        }
        broadcasting = false;
    }

    @SuppressWarnings("unused")
    public void setOnCheckedChangedListener(OnCheckedChangedListener onCheckedChangedListener) {
        this.onCheckedChangedListener = onCheckedChangedListener;
    }

    public void setOnDoubleClickTabListener(OnDoubleClickTabListener onDoubleClickTabListener) {
        this.onDoubleClickTabListener = onDoubleClickTabListener;
    }

    public void setColor(int normalColor, int checkedColor) {
        this.normalColor = normalColor;
        this.checkedColor = checkedColor;
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        SavedState ss = new SavedState(superState);

        ss.position = getCheckedPosition();
        return ss;
    }


    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;

        super.onRestoreInstanceState(ss.getSuperState());
        selectedTab(ss.position);
        requestLayout();
    }

    public interface OnCheckedChangedListener {
        void onChecked(int position);
    }

    public interface OnDoubleClickTabListener {
        void onDoubleClickTab(int position);
    }

    static class SavedState extends BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        int position;

        SavedState(Parcelable superState) {
            super(superState);
        }

        /**
         * Constructor called from {@link #CREATOR}
         */
        private SavedState(Parcel in) {
            super(in);
            position = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(position);
        }

        @Override
        public String toString() {
            return "MainTabsView.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " position=" + position + "}";
        }
    }


}
