// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.model;

import android.graphics.Bitmap;

public class KongInteractivePostPlayModel$KongCollectionItems
{
    KongInteractivePostPlayModel$KongCollectionItemSound audio;
    Bitmap badgeBitmap;
    KongInteractivePostPlayModel$KongCollectionItemImage images;
    String result;
    KongInteractivePostPlayModel$KongNameString strings;
    final /* synthetic */ KongInteractivePostPlayModel this$0;
    int videoId;
    
    public KongInteractivePostPlayModel$KongCollectionItems(final KongInteractivePostPlayModel this$0) {
        this.this$0 = this$0;
    }
    
    public KongInteractivePostPlayModel$KongImage getBadge() {
        if (this.images == null) {
            return null;
        }
        return this.images.badge;
    }
    
    public Bitmap getBadgeBitmap() {
        return this.badgeBitmap;
    }
    
    public String getBadgeUrl() {
        final KongInteractivePostPlayModel$KongImage badge = this.getBadge();
        if (badge != null) {
            return badge.url;
        }
        return null;
    }
    
    public KongInteractivePostPlayModel$KongImage getBattleBadge() {
        if (this.images == null) {
            return null;
        }
        return this.images.battleBadge;
    }
    
    public KongInteractivePostPlayModel$KongSound getItemBattleAgainSound() {
        if (this.audio == null) {
            return null;
        }
        return this.audio.battleAgainVO;
    }
    
    public String getItemNameString() {
        if (this.strings == null) {
            return null;
        }
        return this.strings.name;
    }
    
    public KongInteractivePostPlayModel$KongImage getItemOnAvatar() {
        if (this.images == null) {
            return null;
        }
        return this.images.itemOnAvatar;
    }
    
    public String getItemResult() {
        return this.result;
    }
    
    public KongInteractivePostPlayModel$KongSound getItemSound() {
        if (this.audio == null) {
            return null;
        }
        return this.audio.getVo();
    }
    
    public int getVideoId() {
        return this.videoId;
    }
    
    public void setBadgeBitmap(final Bitmap badgeBitmap) {
        this.badgeBitmap = badgeBitmap;
    }
    
    @Override
    public String toString() {
        return "KongCollectionItems{result='" + this.result + '\'' + ", videoId=" + this.videoId + ", strings=" + this.strings + ", images=" + this.images + ", audio=" + this.audio + '}';
    }
}
