package com.ybao.sticky.utils;

import java.util.HashMap;

/**
 * Created by Y-bao on 2016/12/2.
 */

public class BiHashMap<K, V> {
    HashMap<K, V> kvHashMap = new HashMap<>();
    HashMap<V, K> vkHashMap = new HashMap<>();

    public boolean put(K k, V v) {
        if (k == null || v == null) {
            return false;
        }
        V ov = kvHashMap.remove(k);
        if (ov != null) {
            vkHashMap.remove(ov);
        }
        K ok = vkHashMap.remove(v);
        if (ok != null) {
            kvHashMap.remove(ok);
        }
        kvHashMap.put(k, v);
        vkHashMap.put(v, k);
        return true;
    }

    public V getByKey(K k) {
        return kvHashMap.get(k);
    }

    public K getByValue(V v) {
        return vkHashMap.get(v);
    }

    public V removeBykey(K k) {
        V ov = kvHashMap.remove(k);
        if (ov != null) {
            vkHashMap.remove(ov);
        }
        return ov;
    }

    public K removeByValue(V v) {
        K ok = vkHashMap.remove(v);
        if (ok != null) {
            kvHashMap.remove(ok);
        }
        return ok;
    }

    public boolean containsKey(K k) {
        return kvHashMap.containsKey(k);
    }

    public boolean containsValue(V v) {
        return vkHashMap.containsKey(v);
    }

    public void clear() {
        kvHashMap.clear();
        vkHashMap.clear();
    }
}
