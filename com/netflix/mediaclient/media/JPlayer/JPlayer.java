// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import java.util.Arrays;
import java.nio.ByteBuffer;
import android.util.Log;
import android.media.MediaFormat;
import android.view.SurfaceHolder;
import android.view.Surface;
import org.json.JSONObject;
import android.annotation.TargetApi;

@TargetApi(16)
public class JPlayer
{
    static final int INIT_AUDIO_ERROR = -1;
    static final int INIT_VIDEO_ERROR = -2;
    static final int MAX_INPUT_SIZE = 163840;
    static final int RUNTIME_ERROR = -3;
    static final int SD_HEIGHT = 480;
    static final int SD_WIDTH = 720;
    static final int STATE_INIT = -1;
    static final int STATE_PAUSED = 2;
    static final int STATE_PLAYING = 1;
    static final int STATE_STOPPED = 0;
    private static final String TAG = "NF_JPlayer";
    private String mAudioErrStack;
    private MediaDecoderPipe mAudioPipe;
    private boolean mEnablePlatformDrs;
    private boolean mFlushed;
    private boolean mInitialAudioInit;
    private boolean mInitialVideoInit;
    private JSONObject mJPlayerConfig;
    private JPlayer$JplayerListener mJplayerListener;
    JPlayer$VideoEventListener mListener;
    private long mNativePlayer;
    private volatile int mState;
    private Surface mSurface1;
    private Surface mSurface2;
    private SurfaceHolder mSurfaceHolder;
    private boolean mSwitchingPending;
    private Thread mVideoConfigureThread;
    private MediaDecoderPipe mVideoPipe;
    private MediaDecoderPipe mVideoPipe1;
    private MediaDecoderPipe mVideoPipe2;
    
