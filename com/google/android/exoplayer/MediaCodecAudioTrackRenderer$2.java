// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.media.MediaCodec$BufferInfo;
import java.nio.ByteBuffer;
import com.google.android.exoplayer.util.MimeTypes;
import android.media.PlaybackParams;
import android.view.Surface;
import android.media.MediaCrypto;
import android.media.MediaCodec;
import com.google.android.exoplayer.audio.AudioTrack$InitializationException;
import com.google.android.exoplayer.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer.audio.AudioCapabilities;
import android.os.Handler;
import com.google.android.exoplayer.drm.DrmSessionManager;
import android.media.MediaFormat;
import com.google.android.exoplayer.audio.AudioTrack;
import android.annotation.TargetApi;
import com.google.android.exoplayer.audio.AudioTrack$WriteException;

class MediaCodecAudioTrackRenderer$2 implements Runnable
{
    final /* synthetic */ MediaCodecAudioTrackRenderer this$0;
    final /* synthetic */ AudioTrack$WriteException val$e;
    
    MediaCodecAudioTrackRenderer$2(final MediaCodecAudioTrackRenderer this$0, final AudioTrack$WriteException val$e) {
        this.this$0 = this$0;
        this.val$e = val$e;
    }
    
    @Override
    public void run() {
        this.this$0.eventListener.onAudioTrackWriteError(this.val$e);
    }
}
