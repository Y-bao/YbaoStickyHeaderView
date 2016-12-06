package com.ybao.demo.adapter.tree;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ybao.demo.R;
import com.ybao.sticky.StickyHeaderView;
import com.ybao.sticky.helper.StickyHeaderHelper;
import com.ybao.treehelper.adapter.TreeRecyclerViewAdapter;
import com.ybao.treehelper.model.Node;

import java.util.List;

/**
 * Copyright 2015 Pengyuan-Jiang
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 * Author：Ybao on 2015/11/15 0015 17:20
 * <p/>
 * QQ: 392579823
 * <p/>
 * Email：32579823@qq.com
 */
public class StickyHeaderAdapter extends TreeRecyclerViewAdapter<SimpleItemHolder, SimpleNode> implements StickyHeaderHelper.HeaderImpt {
    StickyHeaderHelper stickyHeaderHelper;

    public StickyHeaderAdapter() {
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


    public void setData(List<SimpleNode> datas) {
        super.setData(datas);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(Node node, SimpleItemHolder holder, int position) {
        holder.textView.getBackground().setLevel(node.getLevel() == 0 ? 0 : 1);
        if (node.isLeaf()) {
            holder.textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        } else if (node.isExpand()) {
            holder.textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.indicator_tree_item_open, 0);
        } else {
            holder.textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.indicator_tree_item_close, 0);
        }
        holder.textView.setPadding(30 + node.getLevel() * 40, 30, 30, 30);
        holder.textView.setText(node.getName());
    }

    @Override
    public SimpleItemHolder onCreateViewHolder(ViewGroup parent, int arg1) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new SimpleItemHolder(textView);
    }

    public StickyHeaderHelper getStickyHeaderHelper() {
        return stickyHeaderHelper;
    }

    @Override
    public int getDataCount() {
        return getItemCount();
    }

    @Override
    public boolean isHeader(int position) {
        Node node = getItemNode(position);
        return node.getLevel() == 0 && !node.isLeaf();
    }

    @Override
    public void onStickyHeader(final int headerPosition, StickyHeaderView stickyHeaderView, StickyHeaderHelper stickyHeaderHelper) {
        View view = stickyHeaderView.getChildAt(0);
        TextView textView;
        if (view == null || !(view instanceof TextView)) {
            textView = (TextView) LayoutInflater.from(stickyHeaderView.getContext()).inflate(R.layout.item, stickyHeaderView, false);
            stickyHeaderView.setCevView(textView);
        } else {
            textView = (TextView) view;
        }
        Node node = getItemNode(headerPosition);
        textView.getBackground().setLevel(node.getLevel() == 0 ? 0 : 1);
        if (node.isLeaf()) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        } else if (node.isExpand()) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.indicator_tree_item_open, 0);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.indicator_tree_item_close, 0);
        }
        textView.setPadding(30 + node.getLevel() * 40, 30, 30, 30);
        textView.setText(node.getName());
        stickyHeaderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v, headerPosition);
            }
        });
    }
}

