// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.netflix.mediaclient.ui.iko.wordparty.model.WPInteractiveMomentsModel;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractiveMomentsModel;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.ui.iko.model.InteractiveMomentsModel;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public class InteractivePlaybackMoments implements JsonMerger, JsonPopulator
{
    private static final String TAG = "InteractivePlaybackMoments";
    InteractiveMomentsModel data;
    
    public InteractiveMomentsModel getData() {
        return this.data;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        this.data = FalkorParseUtils.getGson().fromJson(jsonElement, InteractiveMomentsModel.class);
        if (this.data != null && StringUtils.isNotEmpty(this.data.getType())) {
            if ("kong".equalsIgnoreCase(this.data.getType())) {
                this.data = FalkorParseUtils.getGson().fromJson(jsonElement, KongInteractiveMomentsModel.class);
            }
            else if ("wordparty".equalsIgnoreCase(this.data.getType())) {
                this.data = FalkorParseUtils.getGson().fromJson(jsonElement, WPInteractiveMomentsModel.class);
            }
        }
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("InteractivePlaybackMoments", "Populating with: " + asJsonObject);
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        return false;
    }
}
