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
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.JsonPopulator;
import com.netflix.mediaclient.servicemgr.interface_.JsonMerger;

public class Video$Summary implements JsonMerger, JsonPopulator, Video
{
    private static final String TAG = "Summary";
    public String boxartImageTypeIdentifier;
    public String boxartUrl;
    public VideoType enumType;
    public VideoType errorType;
    public String horzDispSmallUrl;
    public String horzDispUrl;
    public String id;
    public boolean isEpisode;
    public boolean isOriginal;
    public boolean isPreRelease;
    public String stillImageUrl;
    public String storyImgDispUrl;
    public String title;
    public String tvCardUrl;
    public String type;
    public int videoYear;
    
    @Override
    public String getBoxartImageTypeIdentifier() {
        return this.boxartImageTypeIdentifier;
    }
    
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
    
    public boolean isEpisode() {
        return this.isEpisode;
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
                    case 1490782694: {
                        if (s.equals("boxartImageTypeIdentifier")) {
                            n = 2;
                            break Label_0226;
                        }
                        break;
                    }
                    case 3575610: {
                        if (s.equals("type")) {
                            n = 3;
                            break Label_0226;
                        }
                        break;
                    }
                    case 110371416: {
                        if (s.equals("title")) {
                            n = 4;
                            break Label_0226;
                        }
                        break;
                    }
                    case -2124216975: {
                        if (s.equals("isEpisode")) {
                            n = 5;
                            break Label_0226;
                        }
                        break;
                    }
                    case 329552226: {
                        if (s.equals("errorType")) {
                            n = 6;
                            break Label_0226;
                        }
                        break;
                    }
                    case -1794520227: {
                        if (s.equals("tvCardUrl")) {
                            n = 7;
                            break Label_0226;
                        }
                        break;
                    }
                    case -1217996834: {
                        if (s.equals("horzDispUrl")) {
                            n = 8;
                            break Label_0226;
                        }
                        break;
                    }
                    case 1882776025: {
                        if (s.equals("horzDispSmallUrl")) {
                            n = 9;
                            break Label_0226;
                        }
                        break;
                    }
                    case 1251932671: {
                        if (s.equals("storyImgDispUrl")) {
                            n = 10;
                            break Label_0226;
                        }
                        break;
                    }
                    case 1333091160: {
                        if (s.equals("videoYear")) {
                            n = 11;
                            break Label_0226;
                        }
                        break;
                    }
                    case 585773339: {
                        if (s.equals("isOriginal")) {
                            n = 12;
                            break Label_0226;
                        }
                        break;
                    }
                    case 135683246: {
                        if (s.equals("isPreRelease")) {
                            n = 13;
                            break Label_0226;
                        }
                        break;
                    }
                    case 530612188: {
                        if (s.equals("stillImageUrl")) {
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
                    this.boxartImageTypeIdentifier = jsonElement2.getAsString();
                    continue;
                }
                case 3: {
                    this.type = jsonElement2.getAsString();
                    continue;
                }
                case 4: {
                    this.title = jsonElement2.getAsString();
                    continue;
                }
                case 5: {
                    this.isEpisode = entry.getValue().getAsBoolean();
                    continue;
                }
                case 6: {
                    this.errorType = VideoType.create(jsonElement2.getAsString());
                    continue;
                }
                case 7: {
                    this.tvCardUrl = jsonElement2.getAsString();
                    continue;
                }
                case 8: {
                    this.horzDispUrl = jsonElement2.getAsString();
                    continue;
                }
                case 9: {
                    this.horzDispSmallUrl = jsonElement2.getAsString();
                    continue;
                }
                case 10: {
                    this.storyImgDispUrl = jsonElement2.getAsString();
                    continue;
                }
                case 11: {
                    this.videoYear = entry.getValue().getAsInt();
                    continue;
                }
                case 12: {
                    this.isOriginal = entry.getValue().getAsBoolean();
                    continue;
                }
                case 13: {
                    this.isPreRelease = entry.getValue().getAsBoolean();
                    continue;
                }
                case 14: {
                    this.stillImageUrl = jsonElement2.getAsString();
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
            case "id": {
                this.id = jsonParser.getValueAsString();
                break;
            }
            case "boxartUrl": {
                this.boxartUrl = jsonParser.getValueAsString();
                break;
            }
            case "boxartImageTypeIdentifier": {
                this.boxartImageTypeIdentifier = jsonParser.getValueAsString();
                break;
            }
            case "type": {
                this.type = jsonParser.getValueAsString();
                break;
            }
            case "title": {
                this.title = jsonParser.getValueAsString();
                break;
            }
            case "isEpisode": {
                this.isEpisode = jsonParser.getValueAsBoolean();
                break;
            }
            case "errorType": {
                this.errorType = VideoType.create(jsonParser.getValueAsString());
                break;
            }
            case "tvCardUrl": {
                this.tvCardUrl = jsonParser.getValueAsString();
                break;
            }
            case "horzDispUrl": {
                this.horzDispUrl = jsonParser.getValueAsString();
                break;
            }
            case "horzDispSmallUrl": {
                this.horzDispSmallUrl = jsonParser.getValueAsString();
                break;
            }
            case "storyImgDispUrl": {
                this.storyImgDispUrl = jsonParser.getValueAsString();
                break;
            }
            case "videoYear": {
                this.videoYear = jsonParser.getValueAsInt();
                break;
            }
            case "isOriginal": {
                this.isOriginal = jsonParser.getValueAsBoolean();
                break;
            }
            case "isPreRelease": {
                this.isPreRelease = jsonParser.getValueAsBoolean();
                break;
            }
            case "stillImageUrl": {
                this.stillImageUrl = jsonParser.getValueAsString();
                break;
            }
        }
        return true;
    }
    
    public void setErrorType(final VideoType errorType) {
        this.errorType = errorType;
    }
    
    @Override
    public String toString() {
        return "Summary [id=" + this.id + ", type=" + this.type + ", title=" + this.title + ", isEpisode=" + this.isEpisode + "]";
    }
}
