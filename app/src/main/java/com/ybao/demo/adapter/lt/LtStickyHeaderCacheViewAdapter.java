package com.ybao.demo.adapter.lt;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ybao.sticky.ItemHeaderView;
import com.ybao.sticky.StickyHeaderView;
import com.ybao.sticky.helper.CacheViewHelper;
import com.ybao.sticky.helper.StickyHeaderHelper;

/**
 * Created by Y-bao on 2016/12/2.
 */

public class LtStickyHeaderCacheViewAdapter extends BaseAdapter implements StickyHeaderHelper.HeaderImpt, CacheViewHelper.CacheViewImpt {
    StickyHeaderHelper stickyHeaderHelper;
    CacheViewHelper cacheViewHelper;

    public LtStickyHeaderCacheViewAdapter() {
        stickyHeaderHelper = new StickyHeaderHelper(this);
        cacheViewHelper = new CacheViewHelper(this);
        stickyHeaderHelper.onAdapterUpdate();
        registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                stickyHeaderHelper.onAdapterUpdate();
            }

            @Override
            public void onInvalidated() {
                super.onInvalidated();
                stickyHeaderHelper.onAdapterUpdate();
            }
        });
    }

    @Override
    public int getCount() {
        return 300;
    }

    @Override
    public Object getItem(int position) {
        return "" + (position / 5 + 1);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 5 == 0 ? 1 : 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int i = position % 5;
        View v = null;
        if (isHeader(position)) {
            ItemHeaderView itemHeaderView = cacheViewHelper.onCreateBindItemHeaderView(parent.getContext(), convertView);
            cacheViewHelper.onBindItemHeaderView(itemHeaderView, position);
            v = itemHeaderView;
        } else {
            TextView textView;
            if (convertView == null) {
                textView = new TextView(parent.getContext());
            } else {
                textView = (TextView) convertView;
            }
            textView.setText(i + "");
            textView.setBackgroundColor(0xffffffff);
            textView.setPadding(40, 40, 40, 40);
            v = textView;
        }
        return v;
    }

    /************************************************************************************/

    public StickyHeaderHelper getStickyHeaderHelper() {
        return stickyHeaderHelper;
    }

    @Override
    public String getHeaderKey(int position) {
        return position + "";
    }

    @Override
    public View getHeaderView(Context context, View convertView, int position) {
        TextView textView;
        if (convertView == null) {
            textView = new TextView(context);
            textView.setBackgroundColor(0xfff8f8f8);
            textView.setPadding(40, 40, 40, 40);
            int n = position / 5;
            textView.setText("parent:" + (n + 1));
            return textView;
        } else {
            return convertView;
        }
    }

    @Override
    public int getDataCount() {
        return getCount();
    }

    public boolean isHeader(int position) {
        return position % 5 == 0;
    }

    @Override
    public void onStickyHeader(int headerPosition, StickyHeaderView stickyHeaderView, StickyHeaderHelper stickyHeaderHelper) {
        View view = cacheViewHelper.getHeaderView(stickyHeaderView.getContext(), headerPosition);
        View oldView = stickyHeaderView.getChildAt(0);
        if (stickyHeaderView.setCevView(view) && oldView != null) {
            ItemHeaderView itemHeaderView = cacheViewHelper.getGroup(oldView);
            if (itemHeaderView != null) {
                itemHeaderView.setCevView(oldView);
            }
        }
    }
}
