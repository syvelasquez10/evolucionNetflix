// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import com.netflix.mediaclient.Log;
import android.os.Message;
import android.os.Looper;
import android.view.Surface;
import android.media.MediaFormat;
import android.os.HandlerThread;
import android.os.Handler;
import android.annotation.TargetApi;
import android.annotation.SuppressLint;

@SuppressLint({ "HandlerLeak" })
@TargetApi(16)
public class VideoDecoderPipe extends MediaDecoderPipe
{
    private static final int DEFAULT_LOOPING_TIME = 30;
    private static final int FAST_LOOPING_TIME = 10;
    private static final int MAX_LOOPING_TIME = 50;
    private static final int MSG_RENDER_FRAME = 1;
    private static final int RENDER_SKIP = 30;
    private static final int RENDER_WHIGH = 20;
    private static final int SCHEDULE_OFFSET = 5;
    private static final String TAG = "MediaPipeVideo";
    private volatile boolean mDecoderStopped;
    private boolean mFirstFrameRendered;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    RenderHeartbeat mHearbeat;
    private boolean mLastFrameRendered;
    private boolean mPaused;
    private boolean mRendererStarted;
    private String mTag;
    private long nFrameRendered;
    private long nFrameSkipped;
    private long previousPts;
    
    public VideoDecoderPipe(final InputDataSource inputDataSource, final String s, final MediaFormat mediaFormat, final Surface surface, final String s2) throws Exception {
        super(inputDataSource, s, mediaFormat, surface, s2);
        this.nFrameRendered = 0L;
        this.nFrameSkipped = 0L;
        this.mRendererStarted = false;
        this.mLastFrameRendered = false;
        this.mFirstFrameRendered = false;
        this.mPaused = false;
        this.previousPts = -1L;
        this.mDecoderStopped = false;
        this.mHearbeat = new RenderHeartbeat();
        this.makeHandler();
        final StringBuilder sb = new StringBuilder("MediaPipeVideo");
        sb.append(s2);
        this.mTag = sb.toString();
    }
    
