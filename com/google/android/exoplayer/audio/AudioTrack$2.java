// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.audio;

class AudioTrack$2 extends Thread
{
    final /* synthetic */ AudioTrack this$0;
    final /* synthetic */ android.media.AudioTrack val$toRelease;
    
    AudioTrack$2(final AudioTrack this$0, final android.media.AudioTrack val$toRelease) {
        this.this$0 = this$0;
        this.val$toRelease = val$toRelease;
    }
    
    @Override
    public void run() {
        this.val$toRelease.release();
    }
}
