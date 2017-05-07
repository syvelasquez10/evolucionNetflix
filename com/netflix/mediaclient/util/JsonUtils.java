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
    
    public static boolean getAsBoolSafe(final JsonElement jsonElement) {
        return !jsonElement.isJsonNull() && jsonElement.getAsBoolean();
    }
    
    public static float getAsFloatSafe(final JsonElement jsonElement) {
        if (!jsonElement.isJsonNull() && (!jsonElement.isJsonPrimitive() || !"null".equals(jsonElement.getAsString()))) {
            return jsonElement.getAsFloat();
        }
        return 0.0f;
    }
    
    public static int getAsIntSafe(final JsonElement jsonElement) {
        if (!jsonElement.isJsonNull() && (!jsonElement.isJsonPrimitive() || !"null".equals(jsonElement.getAsString()))) {
            return jsonElement.getAsInt();
        }
        return 0;
    }
    
    public static long getAsLongSafe(final JsonElement jsonElement) {
        if (!jsonElement.isJsonNull() && (!jsonElement.isJsonPrimitive() || !"null".equals(jsonElement.getAsString()))) {
            return jsonElement.getAsLong();
        }
        return 0L;
    }
    
    public static String getAsStringSafe(final JsonElement jsonElement) {
        if (jsonElement.isJsonNull()) {
            return null;
        }
        return jsonElement.getAsString();
    }
    
    public static boolean getBoolean(final JSONObject jsonObject, final String s, boolean boolean1) {
        if (!jsonObject.isNull(s)) {
            boolean1 = jsonObject.getBoolean(s);
        }
        return boolean1;
    }
    
    public static int getInt(final JSONObject jsonObject, final String s, int int1) {
        if (!jsonObject.isNull(s)) {
            int1 = jsonObject.getInt(s);
        }
        return int1;
    }
    
    public static Integer getIntegerObject(final JSONObject jsonObject, final String s, Integer value) {
        if (!jsonObject.isNull(s)) {
            value = jsonObject.getInt(s);
        }
        return value;
    }
    
    public static JSONArray getJSONArray(final JSONObject jsonObject, final String s) {
        JSONArray jsonArray = null;
        if (!jsonObject.isNull(s)) {
            jsonArray = jsonObject.getJSONArray(s);
        }
        return jsonArray;
    }
    
    public static JSONObject getJSONObject(final JSONObject jsonObject, final String s, JSONObject jsonObject2) {
        if (!jsonObject.isNull(s)) {
            jsonObject2 = jsonObject.getJSONObject(s);
        }
        return jsonObject2;
    }
    
    public static long getLong(final JSONObject jsonObject, final String s, long long1) {
        if (!jsonObject.isNull(s)) {
            long1 = jsonObject.getLong(s);
        }
        return long1;
    }
    
    public static String getString(final JSONObject jsonObject, final String s, String string) {
        if (!jsonObject.isNull(s)) {
            string = jsonObject.getString(s);
        }
        return string;
    }
    
    public static boolean isNull(final JsonElement jsonElement) {
        return jsonElement == null || jsonElement.isJsonNull();
    }
}
