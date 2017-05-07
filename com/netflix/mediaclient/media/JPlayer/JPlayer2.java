// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.util.Pair;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.configuration.drm.DrmManagerRegistry;
import android.media.MediaFormat;
import com.netflix.mediaclient.Log;
import java.nio.ByteBuffer;
import android.view.Surface;
import com.netflix.mediaclient.media.VideoResolutionRange;
import android.media.MediaCrypto;
import android.annotation.SuppressLint;

@SuppressLint({ "InlinedApi" })
public class JPlayer2
{
    static final int INIT_ERROR = -1;
    static final int STATE_FLUSHED = 3;
    static final int STATE_INIT = -1;
    static final int STATE_PAUSED = 2;
    static final int STATE_PLAYING = 1;
    static final int STATE_STOPPED = 0;
    private static final String TAG = "NF_JPlayer2";
    private MediaDecoderBase mAudioPipe;
    private MediaCrypto mCrypto;
    private DecoderListener mDecoderListener;
    private VideoResolutionRange mMaxVideoRes;
    private long mNativePlayer;
    private volatile int mState;
    private Surface mSurface;
    private MediaDecoderBase mVideoPipe;
    
    public JPlayer2(final Surface mSurface) {
        this.mState = -1;
        this.mDecoderListener = new DecoderListener();
        this.mSurface = mSurface;
    }
    
    public JPlayer2(final Surface surface, final MediaCrypto mCrypto) {
        this(surface);
        this.mCrypto = mCrypto;
    }
    
    private void configureAudioPipe() throws Exception {
        Log.d("NF_JPlayer2", "configureAudioPipe");
        final MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", "audio/mp4a-latm");
        mediaFormat.setInteger("max-input-size", 1536);
        mediaFormat.setInteger("channel-count", 2);
        mediaFormat.setInteger("sample-rate", 48000);
        mediaFormat.setInteger("is-adts", 1);
        this.mAudioPipe = new MediaDecoder2Audio(new MediaDataSource(true), "audio/mp4a-latm", mediaFormat, this.mDecoderListener);
    }
    
    private void configureVideoPipe() throws Exception {
        Log.d("NF_JPlayer2", "configureVideoPipe");
        if (DrmManagerRegistry.isWidevineDrmAllowed() && this.mCrypto == null) {
            this.mCrypto = DrmManagerRegistry.getWidevineMediaDrmEngine().getMediaCrypto();
        }
        final MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", "video/avc");
        if (AndroidUtils.getAndroidVersion() > 18) {
            final Pair<Integer, Integer> requiredMaximumResolution = AdaptiveMediaDecoderHelper.getRequiredMaximumResolution(this.mMaxVideoRes, this.mCrypto != null);
            int intValue;
            if ((intValue = (int)requiredMaximumResolution.first) > 1920) {
                intValue = 1920;
            }
            int intValue2;
            if ((intValue2 = (int)requiredMaximumResolution.second) > 1080) {
                intValue2 = 1080;
            }
            if (Log.isLoggable("NF_JPlayer2", 3)) {
                Log.d("NF_JPlayer2", "video max resolution is " + requiredMaximumResolution.first + " x " + requiredMaximumResolution.second);
                Log.d("NF_JPlayer2", "video real resolution is " + intValue + " x " + intValue2);
            }
            mediaFormat.setInteger("max-width", intValue);
            mediaFormat.setInteger("max-height", intValue2);
            mediaFormat.setInteger("width", intValue);
            mediaFormat.setInteger("height", intValue2);
        }
        else {
            mediaFormat.setInteger("max-input-size", 1048576);
            mediaFormat.setInteger("width", 1920);
            mediaFormat.setInteger("height", 1080);
        }
        if (this.mVideoPipe == null) {
            this.mVideoPipe = new MediaDecoder2Video(new MediaDataSource(false), "video/avc", mediaFormat, this.mSurface, this.mCrypto, this.mDecoderListener);
            Log.d("NF_JPlayer2", "video pipe is ready");
            return;
        }
        Log.d("NF_JPlayer2", "video pipe is not ready, wait...");
    }
    
    private void getBuffer(final byte[] array, final boolean b, final InputDataSource.BufferMeta bufferMeta) {
        synchronized (this) {
            if (this.mState != 0) {
                this.nativeGetBuffer(array, b, bufferMeta);
            }
        }
    }
    
    private void getBufferDirect(final ByteBuffer byteBuffer, final boolean b, final InputDataSource.BufferMeta bufferMeta) {
        synchronized (this) {
            if (this.mState != 0) {
                this.nativeGetBufferDirect(byteBuffer, b, bufferMeta);
            }
        }
    }
    
    private native void nativeGetBuffer(final byte[] p0, final boolean p1, final InputDataSource.BufferMeta p2);
    
