// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline.realm;

import java.util.Iterator;
import com.netflix.mediaclient.Log;
import java.io.File;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineImageUtils;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.model.leafs.advisory.Advisory;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import io.realm.Realm$Transaction;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.service.NetflixService;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmVideoDetailsRealmProxyInterface;
import io.realm.RealmModel;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;

public class RealmVideoDetails implements VideoDetails, RealmModel, RealmVideoDetailsRealmProxyInterface
{
    private String actors;
    private String bifUrl;
    private String boxartImageId;
    private String boxshotUrl;
    private String catalogIdUrl;
    private String cert;
    private String copyright;
    private String defaultTrailer;
    private int errorType;
    private String genres;
    private String hResLandBoxArtUrl;
    private String hResPortBoxArtUrl;
    private boolean hasTrailers;
    private boolean hasWatched;
    private String horzDispSmallUrl;
    private String horzDispUrl;
    private String id;
    private boolean isInQueue;
    private boolean isOriginal;
    private boolean isPreRelease;
    private boolean isVideo3D;
    private boolean isVideo5dot1;
    private boolean isVideoDolbyVision;
    private boolean isVideoHd;
    private boolean isVideoHdr10;
    private boolean isVideoUhd;
    private int maturityLevel;
    private RealmPlayable playable;
    private float predictedRating;
    private String profileId;
    private String quality;
    private RealmList<RealmSeason> seasonLabels;
    private int seasonNumber;
    private String storyDispUrl;
    private String storyUrl;
    private String supplMessage;
    private String synopsis;
    private String title;
    private String titleCroppedImgUrl;
    private String titleImgUrl;
    private String tvCardUrl;
    private float userRating;
    private int videoType;
    private int year;
    
    public static void insertInRealm(final Realm realm, final NetflixService netflixService, final VideoDetails videoDetails, final String s) {
        insertInRealm(realm, netflixService, videoDetails, null, s);
    }
    
    public static void insertInRealm(final Realm realm, final NetflixService netflixService, final VideoDetails videoDetails, final List<SeasonDetails> list, final String s) {
        if (RealmUtils.idNotExists(realm, RealmVideoDetails.class, videoDetails.getId())) {
            RealmUtils.executeTransaction(realm, (Realm$Transaction)new RealmVideoDetails$1(videoDetails, s, list, netflixService));
        }
    }
    
    private void setPlayable(final RealmPlayable realmPlayable) {
        this.realmSet$playable(realmPlayable);
    }
    
