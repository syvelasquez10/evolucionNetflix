// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.google.gson.JsonObject;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import com.google.gson.JsonElement;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;

public class Video$Summary implements JsonPopulator, Video
{
    private static final String TAG = "Summary";
    public String boxartUrl;
    public VideoType enumType;
    public VideoType errorType;
    public boolean hasWatched;
    public String horzDispSmallUrl;
    public String horzDispUrl;
    public String id;
    public boolean isEpisode;
    public boolean isNSRE;
    public boolean isOriginal;
    public boolean isPreRelease;
    public String storyImgDispUrl;
    public String title;
    public String tvCardUrl;
    public String type;
    public int videoYear;
    
    @Override
    public String getBoxshotUrl() {
        return this.boxartUrl;
    }
    
    @Override
    public VideoType getErrorType() {
        return this.errorType;
    }
    
    @Override
    public String getHorzDispSmallUrl() {
        return this.horzDispSmallUrl;
    }
    
    @Override
    public String getHorzDispUrl() {
        return this.horzDispUrl;
    }
    
    @Override
    public String getId() {
        return this.id;
    }
    
    @Override
    public String getStoryDispUrl() {
        return this.storyImgDispUrl;
    }
    
    @Override
    public String getTitle() {
        return this.title;
    }
    
    @Override
    public String getTvCardUrl() {
        return this.tvCardUrl;
    }
    
    @Override
    public VideoType getType() {
        if (this.enumType == null) {
            this.enumType = VideoType.create(this.type);
        }
        return this.enumType;
    }
    
    @Override
    public boolean hasWatched() {
        return this.hasWatched;
    }
    
    public boolean isEpisode() {
        return this.isEpisode;
    }
    
    @Override
    public boolean isNSRE() {
        return this.isNSRE;
    }
    
    @Override
    public boolean isOriginal() {
        return this.isOriginal;
    }
    
    @Override
    public boolean isPreRelease() {
        return this.isPreRelease;
    }
    
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
            Label_0226: {
                switch (s.hashCode()) {
                    case 3355: {
                        if (s.equals("id")) {
                            n = 0;
                            break Label_0226;
                        }
                        break;
                    }
                    case 1153650071: {
                        if (s.equals("boxartUrl")) {
                            n = 1;
                            break Label_0226;
                        }
                        break;
                    }
                    case 3575610: {
                        if (s.equals("type")) {
                            n = 2;
                            break Label_0226;
                        }
                        break;
                    }
                    case 110371416: {
                        if (s.equals("title")) {
                            n = 3;
                            break Label_0226;
                        }
                        break;
                    }
                    case -2124216975: {
                        if (s.equals("isEpisode")) {
                            n = 4;
                            break Label_0226;
                        }
                        break;
                    }
                    case 329552226: {
                        if (s.equals("errorType")) {
                            n = 5;
                            break Label_0226;
                        }
                        break;
                    }
                    case -1794520227: {
                        if (s.equals("tvCardUrl")) {
                            n = 6;
                            break Label_0226;
                        }
                        break;
                    }
                    case -1217996834: {
                        if (s.equals("horzDispUrl")) {
                            n = 7;
                            break Label_0226;
                        }
                        break;
                    }
                    case 1882776025: {
                        if (s.equals("horzDispSmallUrl")) {
                            n = 8;
                            break Label_0226;
                        }
                        break;
                    }
                    case 1251932671: {
                        if (s.equals("storyImgDispUrl")) {
                            n = 9;
                            break Label_0226;
                        }
                        break;
                    }
                    case 1333091160: {
                        if (s.equals("videoYear")) {
                            n = 10;
                            break Label_0226;
                        }
                        break;
                    }
                    case 585773339: {
                        if (s.equals("isOriginal")) {
                            n = 11;
                            break Label_0226;
                        }
                        break;
                    }
                    case -1180295454: {
                        if (s.equals("isNSRE")) {
                            n = 12;
                            break Label_0226;
                        }
                        break;
                    }
                    case -1136616012: {
                        if (s.equals("hasWatched")) {
                            n = 13;
                            break Label_0226;
                        }
                        break;
                    }
                    case 135683246: {
                        if (s.equals("isPreRelease")) {
                            n = 14;
                            break Label_0226;
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
                    this.id = jsonElement2.getAsString();
                    continue;
                }
                case 1: {
                    this.boxartUrl = jsonElement2.getAsString();
                    continue;
                }
                case 2: {
                    this.type = jsonElement2.getAsString();
                    continue;
                }
                case 3: {
                    this.title = jsonElement2.getAsString();
                    continue;
                }
                case 4: {
                    this.isEpisode = entry.getValue().getAsBoolean();
                    continue;
                }
                case 5: {
                    this.errorType = VideoType.create(jsonElement2.getAsString());
                    continue;
                }
                case 6: {
                    this.tvCardUrl = jsonElement2.getAsString();
                    continue;
                }
                case 7: {
                    this.horzDispUrl = jsonElement2.getAsString();
                    continue;
                }
                case 8: {
                    this.horzDispSmallUrl = jsonElement2.getAsString();
                    continue;
                }
                case 9: {
                    this.storyImgDispUrl = jsonElement2.getAsString();
                    continue;
                }
                case 10: {
                    this.videoYear = entry.getValue().getAsInt();
                    continue;
                }
                case 11: {
                    this.isOriginal = entry.getValue().getAsBoolean();
                    continue;
                }
                case 12: {
                    this.isNSRE = entry.getValue().getAsBoolean();
                    continue;
                }
                case 13: {
                    this.hasWatched = entry.getValue().getAsBoolean();
                    continue;
                }
                case 14: {
                    this.isPreRelease = entry.getValue().getAsBoolean();
                    continue;
                }
            }
        }
    }
    
    public void setErrorType(final VideoType errorType) {
        this.errorType = errorType;
    }
    
    @Override
    public String toString() {
        return "Summary [id=" + this.id + ", type=" + this.type + ", title=" + this.title + ", isEpisode=" + this.isEpisode + "]";
    }
}
