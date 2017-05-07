// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.os.Message;
import android.os.Looper;
import java.util.concurrent.TimeUnit;
import android.media.AudioTimestamp;
import com.netflix.mediaclient.Log;
import java.nio.ByteBuffer;
import android.media.MediaCodec$BufferInfo;
import android.media.MediaCrypto;
import android.view.Surface;
import android.media.MediaFormat;
import android.os.HandlerThread;
import android.os.Handler;
import android.media.AudioTrack;
import android.annotation.TargetApi;

@TargetApi(19)
public class MediaDecoder2Audio extends MediaDecoderPipe2
{
    private static final int MSG_RENDER_FLUSH = 2;
    private static final int MSG_RENDER_FLUSHED = 4;
    private static final int MSG_RENDER_FRAME = 1;
    private static final int MSG_RENDER_PAUSE = 3;
    private static final String TAG = "MediaDecoder2Audio";
    private AudioTrack mAudioTrack;
    private int mBufferSize;
    private int mChannelConfig;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private LocalStateNotifier mRenderState;
    private long mSampleCnt;
    private int mSampleRate;
    private int mSampleSize;
    private long nFrameRendered;
    
    public MediaDecoder2Audio(final InputDataSource inputDataSource, final String s, final MediaFormat mediaFormat) throws Exception {
        super(inputDataSource, s, mediaFormat, null, null);
        this.mSampleRate = 48000;
        this.mChannelConfig = 12;
        this.nFrameRendered = 0L;
        this.mRenderState = new LocalStateNotifier(this);
    }
    
    private void createAudioTrack() {
        if (this.mAudioTrack == null) {
            Log.d("MediaDecoder2Audio", "create audiotrack ... ");
            this.mBufferSize = AudioTrack.getMinBufferSize(this.mSampleRate, this.mChannelConfig, 2);
            if (this.mBufferSize < 32768) {
                this.mBufferSize = 32768;
            }
            this.mAudioTrack = new AudioTrack(3, this.mSampleRate, this.mChannelConfig, 2, this.mBufferSize, 1);
            if (Log.isLoggable("MediaDecoder2Audio", 3)) {
                Log.d("MediaDecoder2Audio", "mBufferSize = " + this.mBufferSize);
            }
            this.mSampleSize = 4;
        }
    }
    
    private long getAudioHeaderPosition() {
        if (MediaDecoder2Audio.USE_ANDROID_L_API) {
            final AudioTimestamp audioTimestamp = new AudioTimestamp();
            if (this.mAudioTrack.getTimestamp(audioTimestamp)) {
                return audioTimestamp.framePosition;
            }
        }
        return this.mAudioTrack.getPlaybackHeadPosition();
    }
    
