// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

import com.netflix.mediaclient.servicemgr.model.VideoType;
import java.util.List;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableListSummary;

public abstract class Video
{
    public TrackableListSummary similarListSummary;
    public List<com.netflix.mediaclient.servicemgr.model.Video> similarVideos;
    
    public static boolean isSocialVideoType(final VideoType videoType) {
        return VideoType.SOCIAL_FRIEND.equals(videoType) || VideoType.SOCIAL_GROUP.equals(videoType) || VideoType.SOCIAL_POPULAR.equals(videoType);
    }
    
    public static class Bookmark
    {
        private int bookmarkPosition;
        private long lastModified;
        
        public void deepCopy(final Bookmark bookmark) {
            this.bookmarkPosition = bookmark.bookmarkPosition;
            this.lastModified = bookmark.lastModified;
        }
        
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
        public String intrUrl;
        public boolean isAutoPlayEnabled;
        public boolean isHdAvailable;
        public boolean isNextPlayableEpisode;
        public boolean isPinProtected;
        public String mdxHorzUrl;
        public String mdxVertUrl;
        public float predictedRating;
        public String quality;
        public String restUrl;
        public int runtime;
        public int seasonCount;
        public String storyImgDispUrl;
        public String storyImgUrl;
        public String synopsis;
        public String synopsisNarrative;
        public String tvCardUrl;
        public int year;
        
        public void deepCopy(final Detail detail) {
            this.year = detail.year;
            this.synopsis = detail.synopsis;
            this.synopsisNarrative = detail.synopsisNarrative;
            this.quality = detail.quality;
            this.directors = detail.directors;
            this.actors = detail.actors;
            this.genres = detail.genres;
            this.certification = detail.certification;
            this.horzDispUrl = detail.horzDispUrl;
            this.restUrl = detail.restUrl;
            this.bifUrl = detail.bifUrl;
            this.baseUrl = detail.baseUrl;
            this.tvCardUrl = detail.tvCardUrl;
            this.mdxHorzUrl = detail.mdxHorzUrl;
            this.mdxVertUrl = detail.mdxVertUrl;
            this.storyImgUrl = detail.storyImgUrl;
            this.storyImgDispUrl = detail.storyImgDispUrl;
            this.intrUrl = detail.intrUrl;
            this.episodeCount = detail.episodeCount;
            this.seasonCount = detail.seasonCount;
            this.isHdAvailable = detail.isHdAvailable;
            this.isAutoPlayEnabled = detail.isAutoPlayEnabled;
            this.isNextPlayableEpisode = detail.isNextPlayableEpisode;
            this.predictedRating = detail.predictedRating;
            this.isPinProtected = detail.isPinProtected;
        }
        
        @Override
        public String toString() {
            return "Detail [year=" + this.year + ", synopsis=" + this.synopsis + ", synopsisNarrative=" + this.synopsisNarrative + ", quality=" + this.quality + ", directors=" + this.directors + ", actors=" + this.actors + ", genres=" + this.genres + ", certification=" + this.certification + ", horzDispUrl=" + this.horzDispUrl + ", restUrl=" + this.restUrl + ", bifUrl=" + this.bifUrl + ", baseUrl=" + this.baseUrl + ", tvCardUrl=" + this.tvCardUrl + ", mdxHorzUrl=" + this.mdxHorzUrl + ", mdxVertUrl=" + this.mdxVertUrl + ", storyImgUrl=" + this.storyImgUrl + ", storyImgDispUrl=" + this.storyImgDispUrl + ", intrUrl=" + this.intrUrl + ", episodeCount=" + this.episodeCount + ", seasonCount=" + this.seasonCount + ", isHdAvailable=" + this.isHdAvailable + ", isAutoPlayEnabled=" + this.isAutoPlayEnabled + ", isNextPlayableEpisode=" + this.isNextPlayableEpisode + ", predictedRating=" + this.predictedRating + ", isPinProtected=" + this.isPinProtected + ", runtime=" + this.runtime + ", endtime=" + this.endtime + "]";
        }
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
        public String certification;
        public String horzDispUrl;
        public int releaseYear;
        public String title;
        
        @Override
        public String toString() {
            return this.title;
        }
    }
    
    public static class Summary implements Video
    {
        public String boxartUrl;
        public VideoType enumType;
        public VideoType errorType;
        public String horzDispUrl;
        public String id;
        public boolean isEpisode;
        public String squareUrl;
        public String title;
        public String tvCardUrl;
        public String type;
        public int videoYear;
        
        @Override
        public String getBoxshotURL() {
            return this.boxartUrl;
        }
        
        @Override
        public VideoType getErrorType() {
            return this.errorType;
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
        public String getSquareUrl() {
            return this.squareUrl;
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
        
        public void setErrorType(final VideoType errorType) {
            this.errorType = errorType;
        }
        
        @Override
        public String toString() {
            return "Summary [id=" + this.id + ", boxartUrl=" + this.boxartUrl + ", type=" + this.type + ", enumType=" + this.enumType + ", title=" + this.title + ", isEpisode=" + this.isEpisode + ", errorType=" + this.errorType + ", tvCardUrl=" + this.tvCardUrl + ", horzDispUrl=" + this.horzDispUrl + ", squareUrl=" + this.squareUrl + ", videoYear=" + this.videoYear + "]";
        }
    }
}
