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
import com.netflix.falkor.BranchNodeUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public class Delivery implements JsonMerger, JsonPopulator
{
    private static final String TAG = "Delivery";
    private Boolean has3D;
    private Boolean has51Audio;
    private Boolean hasHD;
    private Boolean hasUltraHD;
    private String quality;
    
    public Delivery(final JsonParser jsonParser) {
        BranchNodeUtils.merge(this, jsonParser, jsonParser.getCurrentToken(), false, 10);
    }
    
    public Delivery(final JsonElement jsonElement) {
        this.populate(jsonElement);
    }
    
    public String getQuality() {
        return this.quality;
    }
    
    public Boolean has3D() {
        return this.has3D;
    }
    
    public Boolean has51Audio() {
        return this.has51Audio;
    }
    
    public Boolean hasHD() {
        return this.hasHD;
    }
    
    public Boolean hasUltraHD() {
        return this.hasUltraHD;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Delivery", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0146: {
                switch (s.hashCode()) {
                    case 99048075: {
                        if (s.equals("has3D")) {
                            n = 0;
                            break Label_0146;
                        }
                        break;
                    }
                    case 99048726: {
                        if (s.equals("hasHD")) {
                            n = 1;
                            break Label_0146;
                        }
                        break;
                    }
                    case 1698703790: {
                        if (s.equals("hasUltraHD")) {
                            n = 2;
                            break Label_0146;
                        }
                        break;
                    }
                    case 1627230912: {
                        if (s.equals("has51Audio")) {
                            n = 3;
                            break Label_0146;
                        }
                        break;
                    }
                    case 651215103: {
                        if (s.equals("quality")) {
                            n = 4;
                            break Label_0146;
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
                    this.has3D = JsonUtils.getAsBoolSafe(jsonElement2);
                    continue;
                }
                case 1: {
                    this.hasHD = JsonUtils.getAsBoolSafe(jsonElement2);
                    continue;
                }
                case 2: {
                    this.hasUltraHD = JsonUtils.getAsBoolSafe(jsonElement2);
                    continue;
                }
                case 3: {
                    this.has51Audio = JsonUtils.getAsBoolSafe(jsonElement2);
                    continue;
                }
                case 4: {
                    this.quality = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
            }
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Delivery", "Populating with: " + jsonParser);
        }
        switch (s) {
            default: {
                return false;
            }
            case "has3D": {
                this.has3D = jsonParser.getValueAsBoolean();
                break;
            }
            case "hasHD": {
                this.hasHD = jsonParser.getValueAsBoolean();
                break;
            }
            case "hasUltraHD": {
                this.hasUltraHD = jsonParser.getValueAsBoolean();
                break;
            }
            case "has51Audio": {
                this.has51Audio = jsonParser.getValueAsBoolean();
                break;
            }
            case "quality": {
                this.quality = jsonParser.getValueAsString();
                break;
            }
        }
        return true;
    }
}
