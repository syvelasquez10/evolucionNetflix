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

public final class KidsCharacter$Detail implements JsonPopulator
{
    private static final String TAG = "KidsCharacter.Detail";
    public int episodeCount;
    public boolean hasWatchedRecently;
    public int movieCount;
    public String storyImgUrl;
    public String synopsis;
    
    @Override
    public void populate(final JsonElement jsonElement) {
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Log.isLoggable("KidsCharacter.Detail", 2)) {
            Log.v("KidsCharacter.Detail", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0150: {
                switch (s.hashCode()) {
                    case 1590765524: {
                        if (s.equals("episodeCount")) {
                            n = 0;
                            break Label_0150;
                        }
                        break;
                    }
                    case -1855447105: {
                        if (s.equals("movieCount")) {
                            n = 1;
                            break Label_0150;
                        }
                        break;
                    }
                    case -2011547908: {
                        if (s.equals("hasWatchedRecently")) {
                            n = 2;
                            break Label_0150;
                        }
                        break;
                    }
                    case 1828656532: {
                        if (s.equals("synopsis")) {
                            n = 3;
                            break Label_0150;
                        }
                        break;
                    }
                    case -1551264767: {
                        if (s.equals("storyImgUrl")) {
                            n = 4;
                            break Label_0150;
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
                    this.episodeCount = jsonElement2.getAsInt();
                    continue;
                }
                case 1: {
                    this.movieCount = jsonElement2.getAsInt();
                    continue;
                }
                case 2: {
                    this.hasWatchedRecently = jsonElement2.getAsBoolean();
                    continue;
                }
                case 3: {
                    this.synopsis = jsonElement2.getAsString();
                    continue;
                }
                case 4: {
                    this.storyImgUrl = jsonElement2.getAsString();
                    continue;
                }
            }
        }
    }
    
    @Override
    public String toString() {
        return "Detail [episodeCount=" + this.episodeCount + ", movieCount=" + this.movieCount + ", hasWatchedRecently=" + this.hasWatchedRecently + ", synopsis=" + this.synopsis + ", storyImgUrl=" + this.storyImgUrl + "]";
    }
}
