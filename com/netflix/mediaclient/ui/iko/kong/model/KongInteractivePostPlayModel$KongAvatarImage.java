// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.model;

import com.google.gson.annotations.SerializedName;

public class KongInteractivePostPlayModel$KongAvatarImage
{
    @SerializedName("default")
    public KongInteractivePostPlayModel$KongImage _default;
    final /* synthetic */ KongInteractivePostPlayModel this$0;
    
    public KongInteractivePostPlayModel$KongAvatarImage(final KongInteractivePostPlayModel this$0) {
        this.this$0 = this$0;
    }
    
    public KongInteractivePostPlayModel$KongImage get_default() {
        return this._default;
    }
    
    @Override
    public String toString() {
        return "KongAvatarImage{_default=" + this._default + '}';
    }
}
