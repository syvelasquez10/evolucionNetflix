// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import org.json.JSONArray;
import org.json.JSONObject;

public class SocialLogging$FriendPosition
{
    private String id;
    private int position;
    private boolean searched;
    
    public SocialLogging$FriendPosition(final String id, final int position, final boolean searched) {
        this.id = id;
        this.position = position;
        this.searched = searched;
    }
    
    public static SocialLogging$FriendPosition fromJson(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        return new SocialLogging$FriendPosition(jsonObject.optString("id"), jsonObject.optInt("position", 0), jsonObject.optBoolean("searched", false));
    }
    
    public static SocialLogging$FriendPosition[] fromJsonArray(final JSONArray jsonArray) {
        int i = 0;
        if (jsonArray == null) {
            return new SocialLogging$FriendPosition[0];
        }
        SocialLogging$FriendPosition[] array;
        for (array = new SocialLogging$FriendPosition[jsonArray.length()]; i < array.length; ++i) {
            array[i] = fromJson(jsonArray.getJSONObject(i));
        }
        return array;
    }
    
    public JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        if (this.id != null) {
            jsonObject.put("id", (Object)this.id);
        }
        jsonObject.put("searched", this.searched);
        jsonObject.put("position", this.position);
        return jsonObject;
    }
}