    private long getAudioPresentationLatencyMs() {
        if (MediaDecoder2Audio.USE_ANDROID_L_API) {
            final AudioTimestamp audioTimestamp = new AudioTimestamp();
            if (this.mAudioTrack.getTimestamp(audioTimestamp)) {
                Log.d("MediaDecoder2Audio", "AudioClock: diff nanoTime in ms = " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - audioTimestamp.nanoTime));
                final long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - audioTimestamp.nanoTime);
                if (millis >= 0L && millis <= 20L) {
                    return millis;
                }
            }
        }
        return 0L;
    }
    
    private void renderOneFrame(final int p0, final MediaCodec$BufferInfo p1, final ByteBuffer p2, final long p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_3        
        //     1: invokevirtual   java/nio/ByteBuffer.clear:()Ljava/nio/Buffer;
        //     4: pop            
        //     5: aload_3        
        //     6: iconst_0       
        //     7: invokevirtual   java/nio/ByteBuffer.position:(I)Ljava/nio/Buffer;
        //    10: pop            
        //    11: aload_3        
        //    12: invokevirtual   java/nio/ByteBuffer.hasArray:()Z
        //    15: ifeq            446
        //    18: aload_3        
        //    19: invokevirtual   java/nio/ByteBuffer.array:()[B
        //    22: astore_3       
        //    23: aload_3        
        //    24: ifnull          315
        //    27: lload           4
        //    29: ldc2_w          5000
        //    32: lcmp           
        //    33: ifge            472
        //    36: aload_0        
        //    37: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mAudioTrack:Landroid/media/AudioTrack;
        //    40: invokestatic    android/media/AudioTrack.getMinVolume:()F
        //    43: invokestatic    android/media/AudioTrack.getMinVolume:()F
        //    46: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
        //    49: pop            
        //    50: aload_0        
        //    51: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mAudioTrack:Landroid/media/AudioTrack;
        //    54: aload_3        
        //    55: iconst_0       
        //    56: aload_2        
        //    57: getfield        android/media/MediaCodec$BufferInfo.size:I
        //    60: invokevirtual   android/media/AudioTrack.write:([BII)I
        //    63: pop            
        //    64: aload_0        
        //    65: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //    68: aload_0        
        //    69: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //    72: aload_0        
        //    73: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleSize:I
        //    76: i2l            
        //    77: ldiv           
        //    78: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.shouldUpdate:(J)Z
        //    81: ifeq            273
        //    84: ldc2_w          1000
        //    87: aload_0        
        //    88: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //    91: aload_0        
        //    92: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleSize:I
        //    95: i2l            
        //    96: ldiv           
        //    97: aload_0        
        //    98: invokespecial   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.getAudioHeaderPosition:()J
        //   101: lsub           
        //   102: lmul           
        //   103: aload_0        
        //   104: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleRate:I
        //   107: i2l            
        //   108: ldiv           
        //   109: aload_0        
        //   110: invokespecial   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.getAudioPresentationLatencyMs:()J
        //   113: ladd           
        //   114: lstore          4
        //   116: getstatic       java/util/concurrent/TimeUnit.MICROSECONDS:Ljava/util/concurrent/TimeUnit;
        //   119: aload_2        
        //   120: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   123: invokevirtual   java/util/concurrent/TimeUnit.toMillis:(J)J
        //   126: lload           4
        //   128: lsub           
        //   129: lstore          6
        //   131: ldc             "MediaDecoder2Audio"
        //   133: iconst_3       
        //   134: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   137: ifeq            264
        //   140: ldc             "MediaDecoder2Audio"
        //   142: new             Ljava/lang/StringBuilder;
        //   145: dup            
        //   146: invokespecial   java/lang/StringBuilder.<init>:()V
        //   149: ldc             "timestamp = "
        //   151: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   154: aload_2        
        //   155: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   158: ldc2_w          1000
        //   161: ldiv           
        //   162: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   165: ldc             " ms, "
        //   167: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: ldc             "total "
        //   172: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   175: aload_0        
        //   176: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //   179: aload_0        
        //   180: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleSize:I
        //   183: i2l            
        //   184: ldiv           
        //   185: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   188: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   191: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   194: pop            
        //   195: ldc             "MediaDecoder2Audio"
        //   197: new             Ljava/lang/StringBuilder;
        //   200: dup            
        //   201: invokespecial   java/lang/StringBuilder.<init>:()V
        //   204: ldc             "AudioClock: predicted "
        //   206: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   209: aload_0        
        //   210: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   213: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
        //   216: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   219: ldc             " ms, update to = "
        //   221: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   224: lload           6
        //   226: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   229: ldc             " ms, delta = "
        //   231: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   234: lload           6
        //   236: aload_0        
        //   237: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   240: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
        //   243: lsub           
        //   244: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   247: ldc             ", pending in ms = "
        //   249: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   252: lload           4
        //   254: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   257: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   260: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   263: pop            
        //   264: aload_0        
        //   265: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   268: lload           6
        //   270: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.update:(J)V
        //   273: aload_0        
        //   274: aload_0        
        //   275: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //   278: aload_2        
        //   279: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   282: i2l            
        //   283: ladd           
        //   284: putfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //   287: aload_0        
        //   288: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   291: ifnull          315
        //   294: aload_0        
        //   295: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   298: iconst_1       
        //   299: aload_0        
        //   300: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   303: aload_0        
        //   304: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   307: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
        //   310: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onSampleRendered:(ZJJ)V
        //   315: aload_0        
        //   316: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   319: astore_3       
        //   320: aload_3        
        //   321: monitorenter   
        //   322: aload_0        
        //   323: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   326: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //   329: pop            
        //   330: aload_0        
        //   331: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   334: iload_1        
        //   335: aconst_null    
        //   336: aastore        
        //   337: aload_3        
        //   338: monitorexit    
        //   339: aload_0        
        //   340: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mDecoder:Landroid/media/MediaCodec;
        //   343: iload_1        
        //   344: iconst_0       
        //   345: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   348: aload_0        
        //   349: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   352: lconst_0       
        //   353: lcmp           
        //   354: ifgt            435
        //   357: ldc             "MediaDecoder2Audio"
        //   359: iconst_3       
        //   360: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   363: ifeq            435
        //   366: ldc             "MediaDecoder2Audio"
        //   368: new             Ljava/lang/StringBuilder;
        //   371: dup            
        //   372: invokespecial   java/lang/StringBuilder.<init>:()V
        //   375: ldc_w           "ReleaseOutputBuffer "
        //   378: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   381: iload_1        
        //   382: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   385: ldc_w           " size= "
        //   388: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   391: aload_2        
        //   392: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   395: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   398: ldc_w           " @"
        //   401: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   404: aload_2        
        //   405: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   408: ldc2_w          1000
        //   411: ldiv           
        //   412: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   415: ldc_w           " ms,flags "
        //   418: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   421: aload_2        
        //   422: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   425: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   428: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   431: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   434: pop            
        //   435: aload_0        
        //   436: aload_0        
        //   437: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   440: lconst_1       
        //   441: ladd           
        //   442: putfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   445: return         
        //   446: aload_2        
        //   447: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   450: newarray        B
        //   452: astore          8
        //   454: aload_3        
        //   455: aload           8
        //   457: iconst_0       
        //   458: aload_2        
        //   459: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   462: invokevirtual   java/nio/ByteBuffer.get:([BII)Ljava/nio/ByteBuffer;
        //   465: pop            
        //   466: aload           8
        //   468: astore_3       
        //   469: goto            23
        //   472: lload           4
        //   474: ldc2_w          15000
        //   477: lcmp           
        //   478: ifge            50
        //   481: aload_0        
        //   482: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mAudioTrack:Landroid/media/AudioTrack;
        //   485: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   488: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   491: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
        //   494: pop            
        //   495: goto            50
        //   498: astore_3       
        //   499: ldc             "MediaDecoder2Audio"
        //   501: new             Ljava/lang/StringBuilder;
        //   504: dup            
        //   505: invokespecial   java/lang/StringBuilder.<init>:()V
        //   508: ldc_w           "update clock has Exception"
        //   511: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   514: aload_3        
        //   515: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   518: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   521: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   524: pop            
        //   525: goto            273
        //   528: astore_2       
        //   529: aload_3        
        //   530: monitorexit    
        //   531: aload_2        
        //   532: athrow         
        //   533: astore_3       
        //   534: ldc             "MediaDecoder2Audio"
        //   536: ldc_w           "get un-documented exception as a result of releaseOutputBuffer()"
        //   539: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   542: pop            
        //   543: goto            348
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  84     264    498    528    Ljava/lang/Exception;
        //  264    273    498    528    Ljava/lang/Exception;
        //  322    339    528    533    Any
        //  339    348    533    546    Ljava/lang/Exception;
        //  529    531    528    533    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 265, Size: 265
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    void addToRenderer(final int n, final MediaCodec$BufferInfo mediaCodec$BufferInfo) {
        synchronized (this.mOutputBuffersQ) {
            this.mOutputBuffersQ.add(n);
            this.mOutputBufferInfo[n] = mediaCodec$BufferInfo;
        }
    }
    
    @Override
    void createRenderer() {
        this.createAudioTrack();
        this.mRenderState.onPaused();
        (this.mHandlerThread = new HandlerThread("RenderThreadAudeo", -2)).start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) {
            public void handleMessage(final Message p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_1        
                //     1: getfield        android/os/Message.what:I
                //     4: tableswitch {
                //                2: 45
                //                3: 445
                //                4: 363
                //                5: 595
                //          default: 36
                //        }
                //    36: ldc             "MediaDecoder2Audio"
                //    38: ldc             "RenderThreadAudeo had unknown message"
                //    40: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //    43: pop            
                //    44: return         
                //    45: aload_0        
                //    46: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //    49: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //    52: astore_1       
                //    53: aload_1        
                //    54: monitorenter   
                //    55: aload_0        
                //    56: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //    59: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //    62: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier.isPaused:()Z
                //    65: ifeq            96
                //    68: aload_0        
                //    69: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //    72: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //    75: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier.onPlaying:()V
                //    78: aload_0        
                //    79: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //    82: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //    85: invokevirtual   java/lang/Object.notify:()V
                //    88: ldc             "MediaDecoder2Audio"
                //    90: ldc             "render state play"
                //    92: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //    95: pop            
                //    96: aload_1        
                //    97: monitorexit    
                //    98: iconst_m1      
                //    99: istore_2       
                //   100: aconst_null    
                //   101: astore_1       
                //   102: aconst_null    
                //   103: astore          5
                //   105: aload_0        
                //   106: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   109: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
                //   112: astore          6
                //   114: aload           6
                //   116: monitorenter   
                //   117: aload_0        
                //   118: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   121: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
                //   124: invokevirtual   java/util/LinkedList.isEmpty:()Z
                //   127: ifne            168
                //   130: aload_0        
                //   131: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   134: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
                //   137: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
                //   140: checkcast       Ljava/lang/Integer;
                //   143: invokevirtual   java/lang/Integer.intValue:()I
                //   146: istore_2       
                //   147: aload_0        
                //   148: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   151: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                //   154: iload_2        
                //   155: aaload         
                //   156: astore_1       
                //   157: aload_0        
                //   158: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   161: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffers:[Ljava/nio/ByteBuffer;
                //   164: iload_2        
                //   165: aaload         
                //   166: astore          5
                //   168: aload           6
                //   170: monitorexit    
                //   171: aload_1        
                //   172: ifnull          269
                //   175: aload_1        
                //   176: getfield        android/media/MediaCodec$BufferInfo.flags:I
                //   179: iconst_4       
                //   180: iand           
                //   181: ifeq            269
                //   184: ldc             "MediaDecoder2Audio"
                //   186: ldc             "renderer got buffer BUFFER_FLAG_END_OF_STREAM"
                //   188: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   191: pop            
                //   192: aload_0        
                //   193: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   196: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //   199: ifnull          215
                //   202: aload_0        
                //   203: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   206: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //   209: iconst_1       
                //   210: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onEndOfStream:(Z)V
                //   215: aload_0        
                //   216: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   219: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //   222: astore_1       
                //   223: aload_1        
                //   224: monitorenter   
                //   225: aload_0        
                //   226: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   229: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //   232: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier.isPlaying:()Z
                //   235: ifne            345
                //   238: ldc             "MediaDecoder2Audio"
                //   240: ldc             "render state is not play"
                //   242: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   245: pop            
                //   246: aload_1        
                //   247: monitorexit    
                //   248: return         
                //   249: astore          5
                //   251: aload_1        
                //   252: monitorexit    
                //   253: aload           5
                //   255: athrow         
                //   256: astore          5
                //   258: aload_1        
                //   259: monitorexit    
                //   260: aload           5
                //   262: athrow         
                //   263: astore_1       
                //   264: aload           6
                //   266: monitorexit    
                //   267: aload_1        
                //   268: athrow         
                //   269: aload_0        
                //   270: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   273: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.startRenderer:()V
                //   276: aload_0        
                //   277: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   280: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)J
                //   283: lstore_3       
                //   284: iload_2        
                //   285: iflt            300
                //   288: aload_0        
                //   289: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   292: iload_2        
                //   293: aload_1        
                //   294: aload           5
                //   296: lload_3        
                //   297: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;ILandroid/media/MediaCodec$BufferInfo;Ljava/nio/ByteBuffer;J)V
                //   300: aload_0        
                //   301: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   304: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
                //   307: invokevirtual   java/util/LinkedList.isEmpty:()Z
                //   310: ifeq            98
                //   313: goto            215
                //   316: astore_1       
                //   317: ldc             "MediaDecoder2Audio"
                //   319: new             Ljava/lang/StringBuilder;
                //   322: dup            
                //   323: invokespecial   java/lang/StringBuilder.<init>:()V
                //   326: ldc             "getAudioHeaderPosition() has Exception"
                //   328: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   331: aload_1        
                //   332: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
                //   335: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   338: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
                //   341: pop            
                //   342: goto            215
                //   345: aload_0        
                //   346: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   349: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/os/Handler;
                //   352: iconst_1       
                //   353: ldc2_w          20
                //   356: invokevirtual   android/os/Handler.sendEmptyMessageDelayed:(IJ)Z
                //   359: pop            
                //   360: goto            246
                //   363: aload_0        
                //   364: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   367: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                //   370: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.pause:()J
                //   373: pop2           
                //   374: aload_0        
                //   375: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   378: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //   381: astore_1       
                //   382: aload_1        
                //   383: monitorenter   
                //   384: aload_0        
                //   385: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   388: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //   391: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier.onPaused:()V
                //   394: aload_0        
                //   395: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   398: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //   401: invokevirtual   java/lang/Object.notify:()V
                //   404: aload_1        
                //   405: monitorexit    
                //   406: ldc             "MediaDecoder2Audio"
                //   408: ldc             "render state pause"
                //   410: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   413: pop            
                //   414: aload_0        
                //   415: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   418: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //   421: ifnull          44
                //   424: aload_0        
                //   425: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   428: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //   431: iconst_1       
                //   432: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onPasued:(Z)V
                //   437: return         
                //   438: astore          5
                //   440: aload_1        
                //   441: monitorexit    
                //   442: aload           5
                //   444: athrow         
                //   445: ldc             "MediaDecoder2Audio"
                //   447: ldc             "render state flushing"
                //   449: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   452: pop            
                //   453: aload_0        
                //   454: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   457: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                //   460: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.flush:()V
                //   463: aload_0        
                //   464: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   467: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
                //   470: astore_1       
                //   471: aload_1        
                //   472: monitorenter   
                //   473: aload_0        
                //   474: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   477: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
                //   480: invokevirtual   java/util/LinkedList.clear:()V
                //   483: aload_1        
                //   484: monitorexit    
                //   485: aload_0        
                //   486: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   489: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/media/AudioTrack;
                //   492: ifnull          524
                //   495: aload_0        
                //   496: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   499: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/media/AudioTrack;
                //   502: invokevirtual   android/media/AudioTrack.stop:()V
                //   505: aload_0        
                //   506: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   509: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/media/AudioTrack;
                //   512: invokevirtual   android/media/AudioTrack.release:()V
                //   515: aload_0        
                //   516: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   519: aconst_null    
                //   520: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;Landroid/media/AudioTrack;)Landroid/media/AudioTrack;
                //   523: pop            
                //   524: aload_0        
                //   525: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   528: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)V
                //   531: aload_0        
                //   532: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   535: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.startRenderer:()V
                //   538: aload_0        
                //   539: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   542: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //   545: astore_1       
                //   546: aload_1        
                //   547: monitorenter   
                //   548: aload_0        
                //   549: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   552: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //   555: invokevirtual   java/lang/Object.notify:()V
                //   558: aload_1        
                //   559: monitorexit    
                //   560: ldc             "MediaDecoder2Audio"
                //   562: ldc             "render state flushed"
                //   564: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   567: pop            
                //   568: return         
                //   569: astore          5
                //   571: aload_1        
                //   572: monitorexit    
                //   573: aload           5
                //   575: athrow         
                //   576: astore_1       
                //   577: ldc             "MediaDecoder2Audio"
                //   579: ldc             "mAudioTrack already stopped/uninitialized"
                //   581: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   584: pop            
                //   585: goto            505
                //   588: astore          5
                //   590: aload_1        
                //   591: monitorexit    
                //   592: aload           5
                //   594: athrow         
                //   595: aload_0        
                //   596: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   599: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //   602: ifnull          44
                //   605: aload_0        
                //   606: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   609: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //   612: iconst_1       
                //   613: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onFlushed:(Z)V
                //   618: return         
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                             
                //  -----  -----  -----  -----  ---------------------------------
                //  55     96     256    263    Any
                //  96     98     256    263    Any
                //  117    168    263    269    Any
                //  168    171    263    269    Any
                //  225    246    249    256    Any
                //  246    248    249    256    Any
                //  251    253    249    256    Any
                //  258    260    256    263    Any
                //  264    267    263    269    Any
                //  276    284    316    345    Ljava/lang/Exception;
                //  345    360    249    256    Any
                //  384    406    438    445    Any
                //  440    442    438    445    Any
                //  473    485    569    576    Any
                //  495    505    576    588    Ljava/lang/IllegalStateException;
                //  548    560    588    595    Any
                //  571    573    569    576    Any
                //  590    592    588    595    Any
                // 
                // The error that occurred was:
                // 
                // java.lang.IndexOutOfBoundsException: Index: 293, Size: 293
                //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
                //     at java.util.ArrayList.get(ArrayList.java:429)
                //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
                //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
                //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
                //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
                //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
                // 
                throw new IllegalStateException("An error occurred while decompiling this method.");
            }
        };
    }
    
    @Override
    void flushRenderer() {
        if (this.mHandler == null) {
            return;
        }
        synchronized (this.mRenderState) {
            this.mHandler.sendEmptyMessage(2);
            try {
                this.mRenderState.wait();
                // monitorexit(this.mRenderState)
                this.mHandler.sendEmptyMessageDelayed(4, 20L);
            }
            catch (InterruptedException ex) {
                Log.d("MediaDecoder2Audio", "flushRenderer interrupted");
            }
        }
    }
    
    @Override
    void pauseRenderer() {
        if (this.mHandler != null) {
            synchronized (this.mRenderState) {
                this.mRenderState.onPausing();
                this.mHandler.sendEmptyMessage(3);
                this.mHandler.removeMessages(1);
                try {
                    this.mRenderState.wait();
                }
                catch (InterruptedException ex) {
                    Log.d("MediaDecoder2Audio", "pauseRenderer interrupted");
                }
            }
        }
    }
    
    @Override
    void startRenderer() {
        if (this.mAudioTrack == null || this.mAudioTrack.getPlayState() == 3 || this.mAudioTrack.getPlayState() == 0) {
            return;
        }
        Log.d("MediaDecoder2Audio", "start audiotrack ... ");
        this.mSampleCnt = 0L;
        try {
            this.mAudioTrack.play();
        }
        catch (IllegalStateException ex) {
            Log.w("MediaDecoder2Audio", "mAudioTrack already stopped/uninitialized");
        }
    }
    
    @Override
    void stopRenderer() {
        if (this.mHandler != null) {
            this.mHandler.removeMessages(1);
            this.mHandler.removeMessages(2);
            this.mHandler.removeMessages(3);
        }
        if (this.mHandlerThread != null) {
            this.mHandlerThread.quit();
        }
        while (true) {
            try {
                if (this.mAudioTrack != null) {
                    this.mAudioTrack.stop();
                    this.mAudioTrack.release();
                    this.mAudioTrack = null;
                }
                this.mSampleCnt = 0L;
            }
            catch (IllegalStateException ex) {
                Log.d("MediaDecoder2Audio", "AudioTrack.stop() has  IllegalStateException");
                continue;
            }
            break;
        }
    }
    
    @Override
    void unpauseRenderer() {
        if (this.mHandler != null) {
            synchronized (this.mRenderState) {
                this.mHandler.sendEmptyMessage(1);
                try {
                    this.mRenderState.wait();
                }
                catch (InterruptedException ex) {
                    Log.d("MediaDecoder2Audio", "unpauseRenderer interrupted");
                }
            }
        }
    }
}
