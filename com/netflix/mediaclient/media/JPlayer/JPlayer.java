// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import java.util.Arrays;
import com.netflix.mediaclient.util.AndroidUtils;
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
    private JplayerListener mJplayerListener;
    VideoEventListener mListener;
    private long mNativePlayer;
    private volatile int mState;
    private Surface mSurface1;
    private Surface mSurface2;
    private SurfaceHolder mSurfaceHolder;
    private boolean mSwitchingPending;
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
        this.mListener = new VideoEventListener();
        this.mJPlayerConfig = mjPlayerConfig;
        final MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", "audio/mp4a-latm");
        mediaFormat.setInteger("max-input-size", 1536);
        mediaFormat.setInteger("channel-count", 2);
        mediaFormat.setInteger("sample-rate", 48000);
        while (true) {
            try {
                this.mAudioPipe = new AudioDecoderPipe(new AudioDataSource(), "audio/mp4a-latm", mediaFormat, null, "1", mjPlayerConfig);
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
        this.mListener = new VideoEventListener();
        this.mJPlayerConfig = mjPlayerConfig;
        final MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", "audio/mp4a-latm");
        mediaFormat.setInteger("max-input-size", 1536);
        mediaFormat.setInteger("channel-count", 2);
        mediaFormat.setInteger("sample-rate", 48000);
        while (true) {
            try {
                this.mAudioPipe = new AudioDecoderPipe(new AudioDataSource(), "audio/mp4a-latm", mediaFormat, null, "1", mjPlayerConfig);
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                final MediaFormat mediaFormat = new MediaFormat();
                mediaFormat.setString("mime", "video/avc");
            Label_0194_Outer:
                while (true) {
                    while (true) {
                        Label_0240: {
                            if (AndroidUtils.getAndroidVersion() <= 18) {
                                break Label_0240;
                            }
                            mediaFormat.setInteger("max-width", 720);
                            mediaFormat.setInteger("max-height", 480);
                            mediaFormat.setInteger("width", 720);
                            mediaFormat.setInteger("height", 480);
                            if (JPlayer.this.mVideoPipe1 != null && (JPlayer.this.mVideoPipe == JPlayer.this.mVideoPipe1 || !JPlayer.this.mVideoPipe1.isStopped())) {
                                break Label_0240;
                            }
                            Log.d("NF_JPlayer", "mVideoPipe1 is idle");
                            try {
                                JPlayer.this.mVideoPipe1 = new VideoDecoderPipe(new VideoDataSource(), "video/avc", mediaFormat, JPlayer.this.mSurface1, "1", JPlayer.this.mJPlayerConfig);
                                JPlayer.this.mVideoPipe = JPlayer.this.mVideoPipe1;
                                JPlayer.this.mInitialVideoInit = true;
                                JPlayer.this.mVideoPipe1.setEventListener((MediaDecoderPipe.EventListener)JPlayer.this.mListener);
                                if (JPlayer.this.mVideoPipe.isDecoderCreated()) {
                                    final MediaDecoderPipe.Clock clock = JPlayer.this.mAudioPipe.getClock();
                                    JPlayer.this.mVideoPipe.start();
                                    JPlayer.this.mVideoPipe.setReferenceClock(clock);
                                    return;
                                }
                                break Label_0194_Outer;
                                mediaFormat.setInteger("max-input-size", 163840);
                                mediaFormat.setInteger("width", 720);
                                mediaFormat.setInteger("height", 480);
                                continue Label_0194_Outer;
                            }
                            catch (Exception ex) {
                                Log.e("NF_JPlayer", Log.getStackTraceString((Throwable)ex));
                                if (!JPlayer.this.mInitialVideoInit) {
                                    JPlayer.this.nativeNotifyError(-2, Log.getStackTraceString((Throwable)ex));
                                }
                                JPlayer.this.mVideoPipe = null;
                                return;
                            }
                        }
                        if (JPlayer.this.mVideoPipe2 == null || (JPlayer.this.mVideoPipe != JPlayer.this.mVideoPipe2 && JPlayer.this.mVideoPipe2.isStopped())) {
                            Log.d("NF_JPlayer", "mVideoPipe2 is idle");
                            if (JPlayer.this.mSurface2 == null) {
                                if (JPlayer.this.mJplayerListener != null) {
                                    JPlayer.this.mSurface2 = JPlayer.this.mJplayerListener.onGetTextureSurface();
                                }
                                if (JPlayer.this.mSurface2 == null) {
                                    Log.d("NF_JPlayer", "TextureSurface is not ready, wait...");
                                    try {
                                        Thread.sleep(10L);
                                        continue Label_0194_Outer;
                                    }
                                    catch (InterruptedException ex3) {
                                        Log.d("NF_JPlayer", "configureVideoPipe interrupted");
                                        continue;
                                    }
                                }
                            }
                            try {
                                JPlayer.this.mVideoPipe2 = new VideoDecoderPipe(new VideoDataSource(), "video/avc", mediaFormat, JPlayer.this.mSurface2, "2", JPlayer.this.mJPlayerConfig);
                                JPlayer.this.mVideoPipe = JPlayer.this.mVideoPipe2;
                                JPlayer.this.mVideoPipe2.setEventListener((MediaDecoderPipe.EventListener)JPlayer.this.mListener);
                                continue;
                            }
                            catch (Exception ex2) {
                                JPlayer.this.mVideoPipe = null;
                                Log.e("NF_JPlayer", Log.getStackTraceString((Throwable)ex2));
                                return;
                            }
                        }
                        try {
                            Thread.sleep(50L);
                            Log.d("NF_JPlayer", "video pipe is not ready, wait...");
                            continue Label_0194_Outer;
                        }
                        catch (InterruptedException ex4) {
                            Log.d("NF_JPlayer", "configureVideoPipe interrupted");
                            continue;
                        }
                        break;
                    }
                    break;
                }
                Log.e("NF_JPlayer", "VideoDecoder initialization failed, exiting...");
                JPlayer.this.mVideoPipe = null;
            }
        }, "configure video pipe").start();
    }
    
    private native int nativeGetBuffer(final byte[] p0, final boolean p1, final InputBufInfo p2);
    
    private native int nativeGetBufferDirect(final ByteBuffer p0, final boolean p1, final InputBufInfo p2);
    
    private native long nativeGetPlayer();
    
    private native void nativeNotifyError(final int p0, final String p1);
    
    private native void nativeReleasePlayer(final long p0);
    
    private void setVideoCsd(byte[] array, final MediaFormat mediaFormat) {
        final InputBufInfo inputBufInfo = new InputBufInfo();
        final ByteBuffer wrap = ByteBuffer.wrap(array);
        inputBufInfo.mDataSize = array.length;
        if (1 == inputBufInfo.mFlags) {
            wrap.limit(inputBufInfo.mDataSize);
            wrap.position(0);
            array = new byte[inputBufInfo.mDataSize];
            wrap.get(array);
            int n;
            for (n = 1; n < inputBufInfo.mDataSize - 3 && (array[n] != 0 || array[n + 1] != 0 || array[n + 2] != 0 || array[n + 3] != 1); ++n) {}
            final ByteBuffer wrap2 = ByteBuffer.wrap(array, 0, n - 1);
            final ByteBuffer wrap3 = ByteBuffer.wrap(Arrays.copyOfRange(array, n, inputBufInfo.mDataSize - 1));
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
                this.configureVideoPipe();
                this.mState = 1;
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
    
    public void setJplayerListener(final JplayerListener mJplayerListener) {
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
    
    public class AudioDataSource implements InputDataSource
    {
        @Override
        public BufferMeta onRequestData(final ByteBuffer byteBuffer) {
            final BufferMeta bufferMeta = new BufferMeta();
            final InputBufInfo inputBufInfo = new InputBufInfo();
            int n;
            if (byteBuffer.isDirect()) {
                n = JPlayer.this.nativeGetBufferDirect(byteBuffer, true, inputBufInfo);
            }
            else {
                Log.e("NF_JPlayer", "WITH NON-DIRECT BYTEBUFFER");
                final byte[] array = byteBuffer.array();
                if (array == null) {
                    bufferMeta.size = 0;
                    bufferMeta.flags = 256;
                    Log.e("NF_JPlayer", "can't get bytearray");
                    return bufferMeta;
                }
                n = JPlayer.this.nativeGetBuffer(array, true, inputBufInfo);
            }
            assert n == inputBufInfo.mDataSize;
            bufferMeta.timestamp = inputBufInfo.mTimeStamp;
            bufferMeta.flags = inputBufInfo.mFlags;
            bufferMeta.size = inputBufInfo.mDataSize;
            byteBuffer.limit(inputBufInfo.mDataSize);
            byteBuffer.position(0);
            return bufferMeta;
        }
    }
    
    public class InputBufInfo
    {
        int mDataSize;
        long mDebug;
        int mFlags;
        long mTimeStamp;
    }
    
    public interface JplayerListener
    {
        Surface onGetTextureSurface();
        
        void onSurface2Visibility(final boolean p0);
    }
    
    public class VideoDataSource implements InputDataSource
    {
        @Override
        public BufferMeta onRequestData(final ByteBuffer byteBuffer) {
            final BufferMeta bufferMeta = new BufferMeta();
            final InputBufInfo inputBufInfo = new InputBufInfo();
            int n;
            if (byteBuffer.isDirect()) {
                n = JPlayer.this.nativeGetBufferDirect(byteBuffer, false, inputBufInfo);
            }
            else {
                Log.e("NF_JPlayer", "WITH NON-DIRECT BYTEBUFFER");
                final byte[] array = byteBuffer.array();
                if (array == null) {
                    bufferMeta.size = 0;
                    bufferMeta.flags = 256;
                    Log.e("NF_JPlayer", "can't get bytearray");
                    return bufferMeta;
                }
                n = JPlayer.this.nativeGetBuffer(array, false, inputBufInfo);
            }
            assert n == inputBufInfo.mDataSize;
            bufferMeta.offset = 0;
            bufferMeta.timestamp = inputBufInfo.mTimeStamp;
            Label_0235: {
                if ((inputBufInfo.mFlags & 0x4) == 0x0) {
                    break Label_0235;
                }
                Label_0204: {
                    if (!JPlayer.this.mEnablePlatformDrs) {
                        break Label_0204;
                    }
                    bufferMeta.flags = 1;
                    bufferMeta.size = inputBufInfo.mDataSize;
                Label_0196_Outer:
                    while (true) {
                        if (byteBuffer.capacity() < bufferMeta.size) {
                            bufferMeta.size = byteBuffer.capacity();
                        }
                        while (true) {
                            try {
                                byteBuffer.limit(bufferMeta.size);
                                byteBuffer.position(0);
                                return bufferMeta;
                                bufferMeta.flags = inputBufInfo.mFlags;
                                bufferMeta.size = inputBufInfo.mDataSize;
                                continue Label_0196_Outer;
                                bufferMeta.flags = 256;
                                bufferMeta.size = 0;
                                JPlayer.this.configureVideoPipe();
                                JPlayer.this.mSwitchingPending = true;
                                continue Label_0196_Outer;
                            }
                            catch (IllegalArgumentException ex) {
                                bufferMeta.size = 0;
                                continue;
                            }
                            break;
                        }
                        break;
                    }
                }
            }
        }
    }
    
    public class VideoEventListener implements EventListener
    {
        @Override
        public void onDecoderStarted() {
            if (JPlayer.this.mAudioPipe != null && JPlayer.this.mAudioPipe.isPauseded()) {
                JPlayer.this.mAudioPipe.unpause();
            }
        }
        
        @Override
        public void onStartRender() {
            if (JPlayer.this.mVideoPipe != JPlayer.this.mVideoPipe1) {
                Log.d("NF_JPlayer", "mVideoPipe2 is current");
                if (JPlayer.this.mJplayerListener != null) {
                    JPlayer.this.mJplayerListener.onSurface2Visibility(true);
                }
                if (JPlayer.this.mVideoPipe1 != null && !JPlayer.this.mVideoPipe1.isStopped()) {
                    JPlayer.this.mVideoPipe1.stop();
                }
                JPlayer.this.mVideoPipe1 = null;
                return;
            }
            Log.d("NF_JPlayer", "mVideoPipe1 is current");
            if (JPlayer.this.mJplayerListener != null) {
                JPlayer.this.mJplayerListener.onSurface2Visibility(false);
            }
            if (JPlayer.this.mVideoPipe2 != null && !JPlayer.this.mVideoPipe2.isStopped()) {
                JPlayer.this.mVideoPipe2.stop();
            }
            JPlayer.this.mVideoPipe2 = null;
        }
        
        @Override
        public void onStop() {
        }
    }
}
