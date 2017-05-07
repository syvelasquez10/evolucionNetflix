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
    private static final int AUDIO_CLOCK_OFFSET_SAMPLES = 4800;
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
    
    public MediaDecoder2Audio(final InputDataSource inputDataSource, final String s, final MediaFormat mediaFormat, final EventListener eventListener) throws Exception {
        super(inputDataSource, s, mediaFormat, null, null, eventListener);
        this.mSampleRate = 48000;
        this.mChannelConfig = 12;
        this.nFrameRendered = 0L;
        this.mRenderState = new LocalStateNotifier(this);
    }
    
    private boolean canAssumeRenderingStarted() {
        return this.mSampleCnt > this.mBufferSize / 2;
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
        if (MediaDecoder2Audio.USE_ANDROID_L_API || this.mAudioUseGetTimestampAPI) {
            final AudioTimestamp audioTimestamp = new AudioTimestamp();
            if (this.mAudioTrack.getTimestamp(audioTimestamp)) {
                if (audioTimestamp.framePosition > 4800L) {
                    return audioTimestamp.framePosition - 4800L;
                }
                return 0L;
            }
        }
        return this.mAudioTrack.getPlaybackHeadPosition();
    }
    
    private long getAudioPresentationLatencyMs() {
        if (MediaDecoder2Audio.USE_ANDROID_L_API || this.mAudioUseGetTimestampAPI) {
            final AudioTimestamp audioTimestamp = new AudioTimestamp();
            if (this.mAudioTrack.getTimestamp(audioTimestamp)) {
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
        //     0: aload_2        
        //     1: getfield        android/media/MediaCodec$BufferInfo.size:I
        //     4: ifle            308
        //     7: lload           4
        //     9: ldc2_w          5000
        //    12: lcmp           
        //    13: ifge            439
        //    16: aload_0        
        //    17: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mAudioTrack:Landroid/media/AudioTrack;
        //    20: invokestatic    android/media/AudioTrack.getMinVolume:()F
        //    23: invokestatic    android/media/AudioTrack.getMinVolume:()F
        //    26: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
        //    29: pop            
        //    30: getstatic       com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.USE_ANDROID_L_API:Z
        //    33: ifeq            465
        //    36: aload_0        
        //    37: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mAudioTrack:Landroid/media/AudioTrack;
        //    40: aload_3        
        //    41: aload_2        
        //    42: getfield        android/media/MediaCodec$BufferInfo.size:I
        //    45: iconst_0       
        //    46: invokevirtual   android/media/AudioTrack.write:(Ljava/nio/ByteBuffer;II)I
        //    49: pop            
        //    50: aload_0        
        //    51: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //    54: aload_0        
        //    55: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //    58: aload_0        
        //    59: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleSize:I
        //    62: i2l            
        //    63: ldiv           
        //    64: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.shouldUpdate:(J)Z
        //    67: ifeq            266
        //    70: ldc2_w          1000
        //    73: aload_0        
        //    74: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //    77: aload_0        
        //    78: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleSize:I
        //    81: i2l            
        //    82: ldiv           
        //    83: aload_0        
        //    84: invokespecial   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.getAudioHeaderPosition:()J
        //    87: lsub           
        //    88: lmul           
        //    89: aload_0        
        //    90: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleRate:I
        //    93: i2l            
        //    94: ldiv           
        //    95: aload_0        
        //    96: invokespecial   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.getAudioPresentationLatencyMs:()J
        //    99: ladd           
        //   100: lstore          4
        //   102: getstatic       java/util/concurrent/TimeUnit.MICROSECONDS:Ljava/util/concurrent/TimeUnit;
        //   105: aload_2        
        //   106: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   109: invokevirtual   java/util/concurrent/TimeUnit.toMillis:(J)J
        //   112: lload           4
        //   114: lsub           
        //   115: lstore          6
        //   117: ldc             "MediaDecoder2Audio"
        //   119: iconst_3       
        //   120: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   123: ifeq            250
        //   126: ldc             "MediaDecoder2Audio"
        //   128: new             Ljava/lang/StringBuilder;
        //   131: dup            
        //   132: invokespecial   java/lang/StringBuilder.<init>:()V
        //   135: ldc             "timestamp = "
        //   137: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   140: aload_2        
        //   141: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   144: ldc2_w          1000
        //   147: ldiv           
        //   148: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   151: ldc             " ms, "
        //   153: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   156: ldc             "total "
        //   158: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   161: aload_0        
        //   162: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //   165: aload_0        
        //   166: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleSize:I
        //   169: i2l            
        //   170: ldiv           
        //   171: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   174: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   177: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   180: pop            
        //   181: ldc             "MediaDecoder2Audio"
        //   183: new             Ljava/lang/StringBuilder;
        //   186: dup            
        //   187: invokespecial   java/lang/StringBuilder.<init>:()V
        //   190: ldc             "AudioClock: predicted "
        //   192: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   195: aload_0        
        //   196: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   199: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
        //   202: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   205: ldc             " ms, update to = "
        //   207: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   210: lload           6
        //   212: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   215: ldc             " ms, delta = "
        //   217: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   220: lload           6
        //   222: aload_0        
        //   223: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   226: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
        //   229: lsub           
        //   230: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   233: ldc             ", pending in ms = "
        //   235: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   238: lload           4
        //   240: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   243: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   246: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   249: pop            
        //   250: aload_0        
        //   251: invokespecial   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.canAssumeRenderingStarted:()Z
        //   254: ifeq            543
        //   257: aload_0        
        //   258: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   261: lload           6
        //   263: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.update:(J)V
        //   266: aload_0        
        //   267: aload_0        
        //   268: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //   271: aload_2        
        //   272: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   275: i2l            
        //   276: ladd           
        //   277: putfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //   280: aload_0        
        //   281: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   284: ifnull          308
        //   287: aload_0        
        //   288: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   291: iconst_1       
        //   292: aload_0        
        //   293: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   296: aload_0        
        //   297: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   300: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
        //   303: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onSampleRendered:(ZJJ)V
        //   308: aload_0        
        //   309: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   312: astore_3       
        //   313: aload_3        
        //   314: monitorenter   
        //   315: aload_0        
        //   316: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   319: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //   322: pop            
        //   323: aload_0        
        //   324: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   327: iload_1        
        //   328: aconst_null    
        //   329: aastore        
        //   330: aload_3        
        //   331: monitorexit    
        //   332: aload_0        
        //   333: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mDecoder:Landroid/media/MediaCodec;
        //   336: iload_1        
        //   337: iconst_0       
        //   338: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   341: aload_0        
        //   342: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   345: lconst_0       
        //   346: lcmp           
        //   347: ifgt            428
        //   350: ldc             "MediaDecoder2Audio"
        //   352: iconst_3       
        //   353: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   356: ifeq            428
        //   359: ldc             "MediaDecoder2Audio"
        //   361: new             Ljava/lang/StringBuilder;
        //   364: dup            
        //   365: invokespecial   java/lang/StringBuilder.<init>:()V
        //   368: ldc_w           "ReleaseOutputBuffer "
        //   371: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   374: iload_1        
        //   375: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   378: ldc_w           " size= "
        //   381: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   384: aload_2        
        //   385: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   388: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   391: ldc_w           " @"
        //   394: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   397: aload_2        
        //   398: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   401: ldc2_w          1000
        //   404: ldiv           
        //   405: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   408: ldc_w           " ms,flags "
        //   411: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   414: aload_2        
        //   415: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   418: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   421: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   424: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   427: pop            
        //   428: aload_0        
        //   429: aload_0        
        //   430: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   433: lconst_1       
        //   434: ladd           
        //   435: putfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   438: return         
        //   439: lload           4
        //   441: ldc2_w          15000
        //   444: lcmp           
        //   445: ifge            30
        //   448: aload_0        
        //   449: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mAudioTrack:Landroid/media/AudioTrack;
        //   452: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   455: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   458: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
        //   461: pop            
        //   462: goto            30
        //   465: aload_3        
        //   466: invokevirtual   java/nio/ByteBuffer.clear:()Ljava/nio/Buffer;
        //   469: pop            
        //   470: aload_3        
        //   471: iconst_0       
        //   472: invokevirtual   java/nio/ByteBuffer.position:(I)Ljava/nio/Buffer;
        //   475: pop            
        //   476: aload_3        
        //   477: invokevirtual   java/nio/ByteBuffer.hasArray:()Z
        //   480: ifeq            509
        //   483: aload_3        
        //   484: invokevirtual   java/nio/ByteBuffer.array:()[B
        //   487: astore_3       
        //   488: aload_3        
        //   489: ifnull          535
        //   492: aload_0        
        //   493: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mAudioTrack:Landroid/media/AudioTrack;
        //   496: aload_3        
        //   497: iconst_0       
        //   498: aload_2        
        //   499: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   502: invokevirtual   android/media/AudioTrack.write:([BII)I
        //   505: pop            
        //   506: goto            50
        //   509: aload_2        
        //   510: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   513: newarray        B
        //   515: astore          8
        //   517: aload_3        
        //   518: aload           8
        //   520: iconst_0       
        //   521: aload_2        
        //   522: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   525: invokevirtual   java/nio/ByteBuffer.get:([BII)Ljava/nio/ByteBuffer;
        //   528: pop            
        //   529: aload           8
        //   531: astore_3       
        //   532: goto            488
        //   535: aload_2        
        //   536: iconst_0       
        //   537: putfield        android/media/MediaCodec$BufferInfo.size:I
        //   540: goto            50
        //   543: aload_0        
        //   544: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   547: lload           6
        //   549: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.updateAndPause:(J)V
        //   552: goto            266
        //   555: astore_3       
        //   556: ldc             "MediaDecoder2Audio"
        //   558: new             Ljava/lang/StringBuilder;
        //   561: dup            
        //   562: invokespecial   java/lang/StringBuilder.<init>:()V
        //   565: ldc_w           "update clock has Exception"
        //   568: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   571: aload_3        
        //   572: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   575: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   578: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   581: pop            
        //   582: goto            266
        //   585: astore_2       
        //   586: aload_3        
        //   587: monitorexit    
        //   588: aload_2        
        //   589: athrow         
        //   590: astore_3       
        //   591: ldc             "MediaDecoder2Audio"
        //   593: ldc_w           "get un-documented exception as a result of releaseOutputBuffer()"
        //   596: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   599: pop            
        //   600: goto            341
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  70     250    555    585    Ljava/lang/Exception;
        //  250    266    555    585    Ljava/lang/Exception;
        //  315    332    585    590    Any
        //  332    341    590    603    Ljava/lang/Exception;
        //  543    552    555    585    Ljava/lang/Exception;
        //  586    588    585    590    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 291, Size: 291
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
