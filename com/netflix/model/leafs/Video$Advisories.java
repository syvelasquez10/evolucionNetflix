// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import com.netflix.model.leafs.advisory.Advisory;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public final class Video$Advisories implements JsonMerger, JsonPopulator
{
    private static final String TAG = "Advisories";
    private List<Advisory> advisories;
    
    public Video$Advisories() {
        this.advisories = new ArrayList<Advisory>(0);
    }
    
    public List<Advisory> getAdvisoryList() {
        return this.advisories;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Advisories", "Populating with: " + asJsonObject);
        }
        this.advisories = Advisory.asList(asJsonObject.getAsJsonArray("advisory"));
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        return false;
    }
    
    @Override
    public String toString() {
        return "Advisories [advisory=" + this.advisories + "]";
    }
}
