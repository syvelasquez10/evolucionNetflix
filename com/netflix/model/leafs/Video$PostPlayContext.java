// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.util.JsonUtils;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayContext;
import com.netflix.mediaclient.servicemgr.model.JsonPopulator;

public class Video$PostPlayContext implements JsonPopulator, PostPlayContext, Trackable
{
    private static final String TAG = "PostPlayContext";
    private String requestId;
    private int trackId;
    
    @Override
    public int getHeroTrackId() {
        throw new UnsupportedOperationException("Should not be needed");
    }
    
    @Override
    public int getListPos() {
        return 0;
    }
    
    @Override
    public String getRequestId() {
        return this.requestId;
    }
    
    @Override
    public int getTrackId() {
        return this.trackId;
    }
    
    @Override
    public boolean isHero() {
        return false;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("PostPlayContext", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final String s = entry.getKey();
            int n = 0;
            Label_0110: {
                switch (s.hashCode()) {
                    case -1067396154: {
                        if (s.equals("trackId")) {
                            n = 0;
                            break Label_0110;
                        }
                        break;
                    }
                    case 693933066: {
                        if (s.equals("requestId")) {
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
                    this.trackId = JsonUtils.getAsIntSafe(entry.getValue());
                    continue;
                }
                case 1: {
                    this.requestId = JsonUtils.getAsStringSafe(entry.getValue());
                    continue;
                }
            }
        }
    }
    
    @Override
    public String toString() {
        return "PostPlayContext [trackId=" + this.trackId + ", requestId=" + this.requestId + "]";
    }
}