    public JPlayer(final Surface mSurface1, final JSONObject mjPlayerConfig) {
        this.mEnablePlatformDrs = false;
        this.mState = -1;
        this.mFlushed = false;
        this.mInitialVideoInit = false;
        this.mInitialAudioInit = false;
        this.mAudioErrStack = "";
        this.mJPlayerConfig = null;
        this.mListener = new JPlayer$VideoEventListener(this);
        this.mJPlayerConfig = mjPlayerConfig;
        final MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", "audio/mp4a-latm");
        mediaFormat.setInteger("max-input-size", 1536);
        mediaFormat.setInteger("channel-count", 2);
        mediaFormat.setInteger("sample-rate", 48000);
        while (true) {
            try {
                this.mAudioPipe = new AudioDecoderPipe(new JPlayer$AudioDataSource(this), "audio/mp4a-latm", mediaFormat, null, "1", mjPlayerConfig);
                this.mInitialAudioInit = true;
                this.mSurface1 = mSurface1;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.mAudioErrStack = Log.getStackTraceString((Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public JPlayer(final SurfaceHolder mSurfaceHolder, final JSONObject mjPlayerConfig) {
        this.mEnablePlatformDrs = false;
        this.mState = -1;
        this.mFlushed = false;
        this.mInitialVideoInit = false;
        this.mInitialAudioInit = false;
        this.mAudioErrStack = "";
        this.mJPlayerConfig = null;
        this.mListener = new JPlayer$VideoEventListener(this);
        this.mJPlayerConfig = mjPlayerConfig;
        final MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", "audio/mp4a-latm");
        mediaFormat.setInteger("max-input-size", 1536);
        mediaFormat.setInteger("channel-count", 2);
        mediaFormat.setInteger("sample-rate", 48000);
        while (true) {
            try {
                this.mAudioPipe = new AudioDecoderPipe(new JPlayer$AudioDataSource(this), "audio/mp4a-latm", mediaFormat, null, "1", mjPlayerConfig);
                this.mInitialAudioInit = true;
                this.mSurfaceHolder = mSurfaceHolder;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.mAudioErrStack = Log.getStackTraceString((Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    private void configureVideoPipe() {
        Log.d("NF_JPlayer", "start video pipe");
        this.makeSureConfigureThreadStopped();
        (this.mVideoConfigureThread = new Thread(new JPlayer$1(this), "configure video pipe")).setUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new JPlayer$2(this));
        this.mVideoConfigureThread.start();
    }
    
    private void makeSureConfigureThreadStopped() {
        if (this.mVideoConfigureThread == null) {
            return;
        }
        if (this.mVideoConfigureThread.isAlive()) {
            this.mVideoConfigureThread.interrupt();
        }
        try {
            this.mVideoConfigureThread.join();
        }
        catch (InterruptedException ex) {
            Log.d("NF_JPlayer", "configureVideoPipe is interrupted");
        }
    }
    
    private native int nativeGetBuffer(final byte[] p0, final boolean p1, final JPlayer$InputBufInfo p2);
    
    private native int nativeGetBufferDirect(final ByteBuffer p0, final boolean p1, final JPlayer$InputBufInfo p2);
    
    private native long nativeGetPlayer();
    
    private native void nativeNotifyError(final int p0, final String p1);
    
    private native void nativeReleasePlayer(final long p0);
    
    private void setVideoCsd(byte[] array, final MediaFormat mediaFormat) {
        final JPlayer$InputBufInfo player$InputBufInfo = new JPlayer$InputBufInfo(this);
        final ByteBuffer wrap = ByteBuffer.wrap(array);
        player$InputBufInfo.mDataSize = array.length;
        if (1 == player$InputBufInfo.mFlags) {
            wrap.limit(player$InputBufInfo.mDataSize);
            wrap.position(0);
            array = new byte[player$InputBufInfo.mDataSize];
            wrap.get(array);
            int n;
            for (n = 1; n < player$InputBufInfo.mDataSize - 3 && (array[n] != 0 || array[n + 1] != 0 || array[n + 2] != 0 || array[n + 3] != 1); ++n) {}
            final ByteBuffer wrap2 = ByteBuffer.wrap(array, 0, n - 1);
            final ByteBuffer wrap3 = ByteBuffer.wrap(Arrays.copyOfRange(array, n, player$InputBufInfo.mDataSize - 1));
            mediaFormat.setByteBuffer("csd-0", wrap2);
            if (Log.isLoggable("NF_JPlayer", 3)) {
                Log.d("NF_JPlayer", "csd-0: " + wrap2);
            }
            mediaFormat.setByteBuffer("csd-1", wrap3);
            if (Log.isLoggable("NF_JPlayer", 3)) {
                Log.d("NF_JPlayer", "csd-1: " + wrap3);
            }
        }
    }
    
    public void Flush() {
        if (this.mVideoPipe != null) {
            this.mVideoPipe.flush();
        }
        if (this.mVideoPipe != this.mVideoPipe1 && this.mVideoPipe1 != null) {
            this.mVideoPipe1.flush();
        }
        else if (this.mVideoPipe != this.mVideoPipe2 && this.mVideoPipe2 != null) {
            this.mVideoPipe2.flush();
        }
        if (this.mAudioPipe != null) {
            this.mAudioPipe.flush();
        }
        this.mFlushed = true;
        Log.d("NF_JPlayer", "Flush called");
    }
    
    public long GetPTS() {
        long value = -1L;
        if (this.mAudioPipe != null) {
            value = this.mAudioPipe.getClock().get();
        }
        return value;
    }
    
    public void Pause() {
        if (this.mVideoPipe != null) {
            this.mVideoPipe.pause();
        }
        if (this.mAudioPipe != null) {
            this.mAudioPipe.pause();
        }
        this.mState = 2;
        Log.d("NF_JPlayer", "Pause called");
    }
    
    public void Play() {
        switch (this.mState) {
            case 2: {
                if (!this.mFlushed && this.mAudioPipe != null) {
                    this.mAudioPipe.unpause();
                }
                if (this.mVideoPipe != null) {
                    this.mVideoPipe.unpause();
                }
                this.mState = 1;
                break;
            }
            case -1:
            case 0: {
                if (this.mAudioPipe != null) {
                    this.mAudioPipe.start();
                    this.mAudioPipe.setReferenceClock(this.mAudioPipe.getClock());
                    this.mAudioPipe.pause();
                }
                else {
                    Log.e("NF_JPlayer", "mAudioPipe is null");
                }
                this.mState = 1;
                this.configureVideoPipe();
                break;
            }
        }
        this.mFlushed = false;
        Log.d("NF_JPlayer", "Play called");
    }
    
    public void Start() {
        Log.d("NF_JPlayer", "Start called");
        if (!this.mInitialAudioInit) {
            this.nativeNotifyError(-1, this.mAudioErrStack);
        }
    }
    
    public void Stop() {
        if (this.mVideoPipe != null) {
            this.mVideoPipe.stop();
        }
        if (this.mAudioPipe != null) {
            this.mAudioPipe.stop();
        }
        this.mVideoPipe = null;
        this.mState = 0;
        this.mFlushed = false;
        this.makeSureConfigureThreadStopped();
        Log.d("NF_JPlayer", "Stop called");
    }
    
    public long getNativePlayer() {
        return this.mNativePlayer = this.nativeGetPlayer();
    }
    
    public void release() {
        this.nativeReleasePlayer(this.mNativePlayer);
        this.mJplayerListener = null;
    }
    
    public void setEnablePlatformDrs(final boolean mEnablePlatformDrs) {
        this.mEnablePlatformDrs = mEnablePlatformDrs;
        if (Log.isLoggable("NF_JPlayer", 3)) {
            Log.d("NF_JPlayer", "setEnablePlatformDrs: " + mEnablePlatformDrs);
        }
    }
    
    public void setJplayerListener(final JPlayer$JplayerListener mJplayerListener) {
        this.mJplayerListener = mJplayerListener;
        if (this.mJplayerListener == null) {
            Log.e("NF_JPlayer", "setJplayerListener mJplayerListener is null");
            return;
        }
        Log.d("NF_JPlayer", "setJplayerListener ");
        this.mJplayerListener.onSurface2Visibility(false);
    }
    
    public void setSurface(final Surface mSurface2) {
        Log.d("NF_JPlayer", "setSurface");
        this.mSurface2 = mSurface2;
    }
}
