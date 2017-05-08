// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.util;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import com.netflix.android.org.json.JSONArray;
import com.netflix.android.org.json.JSONObject;

public class JsonUtil
{
    private static Object fromJson(final Object o) {
        Object o2;
        if (o == JSONObject.NULL) {
            o2 = null;
        }
        else {
            if (o instanceof JSONObject) {
                return toMap((JSONObject)o);
            }
            o2 = o;
            if (o instanceof JSONArray) {
                return toList((JSONArray)o);
            }
        }
        return o2;
    }
    
    public static Map<String, Object> getMap(final JSONObject jsonObject, final String s) {
        return toMap(jsonObject.getJSONObject(s));
    }
    
    public static boolean isEmptyObject(final JSONObject jsonObject) {
        return jsonObject.names() == null;
    }
    
    public static Object toJSON(final Object o) {
        Object o2;
        if (o instanceof Map) {
            o2 = new JSONObject();
            final Map map = (Map)o;
            for (final Object next : map.keySet()) {
                ((JSONObject)o2).put(next.toString(), toJSON(map.get(next)));
            }
        }
        else {
            o2 = o;
            if (o instanceof Iterable) {
                final JSONArray jsonArray = new JSONArray();
                final Iterator<Object> iterator2 = ((Iterable)o).iterator();
                while (iterator2.hasNext()) {
                    jsonArray.put(iterator2.next());
                }
                return jsonArray;
            }
        }
        return o2;
    }
    
    public static List toList(final JSONArray jsonArray) {
        final ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0; i < jsonArray.length(); ++i) {
            list.add(fromJson(jsonArray.get(i)));
        }
        return list;
    }
    
    public static Map<String, Object> toMap(final JSONObject jsonObject) {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            hashMap.put(s, fromJson(jsonObject.get(s)));
        }
        return hashMap;
    }
}
