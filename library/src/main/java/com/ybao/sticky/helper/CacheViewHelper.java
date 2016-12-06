package com.ybao.sticky.helper;

import android.content.Context;
import android.view.View;

import com.ybao.sticky.ItemHeaderView;
import com.ybao.sticky.utils.BiHashMap;

/**
 * Created by Y-bao on 2016/12/2.
 */

public class CacheViewHelper {
    BiHashMap<String, View> map = new BiHashMap<>();
    BiHashMap<String, ItemHeaderView> groupMap = new BiHashMap<>();
    CacheViewImpt cacheViewImpt;

    public CacheViewHelper(CacheViewImpt cacheViewImpt) {
        this.cacheViewImpt = cacheViewImpt;

    }

    public ItemHeaderView onCreateBindItemHeaderView(Context context, View convertView) {
        ItemHeaderView itemHeaderView;
        if (convertView == null || !(convertView instanceof ItemHeaderView)) {
            itemHeaderView = new ItemHeaderView(context);
        } else {
            itemHeaderView = (ItemHeaderView) convertView;
        }
        return itemHeaderView;
    }

    //当BiHashMap是1对1的 如果用普通hashMap 两个不通的key 可以同时关联同一个View, 在控件复用是会出错,BiHashMap会覆盖掉相同的key和相同的View,解决了这个问题
    public void onBindItemHeaderView(ItemHeaderView itemHeaderView, int position) {
        itemHeaderView.setCacheViewHelper(this);
        groupMap.put(cacheViewImpt.getHeaderKey(position), itemHeaderView);
        View view = getHeaderView(itemHeaderView.getContext(), position);
        itemHeaderView.setCevView(view);
    }

    public View getHeaderView(Context context, int position) {
        String key = cacheViewImpt.getHeaderKey(position);
        View view = map.getByKey(key);
        View newView = cacheViewImpt.getHeaderView(context, view, position);
        if (view != newView && newView != null) {
            map.put(key, newView);
        }
        return newView;
    }

    //当FloatClientView onDetachedFromWindow时要移除
    public void removeGroup(ItemHeaderView itemHeaderView) {
        groupMap.removeByValue(itemHeaderView);
    }

    //利用了BiHashMap 反向查找key的功能来 找到对应的FloatClientView(占为控件)
    public ItemHeaderView getGroup(View view) {
        return groupMap.getByKey(map.getByValue(view));
    }

    public interface CacheViewImpt {
        View getHeaderView(Context context, View convertView, int position);

        String getHeaderKey(int position);
    }
}
