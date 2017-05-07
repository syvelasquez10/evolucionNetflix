// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

import com.netflix.mediaclient.servicemgr.model.VideoType;

public class Episode
{
    public Video.Bookmark bookmark;
    public Detail detail;
    
    public static class Detail extends Video.Detail
    {
        public String boxartUrl;
        private VideoType enumType;
        private int episodeNumber;
        public VideoType errorType;
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
        
        public void deepCopy(final Detail detail) {
            super.deepCopy((Video.Detail)detail);
            this.id = detail.id;
            this.episodeNumber = detail.episodeNumber;
            this.seasonNumber = detail.seasonNumber;
            this.seasonId = detail.seasonId;
            this.showId = detail.showId;
            this.showTitle = detail.showTitle;
            this.showRestUrl = detail.showRestUrl;
            this.title = detail.title;
            this.boxartUrl = detail.boxartUrl;
            this.nextEpisodeId = detail.nextEpisodeId;
            this.nextEpisodeTitle = detail.nextEpisodeTitle;
        }
        
        public String getBaseUrl() {
            return this.baseUrl;
        }
        
        public String getBoxshotURL() {
            return this.boxartUrl;
        }
        
        public int getEpisodeNumber() {
            return this.episodeNumber;
        }
        
        public VideoType getErrorType() {
            return this.errorType;
        }
        
        public String getId() {
            return this.id;
        }
        
        public String getInterestingUrl() {
            return this.intrUrl;
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
        
        public String getTitle() {
            return this.title;
        }
        
        public VideoType getType() {
            if (this.enumType == null) {
                this.enumType = VideoType.create(this.type);
            }
            return this.enumType;
        }
        
        public boolean isAutoPlayEnabled() {
            return this.isAutoPlayEnabled;
        }
        
        public boolean isNextPlayableEpisode() {
            return this.isNextPlayableEpisode;
        }
        
        @Override
        public String toString() {
            return "Detail [super=" + super.toString() + ", id=" + this.id + ", seasonNumber=" + this.seasonNumber + ", episodeNumber=" + this.episodeNumber + ", showTitle=" + this.showTitle + ", title=" + this.title + ", nextEpisodeId=" + this.nextEpisodeId + ", nextEpisodeTitle=" + this.nextEpisodeTitle + "]";
        }
    }
}
