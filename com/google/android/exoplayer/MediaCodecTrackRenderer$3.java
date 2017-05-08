// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import com.google.android.exoplayer.util.TraceUtil;
import android.media.MediaCrypto;
import android.os.SystemClock;
import android.media.MediaCodec$CryptoInfo;
import com.google.android.exoplayer.util.NalUnitUtil;
import android.media.MediaCodec$CryptoException;
import java.util.ArrayList;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import android.media.MediaCodec$BufferInfo;
import java.nio.ByteBuffer;
import android.os.Handler;
import com.google.android.exoplayer.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer.drm.DrmSessionManager;
import com.google.android.exoplayer.drm.DrmInitData;
import java.util.List;
import android.media.MediaCodec;
import android.annotation.TargetApi;

class MediaCodecTrackRenderer$3 implements Runnable
{
    final /* synthetic */ MediaCodecTrackRenderer this$0;
    final /* synthetic */ String val$decoderName;
    final /* synthetic */ long val$initializationDuration;
    final /* synthetic */ long val$initializedTimestamp;
    
    MediaCodecTrackRenderer$3(final MediaCodecTrackRenderer this$0, final String val$decoderName, final long val$initializedTimestamp, final long val$initializationDuration) {
        this.this$0 = this$0;
        this.val$decoderName = val$decoderName;
        this.val$initializedTimestamp = val$initializedTimestamp;
        this.val$initializationDuration = val$initializationDuration;
    }
    
    @Override
    public void run() {
        this.this$0.eventListener.onDecoderInitialized(this.val$decoderName, this.val$initializedTimestamp, this.val$initializationDuration);
    }
}
