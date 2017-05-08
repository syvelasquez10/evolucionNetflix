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

class MediaCodecVideoTrackRenderer$3 implements Runnable
{
    final /* synthetic */ MediaCodecVideoTrackRenderer this$0;
    final /* synthetic */ int val$countToNotify;
    final /* synthetic */ long val$elapsedToNotify;
    
    MediaCodecVideoTrackRenderer$3(final MediaCodecVideoTrackRenderer this$0, final int val$countToNotify, final long val$elapsedToNotify) {
        this.this$0 = this$0;
        this.val$countToNotify = val$countToNotify;
        this.val$elapsedToNotify = val$elapsedToNotify;
    }
    
    @Override
    public void run() {
        this.this$0.eventListener.onDroppedFrames(this.val$countToNotify, this.val$elapsedToNotify);
    }
}
