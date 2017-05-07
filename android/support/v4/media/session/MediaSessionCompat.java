// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.support.v4.media.VolumeProviderCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.os.Handler;
import android.os.Bundle;
import android.os.Build$VERSION;
import android.text.TextUtils;
import android.content.Context;

public class MediaSessionCompat
{
    public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
    public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
    private final MediaSessionCompat$MediaSessionImpl mImpl;
    
    public MediaSessionCompat(final Context context, final String s) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("tag must not be null or empty");
        }
        if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaSessionCompat$MediaSessionImplApi21(context, s);
            return;
        }
        this.mImpl = new MediaSessionCompat$MediaSessionImplBase();
    }
    
    private MediaSessionCompat(final MediaSessionCompat$MediaSessionImpl mImpl) {
        this.mImpl = mImpl;
    }
    
    public static MediaSessionCompat obtain(final Object o) {
        return new MediaSessionCompat(new MediaSessionCompat$MediaSessionImplApi21(o));
    }
    
    public Object getMediaSession() {
        return this.mImpl.getMediaSession();
    }
    
    public MediaSessionCompat$Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }
    
    public boolean isActive() {
        return this.mImpl.isActive();
    }
    
    public void release() {
        this.mImpl.release();
    }
    
    public void sendSessionEvent(final String s, final Bundle bundle) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("event cannot be null or empty");
        }
        this.mImpl.sendSessionEvent(s, bundle);
    }
    
    public void setActive(final boolean active) {
        this.mImpl.setActive(active);
    }
    
    public void setCallback(final MediaSessionCompat$Callback mediaSessionCompat$Callback) {
        this.setCallback(mediaSessionCompat$Callback, null);
    }
    
    public void setCallback(final MediaSessionCompat$Callback mediaSessionCompat$Callback, Handler handler) {
        final MediaSessionCompat$MediaSessionImpl mImpl = this.mImpl;
        if (handler == null) {
            handler = new Handler();
        }
        mImpl.setCallback(mediaSessionCompat$Callback, handler);
    }
    
    public void setFlags(final int flags) {
        this.mImpl.setFlags(flags);
    }
    
    public void setMetadata(final MediaMetadataCompat metadata) {
        this.mImpl.setMetadata(metadata);
    }
    
    public void setPlaybackState(final PlaybackStateCompat playbackState) {
        this.mImpl.setPlaybackState(playbackState);
    }
    
    public void setPlaybackToLocal(final int playbackToLocal) {
        this.mImpl.setPlaybackToLocal(playbackToLocal);
    }
    
    public void setPlaybackToRemote(final VolumeProviderCompat playbackToRemote) {
        if (playbackToRemote == null) {
            throw new IllegalArgumentException("volumeProvider may not be null!");
        }
        this.mImpl.setPlaybackToRemote(playbackToRemote);
    }
}
