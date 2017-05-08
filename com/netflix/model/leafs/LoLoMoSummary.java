// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.google.gson.annotations.SerializedName;
import com.netflix.mediaclient.servicemgr.interface_.LoLoMo;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public class LoLoMoSummary implements JsonMerger, JsonPopulator, LoLoMo
{
    private static final String TAG = "LoLoMoSummary";
    private String mGenreId;
    @SerializedName("length")
    private int mNumLoMos;
    @SerializedName("title")
    private String mTitle;
    
    @Override
    public String getId() {
        return this.mGenreId;
    }
    
    @Override
    public String getLolomosId() {
        return null;
    }
    
    @Override
    public int getNumLoMos() {
        return this.mNumLoMos;
    }
    
    @Override
    public String getTitle() {
        return this.mTitle;
    }
    
    @Override
    public LoMoType getType() {
        return LoMoType.STANDARD;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("LoLoMoSummary", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final String s = entry.getKey();
            int n = 0;
            Label_0110: {
                switch (s.hashCode()) {
                    case 110371416: {
                        if (s.equals("title")) {
                            n = 0;
                            break Label_0110;
                        }
                        break;
                    }
                    case -1106363674: {
                        if (s.equals("length")) {
                            n = 1;
                            break Label_0110;
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
                    this.mTitle = StringUtils.decodeHtmlEntities(entry.getValue().getAsString());
                    continue;
                }
                case 1: {
                    this.mNumLoMos = entry.getValue().getAsInt();
                    continue;
                }
            }
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        switch (s) {
            default: {
                return false;
            }
            case "title": {
                this.mTitle = StringUtils.decodeHtmlEntities(jsonParser.getValueAsString());
                break;
            }
            case "length": {
                this.mNumLoMos = jsonParser.getValueAsInt();
                break;
            }
        }
        return true;
    }
    
    public void setGenreId(final String mGenreId) {
        this.mGenreId = mGenreId;
    }
}
