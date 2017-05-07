// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import java.util.LinkedList;
import android.media.MediaCodec$BufferInfo;
import java.util.concurrent.TimeUnit;
import android.media.MediaCrypto;
import android.view.Surface;
import android.media.MediaFormat;
import android.os.HandlerThread;
import android.os.Handler;
import android.annotation.TargetApi;
import com.netflix.mediaclient.Log;

class MediaDecoder2Video$RenderHeartbeat
{
    static final long HRATBEAT_INTERVAL = 5000L;
    private long mLastBeat;
    final /* synthetic */ MediaDecoder2Video this$0;
    
    MediaDecoder2Video$RenderHeartbeat(final MediaDecoder2Video this$0) {
        this.this$0 = this$0;
        this.mLastBeat = System.currentTimeMillis();
    }
    
    void ShowHearbeat() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis >= this.mLastBeat + 5000L) {
            this.mLastBeat = currentTimeMillis;
            if (Log.isLoggable("MediaDecoder2Video", 3)) {
                Log.d("MediaDecoder2Video", "render alive, rendered frame " + this.this$0.nFrameRendered + ",skipped frame " + this.this$0.nFrameSkipped);
            }
        }
    }
}
