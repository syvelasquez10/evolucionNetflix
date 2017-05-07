// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.VideoType;

public class Video
{
    public static boolean isSocialVideoType(final VideoType videoType) {
        return VideoType.SOCIAL_FRIEND.equals(videoType) || VideoType.SOCIAL_GROUP.equals(videoType) || VideoType.SOCIAL_POPULAR.equals(videoType);
    }
    
    public static class Bookmark
    {
        private int bookmarkPosition;
        private long lastModified;
        
        public int getBookmarkPosition() {
            return this.bookmarkPosition;
        }
        
        public long getLastModified() {
            return this.lastModified;
        }
        
        public void setBookmarkPosition(final int bookmarkPosition) {
            this.bookmarkPosition = bookmarkPosition;
        }
        
        public void setLastModified(final long lastModified) {
            this.lastModified = lastModified;
        }
        
        @Override
        public String toString() {
            return "Bookmark [bookmarkPosition=" + this.bookmarkPosition + ", lastModified=" + this.lastModified + "]";
        }
    }
    
    public static class BookmarkStill
    {
        public String stillType;
        public String stillUrl;
    }
    
    public static class Detail
    {
        public String actors;
        public String baseUrl;
        public String bifUrl;
        public String certification;
        public String directors;
        public int endtime;
        public int episodeCount;
        public String genres;
        public String horzDispUrl;
        public boolean isHdAvailable;
        public String mdxHorzUrl;
        public String mdxVertUrl;
        public float predictedRating;
        public String quality;
        public String restUrl;
        public int runtime;
        public int seasonCount;
        public String storyImgUrl;
        public String synopsis;
        public String tvCardUrl;
        public int year;
    }
    
    public static class InQueue
    {
        public boolean inQueue;
        
        @Override
        public String toString() {
            return "InQueue [inQueue=" + this.inQueue + "]";
        }
    }
    
    public static class Rating
    {
        public float userRating;
        
        @Override
        public String toString() {
            return "Rating [userRating=" + this.userRating + "]";
        }
    }
    
    public static class SearchTitle
    {
        public int releaseYear;
        public String title;
        
        @Override
        public String toString() {
            return this.title;
        }
    }
    
    public static class Summary implements Video
    {
        protected String boxart;
        protected VideoType enumType;
        public VideoType errorType;
        protected String id;
        protected boolean isEpisode;
        protected String title;
        protected String type;
        protected String vertArtUrl;
        
        @Override
        public String getBoxshotURL() {
            if (StringUtils.isEmpty(this.vertArtUrl)) {
                return this.boxart;
            }
            return this.vertArtUrl;
        }
        
        @Override
        public VideoType getErrorType() {
            return this.errorType;
        }
        
        @Override
        public String getId() {
            return this.id;
        }
        
        @Override
        public String getTitle() {
            return this.title;
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
        
        public void setErrorType(final VideoType errorType) {
            this.errorType = errorType;
        }
        
        @Override
        public String toString() {
            return "Summary [id=" + this.id + ", type=" + this.type + ", title=" + this.title + ", isEpisode=" + this.isEpisode + "]";
        }
    }
}
