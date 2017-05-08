// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.model;

public class KongInteractivePostPlayModel$KongAvatar
{
    KongInteractivePostPlayModel$KongAvatarSound audio;
    KongInteractivePostPlayModel$KongAvatarImage images;
    final /* synthetic */ KongInteractivePostPlayModel this$0;
    
    public KongInteractivePostPlayModel$KongAvatar(final KongInteractivePostPlayModel this$0) {
        this.this$0 = this$0;
    }
    
    public KongInteractivePostPlayModel$KongAvatarSound getAudio() {
        return this.audio;
    }
    
    public KongInteractivePostPlayModel$KongAvatarImage getImages() {
        return this.images;
    }
    
    @Override
    public String toString() {
        return "KongAvatar{audio=" + this.audio + ", images=" + this.images + '}';
    }
}
