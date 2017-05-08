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

public class Video$VerticalStoryArt implements JsonMerger, JsonPopulator
{
    private static final String TAG = "VerticalStoryArt";
    public String url;
    
    @Override
    public void populate(JsonElement value) {
        final JsonObject asJsonObject = value.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("VerticalStoryArt", "Populating with: " + asJsonObject);
        }
        if (asJsonObject != null) {
            value = asJsonObject.get("vertStoryArt");
            if (value != null) {
                this.url = value.getAsString();
            }
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("VerticalStoryArt", "Populating with: " + jsonParser);
        }
        if (jsonParser != null && "vertStoryArt".equalsIgnoreCase(s)) {
            this.url = jsonParser.getValueAsString();
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "VerticalStoryArt [vertStoryArtUrl=" + this.url + "]";
    }
}
