// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

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
    private MediaDecoderPipe2$LocalStateNotifier mRenderState;
    private long mSampleCnt;
    private int mSampleRate;
    private int mSampleSize;
    private long nFrameRendered;
    
    public MediaDecoder2Audio(final MediaDecoderBase$InputDataSource mediaDecoderBase$InputDataSource, final String s, final MediaFormat mediaFormat, final MediaDecoderBase$EventListener mediaDecoderBase$EventListener) {
        super(mediaDecoderBase$InputDataSource, s, mediaFormat, null, null, mediaDecoderBase$EventListener);
        this.mSampleRate = 48000;
        this.mChannelConfig = 12;
        this.nFrameRendered = 0L;
        this.mRenderState = new MediaDecoderPipe2$LocalStateNotifier(this);
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
            if (Log.isLoggable()) {
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
        //     4: ifle            305
        //     7: lload           4
        //     9: ldc2_w          5000
        //    12: lcmp           
        //    13: ifge            433
        //    16: aload_0        
        //    17: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mAudioTrack:Landroid/media/AudioTrack;
        //    20: invokestatic    android/media/AudioTrack.getMinVolume:()F
        //    23: invokestatic    android/media/AudioTrack.getMinVolume:()F
        //    26: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
        //    29: pop            
        //    30: getstatic       com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.USE_ANDROID_L_API:Z
        //    33: ifeq            459
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
        //    67: ifeq            263
        //    70: aload_0        
        //    71: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //    74: aload_0        
        //    75: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleSize:I
        //    78: i2l            
        //    79: ldiv           
        //    80: aload_0        
        //    81: invokespecial   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.getAudioHeaderPosition:()J
        //    84: lsub           
        //    85: ldc2_w          1000
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
        //   117: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   120: ifeq            247
        //   123: ldc             "MediaDecoder2Audio"
        //   125: new             Ljava/lang/StringBuilder;
        //   128: dup            
        //   129: invokespecial   java/lang/StringBuilder.<init>:()V
        //   132: ldc             "timestamp = "
        //   134: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   137: aload_2        
        //   138: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   141: ldc2_w          1000
        //   144: ldiv           
        //   145: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   148: ldc             " ms, "
        //   150: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   153: ldc             "total "
        //   155: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   158: aload_0        
        //   159: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //   162: aload_0        
        //   163: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleSize:I
        //   166: i2l            
        //   167: ldiv           
        //   168: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   171: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   174: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   177: pop            
        //   178: ldc             "MediaDecoder2Audio"
        //   180: new             Ljava/lang/StringBuilder;
        //   183: dup            
        //   184: invokespecial   java/lang/StringBuilder.<init>:()V
        //   187: ldc             "AudioClock: predicted "
        //   189: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   192: aload_0        
        //   193: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   196: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
        //   199: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   202: ldc             " ms, update to = "
        //   204: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   207: lload           6
        //   209: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   212: ldc             " ms, delta = "
        //   214: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   217: lload           6
        //   219: aload_0        
        //   220: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   223: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
        //   226: lsub           
        //   227: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   230: ldc             ", pending in ms = "
        //   232: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   235: lload           4
        //   237: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   240: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   243: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   246: pop            
        //   247: aload_0        
        //   248: invokespecial   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.canAssumeRenderingStarted:()Z
        //   251: ifeq            537
        //   254: aload_0        
        //   255: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   258: lload           6
        //   260: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.update:(J)V
        //   263: aload_0        
        //   264: aload_0        
        //   265: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //   268: aload_2        
        //   269: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   272: i2l            
        //   273: ladd           
        //   274: putfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //   277: aload_0        
        //   278: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   281: ifnull          305
        //   284: aload_0        
        //   285: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   288: iconst_1       
        //   289: aload_0        
        //   290: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   293: aload_0        
        //   294: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   297: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
        //   300: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onSampleRendered:(ZJJ)V
        //   305: aload_0        
        //   306: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   309: astore_3       
        //   310: aload_3        
        //   311: monitorenter   
        //   312: aload_0        
        //   313: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   316: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //   319: pop            
        //   320: aload_0        
        //   321: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   324: iload_1        
        //   325: aconst_null    
        //   326: aastore        
        //   327: aload_3        
        //   328: monitorexit    
        //   329: aload_0        
        //   330: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mDecoder:Landroid/media/MediaCodec;
        //   333: iload_1        
        //   334: iconst_0       
        //   335: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   338: aload_0        
        //   339: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   342: lconst_0       
        //   343: lcmp           
        //   344: ifgt            422
        //   347: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   350: ifeq            422
        //   353: ldc             "MediaDecoder2Audio"
        //   355: new             Ljava/lang/StringBuilder;
        //   358: dup            
        //   359: invokespecial   java/lang/StringBuilder.<init>:()V
        //   362: ldc_w           "ReleaseOutputBuffer "
        //   365: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   368: iload_1        
        //   369: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   372: ldc_w           " size= "
        //   375: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   378: aload_2        
        //   379: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   382: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   385: ldc_w           " @"
        //   388: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   391: aload_2        
        //   392: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   395: ldc2_w          1000
        //   398: ldiv           
        //   399: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   402: ldc_w           " ms,flags "
        //   405: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   408: aload_2        
        //   409: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   412: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   415: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   418: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   421: pop            
        //   422: aload_0        
        //   423: aload_0        
        //   424: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   427: lconst_1       
        //   428: ladd           
        //   429: putfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   432: return         
        //   433: lload           4
        //   435: ldc2_w          15000
        //   438: lcmp           
        //   439: ifge            30
        //   442: aload_0        
        //   443: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mAudioTrack:Landroid/media/AudioTrack;
        //   446: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   449: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   452: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
        //   455: pop            
        //   456: goto            30
        //   459: aload_3        
        //   460: invokevirtual   java/nio/ByteBuffer.clear:()Ljava/nio/Buffer;
        //   463: pop            
        //   464: aload_3        
        //   465: iconst_0       
        //   466: invokevirtual   java/nio/ByteBuffer.position:(I)Ljava/nio/Buffer;
        //   469: pop            
        //   470: aload_3        
        //   471: invokevirtual   java/nio/ByteBuffer.hasArray:()Z
        //   474: ifeq            503
        //   477: aload_3        
        //   478: invokevirtual   java/nio/ByteBuffer.array:()[B
        //   481: astore_3       
        //   482: aload_3        
        //   483: ifnull          529
        //   486: aload_0        
        //   487: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mAudioTrack:Landroid/media/AudioTrack;
        //   490: aload_3        
        //   491: iconst_0       
        //   492: aload_2        
        //   493: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   496: invokevirtual   android/media/AudioTrack.write:([BII)I
        //   499: pop            
        //   500: goto            50
        //   503: aload_2        
        //   504: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   507: newarray        B
        //   509: astore          8
        //   511: aload_3        
        //   512: aload           8
        //   514: iconst_0       
        //   515: aload_2        
        //   516: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   519: invokevirtual   java/nio/ByteBuffer.get:([BII)Ljava/nio/ByteBuffer;
        //   522: pop            
        //   523: aload           8
        //   525: astore_3       
        //   526: goto            482
        //   529: aload_2        
        //   530: iconst_0       
        //   531: putfield        android/media/MediaCodec$BufferInfo.size:I
        //   534: goto            50
        //   537: aload_0        
        //   538: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   541: lload           6
        //   543: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.updateAndPause:(J)V
        //   546: goto            263
        //   549: astore_3       
        //   550: ldc             "MediaDecoder2Audio"
        //   552: new             Ljava/lang/StringBuilder;
        //   555: dup            
        //   556: invokespecial   java/lang/StringBuilder.<init>:()V
        //   559: ldc_w           "update clock has Exception"
        //   562: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   565: aload_3        
        //   566: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   569: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   572: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   575: pop            
        //   576: goto            263
        //   579: astore_2       
        //   580: aload_3        
        //   581: monitorexit    
        //   582: aload_2        
        //   583: athrow         
        //   584: astore_3       
        //   585: ldc             "MediaDecoder2Audio"
        //   587: ldc_w           "get un-documented exception as a result of releaseOutputBuffer()"
        //   590: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   593: pop            
        //   594: goto            338
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  70     247    549    579    Ljava/lang/Exception;
        //  247    263    549    579    Ljava/lang/Exception;
        //  312    329    579    584    Any
        //  329    338    584    597    Ljava/lang/Exception;
        //  537    546    549    579    Ljava/lang/Exception;
        //  580    582    579    584    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 287, Size: 287
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
        this.mHandler = new MediaDecoder2Audio$1(this, this.mHandlerThread.getLooper());
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
