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
        //    49: astore          14
        //    51: ldc2_w          20
        //    54: lstore          4
        //    56: aload_0        
        //    57: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //    60: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //    63: astore          15
        //    65: aload           15
        //    67: monitorenter   
        //    68: aload           14
        //    70: astore_1       
        //    71: iload_3        
        //    72: istore_2       
        //    73: aload_0        
        //    74: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //    77: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //    80: invokevirtual   java/util/LinkedList.isEmpty:()Z
        //    83: ifne            128
        //    86: aload           14
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
        //   130: if_icmpeq       1164
        //   133: aload_1        
        //   134: ifnull          1164
        //   137: aload_0        
        //   138: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   141: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   144: ifnull          1164
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
        //   177: aload           15
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
        //   369: lstore          12
        //   371: lload           12
        //   373: ldc2_w          -30
        //   376: lcmp           
        //   377: ifle            762
        //   380: iconst_1       
        //   381: istore_3       
        //   382: lload           12
        //   384: ldc2_w          20
        //   387: lcmp           
        //   388: ifge            1135
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
        //   413: lstore          8
        //   415: lload           8
        //   417: ldc2_w          30
        //   420: lcmp           
        //   421: iflt            1172
        //   424: lload           8
        //   426: lstore          4
        //   428: lload           8
        //   430: ldc2_w          50
        //   433: lcmp           
        //   434: ifle            440
        //   437: goto            1172
        //   440: iload_3        
        //   441: ifeq            1199
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
        //   479: lload           12
        //   481: ladd           
        //   482: lstore          8
        //   484: lload           8
        //   486: ldc2_w          5
        //   489: lcmp           
        //   490: ifle            1180
        //   493: lload           8
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
        //   545: lload           6
        //   547: lstore          8
        //   549: lload           4
        //   551: lstore          10
        //   553: aload_1        
        //   554: astore          14
        //   556: lload           12
        //   558: ldc2_w          -20
        //   561: lcmp           
        //   562: ifge            1208
        //   565: lload           6
        //   567: lstore          8
        //   569: lload           4
        //   571: lstore          10
        //   573: aload_1        
        //   574: astore          14
        //   576: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   579: ifeq            1208
        //   582: aload_0        
        //   583: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   586: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   589: new             Ljava/lang/StringBuilder;
        //   592: dup            
        //   593: invokespecial   java/lang/StringBuilder.<init>:()V
        //   596: ldc             "STAT:rendered frame "
        //   598: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   601: aload_0        
        //   602: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   605: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   608: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   611: ldc             " @"
        //   613: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   616: lload           6
        //   618: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   621: ldc             ", with delta "
        //   623: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   626: lload           12
        //   628: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   631: ldc             ", next after "
        //   633: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   636: lload           4
        //   638: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   641: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   644: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   647: pop            
        //   648: aload_0        
        //   649: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   652: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$508:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   655: pop2           
        //   656: aload_0        
        //   657: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   660: lload           6
        //   662: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$102:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;J)J
        //   665: pop2           
        //   666: aload_1        
        //   667: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   670: iconst_4       
        //   671: if_icmpne       177
        //   674: aload_0        
        //   675: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   678: iconst_1       
        //   679: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
        //   682: pop            
        //   683: ldc2_w          -1
        //   686: lstore          6
        //   688: lload           6
        //   690: lstore          4
        //   692: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   695: ifeq            177
        //   698: aload_0        
        //   699: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   702: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   705: new             Ljava/lang/StringBuilder;
        //   708: dup            
        //   709: invokespecial   java/lang/StringBuilder.<init>:()V
        //   712: ldc             "EOS: has flag, rendered frame "
        //   714: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   717: aload_0        
        //   718: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   721: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   724: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   727: ldc             ",skipped frame "
        //   729: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   732: aload_0        
        //   733: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   736: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   739: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   742: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   745: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   748: pop            
        //   749: lload           6
        //   751: lstore          4
        //   753: goto            177
        //   756: astore_1       
        //   757: aload           15
        //   759: monitorexit    
        //   760: aload_1        
        //   761: athrow         
        //   762: iconst_0       
        //   763: istore_3       
        //   764: goto            382
        //   767: astore          14
        //   769: aload_0        
        //   770: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   773: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   776: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
        //   778: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   781: pop            
        //   782: aload_0        
        //   783: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   786: iconst_1       
        //   787: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
        //   790: pop            
        //   791: goto            477
        //   794: aload_0        
        //   795: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   798: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //   801: invokevirtual   java/util/LinkedList.size:()I
        //   804: iconst_1       
        //   805: if_icmple       943
        //   808: lload           4
        //   810: ldc2_w          -30
        //   813: lcmp           
        //   814: ifgt            943
        //   817: aload_0        
        //   818: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   821: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //   824: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //   827: pop            
        //   828: aload_0        
        //   829: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   832: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   835: iload_2        
        //   836: aconst_null    
        //   837: aastore        
        //   838: aload_0        
        //   839: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   842: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //   845: iload_2        
        //   846: iconst_0       
        //   847: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   850: aload_0        
        //   851: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   854: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //   857: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
        //   860: checkcast       Ljava/lang/Integer;
        //   863: invokevirtual   java/lang/Integer.intValue:()I
        //   866: istore_2       
        //   867: aload_0        
        //   868: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   871: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   874: iload_2        
        //   875: aaload         
        //   876: astore_1       
        //   877: aload_1        
        //   878: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   881: ldc2_w          1000
        //   884: ldiv           
        //   885: lstore          4
        //   887: aload_0        
        //   888: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   891: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   894: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //   897: lstore          8
        //   899: iload_3        
        //   900: iconst_1       
        //   901: iadd           
        //   902: istore_3       
        //   903: lload           4
        //   905: lstore          6
        //   907: lload           4
        //   909: lload           8
        //   911: lsub           
        //   912: lstore          4
        //   914: goto            794
        //   917: astore_1       
        //   918: aload_0        
        //   919: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   922: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   925: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
        //   927: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   930: pop            
        //   931: aload_0        
        //   932: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   935: iconst_1       
        //   936: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
        //   939: pop            
        //   940: goto            850
        //   943: aload_0        
        //   944: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   947: iload_3        
        //   948: i2l            
        //   949: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$514:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;J)J
        //   952: pop2           
        //   953: aload_0        
        //   954: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   957: iload_3        
        //   958: i2l            
        //   959: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$614:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;J)J
        //   962: pop2           
        //   963: aload_0        
        //   964: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   967: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //   970: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //   973: pop            
        //   974: aload_0        
        //   975: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   978: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   981: iload_2        
        //   982: aconst_null    
        //   983: aastore        
        //   984: aload_0        
        //   985: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   988: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //   991: iload_2        
        //   992: iconst_1       
        //   993: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   996: ldc2_w          10
        //   999: lstore          12
        //  1001: lload           6
        //  1003: lstore          8
        //  1005: lload           12
        //  1007: lstore          10
        //  1009: aload_1        
        //  1010: astore          14
        //  1012: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //  1015: ifeq            1208
        //  1018: aload_0        
        //  1019: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //  1022: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //  1025: new             Ljava/lang/StringBuilder;
        //  1028: dup            
        //  1029: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1032: ldc             "STAT:REND frame "
        //  1034: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1037: aload_0        
        //  1038: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //  1041: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //  1044: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1047: ldc             " skipped "
        //  1049: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1052: iload_3        
        //  1053: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1056: ldc             " @"
        //  1058: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1061: lload           6
        //  1063: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1066: ldc             ", with delta "
        //  1068: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1071: lload           4
        //  1073: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1076: ldc             ", next after "
        //  1078: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1081: ldc2_w          10
        //  1084: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1087: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1090: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1093: pop            
        //  1094: lload           6
        //  1096: lstore          8
        //  1098: lload           12
        //  1100: lstore          10
        //  1102: aload_1        
        //  1103: astore          14
        //  1105: goto            1208
        //  1108: astore          14
        //  1110: aload_0        
        //  1111: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //  1114: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //  1117: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
        //  1119: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1122: pop            
        //  1123: aload_0        
        //  1124: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //  1127: iconst_1       
        //  1128: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
        //  1131: pop            
        //  1132: goto            996
        //  1135: lload           12
        //  1137: ldc2_w          5
        //  1140: lsub           
        //  1141: lstore          6
        //  1143: lload           6
        //  1145: lstore          4
        //  1147: lload           6
        //  1149: ldc2_w          50
        //  1152: lcmp           
        //  1153: ifle            177
        //  1156: ldc2_w          50
        //  1159: lstore          4
        //  1161: goto            177
        //  1164: ldc2_w          20
        //  1167: lstore          4
        //  1169: goto            177
        //  1172: ldc2_w          30
        //  1175: lstore          4
        //  1177: goto            440
        //  1180: lload           8
        //  1182: lstore          4
        //  1184: lload           8
        //  1186: lconst_0       
        //  1187: lcmp           
        //  1188: ifge            501
        //  1191: ldc2_w          10
        //  1194: lstore          4
        //  1196: goto            501
        //  1199: iconst_0       
        //  1200: istore_3       
        //  1201: lload           12
        //  1203: lstore          4
        //  1205: goto            794
        //  1208: lload           10
        //  1210: lstore          4
        //  1212: lload           8
        //  1214: lstore          6
        //  1216: aload           14
        //  1218: astore_1       
        //  1219: goto            648
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  73     86     756    762    Any
        //  91     128    756    762    Any
        //  137    172    756    762    Any
        //  177    180    756    762    Any
        //  356    371    756    762    Any
        //  391    415    756    762    Any
        //  444    465    756    762    Any
        //  465    477    767    794    Ljava/lang/Exception;
        //  465    477    756    762    Any
        //  501    545    756    762    Any
        //  576    648    756    762    Any
        //  648    666    756    762    Any
        //  666    683    756    762    Any
        //  692    749    756    762    Any
        //  757    760    756    762    Any
        //  769    791    756    762    Any
        //  794    808    756    762    Any
        //  817    838    756    762    Any
        //  838    850    917    943    Ljava/lang/Exception;
        //  838    850    756    762    Any
        //  850    899    756    762    Any
        //  918    940    756    762    Any
        //  943    984    756    762    Any
        //  984    996    1108   1135   Ljava/lang/Exception;
        //  984    996    756    762    Any
        //  1012   1094   756    762    Any
        //  1110   1132   756    762    Any
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
