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
    
    public MediaDecoder2Video(final InputDataSource inputDataSource, final String s, final MediaFormat mediaFormat, final Surface surface, final MediaCrypto mediaCrypto, final EventListener eventListener) throws Exception {
        super(inputDataSource, s, mediaFormat, surface, mediaCrypto, eventListener);
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
    Label_0183_Outer:
        while (true) {
        Label_0277_Outer:
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
                        break Label_0183;
                    }
                    presentationTimeUs = mediaCodec$BufferInfo.presentationTimeUs;
                    value = this.mRefClock.get();
                    if (value < 0L) {
                        if (this.mHandler != null) {
                            this.mHandler.sendEmptyMessageDelayed(1, 30L);
                        }
                        return;
                    }
                }
                presentationTimeUs = TimeUnit.MICROSECONDS.toNanos(presentationTimeUs) - TimeUnit.MILLISECONDS.toNanos(value);
                millis = TimeUnit.NANOSECONDS.toMillis(presentationTimeUs);
                Label_0207: {
                    if (millis >= 0L) {
                        break Label_0207;
                    }
                    while (true) {
                        try {
                            this.mDecoder.releaseOutputBuffer(intValue, false);
                            ++this.nFrameRendered;
                            ++this.nFrameSkipped;
                            this.removeFrameFromQ(intValue);
                            // monitorexit(list)
                            continue Label_0183_Outer;
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
                            continue Label_0277_Outer;
                        }
                        continue Label_0277_Outer;
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
        if (this.mHandler != null) {
            this.mHandler.sendEmptyMessageDelayed(1, millis - 500L);
        }
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
                        //                3: 1260
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
                        //   278: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$502:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
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
                        //   354: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
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
                        //   450: aload_0        
                        //   451: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   454: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                        //   457: ifeq            1336
                        //   460: lload           12
                        //   462: ldc2_w          -30
                        //   465: lcmp           
                        //   466: ifle            883
                        //   469: goto            1336
                        //   472: lload           12
                        //   474: ldc2_w          20
                        //   477: lcmp           
                        //   478: ifge            1231
                        //   481: lload           6
                        //   483: lstore          4
                        //   485: aload_0        
                        //   486: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   489: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   492: lconst_0       
                        //   493: lcmp           
                        //   494: ifle            534
                        //   497: lload           8
                        //   499: aload_0        
                        //   500: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   503: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   506: lsub           
                        //   507: lstore          6
                        //   509: lload           6
                        //   511: ldc2_w          30
                        //   514: lcmp           
                        //   515: iflt            1341
                        //   518: lload           6
                        //   520: lstore          4
                        //   522: lload           6
                        //   524: ldc2_w          50
                        //   527: lcmp           
                        //   528: ifle            534
                        //   531: goto            1341
                        //   534: iload_3        
                        //   535: ifeq            1368
                        //   538: aload_0        
                        //   539: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   542: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //   545: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
                        //   548: pop            
                        //   549: aload_0        
                        //   550: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   553: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                        //   556: iload_2        
                        //   557: aconst_null    
                        //   558: aastore        
                        //   559: aload_0        
                        //   560: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   563: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
                        //   566: iload_2        
                        //   567: iconst_1       
                        //   568: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
                        //   571: lload           4
                        //   573: lload           12
                        //   575: ladd           
                        //   576: lstore          6
                        //   578: lload           6
                        //   580: ldc2_w          5
                        //   583: lcmp           
                        //   584: ifle            1349
                        //   587: lload           6
                        //   589: ldc2_w          5
                        //   592: lsub           
                        //   593: lstore          4
                        //   595: aload_0        
                        //   596: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   599: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                        //   602: ifne            622
                        //   605: ldc             "MediaDecoder2Video"
                        //   607: ldc             "first buffer to render"
                        //   609: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   612: pop            
                        //   613: aload_0        
                        //   614: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   617: iconst_1       
                        //   618: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$302:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //   621: pop            
                        //   622: aload_0        
                        //   623: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   626: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                        //   629: ifnull          654
                        //   632: aload_0        
                        //   633: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   636: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                        //   639: iconst_0       
                        //   640: aload_0        
                        //   641: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   644: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   647: lload           8
                        //   649: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onSampleRendered:(ZJJ)V
                        //   654: lload           8
                        //   656: lstore          10
                        //   658: aload_1        
                        //   659: astore          14
                        //   661: lload           4
                        //   663: lstore          6
                        //   665: lload           12
                        //   667: ldc2_w          -20
                        //   670: lcmp           
                        //   671: ifge            766
                        //   674: lload           8
                        //   676: lstore          10
                        //   678: aload_1        
                        //   679: astore          14
                        //   681: lload           4
                        //   683: lstore          6
                        //   685: ldc             "MediaDecoder2Video"
                        //   687: iconst_3       
                        //   688: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                        //   691: ifeq            766
                        //   694: ldc             "MediaDecoder2Video"
                        //   696: new             Ljava/lang/StringBuilder;
                        //   699: dup            
                        //   700: invokespecial   java/lang/StringBuilder.<init>:()V
                        //   703: ldc             "STAT:rendered frame "
                        //   705: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   708: aload_0        
                        //   709: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   712: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   715: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   718: ldc             " @"
                        //   720: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   723: lload           8
                        //   725: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   728: ldc             ", with delta "
                        //   730: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   733: lload           12
                        //   735: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   738: ldc             ", next after "
                        //   740: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   743: lload           4
                        //   745: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   748: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                        //   751: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   754: pop            
                        //   755: lload           4
                        //   757: lstore          6
                        //   759: aload_1        
                        //   760: astore          14
                        //   762: lload           8
                        //   764: lstore          10
                        //   766: aload_0        
                        //   767: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   770: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$608:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   773: pop2           
                        //   774: aload_0        
                        //   775: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   778: lload           10
                        //   780: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
                        //   783: pop2           
                        //   784: lload           6
                        //   786: lstore          4
                        //   788: aload           14
                        //   790: getfield        android/media/MediaCodec$BufferInfo.flags:I
                        //   793: iconst_4       
                        //   794: if_icmpne       260
                        //   797: aload_0        
                        //   798: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   801: iconst_1       
                        //   802: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$502:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //   805: pop            
                        //   806: ldc2_w          -1
                        //   809: lstore          6
                        //   811: lload           6
                        //   813: lstore          4
                        //   815: ldc             "MediaDecoder2Video"
                        //   817: iconst_3       
                        //   818: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                        //   821: ifeq            260
                        //   824: ldc             "MediaDecoder2Video"
                        //   826: new             Ljava/lang/StringBuilder;
                        //   829: dup            
                        //   830: invokespecial   java/lang/StringBuilder.<init>:()V
                        //   833: ldc             "EOS: has flag, rendered frame "
                        //   835: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   838: aload_0        
                        //   839: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   842: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   845: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   848: ldc             ",skipped frame "
                        //   850: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //   853: aload_0        
                        //   854: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   857: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$700:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //   860: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //   863: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                        //   866: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   869: pop            
                        //   870: lload           6
                        //   872: lstore          4
                        //   874: goto            260
                        //   877: astore_1       
                        //   878: aload           15
                        //   880: monitorexit    
                        //   881: aload_1        
                        //   882: athrow         
                        //   883: iconst_0       
                        //   884: istore_3       
                        //   885: goto            472
                        //   888: astore          14
                        //   890: ldc             "MediaDecoder2Video"
                        //   892: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
                        //   894: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //   897: pop            
                        //   898: aload_0        
                        //   899: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   902: iconst_1       
                        //   903: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$502:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //   906: pop            
                        //   907: goto            571
                        //   910: aload_0        
                        //   911: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   914: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //   917: invokevirtual   java/util/LinkedList.size:()I
                        //   920: iconst_1       
                        //   921: if_icmple       1046
                        //   924: lload           8
                        //   926: ldc2_w          -30
                        //   929: lcmp           
                        //   930: ifgt            1046
                        //   933: aload_0        
                        //   934: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   937: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //   940: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
                        //   943: pop            
                        //   944: aload_0        
                        //   945: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   948: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                        //   951: iload_2        
                        //   952: aconst_null    
                        //   953: aastore        
                        //   954: aload_0        
                        //   955: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   958: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
                        //   961: iload_2        
                        //   962: iconst_0       
                        //   963: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
                        //   966: aload_0        
                        //   967: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   970: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //   973: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
                        //   976: checkcast       Ljava/lang/Integer;
                        //   979: invokevirtual   java/lang/Integer.intValue:()I
                        //   982: istore_2       
                        //   983: aload_0        
                        //   984: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //   987: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                        //   990: iload_2        
                        //   991: aaload         
                        //   992: astore_1       
                        //   993: aload_1        
                        //   994: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
                        //   997: ldc2_w          1000
                        //  1000: ldiv           
                        //  1001: lstore          4
                        //  1003: lload           4
                        //  1005: aload_0        
                        //  1006: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1009: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                        //  1012: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
                        //  1015: lsub           
                        //  1016: lstore          8
                        //  1018: iload_3        
                        //  1019: iconst_1       
                        //  1020: iadd           
                        //  1021: istore_3       
                        //  1022: goto            910
                        //  1025: astore_1       
                        //  1026: ldc             "MediaDecoder2Video"
                        //  1028: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
                        //  1030: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //  1033: pop            
                        //  1034: aload_0        
                        //  1035: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1038: iconst_1       
                        //  1039: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$502:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //  1042: pop            
                        //  1043: goto            966
                        //  1046: aload_0        
                        //  1047: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1050: iload_3        
                        //  1051: i2l            
                        //  1052: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$614:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
                        //  1055: pop2           
                        //  1056: aload_0        
                        //  1057: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1060: iload_3        
                        //  1061: i2l            
                        //  1062: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$714:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
                        //  1065: pop2           
                        //  1066: aload_0        
                        //  1067: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1070: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //  1073: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
                        //  1076: pop            
                        //  1077: aload_0        
                        //  1078: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1081: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                        //  1084: iload_2        
                        //  1085: aconst_null    
                        //  1086: aastore        
                        //  1087: aload_0        
                        //  1088: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1091: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
                        //  1094: iload_2        
                        //  1095: iconst_1       
                        //  1096: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
                        //  1099: ldc2_w          10
                        //  1102: lstore          12
                        //  1104: lload           4
                        //  1106: lstore          10
                        //  1108: aload_1        
                        //  1109: astore          14
                        //  1111: lload           12
                        //  1113: lstore          6
                        //  1115: ldc             "MediaDecoder2Video"
                        //  1117: iconst_3       
                        //  1118: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                        //  1121: ifeq            766
                        //  1124: ldc             "MediaDecoder2Video"
                        //  1126: new             Ljava/lang/StringBuilder;
                        //  1129: dup            
                        //  1130: invokespecial   java/lang/StringBuilder.<init>:()V
                        //  1133: ldc             "STAT:REND frame "
                        //  1135: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //  1138: aload_0        
                        //  1139: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1142: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                        //  1145: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //  1148: ldc             " skipped "
                        //  1150: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //  1153: iload_3        
                        //  1154: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                        //  1157: ldc             " @"
                        //  1159: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //  1162: lload           4
                        //  1164: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //  1167: ldc             ", with delta "
                        //  1169: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //  1172: lload           8
                        //  1174: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //  1177: ldc             ", next after "
                        //  1179: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                        //  1182: ldc2_w          10
                        //  1185: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                        //  1188: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                        //  1191: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //  1194: pop            
                        //  1195: lload           4
                        //  1197: lstore          10
                        //  1199: aload_1        
                        //  1200: astore          14
                        //  1202: lload           12
                        //  1204: lstore          6
                        //  1206: goto            766
                        //  1209: astore          14
                        //  1211: ldc             "MediaDecoder2Video"
                        //  1213: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
                        //  1215: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //  1218: pop            
                        //  1219: aload_0        
                        //  1220: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1223: iconst_1       
                        //  1224: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$502:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //  1227: pop            
                        //  1228: goto            1099
                        //  1231: lload           12
                        //  1233: ldc2_w          5
                        //  1236: lsub           
                        //  1237: lstore          6
                        //  1239: lload           6
                        //  1241: lstore          4
                        //  1243: lload           6
                        //  1245: ldc2_w          50
                        //  1248: lcmp           
                        //  1249: ifle            260
                        //  1252: ldc2_w          50
                        //  1255: lstore          4
                        //  1257: goto            260
                        //  1260: ldc             "MediaDecoder2Video"
                        //  1262: ldc             "render state flushing"
                        //  1264: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                        //  1267: pop            
                        //  1268: aload_0        
                        //  1269: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1272: iconst_0       
                        //  1273: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$302:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                        //  1276: pop            
                        //  1277: aload_0        
                        //  1278: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1281: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //  1284: astore_1       
                        //  1285: aload_1        
                        //  1286: monitorenter   
                        //  1287: aload_0        
                        //  1288: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1291: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                        //  1294: invokevirtual   java/util/LinkedList.clear:()V
                        //  1297: aload_1        
                        //  1298: monitorexit    
                        //  1299: aload_0        
                        //  1300: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1303: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$1000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                        //  1306: astore_1       
                        //  1307: aload_1        
                        //  1308: monitorenter   
                        //  1309: aload_0        
                        //  1310: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                        //  1313: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$1000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                        //  1316: invokevirtual   java/lang/Object.notify:()V
                        //  1319: aload_1        
                        //  1320: monitorexit    
                        //  1321: return         
                        //  1322: astore          14
                        //  1324: aload_1        
                        //  1325: monitorexit    
                        //  1326: aload           14
                        //  1328: athrow         
                        //  1329: astore          14
                        //  1331: aload_1        
                        //  1332: monitorexit    
                        //  1333: aload           14
                        //  1335: athrow         
                        //  1336: iconst_1       
                        //  1337: istore_3       
                        //  1338: goto            472
                        //  1341: ldc2_w          30
                        //  1344: lstore          4
                        //  1346: goto            534
                        //  1349: lload           6
                        //  1351: lstore          4
                        //  1353: lload           6
                        //  1355: lconst_0       
                        //  1356: lcmp           
                        //  1357: ifge            595
                        //  1360: ldc2_w          10
                        //  1363: lstore          4
                        //  1365: goto            595
                        //  1368: iconst_0       
                        //  1369: istore_3       
                        //  1370: lload           8
                        //  1372: lstore          4
                        //  1374: lload           12
                        //  1376: lstore          8
                        //  1378: goto            910
                        //    Exceptions:
                        //  Try           Handler
                        //  Start  End    Start  End    Type                 
                        //  -----  -----  -----  -----  ---------------------
                        //  100    113    877    883    Any
                        //  118    155    877    883    Any
                        //  159    199    877    883    Any
                        //  220    255    877    883    Any
                        //  260    263    877    883    Any
                        //  435    460    877    883    Any
                        //  485    509    877    883    Any
                        //  538    559    877    883    Any
                        //  559    571    888    910    Ljava/lang/Exception;
                        //  559    571    877    883    Any
                        //  595    622    877    883    Any
                        //  622    654    877    883    Any
                        //  685    755    877    883    Any
                        //  766    784    877    883    Any
                        //  788    806    877    883    Any
                        //  815    870    877    883    Any
                        //  878    881    877    883    Any
                        //  890    907    877    883    Any
                        //  910    924    877    883    Any
                        //  933    954    877    883    Any
                        //  954    966    1025   1046   Ljava/lang/Exception;
                        //  954    966    877    883    Any
                        //  966    1018   877    883    Any
                        //  1026   1043   877    883    Any
                        //  1046   1087   877    883    Any
                        //  1087   1099   1209   1231   Ljava/lang/Exception;
                        //  1087   1099   877    883    Any
                        //  1115   1195   877    883    Any
                        //  1211   1228   877    883    Any
                        //  1287   1299   1329   1336   Any
                        //  1309   1321   1322   1329   Any
                        //  1324   1326   1322   1329   Any
                        //  1331   1333   1329   1336   Any
                        // 
                        // The error that occurred was:
                        // 
                        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0571:
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
            this.mFirstFrameRendered = false;
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
