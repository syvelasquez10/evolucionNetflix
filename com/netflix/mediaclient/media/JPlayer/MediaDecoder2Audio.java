// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.os.Message;
import android.os.Looper;
import com.netflix.mediaclient.Log;
import android.media.MediaCrypto;
import android.view.Surface;
import android.media.MediaFormat;
import android.os.HandlerThread;
import android.os.Handler;
import android.media.AudioTrack;

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
                //                3: 841
                //                4: 758
                //                5: 994
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
                //   103: astore          6
                //   105: aload_0        
                //   106: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   109: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
                //   112: astore          7
                //   114: aload           7
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
                //   166: astore          6
                //   168: aload           7
                //   170: monitorexit    
                //   171: aload_1        
                //   172: ifnull          229
                //   175: aload_1        
                //   176: getfield        android/media/MediaCodec$BufferInfo.flags:I
                //   179: iconst_4       
                //   180: iand           
                //   181: ifeq            229
                //   184: ldc             "MediaDecoder2Audio"
                //   186: ldc             "renderer got buffer BUFFER_FLAG_END_OF_STREAM"
                //   188: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   191: pop            
                //   192: aload_0        
                //   193: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   196: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //   199: ifnull          44
                //   202: aload_0        
                //   203: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   206: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //   209: iconst_1       
                //   210: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onEndOfStream:(Z)V
                //   215: return         
                //   216: astore          6
                //   218: aload_1        
                //   219: monitorexit    
                //   220: aload           6
                //   222: athrow         
                //   223: astore_1       
                //   224: aload           7
                //   226: monitorexit    
                //   227: aload_1        
                //   228: athrow         
                //   229: aload_0        
                //   230: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   233: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.startRenderer:()V
                //   236: aload_0        
                //   237: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   240: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/media/AudioTrack;
                //   243: invokevirtual   android/media/AudioTrack.getPlaybackHeadPosition:()I
                //   246: istore_3       
                //   247: iload_2        
                //   248: iflt            728
                //   251: aload           6
                //   253: invokevirtual   java/nio/ByteBuffer.clear:()Ljava/nio/Buffer;
                //   256: pop            
                //   257: aload           6
                //   259: iconst_0       
                //   260: invokevirtual   java/nio/ByteBuffer.position:(I)Ljava/nio/Buffer;
                //   263: pop            
                //   264: aload           6
                //   266: invokevirtual   java/nio/ByteBuffer.hasArray:()Z
                //   269: ifeq            653
                //   272: aload           6
                //   274: invokevirtual   java/nio/ByteBuffer.array:()[B
                //   277: astore          6
                //   279: aload           6
                //   281: ifnull          443
                //   284: iload_3        
                //   285: sipush          5000
                //   288: if_icmpge       681
                //   291: aload_0        
                //   292: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   295: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/media/AudioTrack;
                //   298: invokestatic    android/media/AudioTrack.getMinVolume:()F
                //   301: invokestatic    android/media/AudioTrack.getMinVolume:()F
                //   304: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
                //   307: pop            
                //   308: aload_0        
                //   309: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   312: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/media/AudioTrack;
                //   315: aload           6
                //   317: iconst_0       
                //   318: aload_1        
                //   319: getfield        android/media/MediaCodec$BufferInfo.size:I
                //   322: invokevirtual   android/media/AudioTrack.write:([BII)I
                //   325: pop            
                //   326: ldc2_w          1000
                //   329: aload_0        
                //   330: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   333: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)J
                //   336: aload_0        
                //   337: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   340: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)I
                //   343: i2l            
                //   344: ldiv           
                //   345: aload_0        
                //   346: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   349: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/media/AudioTrack;
                //   352: invokevirtual   android/media/AudioTrack.getPlaybackHeadPosition:()I
                //   355: i2l            
                //   356: lsub           
                //   357: lmul           
                //   358: aload_0        
                //   359: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   362: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)I
                //   365: i2l            
                //   366: ldiv           
                //   367: lstore          4
                //   369: aload_0        
                //   370: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   373: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                //   376: aload_1        
                //   377: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
                //   380: ldc2_w          1000
                //   383: ldiv           
                //   384: lload           4
                //   386: lsub           
                //   387: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.update:(J)V
                //   390: aload_0        
                //   391: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   394: aload_1        
                //   395: getfield        android/media/MediaCodec$BufferInfo.size:I
                //   398: i2l            
                //   399: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$214:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;J)J
                //   402: pop2           
                //   403: aload_0        
                //   404: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   407: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //   410: ifnull          443
                //   413: aload_0        
                //   414: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   417: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //   420: iconst_1       
                //   421: aload_0        
                //   422: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   425: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)J
                //   428: aload_0        
                //   429: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   432: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                //   435: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
                //   438: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onSampleRendered:(ZJJ)V
                //   443: aload_0        
                //   444: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   447: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
                //   450: astore          6
                //   452: aload           6
                //   454: monitorenter   
                //   455: aload_0        
                //   456: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   459: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
                //   462: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
                //   465: pop            
                //   466: aload_0        
                //   467: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   470: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                //   473: iload_2        
                //   474: aconst_null    
                //   475: aastore        
                //   476: aload           6
                //   478: monitorexit    
                //   479: aload_0        
                //   480: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   483: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mDecoder:Landroid/media/MediaCodec;
                //   486: iload_2        
                //   487: iconst_0       
                //   488: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
                //   491: aload_0        
                //   492: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   495: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)J
                //   498: lconst_0       
                //   499: lcmp           
                //   500: ifgt            577
                //   503: ldc             "MediaDecoder2Audio"
                //   505: iconst_3       
                //   506: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   509: ifeq            577
                //   512: ldc             "MediaDecoder2Audio"
                //   514: new             Ljava/lang/StringBuilder;
                //   517: dup            
                //   518: invokespecial   java/lang/StringBuilder.<init>:()V
                //   521: ldc             "ReleaseOutputBuffer "
                //   523: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   526: iload_2        
                //   527: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //   530: ldc             " size= "
                //   532: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   535: aload_1        
                //   536: getfield        android/media/MediaCodec$BufferInfo.size:I
                //   539: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //   542: ldc             " @"
                //   544: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   547: aload_1        
                //   548: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
                //   551: ldc2_w          1000
                //   554: ldiv           
                //   555: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   558: ldc             " ms,flags "
                //   560: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   563: aload_1        
                //   564: getfield        android/media/MediaCodec$BufferInfo.flags:I
                //   567: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //   570: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   573: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   576: pop            
                //   577: aload_0        
                //   578: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   581: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$508:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)J
                //   584: pop2           
                //   585: aload_0        
                //   586: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   589: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //   592: astore_1       
                //   593: aload_1        
                //   594: monitorenter   
                //   595: aload_0        
                //   596: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   599: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //   602: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier.isPlaying:()Z
                //   605: ifne            740
                //   608: ldc             "MediaDecoder2Audio"
                //   610: ldc             "render state is not play"
                //   612: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   615: pop            
                //   616: aload_1        
                //   617: monitorexit    
                //   618: return         
                //   619: astore          6
                //   621: aload_1        
                //   622: monitorexit    
                //   623: aload           6
                //   625: athrow         
                //   626: astore_1       
                //   627: ldc             "MediaDecoder2Audio"
                //   629: new             Ljava/lang/StringBuilder;
                //   632: dup            
                //   633: invokespecial   java/lang/StringBuilder.<init>:()V
                //   636: ldc             "AudioTrack.getPlaybackHeadPosition() has Exception"
                //   638: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   641: aload_1        
                //   642: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
                //   645: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   648: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
                //   651: pop            
                //   652: return         
                //   653: aload_1        
                //   654: getfield        android/media/MediaCodec$BufferInfo.size:I
                //   657: newarray        B
                //   659: astore          7
                //   661: aload           6
                //   663: aload           7
                //   665: iconst_0       
                //   666: aload_1        
                //   667: getfield        android/media/MediaCodec$BufferInfo.size:I
                //   670: invokevirtual   java/nio/ByteBuffer.get:([BII)Ljava/nio/ByteBuffer;
                //   673: pop            
                //   674: aload           7
                //   676: astore          6
                //   678: goto            279
                //   681: iload_3        
                //   682: sipush          15000
                //   685: if_icmpge       308
                //   688: aload_0        
                //   689: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   692: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/media/AudioTrack;
                //   695: invokestatic    android/media/AudioTrack.getMaxVolume:()F
                //   698: invokestatic    android/media/AudioTrack.getMaxVolume:()F
                //   701: invokevirtual   android/media/AudioTrack.setStereoVolume:(FF)I
                //   704: pop            
                //   705: goto            308
                //   708: astore_1       
                //   709: aload           6
                //   711: monitorexit    
                //   712: aload_1        
                //   713: athrow         
                //   714: astore          6
                //   716: ldc             "MediaDecoder2Audio"
                //   718: ldc_w           "get un-documented exception as a result of releaseOutputBuffer()"
                //   721: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   724: pop            
                //   725: goto            491
                //   728: ldc             "MediaDecoder2Audio"
                //   730: ldc_w           "render buffer not ready"
                //   733: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   736: pop            
                //   737: goto            585
                //   740: aload_0        
                //   741: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   744: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/os/Handler;
                //   747: iconst_1       
                //   748: ldc2_w          20
                //   751: invokevirtual   android/os/Handler.sendEmptyMessageDelayed:(IJ)Z
                //   754: pop            
                //   755: goto            616
                //   758: aload_0        
                //   759: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   762: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                //   765: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.pause:()J
                //   768: pop2           
                //   769: aload_0        
                //   770: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   773: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //   776: astore_1       
                //   777: aload_1        
                //   778: monitorenter   
                //   779: aload_0        
                //   780: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   783: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //   786: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier.onPaused:()V
                //   789: aload_0        
                //   790: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   793: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //   796: invokevirtual   java/lang/Object.notify:()V
                //   799: aload_1        
                //   800: monitorexit    
                //   801: ldc             "MediaDecoder2Audio"
                //   803: ldc_w           "render state pause"
                //   806: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   809: pop            
                //   810: aload_0        
                //   811: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   814: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //   817: ifnull          44
                //   820: aload_0        
                //   821: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   824: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //   827: iconst_1       
                //   828: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onPasued:(Z)V
                //   833: return         
                //   834: astore          6
                //   836: aload_1        
                //   837: monitorexit    
                //   838: aload           6
                //   840: athrow         
                //   841: ldc             "MediaDecoder2Audio"
                //   843: ldc_w           "render state flushing"
                //   846: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   849: pop            
                //   850: aload_0        
                //   851: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   854: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                //   857: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.flush:()V
                //   860: aload_0        
                //   861: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   864: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
                //   867: astore_1       
                //   868: aload_1        
                //   869: monitorenter   
                //   870: aload_0        
                //   871: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   874: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
                //   877: invokevirtual   java/util/LinkedList.clear:()V
                //   880: aload_1        
                //   881: monitorexit    
                //   882: aload_0        
                //   883: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   886: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/media/AudioTrack;
                //   889: ifnull          921
                //   892: aload_0        
                //   893: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   896: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/media/AudioTrack;
                //   899: invokevirtual   android/media/AudioTrack.stop:()V
                //   902: aload_0        
                //   903: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   906: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/media/AudioTrack;
                //   909: invokevirtual   android/media/AudioTrack.release:()V
                //   912: aload_0        
                //   913: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   916: aconst_null    
                //   917: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$102:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;Landroid/media/AudioTrack;)Landroid/media/AudioTrack;
                //   920: pop            
                //   921: aload_0        
                //   922: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   925: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$700:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)V
                //   928: aload_0        
                //   929: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   932: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.startRenderer:()V
                //   935: aload_0        
                //   936: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   939: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //   942: astore_1       
                //   943: aload_1        
                //   944: monitorenter   
                //   945: aload_0        
                //   946: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   949: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //   952: invokevirtual   java/lang/Object.notify:()V
                //   955: aload_1        
                //   956: monitorexit    
                //   957: ldc             "MediaDecoder2Audio"
                //   959: ldc_w           "render state flushed"
                //   962: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   965: pop            
                //   966: return         
                //   967: astore          6
                //   969: aload_1        
                //   970: monitorexit    
                //   971: aload           6
                //   973: athrow         
                //   974: astore_1       
                //   975: ldc             "MediaDecoder2Audio"
                //   977: ldc_w           "mAudioTrack already stopped/uninitialized"
                //   980: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   983: pop            
                //   984: goto            902
                //   987: astore          6
                //   989: aload_1        
                //   990: monitorexit    
                //   991: aload           6
                //   993: athrow         
                //   994: aload_0        
                //   995: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //   998: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //  1001: ifnull          44
                //  1004: aload_0        
                //  1005: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
                //  1008: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //  1011: iconst_1       
                //  1012: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onFlushed:(Z)V
                //  1017: return         
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                             
                //  -----  -----  -----  -----  ---------------------------------
                //  55     96     216    223    Any
                //  96     98     216    223    Any
                //  117    168    223    229    Any
                //  168    171    223    229    Any
                //  218    220    216    223    Any
                //  224    227    223    229    Any
                //  236    247    626    653    Ljava/lang/Exception;
                //  455    479    708    714    Any
                //  479    491    714    728    Ljava/lang/Exception;
                //  595    616    619    626    Any
                //  616    618    619    626    Any
                //  621    623    619    626    Any
                //  709    712    708    714    Any
                //  740    755    619    626    Any
                //  779    801    834    841    Any
                //  836    838    834    841    Any
                //  870    882    967    974    Any
                //  892    902    974    987    Ljava/lang/IllegalStateException;
                //  945    957    987    994    Any
                //  969    971    967    974    Any
                //  989    991    987    994    Any
                // 
                // The error that occurred was:
                // 
                // java.lang.IndexOutOfBoundsException: Index: 479, Size: 479
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
