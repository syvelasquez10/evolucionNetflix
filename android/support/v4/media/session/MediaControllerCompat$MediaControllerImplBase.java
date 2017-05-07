// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.ResultReceiver;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.MediaMetadataCompat;
import android.view.KeyEvent;

class MediaControllerCompat$MediaControllerImplBase implements MediaControllerCompat$MediaControllerImpl
{
    @Override
    public boolean dispatchMediaButtonEvent(final KeyEvent keyEvent) {
        return false;
    }
    
    @Override
    public Object getMediaController() {
        return null;
    }
    
    @Override
    public MediaMetadataCompat getMetadata() {
        return null;
    }
    
    @Override
    public MediaControllerCompat$PlaybackInfo getPlaybackInfo() {
        return null;
    }
    
    @Override
    public PlaybackStateCompat getPlaybackState() {
        return null;
    }
    
    @Override
    public int getRatingType() {
        return 0;
    }
    
    @Override
    public MediaControllerCompat$TransportControls getTransportControls() {
        return null;
    }
    
    @Override
    public void registerCallback(final MediaControllerCompat$Callback mediaControllerCompat$Callback, final Handler handler) {
    }
    
    @Override
    public void sendCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
    }
    
    @Override
    public void unregisterCallback(final MediaControllerCompat$Callback mediaControllerCompat$Callback) {
    }
}
