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
import com.netflix.mediaclient.servicemgr.model.JsonPopulator;

public final class Video$InQueue implements JsonPopulator
{
    private static final String TAG = "InQueue";
    public boolean inQueue;
    
    public Video$InQueue() {
    }
    
    public Video$InQueue(final boolean inQueue) {
        this.inQueue = inQueue;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("InQueue", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0114: {
                switch (s.hashCode()) {
                    case 1926204140: {
                        if (s.equals("inQueue")) {
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
                    this.inQueue = jsonElement2.getAsBoolean();
                    continue;
                }
            }
        }
    }
    
    @Override
    public String toString() {
        return "InQueue [inQueue=" + this.inQueue + "]";
    }
}
