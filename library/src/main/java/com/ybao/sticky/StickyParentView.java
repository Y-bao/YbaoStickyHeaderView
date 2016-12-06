package com.ybao.sticky;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by Y-bao on 2016/12/1.
 */

public class StickyParentView extends FrameLayout {

    public StickyParentView(Context context) {
        super(context);
    }

    public StickyParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StickyParentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StickyParentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public boolean setCevView(View view) {
        if (view == null) {
            return false;
        }
        if (view == getChildAt(0)) {
            return false;
        }
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) {
            params = generateDefaultLayoutParams();
            if (params == null) {
                return false;
            }
        }
        if (view.getParent() != null && view.getParent() instanceof ViewGroup) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        removeAllViews();
        super.addView(view, 0, params);
        return true;
    }

    @Deprecated
    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
    }

    @Deprecated
    @Override
    public void addView(View child, int index) {
    }

    @Deprecated
    @Override
    public void addView(View child, int width, int height) {
    }

    @Deprecated
    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
    }

    @Deprecated
    @Override
    public void addView(View child) {
    }
}
