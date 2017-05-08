// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;
import com.google.gson.JsonObject;
import com.google.gson.JsonNull;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public class PostPlayAsset implements JsonMerger, JsonPopulator
{
    private static final String TAG = "PostPlayAsset";
    private String assetType;
    private int height;
    private boolean isBadged;
    private PostPlayAsset$Tone tone;
    private PostPlayAsset$Type type;
    private String url;
    private int width;
    
    public PostPlayAsset(final PostPlayAsset$Type postPlayAsset$Type) {
        this(postPlayAsset$Type, null);
    }
    
    public PostPlayAsset(final PostPlayAsset$Type type, final JsonElement jsonElement) {
        this.width = 0;
        this.height = 0;
        this.isBadged = false;
        this.type = type;
        if (jsonElement != null) {
            this.populate(jsonElement);
        }
    }
    
    public String getAssetType() {
        return this.assetType;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public PostPlayAsset$Tone getTone() {
        return this.tone;
    }
    
    public PostPlayAsset$Type getType() {
        return this.type;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public boolean isBadged() {
        return this.isBadged;
    }
    
    @Override
    public void populate(JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("PostPlayAsset", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            jsonElement = entry.getValue();
            if (!(jsonElement instanceof JsonNull)) {
                final String s = entry.getKey();
                int n = 0;
                Label_0162: {
                    switch (s.hashCode()) {
                        case 116079: {
                            if (s.equals("url")) {
                                n = 0;
                                break Label_0162;
                            }
                            break;
                        }
                        case 1315305034: {
                            if (s.equals("assetType")) {
                                n = 1;
                                break Label_0162;
                            }
                            break;
                        }
                        case 113126854: {
                            if (s.equals("width")) {
                                n = 2;
                                break Label_0162;
                            }
                            break;
                        }
                        case -1221029593: {
                            if (s.equals("height")) {
                                n = 3;
                                break Label_0162;
                            }
                            break;
                        }
                        case -722613525: {
                            if (s.equals("isBadged")) {
                                n = 4;
                                break Label_0162;
                            }
                            break;
                        }
                        case 3565938: {
                            if (s.equals("tone")) {
                                n = 5;
                                break Label_0162;
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
                        this.url = jsonElement.getAsString();
                        continue;
                    }
                    case 1: {
                        this.assetType = jsonElement.getAsString();
                        continue;
                    }
                    case 2: {
                        this.width = jsonElement.getAsInt();
                        continue;
                    }
                    case 3: {
                        this.height = jsonElement.getAsInt();
                        continue;
                    }
                    case 4: {
                        this.isBadged = jsonElement.getAsBoolean();
                        continue;
                    }
                    case 5: {
                        PostPlayAsset$Tone tone;
                        if (jsonElement.getAsString().equals(PostPlayAsset$Tone.DARK.toString())) {
                            tone = PostPlayAsset$Tone.DARK;
                        }
                        else {
                            tone = PostPlayAsset$Tone.LIGHT;
                        }
                        this.tone = tone;
                        continue;
                    }
                }
            }
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        return false;
    }
    
    public void setAssetType(final String assetType) {
        this.assetType = assetType;
    }
    
    public void setBadged(final boolean isBadged) {
        this.isBadged = isBadged;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public void setTone(final PostPlayAsset$Tone tone) {
        this.tone = tone;
    }
    
    public void setType(final PostPlayAsset$Type type) {
        this.type = type;
    }
    
    public void setUrl(final String url) {
        this.url = url;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
}
