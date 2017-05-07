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
    private JPlayer2$DecoderListener mDecoderListener;
    private VideoResolutionRange mMaxVideoRes;
    private long mNativePlayer;
    private volatile int mState;
    private Surface mSurface;
    private MediaDecoderBase mVideoPipe;
    
    public JPlayer2(final Surface mSurface) {
        this.mState = -1;
        this.mDecoderListener = new JPlayer2$DecoderListener(this);
        this.mSurface = mSurface;
    }
    
    public JPlayer2(final Surface surface, final MediaCrypto mCrypto) {
        this(surface);
        this.mCrypto = mCrypto;
    }
    
    private void configureAudioPipe() {
        Log.d("NF_JPlayer2", "configureAudioPipe");
        final MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", "audio/mp4a-latm");
        mediaFormat.setInteger("max-input-size", 1536);
        mediaFormat.setInteger("channel-count", 2);
        mediaFormat.setInteger("sample-rate", 48000);
        mediaFormat.setInteger("is-adts", 1);
        this.mAudioPipe = new MediaDecoder2Audio(new JPlayer2$MediaDataSource(this, true), "audio/mp4a-latm", mediaFormat, this.mDecoderListener);
    }
    
    private void configureVideoPipe() {
        int n = 1920;
        Log.d("NF_JPlayer2", "configureVideoPipe");
        if (DrmManagerRegistry.isWidevineDrmAllowed() && this.mCrypto == null) {
            this.mCrypto = DrmManagerRegistry.getWidevineMediaDrmEngine().getMediaCrypto();
        }
        final MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", "video/avc");
        if (AndroidUtils.getAndroidVersion() > 18) {
            final Pair<Integer, Integer> requiredMaximumResolution = AdaptiveMediaDecoderHelper.getRequiredMaximumResolution(this.mMaxVideoRes, this.mCrypto != null);
            final int intValue = (int)requiredMaximumResolution.first;
            if (intValue <= 1920) {
                n = intValue;
            }
            int intValue2;
            if ((intValue2 = (int)requiredMaximumResolution.second) > 1080) {
                intValue2 = 1080;
            }
            if (Log.isLoggable("NF_JPlayer2", 3)) {
                Log.d("NF_JPlayer2", "video max resolution is " + requiredMaximumResolution.first + " x " + requiredMaximumResolution.second);
                Log.d("NF_JPlayer2", "video real resolution is " + n + " x " + intValue2);
            }
            mediaFormat.setInteger("max-width", n);
            mediaFormat.setInteger("max-height", intValue2);
            mediaFormat.setInteger("width", n);
            mediaFormat.setInteger("height", intValue2);
        }
        else {
            mediaFormat.setInteger("max-input-size", 1048576);
            mediaFormat.setInteger("width", 1920);
            mediaFormat.setInteger("height", 1080);
        }
        if (this.mVideoPipe == null) {
            this.mVideoPipe = new MediaDecoder2Video(new JPlayer2$MediaDataSource(this, false), "video/avc", mediaFormat, this.mSurface, this.mCrypto, this.mDecoderListener);
            Log.d("NF_JPlayer2", "video pipe is ready");
            return;
        }
        Log.d("NF_JPlayer2", "video pipe is not ready, wait...");
    }
    
    private void getBuffer(final byte[] array, final boolean b, final MediaDecoderBase$InputDataSource$BufferMeta mediaDecoderBase$InputDataSource$BufferMeta) {
        synchronized (this) {
            if (this.mState != 0) {
                this.nativeGetBuffer(array, b, mediaDecoderBase$InputDataSource$BufferMeta);
            }
        }
    }
    
    private void getBufferDirect(final ByteBuffer byteBuffer, final boolean b, final MediaDecoderBase$InputDataSource$BufferMeta mediaDecoderBase$InputDataSource$BufferMeta) {
        synchronized (this) {
            if (this.mState != 0) {
                this.nativeGetBufferDirect(byteBuffer, b, mediaDecoderBase$InputDataSource$BufferMeta);
            }
        }
    }
    
    private native void nativeGetBuffer(final byte[] p0, final boolean p1, final MediaDecoderBase$InputDataSource$BufferMeta p2);
    
    private native void nativeGetBufferDirect(final ByteBuffer p0, final boolean p1, final MediaDecoderBase$InputDataSource$BufferMeta p2);
    
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
}
