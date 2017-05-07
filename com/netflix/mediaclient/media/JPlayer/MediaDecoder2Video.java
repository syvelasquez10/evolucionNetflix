// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import java.util.LinkedList;
import android.media.MediaCodec$BufferInfo;
import com.netflix.mediaclient.Log;
import java.util.concurrent.TimeUnit;
import android.media.MediaCrypto;
import android.view.Surface;
import android.media.MediaFormat;
import android.os.HandlerThread;
import android.os.Handler;
import android.annotation.TargetApi;

@TargetApi(16)
public class MediaDecoder2Video extends MediaDecoderPipe2
{
    private static final int DEFAULT_LOOPING_TIME = 30;
    private static final int FAST_LOOPING_TIME = 10;
    private static final long MAX_AHEAD_TIMED_RELEASE_MS = 500L;
    private static final int MAX_LOOPING_TIME = 50;
    private static final int MSG_RENDER_FLUSH = 2;
    private static final int MSG_RENDER_FRAME = 1;
    private static final int RENDER_SKIP = 30;
    private static final int RENDER_WHIGH = 20;
    private static final int SCHEDULE_OFFSET = 5;
    private static final String TAG = "MediaDecoder2Video";
    private volatile boolean mDecoderStopped;
    private boolean mFirstFrameRendered;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    MediaDecoder2Video$RenderHeartbeat mHearbeat;
    private boolean mLastFrameRendered;
    private boolean mPaused;
    private MediaDecoderPipe2$LocalStateNotifier mRenderState;
    private boolean mRendererStarted;
    private long nFrameRendered;
    private long nFrameSkipped;
    private long previousPts;
    
    public MediaDecoder2Video(final MediaDecoderBase$InputDataSource mediaDecoderBase$InputDataSource, final String s, final MediaFormat mediaFormat, final Surface surface, final MediaCrypto mediaCrypto, final MediaDecoderBase$EventListener mediaDecoderBase$EventListener) {
        super(mediaDecoderBase$InputDataSource, s, mediaFormat, surface, mediaCrypto, mediaDecoderBase$EventListener);
        this.nFrameRendered = 0L;
        this.nFrameSkipped = 0L;
        this.mRendererStarted = false;
        this.mLastFrameRendered = false;
        this.mFirstFrameRendered = false;
        this.mPaused = false;
        this.previousPts = -1L;
        this.mDecoderStopped = false;
        this.mRenderState = new MediaDecoderPipe2$LocalStateNotifier(this);
        this.mHearbeat = new MediaDecoder2Video$RenderHeartbeat(this);
    }
    
    private void removeFrameFromQ(final int n) {
        synchronized (this.mOutputBuffersQ) {
            this.mOutputBuffersQ.removeFirst();
            this.mOutputBufferInfo[n] = null;
        }
    }
    
