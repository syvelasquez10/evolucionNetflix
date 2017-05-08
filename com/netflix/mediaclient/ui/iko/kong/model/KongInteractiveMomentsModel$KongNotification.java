// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.model;

public class KongInteractiveMomentsModel$KongNotification
{
    KongInteractiveMomentsModel$KongUnlockedAudio audio;
    int endTimeMS;
    int startTimeMS;
    KongInteractiveMomentsModel$KongUnlockState states;
    KongInteractiveMomentsModel$KongUnlockedString strings;
    final /* synthetic */ KongInteractiveMomentsModel this$0;
    String type;
    
    public KongInteractiveMomentsModel$KongNotification(final KongInteractiveMomentsModel this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public String toString() {
        return "KongNotification{type='" + this.type + '\'' + ", startTimeMS=" + this.startTimeMS + ", endTimeMS=" + this.endTimeMS + ", strings=" + this.strings + ", audio=" + this.audio + ", states=" + this.states + '}';
    }
}
