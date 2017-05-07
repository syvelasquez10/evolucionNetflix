// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

import org.json.JSONArray;
import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.Bridge;
import com.netflix.mediaclient.javabridge.NrdpObject;

public abstract class BaseNrdObject implements NrdpObject
{
    protected static final String JSON_FIELD_DATA = "data";
    protected static final String JSON_FIELD_NAME = "name";
    protected static final String JSON_FIELD_OBJECT = "object";
    protected static final String JSON_FIELD_PROPERTIES = "properties";
    protected static final String JSON_FIELD_TYPE = "type";
    protected static final String NAME_IMC = "IMediaControl";
    protected static final String TYPE_EVENT = "Event";
    protected static final String TYPE_PROP_UPDATE = "PropertyUpdate";
    protected Bridge bridge;
    
    public BaseNrdObject(final Bridge bridge) {
        if (bridge == null) {
            throw new IllegalArgumentException("bridge is null!");
        }
        this.bridge = bridge;
    }
    
    protected boolean getBoolean(final JSONObject jsonObject, final String s, final boolean b) throws JSONException {
        return JsonUtils.getBoolean(jsonObject, s, b);
    }
    
    protected int getInt(final JSONObject jsonObject, final String s, final int n) throws JSONException {
        return JsonUtils.getInt(jsonObject, s, n);
    }
    
    protected JSONArray getJSONArray(final JSONObject jsonObject, final String s) throws JSONException {
        return JsonUtils.getJSONArray(jsonObject, s);
    }
    
    protected JSONObject getJSONObject(final JSONObject jsonObject, final String s, final JSONObject jsonObject2) throws JSONException {
        return JsonUtils.getJSONObject(jsonObject, s, jsonObject2);
    }
    
    protected long getLong(final JSONObject jsonObject, final String s, final long n) throws JSONException {
        return JsonUtils.getLong(jsonObject, s, n);
    }
    
    protected String getString(final JSONObject jsonObject, final String s, final String s2) throws JSONException {
        return JsonUtils.getString(jsonObject, s, s2);
    }
}