    private void tryToReleaseBuffers() {
        long millis = 0L;
    Label_0183_Outer:
        while (true) {
        Label_0256_Outer:
            while (true) {
                final int intValue;
                long presentationTimeUs;
                final long value;
                synchronized (this.mOutputBuffersQ) {
                    if (this.mOutputBuffersQ.isEmpty()) {
                        return;
                    }
                    intValue = this.mOutputBuffersQ.peekFirst();
                    final MediaCodec$BufferInfo mediaCodec$BufferInfo = this.mOutputBufferInfo[intValue];
                    if (intValue == -1 || mediaCodec$BufferInfo == null || this.mRefClock == null) {
                        break Label_0183;
                    }
                    presentationTimeUs = mediaCodec$BufferInfo.presentationTimeUs;
                    value = this.mRefClock.get();
                    if (value < 0L) {
                        if (this.mHandler != null) {
                            this.mHandler.sendEmptyMessageDelayed(1, 30L);
                        }
                        return;
                    }
                }
                presentationTimeUs = TimeUnit.MICROSECONDS.toNanos(presentationTimeUs) - TimeUnit.MILLISECONDS.toNanos(value);
                millis = TimeUnit.NANOSECONDS.toMillis(presentationTimeUs);
                Label_0207: {
                    if (millis >= 0L) {
                        break Label_0207;
                    }
                    while (true) {
                        try {
                            this.mDecoder.releaseOutputBuffer(intValue, false);
                            ++this.nFrameRendered;
                            ++this.nFrameSkipped;
                            this.removeFrameFromQ(intValue);
                            // monitorexit(list)
                            continue Label_0183_Outer;
                        }
                        catch (Exception ex) {
                            Log.d("MediaDecoder2Video", "get exception as skip frame with releaseOutputBuffer()");
                            this.mLastFrameRendered = true;
                            continue;
                        }
                        break;
                    }
                }
                if (millis > 500L) {
                    break;
                }
                while (true) {
                    try {
                        this.mDecoder.releaseOutputBuffer(intValue, presentationTimeUs + System.nanoTime());
                        ++this.nFrameRendered;
                        this.removeFrameFromQ(intValue);
                        if (this.mEventListener != null) {
                            this.mEventListener.onSampleRendered(false, this.nFrameRendered, value);
                            continue Label_0256_Outer;
                        }
                        continue Label_0256_Outer;
                    }
                    catch (Exception ex2) {
                        Log.d("MediaDecoder2Video", "get exception as a result of timed releaseOutputBuffer()");
                        this.mLastFrameRendered = true;
                        continue;
                    }
                    break;
                }
                break;
            }
        }
        if (this.mHandler != null) {
            this.mHandler.sendEmptyMessageDelayed(1, millis - 500L);
        }
    }
    // monitorexit(list)
    
    @Override
    void addToRenderer(final int n, final MediaCodec$BufferInfo mediaCodec$BufferInfo) {
        synchronized (this.mOutputBuffersQ) {
            this.mOutputBuffersQ.add(n);
            this.mOutputBufferInfo[n] = mediaCodec$BufferInfo;
            // monitorexit(this.mOutputBuffersQ)
            if (!this.mPaused && MediaDecoder2Video.USE_ANDROID_L_API) {
                this.mHearbeat.ShowHearbeat();
                this.tryToReleaseBuffers();
            }
        }
    }
    
    @Override
    void createRenderer() {
        (this.mHandlerThread = new HandlerThread("RenderThreadVideo", -4)).start();
        this.mHandler = new MediaDecoder2Video$1(this, this.mHandlerThread.getLooper());
    }
    
    @Override
    void flushRenderer() {
        if (this.mHandler != null) {
            synchronized (this.mRenderState) {
                this.mHandler.sendEmptyMessage(2);
                try {
                    this.mRenderState.wait();
                }
                catch (InterruptedException ex) {
                    Log.d("MediaDecoder2Video", "flushRenderer interrupted");
                }
            }
        }
    }
    
    @Override
    void pauseRenderer() {
        this.mPaused = true;
    }
    
    @Override
    void startRenderer() {
        this.mDecoderStopped = false;
        if (!this.mLastFrameRendered && !this.mRendererStarted) {
            Log.d("MediaDecoder2Video", "start rendering");
            this.mHandler.sendEmptyMessage(1);
            this.mRendererStarted = true;
            this.mFirstFrameRendered = false;
        }
    }
    
    @Override
    void stopRenderer() {
        this.mDecoderStopped = true;
        if (this.mHandler != null) {
            this.mHandler.removeMessages(1);
        }
        if (this.mHandlerThread != null) {
            this.mHandlerThread.quit();
        }
    }
    
    @Override
    void terminateRenderer() {
    }
    
    @Override
    void unpauseRenderer() {
        this.mPaused = false;
        if (this.mHandler != null && MediaDecoder2Video.USE_ANDROID_L_API) {
            this.mHandler.sendEmptyMessage(1);
        }
    }
}