    private native void nativeGetBufferDirect(final ByteBuffer p0, final boolean p1, final InputDataSource.BufferMeta p2);
    
    private native long nativeGetPlayer();
    
    private native void nativeNotifyEndOfStream(final boolean p0);
    
    private native void nativeNotifyError(final int p0, final String p1);
    
    private native void nativeNotifyReady();
    
    private native void nativeReleasePlayer(final long p0);
    
    private native void nativeUpdatePosition(final boolean p0, final long p1);
    
    private void notifyEndOfStream(final boolean b) {
        synchronized (this) {
            if (this.mState != 0) {
                this.nativeNotifyEndOfStream(b);
            }
        }
    }
    
    private void notifyReady() {
        synchronized (this) {
            if (this.mState != 0) {
                this.nativeNotifyReady();
            }
        }
    }
    
    private void updatePosition(final boolean b, final long n) {
        synchronized (this) {
            if (this.mState != 0) {
                this.nativeUpdatePosition(b, n);
            }
        }
    }
    
    private void videoToBackground() {
        if (this.mVideoPipe != null) {
            this.mVideoPipe.stop();
        }
        this.mVideoPipe = null;
    }
    
    private void videoToForeground() {
        Log.d("NF_JPlayer2", "new surface, reconfigure decoder ");
        if (this.mCrypto != null) {
            this.mCrypto = DrmManagerRegistry.getWidevineMediaDrmEngine().renewMediaCrypto();
        }
        while (true) {
            try {
                this.configureVideoPipe();
                if (this.mVideoPipe.isDecoderCreated()) {
                    this.mVideoPipe.setReferenceClock(this.mAudioPipe.getClock());
                    this.mVideoPipe.start();
                    return;
                }
            }
            catch (Exception ex) {
                this.nativeNotifyError(-1, android.util.Log.getStackTraceString((Throwable)ex));
                continue;
            }
            break;
        }
        Log.e("NF_JPlayer2", "VideoDecoder initialization failed at PAUSED, exiting...");
        this.mVideoPipe = null;
    }
    
    public void Flush() {
        if (this.mVideoPipe != null) {
            this.mVideoPipe.flush();
        }
        if (this.mAudioPipe != null) {
            this.mAudioPipe.flush();
        }
        this.mState = 3;
        Log.d("NF_JPlayer2", "Flush called");
    }
    
    public void Pause() {
        if (this.mVideoPipe != null) {
            this.mVideoPipe.pause();
        }
        if (this.mAudioPipe != null) {
            this.mAudioPipe.pause();
        }
        this.mState = 2;
        Log.d("NF_JPlayer2", "Pause called");
    }
    
    public void Play() {
        switch (this.mState) {
            default: {
                Log.d("NF_JPlayer2", "can not call Play ");
                break;
            }
            case 2: {
                if (this.mVideoPipe != null) {
                    if (this.mAudioPipe != null) {
                        this.mAudioPipe.unpause();
                    }
                    this.mVideoPipe.unpause();
                }
                else {
                    this.videoToForeground();
                }
                this.mState = 1;
                break;
            }
            case -1: {
                if (this.mAudioPipe != null) {
                    this.mAudioPipe.setReferenceClock(this.mAudioPipe.getClock());
                    this.mAudioPipe.start();
                }
                else {
                    Log.e("NF_JPlayer2", "mAudioPipe is null");
                }
                if (this.mVideoPipe != null) {
                    if (this.mVideoPipe.isDecoderCreated()) {
                        this.mVideoPipe.setReferenceClock(this.mAudioPipe.getClock());
                        this.mVideoPipe.start();
                    }
                    else {
                        Log.e("NF_JPlayer2", "VideoDecoder initialization failed, exiting...");
                        this.mVideoPipe = null;
                    }
                }
                else {
                    Log.e("NF_JPlayer2", "mVideoPipe is null");
                }
                this.mState = 1;
                break;
            }
            case 3: {
                if (this.mAudioPipe != null) {
                    this.mAudioPipe.restart();
                }
                if (this.mVideoPipe != null) {
                    this.mVideoPipe.restart();
                }
                else {
                    this.videoToForeground();
                }
                this.mState = 1;
                break;
            }
        }
        Log.d("NF_JPlayer2", "Play called");
    }
    
