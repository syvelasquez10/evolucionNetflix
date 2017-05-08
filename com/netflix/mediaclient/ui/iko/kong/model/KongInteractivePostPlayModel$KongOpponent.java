// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.model;

public class KongInteractivePostPlayModel$KongOpponent
{
    KongInteractivePostPlayModel$KongAvatarImage images;
    KongInteractivePostPlayModel$KongNameString name;
    final /* synthetic */ KongInteractivePostPlayModel this$0;
    
    public KongInteractivePostPlayModel$KongOpponent(final KongInteractivePostPlayModel this$0) {
        this.this$0 = this$0;
    }
    
    public KongInteractivePostPlayModel$KongAvatarImage getImages() {
        return this.images;
    }
    
    public KongInteractivePostPlayModel$KongNameString getName() {
        return this.name;
    }
    
    @Override
    public String toString() {
        return "KongOpponent{name=" + this.name + ", images=" + this.images + '}';
    }
}
