// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.view.WindowManager;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(16)
public final class VideoFrameReleaseTimeHelper
{
    private long adjustedLastFrameTimeNs;
    private long frameCount;
    private boolean haveSync;
    private long lastFramePresentationTimeUs;
    private long pendingAdjustedFrameTimeNs;
    private long syncFramePresentationTimeNs;
    private long syncUnadjustedReleaseTimeNs;
    private final boolean useDefaultDisplayVsync;
    private final long vsyncDurationNs;
    private final long vsyncOffsetNs;
    private final VideoFrameReleaseTimeHelper$VSyncSampler vsyncSampler;
    
    public VideoFrameReleaseTimeHelper() {
        this(-1.0f, false);
    }
    
    private VideoFrameReleaseTimeHelper(final float n, final boolean useDefaultDisplayVsync) {
        this.useDefaultDisplayVsync = useDefaultDisplayVsync;
        if (useDefaultDisplayVsync) {
            this.vsyncSampler = VideoFrameReleaseTimeHelper$VSyncSampler.getInstance();
            this.vsyncDurationNs = (long)(1.0E9 / n);
            this.vsyncOffsetNs = this.vsyncDurationNs * 80L / 100L;
            return;
        }
        this.vsyncSampler = null;
        this.vsyncDurationNs = -1L;
        this.vsyncOffsetNs = -1L;
    }
    
    public VideoFrameReleaseTimeHelper(final Context context) {
        this(getDefaultDisplayRefreshRate(context), true);
    }
    
    private static long closestVsync(final long n, long n2, final long n3) {
        n2 += (n - n2) / n3 * n3;
        long n4;
        if (n <= n2) {
            n4 = n2 - n3;
        }
        else {
            n4 = n2;
            n2 += n3;
        }
        if (n2 - n < n - n4) {
            return n2;
        }
        return n4;
    }
    
    private static float getDefaultDisplayRefreshRate(final Context context) {
        return ((WindowManager)context.getSystemService("window")).getDefaultDisplay().getRefreshRate();
    }
    
    private boolean isDriftTooLarge(final long n, final long n2) {
        return Math.abs(n2 - this.syncUnadjustedReleaseTimeNs - (n - this.syncFramePresentationTimeNs)) > 20000000L;
    }
    
    public long adjustReleaseTime(final long lastFramePresentationTimeUs, final long syncUnadjustedReleaseTimeNs) {
        final long syncFramePresentationTimeNs = lastFramePresentationTimeUs * 1000L;
        while (true) {
            Label_0194: {
                if (!this.haveSync) {
                    break Label_0194;
                }
                if (lastFramePresentationTimeUs != this.lastFramePresentationTimeUs) {
                    ++this.frameCount;
                    this.adjustedLastFrameTimeNs = this.pendingAdjustedFrameTimeNs;
                }
                long pendingAdjustedFrameTimeNs;
                long n;
                if (this.frameCount >= 6L) {
                    pendingAdjustedFrameTimeNs = this.adjustedLastFrameTimeNs + (syncFramePresentationTimeNs - this.syncFramePresentationTimeNs) / this.frameCount;
                    if (this.isDriftTooLarge(pendingAdjustedFrameTimeNs, syncUnadjustedReleaseTimeNs)) {
                        this.haveSync = false;
                        n = syncUnadjustedReleaseTimeNs;
                        pendingAdjustedFrameTimeNs = syncFramePresentationTimeNs;
                    }
                    else {
                        n = this.syncUnadjustedReleaseTimeNs + pendingAdjustedFrameTimeNs - this.syncFramePresentationTimeNs;
                    }
                }
                else {
                    if (this.isDriftTooLarge(syncFramePresentationTimeNs, syncUnadjustedReleaseTimeNs)) {
                        this.haveSync = false;
                    }
                    break Label_0194;
                }
                if (!this.haveSync) {
                    this.syncFramePresentationTimeNs = syncFramePresentationTimeNs;
                    this.syncUnadjustedReleaseTimeNs = syncUnadjustedReleaseTimeNs;
                    this.frameCount = 0L;
                    this.haveSync = true;
                    this.onSynced();
                }
                this.lastFramePresentationTimeUs = lastFramePresentationTimeUs;
                this.pendingAdjustedFrameTimeNs = pendingAdjustedFrameTimeNs;
                if (this.vsyncSampler == null || this.vsyncSampler.sampledVsyncTimeNs == 0L) {
                    return n;
                }
                return closestVsync(n, this.vsyncSampler.sampledVsyncTimeNs, this.vsyncDurationNs) - this.vsyncOffsetNs;
            }
            long n = syncUnadjustedReleaseTimeNs;
            long pendingAdjustedFrameTimeNs = syncFramePresentationTimeNs;
            continue;
        }
    }
    
    public void disable() {
        if (this.useDefaultDisplayVsync) {
            this.vsyncSampler.removeObserver();
        }
    }
    
    public void enable() {
        this.haveSync = false;
        if (this.useDefaultDisplayVsync) {
            this.vsyncSampler.addObserver();
        }
    }
    
    protected void onSynced() {
    }
}
