// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

public class RemotePlayer$RemoteTargetState
{
    public final boolean buffering;
    public final int duration;
    public final boolean paused;
    public final int positionInSeconds;
    public final boolean showMiniPlayer;
    final /* synthetic */ RemotePlayer this$0;
    public final int volume;
    
    private RemotePlayer$RemoteTargetState(final RemotePlayer this$0, final boolean paused, final boolean buffering, final int positionInSeconds, final int duration, final int volume, final boolean showMiniPlayer) {
        this.this$0 = this$0;
        this.paused = paused;
        this.buffering = buffering;
        this.positionInSeconds = positionInSeconds;
        this.duration = duration;
        this.volume = volume;
        this.showMiniPlayer = showMiniPlayer;
    }
    
    @Override
    public String toString() {
        return "RemoteTargetState [paused=" + this.paused + ", buffering=" + this.buffering + ", position(seconds)=" + this.positionInSeconds + ", duration=" + this.duration + ", volume=" + this.volume + ", showMiniPlayer=" + this.showMiniPlayer + "]";
    }
}
