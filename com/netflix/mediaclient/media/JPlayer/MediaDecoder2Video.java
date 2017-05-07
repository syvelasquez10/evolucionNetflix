// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import com.netflix.mediaclient.Log;
import android.os.Message;
import android.os.Looper;
import android.os.Process;
import android.media.MediaCrypto;
import android.view.Surface;
import android.media.MediaFormat;
import android.os.HandlerThread;
import android.os.Handler;
import android.annotation.TargetApi;

@TargetApi(16)
public class MediaDecoder2Video extends MediaDecoderPipe2
{
    private static final int DEFAULT_LOOPING_TIME = 30;
    private static final int FAST_LOOPING_TIME = 10;
    private static final int MAX_LOOPING_TIME = 50;
    private static final int MSG_RENDER_FLUSH = 2;
    private static final int MSG_RENDER_FRAME = 1;
    private static final int RENDER_SKIP = 30;
    private static final int RENDER_WHIGH = 20;
    private static final int SCHEDULE_OFFSET = 5;
    private static final String TAG = "MediaDecoder2Video";
    private volatile boolean mDecoderStopped;
    private boolean mFirstFrameRendered;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    RenderHeartbeat mHearbeat;
    private boolean mLastFrameRendered;
    private boolean mPaused;
    private final LocalStateNotifier mRenderState;
    private boolean mRendererStarted;
    private long nFrameRendered;
    private long nFrameSkipped;
    private long previousPts;
    
    public MediaDecoder2Video(final InputDataSource inputDataSource, final String s, final MediaFormat mediaFormat, final Surface surface, final MediaCrypto mediaCrypto) throws Exception {
        super(inputDataSource, s, mediaFormat, surface, mediaCrypto);
        this.nFrameRendered = 0L;
        this.nFrameSkipped = 0L;
        this.mRendererStarted = false;
        this.mLastFrameRendered = false;
        this.mFirstFrameRendered = false;
        this.mPaused = false;
        this.previousPts = -1L;
        this.mDecoderStopped = false;
        this.mRenderState = new LocalStateNotifier(this);
        this.mHearbeat = new RenderHeartbeat();
    }
    
