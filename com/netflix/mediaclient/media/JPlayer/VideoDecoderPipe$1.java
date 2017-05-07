// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.os.Message;
import android.os.Looper;
import android.os.Handler;

class VideoDecoderPipe$1 extends Handler
{
    final /* synthetic */ VideoDecoderPipe this$0;
    
    VideoDecoderPipe$1(final VideoDecoderPipe this$0, final Looper looper) {
        this.this$0 = this$0;
        super(looper);
    }
    
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
        //    49: astore          12
        //    51: ldc2_w          20
        //    54: lstore          4
        //    56: aload_0        
        //    57: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //    60: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //    63: astore          13
        //    65: aload           13
        //    67: monitorenter   
        //    68: aload           12
        //    70: astore_1       
        //    71: iload_3        
        //    72: istore_2       
        //    73: aload_0        
        //    74: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //    77: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //    80: invokevirtual   java/util/LinkedList.isEmpty:()Z
        //    83: ifne            128
        //    86: aload           12
        //    88: astore_1       
        //    89: iload_3        
        //    90: istore_2       
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
        //   128: iload_2        
        //   129: iconst_m1      
        //   130: if_icmpeq       1118
        //   133: aload_1        
        //   134: ifnull          1118
        //   137: aload_0        
        //   138: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   141: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   144: ifnull          1118
        //   147: aload_1        
        //   148: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   151: ldc2_w          1000
        //   154: ldiv           
        //   155: lstore          6
        //   157: aload_0        
        //   158: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   161: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   164: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //   167: lconst_0       
        //   168: lcmp           
        //   169: ifge            356
        //   172: ldc2_w          30
        //   175: lstore          4
        //   177: aload           13
        //   179: monitorexit    
        //   180: aload_0        
        //   181: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   184: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$700:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Z
        //   187: ifeq            269
        //   190: aload_0        
        //   191: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   194: iconst_1       
        //   195: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
        //   198: pop            
        //   199: ldc2_w          -1
        //   202: lstore          6
        //   204: lload           6
        //   206: lstore          4
        //   208: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   211: ifeq            269
        //   214: aload_0        
        //   215: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   218: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   221: new             Ljava/lang/StringBuilder;
        //   224: dup            
        //   225: invokespecial   java/lang/StringBuilder.<init>:()V
        //   228: ldc             "EOS: stopped, rendered frame "
        //   230: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   233: aload_0        
        //   234: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   237: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   240: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   243: ldc             ",skipped frame "
        //   245: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   248: aload_0        
        //   249: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   252: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   255: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   258: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   261: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   264: pop            
        //   265: lload           6
        //   267: lstore          4
        //   269: aload_0        
        //   270: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   273: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Z
        //   276: ifne            45
        //   279: lload           4
        //   281: ldc2_w          60
        //   284: lcmp           
        //   285: ifgt            299
        //   288: lload           4
        //   290: lstore          6
        //   292: lload           4
        //   294: lconst_0       
        //   295: lcmp           
        //   296: ifgt            341
        //   299: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   302: ifeq            336
        //   305: aload_0        
        //   306: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   309: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   312: new             Ljava/lang/StringBuilder;
        //   315: dup            
        //   316: invokespecial   java/lang/StringBuilder.<init>:()V
        //   319: ldc             "unexpect loop time "
        //   321: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   324: lload           4
        //   326: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   329: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   332: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   335: pop            
        //   336: ldc2_w          30
        //   339: lstore          6
        //   341: aload_0        
        //   342: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   345: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$800:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Landroid/os/Handler;
        //   348: iconst_1       
        //   349: lload           6
        //   351: invokevirtual   android/os/Handler.sendEmptyMessageDelayed:(IJ)Z
        //   354: pop            
        //   355: return         
        //   356: lload           6
        //   358: aload_0        
        //   359: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   362: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   365: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //   368: lsub           
        //   369: lstore          8
        //   371: lload           8
        //   373: ldc2_w          -30
        //   376: lcmp           
        //   377: ifle            740
        //   380: iconst_1       
        //   381: istore_3       
        //   382: lload           8
        //   384: ldc2_w          20
        //   387: lcmp           
        //   388: ifge            1086
        //   391: aload_0        
        //   392: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   395: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$100:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   398: lconst_0       
        //   399: lcmp           
        //   400: ifle            440
        //   403: lload           6
        //   405: aload_0        
        //   406: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   409: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$100:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   412: lsub           
        //   413: lstore          10
        //   415: lload           10
        //   417: ldc2_w          30
        //   420: lcmp           
        //   421: iflt            1126
        //   424: lload           10
        //   426: lstore          4
        //   428: lload           10
        //   430: ldc2_w          50
        //   433: lcmp           
        //   434: ifle            440
        //   437: goto            1126
        //   440: iload_3        
        //   441: ifeq            1153
        //   444: aload_0        
        //   445: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   448: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //   451: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //   454: pop            
        //   455: aload_0        
        //   456: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   459: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   462: iload_2        
        //   463: aconst_null    
        //   464: aastore        
        //   465: aload_0        
        //   466: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   469: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //   472: iload_2        
        //   473: iconst_1       
        //   474: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   477: lload           4
        //   479: lload           8
        //   481: ladd           
        //   482: lstore          10
        //   484: lload           10
        //   486: ldc2_w          5
        //   489: lcmp           
        //   490: ifle            1134
        //   493: lload           10
        //   495: ldc2_w          5
        //   498: lsub           
        //   499: lstore          4
        //   501: aload_0        
        //   502: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   505: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$400:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Z
        //   508: ifne            545
        //   511: aload_0        
        //   512: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   515: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   518: ldc             "first buffer to render"
        //   520: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   523: pop            
        //   524: aload_0        
        //   525: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   528: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
        //   531: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener.onStartRender:()V
        //   536: aload_0        
        //   537: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   540: iconst_1       
        //   541: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$402:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
        //   544: pop            
        //   545: lload           8
        //   547: ldc2_w          -20
        //   550: lcmp           
        //   551: ifge            1115
        //   554: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   557: ifeq            1115
        //   560: aload_0        
        //   561: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   564: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   567: new             Ljava/lang/StringBuilder;
        //   570: dup            
        //   571: invokespecial   java/lang/StringBuilder.<init>:()V
        //   574: ldc             "STAT:rendered frame "
        //   576: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   579: aload_0        
        //   580: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   583: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   586: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   589: ldc             " @"
        //   591: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   594: lload           6
        //   596: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   599: ldc             ", with delta "
        //   601: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   604: lload           8
        //   606: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   609: ldc             ", next after "
        //   611: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   614: lload           4
        //   616: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   619: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   622: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   625: pop            
        //   626: aload_0        
        //   627: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   630: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$508:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   633: pop2           
        //   634: aload_0        
        //   635: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   638: lload           6
        //   640: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$102:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;J)J
        //   643: pop2           
        //   644: aload_1        
        //   645: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   648: iconst_4       
        //   649: if_icmpne       177
        //   652: aload_0        
        //   653: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   656: iconst_1       
        //   657: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
        //   660: pop            
        //   661: ldc2_w          -1
        //   664: lstore          6
        //   666: lload           6
        //   668: lstore          4
        //   670: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   673: ifeq            177
        //   676: aload_0        
        //   677: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   680: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   683: new             Ljava/lang/StringBuilder;
        //   686: dup            
        //   687: invokespecial   java/lang/StringBuilder.<init>:()V
        //   690: ldc             "EOS: has flag, rendered frame "
        //   692: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   695: aload_0        
        //   696: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   699: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   702: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   705: ldc             ",skipped frame "
        //   707: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   710: aload_0        
        //   711: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   714: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   717: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   720: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   723: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   726: pop            
        //   727: lload           6
        //   729: lstore          4
        //   731: goto            177
        //   734: astore_1       
        //   735: aload           13
        //   737: monitorexit    
        //   738: aload_1        
        //   739: athrow         
        //   740: iconst_0       
        //   741: istore_3       
        //   742: goto            382
        //   745: astore          12
        //   747: aload_0        
        //   748: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   751: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   754: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
        //   756: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   759: pop            
        //   760: aload_0        
        //   761: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   764: iconst_1       
        //   765: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
        //   768: pop            
        //   769: goto            477
        //   772: aload_0        
        //   773: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   776: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //   779: invokevirtual   java/util/LinkedList.size:()I
        //   782: iconst_1       
        //   783: if_icmple       921
        //   786: lload           6
        //   788: ldc2_w          -30
        //   791: lcmp           
        //   792: ifgt            921
        //   795: aload_0        
        //   796: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   799: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //   802: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //   805: pop            
        //   806: aload_0        
        //   807: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   810: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   813: iload_2        
        //   814: aconst_null    
        //   815: aastore        
        //   816: aload_0        
        //   817: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   820: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //   823: iload_2        
        //   824: iconst_0       
        //   825: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   828: aload_0        
        //   829: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   832: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //   835: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
        //   838: checkcast       Ljava/lang/Integer;
        //   841: invokevirtual   java/lang/Integer.intValue:()I
        //   844: istore_2       
        //   845: aload_0        
        //   846: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   849: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   852: iload_2        
        //   853: aaload         
        //   854: astore_1       
        //   855: aload_1        
        //   856: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   859: ldc2_w          1000
        //   862: ldiv           
        //   863: lstore          6
        //   865: aload_0        
        //   866: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   869: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   872: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //   875: lstore          8
        //   877: iload_3        
        //   878: iconst_1       
        //   879: iadd           
        //   880: istore_3       
        //   881: lload           6
        //   883: lstore          4
        //   885: lload           6
        //   887: lload           8
        //   889: lsub           
        //   890: lstore          6
        //   892: goto            772
        //   895: astore_1       
        //   896: aload_0        
        //   897: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   900: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   903: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
        //   905: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   908: pop            
        //   909: aload_0        
        //   910: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   913: iconst_1       
        //   914: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
        //   917: pop            
        //   918: goto            828
        //   921: aload_0        
        //   922: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   925: iload_3        
        //   926: i2l            
        //   927: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$514:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;J)J
        //   930: pop2           
        //   931: aload_0        
        //   932: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   935: iload_3        
        //   936: i2l            
        //   937: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$614:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;J)J
        //   940: pop2           
        //   941: aload_0        
        //   942: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   945: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //   948: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //   951: pop            
        //   952: aload_0        
        //   953: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   956: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   959: iload_2        
        //   960: aconst_null    
        //   961: aastore        
        //   962: aload_0        
        //   963: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   966: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //   969: iload_2        
        //   970: iconst_1       
        //   971: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   974: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   977: ifeq            1166
        //   980: aload_0        
        //   981: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   984: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   987: new             Ljava/lang/StringBuilder;
        //   990: dup            
        //   991: invokespecial   java/lang/StringBuilder.<init>:()V
        //   994: ldc             "STAT:REND frame "
        //   996: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   999: aload_0        
        //  1000: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //  1003: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //  1006: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1009: ldc             " skipped "
        //  1011: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1014: iload_3        
        //  1015: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1018: ldc             " @"
        //  1020: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1023: lload           4
        //  1025: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1028: ldc             ", with delta "
        //  1030: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1033: lload           6
        //  1035: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1038: ldc             ", next after "
        //  1040: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1043: ldc2_w          10
        //  1046: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1049: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1052: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1055: pop            
        //  1056: goto            1166
        //  1059: astore          12
        //  1061: aload_0        
        //  1062: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //  1065: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //  1068: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
        //  1070: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1073: pop            
        //  1074: aload_0        
        //  1075: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //  1078: iconst_1       
        //  1079: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
        //  1082: pop            
        //  1083: goto            974
        //  1086: lload           8
        //  1088: ldc2_w          5
        //  1091: lsub           
        //  1092: lstore          6
        //  1094: lload           6
        //  1096: lstore          4
        //  1098: lload           6
        //  1100: ldc2_w          50
        //  1103: lcmp           
        //  1104: ifle            177
        //  1107: ldc2_w          50
        //  1110: lstore          4
        //  1112: goto            177
        //  1115: goto            626
        //  1118: ldc2_w          20
        //  1121: lstore          4
        //  1123: goto            177
        //  1126: ldc2_w          30
        //  1129: lstore          4
        //  1131: goto            440
        //  1134: lload           10
        //  1136: lstore          4
        //  1138: lload           10
        //  1140: lconst_0       
        //  1141: lcmp           
        //  1142: ifge            501
        //  1145: ldc2_w          10
        //  1148: lstore          4
        //  1150: goto            501
        //  1153: iconst_0       
        //  1154: istore_3       
        //  1155: lload           6
        //  1157: lstore          4
        //  1159: lload           8
        //  1161: lstore          6
        //  1163: goto            772
        //  1166: ldc2_w          10
        //  1169: lstore          8
        //  1171: lload           4
        //  1173: lstore          6
        //  1175: lload           8
        //  1177: lstore          4
        //  1179: goto            626
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  73     86     734    740    Any
        //  91     128    734    740    Any
        //  137    172    734    740    Any
        //  177    180    734    740    Any
        //  356    371    734    740    Any
        //  391    415    734    740    Any
        //  444    465    734    740    Any
        //  465    477    745    772    Ljava/lang/Exception;
        //  465    477    734    740    Any
        //  501    545    734    740    Any
        //  554    626    734    740    Any
        //  626    644    734    740    Any
        //  644    661    734    740    Any
        //  670    727    734    740    Any
        //  735    738    734    740    Any
        //  747    769    734    740    Any
        //  772    786    734    740    Any
        //  795    816    734    740    Any
        //  816    828    895    921    Ljava/lang/Exception;
        //  816    828    734    740    Any
        //  828    877    734    740    Any
        //  896    918    734    740    Any
        //  921    962    734    740    Any
        //  962    974    1059   1086   Ljava/lang/Exception;
        //  962    974    734    740    Any
        //  974    1056   734    740    Any
        //  1061   1083   734    740    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0477:
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
}
