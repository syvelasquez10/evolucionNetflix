// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.util.Pair;
import com.netflix.mediaclient.util.AndroidUtils;
import android.media.MediaFormat;
import com.netflix.mediaclient.service.configuration.drm.DrmManagerRegistry;
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
        int access$300 = 0;
        final MediaDecoderBase$InputDataSource$BufferMeta mediaDecoderBase$InputDataSource$BufferMeta = new MediaDecoderBase$InputDataSource$BufferMeta();
        mediaDecoderBase$InputDataSource$BufferMeta.size = 0;
        mediaDecoderBase$InputDataSource$BufferMeta.flags = 0;
        Label_0163: {
            if (!byteBuffer.isDirect()) {
                break Label_0163;
            }
            this.this$0.getBufferDirect(byteBuffer, this.mIsAudio, mediaDecoderBase$InputDataSource$BufferMeta);
        Label_0156_Outer:
            while (true) {
                byteBuffer.limit(mediaDecoderBase$InputDataSource$BufferMeta.size);
                byteBuffer.position(0);
                if (!this.mIsAudio || (mediaDecoderBase$InputDataSource$BufferMeta.flags & 0x10000) == 0x0) {
                    return mediaDecoderBase$InputDataSource$BufferMeta;
                }
                final byte[] array = new byte[mediaDecoderBase$InputDataSource$BufferMeta.size];
                byteBuffer.get(array);
                final String s = new String(array);
                Log.d("NF_JPlayer2", "codecId:  " + s);
                if (!this.this$0.isAudioPipeNeedReconfig(s)) {
                    mediaDecoderBase$InputDataSource$BufferMeta.flags = 0;
                    mediaDecoderBase$InputDataSource$BufferMeta.size = 0;
                    return mediaDecoderBase$InputDataSource$BufferMeta;
                }
                while (true) {
                    try {
                        access$300 = (this.this$0.reconfigureAudioPipe(s.contains("ec-3")) ? 1 : 0);
                        if (access$300 == 0) {
                            return mediaDecoderBase$InputDataSource$BufferMeta;
                        }
                        break Label_0163;
                        Label_0205: {
                            final byte[] array2;
                            this.this$0.getBuffer(array2, this.mIsAudio, mediaDecoderBase$InputDataSource$BufferMeta);
                        }
                        continue Label_0156_Outer;
                        while (true) {
                            mediaDecoderBase$InputDataSource$BufferMeta.size = 0;
                            mediaDecoderBase$InputDataSource$BufferMeta.flags = 4;
                            Log.e("NF_JPlayer2", "can't get bytearray");
                            return mediaDecoderBase$InputDataSource$BufferMeta;
                            Log.e("NF_JPlayer2", "WITH NON-DIRECT BYTEBUFFER");
                            final byte[] array2 = byteBuffer.array();
                            continue;
                        }
                    }
                    // iftrue(Label_0205:, array2 != null)
                    catch (Exception ex) {
                        Log.w("NF_JPlayer2", "reconfig audio decoder failed");
                        continue;
                    }
                    break;
                }
                break;
            }
        }
        final MediaDecoderBase$Clock clock = this.this$0.mAudioPipe.getClock();
        this.this$0.mAudioPipe.setReferenceClock(clock);
        this.this$0.mAudioPipe.start();
        this.this$0.mAudioPipe.unpause();
        this.this$0.mVideoPipe.setReferenceClock(clock);
        return mediaDecoderBase$InputDataSource$BufferMeta;
    }
}