    public void fillForRealm(final VideoDetails videoDetails) {
        this.realmSet$year(videoDetails.getYear());
        this.realmSet$maturityLevel(videoDetails.getMaturityLevel());
        this.realmSet$synopsis(videoDetails.getSynopsis());
        this.realmSet$quality(videoDetails.getQuality());
        this.realmSet$actors(videoDetails.getActors());
        this.realmSet$genres(videoDetails.getGenres());
        this.realmSet$cert(videoDetails.getCertification());
        this.realmSet$supplMessage(videoDetails.getSupplementalMessage());
        this.realmSet$defaultTrailer(videoDetails.getDefaultTrailer());
        this.realmSet$copyright(videoDetails.getCopyright());
        this.realmSet$hResPortBoxArtUrl(videoDetails.getHighResolutionPortraitBoxArtUrl());
        this.realmSet$hResLandBoxArtUrl(videoDetails.getHighResolutionLandscapeBoxArtUrl());
        this.realmSet$boxshotUrl(videoDetails.getBoxshotUrl());
        this.realmSet$boxartImageId(videoDetails.getBoxartImageTypeIdentifier());
        if (videoDetails instanceof EpisodeDetails) {
            this.realmSet$horzDispUrl(((EpisodeDetails)videoDetails).getInterestingUrl());
        }
        else {
            this.realmSet$horzDispUrl(videoDetails.getHorzDispUrl());
        }
        this.realmSet$horzDispSmallUrl(videoDetails.getHorzDispSmallUrl());
        this.realmSet$storyDispUrl(videoDetails.getStoryDispUrl());
        this.realmSet$tvCardUrl(videoDetails.getTvCardUrl());
        this.realmSet$storyUrl(videoDetails.getStoryUrl());
        this.realmSet$bifUrl(videoDetails.getBifUrl());
        this.realmSet$catalogIdUrl(videoDetails.getCatalogIdUrl());
        this.realmSet$titleImgUrl(videoDetails.getTitleImgUrl());
        this.realmSet$titleCroppedImgUrl(videoDetails.getTitleCroppedImgUrl());
        this.realmSet$title(videoDetails.getTitle());
        this.realmSet$isOriginal(videoDetails.isOriginal());
        this.realmSet$isPreRelease(videoDetails.isPreRelease());
        this.realmSet$hasWatched(videoDetails.hasWatched());
        this.realmSet$hasTrailers(videoDetails.hasTrailers());
        this.realmSet$isInQueue(videoDetails.isInQueue());
        this.realmSet$isVideoHd(videoDetails.isVideoHd());
        this.realmSet$isVideoUhd(videoDetails.isVideoUhd());
        this.realmSet$isVideo3D(videoDetails.isVideo3D());
        this.realmSet$isVideo5dot1(videoDetails.isVideo5dot1());
        this.realmSet$isVideoHdr10(videoDetails.isVideoHdr10());
        this.realmSet$isVideoDolbyVision(videoDetails.isVideoDolbyVision());
        this.realmSet$userRating(videoDetails.getUserRating());
        this.realmSet$predictedRating(videoDetails.getPredictedRating());
        if (videoDetails.getErrorType() != null) {
            this.realmSet$errorType(videoDetails.getErrorType().getKey());
        }
        this.realmSet$seasonNumber(videoDetails.getPlayable().getSeasonNumber());
        this.realmSet$videoType(videoDetails.getType().getKey());
    }
    
    @Override
    public String getActors() {
        return this.realmGet$actors();
    }
    
    @Override
    public List<Advisory> getAdvisories() {
        return this.realmGet$playable().getAdvisories();
    }
    
    @Override
    public String getBifUrl() {
        return this.realmGet$bifUrl();
    }
    
    public String getBoxartImageTypeIdentifier() {
        return this.realmGet$boxartImageId();
    }
    
    public String getBoxshotUrl() {
        return this.realmGet$boxshotUrl();
    }
    
    @Override
    public String getCatalogIdUrl() {
        return this.realmGet$catalogIdUrl();
    }
    
    @Override
    public String getCertification() {
        return this.realmGet$cert();
    }
    
    @Override
    public String getCopyright() {
        return this.realmGet$copyright();
    }
    
    @Override
    public String getDefaultTrailer() {
        return this.realmGet$defaultTrailer();
    }
    
    public VideoType getErrorType() {
        return VideoType.values()[this.realmGet$errorType()];
    }
    
    @Override
    public long getExpirationTime() {
        return this.realmGet$playable().getExpirationTime();
    }
    
    @Override
    public String getGenres() {
        return this.realmGet$genres();
    }
    
    @Override
    public String getHighResolutionLandscapeBoxArtUrl() {
        return this.realmGet$hResLandBoxArtUrl();
    }
    
    @Override
    public String getHighResolutionPortraitBoxArtUrl() {
        return this.realmGet$hResPortBoxArtUrl();
    }
    
    public String getHorzDispSmallUrl() {
        return this.realmGet$horzDispSmallUrl();
    }
    
    public String getHorzDispUrl() {
        return this.realmGet$horzDispUrl();
    }
    
    public String getId() {
        return this.realmGet$id();
    }
    
    @Override
    public int getMaturityLevel() {
        return this.realmGet$maturityLevel();
    }
    
    @Override
    public Playable getPlayable() {
        return this.realmGet$playable();
    }
    
    public float getPredictedRating() {
        return this.realmGet$predictedRating();
    }
    
