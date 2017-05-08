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
import com.netflix.mediaclient.servicemgr.interface_.ExpiringContentType;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;
import com.netflix.mediaclient.servicemgr.interface_.IExpiringContentWarning;

public class ExpiringContent implements IExpiringContentWarning, JsonMerger, JsonPopulator
{
    private static final String TAG = "ExpiringContent";
    private long expirationTime;
    private String localizedDate;
    private ExpiringContentType type;
    private boolean willExpire;
    
    public ExpiringContent() {
        this.type = ExpiringContentType.SEASON;
        this.expirationTime = -1L;
    }
    
    @Override
    public long getExpirationTime() {
        return this.expirationTime;
    }
    
    @Override
    public String getLocalizedDate() {
        return this.localizedDate;
    }
    
    @Override
    public ExpiringContentType getWarningType() {
        return this.type;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("ExpiringContent", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0138: {
                switch (s.hashCode()) {
                    case -18680943: {
                        if (s.equals("willExpire")) {
                            n = 0;
                            break Label_0138;
                        }
                        break;
                    }
                    case 3575610: {
                        if (s.equals("type")) {
                            n = 1;
                            break Label_0138;
                        }
                        break;
                    }
                    case -668327396: {
                        if (s.equals("expirationTime")) {
                            n = 2;
                            break Label_0138;
                        }
                        break;
                    }
                    case 696250825: {
                        if (s.equals("localizedDate")) {
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
                    this.willExpire = jsonElement2.getAsBoolean();
                    continue;
                }
                case 1: {
                    this.type = ExpiringContentType.valueOf(jsonElement2.getAsString().toUpperCase());
                    continue;
                }
                case 2: {
                    this.expirationTime = jsonElement2.getAsLong();
                    continue;
                }
                case 3: {
                    this.localizedDate = jsonElement2.getAsString();
                    continue;
                }
            }
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("ExpiringContent", "Populating with: " + jsonParser);
        }
        switch (s) {
            default: {
                return false;
            }
            case "willExpire": {
                this.willExpire = jsonParser.getValueAsBoolean();
                break;
            }
            case "type": {
                this.type = ExpiringContentType.valueOf(jsonParser.getValueAsString().toUpperCase());
                break;
            }
            case "expirationTime": {
                this.expirationTime = jsonParser.getValueAsLong();
                break;
            }
            case "localizedDate": {
                this.localizedDate = jsonParser.getValueAsString();
                break;
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "type=" + this.type + ", willExpire=" + this.willExpire + ", expirationTime=" + this.expirationTime + ", localizedDate=" + this.localizedDate + "]";
    }
    
    @Override
    public boolean willExpire() {
        return this.willExpire;
    }
}
