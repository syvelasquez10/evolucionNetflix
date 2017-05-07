// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.originals;

import java.util.Iterator;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.util.JsonUtils;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.model.branches.FalkorObject;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;

public class BillboardLogo implements JsonPopulator, FalkorObject
{
    private static final String TAG = "Logo";
    private Integer height;
    private String tone;
    private String url;
    private Integer width;
    
    public BillboardLogo(final JsonElement jsonElement) {
        this.populate(jsonElement);
    }
    
    public Integer getHeight() {
        return this.height;
    }
    
    public String getTone() {
        return this.tone;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Integer getWidth() {
        return this.width;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Logo", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0138: {
                switch (s.hashCode()) {
                    case 116079: {
                        if (s.equals("url")) {
                            n = 0;
                            break Label_0138;
                        }
                        break;
                    }
                    case 113126854: {
                        if (s.equals("width")) {
                            n = 1;
                            break Label_0138;
                        }
                        break;
                    }
                    case -1221029593: {
                        if (s.equals("height")) {
                            n = 2;
                            break Label_0138;
                        }
                        break;
                    }
                    case 3565938: {
                        if (s.equals("tone")) {
                            n = 3;
                            break Label_0138;
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
                    this.url = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 1: {
                    this.width = JsonUtils.getAsIntSafe(jsonElement2);
                    continue;
                }
                case 2: {
                    this.height = JsonUtils.getAsIntSafe(jsonElement2);
                    continue;
                }
                case 3: {
                    this.tone = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
            }
        }
    }
}
