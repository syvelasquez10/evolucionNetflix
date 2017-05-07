// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;

public class UserActionLogging$Profile
{
    public static final String PROFILE = "profile";
    public static final String PROFILE_AGE = "age";
    public static final String PROFILE_ID = "profileId";
    public static final String PROFILE_IS_KIDS = "kids";
    public static final String PROFILE_NAME = "name";
    private Integer age;
    private String id;
    private boolean isKids;
    private String name;
    
    public UserActionLogging$Profile(final String id, final String name, final Integer age, final boolean isKids) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isKids = isKids;
    }
    
    public UserActionLogging$Profile(final JSONObject jsonObject) {
        this.id = JsonUtils.getString(jsonObject, "profileId", null);
        this.name = JsonUtils.getString(jsonObject, "name", null);
        this.isKids = JsonUtils.getBoolean(jsonObject, "kids", false);
        final int int1 = JsonUtils.getInt(jsonObject, "age", -1);
        if (int1 < 0) {
            this.age = int1;
        }
    }
    
    public Integer getAge() {
        return this.age;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean isKids() {
        return this.isKids;
    }
    
    public JSONObject toJson() {
        final JSONObject jsonObject = new JSONObject();
        if (this.id != null) {
            jsonObject.put("profileId", (Object)this.id);
        }
        if (this.name != null) {
            jsonObject.put("name", (Object)this.name);
        }
        if (this.age != null) {
            jsonObject.put("age", (int)this.age);
        }
        jsonObject.put("kids", this.isKids);
        return jsonObject;
    }
}
