// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;
import com.netflix.mediaclient.servicemgr.interface_.CleanBoxart;

public class Video$CwCleanBoxart implements CleanBoxart, JsonMerger, JsonPopulator
{
    private static final String TAG = "CwCleanBoxart";
    public String bannerLessBoxartUrl;
    
    @Override
    public String getCleanBoxshotUrl() {
        return this.bannerLessBoxartUrl;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("CwCleanBoxart", "Populating with: " + asJsonObject);
        }
        this.bannerLessBoxartUrl = asJsonObject.get("cleanBoxartUrl").getAsString();
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("CwCleanBoxart", "Populating with: " + jsonParser);
        }
        this.bannerLessBoxartUrl = jsonParser.getValueAsString();
        return true;
    }
}
