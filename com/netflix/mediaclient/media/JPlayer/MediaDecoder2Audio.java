// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import java.util.concurrent.TimeUnit;
import android.media.AudioTimestamp;
import android.media.AudioFormat$Builder;
import android.media.AudioAttributes$Builder;
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
    
    @TargetApi(21)
    private void createAudioTrack() {
        if (this.mAudioTrack == null) {
            Log.d("MediaDecoder2Audio", "create audiotrack ... ");
            this.mBufferSize = AudioTrack.getMinBufferSize(this.mSampleRate, this.mChannelConfig, 2);
            if (this.mBufferSize < 32768) {
                this.mBufferSize = 32768;
            }
            if (AndroidUtils.getAndroidVersion() >= 21) {
                this.mAudioTrack = new AudioTrack(new AudioAttributes$Builder().setUsage(1).setContentType(3).build(), new AudioFormat$Builder().setChannelMask(this.mChannelConfig).setEncoding(2).setSampleRate(this.mSampleRate).build(), this.mBufferSize, 1, 0);
            }
            else {
                this.mAudioTrack = new AudioTrack(3, this.mSampleRate, this.mChannelConfig, 2, this.mBufferSize, 1);
            }
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
        //     4: ifle            203
        //     7: lload           4
        //     9: ldc2_w          5000
        //    12: lcmp           
        //    13: ifge            331
        //    16: aload_0        
        //    17: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mAudioTrack:Landroid/media/AudioTrack;
        //    20: invokestatic    android/media/AudioTrack.getMinVolume:()F
        //    23: invokestatic    android/media/AudioTrack.getMinVolume:()F
        //    26: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
        //    29: pop            
        //    30: getstatic       com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.USE_ANDROID_L_API:Z
        //    33: ifeq            357
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
        //    67: ifeq            161
        //    70: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    73: ifeq            128
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
        //   102: ldc_w           " ms, total "
        //   105: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   108: aload_0        
        //   109: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //   112: aload_0        
        //   113: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleSize:I
        //   116: i2l            
        //   117: ldiv           
        //   118: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   121: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   124: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   127: pop            
        //   128: aload_0        
        //   129: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.getLatencyMethod:Ljava/lang/reflect/Method;
        //   132: ifnull          435
        //   135: aload_0        
        //   136: aload_2        
        //   137: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   140: invokespecial   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.getRenderingTimeWithHiddenApi:(J)J
        //   143: lstore          4
        //   145: aload_0        
        //   146: invokespecial   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.canAssumeRenderingStarted:()Z
        //   149: ifeq            448
        //   152: aload_0        
        //   153: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   156: lload           4
        //   158: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.update:(J)V
        //   161: aload_0        
        //   162: aload_0        
        //   163: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //   166: aload_2        
        //   167: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   170: i2l            
        //   171: ladd           
        //   172: putfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mSampleCnt:J
        //   175: aload_0        
        //   176: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   179: ifnull          203
        //   182: aload_0        
        //   183: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   186: iconst_1       
        //   187: aload_0        
        //   188: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   191: aload_0        
        //   192: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   195: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
        //   198: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onSampleRendered:(ZJJ)V
        //   203: aload_0        
        //   204: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   207: astore_3       
        //   208: aload_3        
        //   209: monitorenter   
        //   210: aload_0        
        //   211: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   214: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //   217: pop            
        //   218: aload_0        
        //   219: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   222: iload_1        
        //   223: aconst_null    
        //   224: aastore        
        //   225: aload_3        
        //   226: monitorexit    
        //   227: aload_0        
        //   228: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mDecoder:Landroid/media/MediaCodec;
        //   231: iload_1        
        //   232: iconst_0       
        //   233: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   236: aload_0        
        //   237: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   240: lconst_0       
        //   241: lcmp           
        //   242: ifgt            320
        //   245: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   248: ifeq            320
        //   251: ldc             "MediaDecoder2Audio"
        //   253: new             Ljava/lang/StringBuilder;
        //   256: dup            
        //   257: invokespecial   java/lang/StringBuilder.<init>:()V
        //   260: ldc_w           "ReleaseOutputBuffer "
        //   263: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   266: iload_1        
        //   267: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   270: ldc_w           " size= "
        //   273: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   276: aload_2        
        //   277: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   280: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   283: ldc_w           " @"
        //   286: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   289: aload_2        
        //   290: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   293: ldc2_w          1000
        //   296: ldiv           
        //   297: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   300: ldc_w           " ms,flags "
        //   303: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   306: aload_2        
        //   307: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   310: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   313: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   316: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   319: pop            
        //   320: aload_0        
        //   321: aload_0        
        //   322: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   325: lconst_1       
        //   326: ladd           
        //   327: putfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.nFrameRendered:J
        //   330: return         
        //   331: lload           4
        //   333: ldc2_w          15000
        //   336: lcmp           
        //   337: ifge            30
        //   340: aload_0        
        //   341: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mAudioTrack:Landroid/media/AudioTrack;
        //   344: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   347: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   350: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
        //   353: pop            
        //   354: goto            30
        //   357: aload_3        
        //   358: invokevirtual   java/nio/ByteBuffer.clear:()Ljava/nio/Buffer;
        //   361: pop            
        //   362: aload_3        
        //   363: iconst_0       
        //   364: invokevirtual   java/nio/ByteBuffer.position:(I)Ljava/nio/Buffer;
        //   367: pop            
        //   368: aload_3        
        //   369: invokevirtual   java/nio/ByteBuffer.hasArray:()Z
        //   372: ifeq            401
        //   375: aload_3        
        //   376: invokevirtual   java/nio/ByteBuffer.array:()[B
        //   379: astore_3       
        //   380: aload_3        
        //   381: ifnull          427
        //   384: aload_0        
        //   385: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mAudioTrack:Landroid/media/AudioTrack;
        //   388: aload_3        
        //   389: iconst_0       
        //   390: aload_2        
        //   391: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   394: invokevirtual   android/media/AudioTrack.write:([BII)I
        //   397: pop            
        //   398: goto            50
        //   401: aload_2        
        //   402: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   405: newarray        B
        //   407: astore          6
        //   409: aload_3        
        //   410: aload           6
        //   412: iconst_0       
        //   413: aload_2        
        //   414: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   417: invokevirtual   java/nio/ByteBuffer.get:([BII)Ljava/nio/ByteBuffer;
        //   420: pop            
        //   421: aload           6
        //   423: astore_3       
        //   424: goto            380
        //   427: aload_2        
        //   428: iconst_0       
        //   429: putfield        android/media/MediaCodec$BufferInfo.size:I
        //   432: goto            50
        //   435: aload_0        
        //   436: aload_2        
        //   437: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   440: invokespecial   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.getRenderingTimeGeneric:(J)J
        //   443: lstore          4
        //   445: goto            145
        //   448: aload_0        
        //   449: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   452: lload           4
        //   454: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.updateAndPause:(J)V
        //   457: goto            161
        //   460: astore_3       
        //   461: ldc             "MediaDecoder2Audio"
        //   463: new             Ljava/lang/StringBuilder;
        //   466: dup            
        //   467: invokespecial   java/lang/StringBuilder.<init>:()V
        //   470: ldc_w           "update clock has Exception"
        //   473: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   476: aload_3        
        //   477: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   480: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   483: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   486: pop            
        //   487: goto            161
        //   490: astore_2       
        //   491: aload_3        
        //   492: monitorexit    
        //   493: aload_2        
        //   494: athrow         
        //   495: astore_3       
        //   496: ldc             "MediaDecoder2Audio"
        //   498: ldc_w           "get un-documented exception as a result of releaseOutputBuffer()"
        //   501: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   504: pop            
        //   505: goto            236
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  70     128    460    490    Ljava/lang/Exception;
        //  128    145    460    490    Ljava/lang/Exception;
        //  145    161    460    490    Ljava/lang/Exception;
        //  210    227    490    495    Any
        //  227    236    495    508    Ljava/lang/Exception;
        //  435    445    460    490    Ljava/lang/Exception;
        //  448    457    460    490    Ljava/lang/Exception;
        //  491    493    490    495    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 244, Size: 244
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
    
    public void setAudioDuck(final boolean b) {
        if (this.mAudioTrack != null && this.mAudioTrack.getState() != 0) {
            float volume = 1.0f;
            if (b) {
                volume = 0.3f;
            }
            if (AndroidUtils.getAndroidVersion() < 21) {
                this.mAudioTrack.setStereoVolume(volume, volume);
                return;
            }
            this.mAudioTrack.setVolume(volume);
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
