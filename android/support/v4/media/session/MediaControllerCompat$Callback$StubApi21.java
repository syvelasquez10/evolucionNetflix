// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;

class MediaControllerCompat$Callback$StubApi21 implements MediaControllerCompatApi21$Callback
{
    final /* synthetic */ MediaControllerCompat$Callback this$0;
    
    private MediaControllerCompat$Callback$StubApi21(final MediaControllerCompat$Callback this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onMetadataChanged(final Object o) {
        this.this$0.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(o));
    }
    
    @Override
    public void onPlaybackStateChanged(final Object o) {
        this.this$0.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(o));
    }
    
    @Override
    public void onSessionDestroyed() {
        this.this$0.onSessionDestroyed();
    }
    
    @Override
    public void onSessionEvent(final String s, final Bundle bundle) {
        this.this$0.onSessionEvent(s, bundle);
    }
}
