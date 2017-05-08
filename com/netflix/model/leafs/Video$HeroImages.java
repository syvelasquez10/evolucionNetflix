// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Collection;
import com.netflix.falkor.BranchNodeUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public class Video$HeroImages implements JsonMerger, JsonPopulator
{
    private static final String TAG = "HeroImages";
    public List<String> heroImgs;
    
    public Video$HeroImages() {
        this.heroImgs = new ArrayList<String>(3);
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Log.isLoggable()) {
            Log.v("HeroImages", "Populating with: " + asJsonObject);
        }
        final JsonArray asJsonArray = asJsonObject.getAsJsonArray("heroImgs");
        this.heroImgs.clear();
        for (int i = 0; i < asJsonArray.size(); ++i) {
            this.heroImgs.add(asJsonArray.get(i).getAsString());
        }
        if (Log.isLoggable()) {
            Log.v("HeroImages", "Parsed hero images as: " + this.heroImgs);
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Log.isLoggable()) {
            Log.v("HeroImages", "Populating with: " + jsonParser);
        }
        if ("heroImgs".equalsIgnoreCase(s)) {
            this.heroImgs.clear();
            this.heroImgs.addAll(BranchNodeUtils.getAsStringArray(jsonParser));
            if (Log.isLoggable()) {
                Log.v("HeroImages", "Parsed hero images as: " + this.heroImgs);
            }
            return true;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "HeroImages [heroImgs=" + this.heroImgs + "]";
    }
}
