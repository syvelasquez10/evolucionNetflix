// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.text.TextUtils;
import android.os.ResultReceiver;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.MediaMetadataCompat;
import android.view.KeyEvent;
import android.os.Build$VERSION;
import android.content.Context;

public final class MediaControllerCompat
{
    private final MediaControllerCompat$MediaControllerImpl mImpl;
    
    public MediaControllerCompat(final Context context, final MediaSessionCompat$Token mediaSessionCompat$Token) {
        if (mediaSessionCompat$Token == null) {
            throw new IllegalArgumentException("sessionToken must not be null");
        }
        if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaControllerCompat$MediaControllerImplApi21(context, mediaSessionCompat$Token);
            return;
        }
        this.mImpl = new MediaControllerCompat$MediaControllerImplBase();
    }
    
    public MediaControllerCompat(final Context context, final MediaSessionCompat mediaSessionCompat) {
        if (mediaSessionCompat == null) {
            throw new IllegalArgumentException("session must not be null");
        }
        if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = new MediaControllerCompat$MediaControllerImplApi21(context, mediaSessionCompat);
            return;
        }
        this.mImpl = new MediaControllerCompat$MediaControllerImplBase();
    }
    
    public boolean dispatchMediaButtonEvent(final KeyEvent keyEvent) {
        if (keyEvent == null) {
            throw new IllegalArgumentException("KeyEvent may not be null");
        }
        return this.mImpl.dispatchMediaButtonEvent(keyEvent);
    }
    
    public Object getMediaController() {
        return this.mImpl.getMediaController();
    }
    
    public MediaMetadataCompat getMetadata() {
        return this.mImpl.getMetadata();
    }
    
    public MediaControllerCompat$PlaybackInfo getPlaybackInfo() {
        return this.mImpl.getPlaybackInfo();
    }
    
    public PlaybackStateCompat getPlaybackState() {
        return this.mImpl.getPlaybackState();
    }
    
    public int getRatingType() {
        return this.mImpl.getRatingType();
    }
    
    public MediaControllerCompat$TransportControls getTransportControls() {
        return this.mImpl.getTransportControls();
    }
    
    public void registerCallback(final MediaControllerCompat$Callback mediaControllerCompat$Callback) {
        this.registerCallback(mediaControllerCompat$Callback, null);
    }
    
    public void registerCallback(final MediaControllerCompat$Callback mediaControllerCompat$Callback, final Handler handler) {
        if (mediaControllerCompat$Callback == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        Handler handler2;
        if ((handler2 = handler) == null) {
            handler2 = new Handler();
        }
        this.mImpl.registerCallback(mediaControllerCompat$Callback, handler2);
    }
    
    public void sendCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("command cannot be null or empty");
        }
        this.mImpl.sendCommand(s, bundle, resultReceiver);
    }
    
    public void unregisterCallback(final MediaControllerCompat$Callback mediaControllerCompat$Callback) {
        if (mediaControllerCompat$Callback == null) {
            throw new IllegalArgumentException("callback cannot be null");
        }
        this.mImpl.unregisterCallback(mediaControllerCompat$Callback);
    }
}
