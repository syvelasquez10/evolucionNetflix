// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import android.text.TextUtils;
import android.graphics.SurfaceTexture;
import com.netflix.mediaclient.Log;
import android.view.Surface;
import android.media.MediaPlayer;
import android.view.TextureView;
import android.os.Handler;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.view.TextureView$SurfaceTextureListener;
import android.media.MediaPlayer$OnVideoSizeChangedListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnInfoListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;

public class MediaPlayerWrapper implements MediaPlayer$OnCompletionListener, MediaPlayer$OnErrorListener, MediaPlayer$OnInfoListener, MediaPlayer$OnPreparedListener, MediaPlayer$OnVideoSizeChangedListener, TextureView$SurfaceTextureListener
{
    private static final int MEDIA_PLAYER_END = 8;
    private static final int MEDIA_PLAYER_ERROR = 9;
    private static final int MEDIA_PLAYER_IDLE = 0;
    private static final int MEDIA_PLAYER_INITIALIZED = 1;
    private static final int MEDIA_PLAYER_PAUSED = 6;
    private static final int MEDIA_PLAYER_PLAYBACK_COMPLETED = 7;
    private static final int MEDIA_PLAYER_PREPARED = 2;
    private static final int MEDIA_PLAYER_PREPARING = 3;
    private static final int MEDIA_PLAYER_STARTED = 4;
    private static final int MEDIA_PLAYER_STOPPED = 5;
    private static final String TAG = "MediaPlayerWrapper";
    protected IClientLogging$AssetType assetType;
    private MediaPlayerWrapper$PlaybackEventsListener callbacks;
    private int completedLoops;
    private Handler handler;
    protected long length;
    protected String localUrl;
    protected TextureView mTextureView;
    private int maxLoops;
    private MediaPlayer mediaPlayer;
    protected long offset;
    private int playerState;
    private int seekPosition;
    boolean shouldLoop;
    private Runnable startPlaybackRunnable;
    protected boolean surfaceReady;
    protected Surface videoSurface;
    private float volume;
    
    public MediaPlayerWrapper(final TextureView mTextureView, final boolean shouldLoop, final int maxLoops, final float volume, final IClientLogging$AssetType assetType, final MediaPlayerWrapper$PlaybackEventsListener callbacks) {
        this.volume = 0.0f;
        this.surfaceReady = false;
        if (Log.isLoggable()) {
            Log.d("MediaPlayerWrapper", "Creating MediaPlayerWrapper - shouldLoop: " + shouldLoop + ", maxLoops: " + maxLoops + ", volume: " + volume + ", assetType: " + assetType);
        }
        this.assetType = assetType;
        this.callbacks = callbacks;
        if (mTextureView != null) {
            (this.mTextureView = mTextureView).setSurfaceTextureListener((TextureView$SurfaceTextureListener)this);
        }
        else {
            this.surfaceReady = true;
        }
        this.shouldLoop = shouldLoop;
        this.maxLoops = maxLoops;
        this.volume = volume;
    }
    
    private void releaseResources(final SurfaceTexture surfaceTexture) {
        this.releaseMediaPlayer();
        this.releaseSurface(surfaceTexture);
        if (this.callbacks != null) {
            this.callbacks.onPlaybackFinished();
        }
    }
    
    private void releaseSurface(final SurfaceTexture surfaceTexture) {
        this.surfaceReady = false;
        if (this.videoSurface != null) {
            this.videoSurface.release();
            this.videoSurface = null;
        }
        if (surfaceTexture != null) {
            surfaceTexture.release();
        }
    }
    
    private void startPlayback() {
        if (this.mediaPlayer == null) {
            if (Log.isLoggable()) {
                Log.d("MediaPlayerWrapper", this.assetType + ": MediaPlayer is null, cannot start playback.");
            }
        }
        else {
            final boolean playing = this.mediaPlayer.isPlaying();
            if (Log.isLoggable()) {
                Log.d("MediaPlayerWrapper", this.assetType + ": startPlayback(): Is MediaPlayer playing? - " + playing + " surfaceReady? - " + this.surfaceReady + " playerState=" + this.playerState);
            }
            if (this.surfaceReady && !playing && (this.playerState == 2 || this.playerState == 6 || this.playerState == 7)) {
                if (this.playerState != 6) {
                    this.mediaPlayer.seekTo(this.seekPosition);
                }
                if (Log.isLoggable()) {
                    Log.v("MediaPlayerWrapper", this.assetType + ": Starting media playback");
                }
                this.mediaPlayer.start();
                this.playerState = 4;
                this.mediaPlayer.setOnCompletionListener((MediaPlayer$OnCompletionListener)this);
            }
        }
    }
    
