// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils
{
    public static boolean getBoolean(final JSONObject jsonObject, final String s, final boolean b) throws JSONException {
        if (!jsonObject.isNull(s)) {
            return jsonObject.getBoolean(s);
        }
        return b;
    }
    
    public static int getInt(final JSONObject jsonObject, final String s, final int n) throws JSONException {
        if (!jsonObject.isNull(s)) {
            return jsonObject.getInt(s);
        }
        return n;
    }
    
    public static Integer getIntegerObject(final JSONObject jsonObject, final String s, final Integer n) throws JSONException {
        if (!jsonObject.isNull(s)) {
            return jsonObject.getInt(s);
        }
        return n;
    }
    
    public static JSONArray getJSONArray(final JSONObject jsonObject, final String s) throws JSONException {
        JSONArray jsonArray = null;
        if (!jsonObject.isNull(s)) {
            jsonArray = jsonObject.getJSONArray(s);
        }
        return jsonArray;
    }
    
    public static JSONObject getJSONObject(final JSONObject jsonObject, final String s, final JSONObject jsonObject2) throws JSONException {
        if (!jsonObject.isNull(s)) {
            return jsonObject.getJSONObject(s);
        }
        return jsonObject2;
    }
    
    public static long getLong(final JSONObject jsonObject, final String s, final long n) throws JSONException {
        if (!jsonObject.isNull(s)) {
            return jsonObject.getLong(s);
        }
        return n;
    }
    
    public static String getString(final JSONObject jsonObject, final String s, final String s2) throws JSONException {
        if (!jsonObject.isNull(s)) {
            return jsonObject.getString(s);
        }
        return s2;
    }
}
