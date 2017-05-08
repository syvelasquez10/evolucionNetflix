// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import com.fasterxml.jackson.core.JsonParser;
import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public class FalkorActorStill$Summary implements JsonMerger, JsonPopulator
{
    private static final String TAG = "Summary";
    public String personId;
    public String stillImageHeight;
    public String stillImageUrl;
    public String stillImageWidth;
    public float stillImageXFocus;
    public float stillImageYFocus;
    public String videoId;
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Summary", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0162: {
                switch (s.hashCode()) {
                    case 530612188: {
                        if (s.equals("stillImageUrl")) {
                            n = 0;
                            break Label_0162;
                        }
                        break;
                    }
                    case 1603871642: {
                        if (s.equals("stillImageHeight")) {
                            n = 1;
                            break Label_0162;
                        }
                        break;
                    }
                    case -1181220621: {
                        if (s.equals("stillImageWidth")) {
                            n = 2;
                            break Label_0162;
                        }
                        break;
                    }
                    case 2033484211: {
                        if (s.equals("stillImageXFocus")) {
                            n = 3;
                            break Label_0162;
                        }
                        break;
                    }
                    case 2062113362: {
                        if (s.equals("stillImageYFocus")) {
                            n = 4;
                            break Label_0162;
                        }
                        break;
                    }
                    case 452782838: {
                        if (s.equals("videoId")) {
                            n = 5;
                            break Label_0162;
                        }
                        break;
                    }
                    case 443163472: {
                        if (s.equals("personId")) {
                            n = 6;
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
                    this.stillImageUrl = jsonElement2.getAsString();
                    continue;
                }
                case 1: {
                    this.stillImageHeight = jsonElement2.getAsString();
                    continue;
                }
                case 2: {
                    this.stillImageWidth = jsonElement2.getAsString();
                    continue;
                }
                case 3: {
                    this.stillImageXFocus = jsonElement2.getAsFloat();
                    continue;
                }
                case 4: {
                    this.stillImageYFocus = jsonElement2.getAsFloat();
                    continue;
                }
                case 5: {
                    this.videoId = jsonElement2.getAsString();
                    continue;
                }
                case 6: {
                    this.personId = jsonElement2.getAsString();
                    continue;
                }
            }
        }
    }
    
    @Override
    public boolean set(final String s, final JsonParser jsonParser) {
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Summary", "Populating with: " + jsonParser);
        }
        switch (s) {
            default: {
                return false;
            }
            case "stillImageUrl": {
                this.stillImageUrl = jsonParser.getValueAsString();
                break;
            }
            case "stillImageHeight": {
                this.stillImageHeight = jsonParser.getValueAsString();
                break;
            }
            case "stillImageWidth": {
                this.stillImageWidth = jsonParser.getValueAsString();
                break;
            }
            case "stillImageXFocus": {
                this.stillImageXFocus = (float)jsonParser.getValueAsDouble();
                break;
            }
            case "stillImageYFocus": {
                this.stillImageYFocus = (float)jsonParser.getValueAsDouble();
                break;
            }
            case "videoId": {
                this.videoId = jsonParser.getValueAsString();
                break;
            }
            case "personId": {
                this.personId = jsonParser.getValueAsString();
                break;
            }
        }
        return true;
    }
}
