// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.media.MediaCodec$BufferInfo;
import java.nio.ByteBuffer;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.TraceUtil;
import android.media.MediaCrypto;
import android.media.MediaCodec;
import android.annotation.SuppressLint;
import com.google.android.exoplayer.util.Util;
import android.media.MediaFormat;
import android.os.SystemClock;
import android.os.Handler;
import com.google.android.exoplayer.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer.drm.DrmSessionManager;
import android.content.Context;
import android.view.Surface;
import android.annotation.TargetApi;

@TargetApi(16)
public class MediaCodecVideoTrackRenderer extends MediaCodecTrackRenderer
{
    private final long allowedJoiningTimeUs;
    private int consecutiveDroppedFrameCount;
    private int currentHeight;
    private float currentPixelWidthHeightRatio;
    private int currentUnappliedRotationDegrees;
    private int currentWidth;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrameCount;
    private final MediaCodecVideoTrackRenderer$EventListener eventListener;
    private final VideoFrameReleaseTimeHelper frameReleaseTimeHelper;
    private long joiningDeadlineUs;
    private int lastReportedHeight;
    private float lastReportedPixelWidthHeightRatio;
    private int lastReportedUnappliedRotationDegrees;
    private int lastReportedWidth;
    private final int maxDroppedFrameCountToNotify;
    private float pendingPixelWidthHeightRatio;
    private int pendingRotationDegrees;
    private boolean renderedFirstFrame;
    private boolean reportedDrawnToSurface;
    private Surface surface;
    private final int videoScalingMode;
    
    public MediaCodecVideoTrackRenderer(final Context context, final SampleSource sampleSource, final MediaCodecSelector mediaCodecSelector, final int videoScalingMode, final long n, final DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, final boolean b, final Handler handler, final MediaCodecVideoTrackRenderer$EventListener eventListener, final int maxDroppedFrameCountToNotify) {
        super(sampleSource, mediaCodecSelector, drmSessionManager, b, handler, eventListener);
        this.frameReleaseTimeHelper = new VideoFrameReleaseTimeHelper(context);
        this.videoScalingMode = videoScalingMode;
        this.allowedJoiningTimeUs = 1000L * n;
        this.eventListener = eventListener;
        this.maxDroppedFrameCountToNotify = maxDroppedFrameCountToNotify;
        this.joiningDeadlineUs = -1L;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.lastReportedWidth = -1;
        this.lastReportedHeight = -1;
        this.lastReportedPixelWidthHeightRatio = -1.0f;
    }
    
    private void maybeNotifyDrawnToSurface() {
        if (this.eventHandler == null || this.eventListener == null || this.reportedDrawnToSurface) {
            return;
        }
        this.eventHandler.post((Runnable)new MediaCodecVideoTrackRenderer$2(this, this.surface));
        this.reportedDrawnToSurface = true;
    }
    
    private void maybeNotifyDroppedFrameCount() {
        if (this.eventHandler == null || this.eventListener == null || this.droppedFrameCount == 0) {
            return;
        }
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        this.eventHandler.post((Runnable)new MediaCodecVideoTrackRenderer$3(this, this.droppedFrameCount, elapsedRealtime - this.droppedFrameAccumulationStartTimeMs));
        this.droppedFrameCount = 0;
        this.droppedFrameAccumulationStartTimeMs = elapsedRealtime;
    }
    
    private void maybeNotifyVideoSizeChanged() {
        if (this.eventHandler == null || this.eventListener == null || (this.lastReportedWidth == this.currentWidth && this.lastReportedHeight == this.currentHeight && this.lastReportedUnappliedRotationDegrees == this.currentUnappliedRotationDegrees && this.lastReportedPixelWidthHeightRatio == this.currentPixelWidthHeightRatio)) {
            return;
        }
        final int currentWidth = this.currentWidth;
        final int currentHeight = this.currentHeight;
        final int currentUnappliedRotationDegrees = this.currentUnappliedRotationDegrees;
        final float currentPixelWidthHeightRatio = this.currentPixelWidthHeightRatio;
        this.eventHandler.post((Runnable)new MediaCodecVideoTrackRenderer$1(this, currentWidth, currentHeight, currentUnappliedRotationDegrees, currentPixelWidthHeightRatio));
        this.lastReportedWidth = currentWidth;
        this.lastReportedHeight = currentHeight;
        this.lastReportedUnappliedRotationDegrees = currentUnappliedRotationDegrees;
        this.lastReportedPixelWidthHeightRatio = currentPixelWidthHeightRatio;
    }
    
