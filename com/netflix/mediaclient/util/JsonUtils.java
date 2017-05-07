// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import org.json.JSONArray;
import java.util.Iterator;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonArray;
import android.util.Pair;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils
{
    public static JSONObject createJson(final String s, final String s2) {
        if (s == null || s2 == null) {
            throw new IllegalArgumentException("Name and/or value are null");
        }
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(s, (Object)s2);
            return jsonObject;
        }
        catch (JSONException ex) {
            return jsonObject;
        }
    }
    
    public static JSONObject createJson(final Pair<String, String>[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Name and/or value are null");
        }
        final JSONObject jsonObject = new JSONObject();
        try {
            for (int length = array.length, i = 0; i < length; ++i) {
                final Pair<String, String> pair = array[i];
                jsonObject.put((String)pair.first, pair.second);
            }
        }
        catch (JSONException ex) {}
        return jsonObject;
    }
    
    public static List<String> createStringArray(final JsonArray jsonArray) {
        final ArrayList<String> list = new ArrayList<String>(jsonArray.size());
        final Iterator<JsonElement> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next().getAsString());
        }
        return list;
    }
    
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
