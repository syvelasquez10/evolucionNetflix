// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

import java.nio.ByteBuffer;
import java.math.BigInteger;
import java.util.Random;
import android.util.Log;
import android.view.Surface;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.media.MediaPlayer$OnInfoListener;
import android.media.MediaPlayer$OnVideoSizeChangedListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.media.MediaPlayer$OnBufferingUpdateListener;

public class NetflixAndroidMediaPlayer implements MediaPlayer$OnBufferingUpdateListener, MediaPlayer$OnCompletionListener, MediaPlayer$OnPreparedListener, MediaPlayer$OnVideoSizeChangedListener, MediaPlayer$OnInfoListener, InputDataListener
{
    private static final int STREAM_PORT_BASE = 40000;
    private static final int STREAM_PORT_RANGE = 1000;
    private static final String TAG = "NF_AndroidMediaPlayer";
    private String mFileName;
    private SurfaceHolder mHolder;
    private boolean mIsVideoReadyToBePlayed;
    private boolean mIsVideoSizeKnown;
    private MediaPlayer mMediaPlayer;
    private long mNativePlayer;
    private boolean mNeedPrepareMediaPlayer;
    private NetflixHttpLocalServer mNetflixHttpLocalServer;
    private Thread mNetflixHttpLocalServerThread;
    private int mPort;
    private Surface mSurface;
    private int mVideoHeight;
    private int mVideoWidth;
    private String path;
    
    public NetflixAndroidMediaPlayer(final Surface mSurface) {
        this.mIsVideoSizeKnown = false;
        this.mIsVideoReadyToBePlayed = false;
        this.mNeedPrepareMediaPlayer = true;
        Log.v("NF_AndroidMediaPlayer", "Constructing... ");
        this.initState();
        final Random random = new Random();
        this.mPort = 40000 + random.nextInt(1000);
        this.mNetflixHttpLocalServer = new NetflixHttpLocalServer(this.mPort);
        (this.mNetflixHttpLocalServerThread = new Thread(this.mNetflixHttpLocalServer)).start();
        this.mNetflixHttpLocalServer.setInputDataListener((NetflixHttpLocalServer.InputDataListener)this);
        this.mSurface = mSurface;
        this.mFileName = new BigInteger(256, random).toString(16);
        Log.v("NF_AndroidMediaPlayer", "Constructed ");
    }
    
    public NetflixAndroidMediaPlayer(final SurfaceHolder mHolder) {
        this.mIsVideoSizeKnown = false;
        this.mIsVideoReadyToBePlayed = false;
        this.mNeedPrepareMediaPlayer = true;
        Log.v("NF_AndroidMediaPlayer", "Constructing... ");
        this.initState();
        final Random random = new Random();
        this.mPort = 40000 + random.nextInt(1000);
        this.mNetflixHttpLocalServer = new NetflixHttpLocalServer(this.mPort);
        (this.mNetflixHttpLocalServerThread = new Thread(this.mNetflixHttpLocalServer)).start();
        this.mNetflixHttpLocalServer.setInputDataListener((NetflixHttpLocalServer.InputDataListener)this);
        this.mHolder = mHolder;
        this.mFileName = new BigInteger(256, random).toString(16);
        Log.v("NF_AndroidMediaPlayer", "Constructed ");
    }
    
    private void doFlush() {
        while (true) {
            while (true) {
                synchronized (this) {
                    this.mNeedPrepareMediaPlayer = true;
                    if (this.mMediaPlayer != null) {
                        this.mMediaPlayer.reset();
                        Log.d("NF_AndroidMediaPlayer", "doFlush.reset done");
                    }
                    else {
                        Log.d("NF_AndroidMediaPlayer", "doFlush.reset NOT done, mMediaPlayer is null!");
                    }
                    if (this.mNetflixHttpLocalServerThread != null) {
                        this.mNetflixHttpLocalServerThread.interrupt();
                        this.initState();
                        Log.d("NF_AndroidMediaPlayer", "doFlush done");
                        return;
                    }
                }
                Log.d("NF_AndroidMediaPlayer", "mNetflixHttpLocalServerThread is null!");
                continue;
            }
        }
    }
    
    private void doPause() {
        synchronized (this) {
            if (this.mMediaPlayer != null) {
                this.mMediaPlayer.pause();
                Log.d("NF_AndroidMediaPlayer", "doPause done");
            }
            else {
                Log.d("NF_AndroidMediaPlayer", "doPause mMediaPlayer is null!");
            }
        }
    }
    
