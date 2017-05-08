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
import com.netflix.model.branches.FalkorObject;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;

public class DiscoverySummary implements JsonPopulator, FalkorObject
{
    private static final String TAG = "DiscoverySummary";
    private int collectionId;
    private String storyArtUrl;
    private String title;
    private int trackId;
    
    public String getPivotBoxartUrl() {
        return this.storyArtUrl;
    }
    
    public long getPivotCollectionId() {
        return this.collectionId;
    }
    
    public String getPivotTitle() {
        return this.title;
    }
    
    public int getTrackId() {
        return this.trackId;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("DiscoverySummary", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0138: {
                switch (s.hashCode()) {
                    case 1636075609: {
                        if (s.equals("collectionId")) {
                            n = 0;
                            break Label_0138;
                        }
                        break;
                    }
                    case 1717725486: {
                        if (s.equals("storyArt")) {
                            n = 1;
                            break Label_0138;
                        }
                        break;
                    }
                    case 110371416: {
                        if (s.equals("title")) {
                            n = 2;
                            break Label_0138;
                        }
                        break;
                    }
                    case -1067396154: {
                        if (s.equals("trackId")) {
                            n = 3;
                            break Label_0138;
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
                    this.collectionId = JsonUtils.getAsIntSafe(jsonElement2);
                    continue;
                }
                case 1: {
                    this.storyArtUrl = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 2: {
                    this.title = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 3: {
                    this.trackId = JsonUtils.getAsIntSafe(jsonElement2);
                    continue;
                }
            }
        }
    }
}
