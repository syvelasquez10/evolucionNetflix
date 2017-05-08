// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import com.google.android.exoplayer.util.TraceUtil;
import android.media.MediaCrypto;
import android.os.SystemClock;
import android.media.MediaCodec$CryptoInfo;
import com.google.android.exoplayer.util.NalUnitUtil;
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
import android.media.MediaCodec$CryptoException;

class MediaCodecTrackRenderer$2 implements Runnable
{
    final /* synthetic */ MediaCodecTrackRenderer this$0;
    final /* synthetic */ MediaCodec$CryptoException val$e;
    
    MediaCodecTrackRenderer$2(final MediaCodecTrackRenderer this$0, final MediaCodec$CryptoException val$e) {
        this.this$0 = this$0;
        this.val$e = val$e;
    }
    
    @Override
    public void run() {
        this.this$0.eventListener.onCryptoError(this.val$e);
    }
}
