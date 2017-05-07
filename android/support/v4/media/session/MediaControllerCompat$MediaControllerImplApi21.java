// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.ResultReceiver;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.MediaMetadataCompat;
import android.view.KeyEvent;
import android.os.RemoteException;
import android.content.Context;

class MediaControllerCompat$MediaControllerImplApi21 implements MediaControllerCompat$MediaControllerImpl
{
    private final Object mControllerObj;
    
    public MediaControllerCompat$MediaControllerImplApi21(final Context context, final MediaSessionCompat$Token mediaSessionCompat$Token) {
        this.mControllerObj = MediaControllerCompatApi21.fromToken(context, mediaSessionCompat$Token.getToken());
        if (this.mControllerObj == null) {
            throw new RemoteException();
        }
    }
    
    public MediaControllerCompat$MediaControllerImplApi21(final Context context, final MediaSessionCompat mediaSessionCompat) {
        this.mControllerObj = MediaControllerCompatApi21.fromToken(context, mediaSessionCompat.getSessionToken().getToken());
    }
    
    @Override
    public boolean dispatchMediaButtonEvent(final KeyEvent keyEvent) {
        return MediaControllerCompatApi21.dispatchMediaButtonEvent(this.mControllerObj, keyEvent);
    }
    
    @Override
    public Object getMediaController() {
        return this.mControllerObj;
    }
    
    @Override
    public MediaMetadataCompat getMetadata() {
        final Object metadata = MediaControllerCompatApi21.getMetadata(this.mControllerObj);
        if (metadata != null) {
            return MediaMetadataCompat.fromMediaMetadata(metadata);
        }
        return null;
    }
    
    @Override
    public MediaControllerCompat$PlaybackInfo getPlaybackInfo() {
        final Object playbackInfo = MediaControllerCompatApi21.getPlaybackInfo(this.mControllerObj);
        if (playbackInfo != null) {
            return new MediaControllerCompat$PlaybackInfo(MediaControllerCompatApi21$PlaybackInfo.getPlaybackType(playbackInfo), MediaControllerCompatApi21$PlaybackInfo.getLegacyAudioStream(playbackInfo), MediaControllerCompatApi21$PlaybackInfo.getVolumeControl(playbackInfo), MediaControllerCompatApi21$PlaybackInfo.getMaxVolume(playbackInfo), MediaControllerCompatApi21$PlaybackInfo.getCurrentVolume(playbackInfo));
        }
        return null;
    }
    
    @Override
    public PlaybackStateCompat getPlaybackState() {
        final Object playbackState = MediaControllerCompatApi21.getPlaybackState(this.mControllerObj);
        if (playbackState != null) {
            return PlaybackStateCompat.fromPlaybackState(playbackState);
        }
        return null;
    }
    
    @Override
    public int getRatingType() {
        return MediaControllerCompatApi21.getRatingType(this.mControllerObj);
    }
    
    @Override
    public MediaControllerCompat$TransportControls getTransportControls() {
        final Object transportControls = MediaControllerCompatApi21.getTransportControls(this.mControllerObj);
        if (transportControls != null) {
            return new MediaControllerCompat$TransportControlsApi21(transportControls);
        }
        return null;
    }
    
    @Override
    public void registerCallback(final MediaControllerCompat$Callback mediaControllerCompat$Callback, final Handler handler) {
        MediaControllerCompatApi21.registerCallback(this.mControllerObj, mediaControllerCompat$Callback.mCallbackObj, handler);
    }
    
    @Override
    public void sendCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
        MediaControllerCompatApi21.sendCommand(this.mControllerObj, s, bundle, resultReceiver);
    }
    
    @Override
    public void unregisterCallback(final MediaControllerCompat$Callback mediaControllerCompat$Callback) {
        MediaControllerCompatApi21.unregisterCallback(this.mControllerObj, mediaControllerCompat$Callback.mCallbackObj);
    }
}
