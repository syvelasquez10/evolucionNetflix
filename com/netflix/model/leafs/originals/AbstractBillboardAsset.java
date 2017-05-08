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
import com.netflix.falkor.BranchNodeUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.netflix.model.branches.FalkorObject;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public abstract class AbstractBillboardAsset implements JsonMerger, JsonPopulator, FalkorObject
{
    private final String TAG;
    private Integer height;
    private String tone;
    private String url;
    private Integer width;
    
    public AbstractBillboardAsset(final JsonParser jsonParser) {
        this.TAG = this.getTag();
        BranchNodeUtils.merge(this, jsonParser, jsonParser.getCurrentToken(), false, 10);
    }
    
    public AbstractBillboardAsset(final JsonElement jsonElement) {
        this.TAG = this.getTag();
        this.populate(jsonElement);
    }
    
    public Integer getHeight() {
        return this.height;
    }
    
    public abstract String getTag();
    
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
            Log.v(this.TAG, "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0142: {
                switch (s.hashCode()) {
                    case 116079: {
                        if (s.equals("url")) {
                            n = 0;
                            break Label_0142;
                        }
                        break;
                    }
                    case 113126854: {
                        if (s.equals("width")) {
                            n = 1;
                            break Label_0142;
                        }
                        break;
                    }
                    case -1221029593: {
                        if (s.equals("height")) {
                            n = 2;
                            break Label_0142;
                        }
                        break;
                    }
                    case 3565938: {
                        if (s.equals("tone")) {
                            n = 3;
                            break Label_0142;
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
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v(this.TAG, "Populating with: " + jsonParser);
        }
        switch (s) {
            default: {
                return false;
            }
            case "url": {
                this.url = jsonParser.getValueAsString();
                break;
            }
            case "width": {
                this.width = jsonParser.getValueAsInt();
                break;
            }
            case "height": {
                this.height = jsonParser.getValueAsInt();
                break;
            }
            case "tone": {
                this.tone = jsonParser.getValueAsString();
                break;
            }
        }
        return true;
    }
}
