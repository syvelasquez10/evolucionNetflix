// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import java.util.Arrays;
import android.media.MediaFormat;
import android.view.SurfaceHolder;
import android.view.Surface;
import org.json.JSONObject;
import android.annotation.TargetApi;
import android.util.Log;
import java.nio.ByteBuffer;

public class JPlayer$AudioDataSource implements MediaDecoderPipe$InputDataSource
{
    final /* synthetic */ JPlayer this$0;
    
    static {
        $assertionsDisabled = !JPlayer.class.desiredAssertionStatus();
    }
    
    public JPlayer$AudioDataSource(final JPlayer this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public MediaDecoderPipe$InputDataSource$BufferMeta onRequestData(final ByteBuffer byteBuffer) {
        final MediaDecoderPipe$InputDataSource$BufferMeta mediaDecoderPipe$InputDataSource$BufferMeta = new MediaDecoderPipe$InputDataSource$BufferMeta();
        final JPlayer$InputBufInfo player$InputBufInfo = new JPlayer$InputBufInfo(this.this$0);
        int n;
        if (byteBuffer.isDirect()) {
            n = this.this$0.nativeGetBufferDirect(byteBuffer, true, player$InputBufInfo);
        }
        else {
            Log.e("NF_JPlayer", "WITH NON-DIRECT BYTEBUFFER");
            final byte[] array = byteBuffer.array();
            if (array == null) {
                mediaDecoderPipe$InputDataSource$BufferMeta.size = 0;
                mediaDecoderPipe$InputDataSource$BufferMeta.flags = 256;
                Log.e("NF_JPlayer", "can't get bytearray");
                return mediaDecoderPipe$InputDataSource$BufferMeta;
            }
            n = this.this$0.nativeGetBuffer(array, true, player$InputBufInfo);
        }
        assert n == player$InputBufInfo.mDataSize;
        mediaDecoderPipe$InputDataSource$BufferMeta.timestamp = player$InputBufInfo.mTimeStamp;
        mediaDecoderPipe$InputDataSource$BufferMeta.flags = player$InputBufInfo.mFlags;
        mediaDecoderPipe$InputDataSource$BufferMeta.size = player$InputBufInfo.mDataSize;
        byteBuffer.limit(player$InputBufInfo.mDataSize);
        byteBuffer.position(0);
        return mediaDecoderPipe$InputDataSource$BufferMeta;
    }
}
