package com.ybao.demo.adapter.rc;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ybao.sticky.StickyHeaderView;
import com.ybao.sticky.helper.StickyHeaderHelper;

/**
 * Created by Y-bao on 2016/12/2.
 */

public class RcStickyHeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyHeaderHelper.HeaderImpt {
    StickyHeaderHelper stickyHeaderHelper;

    public RcStickyHeaderAdapter() {
        stickyHeaderHelper = new StickyHeaderHelper(this);
        stickyHeaderHelper.onAdapterUpdate();
        registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                stickyHeaderHelper.onAdapterUpdate();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount) {
                super.onItemRangeChanged(positionStart, itemCount);
                stickyHeaderHelper.onAdapterUpdate();
            }

            @Override
            public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
                super.onItemRangeChanged(positionStart, itemCount, payload);
                stickyHeaderHelper.onAdapterUpdate();
            }

            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                stickyHeaderHelper.onAdapterUpdate();
            }

            @Override
            public void onItemRangeRemoved(int positionStart, int itemCount) {
                super.onItemRangeRemoved(positionStart, itemCount);
                stickyHeaderHelper.onAdapterUpdate();
            }

            @Override
            public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                stickyHeaderHelper.onAdapterUpdate();
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        if (viewType == 1) {
            TextView textView = new TextView(parent.getContext());
            textView.setBackgroundColor(0xfff8f8f8);
            textView.setPadding(40, 40, 40, 40);
            v = textView;
        } else {
            TextView textView = new TextView(parent.getContext());
            textView.setBackgroundColor(0xffffffff);
            textView.setPadding(40, 40, 40, 40);
            v = textView;
        }
        v.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new RecyclerView.ViewHolder(v) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 1) {
            int n = position / 5;
            ((TextView) holder.itemView).setText("parent:" + (n + 1));
        } else {
            ((TextView) holder.itemView).setText(position + "");
        }
    }

    @Override
    public int getItemCount() {
        return 300;
    }


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

    /************************************************************************************/

    public StickyHeaderHelper getStickyHeaderHelper() {
        return stickyHeaderHelper;
    }

    @Override
    public int getDataCount() {
        return getItemCount();
    }

    @Override
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
