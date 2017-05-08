// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.media.RemoteControlClient$OnPlaybackPositionUpdateListener;

class MediaSessionCompatApi18$OnPlaybackPositionUpdateListener<T extends MediaSessionCompatApi18$Callback> implements RemoteControlClient$OnPlaybackPositionUpdateListener
{
    protected final T mCallback;
    
    public MediaSessionCompatApi18$OnPlaybackPositionUpdateListener(final T mCallback) {
        this.mCallback = mCallback;
    }
    
    public void onPlaybackPositionUpdate(final long n) {
        this.mCallback.onSeekTo(n);
    }
}