    public String getProfileId() {
        return this.realmGet$profileId();
    }
    
    @Override
    public String getQuality() {
        return this.realmGet$quality();
    }
    
    public String getRealmHorzDispUrl(final Context context) {
        final String localFileForVideoDetailsImage = OfflineImageUtils.getLocalFileForVideoDetailsImage(context, this.getId());
        final boolean exists = new File(localFileForVideoDetailsImage).exists();
        if (Log.isLoggable()) {
            Log.i("RealmUtils", "getRealmHorzDispUrl filePath=" + localFileForVideoDetailsImage + " exists=" + exists);
        }
        if (exists) {
            return "file://" + localFileForVideoDetailsImage;
        }
        return this.realmGet$horzDispUrl();
    }
    
    public String getSeasonLongLabel(final int n) {
        for (final RealmSeason realmSeason : this.realmGet$seasonLabels()) {
            if (realmSeason.getNumber() == n) {
                return realmSeason.getLabel();
            }
        }
        return null;
    }
    
    public String getStoryDispUrl() {
        return this.realmGet$storyDispUrl();
    }
    
    @Override
    public String getStoryUrl() {
        return this.realmGet$storyUrl();
    }
    
    @Override
    public String getSupplementalMessage() {
        return this.realmGet$supplMessage();
    }
    
    @Override
    public String getSynopsis() {
        return this.realmGet$synopsis();
    }
    
    public String getTitle() {
        return this.realmGet$title();
    }
    
    @Override
    public String getTitleCroppedImgUrl() {
        return this.realmGet$titleCroppedImgUrl();
    }
    
    @Override
    public String getTitleImgUrl() {
        return this.realmGet$titleImgUrl();
    }
    
    @Override
    public String getTvCardUrl() {
        return this.realmGet$tvCardUrl();
    }
    
    public VideoType getType() {
        return VideoType.create(this.realmGet$videoType());
    }
    
    public float getUserRating() {
        return this.realmGet$userRating();
    }
    
    @Override
    public int getYear() {
        return this.realmGet$year();
    }
    
    @Override
    public boolean hasTrailers() {
        return this.realmGet$hasTrailers();
    }
    
    @Override
    public boolean hasWatched() {
        return this.realmGet$hasWatched();
    }
    
    @Override
    public boolean isAvailableToStream() {
        return this.realmGet$playable().isAvailableToStream();
    }
    
    @Override
    public boolean isInQueue() {
        return this.realmGet$isInQueue();
    }
    
    @Override
    public boolean isNSRE() {
        return this.realmGet$playable().isNSRE();
    }
    
    @Override
    public boolean isOriginal() {
        return this.realmGet$isOriginal();
    }
    
    public boolean isPreRelease() {
        return this.realmGet$isPreRelease();
    }
    
    @Override
    public boolean isSupplementalVideo() {
        return false;
    }
    
    public boolean isVideo3D() {
        return this.realmGet$isVideo3D();
    }
    
    public boolean isVideo5dot1() {
        return this.realmGet$isVideo5dot1();
    }
    
    public boolean isVideoDolbyVision() {
        return this.realmGet$isVideoDolbyVision();
    }
    
    public boolean isVideoHd() {
        return this.realmGet$isVideoHd();
    }
    
    public boolean isVideoHdr10() {
        return this.realmGet$isVideoHdr10();
    }
    
    public boolean isVideoUhd() {
        return this.realmGet$isVideoUhd();
    }
    
    public String realmGet$actors() {
        return this.actors;
    }
    
    public String realmGet$bifUrl() {
        return this.bifUrl;
    }
    
    public String realmGet$boxartImageId() {
        return this.boxartImageId;
    }
    
    public String realmGet$boxshotUrl() {
        return this.boxshotUrl;
    }
    
    public String realmGet$catalogIdUrl() {
        return this.catalogIdUrl;
    }
    
    public String realmGet$cert() {
        return this.cert;
    }
    