    @SuppressLint({ "InlinedApi" })
    private void maybeSetMaxInputSize(final MediaFormat mediaFormat, final boolean b) {
        if (!mediaFormat.containsKey("max-input-size")) {
            int n2;
            final int n = n2 = mediaFormat.getInteger("height");
            if (b) {
                n2 = n;
                if (mediaFormat.containsKey("max-height")) {
                    n2 = Math.max(n, mediaFormat.getInteger("max-height"));
                }
            }
            int n4;
            final int n3 = n4 = mediaFormat.getInteger("width");
            if (b) {
                n4 = n3;
                if (mediaFormat.containsKey("max-width")) {
                    n4 = Math.max(n2, mediaFormat.getInteger("max-width"));
                }
            }
            final String string = mediaFormat.getString("mime");
            int n5 = -1;
            switch (string.hashCode()) {
                case -1664118616: {
                    if (string.equals("video/3gpp")) {
                        n5 = 0;
                        break;
                    }
                    break;
                }
                case 1187890754: {
                    if (string.equals("video/mp4v-es")) {
                        n5 = 1;
                        break;
                    }
                    break;
                }
                case 1331836730: {
                    if (string.equals("video/avc")) {
                        n5 = 2;
                        break;
                    }
                    break;
                }
                case 1599127256: {
                    if (string.equals("video/x-vnd.on2.vp8")) {
                        n5 = 3;
                        break;
                    }
                    break;
                }
                case -1662541442: {
                    if (string.equals("video/hevc")) {
                        n5 = 4;
                        break;
                    }
                    break;
                }
                case 1599127257: {
                    if (string.equals("video/x-vnd.on2.vp9")) {
                        n5 = 5;
                        break;
                    }
                    break;
                }
            }
            int n6 = 0;
            int n7 = 0;
            switch (n5) {
                default: {
                    return;
                }
                case 0:
                case 1: {
                    n6 = n2 * n4;
                    n7 = 2;
                    break;
                }
                case 2: {
                    if (!"BRAVIA 4K 2015".equals(Util.MODEL)) {
                        n6 = (n2 + 15) / 16 * ((n4 + 15) / 16) * 16 * 16;
                        n7 = 2;
                        break;
                    }
                    return;
                }
                case 3: {
                    n6 = n2 * n4;
                    n7 = 2;
                    break;
                }
                case 4:
                case 5: {
                    n6 = n2 * n4;
                    n7 = 4;
                    break;
                }
            }
            mediaFormat.setInteger("max-input-size", n6 * 3 / (n7 * 2));
        }
    }
    
    private void setSurface(final Surface surface) {
        if (this.surface != surface) {
            this.surface = surface;
            this.reportedDrawnToSurface = false;
            final int state = this.getState();
            if (state == 2 || state == 3) {
                this.releaseCodec();
                this.maybeInitCodec();
            }
        }
    }
    
    @Override
    protected boolean canReconfigureCodec(final MediaCodec mediaCodec, final boolean b, final com.google.android.exoplayer.MediaFormat mediaFormat, final com.google.android.exoplayer.MediaFormat mediaFormat2) {
        return mediaFormat2.mimeType.equals(mediaFormat.mimeType) && (b || (mediaFormat.width == mediaFormat2.width && mediaFormat.height == mediaFormat2.height));
    }
    
    @Override
    protected void configureCodec(final MediaCodec mediaCodec, final boolean b, final MediaFormat mediaFormat, final MediaCrypto mediaCrypto) {
        this.maybeSetMaxInputSize(mediaFormat, b);
        mediaCodec.configure(mediaFormat, this.surface, mediaCrypto, 0);
    }
    
