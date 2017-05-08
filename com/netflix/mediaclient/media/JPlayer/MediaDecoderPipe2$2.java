// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.view.Surface;
import com.netflix.mediaclient.util.AndroidUtils;
import android.os.HandlerThread;
import java.util.LinkedList;
import java.nio.ByteBuffer;
import android.media.MediaCodec;
import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.media.MediaCodec$BufferInfo;
import com.netflix.mediaclient.Log;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

class MediaDecoderPipe2$2 extends Handler
{
    long frameDecoded;
    final /* synthetic */ MediaDecoderPipe2 this$0;
    
    MediaDecoderPipe2$2(final MediaDecoderPipe2 this$0, final Looper looper) {
        this.this$0 = this$0;
        super(looper);
        this.frameDecoded = 0L;
    }
    
    public void handleMessage(Message access$1400) {
        int n = 4;
        switch (((Message)access$1400).what) {
            default: {
                Log.d(this.this$0.mTag, "outputthread handler had unknown message");
            }
            case 1: {
                if (this.this$0.mDecoderPause) {
                    Log.d(this.this$0.mTag, "outputthread pause");
                    return;
                }
                while (true) {
                    final MediaCodec$BufferInfo mediaCodec$BufferInfo = new MediaCodec$BufferInfo();
                    while (true) {
                        int dequeueOutputBuffer;
                        try {
                            dequeueOutputBuffer = this.this$0.mDecoder.dequeueOutputBuffer(mediaCodec$BufferInfo, -1L);
                            if (dequeueOutputBuffer == -1) {
                                this.this$0.mOutputHandler.removeMessages(1);
                                this.this$0.mOutputHandler.sendEmptyMessage(1);
                                return;
                            }
                        }
                        catch (Exception ex) {
                            Log.d(this.this$0.mTag, "get un-documented exception as a result of dequeueOutputBuffer() " + ex.getMessage());
                            return;
                        }
                        if (dequeueOutputBuffer == -3) {
                            Log.d(this.this$0.mTag, "OUTPUT_BUFFERS_CHANGED");
                            this.this$0.configureOutputBuffers();
                            continue;
                        }
                        if (dequeueOutputBuffer == -2) {
                            final MediaFormat outputFormat = this.this$0.mDecoder.getOutputFormat();
                            if (Log.isLoggable()) {
                                Log.d(this.this$0.mTag, "OUTPUT_FORMAT_CHANGED " + outputFormat);
                                continue;
                            }
                            continue;
                        }
                        else {
                            if (dequeueOutputBuffer < 0 || dequeueOutputBuffer >= this.this$0.mOutputBufferCnt) {
                                Log.e(this.this$0.mTag, dequeueOutputBuffer + " is not valid");
                                continue;
                            }
                            this.this$0.addToRenderer(dequeueOutputBuffer, mediaCodec$BufferInfo);
                            if ((mediaCodec$BufferInfo.flags & 0x4) != 0x0) {
                                Log.d(this.this$0.mTag, "got decoder output BUFFER_FLAG_END_OF_STREAM");
                            }
                            if (this.frameDecoded <= 0L && Log.isLoggable()) {
                                Log.d(this.this$0.mTag, "DequeueOutputBuffer " + dequeueOutputBuffer + " size= " + mediaCodec$BufferInfo.size + " @" + mediaCodec$BufferInfo.presentationTimeUs / 1000L + " ms");
                            }
                            if (this.this$0.mRefClock != null && mediaCodec$BufferInfo.presentationTimeUs / 1000L <= this.this$0.mRefClock.get() && Log.isLoggable()) {
                                Log.d(this.this$0.mTag, "STAT:DEC output late " + this.frameDecoded + " at " + this.this$0.mRefClock.get() + " by " + (mediaCodec$BufferInfo.presentationTimeUs / 1000L - this.this$0.mRefClock.get()) + " ms");
                            }
                            ++this.frameDecoded;
                            int n2;
                            if (this.this$0.mIsAudio) {
                                n2 = this.this$0.mOutputBufferCnt - 1;
                            }
                            else {
                                n2 = 1;
                            }
                            if (n2 <= 0) {
                                n = 1;
                            }
                            else if (n2 < 4) {
                                n = n2;
                            }
                            if (this.frameDecoded == n && this.this$0.mEventListener != null) {
                                this.this$0.mEventListener.onDecoderStarted(this.this$0.mIsAudio);
                                continue;
                            }
                            continue;
                        }
                        break;
                    }
                }
                break;
            }
            case 2: {
                synchronized (this.this$0.mOutputState) {
                    this.this$0.mOutputState.notify();
                    // monitorexit(MediaDecoderPipe2.access$1400(this.this$0))
                    this.frameDecoded = 0L;
                    Log.d(this.this$0.mTag, "flush output done");
                }
            }
            case 3: {
                Log.d(this.this$0.mTag, "output is initialized");
            }
            case 4: {
                Log.d(this.this$0.mTag, "output stopping...");
                while (true) {
                    try {
                        this.this$0.mDecoder.stop();
                        access$1400 = (Exception)this.this$0.mOutputState;
                        // monitorenter(access$1400)
                        final MediaDecoderPipe2$2 mediaDecoderPipe2$2 = this;
                        final MediaDecoderPipe2 mediaDecoderPipe2 = mediaDecoderPipe2$2.this$0;
                        final MediaDecoderPipe2$LocalStateNotifier mediaDecoderPipe2$LocalStateNotifier = mediaDecoderPipe2.mOutputState;
                        mediaDecoderPipe2$LocalStateNotifier.notify();
                        final Exception ex2 = access$1400;
                        // monitorexit(ex2)
                        final MediaDecoderPipe2$2 mediaDecoderPipe2$3 = this;
                        final MediaDecoderPipe2 mediaDecoderPipe3 = mediaDecoderPipe2$3.this$0;
                        final String s = mediaDecoderPipe3.mTag;
                        final String s2 = "output is stopped";
                        Log.d(s, s2);
                        return;
                    }
                    catch (Exception access$1400) {
                        Log.d(this.this$0.mTag, "get un-documented exception as a result of stop() " + access$1400.getMessage());
                        continue;
                    }
                    break;
                }
                try {
                    final MediaDecoderPipe2$2 mediaDecoderPipe2$2 = this;
                    final MediaDecoderPipe2 mediaDecoderPipe2 = mediaDecoderPipe2$2.this$0;
                    final MediaDecoderPipe2$LocalStateNotifier mediaDecoderPipe2$LocalStateNotifier = mediaDecoderPipe2.mOutputState;
                    mediaDecoderPipe2$LocalStateNotifier.notify();
                    final Exception ex2 = access$1400;
                    // monitorexit(ex2)
                    final MediaDecoderPipe2$2 mediaDecoderPipe2$3 = this;
                    final MediaDecoderPipe2 mediaDecoderPipe3 = mediaDecoderPipe2$3.this$0;
                    final String s = mediaDecoderPipe3.mTag;
                    final String s2 = "output is stopped";
                    Log.d(s, s2);
                    return;
                }
                finally {
                }
                // monitorexit(access$1400)
                break;
            }
        }
    }
}
