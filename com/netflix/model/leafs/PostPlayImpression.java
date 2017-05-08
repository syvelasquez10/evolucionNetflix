// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.google.gson.JsonObject;
import com.google.gson.JsonNull;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.model.branches.FalkorObject;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;

public class PostPlayImpression implements JsonPopulator, FalkorObject
{
    private String TAG;
    boolean success;
    
    public PostPlayImpression() {
        this.TAG = "PostPlayImpression";
    }
    
    public boolean isSuccess() {
        return this.success;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v(this.TAG, "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            if (!(jsonElement2 instanceof JsonNull)) {
                final String s = entry.getKey();
                int n = 0;
                Label_0122: {
                    switch (s.hashCode()) {
                        case -1867169789: {
                            if (s.equals("success")) {
                                n = 0;
                                break Label_0122;
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
                        this.success = jsonElement2.getAsBoolean();
                        continue;
                    }
                }
            }
        }
    }
    
    public void setSuccess(final boolean success) {
        this.success = success;
    }
}
