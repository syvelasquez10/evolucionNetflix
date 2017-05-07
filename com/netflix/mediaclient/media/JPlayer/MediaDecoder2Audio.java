// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import java.util.concurrent.TimeUnit;
import android.media.AudioTimestamp;
import com.netflix.mediaclient.Log;
import java.nio.ByteBuffer;
import android.media.MediaCodec$BufferInfo;
import com.netflix.mediaclient.util.AndroidUtils;
import android.media.MediaCrypto;
import android.view.Surface;
import android.media.MediaFormat;
import android.os.HandlerThread;
import android.os.Handler;
import android.media.AudioTrack;
import java.lang.reflect.Method;
import android.annotation.TargetApi;

@TargetApi(19)
public class MediaDecoder2Audio extends MediaDecoderPipe2
{
    private static final int AUDIO_CLOCK_OFFSET_SAMPLES = 4800;
    private static final int MSG_RENDER_FLUSH = 2;
    private static final int MSG_RENDER_FLUSHED = 4;
    private static final int MSG_RENDER_FRAME = 1;
    private static final int MSG_RENDER_PAUSE = 3;
    private static final int MSG_RENDER_STOP = 5;
    private static final String TAG = "MediaDecoder2Audio";
    private Method getLatencyMethod;
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
        if (AndroidUtils.getAndroidVersion() < 18) {
            return;
        }
        try {
            this.getLatencyMethod = AudioTrack.class.getMethod("getLatency", (Class<?>[])null);
        }
        catch (NoSuchMethodException ex) {}
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
    
    private long getRenderingTimeGeneric(long n) {
        final long n2 = (this.mSampleCnt / this.mSampleSize - this.getAudioHeaderPosition()) * 1000L / this.mSampleRate + this.getAudioPresentationLatencyMs();
        n = TimeUnit.MICROSECONDS.toMillis(n) - n2;
        if (Log.isLoggable()) {
            Log.d("MediaDecoder2Audio", "AudioClock: predicted " + this.mClock.get() + " ms, update to = " + n + " ms, delta = " + (n - this.mClock.get()) + ", pending in ms = " + n2);
        }
        return n;
    }
    
    private long getRenderingTimeWithHiddenApi(long n) {
        final long n2 = (this.mSampleCnt / this.mSampleSize - this.mAudioTrack.getPlaybackHeadPosition()) * 1000L / this.mSampleRate;
        final long n3 = n = TimeUnit.MICROSECONDS.toMillis(n) - n2;
        try {
            final Integer n4 = (Integer)this.getLatencyMethod.invoke(this.mAudioTrack, (Object[])null);
            n = n3;
            if (n4 >= 5000) {
                return n3;
            }
            n = n3;
            final long n5 = Math.max(0, n4 - this.mBufferSize * 1000 / (this.mSampleSize * this.mSampleRate));
            final long n6 = n = n3 - n5;
            if (Log.isLoggable()) {
                n = n6;
                Log.d("MediaDecoder2Audio", "latency = " + n4 + ", adjustedlatency = " + n5);
                n = n6;
                Log.d("MediaDecoder2Audio", "AudioClock: predicted " + this.mClock.get() + " ms, update to = " + n6 + " ms, delta = " + (n6 - this.mClock.get()) + ", pending in ms = " + n2);
            }
            return n6;
        }
        catch (Exception ex) {
            Log.w("MediaDecoder2Audio", "can't getLatency");
            this.getLatencyMethod = null;
            return n;
        }
    }
    
    private void releaseAudioTrack() {
        try {
            if (this.mAudioTrack != null) {
                this.mAudioTrack.stop();
                this.mAudioTrack.release();
                this.mAudioTrack = null;
            }
        }
        catch (IllegalStateException ex) {
            Log.d("MediaDecoder2Audio", "AudioTrack.stop() has  IllegalStateException");
        }
    }
    
