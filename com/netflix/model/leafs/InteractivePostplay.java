// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.ui.iko.model.InteractivePostplayModel;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public class InteractivePostplay implements JsonMerger, JsonPopulator
{
    private static final String TAG = "InteractivePostplay";
    InteractivePostplayModel data;
    
    public InteractivePostplayModel getData() {
        return this.data;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        this.data = FalkorParseUtils.getGson().fromJson(jsonElement, InteractivePostplayModel.class);
        if (this.data != null && StringUtils.isNotEmpty(this.data.getType()) && "KONG_POST_PLAY".equalsIgnoreCase(this.data.getType())) {
            this.data = FalkorParseUtils.getGson().fromJson(jsonElement, KongInteractivePostPlayModel.class);
        }
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("InteractivePostplay", "Populating with: " + asJsonObject);
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        return false;
    }
}
