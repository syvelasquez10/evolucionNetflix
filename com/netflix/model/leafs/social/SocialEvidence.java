// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.social;

import com.google.gson.JsonArray;
import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.service.webclient.model.leafs.FriendProfile;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.FriendProfilesProvider;

public class SocialEvidence implements FriendProfilesProvider, JsonPopulator
{
    private static final String TAG = "SocialEvidence";
    private List<FriendProfile> facebookFriends;
    private boolean isVideoHidden;
    
    @Override
    public List<FriendProfile> getFacebookFriends() {
        return this.facebookFriends;
    }
    
    public boolean isVideoHidden() {
        return this.isVideoHidden;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("SocialEvidence", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final String s = entry.getKey();
            int n = 0;
            Label_0110: {
                switch (s.hashCode()) {
                    case -692604293: {
                        if (s.equals("isVideoHidden")) {
                            n = 0;
                            break Label_0110;
                        }
                        break;
                    }
                    case -2015773713: {
                        if (s.equals("facebookFriends")) {
                            n = 1;
                            break Label_0110;
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
                    this.isVideoHidden = entry.getValue().getAsBoolean();
                    continue;
                }
                case 1: {
                    final JsonArray asJsonArray = entry.getValue().getAsJsonArray();
                    this.facebookFriends = new ArrayList<FriendProfile>(asJsonArray.size());
                    for (final JsonElement jsonElement2 : asJsonArray) {
                        final FriendProfile friendProfile = new FriendProfile();
                        friendProfile.populate(jsonElement2.getAsJsonObject());
                        this.facebookFriends.add(friendProfile);
                    }
                    continue;
                }
            }
        }
    }
    
    public void setVideoHidden(final boolean isVideoHidden) {
        this.isVideoHidden = isVideoHidden;
    }
}
