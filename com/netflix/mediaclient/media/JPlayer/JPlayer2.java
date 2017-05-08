// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.util.Pair;
import com.netflix.mediaclient.util.AndroidUtils;
import android.media.MediaFormat;
import com.netflix.mediaclient.service.configuration.drm.DrmManagerRegistry;
import com.netflix.mediaclient.util.MediaDrmUtils;
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
    private boolean mDolbyDigitalPlusDecoderPresent;
    private boolean mErrorReported;
    private VideoResolutionRange mMaxVideoRes;
    private long mNativePlayer;
    private volatile int mState;
    private Surface mSurface;
    private MediaDecoderBase mVideoPipe;
    
    public JPlayer2(final Surface mSurface) {
        this.mState = -1;
        this.mDolbyDigitalPlusDecoderPresent = DolbyDigitalHelper.isEAC3supported();
        this.mErrorReported = false;
        this.mDecoderListener = new JPlayer2$DecoderListener(this);
        this.mSurface = mSurface;
    }
    
    public JPlayer2(final Surface surface, final MediaCrypto mCrypto) {
        this(surface);
        this.mCrypto = mCrypto;
    }
    
    private void configureAudioPipe() {
        Log.d("NF_JPlayer2", "configureAudioPipe");
        this.createAACDecoder();
    }
    
    private void configureVideoPipe() {
        int n = 1920;
        Log.d("NF_JPlayer2", "configureVideoPipe");
        if (MediaDrmUtils.isWidevineDrmAllowed() && this.mCrypto == null) {
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
            if (Log.isLoggable()) {
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
    
    private void createAACDecoder() {
        Log.d("NF_JPlayer2", "createAACDecoder");
        final MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", "audio/mp4a-latm");
        mediaFormat.setInteger("max-input-size", 1536);
        mediaFormat.setInteger("channel-count", 2);
        mediaFormat.setInteger("sample-rate", 48000);
        mediaFormat.setInteger("is-adts", 1);
        this.mAudioPipe = new MediaDecoder2Audio(new JPlayer2$MediaDataSource(this, true), "audio/mp4a-latm", mediaFormat, this.mDecoderListener);
        Log.d("NF_JPlayer2", "createAACDecoder done");
    }
    
    private void createDDPlusDecoder() {
        Log.d("NF_JPlayer2", "createDDPlusDecoder");
        this.mAudioPipe = new MediaDecoder2Audio(new JPlayer2$MediaDataSource(this, true), "audio/eac3", DolbyDigitalHelper.getMediaFormatEAC3(), this.mDecoderListener);
        Log.d("NF_JPlayer2", "createDDPlusDecoder done");
    }
    
    private void createDDPlusPassthrough() {
        Log.d("NF_JPlayer2", "createDDPlusPassthrought");
        this.mAudioPipe = null;
        Log.d("NF_JPlayer2", "createDDPlusPassthrough, not supported");
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
    
    private boolean isAudioPipeNeedReconfig(final String s) {
        final boolean contains = s.contains("ec-3");
        boolean b;
        if (this.mAudioPipe != null && "audio/eac3".equals(this.mAudioPipe.getMime())) {
            b = true;
        }
        else {
            b = false;
        }
        final boolean b2 = this.mAudioPipe != null && "audio/mp4a-latm".equals(this.mAudioPipe.getMime());
        if (contains) {
            if (!this.isDDPlocal() || !b) {
                return true;
            }
        }
        else if (!b2) {
            return true;
        }
        return false;
    }
    
    private boolean isDDPlocal() {
        return this.mDolbyDigitalPlusDecoderPresent;
    }
    
    private boolean isDDPpassthrough() {
        return false;
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
    
    private boolean reconfigureAudioPipe(final boolean b) {
        Log.d("NF_JPlayer2", "reconfigureAudioPipe");
        if (b) {
            if (this.isDDPpassthrough()) {
                this.createDDPlusPassthrough();
            }
            else {
                if (!this.isDDPlocal()) {
                    return false;
                }
                this.createDDPlusDecoder();
            }
        }
        else {
            this.createAACDecoder();
        }
        return true;
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
            this.mVideoPipe.pause();
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
                this.mDecoderListener.onError(false, -1, android.util.Log.getStackTraceString((Throwable)ex));
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
        if (AndroidUtils.getAndroidVersion() >= 19 && !AdaptiveMediaDecoderHelper.isAvcDecoderSupportsAdaptivePlayback()) {
            this.videoToBackground();
            Log.d("NF_JPlayer2", "Video pipe cleared");
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
                    this.mAudioPipe.unpause();
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
                    this.mAudioPipe.unpause();
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
                this.mDecoderListener.onError(false, -1, android.util.Log.getStackTraceString((Throwable)ex));
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
    
    public boolean isDDPsupported() {
        final boolean b = this.isDDPpassthrough() || this.isDDPlocal();
        if (Log.isLoggable()) {
            Log.d("NF_JPlayer2", "dd+ support is " + b);
        }
        return b;
    }
    
    public void release() {
        this.nativeReleasePlayer(this.mNativePlayer);
    }
    
    public void setAudioDuck(final boolean audioDuck) {
        if (this.mAudioPipe != null && this.mAudioPipe instanceof MediaDecoder2Audio) {
            ((MediaDecoder2Audio)this.mAudioPipe).setAudioDuck(audioDuck);
        }
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
