package com.ybao.sticky;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.ybao.sticky.helper.StickyHeaderHelper;

/**
 * Created by Y-bao on 2016/12/1.
 */

public class StickyHeaderView extends StickyParentView {

    public StickyHeaderView(Context context) {
        super(context);
    }

    public StickyHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StickyHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StickyHeaderView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void update(View fristView, int pos, StickyHeaderHelper stickyHeaderHelper) {
        if (pos < 0 || stickyHeaderHelper.getHeaderIndex(pos) == StickyHeaderHelper.NON_HEADER) {
            setVisibility(GONE);
        } else {
            setVisibility(VISIBLE);
            boolean isNextGroupHeader = stickyHeaderHelper.getHeaderIndex(pos + 1) == pos + 1;
            if (isNextGroupHeader) {
                int[] vl = new int[2];
                fristView.getLocationOnScreen(vl);
                int vy = vl[1] + fristView.getMeasuredHeight();
                int[] l = new int[2];
                getLocationOnScreen(l);
                int y = l[1] + getMeasuredHeight();
                float translationY = getTranslationY();
                translationY += vy - y;
                if (translationY > 0) {
                    translationY = 0;
                }
                setTranslationY(translationY);
            } else {
                setTranslationY(0);
            }
            stickyHeaderHelper.onStickyHeader(pos, this);
        }
    }
}
