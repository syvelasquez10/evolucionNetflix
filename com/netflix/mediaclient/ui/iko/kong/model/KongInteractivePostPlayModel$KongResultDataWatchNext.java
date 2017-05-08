// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.model;

public class KongInteractivePostPlayModel$KongResultDataWatchNext
{
    KongInteractivePostPlayModel$KongVOSound audio;
    KongInteractivePostPlayModel$KongResultWatchDataNextString strings;
    final /* synthetic */ KongInteractivePostPlayModel this$0;
    String type;
    
    public KongInteractivePostPlayModel$KongResultDataWatchNext(final KongInteractivePostPlayModel this$0) {
        this.this$0 = this$0;
    }
    
    public KongInteractivePostPlayModel$KongVOSound getAudio() {
        return this.audio;
    }
    
    public KongInteractivePostPlayModel$KongResultWatchDataNextString getStrings() {
        return this.strings;
    }
    
    public String getType() {
        return this.type;
    }
    
    @Override
    public String toString() {
        return "KongResultDataWatchNext{type='" + this.type + '\'' + ", audio=" + this.audio + ", strings=" + this.strings + '}';
    }
}
