// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.os.Message;
import android.os.Looper;
import java.util.LinkedList;
import android.media.MediaCodec$BufferInfo;
import com.netflix.mediaclient.Log;
import java.util.concurrent.TimeUnit;
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
        Label_0256_Outer:
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
                        this.mDecoder.releaseOutputBuffer(intValue, System.nanoTime() + presentationTimeUs);
                        ++this.nFrameRendered;
                        this.removeFrameFromQ(intValue);
                        if (this.mEventListener != null) {
                            this.mEventListener.onSampleRendered(false, this.nFrameRendered, value);
                            continue Label_0256_Outer;
                        }
                        continue Label_0256_Outer;
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
            if (!this.mPaused && MediaDecoder2Video.USE_ANDROID_L_API) {
                this.mHearbeat.ShowHearbeat();
                this.tryToReleaseBuffers();
            }
        }
    }
    
    @Override
    void createRenderer() {
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
                //                3: 1256
                //          default: 36
                //        }
                //    36: ldc             "MediaDecoder2Video"
                //    38: ldc             "RenderThreadVideo had unknown message"
                //    40: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //    43: pop            
                //    44: return         
                //    45: getstatic       com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2.USE_ANDROID_L_API:Z
                //    48: ifeq            69
                //    51: aload_0        
                //    52: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //    55: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                //    58: ifne            44
                //    61: aload_0        
                //    62: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //    65: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)V
                //    68: return         
                //    69: iconst_m1      
                //    70: istore_3       
                //    71: aconst_null    
                //    72: astore          14
                //    74: ldc2_w          20
                //    77: lstore          6
                //    79: aload_0        
                //    80: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //    83: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                //    86: astore          15
                //    88: aload           15
                //    90: monitorenter   
                //    91: iload_3        
                //    92: istore_2       
                //    93: aload           14
                //    95: astore_1       
                //    96: aload_0        
                //    97: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   100: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                //   103: invokevirtual   java/util/LinkedList.isEmpty:()Z
                //   106: ifne            151
                //   109: iload_3        
                //   110: istore_2       
                //   111: aload           14
                //   113: astore_1       
                //   114: aload_0        
                //   115: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   118: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                //   121: ifne            151
                //   124: aload_0        
                //   125: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   128: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                //   131: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
                //   134: checkcast       Ljava/lang/Integer;
                //   137: invokevirtual   java/lang/Integer.intValue:()I
                //   140: istore_2       
                //   141: aload_0        
                //   142: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   145: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                //   148: iload_2        
                //   149: aaload         
                //   150: astore_1       
                //   151: aload_1        
                //   152: ifnull          195
                //   155: aload_1        
                //   156: getfield        android/media/MediaCodec$BufferInfo.flags:I
                //   159: iconst_4       
                //   160: iand           
                //   161: ifeq            195
                //   164: ldc             "MediaDecoder2Video"
                //   166: ldc             "renderer got buffer BUFFER_FLAG_END_OF_STREAM"
                //   168: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   171: pop            
                //   172: aload_0        
                //   173: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   176: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //   179: ifnull          195
                //   182: aload_0        
                //   183: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   186: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //   189: iconst_0       
                //   190: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onEndOfStream:(Z)V
                //   195: lload           6
                //   197: lstore          4
                //   199: iload_2        
                //   200: iconst_m1      
                //   201: if_icmpeq       256
                //   204: lload           6
                //   206: lstore          4
                //   208: aload_1        
                //   209: ifnull          256
                //   212: lload           6
                //   214: lstore          4
                //   216: aload_0        
                //   217: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   220: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                //   223: ifnull          256
                //   226: aload_1        
                //   227: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
                //   230: ldc2_w          1000
                //   233: ldiv           
                //   234: lstore          8
                //   236: aload_0        
                //   237: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   240: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                //   243: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
                //   246: lconst_0       
                //   247: lcmp           
                //   248: ifge            431
                //   251: ldc2_w          30
                //   254: lstore          4
                //   256: aload           15
                //   258: monitorexit    
                //   259: aload_0        
                //   260: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   263: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$700:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                //   266: ifeq            346
                //   269: aload_0        
                //   270: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   273: iconst_1       
                //   274: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                //   277: pop            
                //   278: ldc2_w          -1
                //   281: lstore          6
                //   283: lload           6
                //   285: lstore          4
                //   287: ldc             "MediaDecoder2Video"
                //   289: iconst_3       
                //   290: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   293: ifeq            346
                //   296: ldc             "MediaDecoder2Video"
                //   298: new             Ljava/lang/StringBuilder;
                //   301: dup            
                //   302: invokespecial   java/lang/StringBuilder.<init>:()V
                //   305: ldc             "EOS: stopped, rendered frame "
                //   307: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   310: aload_0        
                //   311: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   314: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                //   317: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   320: ldc             ",skipped frame "
                //   322: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   325: aload_0        
                //   326: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   329: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                //   332: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   335: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   338: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   341: pop            
                //   342: lload           6
                //   344: lstore          4
                //   346: aload_0        
                //   347: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   350: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                //   353: ifne            44
                //   356: lload           4
                //   358: ldc2_w          60
                //   361: lcmp           
                //   362: ifgt            376
                //   365: lload           4
                //   367: lstore          6
                //   369: lload           4
                //   371: lconst_0       
                //   372: lcmp           
                //   373: ifgt            416
                //   376: ldc             "MediaDecoder2Video"
                //   378: iconst_3       
                //   379: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   382: ifeq            411
                //   385: ldc             "MediaDecoder2Video"
                //   387: new             Ljava/lang/StringBuilder;
                //   390: dup            
                //   391: invokespecial   java/lang/StringBuilder.<init>:()V
                //   394: ldc             "unexpect loop time "
                //   396: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   399: lload           4
                //   401: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   404: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   407: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   410: pop            
                //   411: ldc2_w          30
                //   414: lstore          6
                //   416: aload_0        
                //   417: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   420: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$800:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Landroid/os/Handler;
                //   423: iconst_1       
                //   424: lload           6
                //   426: invokevirtual   android/os/Handler.sendEmptyMessageDelayed:(IJ)Z
                //   429: pop            
                //   430: return         
                //   431: lload           8
                //   433: aload_0        
                //   434: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   437: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                //   440: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
                //   443: lsub           
                //   444: lstore          12
                //   446: aload_0        
                //   447: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   450: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                //   453: ifeq            1332
                //   456: lload           12
                //   458: ldc2_w          -30
                //   461: lcmp           
                //   462: ifle            879
                //   465: goto            1332
                //   468: lload           12
                //   470: ldc2_w          20
                //   473: lcmp           
                //   474: ifge            1227
                //   477: lload           6
                //   479: lstore          4
                //   481: aload_0        
                //   482: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   485: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                //   488: lconst_0       
                //   489: lcmp           
                //   490: ifle            530
                //   493: lload           8
                //   495: aload_0        
                //   496: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   499: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                //   502: lsub           
                //   503: lstore          6
                //   505: lload           6
                //   507: ldc2_w          30
                //   510: lcmp           
                //   511: iflt            1337
                //   514: lload           6
                //   516: lstore          4
                //   518: lload           6
                //   520: ldc2_w          50
                //   523: lcmp           
                //   524: ifle            530
                //   527: goto            1337
                //   530: iload_3        
                //   531: ifeq            1364
                //   534: aload_0        
                //   535: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   538: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                //   541: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
                //   544: pop            
                //   545: aload_0        
                //   546: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   549: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                //   552: iload_2        
                //   553: aconst_null    
                //   554: aastore        
                //   555: aload_0        
                //   556: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   559: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
                //   562: iload_2        
                //   563: iconst_1       
                //   564: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
                //   567: lload           4
                //   569: lload           12
                //   571: ladd           
                //   572: lstore          6
                //   574: lload           6
                //   576: ldc2_w          5
                //   579: lcmp           
                //   580: ifle            1345
                //   583: lload           6
                //   585: ldc2_w          5
                //   588: lsub           
                //   589: lstore          4
                //   591: aload_0        
                //   592: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   595: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
                //   598: ifne            618
                //   601: ldc             "MediaDecoder2Video"
                //   603: ldc             "first buffer to render"
                //   605: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   608: pop            
                //   609: aload_0        
                //   610: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   613: iconst_1       
                //   614: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$202:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                //   617: pop            
                //   618: aload_0        
                //   619: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   622: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //   625: ifnull          650
                //   628: aload_0        
                //   629: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   632: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
                //   635: iconst_0       
                //   636: aload_0        
                //   637: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   640: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                //   643: lload           8
                //   645: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onSampleRendered:(ZJJ)V
                //   650: lload           8
                //   652: lstore          10
                //   654: aload_1        
                //   655: astore          14
                //   657: lload           4
                //   659: lstore          6
                //   661: lload           12
                //   663: ldc2_w          -20
                //   666: lcmp           
                //   667: ifge            762
                //   670: lload           8
                //   672: lstore          10
                //   674: aload_1        
                //   675: astore          14
                //   677: lload           4
                //   679: lstore          6
                //   681: ldc             "MediaDecoder2Video"
                //   683: iconst_3       
                //   684: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   687: ifeq            762
                //   690: ldc             "MediaDecoder2Video"
                //   692: new             Ljava/lang/StringBuilder;
                //   695: dup            
                //   696: invokespecial   java/lang/StringBuilder.<init>:()V
                //   699: ldc             "STAT:rendered frame "
                //   701: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   704: aload_0        
                //   705: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   708: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                //   711: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   714: ldc             " @"
                //   716: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   719: lload           8
                //   721: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   724: ldc             ", with delta "
                //   726: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   729: lload           12
                //   731: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   734: ldc             ", next after "
                //   736: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   739: lload           4
                //   741: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   744: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   747: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   750: pop            
                //   751: lload           4
                //   753: lstore          6
                //   755: aload_1        
                //   756: astore          14
                //   758: lload           8
                //   760: lstore          10
                //   762: aload_0        
                //   763: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   766: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$508:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                //   769: pop2           
                //   770: aload_0        
                //   771: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   774: lload           10
                //   776: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$302:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
                //   779: pop2           
                //   780: lload           6
                //   782: lstore          4
                //   784: aload           14
                //   786: getfield        android/media/MediaCodec$BufferInfo.flags:I
                //   789: iconst_4       
                //   790: if_icmpne       256
                //   793: aload_0        
                //   794: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   797: iconst_1       
                //   798: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                //   801: pop            
                //   802: ldc2_w          -1
                //   805: lstore          6
                //   807: lload           6
                //   809: lstore          4
                //   811: ldc             "MediaDecoder2Video"
                //   813: iconst_3       
                //   814: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //   817: ifeq            256
                //   820: ldc             "MediaDecoder2Video"
                //   822: new             Ljava/lang/StringBuilder;
                //   825: dup            
                //   826: invokespecial   java/lang/StringBuilder.<init>:()V
                //   829: ldc             "EOS: has flag, rendered frame "
                //   831: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   834: aload_0        
                //   835: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   838: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                //   841: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   844: ldc             ",skipped frame "
                //   846: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //   849: aload_0        
                //   850: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   853: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                //   856: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //   859: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //   862: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   865: pop            
                //   866: lload           6
                //   868: lstore          4
                //   870: goto            256
                //   873: astore_1       
                //   874: aload           15
                //   876: monitorexit    
                //   877: aload_1        
                //   878: athrow         
                //   879: iconst_0       
                //   880: istore_3       
                //   881: goto            468
                //   884: astore          14
                //   886: ldc             "MediaDecoder2Video"
                //   888: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
                //   890: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //   893: pop            
                //   894: aload_0        
                //   895: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   898: iconst_1       
                //   899: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                //   902: pop            
                //   903: goto            567
                //   906: aload_0        
                //   907: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   910: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                //   913: invokevirtual   java/util/LinkedList.size:()I
                //   916: iconst_1       
                //   917: if_icmple       1042
                //   920: lload           8
                //   922: ldc2_w          -30
                //   925: lcmp           
                //   926: ifgt            1042
                //   929: aload_0        
                //   930: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   933: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                //   936: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
                //   939: pop            
                //   940: aload_0        
                //   941: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   944: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                //   947: iload_2        
                //   948: aconst_null    
                //   949: aastore        
                //   950: aload_0        
                //   951: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   954: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
                //   957: iload_2        
                //   958: iconst_0       
                //   959: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
                //   962: aload_0        
                //   963: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   966: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                //   969: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
                //   972: checkcast       Ljava/lang/Integer;
                //   975: invokevirtual   java/lang/Integer.intValue:()I
                //   978: istore_2       
                //   979: aload_0        
                //   980: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //   983: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                //   986: iload_2        
                //   987: aaload         
                //   988: astore_1       
                //   989: aload_1        
                //   990: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
                //   993: ldc2_w          1000
                //   996: ldiv           
                //   997: lstore          4
                //   999: lload           4
                //  1001: aload_0        
                //  1002: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //  1005: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
                //  1008: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
                //  1011: lsub           
                //  1012: lstore          8
                //  1014: iload_3        
                //  1015: iconst_1       
                //  1016: iadd           
                //  1017: istore_3       
                //  1018: goto            906
                //  1021: astore_1       
                //  1022: ldc             "MediaDecoder2Video"
                //  1024: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
                //  1026: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //  1029: pop            
                //  1030: aload_0        
                //  1031: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //  1034: iconst_1       
                //  1035: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                //  1038: pop            
                //  1039: goto            962
                //  1042: aload_0        
                //  1043: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //  1046: iload_3        
                //  1047: i2l            
                //  1048: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$514:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
                //  1051: pop2           
                //  1052: aload_0        
                //  1053: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //  1056: iload_3        
                //  1057: i2l            
                //  1058: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$614:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
                //  1061: pop2           
                //  1062: aload_0        
                //  1063: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //  1066: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                //  1069: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
                //  1072: pop            
                //  1073: aload_0        
                //  1074: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //  1077: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
                //  1080: iload_2        
                //  1081: aconst_null    
                //  1082: aastore        
                //  1083: aload_0        
                //  1084: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //  1087: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
                //  1090: iload_2        
                //  1091: iconst_1       
                //  1092: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
                //  1095: ldc2_w          10
                //  1098: lstore          12
                //  1100: lload           4
                //  1102: lstore          10
                //  1104: aload_1        
                //  1105: astore          14
                //  1107: lload           12
                //  1109: lstore          6
                //  1111: ldc             "MediaDecoder2Video"
                //  1113: iconst_3       
                //  1114: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
                //  1117: ifeq            762
                //  1120: ldc             "MediaDecoder2Video"
                //  1122: new             Ljava/lang/StringBuilder;
                //  1125: dup            
                //  1126: invokespecial   java/lang/StringBuilder.<init>:()V
                //  1129: ldc             "STAT:REND frame "
                //  1131: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1134: aload_0        
                //  1135: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //  1138: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
                //  1141: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //  1144: ldc             " skipped "
                //  1146: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1149: iload_3        
                //  1150: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
                //  1153: ldc             " @"
                //  1155: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1158: lload           4
                //  1160: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //  1163: ldc             ", with delta "
                //  1165: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1168: lload           8
                //  1170: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //  1173: ldc             ", next after "
                //  1175: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
                //  1178: ldc2_w          10
                //  1181: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
                //  1184: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
                //  1187: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //  1190: pop            
                //  1191: lload           4
                //  1193: lstore          10
                //  1195: aload_1        
                //  1196: astore          14
                //  1198: lload           12
                //  1200: lstore          6
                //  1202: goto            762
                //  1205: astore          14
                //  1207: ldc             "MediaDecoder2Video"
                //  1209: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
                //  1211: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //  1214: pop            
                //  1215: aload_0        
                //  1216: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //  1219: iconst_1       
                //  1220: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                //  1223: pop            
                //  1224: goto            1095
                //  1227: lload           12
                //  1229: ldc2_w          5
                //  1232: lsub           
                //  1233: lstore          6
                //  1235: lload           6
                //  1237: lstore          4
                //  1239: lload           6
                //  1241: ldc2_w          50
                //  1244: lcmp           
                //  1245: ifle            256
                //  1248: ldc2_w          50
                //  1251: lstore          4
                //  1253: goto            256
                //  1256: ldc             "MediaDecoder2Video"
                //  1258: ldc             "render state flushing"
                //  1260: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
                //  1263: pop            
                //  1264: aload_0        
                //  1265: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //  1268: iconst_0       
                //  1269: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$202:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
                //  1272: pop            
                //  1273: aload_0        
                //  1274: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //  1277: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                //  1280: astore_1       
                //  1281: aload_1        
                //  1282: monitorenter   
                //  1283: aload_0        
                //  1284: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //  1287: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
                //  1290: invokevirtual   java/util/LinkedList.clear:()V
                //  1293: aload_1        
                //  1294: monitorexit    
                //  1295: aload_0        
                //  1296: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //  1299: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$900:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //  1302: astore_1       
                //  1303: aload_1        
                //  1304: monitorenter   
                //  1305: aload_0        
                //  1306: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
                //  1309: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$900:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
                //  1312: invokevirtual   java/lang/Object.notify:()V
                //  1315: aload_1        
                //  1316: monitorexit    
                //  1317: return         
                //  1318: astore          14
                //  1320: aload_1        
                //  1321: monitorexit    
                //  1322: aload           14
                //  1324: athrow         
                //  1325: astore          14
                //  1327: aload_1        
                //  1328: monitorexit    
                //  1329: aload           14
                //  1331: athrow         
                //  1332: iconst_1       
                //  1333: istore_3       
                //  1334: goto            468
                //  1337: ldc2_w          30
                //  1340: lstore          4
                //  1342: goto            530
                //  1345: lload           6
                //  1347: lstore          4
                //  1349: lload           6
                //  1351: lconst_0       
                //  1352: lcmp           
                //  1353: ifge            591
                //  1356: ldc2_w          10
                //  1359: lstore          4
                //  1361: goto            591
                //  1364: iconst_0       
                //  1365: istore_3       
                //  1366: lload           8
                //  1368: lstore          4
                //  1370: lload           12
                //  1372: lstore          8
                //  1374: goto            906
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  96     109    873    879    Any
                //  114    151    873    879    Any
                //  155    195    873    879    Any
                //  216    251    873    879    Any
                //  256    259    873    879    Any
                //  431    456    873    879    Any
                //  481    505    873    879    Any
                //  534    555    873    879    Any
                //  555    567    884    906    Ljava/lang/Exception;
                //  555    567    873    879    Any
                //  591    618    873    879    Any
                //  618    650    873    879    Any
                //  681    751    873    879    Any
                //  762    780    873    879    Any
                //  784    802    873    879    Any
                //  811    866    873    879    Any
                //  874    877    873    879    Any
                //  886    903    873    879    Any
                //  906    920    873    879    Any
                //  929    950    873    879    Any
                //  950    962    1021   1042   Ljava/lang/Exception;
                //  950    962    873    879    Any
                //  962    1014   873    879    Any
                //  1022   1039   873    879    Any
                //  1042   1083   873    879    Any
                //  1083   1095   1205   1227   Ljava/lang/Exception;
                //  1083   1095   873    879    Any
                //  1111   1191   873    879    Any
                //  1207   1224   873    879    Any
                //  1283   1295   1325   1332   Any
                //  1305   1317   1318   1325   Any
                //  1320   1322   1318   1325   Any
                //  1327   1329   1325   1332   Any
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0567:
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
        if (this.mHandler != null && MediaDecoder2Video.USE_ANDROID_L_API) {
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
