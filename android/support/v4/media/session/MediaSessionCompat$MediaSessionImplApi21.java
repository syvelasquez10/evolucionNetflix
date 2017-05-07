// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.os.Handler;
import android.os.Bundle;
import android.content.Context;

class MediaSessionCompat$MediaSessionImplApi21 implements MediaSessionCompat$MediaSessionImpl
{
    private final Object mSessionObj;
    private final MediaSessionCompat$Token mToken;
    
    public MediaSessionCompat$MediaSessionImplApi21(final Context context, final String s) {
        this.mSessionObj = MediaSessionCompatApi21.createSession(context, s);
        this.mToken = new MediaSessionCompat$Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj));
    }
    
    public MediaSessionCompat$MediaSessionImplApi21(final Object o) {
        this.mSessionObj = MediaSessionCompatApi21.verifySession(o);
        this.mToken = new MediaSessionCompat$Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj));
    }
    
    @Override
    public Object getMediaSession() {
        return this.mSessionObj;
    }
    
    @Override
    public MediaSessionCompat$Token getSessionToken() {
        return this.mToken;
    }
    
    @Override
    public boolean isActive() {
        return MediaSessionCompatApi21.isActive(this.mSessionObj);
    }
    
    @Override
    public void release() {
        MediaSessionCompatApi21.release(this.mSessionObj);
    }
    
    @Override
    public void sendSessionEvent(final String s, final Bundle bundle) {
        MediaSessionCompatApi21.sendSessionEvent(this.mSessionObj, s, bundle);
    }
    
    @Override
    public void setActive(final boolean b) {
        MediaSessionCompatApi21.setActive(this.mSessionObj, b);
    }
    
    @Override
    public void setCallback(final MediaSessionCompat$Callback mediaSessionCompat$Callback, final Handler handler) {
        MediaSessionCompatApi21.setCallback(this.mSessionObj, mediaSessionCompat$Callback.mCallbackObj, handler);
    }
    
    @Override
    public void setFlags(final int n) {
        MediaSessionCompatApi21.setFlags(this.mSessionObj, n);
    }
    
    @Override
    public void setMetadata(final MediaMetadataCompat mediaMetadataCompat) {
        MediaSessionCompatApi21.setMetadata(this.mSessionObj, mediaMetadataCompat.getMediaMetadata());
    }
    
    @Override
    public void setPlaybackState(final PlaybackStateCompat playbackStateCompat) {
        MediaSessionCompatApi21.setPlaybackState(this.mSessionObj, playbackStateCompat.getPlaybackState());
    }
    
    @Override
    public void setPlaybackToLocal(final int n) {
        MediaSessionCompatApi21.setPlaybackToLocal(this.mSessionObj, n);
    }
    
    @Override
    public void setPlaybackToRemote(final VolumeProviderCompat volumeProviderCompat) {
        MediaSessionCompatApi21.setPlaybackToRemote(this.mSessionObj, volumeProviderCompat.getVolumeProvider());
    }
}