    public String realmGet$copyright() {
        return this.copyright;
    }
    
    public String realmGet$defaultTrailer() {
        return this.defaultTrailer;
    }
    
    public int realmGet$errorType() {
        return this.errorType;
    }
    
    public String realmGet$genres() {
        return this.genres;
    }
    
    public String realmGet$hResLandBoxArtUrl() {
        return this.hResLandBoxArtUrl;
    }
    
    public String realmGet$hResPortBoxArtUrl() {
        return this.hResPortBoxArtUrl;
    }
    
    public boolean realmGet$hasTrailers() {
        return this.hasTrailers;
    }
    
    public boolean realmGet$hasWatched() {
        return this.hasWatched;
    }
    
    public String realmGet$horzDispSmallUrl() {
        return this.horzDispSmallUrl;
    }
    
    public String realmGet$horzDispUrl() {
        return this.horzDispUrl;
    }
    
    public String realmGet$id() {
        return this.id;
    }
    
    public boolean realmGet$isInQueue() {
        return this.isInQueue;
    }
    
    public boolean realmGet$isOriginal() {
        return this.isOriginal;
    }
    
    public boolean realmGet$isPreRelease() {
        return this.isPreRelease;
    }
    
    public boolean realmGet$isVideo3D() {
        return this.isVideo3D;
    }
    
    public boolean realmGet$isVideo5dot1() {
        return this.isVideo5dot1;
    }
    
    public boolean realmGet$isVideoDolbyVision() {
        return this.isVideoDolbyVision;
    }
    
    public boolean realmGet$isVideoHd() {
        return this.isVideoHd;
    }
    
    public boolean realmGet$isVideoHdr10() {
        return this.isVideoHdr10;
    }
    
    public boolean realmGet$isVideoUhd() {
        return this.isVideoUhd;
    }
    
    public int realmGet$maturityLevel() {
        return this.maturityLevel;
    }
    
    public RealmPlayable realmGet$playable() {
        return this.playable;
    }
    
    public float realmGet$predictedRating() {
        return this.predictedRating;
    }
    
    public String realmGet$profileId() {
        return this.profileId;
    }
    
    public String realmGet$quality() {
        return this.quality;
    }
    
    public RealmList realmGet$seasonLabels() {
        return this.seasonLabels;
    }
    
    public int realmGet$seasonNumber() {
        return this.seasonNumber;
    }
    
    public String realmGet$storyDispUrl() {
        return this.storyDispUrl;
    }
    
    public String realmGet$storyUrl() {
        return this.storyUrl;
    }
    
    public String realmGet$supplMessage() {
        return this.supplMessage;
    }
    
    public String realmGet$synopsis() {
        return this.synopsis;
    }
    
    public String realmGet$title() {
        return this.title;
    }
    
    public String realmGet$titleCroppedImgUrl() {
        return this.titleCroppedImgUrl;
    }
    
    public String realmGet$titleImgUrl() {
        return this.titleImgUrl;
    }
    
    public String realmGet$tvCardUrl() {
        return this.tvCardUrl;
    }
    
    public float realmGet$userRating() {
        return this.userRating;
    }
    
    public int realmGet$videoType() {
        return this.videoType;
    }
    
    public int realmGet$year() {
        return this.year;
    }
    
    public void realmSet$actors(final String actors) {
        this.actors = actors;
    }
    
    public void realmSet$bifUrl(final String bifUrl) {
        this.bifUrl = bifUrl;
    }
    
    public void realmSet$boxartImageId(final String boxartImageId) {
        this.boxartImageId = boxartImageId;
    }
    
    public void realmSet$boxshotUrl(final String boxshotUrl) {
        this.boxshotUrl = boxshotUrl;
    }
    
    public void realmSet$catalogIdUrl(final String catalogIdUrl) {
        this.catalogIdUrl = catalogIdUrl;
    }
    
    public void realmSet$cert(final String cert) {
        this.cert = cert;
    }
    
