// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.model.UserRating;
import com.netflix.mediaclient.servicemgr.model.JsonPopulator;

public final class Video$UserRating implements JsonPopulator, UserRating
{
    private static final String TAG = "UserRating";
    public float userRating;
    
    @Override
    public float getUserRating() {
        return this.userRating;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("UserRating", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0114: {
                switch (s.hashCode()) {
                    case 1546011976: {
                        if (s.equals("userRating")) {
                            n = 0;
                            break Label_0114;
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
                    this.userRating = jsonElement2.getAsFloat();
                    continue;
                }
            }
        }
    }
    
    @Override
    public String toString() {
        return "Rating [userRating=" + this.userRating + "]";
    }
}
