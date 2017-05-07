// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;

public class Video$KubrickSummary implements JsonPopulator
{
    private static final String TAG = "KubrickSummary";
    public String certification;
    public String horzDispUrl;
    public boolean isHd;
    public String narrative;
    public float predictedRating;
    public int runtime;
    public int seasonCount;
    public String storyImgUrl;
    public String titleUrl;
    public int year;
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Log.isLoggable()) {
            Log.v("KubrickSummary", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0186: {
                switch (s.hashCode()) {
                    case 3704893: {
                        if (s.equals("year")) {
                            n = 0;
                            break Label_0186;
                        }
                        break;
                    }
                    case 1750452338: {
                        if (s.equals("narrative")) {
                            n = 1;
                            break Label_0186;
                        }
                        break;
                    }
                    case -644524870: {
                        if (s.equals("certification")) {
                            n = 2;
                            break Label_0186;
                        }
                        break;
                    }
                    case 1550962648: {
                        if (s.equals("runtime")) {
                            n = 3;
                            break Label_0186;
                        }
                        break;
                    }
                    case -885502996: {
                        if (s.equals("seasonCount")) {
                            n = 4;
                            break Label_0186;
                        }
                        break;
                    }
                    case -1551264767: {
                        if (s.equals("storyImgUrl")) {
                            n = 5;
                            break Label_0186;
                        }
                        break;
                    }
                    case -1217996834: {
                        if (s.equals("horzDispUrl")) {
                            n = 6;
                            break Label_0186;
                        }
                        break;
                    }
                    case 3240902: {
                        if (s.equals("isHd")) {
                            n = 7;
                            break Label_0186;
                        }
                        break;
                    }
                    case -263240971: {
                        if (s.equals("predictedRating")) {
                            n = 8;
                            break Label_0186;
                        }
                        break;
                    }
                    case -1870009353: {
                        if (s.equals("titleUrl")) {
                            n = 9;
                            break Label_0186;
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
                    this.year = jsonElement2.getAsInt();
                    continue;
                }
                case 1: {
                    this.narrative = jsonElement2.getAsString();
                    continue;
                }
                case 2: {
                    this.certification = jsonElement2.getAsString();
                    continue;
                }
                case 3: {
                    this.runtime = jsonElement2.getAsInt();
                    continue;
                }
                case 4: {
                    this.seasonCount = jsonElement2.getAsInt();
                    continue;
                }
                case 5: {
                    this.storyImgUrl = jsonElement2.getAsString();
                    continue;
                }
                case 6: {
                    this.horzDispUrl = jsonElement2.getAsString();
                    continue;
                }
                case 7: {
                    this.isHd = jsonElement2.getAsBoolean();
                    continue;
                }
                case 8: {
                    this.predictedRating = jsonElement2.getAsFloat();
                    continue;
                }
                case 9: {
                    this.titleUrl = jsonElement2.getAsString();
                    continue;
                }
            }
        }
    }
}
