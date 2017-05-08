// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline.realm;

import com.google.gson.JsonParser;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.model.leafs.advisory.Advisory;
import java.util.List;
import com.google.gson.Gson;
import io.realm.internal.RealmObjectProxy;
import io.realm.RealmPlayableRealmProxyInterface;
import io.realm.RealmModel;
import com.netflix.mediaclient.servicemgr.interface_.Playable;

public class RealmPlayable implements Playable, RealmModel, RealmPlayableRealmProxyInterface
{
    private static final String TAG = "RealmPlayable";
    private String advisoriesString;
    protected int bookmark;
    private int duration;
    private int endtime;
    private int episodeNumber;
    private long expTime;
    private boolean isAdvisoryDisabled;
    private boolean isAgeProtected;
    private boolean isAutoPlay;
    private boolean isAvailableToStream;
    private boolean isEpisode;
    private boolean isExemptFromLimit;
    private boolean isNSRE;
    private boolean isNextPlayableEpisode;
    private boolean isPinProtected;
    private boolean isPreviewProtected;
    private boolean isSupplementalVideo;
    private int logicalStart;
    private int maxAutoplay;
    private String parentId;
    private String parentTitle;
    private String playableId;
    private String seasonLabel;
    private int seasonNumber;
    private String title;
    private long watchedTime;
    
