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

class MediaCodecVideoTrackRenderer$1 implements Runnable
{
    final /* synthetic */ MediaCodecVideoTrackRenderer this$0;
    final /* synthetic */ int val$currentHeight;
    final /* synthetic */ float val$currentPixelWidthHeightRatio;
    final /* synthetic */ int val$currentUnappliedRotationDegrees;
    final /* synthetic */ int val$currentWidth;
    
    MediaCodecVideoTrackRenderer$1(final MediaCodecVideoTrackRenderer this$0, final int val$currentWidth, final int val$currentHeight, final int val$currentUnappliedRotationDegrees, final float val$currentPixelWidthHeightRatio) {
        this.this$0 = this$0;
        this.val$currentWidth = val$currentWidth;
        this.val$currentHeight = val$currentHeight;
        this.val$currentUnappliedRotationDegrees = val$currentUnappliedRotationDegrees;
        this.val$currentPixelWidthHeightRatio = val$currentPixelWidthHeightRatio;
    }
    
    @Override
    public void run() {
        this.this$0.eventListener.onVideoSizeChanged(this.val$currentWidth, this.val$currentHeight, this.val$currentUnappliedRotationDegrees, this.val$currentPixelWidthHeightRatio);
    }
}
