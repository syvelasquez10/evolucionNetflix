// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import org.json.JSONException;
import android.media.MediaCrypto;
import java.util.concurrent.locks.ReentrantLock;
import android.view.Surface;
import android.media.MediaFormat;
import android.media.MediaCodec$BufferInfo;
import java.util.concurrent.locks.Lock;
import org.json.JSONObject;
import java.util.LinkedList;
import java.nio.ByteBuffer;
import android.media.MediaCodec;
import java.util.concurrent.locks.Condition;
import android.annotation.TargetApi;
import com.netflix.mediaclient.Log;

class MediaDecoderPipe$DecoderHeartbeat
{
    static final long HRATBEAT_INTERVAL = 5000L;
    private long mLastBeat;
    final /* synthetic */ MediaDecoderPipe this$0;
    
    MediaDecoderPipe$DecoderHeartbeat(final MediaDecoderPipe this$0) {
        this.this$0 = this$0;
        this.mLastBeat = System.currentTimeMillis();
    }
    
    void ShowHearbeat(final long n, final long n2) {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis >= this.mLastBeat + 5000L) {
            this.mLastBeat = currentTimeMillis;
            if (Log.isLoggable()) {
                Log.d(this.this$0.mTag, "decoder alive, received frame " + n + ",decoded frame " + n2);
            }
        }
    }
}
