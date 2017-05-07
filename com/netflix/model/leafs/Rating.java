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
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;

public class Rating implements JsonPopulator
{
    private static final String TAG = "Rating";
    private float average;
    private float predicted;
    private float userRating;
    
    public Rating(final JsonElement jsonElement) {
        this.populate(jsonElement);
    }
    
    public float getAverage() {
        return this.average;
    }
    
    public float getPredicted() {
        return this.predicted;
    }
    
    public float getUserRating() {
        return this.userRating;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Rating", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0130: {
                switch (s.hashCode()) {
                    case -631448035: {
                        if (s.equals("average")) {
                            n = 0;
                            break Label_0130;
                        }
                        break;
                    }
                    case -1348014280: {
                        if (s.equals("predicted")) {
                            n = 1;
                            break Label_0130;
                        }
                        break;
                    }
                    case 1546011976: {
                        if (s.equals("userRating")) {
                            n = 2;
                            break Label_0130;
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
                    this.average = JsonUtils.getAsFloatSafe(jsonElement2);
                    continue;
                }
                case 1: {
                    this.predicted = JsonUtils.getAsFloatSafe(jsonElement2);
                    continue;
                }
                case 2: {
                    this.userRating = JsonUtils.getAsFloatSafe(jsonElement2);
                    continue;
                }
            }
        }
    }
}