    public void realmSet$copyright(final String copyright) {
        this.copyright = copyright;
    }
    
    public void realmSet$defaultTrailer(final String defaultTrailer) {
        this.defaultTrailer = defaultTrailer;
    }
    
    public void realmSet$errorType(final int errorType) {
        this.errorType = errorType;
    }
    
    public void realmSet$genres(final String genres) {
        this.genres = genres;
    }
    
    public void realmSet$hResLandBoxArtUrl(final String hResLandBoxArtUrl) {
        this.hResLandBoxArtUrl = hResLandBoxArtUrl;
    }
    
    public void realmSet$hResPortBoxArtUrl(final String hResPortBoxArtUrl) {
        this.hResPortBoxArtUrl = hResPortBoxArtUrl;
    }
    
    public void realmSet$hasTrailers(final boolean hasTrailers) {
        this.hasTrailers = hasTrailers;
    }
    
    public void realmSet$hasWatched(final boolean hasWatched) {
        this.hasWatched = hasWatched;
    }
    
    public void realmSet$horzDispSmallUrl(final String horzDispSmallUrl) {
        this.horzDispSmallUrl = horzDispSmallUrl;
    }
    
    public void realmSet$horzDispUrl(final String horzDispUrl) {
        this.horzDispUrl = horzDispUrl;
    }
    
    public void realmSet$id(final String id) {
        this.id = id;
    }
    
    public void realmSet$isInQueue(final boolean isInQueue) {
        this.isInQueue = isInQueue;
    }
    
    public void realmSet$isOriginal(final boolean isOriginal) {
        this.isOriginal = isOriginal;
    }
    
    public void realmSet$isPreRelease(final boolean isPreRelease) {
        this.isPreRelease = isPreRelease;
    }
    
    public void realmSet$isVideo3D(final boolean isVideo3D) {
        this.isVideo3D = isVideo3D;
    }
    
    public void realmSet$isVideo5dot1(final boolean isVideo5dot1) {
        this.isVideo5dot1 = isVideo5dot1;
    }
    
    public void realmSet$isVideoDolbyVision(final boolean isVideoDolbyVision) {
        this.isVideoDolbyVision = isVideoDolbyVision;
    }
    
    public void realmSet$isVideoHd(final boolean isVideoHd) {
        this.isVideoHd = isVideoHd;
    }
    
    public void realmSet$isVideoHdr10(final boolean isVideoHdr10) {
        this.isVideoHdr10 = isVideoHdr10;
    }
    
    public void realmSet$isVideoUhd(final boolean isVideoUhd) {
        this.isVideoUhd = isVideoUhd;
    }
    
    public void realmSet$maturityLevel(final int maturityLevel) {
        this.maturityLevel = maturityLevel;
    }
    
    public void realmSet$playable(final RealmPlayable playable) {
        this.playable = playable;
    }
    
    public void realmSet$predictedRating(final float predictedRating) {
        this.predictedRating = predictedRating;
    }
    
    public void realmSet$profileId(final String profileId) {
        this.profileId = profileId;
    }
    
    public void realmSet$quality(final String quality) {
        this.quality = quality;
    }
    
    public void realmSet$seasonLabels(final RealmList seasonLabels) {
        this.seasonLabels = (RealmList<RealmSeason>)seasonLabels;
    }
    
    public void realmSet$seasonNumber(final int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }
    
    public void realmSet$storyDispUrl(final String storyDispUrl) {
        this.storyDispUrl = storyDispUrl;
    }
    
    public void realmSet$storyUrl(final String storyUrl) {
        this.storyUrl = storyUrl;
    }
    
    public void realmSet$supplMessage(final String supplMessage) {
        this.supplMessage = supplMessage;
    }
    
    public void realmSet$synopsis(final String synopsis) {
        this.synopsis = synopsis;
    }
    
    public void realmSet$title(final String title) {
        this.title = title;
    }
    
