// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.os.Handler;
import android.os.Bundle;

interface MediaSessionCompat$MediaSessionImpl
{
    Object getMediaSession();
    
    MediaSessionCompat$Token getSessionToken();
    
    boolean isActive();
    
    void release();
    
    void sendSessionEvent(final String p0, final Bundle p1);
    
    void setActive(final boolean p0);
    
    void setCallback(final MediaSessionCompat$Callback p0, final Handler p1);
    
    void setFlags(final int p0);
    
    void setMetadata(final MediaMetadataCompat p0);
    
    void setPlaybackState(final PlaybackStateCompat p0);
    
    void setPlaybackToLocal(final int p0);
    
    void setPlaybackToRemote(final VolumeProviderCompat p0);
}