    private void stopPlayback() {
        if (this.mediaPlayer != null && this.playerState != 0 && this.playerState != 1 && this.playerState != 9) {
            this.seekPosition = this.mediaPlayer.getCurrentPosition();
            this.mediaPlayer.stop();
            this.playerState = 5;
        }
    }
    
    private void updateVolume() {
        if (this.mediaPlayer != null) {
            this.mediaPlayer.setVolume(this.volume, this.volume);
        }
    }
    
    public void clearCallbacks() {
        this.callbacks = null;
    }
    
    public void delayedStartPlayback(final int n) {
        if (this.handler == null) {
            this.handler = new Handler();
        }
        if (this.startPlaybackRunnable == null) {
            this.startPlaybackRunnable = new MediaPlayerWrapper$1(this);
        }
        if (Log.isLoggable()) {
            Log.d("MediaPlayerWrapper", this.assetType + ": Adding delay before startPlayback(): delay - " + n);
        }
        this.handler.postDelayed(this.startPlaybackRunnable, (long)n);
    }
    
    public void initializeMediaPlayer() {
        if (Log.isLoggable()) {
            Log.d("MediaPlayerWrapper", this.assetType + ": initializeMediaPlayer() surfaceReady? - " + this.surfaceReady);
        }
        if (this.surfaceReady) {
            try {
                if (this.mediaPlayer == null) {
                    this.mediaPlayer = new MediaPlayer();
                    this.playerState = 0;
                    this.mediaPlayer.setOnErrorListener((MediaPlayer$OnErrorListener)this);
                    this.mediaPlayer.setAudioStreamType(3);
                    this.mediaPlayer.setScreenOnWhilePlaying(false);
                    this.mediaPlayer.setOnInfoListener((MediaPlayer$OnInfoListener)this);
                    this.mediaPlayer.setOnPreparedListener((MediaPlayer$OnPreparedListener)this);
                    this.mediaPlayer.setOnVideoSizeChangedListener((MediaPlayer$OnVideoSizeChangedListener)this);
                    this.updateVolume();
                }
                if (this.videoSurface != null) {
                    this.mediaPlayer.setSurface(this.videoSurface);
                }
                if (Log.isLoggable()) {
                    Log.d("MediaPlayerWrapper", this.assetType + ": initializeMediaPlayer() playerState=" + this.playerState + " localUrl=" + this.localUrl);
                }
                if (this.playerState == 6 || this.playerState == 2) {
                    this.startPlayback();
                    return;
                }
                if ((this.playerState == 0 || this.playerState == 5) && !TextUtils.isEmpty((CharSequence)this.localUrl)) {
                    Object o = new File(this.localUrl);
                    if (((File)o).exists()) {
                        o = new FileInputStream((File)o);
                        this.mediaPlayer.setDataSource(((FileInputStream)o).getFD(), this.offset, this.length);
                        ((FileInputStream)o).close();
                        this.playerState = 1;
                        this.mediaPlayer.prepareAsync();
                        this.playerState = 3;
                        return;
                    }
                    goto Label_0361;
                }
            }
            catch (RuntimeException ex) {}
            catch (IOException o) {
                goto Label_0313;
            }
        }
    }
    