    private void doPlay() {
        try {
            this.path = new String("http://127.0.0.1:");
            this.path = this.path.concat(Integer.toString(this.mPort));
            this.path = this.path.concat("/");
            this.path = this.path.concat(this.mFileName);
            Log.v("NF_AndroidMediaPlayer", "PLAY: " + this.path);
            if (this.path == "") {
                Log.e("NF_AndroidMediaPlayer", "path is not valid");
                return;
            }
            if (this.mMediaPlayer == null) {
                this.mNetflixHttpLocalServer.start(this.mFileName);
                (this.mMediaPlayer = new MediaPlayer()).setDataSource(this.path);
                if (this.mHolder != null) {
                    Log.e("NF_AndroidMediaPlayer", "setting surfaceHolder");
                    this.mMediaPlayer.setDisplay(this.mHolder);
                }
                if (this.mSurface != null) {
                    Log.e("NF_AndroidMediaPlayer", "setting surface");
                }
                Log.e("NF_AndroidMediaPlayer", "setting surface 1");
                this.mMediaPlayer.setOnBufferingUpdateListener((MediaPlayer$OnBufferingUpdateListener)this);
                this.mMediaPlayer.setOnCompletionListener((MediaPlayer$OnCompletionListener)this);
                this.mMediaPlayer.setOnPreparedListener((MediaPlayer$OnPreparedListener)this);
                this.mMediaPlayer.setOnVideoSizeChangedListener((MediaPlayer$OnVideoSizeChangedListener)this);
                this.mMediaPlayer.setAudioStreamType(3);
                Log.e("NF_AndroidMediaPlayer", "setting surface 2");
                this.mMediaPlayer.prepareAsync();
                this.mNeedPrepareMediaPlayer = false;
                Log.v("NF_AndroidMediaPlayer", "new MediaPlayer");
                return;
            }
        }
        catch (Exception ex) {
            Log.e("NF_AndroidMediaPlayer", "error: " + ex.getMessage(), (Throwable)ex);
            return;
        }
        if (this.mNeedPrepareMediaPlayer) {
            this.mNetflixHttpLocalServer.start(this.mFileName);
            Log.v("NF_AndroidMediaPlayer", "preparing MediaPlayer");
            this.mMediaPlayer.setDataSource(this.path);
            if (this.mHolder != null) {
                Log.e("NF_AndroidMediaPlayer", "setting surfaceHolder XXX");
                this.mMediaPlayer.setDisplay(this.mHolder);
            }
            if (this.mSurface != null) {
                Log.e("NF_AndroidMediaPlayer", "setting surface XXX");
                this.mMediaPlayer.setSurface(this.mSurface);
            }
            this.mMediaPlayer.setOnBufferingUpdateListener((MediaPlayer$OnBufferingUpdateListener)this);
            this.mMediaPlayer.setOnCompletionListener((MediaPlayer$OnCompletionListener)this);
            this.mMediaPlayer.setOnPreparedListener((MediaPlayer$OnPreparedListener)this);
            this.mMediaPlayer.setOnVideoSizeChangedListener((MediaPlayer$OnVideoSizeChangedListener)this);
            this.mMediaPlayer.setOnInfoListener((MediaPlayer$OnInfoListener)this);
            this.mMediaPlayer.setAudioStreamType(3);
            this.mMediaPlayer.prepareAsync();
            this.mNeedPrepareMediaPlayer = false;
            Log.v("NF_AndroidMediaPlayer", "prepare MediaPlayer");
        }
        else {
            this.startVideoPlayback();
        }
        Log.v("NF_AndroidMediaPlayer", "doPlay() done");
    }
    
    private void doStop() {
        synchronized (this) {
            Log.d("NF_AndroidMediaPlayer", "doStop: stop LocalServer");
            if (this.mNetflixHttpLocalServerThread != null) {
                this.mNetflixHttpLocalServerThread.interrupt();
                this.mNetflixHttpLocalServer.stop();
            }
            else {
                Log.d("NF_AndroidMediaPlayer", "mNetflixHttpLocalServerThread is null!");
            }
            Log.d("NF_AndroidMediaPlayer", "doStop: stop AndroidMediaPlayer");
            this.initState();
            Log.d("NF_AndroidMediaPlayer", "doStop done");
        }
    }
    
    private void initState() {
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        this.mIsVideoReadyToBePlayed = false;
        this.mIsVideoSizeKnown = false;
    }
    
    private native int nativeGetBuffer(final ByteBuffer p0, final long p1);
    
    private native long nativeGetPlayer();
    
