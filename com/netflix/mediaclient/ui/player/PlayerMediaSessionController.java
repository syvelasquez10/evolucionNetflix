// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.support.v4.media.session.PlaybackStateCompat$Builder;
import com.netflix.mediaclient.Log;
import android.support.v4.media.session.MediaSessionCompat$Callback;
import android.content.Context;

public class PlayerMediaSessionController extends AbsMediaSessionController
{
    private static final String TAG;
    
    static {
        TAG = PlayerMediaSessionController.class.getSimpleName();
    }
    
    public PlayerMediaSessionController(final Context context, final MediaSessionCompat$Callback callback) {
        super(context);
        this.setCallback(callback);
    }
    
    public void setMediaSessionState(final int n) {
        Log.i(PlayerMediaSessionController.TAG, "setMediaSessionState, state=" + n);
        final PlaybackStateCompat$Builder playbackStateCompat$Builder = new PlaybackStateCompat$Builder();
        if (n == 3) {
            playbackStateCompat$Builder.setActions(2L);
        }
        else {
            playbackStateCompat$Builder.setActions(4L);
        }
        playbackStateCompat$Builder.setState(n, -1L, 1.0f);
        this.setPlaybackState(playbackStateCompat$Builder.build());
    }
    
    @Override
    protected void startMediaSession() {
        Log.d(PlayerMediaSessionController.TAG, "startMediaSession");
        this.setActive(true);
        this.setMediaSessionState(3);
    }
    
    @Override
    protected void stopMediaSession() {
        Log.i(PlayerMediaSessionController.TAG, "stopMediaSession");
        this.setMediaSessionState(1);
        this.setActive(false);
    }
}
