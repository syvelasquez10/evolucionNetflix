// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.model;

public class KongInteractivePostPlayModel$KongResultData
{
    KongInteractivePostPlayModel$KongVOSound audio;
    KongInteractivePostPlayModel$KongAvatarResult avatar;
    KongInteractivePostPlayModel$KongResultBattleSound battle;
    KongInteractivePostPlayModel$KongImage battleCard;
    boolean hasWatchedAllBattleVideosForEpisode;
    KongInteractivePostPlayModel$KongResultString strings;
    final /* synthetic */ KongInteractivePostPlayModel this$0;
    String type;
    
    public KongInteractivePostPlayModel$KongResultData(final KongInteractivePostPlayModel this$0) {
        this.this$0 = this$0;
    }
    
    public KongInteractivePostPlayModel$KongVOSound getAudio() {
        return this.audio;
    }
    
    public KongInteractivePostPlayModel$KongAvatarResult getAvatar() {
        return this.avatar;
    }
    
    public KongInteractivePostPlayModel$KongResultBattleSound getBattle() {
        return this.battle;
    }
    
    public KongInteractivePostPlayModel$KongImage getBattleCard() {
        return this.battleCard;
    }
    
    public KongInteractivePostPlayModel$KongResultString getStrings() {
        return this.strings;
    }
    
    public String getType() {
        return this.type;
    }
    
    public boolean isHasWatchedAllBattleVideosForEpisode() {
        return this.hasWatchedAllBattleVideosForEpisode;
    }
    
    @Override
    public String toString() {
        return "KongResultData{type='" + this.type + '\'' + ", hasWatchedAllBattleVideosForEpisode=" + this.hasWatchedAllBattleVideosForEpisode + ", strings=" + this.strings + ", avatar=" + this.avatar + ", audio=" + this.audio + ", battleCard=" + this.battleCard + ", battle=" + this.battle + '}';
    }
}
