package com.ybao.demo.adapter.lt;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ybao.sticky.StickyHeaderView;
import com.ybao.sticky.helper.StickyHeaderHelper;

/**
 * Created by Y-bao on 2016/12/2.
 */

public class LtStickyHeaderAdapter extends BaseAdapter implements StickyHeaderHelper.HeaderImpt {
    StickyHeaderHelper stickyHeaderHelper;

    public LtStickyHeaderAdapter() {
        stickyHeaderHelper = new StickyHeaderHelper(this);
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
            TextView textView;
            if (convertView == null) {
                textView = new TextView(parent.getContext());
                textView.setBackgroundColor(0xfff8f8f8);
                textView.setPadding(40, 40, 40, 40);
            } else {
                textView = (TextView) convertView;
            }
            int n = position / 5;
            textView.setText("parent:" + (n + 1));
            v = textView;
        } else {
            TextView textView;
            if (convertView == null) {
                textView = new TextView(parent.getContext());
                textView.setBackgroundColor(0xffffffff);
                textView.setPadding(40, 40, 40, 40);
            } else {
                textView = (TextView) convertView;
            }
            textView.setText(i + "");
            v = textView;
        }
        return v;
    }

    /************************************************************************************/

    public StickyHeaderHelper getStickyHeaderHelper() {
        return stickyHeaderHelper;
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
        View view = stickyHeaderView.getChildAt(0);
        TextView textView;
        if (view == null || !(view instanceof TextView)) {
            textView = new TextView(stickyHeaderView.getContext());
            textView.setBackgroundColor(0xfff8f8f8);
            textView.setPadding(40, 40, 40, 40);
            stickyHeaderView.setCevView(textView);
        } else {
            textView = (TextView) view;
        }
        int n = headerPosition / 5;
        textView.setText("parent:" + (n + 1));
    }
}
