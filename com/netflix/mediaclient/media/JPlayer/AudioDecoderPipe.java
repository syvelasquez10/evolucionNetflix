// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import com.netflix.mediaclient.Log;
import android.media.MediaCodec$BufferInfo;
import java.nio.ByteBuffer;
import com.netflix.mediaclient.util.AndroidUtils;
import org.json.JSONObject;
import android.view.Surface;
import android.media.MediaFormat;
import android.media.AudioTrack;
import java.lang.reflect.Method;
import android.annotation.TargetApi;

@TargetApi(16)
public class AudioDecoderPipe extends MediaDecoderPipe
{
    private static final String TAG = "MediaPipeAudio";
    private Method getLatencyMethod;
    private AudioTrack mAudioTrack;
    private int mBufferSize;
    private int mChannelConfig;
    private long mSampleCnt;
    private int mSampleRate;
    private int mSampleSize;
    private long nFrameRendered;
    
    public AudioDecoderPipe(final MediaDecoderPipe$InputDataSource mediaDecoderPipe$InputDataSource, final String s, final MediaFormat mediaFormat, final Surface surface, final String s2, final JSONObject jsonObject) {
        super(mediaDecoderPipe$InputDataSource, s, mediaFormat, surface, s2, jsonObject);
        this.mSampleRate = 48000;
        this.mChannelConfig = 12;
        this.nFrameRendered = 0L;
        if (AndroidUtils.getAndroidVersion() < 18) {
            return;
        }
        try {
            this.getLatencyMethod = AudioTrack.class.getMethod("getLatency", (Class<?>[])null);
        }
        catch (NoSuchMethodException ex) {}
    }
    
