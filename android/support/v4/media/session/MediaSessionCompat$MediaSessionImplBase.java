// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.os.Handler;
import android.os.Bundle;

class MediaSessionCompat$MediaSessionImplBase implements MediaSessionCompat$MediaSessionImpl
{
    @Override
    public Object getMediaSession() {
        return null;
    }
    
    @Override
    public MediaSessionCompat$Token getSessionToken() {
        return null;
    }
    
    @Override
    public boolean isActive() {
        return false;
    }
    
    @Override
    public void release() {
    }
    
    @Override
    public void sendSessionEvent(final String s, final Bundle bundle) {
    }
    
    @Override
    public void setActive(final boolean b) {
    }
    
    @Override
    public void setCallback(final MediaSessionCompat$Callback mediaSessionCompat$Callback, final Handler handler) {
    }
    
    @Override
    public void setFlags(final int n) {
    }
    
    @Override
    public void setMetadata(final MediaMetadataCompat mediaMetadataCompat) {
    }
    
    @Override
    public void setPlaybackState(final PlaybackStateCompat playbackStateCompat) {
    }
    
    @Override
    public void setPlaybackToLocal(final int n) {
    }
    
    @Override
    public void setPlaybackToRemote(final VolumeProviderCompat volumeProviderCompat) {
    }
}
