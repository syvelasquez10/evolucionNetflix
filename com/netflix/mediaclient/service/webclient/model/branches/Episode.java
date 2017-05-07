// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

import java.util.HashSet;
import java.util.Set;

public class Episode extends Video
{
    public Bookmark bookmark;
    public Detail detail;
    public Summary summary;
    
    @Override
    public Object get(final String s) {
        if ("summary".equals(s)) {
            return this.summary;
        }
        if ("detail".equals(s)) {
            return this.detail;
        }
        if ("bookmark".equals(s)) {
            return this.bookmark;
        }
        return null;
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.summary != null) {
            set.add("summary");
        }
        if (this.detail != null) {
            set.add("detail");
        }
        if (this.bookmark != null) {
            set.add("bookmark");
        }
        return set;
    }
    
    @Override
    public Object getOrCreate(final String s) {
        final Object value = this.get(s);
        if (value != null) {
            return value;
        }
        if ("summary".equals(s)) {
            return this.summary = new Summary();
        }
        if ("detail".equals(s)) {
            return this.detail = new Detail();
        }
        if ("bookmark".equals(s)) {
            return this.bookmark = new Bookmark();
        }
        return null;
    }
    
    @Override
    public void set(final String s, final Object o) {
        if ("summary".equals(s)) {
            this.summary = (Summary)o;
        }
        else {
            if ("detail".equals(s)) {
                this.detail = (Detail)o;
                return;
            }
            if ("bookmark".equals(s)) {
                this.bookmark = (Bookmark)o;
            }
        }
    }
    
    public static class Detail extends Video.Detail
    {
        public String boxartUrl;
        private int episodeNumber;
        private String id;
        private String nextEpisodeId;
        private String nextEpisodeTitle;
        private String seasonId;
        private int seasonNumber;
        private String showId;
        private String showRestUrl;
        private String showTitle;
        private String title;
        
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
        
        @Override
        public String toString() {
            return "Detail [super=" + super.toString() + ", id=" + this.id + ", seasonNumber=" + this.seasonNumber + ", episodeNumber=" + this.episodeNumber + ", showTitle=" + this.showTitle + ", title=" + this.title + ", nextEpisodeId=" + this.nextEpisodeId + ", nextEpisodeTitle=" + this.nextEpisodeTitle + "]";
        }
    }
}