    protected void dropOutputBuffer(final MediaCodec mediaCodec, final int n) {
        TraceUtil.beginSection("dropVideoBuffer");
        mediaCodec.releaseOutputBuffer(n, false);
        TraceUtil.endSection();
        final CodecCounters codecCounters = this.codecCounters;
        ++codecCounters.droppedOutputBufferCount;
        ++this.droppedFrameCount;
        ++this.consecutiveDroppedFrameCount;
        this.codecCounters.maxConsecutiveDroppedOutputBufferCount = Math.max(this.consecutiveDroppedFrameCount, this.codecCounters.maxConsecutiveDroppedOutputBufferCount);
        if (this.droppedFrameCount == this.maxDroppedFrameCountToNotify) {
            this.maybeNotifyDroppedFrameCount();
        }
    }
    
    @Override
    public void handleMessage(final int n, final Object o) {
        if (n == 1) {
            this.setSurface((Surface)o);
            return;
        }
        super.handleMessage(n, o);
    }
    
    @Override
    protected boolean handlesTrack(final MediaCodecSelector mediaCodecSelector, final com.google.android.exoplayer.MediaFormat mediaFormat) {
        final boolean b = false;
        final String mimeType = mediaFormat.mimeType;
        boolean b2 = b;
        if (MimeTypes.isVideo(mimeType)) {
            if (!"video/x-unknown".equals(mimeType)) {
                b2 = b;
                if (mediaCodecSelector.getDecoderInfo(mimeType, false) == null) {
                    return b2;
                }
            }
            b2 = true;
        }
        return b2;
    }
    
    @Override
    protected boolean isReady() {
        if (super.isReady() && (this.renderedFirstFrame || !this.codecInitialized() || this.getSourceState() == 2)) {
            this.joiningDeadlineUs = -1L;
        }
        else {
            if (this.joiningDeadlineUs == -1L) {
                return false;
            }
            if (SystemClock.elapsedRealtime() * 1000L >= this.joiningDeadlineUs) {
                this.joiningDeadlineUs = -1L;
                return false;
            }
        }
        return true;
    }
    
    @Override
    protected void onDisabled() {
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.pendingPixelWidthHeightRatio = -1.0f;
        this.lastReportedWidth = -1;
        this.lastReportedHeight = -1;
        this.lastReportedPixelWidthHeightRatio = -1.0f;
        this.frameReleaseTimeHelper.disable();
        super.onDisabled();
    }
    
    @Override
    protected void onDiscontinuity(final long n) {
        super.onDiscontinuity(n);
        this.renderedFirstFrame = false;
        this.consecutiveDroppedFrameCount = 0;
        this.joiningDeadlineUs = -1L;
    }
    
    @Override
    protected void onEnabled(final int n, final long n2, final boolean b) {
        super.onEnabled(n, n2, b);
        if (b && this.allowedJoiningTimeUs > 0L) {
            this.joiningDeadlineUs = SystemClock.elapsedRealtime() * 1000L + this.allowedJoiningTimeUs;
        }
        this.frameReleaseTimeHelper.enable();
    }
    
    @Override
    protected void onInputFormatChanged(final MediaFormatHolder mediaFormatHolder) {
        super.onInputFormatChanged(mediaFormatHolder);
        float pixelWidthHeightRatio;
        if (mediaFormatHolder.format.pixelWidthHeightRatio == -1.0f) {
            pixelWidthHeightRatio = 1.0f;
        }
        else {
            pixelWidthHeightRatio = mediaFormatHolder.format.pixelWidthHeightRatio;
        }
        this.pendingPixelWidthHeightRatio = pixelWidthHeightRatio;
        int rotationDegrees;
        if (mediaFormatHolder.format.rotationDegrees == -1) {
            rotationDegrees = 0;
        }
        else {
            rotationDegrees = mediaFormatHolder.format.rotationDegrees;
        }
        this.pendingRotationDegrees = rotationDegrees;
    }
    
    @Override
    protected void onOutputFormatChanged(final MediaCodec mediaCodec, final MediaFormat mediaFormat) {
        boolean b;
        if (mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top")) {
            b = true;
        }
        else {
            b = false;
        }
        int integer;
        if (b) {
            integer = mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left") + 1;
        }
        else {
            integer = mediaFormat.getInteger("width");
        }
        this.currentWidth = integer;
        int integer2;
        if (b) {
            integer2 = mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top") + 1;
        }
        else {
            integer2 = mediaFormat.getInteger("height");
        }
        this.currentHeight = integer2;
        this.currentPixelWidthHeightRatio = this.pendingPixelWidthHeightRatio;
        if (Util.SDK_INT >= 21) {
            if (this.pendingRotationDegrees == 90 || this.pendingRotationDegrees == 270) {
                final int currentWidth = this.currentWidth;
                this.currentWidth = this.currentHeight;
                this.currentHeight = currentWidth;
                this.currentPixelWidthHeightRatio = 1.0f / this.currentPixelWidthHeightRatio;
            }
        }
        else {
            this.currentUnappliedRotationDegrees = this.pendingRotationDegrees;
        }
        mediaCodec.setVideoScalingMode(this.videoScalingMode);
    }
    
