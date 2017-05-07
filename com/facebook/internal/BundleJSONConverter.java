// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.util.List;
import java.util.Iterator;
import android.os.Bundle;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.Map;

public class BundleJSONConverter
{
    private static final Map<Class<?>, BundleJSONConverter$Setter> SETTERS;
    
    static {
        (SETTERS = new HashMap<Class<?>, BundleJSONConverter$Setter>()).put(Boolean.class, new BundleJSONConverter$1());
        BundleJSONConverter.SETTERS.put(Integer.class, new BundleJSONConverter$2());
        BundleJSONConverter.SETTERS.put(Long.class, new BundleJSONConverter$3());
        BundleJSONConverter.SETTERS.put(Double.class, new BundleJSONConverter$4());
        BundleJSONConverter.SETTERS.put(String.class, new BundleJSONConverter$5());
        BundleJSONConverter.SETTERS.put(String[].class, new BundleJSONConverter$6());
        BundleJSONConverter.SETTERS.put(JSONArray.class, new BundleJSONConverter$7());
    }
    
    public static Bundle convertToBundle(final JSONObject jsonObject) {
        final Bundle bundle = new Bundle();
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            final Object value = jsonObject.get(s);
            if (value != null && value != JSONObject.NULL) {
                if (value instanceof JSONObject) {
                    bundle.putBundle(s, convertToBundle((JSONObject)value));
                }
                else {
                    final BundleJSONConverter$Setter bundleJSONConverter$Setter = BundleJSONConverter.SETTERS.get(((JSONObject)value).getClass());
                    if (bundleJSONConverter$Setter == null) {
                        throw new IllegalArgumentException("Unsupported type: " + ((JSONObject)value).getClass());
                    }
                    bundleJSONConverter$Setter.setOnBundle(bundle, s, value);
                }
            }
        }
        return bundle;
    }
    
    public static JSONObject convertToJSON(final Bundle bundle) {
        final JSONObject jsonObject = new JSONObject();
        for (final String s : bundle.keySet()) {
            final Object value = bundle.get(s);
            if (value != null) {
                if (value instanceof List) {
                    final JSONArray jsonArray = new JSONArray();
                    final Iterator<String> iterator2 = ((List<String>)value).iterator();
                    while (iterator2.hasNext()) {
                        jsonArray.put((Object)iterator2.next());
                    }
                    jsonObject.put(s, (Object)jsonArray);
                }
                else if (value instanceof Bundle) {
                    jsonObject.put(s, (Object)convertToJSON((Bundle)value));
                }
                else {
                    final BundleJSONConverter$Setter bundleJSONConverter$Setter = BundleJSONConverter.SETTERS.get(((List<String>)value).getClass());
                    if (bundleJSONConverter$Setter == null) {
                        throw new IllegalArgumentException("Unsupported type: " + ((List<String>)value).getClass());
                    }
                    bundleJSONConverter$Setter.setOnJSON(jsonObject, s, value);
                }
            }
        }
        return jsonObject;
    }
}
