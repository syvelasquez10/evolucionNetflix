// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import org.json.JSONArray;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.SerializedName;

public class SessionKey
{
    public static final String SESSION_CATEGORY = "category";
    public static final String SESSION_ID = "sessionId";
    public static final String SESSION_NAME = "sessionName";
    @SerializedName("sessionCategory")
    @Since(1.0)
    private String category;
    @SerializedName("sessionId")
    @Since(1.0)
    private DeviceUniqueId id;
    @SerializedName("sessionName")
    @Since(1.0)
    private String name;
    
    public SessionKey() {
    }
    
    public SessionKey(final DeviceUniqueId id, final String category, final String name) {
        this.id = id;
        this.category = category;
        this.name = name;
    }
    
    public static SessionKey createInstance(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        final SessionKey sessionKey = new SessionKey();
        final long long1 = JsonUtils.getLong(jsonObject, "sessionId", -1L);
        if (long1 > 0L) {
            sessionKey.id = new DeviceUniqueId(long1);
        }
        sessionKey.category = JsonUtils.getString(jsonObject, "category", (String)null);
        sessionKey.name = JsonUtils.getString(jsonObject, "sessionName", (String)null);
        return sessionKey;
    }
    
    public String getCategory() {
        return this.category;
    }
    
    public DeviceUniqueId getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public JSONArray toJSONArray() {
        final JSONArray jsonArray = new JSONArray();
        if (this.id != null) {
            jsonArray.put((Object)("" + this.id.getValue()));
        }
        if (this.category != null) {
            jsonArray.put((Object)this.category);
        }
        if (this.name != null) {
            jsonArray.put((Object)this.name);
        }
        return jsonArray;
    }
    
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = new JSONObject();
        if (this.id != null) {
            jsonObject.put("sessionId", (Object)("" + this.id.getValue()));
        }
        if (this.category != null) {
            jsonObject.put("category", (Object)this.category);
        }
        if (this.name != null) {
            jsonObject.put("sessionName", (Object)this.name);
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "SessionKey [id=" + this.id + ", category=" + this.category + ", name=" + this.name + "]";
    }
}
