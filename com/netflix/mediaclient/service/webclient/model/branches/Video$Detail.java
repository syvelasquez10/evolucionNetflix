// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.branches;

public class Video$Detail
{
    public String actors;
    public String baseUrl;
    public String bifUrl;
    public String certification;
    public String directors;
    public int endtime;
    public int episodeCount;
    public String genres;
    public String hiResHorzUrl;
    public String horzDispUrl;
    public String intrUrl;
    public boolean isAutoPlayEnabled;
    public boolean isHdAvailable;
    public boolean isNextPlayableEpisode;
    public boolean isPinProtected;
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
    public String titleUrl;
    public String tvCardUrl;
    public int year;
    
    public void deepCopy(final Video$Detail video$Detail) {
        this.year = video$Detail.year;
        this.synopsis = video$Detail.synopsis;
        this.synopsisNarrative = video$Detail.synopsisNarrative;
        this.quality = video$Detail.quality;
        this.directors = video$Detail.directors;
        this.actors = video$Detail.actors;
        this.genres = video$Detail.genres;
        this.certification = video$Detail.certification;
        this.horzDispUrl = video$Detail.horzDispUrl;
        this.restUrl = video$Detail.restUrl;
        this.bifUrl = video$Detail.bifUrl;
        this.baseUrl = video$Detail.baseUrl;
        this.tvCardUrl = video$Detail.tvCardUrl;
        this.hiResHorzUrl = video$Detail.hiResHorzUrl;
        this.mdxVertUrl = video$Detail.mdxVertUrl;
        this.storyImgUrl = video$Detail.storyImgUrl;
        this.storyImgDispUrl = video$Detail.storyImgDispUrl;
        this.intrUrl = video$Detail.intrUrl;
        this.episodeCount = video$Detail.episodeCount;
        this.seasonCount = video$Detail.seasonCount;
        this.isHdAvailable = video$Detail.isHdAvailable;
        this.isAutoPlayEnabled = video$Detail.isAutoPlayEnabled;
        this.isNextPlayableEpisode = video$Detail.isNextPlayableEpisode;
        this.predictedRating = video$Detail.predictedRating;
        this.isPinProtected = video$Detail.isPinProtected;
    }
    
    @Override
    public String toString() {
        return "Detail [year=" + this.year + ", synopsis=" + this.synopsis + ", synopsisNarrative=" + this.synopsisNarrative + ", quality=" + this.quality + ", directors=" + this.directors + ", actors=" + this.actors + ", genres=" + this.genres + ", certification=" + this.certification + ", horzDispUrl=" + this.horzDispUrl + ", restUrl=" + this.restUrl + ", bifUrl=" + this.bifUrl + ", baseUrl=" + this.baseUrl + ", tvCardUrl=" + this.tvCardUrl + ", hiResHorzUrl=" + this.hiResHorzUrl + ", mdxVertUrl=" + this.mdxVertUrl + ", storyImgUrl=" + this.storyImgUrl + ", storyImgDispUrl=" + this.storyImgDispUrl + ", intrUrl=" + this.intrUrl + ", episodeCount=" + this.episodeCount + ", seasonCount=" + this.seasonCount + ", isHdAvailable=" + this.isHdAvailable + ", isAutoPlayEnabled=" + this.isAutoPlayEnabled + ", isNextPlayableEpisode=" + this.isNextPlayableEpisode + ", predictedRating=" + this.predictedRating + ", isPinProtected=" + this.isPinProtected + ", runtime=" + this.runtime + ", endtime=" + this.endtime + "]";
    }
}
