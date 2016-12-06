package com.ybao.sticky.helper;

import android.util.SparseArray;

import com.ybao.sticky.StickyHeaderView;

/**
 * Created by Y-bao on 2016/12/2.
 */

public class StickyHeaderHelper {
    SparseArray<Integer> mSparseArray;
    public final static int NON_HEADER = -1;
    HeaderImpt headerImpt;

    public StickyHeaderHelper(HeaderImpt headerImpt) {
        this.headerImpt = headerImpt;
    }

    public void onAdapterUpdate() {
        mSparseArray = new SparseArray<>();
        int n = headerImpt.getDataCount();
        int headerIndex = NON_HEADER;
        for (int i = 0; i < n; i++) {
            if (headerImpt.isHeader(i)) {
                headerIndex = i;
            }
            mSparseArray.append(i, headerIndex);
        }
    }

    public int getHeaderIndex(int position) {
        return mSparseArray.get(position);
    }

    public void onStickyHeader(int pos, StickyHeaderView stickyHeaderView) {
        headerImpt.onStickyHeader(getHeaderIndex(pos), stickyHeaderView, this);
    }

    public interface HeaderImpt {
        int getDataCount();

        boolean isHeader(int position);

        void onStickyHeader(int headerPosition, StickyHeaderView stickyHeaderView, StickyHeaderHelper stickyHeaderHelper);
    }
}