    public RealmPlayable() {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy)this).realm$injectObjectContext();
        }
    }
    
    public RealmPlayable(final Playable playable) {
        if (this instanceof RealmObjectProxy) {
            ((RealmObjectProxy)this).realm$injectObjectContext();
        }
        this.realmSet$playableId(playable.getPlayableId());
        this.realmSet$parentId(playable.getTopLevelId());
        this.realmSet$isNSRE(playable.isNSRE());
        this.realmSet$isEpisode(playable.isPlayableEpisode());
        this.realmSet$title(playable.getPlayableTitle());
        this.realmSet$parentTitle(playable.getParentTitle());
        this.realmSet$watchedTime(playable.getPlayableBookmarkUpdateTime());
        this.realmSet$bookmark(playable.getPlayableBookmarkPosition());
        this.realmSet$seasonNumber(playable.getSeasonNumber());
        this.realmSet$episodeNumber(playable.getEpisodeNumber());
        this.realmSet$duration(playable.getRuntime());
        this.realmSet$endtime(playable.getEndtime());
        this.realmSet$logicalStart(playable.getLogicalStart());
        this.realmSet$isAutoPlay(playable.isAutoPlayEnabled());
        this.realmSet$isNextPlayableEpisode(playable.isNextPlayableEpisode());
        this.realmSet$isAgeProtected(playable.isAgeProtected());
        this.realmSet$isPinProtected(playable.isPinProtected());
        this.realmSet$isPreviewProtected(playable.isPreviewProtected());
        this.realmSet$expTime(playable.getExpirationTime());
        this.realmSet$isAdvisoryDisabled(playable.isAdvisoryDisabled());
        this.realmSet$seasonLabel(playable.getSeasonAbbrSeqLabel());
        this.realmSet$maxAutoplay(playable.getAutoPlayMaxCount());
        this.realmSet$isExemptFromLimit(playable.isExemptFromInterrupterLimit());
        this.realmSet$isAvailableToStream(playable.isAvailableToStream());
        this.realmSet$isSupplementalVideo(playable.isSupplementalVideo());
        if (playable.getAdvisories() != null) {
            this.realmSet$advisoriesString(new Gson().toJson(playable.getAdvisories()));
        }
    }
    
    @Override
    public List<Advisory> getAdvisories() {
        if (StringUtils.isEmpty(this.realmGet$advisoriesString())) {
            return null;
        }
        return (List<Advisory>)Advisory.asList(new JsonParser().parse(this.realmGet$advisoriesString()).getAsJsonArray());
    }
    
    @Override
    public int getAutoPlayMaxCount() {
        return this.realmGet$maxAutoplay();
    }
    
    @Override
    public int getEndtime() {
        return this.realmGet$endtime();
    }
    
    @Override
    public int getEpisodeNumber() {
        return this.realmGet$episodeNumber();
    }
    
    @Override
    public long getExpirationTime() {
        return this.realmGet$expTime();
    }
    
    @Override
    public int getLogicalStart() {
        return this.realmGet$logicalStart();
    }
    
    @Override
    public String getParentTitle() {
        return this.realmGet$parentTitle();
    }
    
    @Override
    public int getPlayableBookmarkPosition() {
        return this.realmGet$bookmark();
    }
    
    @Override
    public long getPlayableBookmarkUpdateTime() {
        return this.realmGet$watchedTime();
    }
    
    @Override
    public String getPlayableId() {
        return this.realmGet$playableId();
    }
    
    @Override
    public String getPlayableTitle() {
        return this.realmGet$title();
    }
    
    @Override
    public int getRuntime() {
        return this.realmGet$duration();
    }
    
    @Override
    public String getSeasonAbbrSeqLabel() {
        return this.realmGet$seasonLabel();
    }
    
    @Override
    public int getSeasonNumber() {
        return this.realmGet$seasonNumber();
    }
    
    @Override
    public String getTopLevelId() {
        return this.realmGet$parentId();
    }
    
    @Override
    public boolean isAdvisoryDisabled() {
        return this.realmGet$isAdvisoryDisabled();
    }
    
    @Override
    public boolean isAgeProtected() {
        return this.realmGet$isAgeProtected();
    }
    
    @Override
    public boolean isAutoPlayEnabled() {
        return this.realmGet$isAutoPlay();
    }
    
    @Override
    public boolean isAvailableOffline() {
        return true;
    }
    
    @Override
    public boolean isAvailableToStream() {
        return this.realmGet$isAvailableToStream();
    }
    
    @Override
    public boolean isExemptFromInterrupterLimit() {
        return this.realmGet$isExemptFromLimit();
    }
    
    @Override
    public boolean isNSRE() {
        return this.realmGet$isNSRE();
    }
    
    @Override
    public boolean isNextPlayableEpisode() {
        return this.realmGet$isNextPlayableEpisode();
    }
    
    @Override
    public boolean isPinProtected() {
        return this.realmGet$isPinProtected();
    }
    
    @Override
    public boolean isPlayableEpisode() {
        return this.realmGet$isEpisode();
    }
    
    @Override
    public boolean isPreviewProtected() {
        return this.realmGet$isPreviewProtected();
    }
    
    @Override
    public boolean isSupplementalVideo() {
        return this.realmGet$isSupplementalVideo();
    }
    
    public String realmGet$advisoriesString() {
        return this.advisoriesString;
    }
    
    public int realmGet$bookmark() {
        return this.bookmark;
    }
    
    public int realmGet$duration() {
        return this.duration;
    }
    
    public int realmGet$endtime() {
        return this.endtime;
    }
    
    public int realmGet$episodeNumber() {
        return this.episodeNumber;
    }
    
    public long realmGet$expTime() {
        return this.expTime;
    }
    
    public boolean realmGet$isAdvisoryDisabled() {
        return this.isAdvisoryDisabled;
    }
    
    public boolean realmGet$isAgeProtected() {
        return this.isAgeProtected;
    }
    
    public boolean realmGet$isAutoPlay() {
        return this.isAutoPlay;
    }
    
    public boolean realmGet$isAvailableToStream() {
        return this.isAvailableToStream;
    }
    
    public boolean realmGet$isEpisode() {
        return this.isEpisode;
    }
    
    public boolean realmGet$isExemptFromLimit() {
        return this.isExemptFromLimit;
    }
    
    public boolean realmGet$isNSRE() {
        return this.isNSRE;
    }
    
    public boolean realmGet$isNextPlayableEpisode() {
        return this.isNextPlayableEpisode;
    }
    
    public boolean realmGet$isPinProtected() {
        return this.isPinProtected;
    }
    
    public boolean realmGet$isPreviewProtected() {
        return this.isPreviewProtected;
    }
    
    public boolean realmGet$isSupplementalVideo() {
        return this.isSupplementalVideo;
    }
    
    public int realmGet$logicalStart() {
        return this.logicalStart;
    }
    
    public int realmGet$maxAutoplay() {
        return this.maxAutoplay;
    }
    
    public String realmGet$parentId() {
        return this.parentId;
    }
    
    public String realmGet$parentTitle() {
        return this.parentTitle;
    }
    
    public String realmGet$playableId() {
        return this.playableId;
    }
    
    public String realmGet$seasonLabel() {
        return this.seasonLabel;
    }
    
    public int realmGet$seasonNumber() {
        return this.seasonNumber;
    }
    
    public String realmGet$title() {
        return this.title;
    }
    
    public long realmGet$watchedTime() {
        return this.watchedTime;
    }
    
    public void realmSet$advisoriesString(final String advisoriesString) {
        this.advisoriesString = advisoriesString;
    }
    
    public void realmSet$bookmark(final int bookmark) {
        this.bookmark = bookmark;
    }
    
    public void realmSet$duration(final int duration) {
        this.duration = duration;
    }
    
    public void realmSet$endtime(final int endtime) {
        this.endtime = endtime;
    }
    
    public void realmSet$episodeNumber(final int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }
    
    public void realmSet$expTime(final long expTime) {
        this.expTime = expTime;
    }
    
    public void realmSet$isAdvisoryDisabled(final boolean isAdvisoryDisabled) {
        this.isAdvisoryDisabled = isAdvisoryDisabled;
    }
    
    public void realmSet$isAgeProtected(final boolean isAgeProtected) {
        this.isAgeProtected = isAgeProtected;
    }
    
    public void realmSet$isAutoPlay(final boolean isAutoPlay) {
        this.isAutoPlay = isAutoPlay;
    }
    
    public void realmSet$isAvailableToStream(final boolean isAvailableToStream) {
        this.isAvailableToStream = isAvailableToStream;
    }
    
    public void realmSet$isEpisode(final boolean isEpisode) {
        this.isEpisode = isEpisode;
    }
    
    public void realmSet$isExemptFromLimit(final boolean isExemptFromLimit) {
        this.isExemptFromLimit = isExemptFromLimit;
    }
    
    public void realmSet$isNSRE(final boolean isNSRE) {
        this.isNSRE = isNSRE;
    }
    
    public void realmSet$isNextPlayableEpisode(final boolean isNextPlayableEpisode) {
        this.isNextPlayableEpisode = isNextPlayableEpisode;
    }
    
    public void realmSet$isPinProtected(final boolean isPinProtected) {
        this.isPinProtected = isPinProtected;
    }
    
    public void realmSet$isPreviewProtected(final boolean isPreviewProtected) {
        this.isPreviewProtected = isPreviewProtected;
    }
    
    public void realmSet$isSupplementalVideo(final boolean isSupplementalVideo) {
        this.isSupplementalVideo = isSupplementalVideo;
    }
    
    public void realmSet$logicalStart(final int logicalStart) {
        this.logicalStart = logicalStart;
    }
    
    public void realmSet$maxAutoplay(final int maxAutoplay) {
        this.maxAutoplay = maxAutoplay;
    }
    
    public void realmSet$parentId(final String parentId) {
        this.parentId = parentId;
    }
    
    public void realmSet$parentTitle(final String parentTitle) {
        this.parentTitle = parentTitle;
    }
    
    public void realmSet$playableId(final String playableId) {
        this.playableId = playableId;
    }
    
    public void realmSet$seasonLabel(final String seasonLabel) {
        this.seasonLabel = seasonLabel;
    }
    
    public void realmSet$seasonNumber(final int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }
    
    public void realmSet$title(final String title) {
        this.title = title;
    }
    
    public void realmSet$watchedTime(final long watchedTime) {
        this.watchedTime = watchedTime;
    }
    
    @Override
    public String toString() {
        return "RealmPlayable{playableId='" + this.realmGet$playableId() + '\'' + ", parentId='" + this.realmGet$parentId() + '\'' + ", title='" + this.realmGet$title() + '\'' + ", seasonLabel='" + this.realmGet$seasonLabel() + '\'' + ", parentTitle='" + this.realmGet$parentTitle() + '\'' + ", isEpisode=" + this.realmGet$isEpisode() + ", isNSRE=" + this.realmGet$isNSRE() + ", isAutoPlay=" + this.realmGet$isAutoPlay() + ", isExemptFromLimit=" + this.realmGet$isExemptFromLimit() + ", isNextPlayableEpisode=" + this.realmGet$isNextPlayableEpisode() + ", isAgeProtected=" + this.realmGet$isAgeProtected() + ", isPinProtected=" + this.realmGet$isPinProtected() + ", isPreviewProtected=" + this.realmGet$isPreviewProtected() + ", isAdvisoryDisabled=" + this.realmGet$isAdvisoryDisabled() + ", isAvailableToStream=" + this.realmGet$isAvailableToStream() + ", duration=" + this.realmGet$duration() + ", seasonNumber=" + this.realmGet$seasonNumber() + ", episodeNumber=" + this.realmGet$episodeNumber() + ", logicalStart=" + this.realmGet$logicalStart() + ", endtime=" + this.realmGet$endtime() + ", maxAutoplay=" + this.realmGet$maxAutoplay() + ", expTime=" + this.realmGet$expTime() + ", advisories=" + this.realmGet$advisoriesString() + ", watchedTime=" + this.realmGet$watchedTime() + ", bookmark=" + this.realmGet$bookmark() + '}';
    }
}
