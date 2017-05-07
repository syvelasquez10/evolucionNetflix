// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.user;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.model.JsonPopulator;

public class SocialBadge extends FriendProfile implements JsonPopulator
{
    private static final String TAG = "FriendProfileBadge";
    private String message;
    private String storyId;
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            if (!super.equals(o)) {
                return false;
            }
            final SocialBadge socialBadge = (SocialBadge)o;
            if (this.message == null) {
                if (socialBadge.message != null) {
                    return false;
                }
            }
            else if (!this.message.equals(socialBadge.message)) {
                return false;
            }
            if (this.storyId == null) {
                if (socialBadge.storyId != null) {
                    return false;
                }
            }
            else if (!this.storyId.equals(socialBadge.storyId)) {
                return false;
            }
        }
        return true;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public String getStoryId() {
        return this.storyId;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        super.populate(jsonElement);
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Log.isLoggable("FriendProfileBadge", 2)) {
            Log.v("FriendProfileBadge", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0130: {
                switch (s.hashCode()) {
                    case -1884251920: {
                        if (s.equals("storyId")) {
                            n = 0;
                            break Label_0130;
                        }
                        break;
                    }
                    case 954925063: {
                        if (s.equals("message")) {
                            n = 1;
                            break Label_0130;
                        }
                        break;
                    }
                }
                n = -1;
            }
            switch (n) {
                default: {
                    continue;
                }
                case 0: {
                    this.setStoryId(jsonElement2.getAsString());
                    continue;
                }
                case 1: {
                    this.setMessage(jsonElement2.getAsString());
                    continue;
                }
            }
        }
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public void setStoryId(final String storyId) {
        this.storyId = storyId;
    }
}
