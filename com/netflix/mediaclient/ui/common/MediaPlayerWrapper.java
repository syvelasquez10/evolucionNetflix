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
import android.view.TextureView$SurfaceTextureListener;
import android.media.MediaPlayer$OnVideoSizeChangedListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;

public class MediaPlayerWrapper implements MediaPlayer$OnCompletionListener, MediaPlayer$OnErrorListener, MediaPlayer$OnPreparedListener, MediaPlayer$OnVideoSizeChangedListener, TextureView$SurfaceTextureListener
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
    private MediaPlayerWrapper$PlaybackEventsListener callbacks;
    private int completedLoops;
    private Handler handler;
    private long length;
    private String localUrl;
    private TextureView mTextureView;
    private int maxLoops;
    private MediaPlayer mediaPlayer;
    private long offset;
    private int playerState;
    private int seekPosition;
    boolean shouldLoop;
    private Runnable startPlaybackRunnable;
    private boolean surfaceReady;
    private Surface videoSurface;
    private float volume;
    
    public MediaPlayerWrapper(final TextureView mTextureView, final boolean shouldLoop, final int maxLoops, final float volume, final MediaPlayerWrapper$PlaybackEventsListener callbacks) {
        this.volume = 0.0f;
        this.surfaceReady = false;
        if (Log.isLoggable()) {
            Log.d("MediaPlayerWrapper", "Creating MediaPlayerWrapper - shouldLoop: " + shouldLoop + ", maxLoops: " + maxLoops + ", volume: " + volume);
        }
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
                Log.d("MediaPlayerWrapper", "MediaPlayer is null, cannot start playback.");
            }
        }
        else {
            final boolean playing = this.mediaPlayer.isPlaying();
            if (Log.isLoggable()) {
                Log.d("MediaPlayerWrapper", "startPlayback(): Is MediaPlayer playing? - " + playing);
            }
            if (this.surfaceReady && !playing && (this.playerState == 2 || this.playerState == 6 || this.playerState == 7)) {
                if (this.playerState != 6) {
                    this.mediaPlayer.seekTo(this.seekPosition);
                }
                Log.v("MediaPlayerWrapper", "Starting media playback");
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
            Log.d("MediaPlayerWrapper", "Adding delay before startPlayback(): delay - " + n);
        }
        this.handler.postDelayed(this.startPlaybackRunnable, (long)n);
    }
    
    public void initializeMediaPlayer() {
        if (this.surfaceReady) {
            try {
                if (this.mediaPlayer == null) {
                    this.mediaPlayer = new MediaPlayer();
                    this.playerState = 0;
                    this.mediaPlayer.setOnErrorListener((MediaPlayer$OnErrorListener)this);
                    this.mediaPlayer.setAudioStreamType(3);
                    this.mediaPlayer.setScreenOnWhilePlaying(false);
                    this.mediaPlayer.setOnPreparedListener((MediaPlayer$OnPreparedListener)this);
                    this.mediaPlayer.setOnVideoSizeChangedListener((MediaPlayer$OnVideoSizeChangedListener)this);
                    this.updateVolume();
                }
                if (this.videoSurface != null) {
                    this.mediaPlayer.setSurface(this.videoSurface);
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
                    goto Label_0250;
                }
            }
            catch (RuntimeException ex) {}
            catch (IOException o) {
                goto Label_0209;
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
            Log.v("MediaPlayerWrapper", "onCompletion: done playing, releasing resources");
            this.releaseResources();
            return;
        }
        if (Log.isLoggable()) {
            Log.v("MediaPlayerWrapper", "onCompletion: not done playing, completedLoops: " + this.completedLoops);
        }
        boolean b2 = b;
        if (this.mediaPlayer != null) {
            b2 = b;
            if (this.mediaPlayer.isPlaying()) {
                b2 = true;
            }
        }
        if (Log.isLoggable()) {
            Log.d("MediaPlayerWrapper", "onCompletion(): Need to loop playback. Is MediaPlayer playing? - " + b2);
        }
        if (b2) {
            Log.d("MediaPlayerWrapper", "MediaPlayer#isPlaying() is still true when expected false. Delay next playback.");
            this.delayedStartPlayback(100);
            return;
        }
        this.startPlayback();
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
        this.startPlayback();
    }
    
    public void onSurfaceTextureAvailable(final SurfaceTexture p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: new             Landroid/view/Surface;
        //     4: dup            
        //     5: aload_1        
        //     6: invokespecial   android/view/Surface.<init>:(Landroid/graphics/SurfaceTexture;)V
        //     9: putfield        com/netflix/mediaclient/ui/common/MediaPlayerWrapper.videoSurface:Landroid/view/Surface;
        //    12: aload_0        
        //    13: iconst_1       
        //    14: putfield        com/netflix/mediaclient/ui/common/MediaPlayerWrapper.surfaceReady:Z
        //    17: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    20: ifeq            32
        //    23: ldc             "MediaPlayerWrapper"
        //    25: ldc_w           "billboard: SurfaceTexture available, starting playback"
        //    28: invokestatic    com/netflix/mediaclient/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //    31: pop            
        //    32: aload_0        
        //    33: getfield        com/netflix/mediaclient/ui/common/MediaPlayerWrapper.localUrl:Ljava/lang/String;
        //    36: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    39: ifne            178
        //    42: new             Ljava/io/File;
        //    45: dup            
        //    46: aload_0        
        //    47: getfield        com/netflix/mediaclient/ui/common/MediaPlayerWrapper.localUrl:Ljava/lang/String;
        //    50: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    53: astore_1       
        //    54: new             Ljava/io/FileInputStream;
        //    57: dup            
        //    58: aload_1        
        //    59: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    62: astore_1       
        //    63: aload_1        
        //    64: invokevirtual   java/io/FileInputStream.getFD:()Ljava/io/FileDescriptor;
        //    67: astore          6
        //    69: new             Landroid/media/MediaMetadataRetriever;
        //    72: dup            
        //    73: invokespecial   android/media/MediaMetadataRetriever.<init>:()V
        //    76: astore          7
        //    78: aload           7
        //    80: aload           6
        //    82: aload_0        
        //    83: getfield        com/netflix/mediaclient/ui/common/MediaPlayerWrapper.offset:J
        //    86: aload_0        
        //    87: getfield        com/netflix/mediaclient/ui/common/MediaPlayerWrapper.length:J
        //    90: invokevirtual   android/media/MediaMetadataRetriever.setDataSource:(Ljava/io/FileDescriptor;JJ)V
        //    93: aload           7
        //    95: bipush          19
        //    97: invokevirtual   android/media/MediaMetadataRetriever.extractMetadata:(I)Ljava/lang/String;
        //   100: invokestatic    java/lang/Float.parseFloat:(Ljava/lang/String;)F
        //   103: fstore          5
        //   105: aload           7
        //   107: bipush          18
        //   109: invokevirtual   android/media/MediaMetadataRetriever.extractMetadata:(I)Ljava/lang/String;
        //   112: invokestatic    java/lang/Float.parseFloat:(Ljava/lang/String;)F
        //   115: fstore          4
        //   117: aload_1        
        //   118: invokevirtual   java/io/FileInputStream.close:()V
        //   121: iload_2        
        //   122: iload_3        
        //   123: if_icmple       235
        //   126: iload_2        
        //   127: i2f            
        //   128: fload           4
        //   130: fdiv           
        //   131: iload_3        
        //   132: i2f            
        //   133: fload           5
        //   135: fdiv           
        //   136: fdiv           
        //   137: fstore          4
        //   139: iload_2        
        //   140: iconst_2       
        //   141: idiv           
        //   142: istore_2       
        //   143: fload           5
        //   145: ldc_w           0.4
        //   148: fmul           
        //   149: f2i            
        //   150: istore_3       
        //   151: new             Landroid/graphics/Matrix;
        //   154: dup            
        //   155: invokespecial   android/graphics/Matrix.<init>:()V
        //   158: astore_1       
        //   159: aload_1        
        //   160: fconst_1       
        //   161: fload           4
        //   163: iload_2        
        //   164: i2f            
        //   165: iload_3        
        //   166: i2f            
        //   167: invokevirtual   android/graphics/Matrix.setScale:(FFFF)V
        //   170: aload_0        
        //   171: getfield        com/netflix/mediaclient/ui/common/MediaPlayerWrapper.mTextureView:Landroid/view/TextureView;
        //   174: aload_1        
        //   175: invokevirtual   android/view/TextureView.setTransform:(Landroid/graphics/Matrix;)V
        //   178: aload_0        
        //   179: invokevirtual   com/netflix/mediaclient/ui/common/MediaPlayerWrapper.initializeMediaPlayer:()V
        //   182: return         
        //   183: astore_1       
        //   184: fconst_0       
        //   185: fstore          4
        //   187: fconst_0       
        //   188: fstore          5
        //   190: ldc_w           "SPY-9199 Failed to retrieve MediaMetadata"
        //   193: invokestatic    com/netflix/mediaclient/service/logging/error/ErrorLoggingManager.logHandledException:(Ljava/lang/String;)V
        //   196: aload_1        
        //   197: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   200: goto            121
        //   203: astore_1       
        //   204: fconst_0       
        //   205: fstore          4
        //   207: goto            190
        //   210: astore_1       
        //   211: goto            190
        //   214: astore_1       
        //   215: fconst_0       
        //   216: fstore          4
        //   218: fconst_0       
        //   219: fstore          5
        //   221: goto            190
        //   224: astore_1       
        //   225: fconst_0       
        //   226: fstore          4
        //   228: goto            190
        //   231: astore_1       
        //   232: goto            190
        //   235: fconst_1       
        //   236: fstore          4
        //   238: goto            139
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  54     105    214    224    Ljava/io/IOException;
        //  54     105    183    190    Ljava/lang/IllegalStateException;
        //  105    117    224    231    Ljava/io/IOException;
        //  105    117    203    210    Ljava/lang/IllegalStateException;
        //  117    121    231    235    Ljava/io/IOException;
        //  117    121    210    214    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 128, Size: 128
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean onSurfaceTextureDestroyed(final SurfaceTexture surfaceTexture) {
        if (Log.isLoggable()) {
            Log.v("MediaPlayerWrapper", "billboard: SurfaceTexture Destroyed, releasing Media Player");
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
            return;
        }
        Log.v("MediaPlayerWrapper", "billboard: video has no width or height. width: " + n + " height: " + n2);
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
        this.releaseResources(null);
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
