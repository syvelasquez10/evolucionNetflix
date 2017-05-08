// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline.realm;

import com.google.gson.JsonParser;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.model.leafs.advisory.Advisory;
import java.util.List;
import com.google.gson.Gson;
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
    }
    
    public RealmPlayable(final Playable playable) {
        this.realmSet$playableId(playable.getPlayableId());
        this.realmSet$parentId(playable.getParentId());
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
        return Advisory.asList(new JsonParser().parse(this.realmGet$advisoriesString()).getAsJsonArray());
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
    public String getParentId() {
        return this.realmGet$parentId();
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
    public boolean isSupplementalVideo() {
        return this.realmGet$isSupplementalVideo();
    }
    
    @Override
    public String realmGet$advisoriesString() {
        return this.advisoriesString;
    }
    
    @Override
    public int realmGet$bookmark() {
        return this.bookmark;
    }
    
    @Override
    public int realmGet$duration() {
        return this.duration;
    }
    
    @Override
    public int realmGet$endtime() {
        return this.endtime;
    }
    
    @Override
    public int realmGet$episodeNumber() {
        return this.episodeNumber;
    }
    
    @Override
    public long realmGet$expTime() {
        return this.expTime;
    }
    
    @Override
    public boolean realmGet$isAdvisoryDisabled() {
        return this.isAdvisoryDisabled;
    }
    
    @Override
    public boolean realmGet$isAgeProtected() {
        return this.isAgeProtected;
    }
    
    @Override
    public boolean realmGet$isAutoPlay() {
        return this.isAutoPlay;
    }
    
    @Override
    public boolean realmGet$isAvailableToStream() {
        return this.isAvailableToStream;
    }
    
    @Override
    public boolean realmGet$isEpisode() {
        return this.isEpisode;
    }
    
    @Override
    public boolean realmGet$isExemptFromLimit() {
        return this.isExemptFromLimit;
    }
    
    @Override
    public boolean realmGet$isNSRE() {
        return this.isNSRE;
    }
    
    @Override
    public boolean realmGet$isNextPlayableEpisode() {
        return this.isNextPlayableEpisode;
    }
    
    @Override
    public boolean realmGet$isPinProtected() {
        return this.isPinProtected;
    }
    
    @Override
    public boolean realmGet$isSupplementalVideo() {
        return this.isSupplementalVideo;
    }
    
    @Override
    public int realmGet$logicalStart() {
        return this.logicalStart;
    }
    
    @Override
    public int realmGet$maxAutoplay() {
        return this.maxAutoplay;
    }
    
    @Override
    public String realmGet$parentId() {
        return this.parentId;
    }
    
    @Override
    public String realmGet$parentTitle() {
        return this.parentTitle;
    }
    
    @Override
    public String realmGet$playableId() {
        return this.playableId;
    }
    
    @Override
    public String realmGet$seasonLabel() {
        return this.seasonLabel;
    }
    
    @Override
    public int realmGet$seasonNumber() {
        return this.seasonNumber;
    }
    
    @Override
    public String realmGet$title() {
        return this.title;
    }
    
    @Override
    public long realmGet$watchedTime() {
        return this.watchedTime;
    }
    
    @Override
    public void realmSet$advisoriesString(final String advisoriesString) {
        this.advisoriesString = advisoriesString;
    }
    
    @Override
    public void realmSet$bookmark(final int bookmark) {
        this.bookmark = bookmark;
    }
    
    @Override
    public void realmSet$duration(final int duration) {
        this.duration = duration;
    }
    
    @Override
    public void realmSet$endtime(final int endtime) {
        this.endtime = endtime;
    }
    
    @Override
    public void realmSet$episodeNumber(final int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }
    
    @Override
    public void realmSet$expTime(final long expTime) {
        this.expTime = expTime;
    }
    
    @Override
    public void realmSet$isAdvisoryDisabled(final boolean isAdvisoryDisabled) {
        this.isAdvisoryDisabled = isAdvisoryDisabled;
    }
    
    @Override
    public void realmSet$isAgeProtected(final boolean isAgeProtected) {
        this.isAgeProtected = isAgeProtected;
    }
    
    @Override
    public void realmSet$isAutoPlay(final boolean isAutoPlay) {
        this.isAutoPlay = isAutoPlay;
    }
    
    @Override
    public void realmSet$isAvailableToStream(final boolean isAvailableToStream) {
        this.isAvailableToStream = isAvailableToStream;
    }
    
    @Override
    public void realmSet$isEpisode(final boolean isEpisode) {
        this.isEpisode = isEpisode;
    }
    
    @Override
    public void realmSet$isExemptFromLimit(final boolean isExemptFromLimit) {
        this.isExemptFromLimit = isExemptFromLimit;
    }
    
    @Override
    public void realmSet$isNSRE(final boolean isNSRE) {
        this.isNSRE = isNSRE;
    }
    
    @Override
    public void realmSet$isNextPlayableEpisode(final boolean isNextPlayableEpisode) {
        this.isNextPlayableEpisode = isNextPlayableEpisode;
    }
    
    @Override
    public void realmSet$isPinProtected(final boolean isPinProtected) {
        this.isPinProtected = isPinProtected;
    }
    
    @Override
    public void realmSet$isSupplementalVideo(final boolean isSupplementalVideo) {
        this.isSupplementalVideo = isSupplementalVideo;
    }
    
    @Override
    public void realmSet$logicalStart(final int logicalStart) {
        this.logicalStart = logicalStart;
    }
    
    @Override
    public void realmSet$maxAutoplay(final int maxAutoplay) {
        this.maxAutoplay = maxAutoplay;
    }
    
    @Override
    public void realmSet$parentId(final String parentId) {
        this.parentId = parentId;
    }
    
    @Override
    public void realmSet$parentTitle(final String parentTitle) {
        this.parentTitle = parentTitle;
    }
    
    public void realmSet$playableId(final String playableId) {
        this.playableId = playableId;
    }
    
    @Override
    public void realmSet$seasonLabel(final String seasonLabel) {
        this.seasonLabel = seasonLabel;
    }
    
    @Override
    public void realmSet$seasonNumber(final int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }
    
    @Override
    public void realmSet$title(final String title) {
        this.title = title;
    }
    
    @Override
    public void realmSet$watchedTime(final long watchedTime) {
        this.watchedTime = watchedTime;
    }
    
    @Override
    public String toString() {
        return "RealmPlayable{playableId='" + this.realmGet$playableId() + '\'' + ", parentId='" + this.realmGet$parentId() + '\'' + ", title='" + this.realmGet$title() + '\'' + ", seasonLabel='" + this.realmGet$seasonLabel() + '\'' + ", parentTitle='" + this.realmGet$parentTitle() + '\'' + ", isEpisode=" + this.realmGet$isEpisode() + ", isNSRE=" + this.realmGet$isNSRE() + ", isAutoPlay=" + this.realmGet$isAutoPlay() + ", isExemptFromLimit=" + this.realmGet$isExemptFromLimit() + ", isNextPlayableEpisode=" + this.realmGet$isNextPlayableEpisode() + ", isAgeProtected=" + this.realmGet$isAgeProtected() + ", isPinProtected=" + this.realmGet$isPinProtected() + ", isAdvisoryDisabled=" + this.realmGet$isAdvisoryDisabled() + ", isAvailableToStream=" + this.realmGet$isAvailableToStream() + ", duration=" + this.realmGet$duration() + ", seasonNumber=" + this.realmGet$seasonNumber() + ", episodeNumber=" + this.realmGet$episodeNumber() + ", logicalStart=" + this.realmGet$logicalStart() + ", endtime=" + this.realmGet$endtime() + ", maxAutoplay=" + this.realmGet$maxAutoplay() + ", expTime=" + this.realmGet$expTime() + ", advisories=" + this.realmGet$advisoriesString() + ", watchedTime=" + this.realmGet$watchedTime() + ", bookmark=" + this.realmGet$bookmark() + '}';
    }
}