    private void makeHandler() {
        (this.mHandlerThread = new HandlerThread("RenderThread")).start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) {
            public void handleMessage(final Message p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //     4: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mHearbeat:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$RenderHeartbeat;
                //     7: invokevirtual   com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$RenderHeartbeat.ShowHearbeat:()V
                //    10: aload_1        
                //    11: getfield        android/os/Message.what:I
                //    14: tableswitch {
                //                2: 46
                //          default: 32
                //        }
                //    32: aload_0        
                //    33: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //    36: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
                //    39: ldc             "handler had unknown message"
                //    41: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //    44: pop            
                //    45: return         
                //    46: iconst_m1      
                //    47: istore_3       
                //    48: aconst_null    
                //    49: astore          14
                //    51: ldc2_w          20
                //    54: lstore          6
                //    56: aload_0        
                //    57: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //    60: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
                //    63: astore          15
                //    65: aload           15
                //    67: monitorenter   
                //    68: iload_3        
                //    69: istore_2       
                //    70: aload           14
                //    72: astore_1       
                //    73: aload_0        
                //    74: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //    77: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
                //    80: invokevirtual   java/util/LinkedList.isEmpty:()Z
                //    83: ifne            128
                //    86: iload_3        
                //    87: istore_2       
                //    88: aload           14
                //    90: astore_1       
                //    91: aload_0        
                //    92: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //    95: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$000:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Z
                //    98: ifne            128
                //   101: aload_0        
                //   102: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   105: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
                //   108: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
                //   111: checkcast       Ljava/lang/Integer;
                //   114: invokevirtual   java/lang/Integer.intValue:()I
                //   117: istore_2       
                //   118: aload_0        
                //   119: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   122: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                //   125: iload_2        
                //   126: aaload         
                //   127: astore_1       
                //   128: lload           6
                //   130: lstore          4
                //   132: iload_2        
                //   133: iconst_m1      
                //   134: if_icmpeq       189
                //   137: lload           6
                //   139: lstore          4
                //   141: aload_1        
                //   142: ifnull          189
                //   145: lload           6
                //   147: lstore          4
                //   149: aload_0        
                //   150: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   153: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
                //   156: ifnull          189
                //   159: aload_1        
                //   160: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
                //   163: ldc2_w          1000
                //   166: ldiv           
                //   167: lstore          8
                //   169: aload_0        
                //   170: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   173: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
                //   176: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
                //   179: lconst_0       
                //   180: lcmp           
                //   181: ifge            384
                //   184: ldc2_w          30
                //   187: lstore          4
                //   189: aload           15
                //   191: monitorexit    
                //   192: aload_0        
                //   193: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   196: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$700:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Z
                //   199: ifeq            289
                //   202: aload_0        
                //   203: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   206: iconst_1       
                //   207: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
                //   210: pop            
                //   211: ldc2_w          -1
                //   214: lstore          6
                //   216: lload           6
                //   218: lstore          4
                //   220: aload_0        
                //   221: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   224: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
                //   227: iconst_3       
                //   228: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   231: ifeq            289
                //   234: aload_0        
                //   235: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   238: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
                //   241: new             Ljava/lang/StringBuilder;
                //   244: dup            
                //   245: invokespecial   java/lang/StringBuilder.<init>:()V
                //   248: ldc             "EOS: stopped, rendered frame "
                //   250: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   253: aload_0        
                //   254: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   257: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
                //   260: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   263: ldc             ",skipped frame "
                //   265: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   268: aload_0        
                //   269: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   272: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
                //   275: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   278: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   281: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   284: pop            
                //   285: lload           6
                //   287: lstore          4
                //   289: aload_0        
                //   290: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   293: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Z
                //   296: ifne            45
                //   299: lload           4
                //   301: ldc2_w          60
                //   304: lcmp           
                //   305: ifgt            319
                //   308: lload           4
                //   310: lstore          6
                //   312: lload           4
                //   314: lconst_0       
                //   315: lcmp           
                //   316: ifgt            369
                //   319: aload_0        
                //   320: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   323: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
                //   326: iconst_3       
                //   327: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   330: ifeq            364
                //   333: aload_0        
                //   334: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   337: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
                //   340: new             Ljava/lang/StringBuilder;
                //   343: dup            
                //   344: invokespecial   java/lang/StringBuilder.<init>:()V
                //   347: ldc             "unexpect loop time "
                //   349: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   352: lload           4
                //   354: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   357: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   360: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   363: pop            
                //   364: ldc2_w          30
                //   367: lstore          6
                //   369: aload_0        
                //   370: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   373: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$800:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Landroid/os/Handler;
                //   376: iconst_1       
                //   377: lload           6
                //   379: invokevirtual   android/os/Handler.sendEmptyMessageDelayed:(IJ)Z
                //   382: pop            
                //   383: return         
                //   384: lload           8
                //   386: aload_0        
                //   387: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   390: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
                //   393: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
                //   396: lsub           
                //   397: lstore          12
                //   399: lload           12
                //   401: ldc2_w          -30
                //   404: lcmp           
                //   405: ifle            826
                //   408: iconst_1       
                //   409: istore_3       
                //   410: lload           12
                //   412: ldc2_w          20
                //   415: lcmp           
                //   416: ifge            1199
                //   419: lload           6
                //   421: lstore          4
                //   423: aload_0        
                //   424: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   427: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$100:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
                //   430: lconst_0       
                //   431: lcmp           
                //   432: ifle            472
                //   435: lload           8
                //   437: aload_0        
                //   438: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   441: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$100:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
                //   444: lsub           
                //   445: lstore          6
                //   447: lload           6
                //   449: ldc2_w          30
                //   452: lcmp           
                //   453: iflt            1228
                //   456: lload           6
                //   458: lstore          4
                //   460: lload           6
                //   462: ldc2_w          50
                //   465: lcmp           
                //   466: ifle            472
                //   469: goto            1228
                //   472: iload_3        
                //   473: ifeq            1255
                //   476: aload_0        
                //   477: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   480: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
                //   483: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
                //   486: pop            
                //   487: aload_0        
                //   488: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   491: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                //   494: iload_2        
                //   495: aconst_null    
                //   496: aastore        
                //   497: aload_0        
                //   498: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   501: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mDecoder:Landroid/media/MediaCodec;
                //   504: iload_2        
                //   505: iconst_1       
                //   506: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
                //   509: lload           4
                //   511: lload           12
                //   513: ladd           
                //   514: lstore          6
                //   516: lload           6
                //   518: ldc2_w          5
                //   521: lcmp           
                //   522: ifle            1236
                //   525: lload           6
                //   527: ldc2_w          5
                //   530: lsub           
                //   531: lstore          4
                //   533: aload_0        
                //   534: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   537: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$400:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Z
                //   540: ifne            577
                //   543: aload_0        
                //   544: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   547: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
                //   550: ldc             "first buffer to render"
                //   552: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   555: pop            
                //   556: aload_0        
                //   557: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   560: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
                //   563: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener.onStartRender:()V
                //   568: aload_0        
                //   569: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   572: iconst_1       
                //   573: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$402:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
                //   576: pop            
                //   577: aload_1        
                //   578: astore          14
                //   580: lload           8
                //   582: lstore          10
                //   584: lload           4
                //   586: lstore          6
                //   588: lload           12
                //   590: ldc2_w          -20
                //   593: lcmp           
                //   594: ifge            699
                //   597: aload_1        
                //   598: astore          14
                //   600: lload           8
                //   602: lstore          10
                //   604: lload           4
                //   606: lstore          6
                //   608: aload_0        
                //   609: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   612: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
                //   615: iconst_3       
                //   616: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   619: ifeq            699
                //   622: aload_0        
                //   623: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   626: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
                //   629: new             Ljava/lang/StringBuilder;
                //   632: dup            
                //   633: invokespecial   java/lang/StringBuilder.<init>:()V
                //   636: ldc             "STAT:rendered frame "
                //   638: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   641: aload_0        
                //   642: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   645: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
                //   648: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   651: ldc             " @"
                //   653: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   656: lload           8
                //   658: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   661: ldc             ", with delta "
                //   663: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   666: lload           12
                //   668: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   671: ldc             ", next after "
                //   673: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   676: lload           4
                //   678: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   681: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   684: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   687: pop            
                //   688: lload           4
                //   690: lstore          6
                //   692: lload           8
                //   694: lstore          10
                //   696: aload_1        
                //   697: astore          14
                //   699: aload_0        
                //   700: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   703: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$508:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
                //   706: pop2           
                //   707: aload_0        
                //   708: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   711: lload           10
                //   713: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$102:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;J)J
                //   716: pop2           
                //   717: lload           6
                //   719: lstore          4
                //   721: aload           14
                //   723: getfield        android/media/MediaCodec$BufferInfo.flags:I
                //   726: iconst_4       
                //   727: if_icmpne       189
                //   730: aload_0        
                //   731: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   734: iconst_1       
                //   735: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
                //   738: pop            
                //   739: ldc2_w          -1
                //   742: lstore          6
                //   744: lload           6
                //   746: lstore          4
                //   748: aload_0        
                //   749: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   752: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
                //   755: iconst_3       
                //   756: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   759: ifeq            189
                //   762: aload_0        
                //   763: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   766: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
                //   769: new             Ljava/lang/StringBuilder;
                //   772: dup            
                //   773: invokespecial   java/lang/StringBuilder.<init>:()V
                //   776: ldc             "EOS: has flag, rendered frame "
                //   778: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   781: aload_0        
                //   782: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   785: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
                //   788: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   791: ldc             ",skipped frame "
                //   793: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   796: aload_0        
                //   797: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   800: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
                //   803: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   806: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   809: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   812: pop            
                //   813: lload           6
                //   815: lstore          4
                //   817: goto            189
                //   820: astore_1       
                //   821: aload           15
                //   823: monitorexit    
                //   824: aload_1        
                //   825: athrow         
                //   826: iconst_0       
                //   827: istore_3       
                //   828: goto            410
                //   831: astore          14
                //   833: aload_0        
                //   834: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   837: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
                //   840: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
                //   842: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   845: pop            
                //   846: aload_0        
                //   847: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   850: iconst_1       
                //   851: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
                //   854: pop            
                //   855: goto            509
                //   858: aload_0        
                //   859: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   862: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
                //   865: invokevirtual   java/util/LinkedList.size:()I
                //   868: iconst_1       
                //   869: if_icmple       999
                //   872: lload           12
                //   874: ldc2_w          -30
                //   877: lcmp           
                //   878: ifgt            999
                //   881: aload_0        
                //   882: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   885: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
                //   888: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
                //   891: pop            
                //   892: aload_0        
                //   893: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   896: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                //   899: iload_2        
                //   900: aconst_null    
                //   901: aastore        
                //   902: aload_0        
                //   903: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   906: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mDecoder:Landroid/media/MediaCodec;
                //   909: iload_2        
                //   910: iconst_0       
                //   911: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
                //   914: aload_0        
                //   915: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   918: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
                //   921: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
                //   924: checkcast       Ljava/lang/Integer;
                //   927: invokevirtual   java/lang/Integer.intValue:()I
                //   930: istore_2       
                //   931: aload_0        
                //   932: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   935: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                //   938: iload_2        
                //   939: aaload         
                //   940: astore_1       
                //   941: aload_1        
                //   942: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
                //   945: ldc2_w          1000
                //   948: ldiv           
                //   949: lstore          4
                //   951: lload           4
                //   953: aload_0        
                //   954: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   957: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
                //   960: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
                //   963: lsub           
                //   964: lstore          12
                //   966: iload_3        
                //   967: iconst_1       
                //   968: iadd           
                //   969: istore_3       
                //   970: goto            858
                //   973: astore_1       
                //   974: aload_0        
                //   975: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   978: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
                //   981: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
                //   983: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   986: pop            
                //   987: aload_0        
                //   988: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //   991: iconst_1       
                //   992: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
                //   995: pop            
                //   996: goto            914
                //   999: aload_0        
                //  1000: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //  1003: iload_3        
                //  1004: i2l            
                //  1005: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$514:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;J)J
                //  1008: pop2           
                //  1009: aload_0        
                //  1010: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //  1013: iload_3        
                //  1014: i2l            
                //  1015: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$614:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;J)J
                //  1018: pop2           
                //  1019: aload_0        
                //  1020: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //  1023: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
                //  1026: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
                //  1029: pop            
                //  1030: aload_0        
                //  1031: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //  1034: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                //  1037: iload_2        
                //  1038: aconst_null    
                //  1039: aastore        
                //  1040: aload_0        
                //  1041: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //  1044: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mDecoder:Landroid/media/MediaCodec;
                //  1047: iload_2        
                //  1048: iconst_1       
                //  1049: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
                //  1052: ldc2_w          10
                //  1055: lstore          8
                //  1057: aload_1        
                //  1058: astore          14
                //  1060: lload           4
                //  1062: lstore          10
                //  1064: lload           8
                //  1066: lstore          6
                //  1068: aload_0        
                //  1069: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //  1072: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
                //  1075: iconst_3       
                //  1076: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //  1079: ifeq            699
                //  1082: aload_0        
                //  1083: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //  1086: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
                //  1089: new             Ljava/lang/StringBuilder;
                //  1092: dup            
                //  1093: invokespecial   java/lang/StringBuilder.<init>:()V
                //  1096: ldc             "STAT:REND frame "
                //  1098: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1101: aload_0        
                //  1102: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //  1105: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
                //  1108: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //  1111: ldc             " skipped "
                //  1113: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1116: iload_3        
                //  1117: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //  1120: ldc             " @"
                //  1122: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1125: lload           4
                //  1127: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //  1130: ldc             ", with delta "
                //  1132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1135: lload           12
                //  1137: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //  1140: ldc             ", next after "
                //  1142: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1145: ldc2_w          10
                //  1148: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //  1151: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //  1154: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //  1157: pop            
                //  1158: aload_1        
                //  1159: astore          14
                //  1161: lload           4
                //  1163: lstore          10
                //  1165: lload           8
                //  1167: lstore          6
                //  1169: goto            699
                //  1172: astore          14
                //  1174: aload_0        
                //  1175: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //  1178: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
                //  1181: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
                //  1183: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //  1186: pop            
                //  1187: aload_0        
                //  1188: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
                //  1191: iconst_1       
                //  1192: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
                //  1195: pop            
                //  1196: goto            1052
                //  1199: lload           12
                //  1201: ldc2_w          5
                //  1204: lsub           
                //  1205: lstore          6
                //  1207: lload           6
                //  1209: lstore          4
                //  1211: lload           6
                //  1213: ldc2_w          50
                //  1216: lcmp           
                //  1217: ifle            189
                //  1220: ldc2_w          50
                //  1223: lstore          4
                //  1225: goto            189
                //  1228: ldc2_w          30
                //  1231: lstore          4
                //  1233: goto            472
                //  1236: lload           6
                //  1238: lstore          4
                //  1240: lload           6
                //  1242: lconst_0       
                //  1243: lcmp           
                //  1244: ifge            533
                //  1247: ldc2_w          10
                //  1250: lstore          4
                //  1252: goto            533
                //  1255: iconst_0       
                //  1256: istore_3       
                //  1257: lload           8
                //  1259: lstore          4
                //  1261: goto            858
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  73     86     820    826    Any
                //  91     128    820    826    Any
                //  149    184    820    826    Any
                //  189    192    820    826    Any
                //  384    399    820    826    Any
                //  423    447    820    826    Any
                //  476    497    820    826    Any
                //  497    509    831    858    Ljava/lang/Exception;
                //  497    509    820    826    Any
                //  533    577    820    826    Any
                //  608    688    820    826    Any
                //  699    717    820    826    Any
                //  721    739    820    826    Any
                //  748    813    820    826    Any
                //  821    824    820    826    Any
                //  833    855    820    826    Any
                //  858    872    820    826    Any
                //  881    902    820    826    Any
                //  902    914    973    999    Ljava/lang/Exception;
                //  902    914    820    826    Any
                //  914    966    820    826    Any
                //  974    996    820    826    Any
                //  999    1040   820    826    Any
                //  1040   1052   1172   1199   Ljava/lang/Exception;
                //  1040   1052   820    826    Any
                //  1068   1158   820    826    Any
                //  1174   1196   820    826    Any
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0509:
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
    public void pause() {
        this.mPaused = true;
        super.pause();
    }
    
    @Override
    boolean renderOutput(final boolean b) {
        if (!b) {
            this.mDecoderStopped = true;
            if (this.mHandler != null) {
                this.mHandler.removeMessages(1);
            }
            if (this.mHandlerThread != null) {
                this.mHandlerThread.quit();
            }
        }
        else {
            this.mDecoderStopped = false;
            if (!this.mLastFrameRendered) {
                if (!this.mRendererStarted) {
                    Log.d(this.mTag, "start rendering");
                    this.mHandler.sendEmptyMessage(1);
                    this.mRendererStarted = true;
                }
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void unpause() {
        this.mPaused = false;
        super.unpause();
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
                if (Log.isLoggable(VideoDecoderPipe.this.mTag, 3)) {
                    Log.d(VideoDecoderPipe.this.mTag, "render alive, rendered frame " + VideoDecoderPipe.this.nFrameRendered + ",skipped frame " + VideoDecoderPipe.this.nFrameSkipped);
                }
            }
        }
    }
}
