package com.ybao.sticky;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.ybao.sticky.helper.CacheViewHelper;

/**
 * Created by Y-bao on 2016/12/1.
 */

public class ItemHeaderView extends StickyParentView {
    CacheViewHelper cacheViewHelper;

    public ItemHeaderView(Context context) {
        super(context);
    }

    private ItemHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private ItemHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private ItemHeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    int h = 0;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        if (mode != MeasureSpec.EXACTLY) {
            View view = getChildAt(0);
            if (view != null) {
                measureChild(view, widthMeasureSpec, heightMeasureSpec);
                h = view.getMeasuredHeight();
            }
            setMeasuredDimension(widthMeasureSpec, MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void setCacheViewHelper(CacheViewHelper cacheViewHelper) {
        this.cacheViewHelper = cacheViewHelper;
    }

    @Override
    protected void onDetachedFromWindow() {
        if (cacheViewHelper != null) {
            cacheViewHelper.removeGroup(this);
        }
        super.onDetachedFromWindow();
    }
}
