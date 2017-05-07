// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.os.Message;
import android.os.Looper;
import android.media.MediaCodec;
import java.util.LinkedList;
import android.media.MediaCodec$BufferInfo;
import com.netflix.mediaclient.Log;
import java.util.concurrent.TimeUnit;
import android.media.MediaCrypto;
import android.view.Surface;
import android.media.MediaFormat;
import java.lang.reflect.Method;
import android.os.HandlerThread;
import android.os.Handler;
import android.annotation.TargetApi;

@TargetApi(16)
public class MediaDecoder2Video extends MediaDecoderPipe2
{
    private static final int DEFAULT_LOOPING_TIME = 30;
    private static final int FAST_LOOPING_TIME = 10;
    private static final long MAX_AHEAD_TIMED_RELEASE_MS = 500L;
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
    private LocalStateNotifier mRenderState;
    private boolean mRendererStarted;
    private Method mTimedReleaseOutputBufferMethod;
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
    
    private boolean isTimedReleaseSupported() {
        return this.mTimedReleaseOutputBufferMethod != null;
    }
    
    private void removeFrameFromQ(final int n) {
        synchronized (this.mOutputBuffersQ) {
            this.mOutputBuffersQ.removeFirst();
            this.mOutputBufferInfo[n] = null;
        }
    }
    
    private void tryToReleaseBuffers() {
        long millis = 0L;
    Label_0176_Outer:
        while (true) {
        Label_0263_Outer:
            while (true) {
                final int intValue;
                long presentationTimeUs;
                final long value;
                synchronized (this.mOutputBuffersQ) {
                    if (this.mOutputBuffersQ.isEmpty()) {
                        return;
                    }
                    intValue = this.mOutputBuffersQ.peekFirst();
                    final MediaCodec$BufferInfo mediaCodec$BufferInfo = this.mOutputBufferInfo[intValue];
                    if (intValue == -1 || mediaCodec$BufferInfo == null || this.mRefClock == null) {
                        break Label_0176;
                    }
                    presentationTimeUs = mediaCodec$BufferInfo.presentationTimeUs;
                    value = this.mRefClock.get();
                    if (value < 0L) {
                        this.mHandler.sendEmptyMessageDelayed(1, 30L);
                        return;
                    }
                }
                presentationTimeUs = TimeUnit.MICROSECONDS.toNanos(presentationTimeUs) - TimeUnit.MILLISECONDS.toNanos(value);
                millis = TimeUnit.NANOSECONDS.toMillis(presentationTimeUs);
                Label_0200: {
                    if (millis >= 0L) {
                        break Label_0200;
                    }
                    while (true) {
                        try {
                            this.mDecoder.releaseOutputBuffer(intValue, false);
                            ++this.nFrameRendered;
                            ++this.nFrameSkipped;
                            this.removeFrameFromQ(intValue);
                            // monitorexit(list)
                            continue Label_0176_Outer;
                        }
                        catch (Exception ex) {
                            Log.d("MediaDecoder2Video", "get exception as skip frame with releaseOutputBuffer()");
                            this.mLastFrameRendered = true;
                            continue;
                        }
                        break;
                    }
                }
                if (millis > 500L) {
                    break;
                }
                while (true) {
                    try {
                        this.mTimedReleaseOutputBufferMethod.invoke(this.mDecoder, intValue, System.nanoTime() + presentationTimeUs);
                        ++this.nFrameRendered;
                        this.removeFrameFromQ(intValue);
                        if (this.mEventListener != null) {
                            this.mEventListener.onSampleRendered(false, this.nFrameRendered, value);
                            continue Label_0263_Outer;
                        }
                        continue Label_0263_Outer;
                    }
                    catch (Exception ex2) {
                        Log.d("MediaDecoder2Video", "get exception as a result of timed releaseOutputBuffer()");
                        this.mLastFrameRendered = true;
                        continue;
                    }
                    break;
                }
                break;
            }
        }
        this.mHandler.sendEmptyMessageDelayed(1, millis - 500L);
    }
    // monitorexit(list)
    