    public void Start() {
        while (true) {
            if (this.mState != -1) {
                break Label_0045;
            }
            try {
                this.configureAudioPipe();
                this.configureVideoPipe();
                if (this.mAudioPipe != null && this.mCrypto != null && AndroidUtils.getAndroidVersion() == 19) {
                    this.mAudioPipe.enableAudioUseGetTimestampAPI();
                }
                Log.d("NF_JPlayer2", "Start called");
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.nativeNotifyError(-1, android.util.Log.getStackTraceString((Throwable)ex));
                continue;
            }
            break;
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
        this.mAudioPipe = null;
        if (this.mCrypto != null) {
            DrmManagerRegistry.getWidevineMediaDrmEngine().releaseMediaCrypto();
            this.mCrypto = null;
        }
        this.mState = 0;
        Log.d("NF_JPlayer2", "Stop called");
    }
    
    public long getNativePlayer() {
        return this.mNativePlayer = this.nativeGetPlayer();
    }
    
    public void release() {
        this.nativeReleasePlayer(this.mNativePlayer);
    }
    
    public void setMaxVideoHeight(final VideoResolutionRange mMaxVideoRes) {
        this.mMaxVideoRes = mMaxVideoRes;
    }
    
    public void updateSurface(final Surface mSurface) {
        Log.d("NF_JPlayer2", "updateSurface");
        if (this.mSurface == mSurface) {
            return;
        }
        this.mSurface = mSurface;
        if (this.mSurface == null) {
            this.videoToBackground();
            Log.d("NF_JPlayer2", "surface becomes null");
        }
        Log.d("NF_JPlayer2", "updateSurface done");
    }
    
    public class DecoderListener implements EventListener
    {
        @Override
        public void onDecoderReady(final boolean b) {
            // monitorenter(this)
            Label_0017: {
                if (!b) {
                    break Label_0017;
                }
                try {
                    Log.d("NF_JPlayer2", "AUDIO init'd");
                    return;
                    Log.d("NF_JPlayer2", "VIDEO init'd");
                    JPlayer2.this.notifyReady();
                }
                finally {
                }
                // monitorexit(this)
            }
        }
        
        @Override
        public void onDecoderStarted(final boolean b) {
            // monitorenter(this)
            Label_0017: {
                if (!b) {
                    break Label_0017;
                }
                try {
                    Log.d("NF_JPlayer2", "AUDIO ready");
                    return;
                    Log.d("NF_JPlayer2", "VIDEO ready");
                    JPlayer2.this.mAudioPipe.unpause();
                    JPlayer2.this.mVideoPipe.unpause();
                }
                finally {
                }
                // monitorexit(this)
            }
        }
        
        @Override
        public void onEndOfStream(final boolean b) {
            JPlayer2.this.notifyEndOfStream(b);
            if (b) {
                Log.d("NF_JPlayer2", "AUDIO END_OF_STREAM");
                return;
            }
            Log.d("NF_JPlayer2", "VIDEO END_OF_STREAM");
        }
        
        @Override
        public void onFlushed(final boolean b) {
            // monitorenter(this)
            Label_0024: {
                if (!b) {
                    break Label_0024;
                }
                try {
                    Log.d("NF_JPlayer2", "AUDIO flushed");
                    JPlayer2.this.notifyReady();
                    return;
                    Log.d("NF_JPlayer2", "VIDEO flushed");
                }
                finally {
                }
                // monitorexit(this)
            }
        }
        
        @Override
        public void onPasued(final boolean b) {
            // monitorenter(this)
            Label_0024: {
                if (!b) {
                    break Label_0024;
                }
                try {
                    Log.d("NF_JPlayer2", "AUDIO paused");
                    JPlayer2.this.notifyReady();
                    return;
                    Log.d("NF_JPlayer2", "VIDEO paused");
                }
                finally {
                }
                // monitorexit(this)
            }
        }
        
        @Override
        public void onSampleRendered(final boolean b, final long n, final long n2) {
            synchronized (this) {
                JPlayer2.this.updatePosition(b, n2);
                if (b) {}
            }
        }
    }
    
    public class MediaDataSource implements InputDataSource
    {
        static final boolean TYPE_AUDIO = true;
        static final boolean TYPE_VIDEO = false;
        private boolean mIsAudio;
        
        MediaDataSource(final boolean mIsAudio) {
            this.mIsAudio = mIsAudio;
        }
        
        @Override
        public BufferMeta onRequestData(final ByteBuffer byteBuffer) {
            final BufferMeta bufferMeta = new BufferMeta();
            bufferMeta.size = 0;
            bufferMeta.flags = 0;
            if (byteBuffer.isDirect()) {
                JPlayer2.this.getBufferDirect(byteBuffer, this.mIsAudio, bufferMeta);
            }
            else {
                Log.e("NF_JPlayer2", "WITH NON-DIRECT BYTEBUFFER");
                final byte[] array = byteBuffer.array();
                if (array == null) {
                    bufferMeta.size = 0;
                    bufferMeta.flags = 4;
                    Log.e("NF_JPlayer2", "can't get bytearray");
                    return bufferMeta;
                }
                JPlayer2.this.getBuffer(array, this.mIsAudio, bufferMeta);
            }
            byteBuffer.limit(bufferMeta.size);
            byteBuffer.position(0);
            return bufferMeta;
        }
    }
}