    private boolean renderOutput(final ByteBuffer p0, final MediaCodec$BufferInfo p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //     4: ifnonnull       123
        //     7: ldc             "MediaPipeAudio"
        //     9: ldc             "create audiotrack ... "
        //    11: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    14: pop            
        //    15: aload_0        
        //    16: aload_0        
        //    17: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleRate:I
        //    20: aload_0        
        //    21: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mChannelConfig:I
        //    24: iconst_2       
        //    25: invokestatic    android/media/AudioTrack.getMinBufferSize:(III)I
        //    28: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mBufferSize:I
        //    31: aload_0        
        //    32: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mBufferSize:I
        //    35: ldc             32768
        //    37: if_icmpge       46
        //    40: aload_0        
        //    41: ldc             32768
        //    43: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mBufferSize:I
        //    46: aload_0        
        //    47: new             Landroid/media/AudioTrack;
        //    50: dup            
        //    51: iconst_3       
        //    52: aload_0        
        //    53: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleRate:I
        //    56: aload_0        
        //    57: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mChannelConfig:I
        //    60: iconst_2       
        //    61: aload_0        
        //    62: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mBufferSize:I
        //    65: iconst_1       
        //    66: invokespecial   android/media/AudioTrack.<init>:(IIIIII)V
        //    69: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //    72: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    75: ifeq            118
        //    78: ldc             "MediaPipeAudio"
        //    80: new             Ljava/lang/StringBuilder;
        //    83: dup            
        //    84: invokespecial   java/lang/StringBuilder.<init>:()V
        //    87: ldc             "mBufferSize = "
        //    89: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    92: aload_0        
        //    93: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mBufferSize:I
        //    96: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    99: ldc             ", b.hasArray() "
        //   101: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   104: aload_1        
        //   105: invokevirtual   java/nio/ByteBuffer.hasArray:()Z
        //   108: invokevirtual   java/lang/StringBuilder.append:(Z)Ljava/lang/StringBuilder;
        //   111: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   114: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   117: pop            
        //   118: aload_0        
        //   119: iconst_4       
        //   120: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleSize:I
        //   123: aload_0        
        //   124: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   127: ifnull          171
        //   130: aload_0        
        //   131: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   134: invokevirtual   android/media/AudioTrack.getPlayState:()I
        //   137: iconst_3       
        //   138: if_icmpeq       171
        //   141: aload_0        
        //   142: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   145: invokevirtual   android/media/AudioTrack.getPlayState:()I
        //   148: ifeq            171
        //   151: ldc             "MediaPipeAudio"
        //   153: ldc             "start audiotrack ... "
        //   155: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   158: pop            
        //   159: aload_0        
        //   160: lconst_0       
        //   161: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleCnt:J
        //   164: aload_0        
        //   165: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   168: invokevirtual   android/media/AudioTrack.play:()V
        //   171: aload_0        
        //   172: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   175: invokevirtual   android/media/AudioTrack.getPlaybackHeadPosition:()I
        //   178: istore_3       
        //   179: aload_1        
        //   180: invokevirtual   java/nio/ByteBuffer.clear:()Ljava/nio/Buffer;
        //   183: pop            
        //   184: aload_1        
        //   185: iconst_0       
        //   186: invokevirtual   java/nio/ByteBuffer.position:(I)Ljava/nio/Buffer;
        //   189: pop            
        //   190: aload_1        
        //   191: invokevirtual   java/nio/ByteBuffer.hasArray:()Z
        //   194: ifeq            428
        //   197: aload_1        
        //   198: invokevirtual   java/nio/ByteBuffer.array:()[B
        //   201: astore_1       
        //   202: aload_1        
        //   203: ifnull          387
        //   206: iload_3        
        //   207: sipush          5000
        //   210: if_icmpge       454
        //   213: aload_0        
        //   214: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   217: invokestatic    android/media/AudioTrack.getMinVolume:()F
        //   220: invokestatic    android/media/AudioTrack.getMinVolume:()F
        //   223: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
        //   226: pop            
        //   227: aload_0        
        //   228: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   231: aload_1        
        //   232: iconst_0       
        //   233: aload_2        
        //   234: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   237: invokevirtual   android/media/AudioTrack.write:([BII)I
        //   240: pop            
        //   241: aload_0        
        //   242: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleCnt:J
        //   245: aload_0        
        //   246: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleSize:I
        //   249: i2l            
        //   250: ldiv           
        //   251: aload_0        
        //   252: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   255: invokevirtual   android/media/AudioTrack.getPlaybackHeadPosition:()I
        //   258: i2l            
        //   259: lsub           
        //   260: ldc2_w          1000
        //   263: lmul           
        //   264: aload_0        
        //   265: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleRate:I
        //   268: i2l            
        //   269: ldiv           
        //   270: lstore          4
        //   272: aload_2        
        //   273: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   276: ldc2_w          1000
        //   279: ldiv           
        //   280: lload           4
        //   282: lsub           
        //   283: lstore          4
        //   285: lload           4
        //   287: lstore          6
        //   289: aload_0        
        //   290: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.getLatencyMethod:Ljava/lang/reflect/Method;
        //   293: ifnull          364
        //   296: aload_0        
        //   297: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.getLatencyMethod:Ljava/lang/reflect/Method;
        //   300: aload_0        
        //   301: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   304: aconst_null    
        //   305: checkcast       [Ljava/lang/Object;
        //   308: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //   311: checkcast       Ljava/lang/Integer;
        //   314: astore_1       
        //   315: aload_1        
        //   316: invokevirtual   java/lang/Integer.intValue:()I
        //   319: sipush          5000
        //   322: if_icmpge       499
        //   325: iconst_0       
        //   326: aload_1        
        //   327: invokevirtual   java/lang/Integer.intValue:()I
        //   330: aload_0        
        //   331: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mBufferSize:I
        //   334: sipush          1000
        //   337: imul           
        //   338: aload_0        
        //   339: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleSize:I
        //   342: aload_0        
        //   343: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleRate:I
        //   346: imul           
        //   347: idiv           
        //   348: isub           
        //   349: invokestatic    java/lang/Math.max:(II)I
        //   352: istore_3       
        //   353: lload           4
        //   355: iload_3        
        //   356: i2l            
        //   357: lsub           
        //   358: lstore          4
        //   360: lload           4
        //   362: lstore          6
        //   364: aload_0        
        //   365: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   368: lload           6
        //   370: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.update:(J)V
        //   373: aload_0        
        //   374: aload_0        
        //   375: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleCnt:J
        //   378: aload_2        
        //   379: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   382: i2l            
        //   383: ladd           
        //   384: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleCnt:J
        //   387: iconst_1       
        //   388: ireturn        
        //   389: astore_1       
        //   390: ldc             "MediaPipeAudio"
        //   392: ldc             "AudioTrack.play() has  IllegalStateException"
        //   394: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   397: pop            
        //   398: iconst_0       
        //   399: ireturn        
        //   400: astore_1       
        //   401: ldc             "MediaPipeAudio"
        //   403: new             Ljava/lang/StringBuilder;
        //   406: dup            
        //   407: invokespecial   java/lang/StringBuilder.<init>:()V
        //   410: ldc             "AudioTrack.getPlaybackHeadPosition() has Exception"
        //   412: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   415: aload_1        
        //   416: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   419: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   422: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   425: pop            
        //   426: iconst_0       
        //   427: ireturn        
        //   428: aload_2        
        //   429: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   432: newarray        B
        //   434: astore          8
        //   436: aload_1        
        //   437: aload           8
        //   439: iconst_0       
        //   440: aload_2        
        //   441: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   444: invokevirtual   java/nio/ByteBuffer.get:([BII)Ljava/nio/ByteBuffer;
        //   447: pop            
        //   448: aload           8
        //   450: astore_1       
        //   451: goto            202
        //   454: iload_3        
        //   455: sipush          15000
        //   458: if_icmpge       227
        //   461: aload_0        
        //   462: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   465: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   468: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   471: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
        //   474: pop            
        //   475: goto            227
        //   478: astore_1       
        //   479: ldc             "MediaPipeAudio"
        //   481: ldc             "can't getLatency"
        //   483: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   486: pop            
        //   487: aload_0        
        //   488: aconst_null    
        //   489: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.getLatencyMethod:Ljava/lang/reflect/Method;
        //   492: lload           4
        //   494: lstore          6
        //   496: goto            364
        //   499: goto            360
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  164    171    389    400    Ljava/lang/IllegalStateException;
        //  171    179    400    428    Ljava/lang/Exception;
        //  296    353    478    499    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 253, Size: 253
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
    public void flush() {
        super.flush();
        while (true) {
            try {
                if (this.mAudioTrack != null && (this.mAudioTrack.getPlayState() == 2 || this.mAudioTrack.getPlayState() == 3)) {
                    this.mAudioTrack.stop();
                    this.mSampleCnt = 0L;
                    Log.d("MediaPipeAudio", "stop audio state: " + this.mAudioTrack.getPlayState());
                }
                Log.d("MediaPipeAudio", "flush audio done");
            }
            catch (IllegalStateException ex) {
                Log.d("MediaPipeAudio", "AudioTrack.flush() has  IllegalStateException");
                continue;
            }
            break;
        }
    }
    
    @Override
    boolean renderOutput(final boolean p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifne            6
        //     4: iconst_0       
        //     5: ireturn        
        //     6: aload_0        
        //     7: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //    10: astore          5
        //    12: aload           5
        //    14: monitorenter   
        //    15: aload_0        
        //    16: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //    19: invokevirtual   java/util/LinkedList.isEmpty:()Z
        //    22: ifne            226
        //    25: aload_0        
        //    26: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //    29: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
        //    32: checkcast       Ljava/lang/Integer;
        //    35: invokevirtual   java/lang/Integer.intValue:()I
        //    38: istore_2       
        //    39: aload_0        
        //    40: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //    43: iload_2        
        //    44: aaload         
        //    45: astore_3       
        //    46: aload_0        
        //    47: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mOutputBuffers:[Ljava/nio/ByteBuffer;
        //    50: iload_2        
        //    51: aaload         
        //    52: astore          4
        //    54: aload           5
        //    56: monitorexit    
        //    57: iload_2        
        //    58: iconst_m1      
        //    59: if_icmpeq       224
        //    62: aload_3        
        //    63: ifnull          224
        //    66: aload_0        
        //    67: aload           4
        //    69: aload_3        
        //    70: invokespecial   com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.renderOutput:(Ljava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)Z
        //    73: ifeq            224
        //    76: aload_0        
        //    77: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //    80: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //    83: pop            
        //    84: aload_0        
        //    85: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //    88: iload_2        
        //    89: aconst_null    
        //    90: aastore        
        //    91: aload_0        
        //    92: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //    95: iload_2        
        //    96: iconst_0       
        //    97: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   100: aload_0        
        //   101: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.nFrameRendered:J
        //   104: lconst_0       
        //   105: lcmp           
        //   106: ifgt            184
        //   109: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   112: ifeq            184
        //   115: ldc             "MediaPipeAudio"
        //   117: new             Ljava/lang/StringBuilder;
        //   120: dup            
        //   121: invokespecial   java/lang/StringBuilder.<init>:()V
        //   124: ldc_w           "ReleaseOutputBuffer "
        //   127: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   130: iload_2        
        //   131: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   134: ldc_w           " size= "
        //   137: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   140: aload_3        
        //   141: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   144: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   147: ldc_w           " @"
        //   150: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   153: aload_3        
        //   154: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   157: ldc2_w          1000
        //   160: ldiv           
        //   161: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   164: ldc_w           " ms,flags "
        //   167: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: aload_3        
        //   171: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   174: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   177: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   180: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   183: pop            
        //   184: aload_0        
        //   185: aload_0        
        //   186: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.nFrameRendered:J
        //   189: lconst_1       
        //   190: ladd           
        //   191: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.nFrameRendered:J
        //   194: aload_3        
        //   195: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   198: iconst_4       
        //   199: if_icmpne       224
        //   202: iconst_0       
        //   203: ireturn        
        //   204: astore_3       
        //   205: aload           5
        //   207: monitorexit    
        //   208: aload_3        
        //   209: athrow         
        //   210: astore          4
        //   212: ldc             "MediaPipeAudio"
        //   214: ldc_w           "IllegalStateException at releaseOutputBuffer"
        //   217: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   220: pop            
        //   221: goto            100
        //   224: iconst_1       
        //   225: ireturn        
        //   226: aconst_null    
        //   227: astore          4
        //   229: aconst_null    
        //   230: astore_3       
        //   231: iconst_m1      
        //   232: istore_2       
        //   233: goto            54
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  15     54     204    210    Any
        //  54     57     204    210    Any
        //  91     100    210    224    Ljava/lang/IllegalStateException;
        //  205    208    204    210    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0100:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    public void stop() {
        while (true) {
            try {
                if (this.mAudioTrack != null) {
                    this.mAudioTrack.stop();
                    this.mAudioTrack.release();
                    this.mAudioTrack = null;
                }
                this.mSampleCnt = 0L;
                super.stop();
            }
            catch (IllegalStateException ex) {
                Log.d("MediaPipeAudio", "AudioTrack.stop() has  IllegalStateException");
                continue;
            }
            break;
        }
    }
}