    @Override
    void addToRenderer(final int n, final MediaCodec$BufferInfo mediaCodec$BufferInfo) {
        synchronized (this.mOutputBuffersQ) {
            this.mOutputBuffersQ.add(n);
            this.mOutputBufferInfo[n] = mediaCodec$BufferInfo;
            // monitorexit(this.mOutputBuffersQ)
            if (!this.mPaused && this.isTimedReleaseSupported()) {
                this.mHearbeat.ShowHearbeat();
                this.tryToReleaseBuffers();
            }
        }
    }
    
    @Override
    void createRenderer() {
        while (true) {
            if (!MediaDecoder2Video.USE_ANDROID_L_API) {
                break Label_0043;
            }
            try {
                this.mTimedReleaseOutputBufferMethod = MediaCodec.class.getMethod("releaseOutputBuffer", Integer.TYPE, Long.TYPE);
                Log.d("MediaDecoder2Video", "use timed buffer release");
                (this.mHandlerThread = new HandlerThread("RenderThreadVideo", -4)).start();
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
                        //                3: 1249
                        //          default: 36
                        //        }
                        //    36: ldc             "MediaDecoder2Video"
                        //    38: ldc             "RenderThreadVideo had unknown message"
                        //    40: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //    43: pop            
                        //    44: return         
                        //    45: aload_0        
                        //    46: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //    49: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                        //    52: ifeq            73
                        //    55: aload_0        
                        //    56: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //    59: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                        //    62: ifne            44
                        //    65: aload_0        
                        //    66: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //    69: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)V
                        //    72: return         
                        //    73: iconst_m1      
                        //    74: istore_3       
                        //    75: aconst_null    
                        //    76: astore          14
                        //    78: ldc2_w          20
                        //    81: lstore          6
                        //    83: aload_0        
                        //    84: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //    87: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //    90: astore          15
                        //    92: aload           15
                        //    94: monitorenter   
                        //    95: iload_3        
                        //    96: istore_2       
                        //    97: aload           14
                        //    99: astore_1       
                        //   100: aload_0        
                        //   101: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   104: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //   107: invokevirtual   java/util/LinkedList.isEmpty:()Z
                        //   110: ifne            155
                        //   113: iload_3        
                        //   114: istore_2       
                        //   115: aload           14
                        //   117: astore_1       
                        //   118: aload_0        
                        //   119: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   122: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                        //   125: ifne            155
                        //   128: aload_0        
                        //   129: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   132: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //   135: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
                        //   138: checkcast       Ljava/lang/Integer;
                        //   141: invokevirtual   java/lang/Integer.intValue:()I
                        //   144: istore_2       
                        //   145: aload_0        
                        //   146: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   149: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                        //   152: iload_2        
                        //   153: aaload         
                        //   154: astore_1       
                        //   155: aload_1        
                        //   156: ifnull          199
                        //   159: aload_1        
                        //   160: getfield        android/media/MediaCodec$BufferInfo.flags:I
                        //   163: iconst_4       
                        //   164: iand           
                        //   165: ifeq            199
                        //   168: ldc             "MediaDecoder2Video"
                        //   170: ldc             "renderer got buffer BUFFER_FLAG_END_OF_STREAM"
                        //   172: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   175: pop            
                        //   176: aload_0        
                        //   177: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   180: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                        //   183: ifnull          199
                        //   186: aload_0        
                        //   187: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   190: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                        //   193: iconst_0       
                        //   194: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onEndOfStream:(Z)V
                        //   199: lload           6
                        //   201: lstore          4
                        //   203: iload_2        
                        //   204: iconst_m1      
                        //   205: if_icmpeq       260
                        //   208: lload           6
                        //   210: lstore          4
                        //   212: aload_1        
                        //   213: ifnull          260
                        //   216: lload           6
                        //   218: lstore          4
                        //   220: aload_0        
                        //   221: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   224: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                        //   227: ifnull          260
                        //   230: aload_1        
                        //   231: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
                        //   234: ldc2_w          1000
                        //   237: ldiv           
                        //   238: lstore          8
                        //   240: aload_0        
                        //   241: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   244: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                        //   247: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
                        //   250: lconst_0       
                        //   251: lcmp           
                        //   252: ifge            435
                        //   255: ldc2_w          30
                        //   258: lstore          4
                        //   260: aload           15
                        //   262: monitorexit    
                        //   263: aload_0        
                        //   264: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   267: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$800:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                        //   270: ifeq            350
                        //   273: aload_0        
                        //   274: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   277: iconst_1       
                        //   278: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //   281: pop            
                        //   282: ldc2_w          -1
                        //   285: lstore          6
                        //   287: lload           6
                        //   289: lstore          4
                        //   291: ldc             "MediaDecoder2Video"
                        //   293: iconst_3       
                        //   294: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                        //   297: ifeq            350
                        //   300: ldc             "MediaDecoder2Video"
                        //   302: new             Ljava/lang/StringBuilder;
                        //   305: dup            
                        //   306: invokespecial   java/lang/StringBuilder.<init>:()V
                        //   309: ldc             "EOS: stopped, rendered frame "
                        //   311: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   314: aload_0        
                        //   315: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   318: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   321: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   324: ldc             ",skipped frame "
                        //   326: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   329: aload_0        
                        //   330: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   333: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$700:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   336: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   339: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                        //   342: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   345: pop            
                        //   346: lload           6
                        //   348: lstore          4
                        //   350: aload_0        
                        //   351: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   354: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                        //   357: ifne            44
                        //   360: lload           4
                        //   362: ldc2_w          60
                        //   365: lcmp           
                        //   366: ifgt            380
                        //   369: lload           4
                        //   371: lstore          6
                        //   373: lload           4
                        //   375: lconst_0       
                        //   376: lcmp           
                        //   377: ifgt            420
                        //   380: ldc             "MediaDecoder2Video"
                        //   382: iconst_3       
                        //   383: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                        //   386: ifeq            415
                        //   389: ldc             "MediaDecoder2Video"
                        //   391: new             Ljava/lang/StringBuilder;
                        //   394: dup            
                        //   395: invokespecial   java/lang/StringBuilder.<init>:()V
                        //   398: ldc             "unexpect loop time "
                        //   400: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   403: lload           4
                        //   405: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   408: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                        //   411: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   414: pop            
                        //   415: ldc2_w          30
                        //   418: lstore          6
                        //   420: aload_0        
                        //   421: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   424: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$900:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Landroid/os/Handler;
                        //   427: iconst_1       
                        //   428: lload           6
                        //   430: invokevirtual   android/os/Handler.sendEmptyMessageDelayed:(IJ)Z
                        //   433: pop            
                        //   434: return         
                        //   435: lload           8
                        //   437: aload_0        
                        //   438: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   441: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                        //   444: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
                        //   447: lsub           
                        //   448: lstore          12
                        //   450: lload           12
                        //   452: ldc2_w          -30
                        //   455: lcmp           
                        //   456: ifle            872
                        //   459: iconst_1       
                        //   460: istore_3       
                        //   461: lload           12
                        //   463: ldc2_w          20
                        //   466: lcmp           
                        //   467: ifge            1220
                        //   470: lload           6
                        //   472: lstore          4
                        //   474: aload_0        
                        //   475: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   478: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   481: lconst_0       
                        //   482: lcmp           
                        //   483: ifle            523
                        //   486: lload           8
                        //   488: aload_0        
                        //   489: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   492: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   495: lsub           
                        //   496: lstore          6
                        //   498: lload           6
                        //   500: ldc2_w          30
                        //   503: lcmp           
                        //   504: iflt            1316
                        //   507: lload           6
                        //   509: lstore          4
                        //   511: lload           6
                        //   513: ldc2_w          50
                        //   516: lcmp           
                        //   517: ifle            523
                        //   520: goto            1316
                        //   523: iload_3        
                        //   524: ifeq            1343
                        //   527: aload_0        
                        //   528: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   531: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //   534: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
                        //   537: pop            
                        //   538: aload_0        
                        //   539: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   542: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                        //   545: iload_2        
                        //   546: aconst_null    
                        //   547: aastore        
                        //   548: aload_0        
                        //   549: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   552: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
                        //   555: iload_2        
                        //   556: iconst_1       
                        //   557: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
                        //   560: lload           4
                        //   562: lload           12
                        //   564: ladd           
                        //   565: lstore          6
                        //   567: lload           6
                        //   569: ldc2_w          5
                        //   572: lcmp           
                        //   573: ifle            1324
                        //   576: lload           6
                        //   578: ldc2_w          5
                        //   581: lsub           
                        //   582: lstore          4
                        //   584: aload_0        
                        //   585: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   588: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                        //   591: ifne            611
                        //   594: ldc             "MediaDecoder2Video"
                        //   596: ldc             "first buffer to render"
                        //   598: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   601: pop            
                        //   602: aload_0        
                        //   603: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   606: iconst_1       
                        //   607: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$502:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //   610: pop            
                        //   611: aload_0        
                        //   612: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   615: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                        //   618: ifnull          643
                        //   621: aload_0        
                        //   622: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   625: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                        //   628: iconst_0       
                        //   629: aload_0        
                        //   630: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   633: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   636: lload           8
                        //   638: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onSampleRendered:(ZJJ)V
                        //   643: lload           8
                        //   645: lstore          10
                        //   647: aload_1        
                        //   648: astore          14
                        //   650: lload           4
                        //   652: lstore          6
                        //   654: lload           12
                        //   656: ldc2_w          -20
                        //   659: lcmp           
                        //   660: ifge            755
                        //   663: lload           8
                        //   665: lstore          10
                        //   667: aload_1        
                        //   668: astore          14
                        //   670: lload           4
                        //   672: lstore          6
                        //   674: ldc             "MediaDecoder2Video"
                        //   676: iconst_3       
                        //   677: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                        //   680: ifeq            755
                        //   683: ldc             "MediaDecoder2Video"
                        //   685: new             Ljava/lang/StringBuilder;
                        //   688: dup            
                        //   689: invokespecial   java/lang/StringBuilder.<init>:()V
                        //   692: ldc             "STAT:rendered frame "
                        //   694: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   697: aload_0        
                        //   698: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   701: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   704: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   707: ldc             " @"
                        //   709: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   712: lload           8
                        //   714: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   717: ldc             ", with delta "
                        //   719: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   722: lload           12
                        //   724: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   727: ldc             ", next after "
                        //   729: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   732: lload           4
                        //   734: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   737: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                        //   740: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   743: pop            
                        //   744: lload           4
                        //   746: lstore          6
                        //   748: aload_1        
                        //   749: astore          14
                        //   751: lload           8
                        //   753: lstore          10
                        //   755: aload_0        
                        //   756: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   759: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$608:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   762: pop2           
                        //   763: aload_0        
                        //   764: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   767: lload           10
                        //   769: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$302:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
                        //   772: pop2           
                        //   773: lload           6
                        //   775: lstore          4
                        //   777: aload           14
                        //   779: getfield        android/media/MediaCodec$BufferInfo.flags:I
                        //   782: iconst_4       
                        //   783: if_icmpne       260
                        //   786: aload_0        
                        //   787: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   790: iconst_1       
                        //   791: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //   794: pop            
                        //   795: ldc2_w          -1
                        //   798: lstore          6
                        //   800: lload           6
                        //   802: lstore          4
                        //   804: ldc             "MediaDecoder2Video"
                        //   806: iconst_3       
                        //   807: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                        //   810: ifeq            260
                        //   813: ldc             "MediaDecoder2Video"
                        //   815: new             Ljava/lang/StringBuilder;
                        //   818: dup            
                        //   819: invokespecial   java/lang/StringBuilder.<init>:()V
                        //   822: ldc             "EOS: has flag, rendered frame "
                        //   824: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   827: aload_0        
                        //   828: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   831: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   834: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   837: ldc             ",skipped frame "
                        //   839: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   842: aload_0        
                        //   843: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   846: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$700:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   849: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   852: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                        //   855: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   858: pop            
                        //   859: lload           6
                        //   861: lstore          4
                        //   863: goto            260
                        //   866: astore_1       
                        //   867: aload           15
                        //   869: monitorexit    
                        //   870: aload_1        
                        //   871: athrow         
                        //   872: iconst_0       
                        //   873: istore_3       
                        //   874: goto            461
                        //   877: astore          14
                        //   879: ldc             "MediaDecoder2Video"
                        //   881: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
                        //   883: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   886: pop            
                        //   887: aload_0        
                        //   888: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   891: iconst_1       
                        //   892: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //   895: pop            
                        //   896: goto            560
                        //   899: aload_0        
                        //   900: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   903: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //   906: invokevirtual   java/util/LinkedList.size:()I
                        //   909: iconst_1       
                        //   910: if_icmple       1035
                        //   913: lload           8
                        //   915: ldc2_w          -30
                        //   918: lcmp           
                        //   919: ifgt            1035
                        //   922: aload_0        
                        //   923: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   926: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //   929: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
                        //   932: pop            
                        //   933: aload_0        
                        //   934: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   937: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                        //   940: iload_2        
                        //   941: aconst_null    
                        //   942: aastore        
                        //   943: aload_0        
                        //   944: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   947: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
                        //   950: iload_2        
                        //   951: iconst_0       
                        //   952: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
                        //   955: aload_0        
                        //   956: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   959: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //   962: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
                        //   965: checkcast       Ljava/lang/Integer;
                        //   968: invokevirtual   java/lang/Integer.intValue:()I
                        //   971: istore_2       
                        //   972: aload_0        
                        //   973: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   976: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                        //   979: iload_2        
                        //   980: aaload         
                        //   981: astore_1       
                        //   982: aload_1        
                        //   983: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
                        //   986: ldc2_w          1000
                        //   989: ldiv           
                        //   990: lstore          4
                        //   992: lload           4
                        //   994: aload_0        
                        //   995: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   998: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                        //  1001: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
                        //  1004: lsub           
                        //  1005: lstore          8
                        //  1007: iload_3        
                        //  1008: iconst_1       
                        //  1009: iadd           
                        //  1010: istore_3       
                        //  1011: goto            899
                        //  1014: astore_1       
                        //  1015: ldc             "MediaDecoder2Video"
                        //  1017: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
                        //  1019: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //  1022: pop            
                        //  1023: aload_0        
                        //  1024: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1027: iconst_1       
                        //  1028: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //  1031: pop            
                        //  1032: goto            955
                        //  1035: aload_0        
                        //  1036: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1039: iload_3        
                        //  1040: i2l            
                        //  1041: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$614:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
                        //  1044: pop2           
                        //  1045: aload_0        
                        //  1046: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1049: iload_3        
                        //  1050: i2l            
                        //  1051: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$714:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
                        //  1054: pop2           
                        //  1055: aload_0        
                        //  1056: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1059: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //  1062: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
                        //  1065: pop            
                        //  1066: aload_0        
                        //  1067: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1070: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                        //  1073: iload_2        
                        //  1074: aconst_null    
                        //  1075: aastore        
                        //  1076: aload_0        
                        //  1077: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1080: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
                        //  1083: iload_2        
                        //  1084: iconst_1       
                        //  1085: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
                        //  1088: ldc2_w          10
                        //  1091: lstore          12
                        //  1093: lload           4
                        //  1095: lstore          10
                        //  1097: aload_1        
                        //  1098: astore          14
                        //  1100: lload           12
                        //  1102: lstore          6
                        //  1104: ldc             "MediaDecoder2Video"
                        //  1106: iconst_3       
                        //  1107: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                        //  1110: ifeq            755
                        //  1113: ldc             "MediaDecoder2Video"
                        //  1115: new             Ljava/lang/StringBuilder;
                        //  1118: dup            
                        //  1119: invokespecial   java/lang/StringBuilder.<init>:()V
                        //  1122: ldc             "STAT:REND frame "
                        //  1124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //  1127: aload_0        
                        //  1128: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1131: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //  1134: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //  1137: ldc             " skipped "
                        //  1139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //  1142: iload_3        
                        //  1143: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                        //  1146: ldc             " @"
                        //  1148: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //  1151: lload           4
                        //  1153: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //  1156: ldc             ", with delta "
                        //  1158: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //  1161: lload           8
                        //  1163: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //  1166: ldc             ", next after "
                        //  1168: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //  1171: ldc2_w          10
                        //  1174: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //  1177: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                        //  1180: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //  1183: pop            
                        //  1184: lload           4
                        //  1186: lstore          10
                        //  1188: aload_1        
                        //  1189: astore          14
                        //  1191: lload           12
                        //  1193: lstore          6
                        //  1195: goto            755
                        //  1198: astore          14
                        //  1200: ldc             "MediaDecoder2Video"
                        //  1202: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
                        //  1204: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //  1207: pop            
                        //  1208: aload_0        
                        //  1209: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1212: iconst_1       
                        //  1213: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //  1216: pop            
                        //  1217: goto            1088
                        //  1220: lload           12
                        //  1222: ldc2_w          5
                        //  1225: lsub           
                        //  1226: lstore          6
                        //  1228: lload           6
                        //  1230: lstore          4
                        //  1232: lload           6
                        //  1234: ldc2_w          50
                        //  1237: lcmp           
                        //  1238: ifle            260
                        //  1241: ldc2_w          50
                        //  1244: lstore          4
                        //  1246: goto            260
                        //  1249: ldc             "MediaDecoder2Video"
                        //  1251: ldc             "render state flushing"
                        //  1253: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //  1256: pop            
                        //  1257: aload_0        
                        //  1258: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1261: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //  1264: astore_1       
                        //  1265: aload_1        
                        //  1266: monitorenter   
                        //  1267: aload_0        
                        //  1268: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1271: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //  1274: invokevirtual   java/util/LinkedList.clear:()V
                        //  1277: aload_1        
                        //  1278: monitorexit    
                        //  1279: aload_0        
                        //  1280: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1283: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$1000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                        //  1286: astore_1       
                        //  1287: aload_1        
                        //  1288: monitorenter   
                        //  1289: aload_0        
                        //  1290: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1293: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$1000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                        //  1296: invokevirtual   java/lang/Object.notify:()V
                        //  1299: aload_1        
                        //  1300: monitorexit    
                        //  1301: return         
                        //  1302: astore          14
                        //  1304: aload_1        
                        //  1305: monitorexit    
                        //  1306: aload           14
                        //  1308: athrow         
                        //  1309: astore          14
                        //  1311: aload_1        
                        //  1312: monitorexit    
                        //  1313: aload           14
                        //  1315: athrow         
                        //  1316: ldc2_w          30
                        //  1319: lstore          4
                        //  1321: goto            523
                        //  1324: lload           6
                        //  1326: lstore          4
                        //  1328: lload           6
                        //  1330: lconst_0       
                        //  1331: lcmp           
                        //  1332: ifge            584
                        //  1335: ldc2_w          10
                        //  1338: lstore          4
                        //  1340: goto            584
                        //  1343: iconst_0       
                        //  1344: istore_3       
                        //  1345: lload           8
                        //  1347: lstore          4
                        //  1349: lload           12
                        //  1351: lstore          8
                        //  1353: goto            899
                        //    Exceptions:
                        //  Try           Handler
                        //  Start  End    Start  End    Type                 
                        //  -----  -----  -----  -----  ---------------------
                        //  100    113    866    872    Any
                        //  118    155    866    872    Any
                        //  159    199    866    872    Any
                        //  220    255    866    872    Any
                        //  260    263    866    872    Any
                        //  435    450    866    872    Any
                        //  474    498    866    872    Any
                        //  527    548    866    872    Any
                        //  548    560    877    899    Ljava/lang/Exception;
                        //  548    560    866    872    Any
                        //  584    611    866    872    Any
                        //  611    643    866    872    Any
                        //  674    744    866    872    Any
                        //  755    773    866    872    Any
                        //  777    795    866    872    Any
                        //  804    859    866    872    Any
                        //  867    870    866    872    Any
                        //  879    896    866    872    Any
                        //  899    913    866    872    Any
                        //  922    943    866    872    Any
                        //  943    955    1014   1035   Ljava/lang/Exception;
                        //  943    955    866    872    Any
                        //  955    1007   866    872    Any
                        //  1015   1032   866    872    Any
                        //  1035   1076   866    872    Any
                        //  1076   1088   1198   1220   Ljava/lang/Exception;
                        //  1076   1088   866    872    Any
                        //  1104   1184   866    872    Any
                        //  1200   1217   866    872    Any
                        //  1267   1279   1309   1316   Any
                        //  1289   1301   1302   1309   Any
                        //  1304   1306   1302   1309   Any
                        //  1311   1313   1309   1316   Any
                        // 
                        // The error that occurred was:
                        // 
                        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0560:
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
            catch (NoSuchMethodException ex) {
                Log.d("MediaDecoder2Video", "no timed buffer release, use regular method");
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
        if (this.isTimedReleaseSupported()) {
            this.mHandler.sendEmptyMessage(1);
        }
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
