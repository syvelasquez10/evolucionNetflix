// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import android.view.Surface;
import android.media.MediaFormat;
import android.os.HandlerThread;
import android.os.Handler;
import android.annotation.TargetApi;
import android.annotation.SuppressLint;

@SuppressLint({ "HandlerLeak" })
@TargetApi(16)
public class VideoDecoderPipe extends MediaDecoderPipe
{
    private static final int DEFAULT_LOOPING_TIME = 30;
    private static final int FAST_LOOPING_TIME = 10;
    private static final int MAX_LOOPING_TIME = 50;
    private static final int MSG_RENDER_FRAME = 1;
    private static final int RENDER_SKIP = 30;
    private static final int RENDER_WHIGH = 20;
    private static final int SCHEDULE_OFFSET = 5;
    private static final String TAG = "MediaPipeVideo";
    private static final String renderThreadPriority = "RenderThreadPriority";
    private volatile boolean mDecoderStopped;
    private boolean mFirstFrameRendered;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    VideoDecoderPipe$RenderHeartbeat mHearbeat;
    private boolean mLastFrameRendered;
    private boolean mPaused;
    private boolean mRendererStarted;
    private String mTag;
    private long nFrameRendered;
    private long nFrameSkipped;
    private long previousPts;
    
    public VideoDecoderPipe(final MediaDecoderPipe$InputDataSource mediaDecoderPipe$InputDataSource, final String s, final MediaFormat mediaFormat, final Surface surface, final String s2, final JSONObject jsonObject) {
        super(mediaDecoderPipe$InputDataSource, s, mediaFormat, surface, s2, jsonObject);
        this.nFrameRendered = 0L;
        this.nFrameSkipped = 0L;
        this.mRendererStarted = false;
        this.mLastFrameRendered = false;
        this.mFirstFrameRendered = false;
        this.mPaused = false;
        this.previousPts = -1L;
        this.mDecoderStopped = false;
        this.mHearbeat = new VideoDecoderPipe$RenderHeartbeat(this);
        this.makeHandler();
        final StringBuilder sb = new StringBuilder("MediaPipeVideo");
        sb.append(s2);
        this.mTag = sb.toString();
    }
    
    private void makeHandler() {
        if (this.isJPlayerThreadConfigured()) {
            this.mHandlerThread = new HandlerThread("RenderThread", this.getThreadPriority("RenderThreadPriority"));
            Log.d(this.mTag, "Thread priority updated:" + this.getThreadPriority("RenderThreadPriority"));
        }
        else {
            this.mHandlerThread = new HandlerThread("RenderThread");
        }
        this.mHandlerThread.start();
        this.mHandler = new VideoDecoderPipe$1(this, this.mHandlerThread.getLooper());
    }
    
    @Override
    public void pause() {
        this.mPaused = true;
        super.pause();
    }
    
    @Override
    boolean renderOutput(final boolean b) {
        if (!b) {
            this.mDecoderStopped = true;
            if (this.mHandler != null) {
                this.mHandler.removeMessages(1);
            }
            if (this.mHandlerThread != null) {
                this.mHandlerThread.quit();
            }
        }
        else {
            this.mDecoderStopped = false;
            if (!this.mLastFrameRendered) {
                if (!this.mRendererStarted) {
                    Log.d(this.mTag, "start rendering");
                    this.mHandler.sendEmptyMessage(1);
                    this.mRendererStarted = true;
                }
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void unpause() {
        this.mPaused = false;
        super.unpause();
    }
}