    private void renderOneFrame(final int p0, final MediaCodec$BufferInfo p1, final ByteBuffer p2, final long p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: getfield        android/media/MediaCodec$BufferInfo.size:I
        //     4: ifle            209
        //     7: lload           4
        //     9: ldc2_w          5000
        //    12: lcmp           
        //    13: ifge            337
        //    16: aload_0        
        //    17: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mAudioTrack:Landroid/media/AudioTrack;
        //    20: invokestatic    android/media/AudioTrack.getMinVolume:()F
        //    23: invokestatic    android/media/AudioTrack.getMinVolume:()F
        //    26: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
        //    29: pop            
        //    30: getstatic       com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.USE_ANDROID_L_API:Z
        //    33: ifeq            363
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
        //    67: ifeq            167
        //    70: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    73: ifeq            134
        //    76: ldc             "MediaDecoder2Audio"
        //    78: new             Ljava/lang/StringBuilder;
        //    81: dup            
        //    82: invokespecial   java/lang/StringBuilder.<init>:()V
        //    85: ldc_w           "timestamp = "
        //    88: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    91: aload_2        
        //    92: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //    95: ldc2_w          1000
        //    98: ldiv           
        //    99: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   102: ldc_w           " ms, "
        //   105: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   108: ldc_w           "total "
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: aload_0        
        //   115: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //   118: aload_0        
        //   119: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleSize:I
        //   122: i2l            
        //   123: ldiv           
        //   124: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   127: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   130: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   133: pop            
        //   134: aload_0        
        //   135: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.getLatencyMethod:Ljava/lang/reflect/Method;
        //   138: ifnull          441
        //   141: aload_0        
        //   142: aload_2        
        //   143: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   146: invokespecial   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.getRenderingTimeWithHiddenApi:(J)J
        //   149: lstore          4
        //   151: aload_0        
        //   152: invokespecial   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.canAssumeRenderingStarted:()Z
        //   155: ifeq            454
        //   158: aload_0        
        //   159: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   162: lload           4
        //   164: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.update:(J)V
        //   167: aload_0        
        //   168: aload_0        
        //   169: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //   172: aload_2        
        //   173: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   176: i2l            
        //   177: ladd           
        //   178: putfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //   181: aload_0        
        //   182: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   185: ifnull          209
        //   188: aload_0        
        //   189: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   192: iconst_1       
        //   193: aload_0        
        //   194: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   197: aload_0        
        //   198: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   201: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
        //   204: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onSampleRendered:(ZJJ)V
        //   209: aload_0        
        //   210: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   213: astore_3       
        //   214: aload_3        
        //   215: monitorenter   
        //   216: aload_0        
        //   217: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   220: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //   223: pop            
        //   224: aload_0        
        //   225: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   228: iload_1        
        //   229: aconst_null    
        //   230: aastore        
        //   231: aload_3        
        //   232: monitorexit    
        //   233: aload_0        
        //   234: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mDecoder:Landroid/media/MediaCodec;
        //   237: iload_1        
        //   238: iconst_0       
        //   239: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   242: aload_0        
        //   243: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   246: lconst_0       
        //   247: lcmp           
        //   248: ifgt            326
        //   251: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   254: ifeq            326
        //   257: ldc             "MediaDecoder2Audio"
        //   259: new             Ljava/lang/StringBuilder;
        //   262: dup            
        //   263: invokespecial   java/lang/StringBuilder.<init>:()V
        //   266: ldc_w           "ReleaseOutputBuffer "
        //   269: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   272: iload_1        
        //   273: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   276: ldc_w           " size= "
        //   279: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   282: aload_2        
        //   283: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   286: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   289: ldc_w           " @"
        //   292: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   295: aload_2        
        //   296: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   299: ldc2_w          1000
        //   302: ldiv           
        //   303: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   306: ldc_w           " ms,flags "
        //   309: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   312: aload_2        
        //   313: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   316: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   319: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   322: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   325: pop            
        //   326: aload_0        
        //   327: aload_0        
        //   328: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   331: lconst_1       
        //   332: ladd           
        //   333: putfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   336: return         
        //   337: lload           4
        //   339: ldc2_w          15000
        //   342: lcmp           
        //   343: ifge            30
        //   346: aload_0        
        //   347: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mAudioTrack:Landroid/media/AudioTrack;
        //   350: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   353: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   356: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
        //   359: pop            
        //   360: goto            30
        //   363: aload_3        
        //   364: invokevirtual   java/nio/ByteBuffer.clear:()Ljava/nio/Buffer;
        //   367: pop            
        //   368: aload_3        
        //   369: iconst_0       
        //   370: invokevirtual   java/nio/ByteBuffer.position:(I)Ljava/nio/Buffer;
        //   373: pop            
        //   374: aload_3        
        //   375: invokevirtual   java/nio/ByteBuffer.hasArray:()Z
        //   378: ifeq            407
        //   381: aload_3        
        //   382: invokevirtual   java/nio/ByteBuffer.array:()[B
        //   385: astore_3       
        //   386: aload_3        
        //   387: ifnull          433
        //   390: aload_0        
        //   391: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mAudioTrack:Landroid/media/AudioTrack;
        //   394: aload_3        
        //   395: iconst_0       
        //   396: aload_2        
        //   397: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   400: invokevirtual   android/media/AudioTrack.write:([BII)I
        //   403: pop            
        //   404: goto            50
        //   407: aload_2        
        //   408: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   411: newarray        B
        //   413: astore          6
        //   415: aload_3        
        //   416: aload           6
        //   418: iconst_0       
        //   419: aload_2        
        //   420: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   423: invokevirtual   java/nio/ByteBuffer.get:([BII)Ljava/nio/ByteBuffer;
        //   426: pop            
        //   427: aload           6
        //   429: astore_3       
        //   430: goto            386
        //   433: aload_2        
        //   434: iconst_0       
        //   435: putfield        android/media/MediaCodec$BufferInfo.size:I
        //   438: goto            50
        //   441: aload_0        
        //   442: aload_2        
        //   443: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   446: invokespecial   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.getRenderingTimeGeneric:(J)J
        //   449: lstore          4
        //   451: goto            151
        //   454: aload_0        
        //   455: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   458: lload           4
        //   460: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.updateAndPause:(J)V
        //   463: goto            167
        //   466: astore_3       
        //   467: ldc             "MediaDecoder2Audio"
        //   469: new             Ljava/lang/StringBuilder;
        //   472: dup            
        //   473: invokespecial   java/lang/StringBuilder.<init>:()V
        //   476: ldc_w           "update clock has Exception"
        //   479: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   482: aload_3        
        //   483: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   486: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   489: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   492: pop            
        //   493: goto            167
        //   496: astore_2       
        //   497: aload_3        
        //   498: monitorexit    
        //   499: aload_2        
        //   500: athrow         
        //   501: astore_3       
        //   502: ldc             "MediaDecoder2Audio"
        //   504: ldc_w           "get un-documented exception as a result of releaseOutputBuffer()"
        //   507: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   510: pop            
        //   511: goto            242
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  70     134    466    496    Ljava/lang/Exception;
        //  134    151    466    496    Ljava/lang/Exception;
        //  151    167    466    496    Ljava/lang/Exception;
        //  216    233    496    501    Any
        //  233    242    501    514    Ljava/lang/Exception;
        //  441    451    466    496    Ljava/lang/Exception;
        //  454    463    466    496    Ljava/lang/Exception;
        //  497    499    496    501    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 246, Size: 246
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
        synchronized (this) {
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
    }
    
    @Override
    void stopRenderer() {
        synchronized (this) {
            if (this.mHandler != null) {
                this.mHandler.removeMessages(1);
                this.mHandler.removeMessages(2);
                this.mHandler.removeMessages(3);
            }
            if (this.mHandlerThread != null) {
                this.mHandlerThread.quit();
            }
            this.releaseAudioTrack();
            this.mSampleCnt = 0L;
        }
    }
    
    @Override
    void terminateRenderer() {
        Log.d("MediaDecoder2Audio", "terminateRenderer ... ");
        Label_0056: {
            if (this.mHandler == null) {
                break Label_0056;
            }
            synchronized (this.mRenderState) {
                this.mRenderState.onStopping();
                this.mHandler.sendEmptyMessage(5);
                this.mHandler.removeMessages(1);
                try {
                    this.mRenderState.wait();
                    // monitorexit(this.mRenderState)
                    this.releaseAudioTrack();
                    Log.d("MediaDecoder2Audio", "terminateRenderer ... done");
                }
                catch (InterruptedException ex) {
                    Log.d("MediaDecoder2Audio", "terminateRenderer interrupted");
                }
            }
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
