// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.graphics.SurfaceTexture;
import java.io.IOException;
import com.netflix.mediaclient.Log;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.view.TextureView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.Surface;
import android.media.MediaPlayer;
import android.view.TextureView$SurfaceTextureListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;

public class MediaPlayerWrapper implements MediaPlayer$OnCompletionListener, MediaPlayer$OnErrorListener, MediaPlayer$OnPreparedListener, TextureView$SurfaceTextureListener
{
    private static final int MEDIA_PLAYER_COMPLETE = 7;
    private static final int MEDIA_PLAYER_END = 8;
    private static final int MEDIA_PLAYER_ERROR = 9;
    private static final int MEDIA_PLAYER_IDLE = 0;
    private static final int MEDIA_PLAYER_INITIALIZED = 1;
    private static final int MEDIA_PLAYER_PAUSED = 6;
    private static final int MEDIA_PLAYER_PREPARED = 2;
    private static final int MEDIA_PLAYER_PREPARING = 3;
    private static final int MEDIA_PLAYER_STARTED = 4;
    private static final int MEDIA_PLAYER_STOPPED = 5;
    private static final String TAG = "MediaPlayerWrapper";
    private MediaPlayerWrapper$PlaybackEventsListener callbacks;
    private int completedLoops;
    private String localUrl;
    private int maxLoops;
    private MediaPlayer mediaPlayer;
    private boolean muted;
    private int playerState;
    private int seekPosition;
    boolean shouldLoop;
    private Surface videoSurface;
    
    public MediaPlayerWrapper(final NetflixActivity netflixActivity, final TextureView textureView, final String s, final boolean b, final int maxLoops, final MediaPlayerWrapper$PlaybackEventsListener callbacks) {
        this.muted = true;
        this.callbacks = callbacks;
        this.shouldLoop = b;
        this.maxLoops = maxLoops;
        netflixActivity.getServiceManager().fetchResource(s, IClientLogging$AssetType.motionBillboard, new MediaPlayerWrapper$1(this));
        this.mediaPlayer = new MediaPlayer();
        this.playerState = 0;
        this.mediaPlayer.setOnErrorListener((MediaPlayer$OnErrorListener)this);
        this.mediaPlayer.setLooping(b);
        this.mediaPlayer.setAudioStreamType(3);
        this.updateVolume();
        this.mediaPlayer.setOnPreparedListener((MediaPlayer$OnPreparedListener)this);
        this.mediaPlayer.setOnCompletionListener((MediaPlayer$OnCompletionListener)this);
        textureView.setSurfaceTextureListener((TextureView$SurfaceTextureListener)this);
    }
    
    private void playVideo() {
        if (!this.mediaPlayer.isPlaying()) {
            this.mediaPlayer.seekTo(this.seekPosition);
            if (this.playerState == 2 || this.playerState == 6) {
                this.mediaPlayer.start();
                this.playerState = 4;
            }
        }
    }
    
    private void prepareMediaPlayer() {
        if (TextUtils.isEmpty((CharSequence)this.localUrl) || this.mediaPlayer == null) {
            return;
        }
        if (this.playerState != 0) {
            if (this.playerState != 5) {
                return;
            }
        }
        try {
            this.mediaPlayer.setDataSource(this.localUrl);
            this.playerState = 1;
            this.mediaPlayer.prepareAsync();
            this.playerState = 3;
        }
        catch (IOException ex) {
            if (Log.isLoggable()) {
                Log.e("MediaPlayerWrapper", "Error loading motion billboard from local url: " + this.localUrl, ex);
            }
            this.releaseResources();
        }
    }
    
    private void releaseMediaPlayer() {
        if (this.mediaPlayer != null) {
            if (this.mediaPlayer.isPlaying()) {
                this.mediaPlayer.stop();
                this.playerState = 5;
            }
            this.mediaPlayer.reset();
            this.playerState = 0;
            this.mediaPlayer.release();
            this.playerState = 8;
            this.mediaPlayer = null;
        }
    }
    
    private void releaseResources(final SurfaceTexture surfaceTexture) {
        this.releaseMediaPlayer();
        this.releaseSurface(surfaceTexture);
        this.callbacks.onCompletion();
    }
    
    private void releaseSurface(final SurfaceTexture surfaceTexture) {
        if (this.videoSurface != null) {
            this.videoSurface.release();
            this.videoSurface = null;
        }
        if (surfaceTexture != null) {
            surfaceTexture.release();
        }
    }
    
    private void updateVolume() {
        if (this.mediaPlayer != null) {
            boolean b;
            if (this.muted) {
                b = false;
            }
            else {
                b = true;
            }
            this.mediaPlayer.setVolume((float)(b ? 1 : 0), (float)(b ? 1 : 0));
        }
    }
    
    public boolean donePlaying() {
        return this.completedLoops > this.maxLoops;
    }
    
    public int getCompletedLoops() {
        return this.completedLoops;
    }
    
    public int getCurrentPosition() {
        if (this.mediaPlayer != null) {
            return this.mediaPlayer.getCurrentPosition();
        }
        return 0;
    }
    
    public void initParams(final int seekPosition, final int completedLoops) {
        this.completedLoops = completedLoops;
        this.seekPosition = seekPosition;
    }
    
    public boolean isMuted() {
        return this.muted;
    }
    
    public void onCompletion(final MediaPlayer mediaPlayer) {
        ++this.completedLoops;
        this.callbacks.updateBookmarks(0, this.completedLoops);
        if (this.donePlaying()) {
            this.playerState = 7;
            this.releaseResources();
        }
    }
    
    public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
        this.playerState = 9;
        if (Log.isLoggable()) {
            Log.v("MediaPlayerWrapper", "billboard: Media Player failed, Error codes -> what: " + n + ", extra: " + n2);
        }
        this.releaseResources();
        return true;
    }
    
    public void onPrepared(final MediaPlayer mediaPlayer) {
        this.playerState = 2;
        this.playVideo();
        this.callbacks.onPrepared();
    }
    
    public void onSurfaceTextureAvailable(final SurfaceTexture surfaceTexture, final int n, final int n2) {
        this.videoSurface = new Surface(surfaceTexture);
        try {
            if (this.mediaPlayer != null) {
                this.mediaPlayer.setSurface(this.videoSurface);
                if (this.playerState == 0 || this.playerState == 5) {
                    this.prepareMediaPlayer();
                    return;
                }
                if (this.playerState == 6 || this.playerState == 2) {
                    this.playVideo();
                }
            }
        }
        catch (IllegalArgumentException ex) {}
        catch (IllegalStateException ex2) {
            goto Label_0074;
        }
        catch (SecurityException ex2) {
            goto Label_0074;
        }
    }
    
    public boolean onSurfaceTextureDestroyed(final SurfaceTexture surfaceTexture) {
        if (Log.isLoggable()) {
            Log.v("MediaPlayerWrapper", "billboard: SurfaceTexture Destroyed, releasing Media Player");
        }
        if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
            this.seekPosition = this.mediaPlayer.getCurrentPosition();
            this.mediaPlayer.pause();
            this.playerState = 6;
            this.callbacks.updateBookmarks(this.seekPosition, this.completedLoops);
        }
        this.releaseSurface(surfaceTexture);
        return false;
    }
    
    public void onSurfaceTextureSizeChanged(final SurfaceTexture surfaceTexture, final int n, final int n2) {
    }
    
    public void onSurfaceTextureUpdated(final SurfaceTexture surfaceTexture) {
    }
    
    public void releaseResources() {
        this.releaseResources(null);
    }
    
    public void toggleVolume() {
        this.muted = !this.muted;
        this.updateVolume();
    }
}
