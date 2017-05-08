// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;

public class Video$VerticalStoryArt implements JsonPopulator
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
    public String toString() {
        return "VerticalStoryArt [vertStoryArtUrl=" + this.url + "]";
    }
}