    @Override
    protected void onStarted() {
        super.onStarted();
        this.droppedFrameCount = 0;
        this.droppedFrameAccumulationStartTimeMs = SystemClock.elapsedRealtime();
    }
    
    @Override
    protected void onStopped() {
        this.joiningDeadlineUs = -1L;
        this.maybeNotifyDroppedFrameCount();
        super.onStopped();
    }
    
    @Override
    protected boolean processOutputBuffer(long adjustReleaseTime, long n, final MediaCodec mediaCodec, final ByteBuffer byteBuffer, final MediaCodec$BufferInfo mediaCodec$BufferInfo, final int n2, final boolean b) {
        if (b) {
            this.skipOutputBuffer(mediaCodec, n2);
            this.consecutiveDroppedFrameCount = 0;
            return true;
        }
        if (!this.renderedFirstFrame) {
            if (Util.SDK_INT >= 21) {
                this.renderOutputBufferV21(mediaCodec, n2, System.nanoTime());
            }
            else {
                this.renderOutputBuffer(mediaCodec, n2);
            }
            this.consecutiveDroppedFrameCount = 0;
            return true;
        }
        if (this.getState() != 3) {
            return false;
        }
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        final long presentationTimeUs = mediaCodec$BufferInfo.presentationTimeUs;
        final long nanoTime = System.nanoTime();
        adjustReleaseTime = this.frameReleaseTimeHelper.adjustReleaseTime(mediaCodec$BufferInfo.presentationTimeUs, (presentationTimeUs - adjustReleaseTime - (elapsedRealtime * 1000L - n)) * 1000L + nanoTime);
        n = (adjustReleaseTime - nanoTime) / 1000L;
        if (n < -30000L) {
            this.dropOutputBuffer(mediaCodec, n2);
            return true;
        }
        if (Util.SDK_INT >= 21) {
            if (n < 50000L) {
                this.renderOutputBufferV21(mediaCodec, n2, adjustReleaseTime);
                this.consecutiveDroppedFrameCount = 0;
                return true;
            }
        }
        else if (n < 30000L) {
            while (true) {
                if (n <= 11000L) {
                    break Label_0211;
                }
                try {
                    Thread.sleep((n - 10000L) / 1000L);
                    this.renderOutputBuffer(mediaCodec, n2);
                    this.consecutiveDroppedFrameCount = 0;
                    return true;
                }
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    continue;
                }
                break;
            }
        }
        return false;
    }
    
    protected void renderOutputBuffer(final MediaCodec mediaCodec, final int n) {
        this.maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(n, true);
        TraceUtil.endSection();
        final CodecCounters codecCounters = this.codecCounters;
        ++codecCounters.renderedOutputBufferCount;
        this.renderedFirstFrame = true;
        this.maybeNotifyDrawnToSurface();
    }
    
    @TargetApi(21)
    protected void renderOutputBufferV21(final MediaCodec mediaCodec, final int n, final long n2) {
        this.maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(n, n2);
        TraceUtil.endSection();
        final CodecCounters codecCounters = this.codecCounters;
        ++codecCounters.renderedOutputBufferCount;
        this.renderedFirstFrame = true;
        this.maybeNotifyDrawnToSurface();
    }
    
    @Override
    protected boolean shouldInitCodec() {
        return super.shouldInitCodec() && this.surface != null && this.surface.isValid();
    }
    
    protected void skipOutputBuffer(final MediaCodec mediaCodec, final int n) {
        TraceUtil.beginSection("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(n, false);
        TraceUtil.endSection();
        final CodecCounters codecCounters = this.codecCounters;
        ++codecCounters.skippedOutputBufferCount;
    }
}