    @Override
    void createRenderer() {
        this.mHandlerThread = new HandlerThread("RenderThreadVideo");
        while (true) {
            try {
                Process.setThreadPriority(-4);
                this.mHandlerThread.start();
                this.mHandler = new Handler(this.mHandlerThread.getLooper()) {
                    public void handleMessage(final Message p0) {
                        // 
                        // This method could not be decompiled.
                        // 
                        // Original Bytecode:
                        // 
                        //     0: aload_0        
                        //     1: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //     4: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mHearbeat:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$RenderHeartbeat;
                        //     7: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$RenderHeartbeat.ShowHearbeat:()V
                        //    10: aload_1        
                        //    11: getfield        android/os/Message.what:I
                        //    14: tableswitch {
                        //                2: 45
                        //                3: 1221
                        //          default: 36
                        //        }
                        //    36: ldc             "MediaDecoder2Video"
                        //    38: ldc             "RenderThreadVideo had unknown message"
                        //    40: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //    43: pop            
                        //    44: return         
                        //    45: iconst_m1      
                        //    46: istore_3       
                        //    47: aconst_null    
                        //    48: astore          14
                        //    50: ldc2_w          20
                        //    53: lstore          6
                        //    55: aload_0        
                        //    56: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //    59: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //    62: astore          15
                        //    64: aload           15
                        //    66: monitorenter   
                        //    67: iload_3        
                        //    68: istore_2       
                        //    69: aload           14
                        //    71: astore_1       
                        //    72: aload_0        
                        //    73: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //    76: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //    79: invokevirtual   java/util/LinkedList.isEmpty:()Z
                        //    82: ifne            127
                        //    85: iload_3        
                        //    86: istore_2       
                        //    87: aload           14
                        //    89: astore_1       
                        //    90: aload_0        
                        //    91: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //    94: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                        //    97: ifne            127
                        //   100: aload_0        
                        //   101: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   104: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //   107: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
                        //   110: checkcast       Ljava/lang/Integer;
                        //   113: invokevirtual   java/lang/Integer.intValue:()I
                        //   116: istore_2       
                        //   117: aload_0        
                        //   118: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   121: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                        //   124: iload_2        
                        //   125: aaload         
                        //   126: astore_1       
                        //   127: aload_1        
                        //   128: ifnull          171
                        //   131: aload_1        
                        //   132: getfield        android/media/MediaCodec$BufferInfo.flags:I
                        //   135: iconst_4       
                        //   136: iand           
                        //   137: ifeq            171
                        //   140: ldc             "MediaDecoder2Video"
                        //   142: ldc             "renderer got buffer BUFFER_FLAG_END_OF_STREAM"
                        //   144: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   147: pop            
                        //   148: aload_0        
                        //   149: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   152: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                        //   155: ifnull          171
                        //   158: aload_0        
                        //   159: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   162: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                        //   165: iconst_0       
                        //   166: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onEndOfStream:(Z)V
                        //   171: lload           6
                        //   173: lstore          4
                        //   175: iload_2        
                        //   176: iconst_m1      
                        //   177: if_icmpeq       232
                        //   180: lload           6
                        //   182: lstore          4
                        //   184: aload_1        
                        //   185: ifnull          232
                        //   188: lload           6
                        //   190: lstore          4
                        //   192: aload_0        
                        //   193: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   196: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                        //   199: ifnull          232
                        //   202: aload_1        
                        //   203: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
                        //   206: ldc2_w          1000
                        //   209: ldiv           
                        //   210: lstore          8
                        //   212: aload_0        
                        //   213: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   216: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                        //   219: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
                        //   222: lconst_0       
                        //   223: lcmp           
                        //   224: ifge            407
                        //   227: ldc2_w          30
                        //   230: lstore          4
                        //   232: aload           15
                        //   234: monitorexit    
                        //   235: aload_0        
                        //   236: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   239: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                        //   242: ifeq            322
                        //   245: aload_0        
                        //   246: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   249: iconst_1       
                        //   250: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$202:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //   253: pop            
                        //   254: ldc2_w          -1
                        //   257: lstore          6
                        //   259: lload           6
                        //   261: lstore          4
                        //   263: ldc             "MediaDecoder2Video"
                        //   265: iconst_3       
                        //   266: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                        //   269: ifeq            322
                        //   272: ldc             "MediaDecoder2Video"
                        //   274: new             Ljava/lang/StringBuilder;
                        //   277: dup            
                        //   278: invokespecial   java/lang/StringBuilder.<init>:()V
                        //   281: ldc             "EOS: stopped, rendered frame "
                        //   283: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   286: aload_0        
                        //   287: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   290: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   293: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   296: ldc             ",skipped frame "
                        //   298: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   301: aload_0        
                        //   302: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   305: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   308: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   311: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                        //   314: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   317: pop            
                        //   318: lload           6
                        //   320: lstore          4
                        //   322: aload_0        
                        //   323: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   326: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                        //   329: ifne            44
                        //   332: lload           4
                        //   334: ldc2_w          60
                        //   337: lcmp           
                        //   338: ifgt            352
                        //   341: lload           4
                        //   343: lstore          6
                        //   345: lload           4
                        //   347: lconst_0       
                        //   348: lcmp           
                        //   349: ifgt            392
                        //   352: ldc             "MediaDecoder2Video"
                        //   354: iconst_3       
                        //   355: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                        //   358: ifeq            387
                        //   361: ldc             "MediaDecoder2Video"
                        //   363: new             Ljava/lang/StringBuilder;
                        //   366: dup            
                        //   367: invokespecial   java/lang/StringBuilder.<init>:()V
                        //   370: ldc             "unexpect loop time "
                        //   372: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   375: lload           4
                        //   377: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   380: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                        //   383: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   386: pop            
                        //   387: ldc2_w          30
                        //   390: lstore          6
                        //   392: aload_0        
                        //   393: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   396: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$700:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Landroid/os/Handler;
                        //   399: iconst_1       
                        //   400: lload           6
                        //   402: invokevirtual   android/os/Handler.sendEmptyMessageDelayed:(IJ)Z
                        //   405: pop            
                        //   406: return         
                        //   407: lload           8
                        //   409: aload_0        
                        //   410: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   413: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                        //   416: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
                        //   419: lsub           
                        //   420: lstore          12
                        //   422: lload           12
                        //   424: ldc2_w          -30
                        //   427: lcmp           
                        //   428: ifle            844
                        //   431: iconst_1       
                        //   432: istore_3       
                        //   433: lload           12
                        //   435: ldc2_w          20
                        //   438: lcmp           
                        //   439: ifge            1192
                        //   442: lload           6
                        //   444: lstore          4
                        //   446: aload_0        
                        //   447: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   450: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   453: lconst_0       
                        //   454: lcmp           
                        //   455: ifle            495
                        //   458: lload           8
                        //   460: aload_0        
                        //   461: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   464: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   467: lsub           
                        //   468: lstore          6
                        //   470: lload           6
                        //   472: ldc2_w          30
                        //   475: lcmp           
                        //   476: iflt            1288
                        //   479: lload           6
                        //   481: lstore          4
                        //   483: lload           6
                        //   485: ldc2_w          50
                        //   488: lcmp           
                        //   489: ifle            495
                        //   492: goto            1288
                        //   495: iload_3        
                        //   496: ifeq            1315
                        //   499: aload_0        
                        //   500: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   503: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //   506: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
                        //   509: pop            
                        //   510: aload_0        
                        //   511: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   514: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                        //   517: iload_2        
                        //   518: aconst_null    
                        //   519: aastore        
                        //   520: aload_0        
                        //   521: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   524: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
                        //   527: iload_2        
                        //   528: iconst_1       
                        //   529: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
                        //   532: lload           4
                        //   534: lload           12
                        //   536: ladd           
                        //   537: lstore          6
                        //   539: lload           6
                        //   541: ldc2_w          5
                        //   544: lcmp           
                        //   545: ifle            1296
                        //   548: lload           6
                        //   550: ldc2_w          5
                        //   553: lsub           
                        //   554: lstore          4
                        //   556: aload_0        
                        //   557: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   560: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                        //   563: ifne            583
                        //   566: ldc             "MediaDecoder2Video"
                        //   568: ldc             "first buffer to render"
                        //   570: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   573: pop            
                        //   574: aload_0        
                        //   575: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   578: iconst_1       
                        //   579: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$302:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //   582: pop            
                        //   583: aload_0        
                        //   584: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   587: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                        //   590: ifnull          615
                        //   593: aload_0        
                        //   594: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   597: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                        //   600: iconst_0       
                        //   601: aload_0        
                        //   602: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   605: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   608: lload           8
                        //   610: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onSampleRendered:(ZJJ)V
                        //   615: lload           8
                        //   617: lstore          10
                        //   619: aload_1        
                        //   620: astore          14
                        //   622: lload           4
                        //   624: lstore          6
                        //   626: lload           12
                        //   628: ldc2_w          -20
                        //   631: lcmp           
                        //   632: ifge            727
                        //   635: lload           8
                        //   637: lstore          10
                        //   639: aload_1        
                        //   640: astore          14
                        //   642: lload           4
                        //   644: lstore          6
                        //   646: ldc             "MediaDecoder2Video"
                        //   648: iconst_3       
                        //   649: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                        //   652: ifeq            727
                        //   655: ldc             "MediaDecoder2Video"
                        //   657: new             Ljava/lang/StringBuilder;
                        //   660: dup            
                        //   661: invokespecial   java/lang/StringBuilder.<init>:()V
                        //   664: ldc             "STAT:rendered frame "
                        //   666: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   669: aload_0        
                        //   670: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   673: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   676: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   679: ldc             " @"
                        //   681: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   684: lload           8
                        //   686: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   689: ldc             ", with delta "
                        //   691: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   694: lload           12
                        //   696: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   699: ldc             ", next after "
                        //   701: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   704: lload           4
                        //   706: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   709: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                        //   712: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   715: pop            
                        //   716: lload           4
                        //   718: lstore          6
                        //   720: aload_1        
                        //   721: astore          14
                        //   723: lload           8
                        //   725: lstore          10
                        //   727: aload_0        
                        //   728: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   731: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$408:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   734: pop2           
                        //   735: aload_0        
                        //   736: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   739: lload           10
                        //   741: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$102:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
                        //   744: pop2           
                        //   745: lload           6
                        //   747: lstore          4
                        //   749: aload           14
                        //   751: getfield        android/media/MediaCodec$BufferInfo.flags:I
                        //   754: iconst_4       
                        //   755: if_icmpne       232
                        //   758: aload_0        
                        //   759: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   762: iconst_1       
                        //   763: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$202:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //   766: pop            
                        //   767: ldc2_w          -1
                        //   770: lstore          6
                        //   772: lload           6
                        //   774: lstore          4
                        //   776: ldc             "MediaDecoder2Video"
                        //   778: iconst_3       
                        //   779: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                        //   782: ifeq            232
                        //   785: ldc             "MediaDecoder2Video"
                        //   787: new             Ljava/lang/StringBuilder;
                        //   790: dup            
                        //   791: invokespecial   java/lang/StringBuilder.<init>:()V
                        //   794: ldc             "EOS: has flag, rendered frame "
                        //   796: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   799: aload_0        
                        //   800: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   803: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   806: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   809: ldc             ",skipped frame "
                        //   811: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   814: aload_0        
                        //   815: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   818: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   821: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   824: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                        //   827: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   830: pop            
                        //   831: lload           6
                        //   833: lstore          4
                        //   835: goto            232
                        //   838: astore_1       
                        //   839: aload           15
                        //   841: monitorexit    
                        //   842: aload_1        
                        //   843: athrow         
                        //   844: iconst_0       
                        //   845: istore_3       
                        //   846: goto            433
                        //   849: astore          14
                        //   851: ldc             "MediaDecoder2Video"
                        //   853: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
                        //   855: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   858: pop            
                        //   859: aload_0        
                        //   860: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   863: iconst_1       
                        //   864: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$202:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //   867: pop            
                        //   868: goto            532
                        //   871: aload_0        
                        //   872: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   875: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //   878: invokevirtual   java/util/LinkedList.size:()I
                        //   881: iconst_1       
                        //   882: if_icmple       1007
                        //   885: lload           8
                        //   887: ldc2_w          -30
                        //   890: lcmp           
                        //   891: ifgt            1007
                        //   894: aload_0        
                        //   895: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   898: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //   901: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
                        //   904: pop            
                        //   905: aload_0        
                        //   906: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   909: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                        //   912: iload_2        
                        //   913: aconst_null    
                        //   914: aastore        
                        //   915: aload_0        
                        //   916: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   919: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
                        //   922: iload_2        
                        //   923: iconst_0       
                        //   924: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
                        //   927: aload_0        
                        //   928: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   931: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //   934: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
                        //   937: checkcast       Ljava/lang/Integer;
                        //   940: invokevirtual   java/lang/Integer.intValue:()I
                        //   943: istore_2       
                        //   944: aload_0        
                        //   945: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   948: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                        //   951: iload_2        
                        //   952: aaload         
                        //   953: astore_1       
                        //   954: aload_1        
                        //   955: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
                        //   958: ldc2_w          1000
                        //   961: ldiv           
                        //   962: lstore          4
                        //   964: lload           4
                        //   966: aload_0        
                        //   967: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   970: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                        //   973: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
                        //   976: lsub           
                        //   977: lstore          8
                        //   979: iload_3        
                        //   980: iconst_1       
                        //   981: iadd           
                        //   982: istore_3       
                        //   983: goto            871
                        //   986: astore_1       
                        //   987: ldc             "MediaDecoder2Video"
                        //   989: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
                        //   991: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   994: pop            
                        //   995: aload_0        
                        //   996: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   999: iconst_1       
                        //  1000: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$202:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //  1003: pop            
                        //  1004: goto            927
                        //  1007: aload_0        
                        //  1008: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1011: iload_3        
                        //  1012: i2l            
                        //  1013: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$414:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
                        //  1016: pop2           
                        //  1017: aload_0        
                        //  1018: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1021: iload_3        
                        //  1022: i2l            
                        //  1023: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$514:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
                        //  1026: pop2           
                        //  1027: aload_0        
                        //  1028: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1031: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //  1034: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
                        //  1037: pop            
                        //  1038: aload_0        
                        //  1039: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1042: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                        //  1045: iload_2        
                        //  1046: aconst_null    
                        //  1047: aastore        
                        //  1048: aload_0        
                        //  1049: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1052: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
                        //  1055: iload_2        
                        //  1056: iconst_1       
                        //  1057: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
                        //  1060: ldc2_w          10
                        //  1063: lstore          12
                        //  1065: lload           4
                        //  1067: lstore          10
                        //  1069: aload_1        
                        //  1070: astore          14
                        //  1072: lload           12
                        //  1074: lstore          6
                        //  1076: ldc             "MediaDecoder2Video"
                        //  1078: iconst_3       
                        //  1079: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                        //  1082: ifeq            727
                        //  1085: ldc             "MediaDecoder2Video"
                        //  1087: new             Ljava/lang/StringBuilder;
                        //  1090: dup            
                        //  1091: invokespecial   java/lang/StringBuilder.<init>:()V
                        //  1094: ldc             "STAT:REND frame "
                        //  1096: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //  1099: aload_0        
                        //  1100: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1103: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //  1106: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //  1109: ldc             " skipped "
                        //  1111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //  1114: iload_3        
                        //  1115: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                        //  1118: ldc             " @"
                        //  1120: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //  1123: lload           4
                        //  1125: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //  1128: ldc             ", with delta "
                        //  1130: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //  1133: lload           8
                        //  1135: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //  1138: ldc             ", next after "
                        //  1140: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //  1143: ldc2_w          10
                        //  1146: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //  1149: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                        //  1152: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //  1155: pop            
                        //  1156: lload           4
                        //  1158: lstore          10
                        //  1160: aload_1        
                        //  1161: astore          14
                        //  1163: lload           12
                        //  1165: lstore          6
                        //  1167: goto            727
                        //  1170: astore          14
                        //  1172: ldc             "MediaDecoder2Video"
                        //  1174: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
                        //  1176: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //  1179: pop            
                        //  1180: aload_0        
                        //  1181: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1184: iconst_1       
                        //  1185: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$202:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //  1188: pop            
                        //  1189: goto            1060
                        //  1192: lload           12
                        //  1194: ldc2_w          5
                        //  1197: lsub           
                        //  1198: lstore          6
                        //  1200: lload           6
                        //  1202: lstore          4
                        //  1204: lload           6
                        //  1206: ldc2_w          50
                        //  1209: lcmp           
                        //  1210: ifle            232
                        //  1213: ldc2_w          50
                        //  1216: lstore          4
                        //  1218: goto            232
                        //  1221: ldc             "MediaDecoder2Video"
                        //  1223: ldc             "render state flushing"
                        //  1225: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //  1228: pop            
                        //  1229: aload_0        
                        //  1230: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1233: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //  1236: astore_1       
                        //  1237: aload_1        
                        //  1238: monitorenter   
                        //  1239: aload_0        
                        //  1240: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1243: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //  1246: invokevirtual   java/util/LinkedList.clear:()V
                        //  1249: aload_1        
                        //  1250: monitorexit    
                        //  1251: aload_0        
                        //  1252: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1255: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$800:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                        //  1258: astore_1       
                        //  1259: aload_1        
                        //  1260: monitorenter   
                        //  1261: aload_0        
                        //  1262: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1265: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$800:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                        //  1268: invokevirtual   java/lang/Object.notify:()V
                        //  1271: aload_1        
                        //  1272: monitorexit    
                        //  1273: return         
                        //  1274: astore          14
                        //  1276: aload_1        
                        //  1277: monitorexit    
                        //  1278: aload           14
                        //  1280: athrow         
                        //  1281: astore          14
                        //  1283: aload_1        
                        //  1284: monitorexit    
                        //  1285: aload           14
                        //  1287: athrow         
                        //  1288: ldc2_w          30
                        //  1291: lstore          4
                        //  1293: goto            495
                        //  1296: lload           6
                        //  1298: lstore          4
                        //  1300: lload           6
                        //  1302: lconst_0       
                        //  1303: lcmp           
                        //  1304: ifge            556
                        //  1307: ldc2_w          10
                        //  1310: lstore          4
                        //  1312: goto            556
                        //  1315: iconst_0       
                        //  1316: istore_3       
                        //  1317: lload           8
                        //  1319: lstore          4
                        //  1321: lload           12
                        //  1323: lstore          8
                        //  1325: goto            871
                        //    Exceptions:
                        //  Try           Handler
                        //  Start  End    Start  End    Type                 
                        //  -----  -----  -----  -----  ---------------------
                        //  72     85     838    844    Any
                        //  90     127    838    844    Any
                        //  131    171    838    844    Any
                        //  192    227    838    844    Any
                        //  232    235    838    844    Any
                        //  407    422    838    844    Any
                        //  446    470    838    844    Any
                        //  499    520    838    844    Any
                        //  520    532    849    871    Ljava/lang/Exception;
                        //  520    532    838    844    Any
                        //  556    583    838    844    Any
                        //  583    615    838    844    Any
                        //  646    716    838    844    Any
                        //  727    745    838    844    Any
                        //  749    767    838    844    Any
                        //  776    831    838    844    Any
                        //  839    842    838    844    Any
                        //  851    868    838    844    Any
                        //  871    885    838    844    Any
                        //  894    915    838    844    Any
                        //  915    927    986    1007   Ljava/lang/Exception;
                        //  915    927    838    844    Any
                        //  927    979    838    844    Any
                        //  987    1004   838    844    Any
                        //  1007   1048   838    844    Any
                        //  1048   1060   1170   1192   Ljava/lang/Exception;
                        //  1048   1060   838    844    Any
                        //  1076   1156   838    844    Any
                        //  1172   1189   838    844    Any
                        //  1239   1251   1281   1288   Any
                        //  1261   1273   1274   1281   Any
                        //  1276   1278   1274   1281   Any
                        //  1283   1285   1281   1288   Any
                        // 
                        // The error that occurred was:
                        // 
                        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0532:
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
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:494)
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
            catch (SecurityException ex) {
                Log.e("MediaDecoder2Video", "fail to setPriority " + ex);
                continue;
            }
            break;
        }
    }
    
    @Override
    void flushRenderer() {
        if (this.mHandler != null) {
            synchronized (this.mRenderState) {
                this.mHandler.sendEmptyMessage(2);
                try {
                    this.mRenderState.wait();
                }
                catch (InterruptedException ex) {
                    Log.d("MediaDecoder2Video", "flushRenderer interrupted");
                }
            }
        }
    }
    
    @Override
    void pauseRenderer() {
        this.mPaused = true;
    }
    
    @Override
    void startRenderer() {
        this.mDecoderStopped = false;
        if (!this.mLastFrameRendered && !this.mRendererStarted) {
            Log.d("MediaDecoder2Video", "start rendering");
            this.mHandler.sendEmptyMessage(1);
            this.mRendererStarted = true;
        }
    }
    
    @Override
    void stopRenderer() {
        this.mDecoderStopped = true;
        if (this.mHandler != null) {
            this.mHandler.removeMessages(1);
        }
        if (this.mHandlerThread != null) {
            this.mHandlerThread.quit();
        }
    }
    
    @Override
    void unpauseRenderer() {
        this.mPaused = false;
    }
    
    private class RenderHeartbeat
    {
        static final long HRATBEAT_INTERVAL = 5000L;
        private long mLastBeat;
        
        RenderHeartbeat() {
            this.mLastBeat = System.currentTimeMillis();
        }
        
        void ShowHearbeat() {
            final long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis >= this.mLastBeat + 5000L) {
                this.mLastBeat = currentTimeMillis;
                if (Log.isLoggable("MediaDecoder2Video", 3)) {
                    Log.d("MediaDecoder2Video", "render alive, rendered frame " + MediaDecoder2Video.this.nFrameRendered + ",skipped frame " + MediaDecoder2Video.this.nFrameSkipped);
                }
            }
        }
    }
}
