// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.os.Build$VERSION;

public abstract class MediaControllerCompat$Callback
{
    final Object mCallbackObj;
    
    public MediaControllerCompat$Callback() {
        if (Build$VERSION.SDK_INT >= 21) {
            this.mCallbackObj = MediaControllerCompatApi21.createCallback(new MediaControllerCompat$Callback$StubApi21(this, null));
            return;
        }
        this.mCallbackObj = null;
    }
    
    public void onMetadataChanged(final MediaMetadataCompat mediaMetadataCompat) {
    }
    
    public void onPlaybackStateChanged(final PlaybackStateCompat playbackStateCompat) {
    }
    
    public void onSessionDestroyed() {
    }
    
    public void onSessionEvent(final String s, final Bundle bundle) {
    }
}
