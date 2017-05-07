// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.JsonPopulator;

public class Video$HeroImages implements JsonPopulator
{
    private static final String TAG = "HeroImages";
    public List<String> heroImgs;
    
    public Video$HeroImages() {
        this.heroImgs = new ArrayList<String>(3);
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Log.isLoggable("HeroImages", 2)) {
            Log.v("HeroImages", "Populating with: " + asJsonObject);
        }
        final JsonArray asJsonArray = asJsonObject.getAsJsonArray("heroImgs");
        this.heroImgs.clear();
        for (int i = 0; i < asJsonArray.size(); ++i) {
            this.heroImgs.add(asJsonArray.get(i).getAsString());
        }
        if (Log.isLoggable("HeroImages", 3)) {
            Log.v("HeroImages", "Parsed hero images as: " + this.heroImgs);
        }
    }
    
    @Override
    public String toString() {
        return "HeroImages [heroImgs=" + this.heroImgs + "]";
    }
}
