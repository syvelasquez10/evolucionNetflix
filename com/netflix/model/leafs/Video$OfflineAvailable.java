// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public final class Video$OfflineAvailable implements JsonMerger, JsonPopulator
{
    private static final String TAG = "OfflineAvailable";
    public boolean isAvailableOffline;
    
    public boolean isAvailableOffline() {
        return this.isAvailableOffline;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("OfflineAvailable", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0114: {
                switch (s.hashCode()) {
                    case -1284582364: {
                        if (s.equals("isAvailableOffline")) {
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
                    this.isAvailableOffline = jsonElement2.getAsBoolean();
                    continue;
                }
            }
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("OfflineAvailable", "Populating with: " + jsonParser);
        }
        switch (s) {
            default: {
                return false;
            }
            case "isAvailableOffline": {
                this.isAvailableOffline = jsonParser.getValueAsBoolean();
                return true;
            }
        }
    }
    
    @Override
    public String toString() {
        return "offlineAvailable [offlineAvailable=" + this.isAvailableOffline + "]";
    }
}
