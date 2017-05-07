// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import com.netflix.mediaclient.Log;
import android.media.MediaCodec$BufferInfo;
import java.nio.ByteBuffer;
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
    
    public AudioDecoderPipe(final InputDataSource inputDataSource, final String s, final MediaFormat mediaFormat, final Surface surface, final String s2) throws Exception {
        super(inputDataSource, s, mediaFormat, surface, s2);
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
        //     4: ifnonnull       128
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
        //    35: sipush          16384
        //    38: if_icmpge       48
        //    41: aload_0        
        //    42: sipush          16384
        //    45: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mBufferSize:I
        //    48: aload_0        
        //    49: new             Landroid/media/AudioTrack;
        //    52: dup            
        //    53: iconst_3       
        //    54: aload_0        
        //    55: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleRate:I
        //    58: aload_0        
        //    59: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mChannelConfig:I
        //    62: iconst_2       
        //    63: aload_0        
        //    64: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mBufferSize:I
        //    67: iconst_1       
        //    68: invokespecial   android/media/AudioTrack.<init>:(IIIIII)V
        //    71: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //    74: ldc             "MediaPipeAudio"
        //    76: iconst_3       
        //    77: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //    80: ifeq            123
        //    83: ldc             "MediaPipeAudio"
        //    85: new             Ljava/lang/StringBuilder;
        //    88: dup            
        //    89: invokespecial   java/lang/StringBuilder.<init>:()V
        //    92: ldc             "mBufferSize = "
        //    94: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    97: aload_0        
        //    98: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mBufferSize:I
        //   101: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   104: ldc             ", b.hasArray() "
        //   106: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   109: aload_1        
        //   110: invokevirtual   java/nio/ByteBuffer.hasArray:()Z
        //   113: invokevirtual   java/lang/StringBuilder.append:(Z)Ljava/lang/StringBuilder;
        //   116: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   119: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   122: pop            
        //   123: aload_0        
        //   124: iconst_4       
        //   125: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleSize:I
        //   128: aload_0        
        //   129: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   132: ifnull          176
        //   135: aload_0        
        //   136: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   139: invokevirtual   android/media/AudioTrack.getPlayState:()I
        //   142: iconst_3       
        //   143: if_icmpeq       176
        //   146: aload_0        
        //   147: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   150: invokevirtual   android/media/AudioTrack.getPlayState:()I
        //   153: ifeq            176
        //   156: ldc             "MediaPipeAudio"
        //   158: ldc             "start audiotrack ... "
        //   160: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   163: pop            
        //   164: aload_0        
        //   165: lconst_0       
        //   166: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleCnt:J
        //   169: aload_0        
        //   170: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   173: invokevirtual   android/media/AudioTrack.play:()V
        //   176: aload_0        
        //   177: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   180: invokevirtual   android/media/AudioTrack.getPlaybackHeadPosition:()I
        //   183: istore_3       
        //   184: aload_1        
        //   185: invokevirtual   java/nio/ByteBuffer.clear:()Ljava/nio/Buffer;
        //   188: pop            
        //   189: aload_1        
        //   190: iconst_0       
        //   191: invokevirtual   java/nio/ByteBuffer.position:(I)Ljava/nio/Buffer;
        //   194: pop            
        //   195: aload_1        
        //   196: invokevirtual   java/nio/ByteBuffer.hasArray:()Z
        //   199: ifeq            350
        //   202: aload_1        
        //   203: invokevirtual   java/nio/ByteBuffer.array:()[B
        //   206: astore_1       
        //   207: aload_1        
        //   208: ifnull          309
        //   211: iload_3        
        //   212: sipush          5000
        //   215: if_icmpge       376
        //   218: aload_0        
        //   219: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   222: invokestatic    android/media/AudioTrack.getMinVolume:()F
        //   225: invokestatic    android/media/AudioTrack.getMinVolume:()F
        //   228: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
        //   231: pop            
        //   232: aload_0        
        //   233: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   236: aload_1        
        //   237: iconst_0       
        //   238: aload_2        
        //   239: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   242: invokevirtual   android/media/AudioTrack.write:([BII)I
        //   245: pop            
        //   246: ldc2_w          1000
        //   249: aload_0        
        //   250: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleCnt:J
        //   253: aload_0        
        //   254: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleSize:I
        //   257: i2l            
        //   258: ldiv           
        //   259: aload_0        
        //   260: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   263: invokevirtual   android/media/AudioTrack.getPlaybackHeadPosition:()I
        //   266: i2l            
        //   267: lsub           
        //   268: lmul           
        //   269: aload_0        
        //   270: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleRate:I
        //   273: i2l            
        //   274: ldiv           
        //   275: lstore          4
        //   277: aload_0        
        //   278: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   281: aload_2        
        //   282: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   285: ldc2_w          1000
        //   288: ldiv           
        //   289: lload           4
        //   291: lsub           
        //   292: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.update:(J)V
        //   295: aload_0        
        //   296: aload_0        
        //   297: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleCnt:J
        //   300: aload_2        
        //   301: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   304: i2l            
        //   305: ladd           
        //   306: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mSampleCnt:J
        //   309: iconst_1       
        //   310: ireturn        
        //   311: astore_1       
        //   312: ldc             "MediaPipeAudio"
        //   314: ldc             "AudioTrack.play() has  IllegalStateException"
        //   316: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   319: pop            
        //   320: iconst_0       
        //   321: ireturn        
        //   322: astore_1       
        //   323: ldc             "MediaPipeAudio"
        //   325: new             Ljava/lang/StringBuilder;
        //   328: dup            
        //   329: invokespecial   java/lang/StringBuilder.<init>:()V
        //   332: ldc             "AudioTrack.getPlaybackHeadPosition() has Exception"
        //   334: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   337: aload_1        
        //   338: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   341: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   344: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   347: pop            
        //   348: iconst_0       
        //   349: ireturn        
        //   350: aload_2        
        //   351: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   354: newarray        B
        //   356: astore          6
        //   358: aload_1        
        //   359: aload           6
        //   361: iconst_0       
        //   362: aload_2        
        //   363: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   366: invokevirtual   java/nio/ByteBuffer.get:([BII)Ljava/nio/ByteBuffer;
        //   369: pop            
        //   370: aload           6
        //   372: astore_1       
        //   373: goto            207
        //   376: iload_3        
        //   377: sipush          15000
        //   380: if_icmpge       232
        //   383: aload_0        
        //   384: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mAudioTrack:Landroid/media/AudioTrack;
        //   387: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   390: invokestatic    android/media/AudioTrack.getMaxVolume:()F
        //   393: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
        //   396: pop            
        //   397: goto            232
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  169    176    311    322    Ljava/lang/IllegalStateException;
        //  176    184    322    350    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0176:
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
        //     6: iconst_m1      
        //     7: istore_2       
        //     8: aconst_null    
        //     9: astore_3       
        //    10: aconst_null    
        //    11: astore          4
        //    13: aload_0        
        //    14: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //    17: astore          5
        //    19: aload           5
        //    21: monitorenter   
        //    22: aload_0        
        //    23: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //    26: invokevirtual   java/util/LinkedList.isEmpty:()Z
        //    29: ifne            61
        //    32: aload_0        
        //    33: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //    36: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
        //    39: checkcast       Ljava/lang/Integer;
        //    42: invokevirtual   java/lang/Integer.intValue:()I
        //    45: istore_2       
        //    46: aload_0        
        //    47: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //    50: iload_2        
        //    51: aaload         
        //    52: astore_3       
        //    53: aload_0        
        //    54: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mOutputBuffers:[Ljava/nio/ByteBuffer;
        //    57: iload_2        
        //    58: aaload         
        //    59: astore          4
        //    61: aload           5
        //    63: monitorexit    
        //    64: iload_2        
        //    65: iconst_m1      
        //    66: if_icmpeq       230
        //    69: aload_3        
        //    70: ifnull          230
        //    73: aload_0        
        //    74: aload           4
        //    76: aload_3        
        //    77: invokespecial   com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.renderOutput:(Ljava/nio/ByteBuffer;Landroid/media/MediaCodec$BufferInfo;)Z
        //    80: ifeq            230
        //    83: aload_0        
        //    84: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //    87: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //    90: pop            
        //    91: aload_0        
        //    92: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //    95: iload_2        
        //    96: aconst_null    
        //    97: aastore        
        //    98: aload_0        
        //    99: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //   102: iload_2        
        //   103: iconst_0       
        //   104: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   107: aload_0        
        //   108: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.nFrameRendered:J
        //   111: lconst_0       
        //   112: lcmp           
        //   113: ifgt            190
        //   116: ldc             "MediaPipeAudio"
        //   118: iconst_3       
        //   119: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   122: ifeq            190
        //   125: ldc             "MediaPipeAudio"
        //   127: new             Ljava/lang/StringBuilder;
        //   130: dup            
        //   131: invokespecial   java/lang/StringBuilder.<init>:()V
        //   134: ldc             "ReleaseOutputBuffer "
        //   136: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   139: iload_2        
        //   140: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   143: ldc             " size= "
        //   145: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   148: aload_3        
        //   149: getfield        android/media/MediaCodec$BufferInfo.size:I
        //   152: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   155: ldc             " @"
        //   157: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   160: aload_3        
        //   161: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   164: ldc2_w          1000
        //   167: ldiv           
        //   168: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   171: ldc             " ms,flags "
        //   173: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   176: aload_3        
        //   177: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   180: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   183: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   186: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   189: pop            
        //   190: aload_0        
        //   191: aload_0        
        //   192: getfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.nFrameRendered:J
        //   195: lconst_1       
        //   196: ladd           
        //   197: putfield        com/netflix/mediaclient/media/JPlayer/AudioDecoderPipe.nFrameRendered:J
        //   200: aload_3        
        //   201: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   204: iconst_4       
        //   205: if_icmpne       230
        //   208: iconst_0       
        //   209: ireturn        
        //   210: astore_3       
        //   211: aload           5
        //   213: monitorexit    
        //   214: aload_3        
        //   215: athrow         
        //   216: astore          4
        //   218: ldc             "MediaPipeAudio"
        //   220: ldc_w           "IllegalStateException at releaseOutputBuffer"
        //   223: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   226: pop            
        //   227: goto            107
        //   230: iconst_1       
        //   231: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  22     61     210    216    Any
        //  61     64     210    216    Any
        //  98     107    216    230    Ljava/lang/IllegalStateException;
        //  211    214    210    216    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0107:
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
