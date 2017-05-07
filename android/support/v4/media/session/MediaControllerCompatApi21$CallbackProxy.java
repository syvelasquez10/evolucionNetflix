// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.Bundle;
import android.media.session.PlaybackState;
import android.media.MediaMetadata;
import android.media.session.MediaController$Callback;

class MediaControllerCompatApi21$CallbackProxy<T extends MediaControllerCompatApi21$Callback> extends MediaController$Callback
{
    protected final T mCallback;
    
    public MediaControllerCompatApi21$CallbackProxy(final T mCallback) {
        this.mCallback = mCallback;
    }
    
    public void onMetadataChanged(final MediaMetadata mediaMetadata) {
        this.mCallback.onMetadataChanged(mediaMetadata);
    }
    
    public void onPlaybackStateChanged(final PlaybackState playbackState) {
        this.mCallback.onPlaybackStateChanged(playbackState);
    }
    
    public void onSessionDestroyed() {
        this.mCallback.onSessionDestroyed();
    }
    
    public void onSessionEvent(final String s, final Bundle bundle) {
        this.mCallback.onSessionEvent(s, bundle);
    }
}
