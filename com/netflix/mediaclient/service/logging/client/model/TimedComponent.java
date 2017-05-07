// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.SerializedName;

public class TimedComponent
{
    public static final String ELAPSED = "elapsed";
    public static final String NAME = "name";
    @SerializedName("elapsed")
    @Since(1.0)
    private long elapsed;
    @SerializedName("name")
    @Since(1.0)
    private String name;
    
    public TimedComponent() {
    }
    
    public TimedComponent(final String name, final long elapsed) {
        this.name = name;
        this.elapsed = elapsed;
    }
    
    public static TimedComponent createInstance(final JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return null;
        }
        final TimedComponent timedComponent = new TimedComponent();
        timedComponent.name = JsonUtils.getString(jsonObject, "name", null);
        timedComponent.elapsed = JsonUtils.getLong(jsonObject, "elapsed", 0L);
        return timedComponent;
    }
    
    public long getElapsed() {
        return this.elapsed;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setElapsed(final long elapsed) {
        this.elapsed = elapsed;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public JSONObject toJSONObject() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        if (this.name != null) {
            jsonObject.put("name", (Object)this.name);
        }
        jsonObject.put("elapsed", this.elapsed);
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "TimedComponent [name=" + this.name + ", elapsed=" + this.elapsed + "]";
    }
}
