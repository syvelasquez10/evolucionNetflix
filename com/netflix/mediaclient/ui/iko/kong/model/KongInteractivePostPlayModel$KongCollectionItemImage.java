// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.model;

public class KongInteractivePostPlayModel$KongCollectionItemImage
{
    KongInteractivePostPlayModel$KongImage badge;
    KongInteractivePostPlayModel$KongImage battleBadge;
    KongInteractivePostPlayModel$KongImage itemOnAvatar;
    final /* synthetic */ KongInteractivePostPlayModel this$0;
    
    public KongInteractivePostPlayModel$KongCollectionItemImage(final KongInteractivePostPlayModel this$0) {
        this.this$0 = this$0;
    }
    
    public KongInteractivePostPlayModel$KongImage getBadge() {
        return this.badge;
    }
    
    public KongInteractivePostPlayModel$KongImage getBattleBadge() {
        return this.battleBadge;
    }
    
    public KongInteractivePostPlayModel$KongImage getItemOnAvatar() {
        return this.itemOnAvatar;
    }
    
    @Override
    public String toString() {
        return "KongCollectionItemImage{badge=" + this.badge + ", itemOnAvatar=" + this.itemOnAvatar + ", battleBadge=" + this.battleBadge + '}';
    }
}
