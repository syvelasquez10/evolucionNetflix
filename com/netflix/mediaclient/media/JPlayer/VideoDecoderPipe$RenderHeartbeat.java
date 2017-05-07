// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import org.json.JSONObject;
import android.view.Surface;
import android.media.MediaFormat;
import android.os.HandlerThread;
import android.os.Handler;
import android.annotation.TargetApi;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.Log;

class VideoDecoderPipe$RenderHeartbeat
{
    static final long HRATBEAT_INTERVAL = 5000L;
    private long mLastBeat;
    final /* synthetic */ VideoDecoderPipe this$0;
    
    VideoDecoderPipe$RenderHeartbeat(final VideoDecoderPipe this$0) {
        this.this$0 = this$0;
        this.mLastBeat = System.currentTimeMillis();
    }
    
    void ShowHearbeat() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis >= this.mLastBeat + 5000L) {
            this.mLastBeat = currentTimeMillis;
            if (Log.isLoggable(this.this$0.mTag, 3)) {
                Log.d(this.this$0.mTag, "render alive, rendered frame " + this.this$0.nFrameRendered + ",skipped frame " + this.this$0.nFrameSkipped);
            }
        }
    }
}
