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
        //   194: ifeq            345
        //   197: aload_1        
        //   198: invokevirtual   java/nio/ByteBuffer.array:()[B
        //   201: astore_1       
        //   202: aload_1        
        //   203: ifnull          304
        //   206: iload_3        
        //   207: sipush          5000
        //   210: if_icmpge       371
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
        //   272: aload_0        
        //   273: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   276: aload_2        
        //   277: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   280: ldc2_w          1000
        //   283: ldiv           
        //   284: lload           4
        //   286: lsub           
        //   287: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.update:(J)V
        //   290: aload_0        
        //   291: aload_0        
        //   292: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleCnt:J
        //   295: aload_2        
        //   296: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   299: i2l            
        //   300: ladd           
        //   301: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleCnt:J
        //   304: iconst_1       
        //   305: ireturn        
        //   306: astore_1       
        //   307: ldc             "MediaPipeAudio"
        //   309: ldc             "AudioTrack.play() has  IllegalStateException"
        //   311: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   314: pop            
        //   315: iconst_0       
        //   316: ireturn        
        //   317: astore_1       
        //   318: ldc             "MediaPipeAudio"
        //   320: new             Ljava/lang/StringBuilder;
        //   323: dup            
        //   324: invokespecial   java/lang/StringBuilder.<init>:()V
        //   327: ldc             "AudioTrack.getPlaybackHeadPosition() has Exception"
        //   329: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   332: aload_1        
        //   333: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   336: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   339: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   342: pop            
        //   343: iconst_0       
        //   344: ireturn        
        //   345: aload_2        
        //   346: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   349: newarray        B
        //   351: astore          6
        //   353: aload_1        
        //   354: aload           6
        //   356: iconst_0       
        //   357: aload_2        
        //   358: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   361: invokevirtual   java/nio/ByteBuffer.get:([BII)Ljava/nio/ByteBuffer;
        //   364: pop            
        //   365: aload           6
        //   367: astore_1       
        //   368: goto            202
        //   371: iload_3        
        //   372: sipush          15000
        //   375: if_icmpge       227
        //   378: aload_0        
        //   379: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   382: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   385: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   388: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
        //   391: pop            
        //   392: goto            227
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  164    171    306    317    Ljava/lang/IllegalStateException;
        //  171    179    317    345    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0171:
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
        //    22: ifne            222
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
        //    59: if_icmpeq       220
        //    62: aload_3        
        //    63: ifnull          220
        //    66: aload_0        
        //    67: aload           4
        //    69: aload_3        
        //    70: invokespecial   com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.renderOutput:(Ljava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)Z
        //    73: ifeq            220
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
        //   106: ifgt            180
        //   109: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   112: ifeq            180
        //   115: ldc             "MediaPipeAudio"
        //   117: new             Ljava/lang/StringBuilder;
        //   120: dup            
        //   121: invokespecial   java/lang/StringBuilder.<init>:()V
        //   124: ldc             "ReleaseOutputBuffer "
        //   126: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   129: iload_2        
        //   130: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   133: ldc             " size= "
        //   135: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   138: aload_3        
        //   139: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   142: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   145: ldc             " @"
        //   147: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   150: aload_3        
        //   151: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   154: ldc2_w          1000
        //   157: ldiv           
        //   158: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   161: ldc             " ms,flags "
        //   163: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   166: aload_3        
        //   167: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   170: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   173: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   176: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   179: pop            
        //   180: aload_0        
        //   181: aload_0        
        //   182: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.nFrameRendered:J
        //   185: lconst_1       
        //   186: ladd           
        //   187: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.nFrameRendered:J
        //   190: aload_3        
        //   191: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   194: iconst_4       
        //   195: if_icmpne       220
        //   198: iconst_0       
        //   199: ireturn        
        //   200: astore_3       
        //   201: aload           5
        //   203: monitorexit    
        //   204: aload_3        
        //   205: athrow         
        //   206: astore          4
        //   208: ldc             "MediaPipeAudio"
        //   210: ldc_w           "IllegalStateException at releaseOutputBuffer"
        //   213: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   216: pop            
        //   217: goto            100
        //   220: iconst_1       
        //   221: ireturn        
        //   222: aconst_null    
        //   223: astore          4
        //   225: aconst_null    
        //   226: astore_3       
        //   227: iconst_m1      
        //   228: istore_2       
        //   229: goto            54
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  15     54     200    206    Any
        //  54     57     200    206    Any
        //  91     100    206    220    Ljava/lang/IllegalStateException;
        //  201    204    200    206    Any
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
