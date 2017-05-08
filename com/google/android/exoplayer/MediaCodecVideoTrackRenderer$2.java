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
import android.annotation.TargetApi;
import android.view.Surface;

class MediaCodecVideoTrackRenderer$2 implements Runnable
{
    final /* synthetic */ MediaCodecVideoTrackRenderer this$0;
    final /* synthetic */ Surface val$surface;
    
    MediaCodecVideoTrackRenderer$2(final MediaCodecVideoTrackRenderer this$0, final Surface val$surface) {
        this.this$0 = this$0;
        this.val$surface = val$surface;
    }
    
    @Override
    public void run() {
        this.this$0.eventListener.onDrawnToSurface(this.val$surface);
    }
}
