// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.event.nrdp;

import java.net.URLDecoder;
import org.json.JSONArray;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.event.UIEvent;

public abstract class BaseNccpEvent implements UIEvent
{
    protected String eventType;
    
    public BaseNccpEvent(final String eventType) {
        if (eventType == null) {
            throw new IllegalArgumentException("Event type can not be null");
        }
        this.eventType = eventType;
    }
    
    protected static boolean getBoolean(final JSONObject jsonObject, final String s, final boolean b) {
        return JsonUtils.getBoolean(jsonObject, s, b);
    }
    
    protected static int getInt(final JSONObject jsonObject, final String s, final int n) {
        return JsonUtils.getInt(jsonObject, s, n);
    }
    
    protected static JSONObject getJSONObject(final JSONObject jsonObject, final String s, final JSONObject jsonObject2) {
        return JsonUtils.getJSONObject(jsonObject, s, jsonObject2);
    }
    
    protected static JSONArray getJsonArray(final JSONObject jsonObject, final String s) {
        return JsonUtils.getJSONArray(jsonObject, s);
    }
    
    protected static String getString(final JSONObject jsonObject, final String s, final String s2) {
        return JsonUtils.getString(jsonObject, s, s2);
    }
    
    protected static String getUrlDecodedString(JSONObject string, String decode, final String s) {
        string = (JSONObject)JsonUtils.getString(string, decode, s);
        if (string == null || "".equals(((String)string).trim())) {
            return (String)string;
        }
        try {
            decode = URLDecoder.decode((String)string, "UTF-8");
            return decode;
        }
        catch (Throwable t) {
            return (String)string;
        }
    }
    
    protected abstract String getJSON();
    
    @Override
    public String getType() {
        return this.eventType;
    }
}