    public boolean isDonePlaying() {
        if (this.maxLoops != -1) {
            final int completedLoops = this.completedLoops;
            int maxLoops;
            if (this.shouldLoop) {
                maxLoops = this.maxLoops;
            }
            else {
                maxLoops = 0;
            }
            if (completedLoops >= maxLoops) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isMuted() {
        return this.volume <= 0.0f;
    }
    
    public void onCompletion(final MediaPlayer mediaPlayer) {
        final boolean b = false;
        this.playerState = 7;
        ++this.completedLoops;
        this.seekPosition = 0;
        if (this.isDonePlaying()) {
            if (Log.isLoggable()) {
                Log.v("MediaPlayerWrapper", this.assetType + ": onCompletion - done playing, releasing resources");
            }
            if (this.callbacks != null) {
                this.callbacks.onPlaybackSuccessfullyCompleted();
            }
            this.releaseResources();
            return;
        }
        if (Log.isLoggable()) {
            Log.v("MediaPlayerWrapper", this.assetType + ": onCompletion - not done playing, completedLoops: " + this.completedLoops);
        }
        boolean b2 = b;
        if (this.mediaPlayer != null) {
            b2 = b;
            if (this.mediaPlayer.isPlaying()) {
                b2 = true;
            }
        }
        if (Log.isLoggable()) {
            Log.d("MediaPlayerWrapper", this.assetType + ": onCompletion() - Need to loop playback. Is MediaPlayer playing? - " + b2);
        }
        if (b2) {
            if (Log.isLoggable()) {
                Log.d("MediaPlayerWrapper", this.assetType + ": MediaPlayer#isPlaying() is still true when expected false. Delay next playback.");
            }
            this.delayedStartPlayback(100);
            return;
        }
        this.startPlayback();
    }
    
    public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
        this.playerState = 9;
        if (Log.isLoggable()) {
            Log.v("MediaPlayerWrapper", this.assetType + ": Media Player failed, Error codes -> what: " + n + ", extra: " + n2);
        }
        if (this.callbacks != null) {
            this.callbacks.onPlaybackError(n, n2);
        }
        this.releaseResources();
        return true;
    }
    
    public boolean onInfo(final MediaPlayer mediaPlayer, final int n, final int n2) {
        if (n == 3) {
            if (this.callbacks != null) {
                this.callbacks.onPlaybackStarted();
            }
            return true;
        }
        return false;
    }
    
    public void onPrepared(final MediaPlayer mediaPlayer) {
        this.playerState = 2;
        this.startPlayback();
    }
    
    public void onSurfaceTextureAvailable(final SurfaceTexture surfaceTexture, final int n, final int n2) {
        if (Log.isLoggable()) {
            Log.v("MediaPlayerWrapper", this.assetType + ": onSurfaceTextureAvailable");
        }
        this.videoSurface = new Surface(surfaceTexture);
        this.surfaceReady = true;
        this.initializeMediaPlayer();
    }
    
    public boolean onSurfaceTextureDestroyed(final SurfaceTexture surfaceTexture) {
        if (Log.isLoggable()) {
            Log.v("MediaPlayerWrapper", this.assetType + ": SurfaceTexture Destroyed, releasing Media Player");
        }
        this.releaseResources(surfaceTexture);
        return false;
    }
    
    public void onSurfaceTextureSizeChanged(final SurfaceTexture surfaceTexture, final int n, final int n2) {
    }
    
    public void onSurfaceTextureUpdated(final SurfaceTexture surfaceTexture) {
    }
    
    public void onVideoSizeChanged(final MediaPlayer mediaPlayer, final int n, final int n2) {
        if (n > 0 && n2 > 0) {
            this.startPlayback();
        }
        else if (Log.isLoggable()) {
            Log.v("MediaPlayerWrapper", this.assetType + ": video has no width or height. width: " + n + " height: " + n2);
        }
    }
    
    public void pausePlayback() {
        if (this.mediaPlayer != null && this.mediaPlayer.isPlaying()) {
            this.seekPosition = this.mediaPlayer.getCurrentPosition();
            this.mediaPlayer.pause();
            this.playerState = 6;
        }
    }
    
    public void releaseMediaPlayer() {
        if (this.mediaPlayer != null) {
            if (this.mediaPlayer.isPlaying()) {
                this.stopPlayback();
            }
            this.mediaPlayer.reset();
            this.playerState = 0;
            this.mediaPlayer.release();
            this.playerState = 8;
            this.mediaPlayer = null;
        }
    }
    
    public void releaseResources() {
        this.releaseResources(false);
    }
    
    public void releaseResources(final boolean b) {
        SurfaceTexture surfaceTexture;
        if (b) {
            surfaceTexture = this.mTextureView.getSurfaceTexture();
        }
        else {
            surfaceTexture = null;
        }
        this.releaseResources(surfaceTexture);
        if (this.handler != null && this.startPlaybackRunnable != null) {
            this.handler.removeCallbacks(this.startPlaybackRunnable);
        }
    }
    
    public boolean resumePlayback() {
        if (this.surfaceReady) {
            this.startPlayback();
            return true;
        }
        return false;
    }
    
    public void setDataSource(final String localUrl, final long offset, final long length) {
        this.localUrl = localUrl;
        this.offset = offset;
        this.length = length;
    }
    
    public void toggleVolume() {
        this.volume = 1.0f - this.volume;
        this.updateVolume();
    }
}
