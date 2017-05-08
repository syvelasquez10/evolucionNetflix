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
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;

public final class Episode$Detail extends Video$Detail
{
    private static final String TAG = "Episode.Detail";
    public String abbrSeqLabel;
    private String availabilityDateMsg;
    private String boxartUrl;
    private VideoType enumType;
    private int episodeNumber;
    private VideoType errorType;
    private String id;
    private String nextEpisodeId;
    private String nextEpisodeTitle;
    private String seasonId;
    private int seasonNumber;
    private String showId;
    private String showRestUrl;
    private String showTitle;
    private String title;
    private String type;
    
    public String getAvailabilityDateMessage() {
        return this.availabilityDateMsg;
    }
    
    public List<String> getEpisodeBadges() {
        return this.episodeBadges;
    }
    
    public int getEpisodeNumber() {
        return this.episodeNumber;
    }
    
    public String getHorzDispUrl() {
        return this.horzDispUrl;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getInterestingSmallUrl() {
        return this.interestingSmallUrl;
    }
    
    public String getInterestingUrl() {
        return this.interestingUrl;
    }
    
    public String getNextEpisodeId() {
        return this.nextEpisodeId;
    }
    
    public String getNextEpisodeTitle() {
        return this.nextEpisodeTitle;
    }
    
    public String getSeasonId() {
        return this.seasonId;
    }
    
    public int getSeasonNumber() {
        return this.seasonNumber;
    }
    
    public String getShowId() {
        return this.showId;
    }
    
    public String getShowRestUrl() {
        return this.showRestUrl;
    }
    
    public String getShowTitle() {
        return this.showTitle;
    }
    
    public String getSoryImgUrl() {
        return this.storyImgUrl;
    }
    
    public String getSynopsis() {
        return this.synopsis;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public VideoType getType() {
        if (this.enumType == null) {
            this.enumType = VideoType.create(this.type);
        }
        return this.enumType;
    }
    
    public boolean isAgeProtected() {
        return this.isAgeProtected;
    }
    
    public boolean isAutoPlayEnabled() {
        return this.isAutoPlayEnabled;
    }
    
    public boolean isNextPlayableEpisode() {
        return this.isNextPlayableEpisode;
    }
    
    public boolean isPinProtected() {
        return this.isPinProtected;
    }
    
    @Override
    public void populate(final JsonElement jsonElement) {
        super.populate(jsonElement);
        final JsonObject asJsonObject = jsonElement.getAsJsonObject();
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("Episode.Detail", "Populating with: " + asJsonObject);
        }
        for (final Map.Entry<String, JsonElement> entry : asJsonObject.entrySet()) {
            final JsonElement jsonElement2 = entry.getValue();
            final String s = entry.getKey();
            int n = 0;
            Label_0230: {
                switch (s.hashCode()) {
                    case 3355: {
                        if (s.equals("id")) {
                            n = 0;
                            break Label_0230;
                        }
                        break;
                    }
                    case 3575610: {
                        if (s.equals("type")) {
                            n = 1;
                            break Label_0230;
                        }
                        break;
                    }
                    case 889931614: {
                        if (s.equals("seasonId")) {
                            n = 2;
                            break Label_0230;
                        }
                        break;
                    }
                    case -903145224: {
                        if (s.equals("showId")) {
                            n = 3;
                            break Label_0230;
                        }
                        break;
                    }
                    case -1913803429: {
                        if (s.equals("showTitle")) {
                            n = 4;
                            break Label_0230;
                        }
                        break;
                    }
                    case 1485653822: {
                        if (s.equals("showRestUrl")) {
                            n = 5;
                            break Label_0230;
                        }
                        break;
                    }
                    case 110371416: {
                        if (s.equals("title")) {
                            n = 6;
                            break Label_0230;
                        }
                        break;
                    }
                    case 1153650071: {
                        if (s.equals("boxartUrl")) {
                            n = 7;
                            break Label_0230;
                        }
                        break;
                    }
                    case -406164797: {
                        if (s.equals("nextEpisodeId")) {
                            n = 8;
                            break Label_0230;
                        }
                        break;
                    }
                    case -1122171984: {
                        if (s.equals("nextEpisodeTitle")) {
                            n = 9;
                            break Label_0230;
                        }
                        break;
                    }
                    case -1905664732: {
                        if (s.equals("episodeNumber")) {
                            n = 10;
                            break Label_0230;
                        }
                        break;
                    }
                    case -1360577524: {
                        if (s.equals("seasonNumber")) {
                            n = 11;
                            break Label_0230;
                        }
                        break;
                    }
                    case -1779904744: {
                        if (s.equals("availabilityDateMsg")) {
                            n = 12;
                            break Label_0230;
                        }
                        break;
                    }
                    case 329552226: {
                        if (s.equals("errorType")) {
                            n = 13;
                            break Label_0230;
                        }
                        break;
                    }
                    case -965087962: {
                        if (s.equals("abbrSeqLabel")) {
                            n = 14;
                            break Label_0230;
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
                    this.type = jsonElement2.getAsString();
                    continue;
                }
                case 2: {
                    this.seasonId = jsonElement2.getAsString();
                    continue;
                }
                case 3: {
                    this.showId = jsonElement2.getAsString();
                    continue;
                }
                case 4: {
                    this.showTitle = jsonElement2.getAsString();
                    continue;
                }
                case 5: {
                    this.showRestUrl = jsonElement2.getAsString();
                    continue;
                }
                case 6: {
                    this.title = jsonElement2.getAsString();
                    continue;
                }
                case 7: {
                    this.boxartUrl = jsonElement2.getAsString();
                    continue;
                }
                case 8: {
                    this.nextEpisodeId = jsonElement2.getAsString();
                    continue;
                }
                case 9: {
                    this.nextEpisodeTitle = jsonElement2.getAsString();
                    continue;
                }
                case 10: {
                    this.episodeNumber = jsonElement2.getAsInt();
                    continue;
                }
                case 11: {
                    this.seasonNumber = jsonElement2.getAsInt();
                    continue;
                }
                case 12: {
                    this.availabilityDateMsg = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
                case 13: {
                    this.errorType = VideoType.create(jsonElement2.getAsString());
                    continue;
                }
                case 14: {
                    this.abbrSeqLabel = JsonUtils.getAsStringSafe(jsonElement2);
                    continue;
                }
            }
        }
    }
    
    @Override
    public String toString() {
        return "Detail [id=" + this.id + ", type=" + this.type + ", seasonId=" + this.seasonId + ", showId=" + this.showId + ", showTitle=" + this.showTitle + ", interestingUrl=" + this.interestingUrl + ", interestingSmallUrl=" + this.interestingSmallUrl + ", showRestUrl=" + this.showRestUrl + ", title=" + this.title + ", boxartUrl=" + this.boxartUrl + ", nextEpisodeId=" + this.nextEpisodeId + ", nextEpisodeTitle=" + this.nextEpisodeTitle + ", availabilityDateMsg=" + this.availabilityDateMsg + ", episodeNumber=" + this.episodeNumber + ", seasonNumber=" + this.seasonNumber + ", errorType=" + this.errorType + ", enumType=" + this.enumType + "]";
    }
}
