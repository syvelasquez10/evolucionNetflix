// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.ResultReceiver;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.media.MediaMetadataCompat;
import android.view.KeyEvent;

interface MediaControllerCompat$MediaControllerImpl
{
    boolean dispatchMediaButtonEvent(final KeyEvent p0);
    
    Object getMediaController();
    
    MediaMetadataCompat getMetadata();
    
    MediaControllerCompat$PlaybackInfo getPlaybackInfo();
    
    PlaybackStateCompat getPlaybackState();
    
    int getRatingType();
    
    MediaControllerCompat$TransportControls getTransportControls();
    
    void registerCallback(final MediaControllerCompat$Callback p0, final Handler p1);
    
    void sendCommand(final String p0, final Bundle p1, final ResultReceiver p2);
    
    void unregisterCallback(final MediaControllerCompat$Callback p0);
}
