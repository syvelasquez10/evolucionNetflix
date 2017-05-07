// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.model;

import android.annotation.SuppressLint;
import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import org.json.JSONObject;

class JsonUtil
{
    static void jsonObjectClear(final JSONObject jsonObject) {
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            keys.next();
            keys.remove();
        }
    }
    
    static boolean jsonObjectContainsValue(final JSONObject jsonObject, final Object o) {
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final Object opt = jsonObject.opt((String)keys.next());
            if (opt != null && opt.equals(o)) {
                return true;
            }
        }
        return false;
    }
    
    static Set<Map.Entry<String, Object>> jsonObjectEntrySet(final JSONObject jsonObject) {
        final HashSet<JSONObjectEntry> set = (HashSet<JSONObjectEntry>)new HashSet<Map.Entry<String, Object>>();
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            set.add(new JSONObjectEntry(s, jsonObject.opt(s)));
        }
        return (Set<Map.Entry<String, Object>>)set;
    }
    
    static Set<String> jsonObjectKeySet(final JSONObject jsonObject) {
        final HashSet<Object> set = (HashSet<Object>)new HashSet<String>();
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            set.add(keys.next());
        }
        return (Set<String>)set;
    }
    
    static void jsonObjectPutAll(final JSONObject jsonObject, final Map<String, Object> map) {
        for (final Map.Entry<String, Object> entry : map.entrySet()) {
            try {
                jsonObject.putOpt((String)entry.getKey(), entry.getValue());
                continue;
            }
            catch (JSONException ex) {
                throw new IllegalArgumentException((Throwable)ex);
            }
            break;
        }
    }
    
    static Collection<Object> jsonObjectValues(final JSONObject jsonObject) {
        final ArrayList<Object> list = new ArrayList<Object>();
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            list.add(jsonObject.opt((String)keys.next()));
        }
        return list;
    }
    
    private static final class JSONObjectEntry implements Entry<String, Object>
    {
        private final String key;
        private final Object value;
        
        JSONObjectEntry(final String key, final Object value) {
            this.key = key;
            this.value = value;
        }
        
        @SuppressLint({ "FieldGetter" })
        public String getKey() {
            return this.key;
        }
        
        @Override
        public Object getValue() {
            return this.value;
        }
        
        @Override
        public Object setValue(final Object o) {
            throw new UnsupportedOperationException("JSONObjectEntry is immutable");
        }
    }
}
