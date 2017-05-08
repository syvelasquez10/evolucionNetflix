// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import java.util.Map;
import java.util.HashMap;
import io.realm.internal.Table;
import io.realm.internal.ColumnInfo;

final class RealmPlayableRealmProxy$RealmPlayableColumnInfo extends ColumnInfo implements Cloneable
{
    public long advisoriesStringIndex;
    public long bookmarkIndex;
    public long durationIndex;
    public long endtimeIndex;
    public long episodeNumberIndex;
    public long expTimeIndex;
    public long isAdvisoryDisabledIndex;
    public long isAgeProtectedIndex;
    public long isAutoPlayIndex;
    public long isAvailableToStreamIndex;
    public long isEpisodeIndex;
    public long isExemptFromLimitIndex;
    public long isNSREIndex;
    public long isNextPlayableEpisodeIndex;
    public long isPinProtectedIndex;
    public long isSupplementalVideoIndex;
    public long logicalStartIndex;
    public long maxAutoplayIndex;
    public long parentIdIndex;
    public long parentTitleIndex;
    public long playableIdIndex;
    public long seasonLabelIndex;
    public long seasonNumberIndex;
    public long titleIndex;
    public long watchedTimeIndex;
    
    RealmPlayableRealmProxy$RealmPlayableColumnInfo(final String s, final Table table) {
        final HashMap<String, Long> indicesMap = new HashMap<String, Long>(25);
        this.playableIdIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "playableId");
        indicesMap.put("playableId", this.playableIdIndex);
        this.parentIdIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "parentId");
        indicesMap.put("parentId", this.parentIdIndex);
        this.titleIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "title");
        indicesMap.put("title", this.titleIndex);
        this.seasonLabelIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "seasonLabel");
        indicesMap.put("seasonLabel", this.seasonLabelIndex);
        this.parentTitleIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "parentTitle");
        indicesMap.put("parentTitle", this.parentTitleIndex);
        this.advisoriesStringIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "advisoriesString");
        indicesMap.put("advisoriesString", this.advisoriesStringIndex);
        this.isEpisodeIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "isEpisode");
        indicesMap.put("isEpisode", this.isEpisodeIndex);
        this.isNSREIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "isNSRE");
        indicesMap.put("isNSRE", this.isNSREIndex);
        this.isAutoPlayIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "isAutoPlay");
        indicesMap.put("isAutoPlay", this.isAutoPlayIndex);
        this.isExemptFromLimitIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "isExemptFromLimit");
        indicesMap.put("isExemptFromLimit", this.isExemptFromLimitIndex);
        this.isNextPlayableEpisodeIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "isNextPlayableEpisode");
        indicesMap.put("isNextPlayableEpisode", this.isNextPlayableEpisodeIndex);
        this.isAgeProtectedIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "isAgeProtected");
        indicesMap.put("isAgeProtected", this.isAgeProtectedIndex);
        this.isPinProtectedIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "isPinProtected");
        indicesMap.put("isPinProtected", this.isPinProtectedIndex);
        this.isAdvisoryDisabledIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "isAdvisoryDisabled");
        indicesMap.put("isAdvisoryDisabled", this.isAdvisoryDisabledIndex);
        this.isAvailableToStreamIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "isAvailableToStream");
        indicesMap.put("isAvailableToStream", this.isAvailableToStreamIndex);
        this.isSupplementalVideoIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "isSupplementalVideo");
        indicesMap.put("isSupplementalVideo", this.isSupplementalVideoIndex);
        this.durationIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "duration");
        indicesMap.put("duration", this.durationIndex);
        this.seasonNumberIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "seasonNumber");
        indicesMap.put("seasonNumber", this.seasonNumberIndex);
        this.episodeNumberIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "episodeNumber");
        indicesMap.put("episodeNumber", this.episodeNumberIndex);
        this.logicalStartIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "logicalStart");
        indicesMap.put("logicalStart", this.logicalStartIndex);
        this.endtimeIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "endtime");
        indicesMap.put("endtime", this.endtimeIndex);
        this.maxAutoplayIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "maxAutoplay");
        indicesMap.put("maxAutoplay", this.maxAutoplayIndex);
        this.expTimeIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "expTime");
        indicesMap.put("expTime", this.expTimeIndex);
        this.watchedTimeIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "watchedTime");
        indicesMap.put("watchedTime", this.watchedTimeIndex);
        this.bookmarkIndex = this.getValidColumnIndex(s, table, "RealmPlayable", "bookmark");
        indicesMap.put("bookmark", this.bookmarkIndex);
        this.setIndicesMap((Map)indicesMap);
    }
    
    public final RealmPlayableRealmProxy$RealmPlayableColumnInfo clone() {
        return (RealmPlayableRealmProxy$RealmPlayableColumnInfo)super.clone();
    }
    
    public final void copyColumnInfoFrom(final ColumnInfo columnInfo) {
        final RealmPlayableRealmProxy$RealmPlayableColumnInfo realmPlayableRealmProxy$RealmPlayableColumnInfo = (RealmPlayableRealmProxy$RealmPlayableColumnInfo)columnInfo;
        this.playableIdIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.playableIdIndex;
        this.parentIdIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.parentIdIndex;
        this.titleIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.titleIndex;
        this.seasonLabelIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.seasonLabelIndex;
        this.parentTitleIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.parentTitleIndex;
        this.advisoriesStringIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.advisoriesStringIndex;
        this.isEpisodeIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.isEpisodeIndex;
        this.isNSREIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.isNSREIndex;
        this.isAutoPlayIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.isAutoPlayIndex;
        this.isExemptFromLimitIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.isExemptFromLimitIndex;
        this.isNextPlayableEpisodeIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.isNextPlayableEpisodeIndex;
        this.isAgeProtectedIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.isAgeProtectedIndex;
        this.isPinProtectedIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.isPinProtectedIndex;
        this.isAdvisoryDisabledIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.isAdvisoryDisabledIndex;
        this.isAvailableToStreamIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.isAvailableToStreamIndex;
        this.isSupplementalVideoIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.isSupplementalVideoIndex;
        this.durationIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.durationIndex;
        this.seasonNumberIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.seasonNumberIndex;
        this.episodeNumberIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.episodeNumberIndex;
        this.logicalStartIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.logicalStartIndex;
        this.endtimeIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.endtimeIndex;
        this.maxAutoplayIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.maxAutoplayIndex;
        this.expTimeIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.expTimeIndex;
        this.watchedTimeIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.watchedTimeIndex;
        this.bookmarkIndex = realmPlayableRealmProxy$RealmPlayableColumnInfo.bookmarkIndex;
        this.setIndicesMap(realmPlayableRealmProxy$RealmPlayableColumnInfo.getIndicesMap());
    }
}
