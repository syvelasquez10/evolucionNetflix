// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.view.Surface;
import android.media.MediaFormat;
import com.netflix.mediaclient.util.AndroidUtils;
import android.media.MediaCodec$BufferInfo;
import android.os.HandlerThread;
import java.util.LinkedList;
import android.media.MediaCodec;
import android.annotation.TargetApi;
import java.nio.ByteBuffer;
import android.media.MediaCodec$CryptoInfo;
import com.netflix.mediaclient.Log;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;

class MediaDecoderPipe2$1 extends Handler
{
    long frameReceived;
    final /* synthetic */ MediaDecoderPipe2 this$0;
    
    MediaDecoderPipe2$1(final MediaDecoderPipe2 this$0, final Looper looper) {
        this.this$0 = this$0;
        super(looper);
        this.frameReceived = 0L;
    }
    
    public void handleMessage(final Message message) {
        Label_0049: {
            switch (message.what) {
                default: {
                    Log.d(this.this$0.mTag, "outputthread handler had unknown message");
                    break;
                }
                case 1: {
                Label_0117:
                    while (true) {
                        if (!this.this$0.mInputBuffersQ.isEmpty() || this.this$0.mDecoderPause) {
                            break Label_0117;
                        }
                        while (true) {
                            int dequeueInputBuffer;
                            while (true) {
                                try {
                                    dequeueInputBuffer = this.this$0.mDecoder.dequeueInputBuffer(-1L);
                                    if (dequeueInputBuffer >= 0 && dequeueInputBuffer < this.this$0.mInputBufferCnt) {
                                        this.this$0.mInputBuffersQ.add(dequeueInputBuffer);
                                        if (this.this$0.mDecoderPause) {
                                            Log.d(this.this$0.mTag, "inputthread pause");
                                            return;
                                        }
                                        break;
                                    }
                                }
                                catch (Exception ex) {
                                    Log.d(this.this$0.mTag, "get un-documented exception as a result of dequeueInputBuffer() " + ex.getMessage());
                                    if (!this.this$0.mDecoderPause) {
                                        final MediaDecoderBase$EventListener mEventListener = this.this$0.mEventListener;
                                        final boolean access$300 = this.this$0.mIsAudio;
                                        int n;
                                        if (this.this$0.mIsAudio) {
                                            n = 1;
                                        }
                                        else {
                                            n = 2;
                                        }
                                        mEventListener.onError(access$300, n, ex.toString());
                                    }
                                    dequeueInputBuffer = -1;
                                    continue;
                                }
                                break;
                            }
                            Log.d(this.this$0.mTag, "get invlaid buffer index " + dequeueInputBuffer + " as a result of dequeueInputBuffer()");
                            continue Label_0117;
                        }
                        break;
                    }
                    if (this.this$0.mInputBuffersQ.isEmpty()) {
                        this.this$0.mInputHandler.removeMessages(1);
                        this.this$0.mInputHandler.sendEmptyMessageDelayed(1, 20L);
                        return;
                    }
                    final int intValue = this.this$0.mInputBuffersQ.peekFirst();
                    final ByteBuffer byteBuffer = this.this$0.mInputBuffers[intValue];
                    final MediaDecoderBase$InputDataSource$BufferMeta onRequestData = this.this$0.mDataSource.onRequestData(byteBuffer);
                    if (onRequestData.size <= 0 && onRequestData.flags == 0) {
                        this.this$0.mInputHandler.removeMessages(1);
                        this.this$0.mInputHandler.sendEmptyMessageDelayed(1, 20L);
                        return;
                    }
                    if ((onRequestData.flags & 0x10000) != 0x0) {
                        Log.d(this.this$0.mTag, "got codec change, need to terminate the pipe");
                        this.this$0.mInputHandler.removeMessages(1);
                        this.this$0.terminateRenderer();
                        return;
                    }
                    if (this.frameReceived <= 0L && Log.isLoggable()) {
                        Log.d(this.this$0.mTag, "QueueInput " + intValue + " from " + onRequestData.offset + " size= " + onRequestData.size + " @" + onRequestData.timestamp + " ms flags " + onRequestData.flags);
                    }
                    if (this.this$0.mRefClock != null && onRequestData.timestamp < this.this$0.mRefClock.get() && Log.isLoggable()) {
                        Log.d(this.this$0.mTag, "STAT:DEC input late " + this.frameReceived + " at " + this.this$0.mRefClock.get() + " by " + (onRequestData.timestamp - this.this$0.mRefClock.get()) + " ms");
                    }
                    boolean b = false;
                    if ((onRequestData.flags & 0x4) != 0x0) {
                        b = true;
                        Log.d(this.this$0.mTag, "got decoder input BUFFER_FLAG_END_OF_STREAM");
                    }
                    if (this.frameReceived > 0L && (onRequestData.flags & 0x2) != 0x0) {
                        Log.d(this.this$0.mTag, "got decoder input BUFFER_FLAG_CODEC_CONFIG during playback, ignored");
                        this.this$0.mInputHandler.removeMessages(1);
                        this.this$0.mInputHandler.sendEmptyMessage(1);
                        return;
                    }
                Label_0985_Outer:
                    while (true) {
                        while (true) {
                        Label_1055:
                            while (true) {
                                try {
                                    if (this.this$0.mEncrypted) {
                                        final MediaCodec$CryptoInfo mediaCodec$CryptoInfo = new MediaCodec$CryptoInfo();
                                        mediaCodec$CryptoInfo.mode = 1;
                                        if (onRequestData.nByteEncrypted.length == 0) {
                                            final byte[] array = new byte[16];
                                            for (int i = 0; i < array.length; ++i) {
                                                array[i] = 0;
                                            }
                                            mediaCodec$CryptoInfo.iv = array;
                                            mediaCodec$CryptoInfo.key = array;
                                            mediaCodec$CryptoInfo.numBytesOfClearData = new int[] { onRequestData.size };
                                            mediaCodec$CryptoInfo.numBytesOfEncryptedData = new int[] { 0 };
                                            mediaCodec$CryptoInfo.numSubSamples = 1;
                                        }
                                        else {
                                            mediaCodec$CryptoInfo.iv = onRequestData.iv;
                                            mediaCodec$CryptoInfo.key = onRequestData.key;
                                            mediaCodec$CryptoInfo.numBytesOfClearData = onRequestData.nByteInClear;
                                            mediaCodec$CryptoInfo.numBytesOfEncryptedData = onRequestData.nByteEncrypted;
                                            mediaCodec$CryptoInfo.numSubSamples = onRequestData.nSubsample;
                                        }
                                        this.this$0.mDecoder.queueSecureInputBuffer(intValue, onRequestData.offset, mediaCodec$CryptoInfo, onRequestData.timestamp * 1000L, onRequestData.flags);
                                        this.this$0.mInputBuffersQ.removeFirst();
                                        ++this.frameReceived;
                                        if (!b) {
                                            this.this$0.mInputHandler.removeMessages(1);
                                            this.this$0.mInputHandler.sendEmptyMessage(1);
                                            return;
                                        }
                                        break Label_0049;
                                    }
                                }
                                catch (Exception ex2) {
                                    Log.d(this.this$0.mTag, "get un-documented exception as a result of queueInputBuffer() " + ex2);
                                    if (this.this$0.mDecoderPause) {
                                        break Label_0049;
                                    }
                                    final MediaDecoderBase$EventListener mEventListener2 = this.this$0.mEventListener;
                                    final boolean access$301 = this.this$0.mIsAudio;
                                    if (this.this$0.mIsAudio) {
                                        final int n2 = 3;
                                        mEventListener2.onError(access$301, n2, ex2.toString());
                                        return;
                                    }
                                    break Label_1055;
                                }
                                if (this.this$0.mIsAudio) {
                                    this.this$0.processAudioIfEncrypted(byteBuffer, onRequestData);
                                }
                                this.this$0.mDecoder.queueInputBuffer(intValue, onRequestData.offset, onRequestData.size, onRequestData.timestamp * 1000L, onRequestData.flags);
                                continue Label_0985_Outer;
                            }
                            final int n2 = 4;
                            continue;
                        }
                    }
                    break;
                }
                case 2: {
                    this.this$0.mInputBuffersQ.clear();
                    synchronized (this.this$0.mInputState) {
                        this.this$0.mInputState.notify();
                        // monitorexit(MediaDecoderPipe2.access$1000(this.this$0))
                        Log.d(this.this$0.mTag, "flush input done");
                        return;
                    }
                }
                case 3: {
                    Log.d(this.this$0.mTag, "input is initialized");
                    if (this.this$0.mEventListener != null) {
                        this.this$0.mEventListener.onDecoderReady(this.this$0.mIsAudio);
                        return;
                    }
                    break;
                }
                case 4: {
                    Log.d(this.this$0.mTag, "input is stopped");
                    return;
                }
            }
        }
    }
}