    public void realmSet$titleCroppedImgUrl(final String titleCroppedImgUrl) {
        this.titleCroppedImgUrl = titleCroppedImgUrl;
    }
    
    public void realmSet$titleImgUrl(final String titleImgUrl) {
        this.titleImgUrl = titleImgUrl;
    }
    
    public void realmSet$tvCardUrl(final String tvCardUrl) {
        this.tvCardUrl = tvCardUrl;
    }
    
    public void realmSet$userRating(final float userRating) {
        this.userRating = userRating;
    }
    
    public void realmSet$videoType(final int videoType) {
        this.videoType = videoType;
    }
    
    public void realmSet$year(final int year) {
        this.year = year;
    }
    
    public void setPlayableAndVideoType(final RealmPlayable playable, final VideoType videoType, final String s) {
        this.setPlayable(playable);
        this.realmSet$videoType(videoType.getKey());
        this.realmSet$title(s);
    }
    
    public void setUserRating(final float n) {
        this.realmSet$userRating(n);
    }
    
    @Override
    public String toString() {
        return "RealmVideoDetails{id='" + this.realmGet$id() + '\'' + ", year=" + this.realmGet$year() + ", maturityLevel=" + this.realmGet$maturityLevel() + ", synopsis='" + this.realmGet$synopsis() + '\'' + ", quality='" + this.realmGet$quality() + '\'' + ", actors='" + this.realmGet$actors() + '\'' + ", genres='" + this.realmGet$genres() + '\'' + ", cert='" + this.realmGet$cert() + '\'' + ", supplMessage='" + this.realmGet$supplMessage() + '\'' + ", defaultTrailer='" + this.realmGet$defaultTrailer() + '\'' + ", copyright='" + this.realmGet$copyright() + '\'' + ", hResPortBoxArtUrl='" + this.realmGet$hResPortBoxArtUrl() + '\'' + ", hResLandBoxArtUrl='" + this.realmGet$hResLandBoxArtUrl() + '\'' + ", boxshotUrl='" + this.realmGet$boxshotUrl() + '\'' + ", boxartImageId='" + this.realmGet$boxartImageId() + '\'' + ", horzDispUrl='" + this.realmGet$horzDispUrl() + '\'' + ", horzDispSmallUrl='" + this.realmGet$horzDispSmallUrl() + '\'' + ", storyDispUrl='" + this.realmGet$storyDispUrl() + '\'' + ", tvCardUrl='" + this.realmGet$tvCardUrl() + '\'' + ", storyUrl='" + this.realmGet$storyUrl() + '\'' + ", bifUrl='" + this.realmGet$bifUrl() + '\'' + ", catalogIdUrl='" + this.realmGet$catalogIdUrl() + '\'' + ", titleImgUrl='" + this.realmGet$titleImgUrl() + '\'' + ", titleCroppedImgUrl='" + this.realmGet$titleCroppedImgUrl() + '\'' + ", title='" + this.realmGet$title() + '\'' + ", isOriginal=" + this.realmGet$isOriginal() + ", isPreRelease=" + this.realmGet$isPreRelease() + ", hasWatched=" + this.realmGet$hasWatched() + ", hasTrailers=" + this.realmGet$hasTrailers() + ", isInQueue=" + this.realmGet$isInQueue() + ", isVideoHd=" + this.realmGet$isVideoHd() + ", isVideoUhd=" + this.realmGet$isVideoUhd() + ", isVideo3D=" + this.realmGet$isVideo3D() + ", isVideo5dot1=" + this.realmGet$isVideo5dot1() + ", isVideoHdr10=" + this.realmGet$isVideoHdr10() + ", isVideoDolbyVision=" + this.realmGet$isVideoDolbyVision() + ", userRating=" + this.realmGet$userRating() + ", predictedRating=" + this.realmGet$predictedRating() + ", playable=" + this.realmGet$playable() + ", errorType=" + this.realmGet$errorType() + ", videoType=" + this.realmGet$videoType() + '}';
    }
}
