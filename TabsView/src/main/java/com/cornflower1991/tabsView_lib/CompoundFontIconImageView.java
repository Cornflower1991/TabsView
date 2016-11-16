package com.cornflower1991.tabsView_lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageView;

import com.cornflower1991.tabsView_lib.utils.DimenUtils;
import com.cornflower1991.tabsview.lib.R;
import com.example.textdrawable.drawable.TextDrawable;

/**
 * @author yexiuliang
 * @page
 * @data 2016/11/15.
 */
public class CompoundFontIconImageView extends ImageView implements Checkable {
    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };
    private boolean checked;
    private boolean mBroadcasting;
    private OnCheckedChangeListener mOnCheckedChangeListener;

    private int iconColor;
    private int checkedIconColor;
    private int iconSize;

    @FontDrawable.Icon
    private String iconValue;
    @FontDrawable.Icon
    private String checkedIconValue;

    private TextDrawable normalDrawable;
    private TextDrawable checkedDrawable;

    public CompoundFontIconImageView(Context context) {
        super(context);
        init(context, null);
    }

    public CompoundFontIconImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CompoundFontIconImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet set) {
        iconColor = Color.parseColor("#717171");
        checkedIconColor = SkinManager.getSkin(context).getPrimaryColor();
        iconSize = DimenUtils.dp2px(getContext(), 19);

        if (set != null) {
            TypedArray typedArray = context.obtainStyledAttributes(set, R.styleable.CompoundFontIconImageView);

            iconColor = typedArray.getColor(R.styleable.CompoundFontIconImageView_cfiiy_iconColor, iconColor);
            checkedIconColor = typedArray.getColor(R.styleable.CompoundFontIconImageView_cfiiy_checked_iconColor, checkedIconColor);
            iconSize = (int) typedArray.getDimension(R.styleable.CompoundFontIconImageView_cfiiy_iconSize, iconSize);
            int iconId = typedArray.getInt(R.styleable.CompoundFontIconImageView_cfiiv_icon, 0);
            int checkedIconId = typedArray.getInt(R.styleable.CompoundFontIconImageView_cfiiv_checked_icon, 0);
            checked = typedArray.getBoolean(R.styleable.CompoundFontIconImageView_cfiiy_checked, checked);

            iconValue = FontDrawable.valueOfId(iconId);
            checkedIconValue = FontDrawable.valueOfId(checkedIconId);

            typedArray.recycle();
        }

        setImageDrawable(makeFontDrawable());
        setChecked(checked);
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void setChecked(boolean checked) {
        if (this.checked != checked) {
            this.checked = checked;
            refreshDrawableState();

            // Avoid infinite recursions if setChecked() is called from a listener
            if (mBroadcasting) {
                return;
            }

            mBroadcasting = true;
            if (mOnCheckedChangeListener != null) {
                mOnCheckedChangeListener.onCheckedChanged(this, this.checked);
            }

            mBroadcasting = false;
        }
    }

    @Override
    public void toggle() {
        setChecked(!checked);
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        Drawable drawable = getDrawable();
        if (drawable != null) {
            int[] myDrawableState = getDrawableState();

            // Set the state of the Drawable
            drawable.setState(myDrawableState);

            invalidate();
        }
    }

    @SuppressWarnings("unused")
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mOnCheckedChangeListener = listener;
    }

    private Drawable makeFontDrawable() {
        if (TextUtils.isEmpty(iconValue) || TextUtils.isEmpty(checkedIconValue)) {
            return null;
        }

        TextDrawable normalDrawable = new FontDrawable(getContext(), iconValue).setTextColor(iconColor).setTextSizeDp(iconSize);
        TextDrawable checkedDrawable = new FontDrawable(getContext(), checkedIconValue).setTextColor(checkedIconColor).setTextSizeDp(iconSize);

        this.checkedDrawable = checkedDrawable;
        this.normalDrawable = normalDrawable;

        return new StateListDrawableBuilder().addCheckedState(checkedDrawable).addNormalState(normalDrawable).build();
    }

    public void setIconColor(int iconColor, int checkedIconColor) {
        this.iconColor = iconColor;
        this.checkedIconColor = checkedIconColor;

        if (normalDrawable != null) {
            normalDrawable.setTextColor(iconColor);
        }

        if (checkedDrawable != null) {
            checkedDrawable.setTextColor(this.checkedIconColor);
        }
    }

    public void setFontDrawable(@FontDrawable.Icon String iconValue, @FontDrawable.Icon String checkedIconValue){
        this.iconValue = iconValue;
        this.checkedIconValue = checkedIconValue;

        setImageDrawable(makeFontDrawable());
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        SavedState ss = new SavedState(superState);

        ss.checked = isChecked();
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;

        super.onRestoreInstanceState(ss.getSuperState());
        setChecked(ss.checked);
        requestLayout();
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
        boolean checked;

        SavedState(Parcelable superState) {
            super(superState);
        }

        /**
         * Constructor called from {@link #CREATOR}
         */
        private SavedState(Parcel in) {
            super(in);
            checked = (Boolean) in.readValue(null);
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeValue(checked);
        }

        @Override
        public String toString() {
            return "CompoundFontIconImageView.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " checked=" + checked + "}";
        }
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(CompoundFontIconImageView fontIconImageView, boolean isChecked);
    }
}
