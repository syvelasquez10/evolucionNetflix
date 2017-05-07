// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.bandwidthsetting;

import com.netflix.mediaclient.Log;

public class BandwidthDelayedBifDownload
{
    private static float DELAY_MULTIPLE;
    private static String TAG;
    private long bufferingCompleteTimeInMs;
    private long startTimeInMs;
    
    static {
        BandwidthDelayedBifDownload.TAG = "nf_bw_delayed_bif";
        BandwidthDelayedBifDownload.DELAY_MULTIPLE = 2.5f;
    }
    
    public BandwidthDelayedBifDownload() {
        this.startTimeInMs = System.currentTimeMillis();
        Log.d(BandwidthDelayedBifDownload.TAG, String.format("BandwidthDelayedBifDownload - startTimeInMs :%d", this.startTimeInMs));
    }
    
    public boolean shouldDownloadBif(final boolean b) {
        if (this.bufferingCompleteTimeInMs > 0L) {
            final boolean b2 = System.currentTimeMillis() - this.bufferingCompleteTimeInMs >= BandwidthDelayedBifDownload.DELAY_MULTIPLE * (this.bufferingCompleteTimeInMs - this.startTimeInMs);
            Log.d(BandwidthDelayedBifDownload.TAG, String.format("download? %b -  bufferingTime:%d, waitedMs: %d", b2, this.bufferingCompleteTimeInMs - this.startTimeInMs, System.currentTimeMillis() - this.bufferingCompleteTimeInMs));
            return b2;
        }
        if (b) {
            this.bufferingCompleteTimeInMs = System.currentTimeMillis();
            Log.d(BandwidthDelayedBifDownload.TAG, String.format("bufferingComplete in ms:%d", this.bufferingCompleteTimeInMs - this.startTimeInMs));
        }
        return false;
    }
}