    private native void nativeReleasePlayer(final long p0);
    
    private void releaseMediaPlayer() {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
    }
    
    private void startVideoPlayback() {
        Log.v("NF_AndroidMediaPlayer", "startVideoPlayback");
        this.mMediaPlayer.start();
    }
    
    public void Flush() {
        Log.d("NF_AndroidMediaPlayer", "Flush called");
        this.doFlush();
    }
    
    public int GetPTS() {
        int currentPosition;
        final int n = currentPosition = -1;
        if (this.mIsVideoReadyToBePlayed) {
            currentPosition = n;
            if (this.mIsVideoSizeKnown) {
                currentPosition = n;
                if (this.mMediaPlayer != null) {
                    currentPosition = this.mMediaPlayer.getCurrentPosition();
                }
            }
        }
        return currentPosition;
    }
    
    public void Pause() {
        Log.d("NF_AndroidMediaPlayer", "Pause called");
        this.doPause();
    }
    
    public void Play() {
        Log.d("NF_AndroidMediaPlayer", "Play called");
        this.doPlay();
    }
    
    public void Stop() {
        Log.d("NF_AndroidMediaPlayer", "Stop called");
        this.doStop();
    }
    
    public long getNativePlayer() {
        return this.mNativePlayer = this.nativeGetPlayer();
    }
    
    public void onBufferingUpdate(final MediaPlayer mediaPlayer, final int n) {
        if (Log.isLoggable("NF_AndroidMediaPlayer", 3)) {
            Log.d("NF_AndroidMediaPlayer", "onBufferingUpdate percent:" + n);
        }
    }
    
    public void onCompletion(final MediaPlayer mediaPlayer) {
        Log.d("NF_AndroidMediaPlayer", "onCompletion called");
    }
    
    public boolean onInfo(final MediaPlayer mediaPlayer, final int n, final int n2) {
        switch (n) {
            case 1: {
                Log.v("NF_AndroidMediaPlayer", "MEDIA_INFO_UNKNOWN");
                break;
            }
            case 700: {
                Log.v("NF_AndroidMediaPlayer", "MEDIA_INFO_VIDEO_TRACK_LAGGING");
                break;
            }
            case 701: {
                Log.v("NF_AndroidMediaPlayer", "MEDIA_INFO_BUFFERING_START");
                break;
            }
            case 702: {
                Log.v("NF_AndroidMediaPlayer", "MEDIA_INFO_BUFFERING_END");
                break;
            }
            case 800: {
                Log.v("NF_AndroidMediaPlayer", "MEDIA_INFO_BAD_INTERLEAVING");
                break;
            }
            case 801: {
                Log.v("NF_AndroidMediaPlayer", "MEDIA_INFO_NOT_SEEKABLE");
                break;
            }
            case 802: {
                Log.v("NF_AndroidMediaPlayer", "MEDIA_INFO_METADATA_UPDATE");
                break;
            }
        }
        return false;
    }
    
    public int onNetflixHttpLocalServerInputData(final ByteBuffer byteBuffer, final long n) {
        return this.nativeGetBuffer(byteBuffer, n);
    }
    
    public void onPrepared(final MediaPlayer mediaPlayer) {
        Log.d("NF_AndroidMediaPlayer", "onPrepared called");
        this.mIsVideoReadyToBePlayed = true;
        if (this.mIsVideoReadyToBePlayed && this.mIsVideoSizeKnown) {
            this.startVideoPlayback();
        }
    }
    
    public void onVideoSizeChanged(final MediaPlayer mediaPlayer, final int mVideoWidth, final int mVideoHeight) {
        Log.v("NF_AndroidMediaPlayer", "onVideoSizeChanged called");
        if (mVideoWidth == 0 || mVideoHeight == 0) {
            Log.e("NF_AndroidMediaPlayer", "invalid video width(" + mVideoWidth + ") or height(" + mVideoHeight + ")");
        }
        else {
            this.mIsVideoSizeKnown = true;
            this.mVideoWidth = mVideoWidth;
            this.mVideoHeight = mVideoHeight;
            if (this.mHolder != null) {
                this.mHolder.setFixedSize(this.mVideoWidth, this.mVideoHeight);
            }
            if (this.mIsVideoReadyToBePlayed && this.mIsVideoSizeKnown) {
                this.startVideoPlayback();
            }
        }
    }
    
    public void release() {
        this.nativeReleasePlayer(this.mNativePlayer);
        this.releaseMediaPlayer();
        this.initState();
    }
}
