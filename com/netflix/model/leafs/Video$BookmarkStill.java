// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.model.JsonPopulator;

public final class Video$BookmarkStill implements JsonPopulator
{
    private static final String TAG = "BookmarkStill";
    public String stillUrl;
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Log.isLoggable("BookmarkStill", 2)) {
            Log.v("BookmarkStill", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0118: {
                switch (s.hashCode()) {
                    case 1540041383: {
                        if (s.equals("stillUrl")) {
                            n = 0;
                            break Label_0118;
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
                    this.stillUrl = jsonElement2.getAsString();
                    continue;
                }
            }
        }
    }
}
