// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;

public class Video$SupplementalVideos implements JsonPopulator
{
    private static final String TAG = "SupplementalVideos";
    public String defaultTrailer;
    
    @Override
    public void populate(JsonElement value) {
        final JsonObject asJsonObject = value.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("SupplementalVideos", "Populating with: " + asJsonObject);
        }
        if (asJsonObject != null) {
            value = asJsonObject.get("id");
            if (value != null) {
                this.defaultTrailer = value.getAsString();
            }
        }
    }
    
    @Override
    public String toString() {
        return "SupplementalVideos [defaultTrailer=" + this.defaultTrailer + "]";
    }
}
