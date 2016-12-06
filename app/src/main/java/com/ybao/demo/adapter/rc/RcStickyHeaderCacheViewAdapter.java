package com.ybao.demo.adapter.rc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ybao.sticky.ItemHeaderView;
import com.ybao.sticky.StickyHeaderView;
import com.ybao.sticky.helper.CacheViewHelper;
import com.ybao.sticky.helper.StickyHeaderHelper;

/**
 * Created by Y-bao on 2016/12/2.
 */

public class RcStickyHeaderCacheViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyHeaderHelper.HeaderImpt, CacheViewHelper.CacheViewImpt {
    StickyHeaderHelper stickyHeaderHelper;
    CacheViewHelper cacheViewHelper;

    public RcStickyHeaderCacheViewAdapter() {
        stickyHeaderHelper = new StickyHeaderHelper(this);
        cacheViewHelper = new CacheViewHelper(this);
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
            ItemHeaderView itemHeaderView = cacheViewHelper.onCreateBindItemHeaderView(parent.getContext(), null);
            v = itemHeaderView;
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
        if (holder.itemView instanceof ItemHeaderView) {
            cacheViewHelper.onBindItemHeaderView((ItemHeaderView) holder.itemView, position);
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

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder.itemView instanceof ItemHeaderView) {
            cacheViewHelper.onBindItemHeaderView((ItemHeaderView) holder.itemView, holder.getAdapterPosition());
        }
    }


    @Override
    public int getDataCount() {
        return getItemCount();
    }

    public boolean isHeader(int position) {
        return position % 5 == 0;
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
    public void onStickyHeader(int pos, StickyHeaderView stickyHeaderView, StickyHeaderHelper stickyHeaderHelper) {
        View view = cacheViewHelper.getHeaderView(stickyHeaderView.getContext(), pos);
        View oldView = stickyHeaderView.getChildAt(0);
        if (stickyHeaderView.setCevView(view) && oldView != null) {
            ItemHeaderView itemHeaderView = cacheViewHelper.getGroup(oldView);
            if (itemHeaderView != null) {
                itemHeaderView.setCevView(oldView);
            }
        }
    }

    @Override
    public String getHeaderKey(int position) {
        return position + "";
    }

    public StickyHeaderHelper getStickyHeaderHelper() {
        return stickyHeaderHelper;
    }
}
