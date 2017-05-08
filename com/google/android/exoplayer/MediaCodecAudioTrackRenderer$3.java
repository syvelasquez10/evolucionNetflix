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
import com.google.android.exoplayer.audio.AudioTrack$WriteException;
import com.google.android.exoplayer.audio.AudioTrack$InitializationException;
import com.google.android.exoplayer.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer.audio.AudioCapabilities;
import android.os.Handler;
import com.google.android.exoplayer.drm.DrmSessionManager;
import android.media.MediaFormat;
import com.google.android.exoplayer.audio.AudioTrack;
import android.annotation.TargetApi;

class MediaCodecAudioTrackRenderer$3 implements Runnable
{
    final /* synthetic */ MediaCodecAudioTrackRenderer this$0;
    final /* synthetic */ int val$bufferSize;
    final /* synthetic */ long val$bufferSizeMs;
    final /* synthetic */ long val$elapsedSinceLastFeedMs;
    
    MediaCodecAudioTrackRenderer$3(final MediaCodecAudioTrackRenderer this$0, final int val$bufferSize, final long val$bufferSizeMs, final long val$elapsedSinceLastFeedMs) {
        this.this$0 = this$0;
        this.val$bufferSize = val$bufferSize;
        this.val$bufferSizeMs = val$bufferSizeMs;
        this.val$elapsedSinceLastFeedMs = val$elapsedSinceLastFeedMs;
    }
    
    @Override
    public void run() {
        this.this$0.eventListener.onAudioTrackUnderrun(this.val$bufferSize, this.val$bufferSizeMs, this.val$elapsedSinceLastFeedMs);
    }
}
