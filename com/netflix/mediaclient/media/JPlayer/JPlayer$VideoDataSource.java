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

public class JPlayer$VideoDataSource implements MediaDecoderPipe$InputDataSource
{
    final /* synthetic */ JPlayer this$0;
    
    static {
        $assertionsDisabled = !JPlayer.class.desiredAssertionStatus();
    }
    
    public JPlayer$VideoDataSource(final JPlayer this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public MediaDecoderPipe$InputDataSource$BufferMeta onRequestData(final ByteBuffer byteBuffer) {
        final MediaDecoderPipe$InputDataSource$BufferMeta mediaDecoderPipe$InputDataSource$BufferMeta = new MediaDecoderPipe$InputDataSource$BufferMeta();
        final JPlayer$InputBufInfo player$InputBufInfo = new JPlayer$InputBufInfo(this.this$0);
        int n;
        if (byteBuffer.isDirect()) {
            n = this.this$0.nativeGetBufferDirect(byteBuffer, false, player$InputBufInfo);
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
            n = this.this$0.nativeGetBuffer(array, false, player$InputBufInfo);
        }
        assert n == player$InputBufInfo.mDataSize;
        mediaDecoderPipe$InputDataSource$BufferMeta.offset = 0;
        mediaDecoderPipe$InputDataSource$BufferMeta.timestamp = player$InputBufInfo.mTimeStamp;
        Label_0235: {
            if ((player$InputBufInfo.mFlags & 0x4) == 0x0) {
                break Label_0235;
            }
            Label_0204: {
                if (!this.this$0.mEnablePlatformDrs) {
                    break Label_0204;
                }
                mediaDecoderPipe$InputDataSource$BufferMeta.flags = 1;
                mediaDecoderPipe$InputDataSource$BufferMeta.size = player$InputBufInfo.mDataSize;
            Label_0196_Outer:
                while (true) {
                    if (byteBuffer.capacity() < mediaDecoderPipe$InputDataSource$BufferMeta.size) {
                        mediaDecoderPipe$InputDataSource$BufferMeta.size = byteBuffer.capacity();
                    }
                    while (true) {
                        try {
                            byteBuffer.limit(mediaDecoderPipe$InputDataSource$BufferMeta.size);
                            byteBuffer.position(0);
                            return mediaDecoderPipe$InputDataSource$BufferMeta;
                            mediaDecoderPipe$InputDataSource$BufferMeta.flags = 256;
                            mediaDecoderPipe$InputDataSource$BufferMeta.size = 0;
                            this.this$0.configureVideoPipe();
                            this.this$0.mSwitchingPending = true;
                            continue Label_0196_Outer;
                            mediaDecoderPipe$InputDataSource$BufferMeta.flags = player$InputBufInfo.mFlags;
                            mediaDecoderPipe$InputDataSource$BufferMeta.size = player$InputBufInfo.mDataSize;
                            continue Label_0196_Outer;
                        }
                        catch (IllegalArgumentException ex) {
                            mediaDecoderPipe$InputDataSource$BufferMeta.size = 0;
                            continue;
                        }
                        break;
                    }
                    break;
                }
            }
        }
    }
}
