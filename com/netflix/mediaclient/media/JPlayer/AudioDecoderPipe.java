// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import com.netflix.mediaclient.Log;
import android.media.MediaCodec$BufferInfo;
import java.nio.ByteBuffer;
import org.json.JSONObject;
import android.view.Surface;
import android.media.MediaFormat;
import android.media.AudioTrack;
import android.annotation.TargetApi;

@TargetApi(16)
public class AudioDecoderPipe extends MediaDecoderPipe
{
    private static final String TAG = "MediaPipeAudio";
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
    }
    
    private boolean renderOutput(final ByteBuffer p0, final MediaCodec$BufferInfo p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //     4: ifnonnull       126
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
        //    72: ldc             "MediaPipeAudio"
        //    74: iconst_3       
        //    75: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //    78: ifeq            121
        //    81: ldc             "MediaPipeAudio"
        //    83: new             Ljava/lang/StringBuilder;
        //    86: dup            
        //    87: invokespecial   java/lang/StringBuilder.<init>:()V
        //    90: ldc             "mBufferSize = "
        //    92: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    95: aload_0        
        //    96: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mBufferSize:I
        //    99: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   102: ldc             ", b.hasArray() "
        //   104: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   107: aload_1        
        //   108: invokevirtual   java/nio/ByteBuffer.hasArray:()Z
        //   111: invokevirtual   java/lang/StringBuilder.append:(Z)Ljava/lang/StringBuilder;
        //   114: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   117: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   120: pop            
        //   121: aload_0        
        //   122: iconst_4       
        //   123: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleSize:I
        //   126: aload_0        
        //   127: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   130: ifnull          174
        //   133: aload_0        
        //   134: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   137: invokevirtual   android/media/AudioTrack.getPlayState:()I
        //   140: iconst_3       
        //   141: if_icmpeq       174
        //   144: aload_0        
        //   145: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   148: invokevirtual   android/media/AudioTrack.getPlayState:()I
        //   151: ifeq            174
        //   154: ldc             "MediaPipeAudio"
        //   156: ldc             "start audiotrack ... "
        //   158: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   161: pop            
        //   162: aload_0        
        //   163: lconst_0       
        //   164: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleCnt:J
        //   167: aload_0        
        //   168: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   171: invokevirtual   android/media/AudioTrack.play:()V
        //   174: aload_0        
        //   175: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   178: invokevirtual   android/media/AudioTrack.getPlaybackHeadPosition:()I
        //   181: istore_3       
        //   182: aload_1        
        //   183: invokevirtual   java/nio/ByteBuffer.clear:()Ljava/nio/Buffer;
        //   186: pop            
        //   187: aload_1        
        //   188: iconst_0       
        //   189: invokevirtual   java/nio/ByteBuffer.position:(I)Ljava/nio/Buffer;
        //   192: pop            
        //   193: aload_1        
        //   194: invokevirtual   java/nio/ByteBuffer.hasArray:()Z
        //   197: ifeq            348
        //   200: aload_1        
        //   201: invokevirtual   java/nio/ByteBuffer.array:()[B
        //   204: astore_1       
        //   205: aload_1        
        //   206: ifnull          307
        //   209: iload_3        
        //   210: sipush          5000
        //   213: if_icmpge       374
        //   216: aload_0        
        //   217: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   220: invokestatic    android/media/AudioTrack.getMinVolume:()F
        //   223: invokestatic    android/media/AudioTrack.getMinVolume:()F
        //   226: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
        //   229: pop            
        //   230: aload_0        
        //   231: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   234: aload_1        
        //   235: iconst_0       
        //   236: aload_2        
        //   237: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   240: invokevirtual   android/media/AudioTrack.write:([BII)I
        //   243: pop            
        //   244: aload_0        
        //   245: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleCnt:J
        //   248: aload_0        
        //   249: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleSize:I
        //   252: i2l            
        //   253: ldiv           
        //   254: aload_0        
        //   255: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   258: invokevirtual   android/media/AudioTrack.getPlaybackHeadPosition:()I
        //   261: i2l            
        //   262: lsub           
        //   263: ldc2_w          1000
        //   266: lmul           
        //   267: aload_0        
        //   268: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleRate:I
        //   271: i2l            
        //   272: ldiv           
        //   273: lstore          4
        //   275: aload_0        
        //   276: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   279: aload_2        
        //   280: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   283: ldc2_w          1000
        //   286: ldiv           
        //   287: lload           4
        //   289: lsub           
        //   290: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.update:(J)V
        //   293: aload_0        
        //   294: aload_0        
        //   295: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleCnt:J
        //   298: aload_2        
        //   299: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   302: i2l            
        //   303: ladd           
        //   304: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleCnt:J
        //   307: iconst_1       
        //   308: ireturn        
        //   309: astore_1       
        //   310: ldc             "MediaPipeAudio"
        //   312: ldc             "AudioTrack.play() has  IllegalStateException"
        //   314: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   317: pop            
        //   318: iconst_0       
        //   319: ireturn        
        //   320: astore_1       
        //   321: ldc             "MediaPipeAudio"
        //   323: new             Ljava/lang/StringBuilder;
        //   326: dup            
        //   327: invokespecial   java/lang/StringBuilder.<init>:()V
        //   330: ldc             "AudioTrack.getPlaybackHeadPosition() has Exception"
        //   332: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   335: aload_1        
        //   336: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   339: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   342: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   345: pop            
        //   346: iconst_0       
        //   347: ireturn        
        //   348: aload_2        
        //   349: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   352: newarray        B
        //   354: astore          6
        //   356: aload_1        
        //   357: aload           6
        //   359: iconst_0       
        //   360: aload_2        
        //   361: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   364: invokevirtual   java/nio/ByteBuffer.get:([BII)Ljava/nio/ByteBuffer;
        //   367: pop            
        //   368: aload           6
        //   370: astore_1       
        //   371: goto            205
        //   374: iload_3        
        //   375: sipush          15000
        //   378: if_icmpge       230
        //   381: aload_0        
        //   382: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   385: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   388: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   391: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
        //   394: pop            
        //   395: goto            230
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  167    174    309    320    Ljava/lang/IllegalStateException;
        //  174    182    320    348    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0174:
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
        //    22: ifne            225
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
        //    59: if_icmpeq       223
        //    62: aload_3        
        //    63: ifnull          223
        //    66: aload_0        
        //    67: aload           4
        //    69: aload_3        
        //    70: invokespecial   com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.renderOutput:(Ljava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)Z
        //    73: ifeq            223
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
        //   106: ifgt            183
        //   109: ldc             "MediaPipeAudio"
        //   111: iconst_3       
        //   112: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   115: ifeq            183
        //   118: ldc             "MediaPipeAudio"
        //   120: new             Ljava/lang/StringBuilder;
        //   123: dup            
        //   124: invokespecial   java/lang/StringBuilder.<init>:()V
        //   127: ldc             "ReleaseOutputBuffer "
        //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   132: iload_2        
        //   133: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   136: ldc             " size= "
        //   138: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   141: aload_3        
        //   142: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   145: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   148: ldc             " @"
        //   150: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   153: aload_3        
        //   154: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   157: ldc2_w          1000
        //   160: ldiv           
        //   161: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   164: ldc             " ms,flags "
        //   166: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   169: aload_3        
        //   170: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   173: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   176: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   179: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   182: pop            
        //   183: aload_0        
        //   184: aload_0        
        //   185: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.nFrameRendered:J
        //   188: lconst_1       
        //   189: ladd           
        //   190: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.nFrameRendered:J
        //   193: aload_3        
        //   194: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   197: iconst_4       
        //   198: if_icmpne       223
        //   201: iconst_0       
        //   202: ireturn        
        //   203: astore_3       
        //   204: aload           5
        //   206: monitorexit    
        //   207: aload_3        
        //   208: athrow         
        //   209: astore          4
        //   211: ldc             "MediaPipeAudio"
        //   213: ldc_w           "IllegalStateException at releaseOutputBuffer"
        //   216: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   219: pop            
        //   220: goto            100
        //   223: iconst_1       
        //   224: ireturn        
        //   225: aconst_null    
        //   226: astore          4
        //   228: aconst_null    
        //   229: astore_3       
        //   230: iconst_m1      
        //   231: istore_2       
        //   232: goto            54
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  15     54     203    209    Any
        //  54     57     203    209    Any
        //  91     100    209    223    Ljava/lang/IllegalStateException;
        //  204    207    203    209    Any
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
