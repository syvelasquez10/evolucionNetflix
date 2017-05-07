// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

public class Episode extends Video
{
    public Bookmark bookmark;
    public BookmarkStill bookmarkStill;
    public Detail detail;
    public Summary summary;
    
    public static class Detail extends Video.Detail
    {
        private int episodeNumber;
        private String id;
        private String intrUrl;
        private String nextEpisodeId;
        private String nextEpisodeTitle;
        private String seasonId;
        private int seasonNumber;
        private String showId;
        private String showRestUrl;
        private String showTitle;
        private String title;
        public String vertArtUrl;
        
        public String getBaseUrl() {
            return this.baseUrl;
        }
        
        public String getBoxshotURL() {
            return this.vertArtUrl;
        }
        
        public int getEpisodeNumber() {
            return this.episodeNumber;
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
        
        public boolean isAutoPlayEnabled() {
            return this.isAutoPlayEnabled;
        }
        
        public boolean isNextPlayableEpisode() {
            return this.isNextPlayableEpisode;
        }
        
        public Detail setId(final String id) {
            this.id = id;
            return this;
        }
        
        @Override
        public String toString() {
            return "Detail [id=" + this.id + ", episodeNumber=" + this.episodeNumber + ", seasonNumber=" + this.seasonNumber + ", seasonId=" + this.seasonId + ", showId=" + this.showId + ", showTitle=" + this.showTitle + ", showRestUrl=" + this.showRestUrl + ", title=" + this.title + ", vertArtUrl=" + this.vertArtUrl + ", nextEpisodeId=" + this.nextEpisodeId + ", nextEpisodeTitle=" + this.nextEpisodeTitle + ", intrUrl=" + this.intrUrl + "]";
        }
    }
}
