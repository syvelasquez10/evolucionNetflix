// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.util.Pair;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.configuration.drm.DrmManagerRegistry;
import android.media.MediaFormat;
import android.view.Surface;
import com.netflix.mediaclient.media.VideoResolutionRange;
import android.media.MediaCrypto;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.Log;
import java.nio.ByteBuffer;

public class JPlayer2$MediaDataSource implements MediaDecoderBase$InputDataSource
{
    static final boolean TYPE_AUDIO = true;
    static final boolean TYPE_VIDEO = false;
    private boolean mIsAudio;
    final /* synthetic */ JPlayer2 this$0;
    
    JPlayer2$MediaDataSource(final JPlayer2 this$0, final boolean mIsAudio) {
        this.this$0 = this$0;
        this.mIsAudio = mIsAudio;
    }
    
    @Override
    public MediaDecoderBase$InputDataSource$BufferMeta onRequestData(final ByteBuffer byteBuffer) {
        final MediaDecoderBase$InputDataSource$BufferMeta mediaDecoderBase$InputDataSource$BufferMeta = new MediaDecoderBase$InputDataSource$BufferMeta();
        mediaDecoderBase$InputDataSource$BufferMeta.size = 0;
        mediaDecoderBase$InputDataSource$BufferMeta.flags = 0;
        if (byteBuffer.isDirect()) {
            this.this$0.getBufferDirect(byteBuffer, this.mIsAudio, mediaDecoderBase$InputDataSource$BufferMeta);
        }
        else {
            Log.e("NF_JPlayer2", "WITH NON-DIRECT BYTEBUFFER");
            final byte[] array = byteBuffer.array();
            if (array == null) {
                mediaDecoderBase$InputDataSource$BufferMeta.size = 0;
                mediaDecoderBase$InputDataSource$BufferMeta.flags = 4;
                Log.e("NF_JPlayer2", "can't get bytearray");
                return mediaDecoderBase$InputDataSource$BufferMeta;
            }
            this.this$0.getBuffer(array, this.mIsAudio, mediaDecoderBase$InputDataSource$BufferMeta);
        }
        byteBuffer.limit(mediaDecoderBase$InputDataSource$BufferMeta.size);
        byteBuffer.position(0);
        return mediaDecoderBase$InputDataSource$BufferMeta;
    }
}
