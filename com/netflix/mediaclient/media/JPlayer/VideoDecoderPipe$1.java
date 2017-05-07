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
        //   130: if_icmpeq       1158
        //   133: aload_1        
        //   134: ifnull          1158
        //   137: aload_0        
        //   138: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   141: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   144: ifnull          1158
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
        //   169: ifge            372
        //   172: ldc2_w          30
        //   175: lstore          4
        //   177: aload           13
        //   179: monitorexit    
        //   180: aload_0        
        //   181: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   184: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$700:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Z
        //   187: ifeq            277
        //   190: aload_0        
        //   191: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   194: iconst_1       
        //   195: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
        //   198: pop            
        //   199: ldc2_w          -1
        //   202: lstore          6
        //   204: lload           6
        //   206: lstore          4
        //   208: aload_0        
        //   209: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   212: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   215: iconst_3       
        //   216: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   219: ifeq            277
        //   222: aload_0        
        //   223: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   226: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   229: new             Ljava/lang/StringBuilder;
        //   232: dup            
        //   233: invokespecial   java/lang/StringBuilder.<init>:()V
        //   236: ldc             "EOS: stopped, rendered frame "
        //   238: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   241: aload_0        
        //   242: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   245: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   248: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   251: ldc             ",skipped frame "
        //   253: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   256: aload_0        
        //   257: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   260: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   263: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   266: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   269: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   272: pop            
        //   273: lload           6
        //   275: lstore          4
        //   277: aload_0        
        //   278: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   281: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Z
        //   284: ifne            45
        //   287: lload           4
        //   289: ldc2_w          60
        //   292: lcmp           
        //   293: ifgt            307
        //   296: lload           4
        //   298: lstore          6
        //   300: lload           4
        //   302: lconst_0       
        //   303: lcmp           
        //   304: ifgt            357
        //   307: aload_0        
        //   308: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   311: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   314: iconst_3       
        //   315: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   318: ifeq            352
        //   321: aload_0        
        //   322: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   325: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   328: new             Ljava/lang/StringBuilder;
        //   331: dup            
        //   332: invokespecial   java/lang/StringBuilder.<init>:()V
        //   335: ldc             "unexpect loop time "
        //   337: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   340: lload           4
        //   342: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   345: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   348: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   351: pop            
        //   352: ldc2_w          30
        //   355: lstore          6
        //   357: aload_0        
        //   358: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   361: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$800:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Landroid/os/Handler;
        //   364: iconst_1       
        //   365: lload           6
        //   367: invokevirtual   android/os/Handler.sendEmptyMessageDelayed:(IJ)Z
        //   370: pop            
        //   371: return         
        //   372: lload           6
        //   374: aload_0        
        //   375: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   378: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   381: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //   384: lsub           
        //   385: lstore          8
        //   387: lload           8
        //   389: ldc2_w          -30
        //   392: lcmp           
        //   393: ifle            772
        //   396: iconst_1       
        //   397: istore_3       
        //   398: lload           8
        //   400: ldc2_w          20
        //   403: lcmp           
        //   404: ifge            1126
        //   407: aload_0        
        //   408: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   411: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$100:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   414: lconst_0       
        //   415: lcmp           
        //   416: ifle            456
        //   419: lload           6
        //   421: aload_0        
        //   422: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   425: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$100:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   428: lsub           
        //   429: lstore          10
        //   431: lload           10
        //   433: ldc2_w          30
        //   436: lcmp           
        //   437: iflt            1166
        //   440: lload           10
        //   442: lstore          4
        //   444: lload           10
        //   446: ldc2_w          50
        //   449: lcmp           
        //   450: ifle            456
        //   453: goto            1166
        //   456: iload_3        
        //   457: ifeq            1193
        //   460: aload_0        
        //   461: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   464: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //   467: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //   470: pop            
        //   471: aload_0        
        //   472: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   475: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   478: iload_2        
        //   479: aconst_null    
        //   480: aastore        
        //   481: aload_0        
        //   482: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   485: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //   488: iload_2        
        //   489: iconst_1       
        //   490: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   493: lload           4
        //   495: lload           8
        //   497: ladd           
        //   498: lstore          10
        //   500: lload           10
        //   502: ldc2_w          5
        //   505: lcmp           
        //   506: ifle            1174
        //   509: lload           10
        //   511: ldc2_w          5
        //   514: lsub           
        //   515: lstore          4
        //   517: aload_0        
        //   518: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   521: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$400:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Z
        //   524: ifne            561
        //   527: aload_0        
        //   528: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   531: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   534: ldc             "first buffer to render"
        //   536: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   539: pop            
        //   540: aload_0        
        //   541: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   544: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
        //   547: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener.onStartRender:()V
        //   552: aload_0        
        //   553: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   556: iconst_1       
        //   557: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$402:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
        //   560: pop            
        //   561: lload           8
        //   563: ldc2_w          -20
        //   566: lcmp           
        //   567: ifge            1155
        //   570: aload_0        
        //   571: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   574: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   577: iconst_3       
        //   578: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   581: ifeq            1155
        //   584: aload_0        
        //   585: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   588: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   591: new             Ljava/lang/StringBuilder;
        //   594: dup            
        //   595: invokespecial   java/lang/StringBuilder.<init>:()V
        //   598: ldc             "STAT:rendered frame "
        //   600: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   603: aload_0        
        //   604: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   607: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   610: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   613: ldc             " @"
        //   615: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   618: lload           6
        //   620: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   623: ldc             ", with delta "
        //   625: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   628: lload           8
        //   630: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   633: ldc             ", next after "
        //   635: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   638: lload           4
        //   640: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   643: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   646: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   649: pop            
        //   650: aload_0        
        //   651: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   654: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$508:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   657: pop2           
        //   658: aload_0        
        //   659: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   662: lload           6
        //   664: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$102:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;J)J
        //   667: pop2           
        //   668: aload_1        
        //   669: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   672: iconst_4       
        //   673: if_icmpne       177
        //   676: aload_0        
        //   677: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   680: iconst_1       
        //   681: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
        //   684: pop            
        //   685: ldc2_w          -1
        //   688: lstore          6
        //   690: lload           6
        //   692: lstore          4
        //   694: aload_0        
        //   695: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   698: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   701: iconst_3       
        //   702: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   705: ifeq            177
        //   708: aload_0        
        //   709: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   712: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   715: new             Ljava/lang/StringBuilder;
        //   718: dup            
        //   719: invokespecial   java/lang/StringBuilder.<init>:()V
        //   722: ldc             "EOS: has flag, rendered frame "
        //   724: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   727: aload_0        
        //   728: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   731: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   734: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   737: ldc             ",skipped frame "
        //   739: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   742: aload_0        
        //   743: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   746: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //   749: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   752: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   755: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   758: pop            
        //   759: lload           6
        //   761: lstore          4
        //   763: goto            177
        //   766: astore_1       
        //   767: aload           13
        //   769: monitorexit    
        //   770: aload_1        
        //   771: athrow         
        //   772: iconst_0       
        //   773: istore_3       
        //   774: goto            398
        //   777: astore          12
        //   779: aload_0        
        //   780: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   783: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   786: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
        //   788: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   791: pop            
        //   792: aload_0        
        //   793: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   796: iconst_1       
        //   797: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
        //   800: pop            
        //   801: goto            493
        //   804: aload_0        
        //   805: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   808: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //   811: invokevirtual   java/util/LinkedList.size:()I
        //   814: iconst_1       
        //   815: if_icmple       953
        //   818: lload           6
        //   820: ldc2_w          -30
        //   823: lcmp           
        //   824: ifgt            953
        //   827: aload_0        
        //   828: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   831: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //   834: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //   837: pop            
        //   838: aload_0        
        //   839: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   842: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   845: iload_2        
        //   846: aconst_null    
        //   847: aastore        
        //   848: aload_0        
        //   849: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   852: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //   855: iload_2        
        //   856: iconst_0       
        //   857: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   860: aload_0        
        //   861: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   864: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //   867: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
        //   870: checkcast       Ljava/lang/Integer;
        //   873: invokevirtual   java/lang/Integer.intValue:()I
        //   876: istore_2       
        //   877: aload_0        
        //   878: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   881: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   884: iload_2        
        //   885: aaload         
        //   886: astore_1       
        //   887: aload_1        
        //   888: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   891: ldc2_w          1000
        //   894: ldiv           
        //   895: lstore          6
        //   897: aload_0        
        //   898: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   901: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   904: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //   907: lstore          8
        //   909: iload_3        
        //   910: iconst_1       
        //   911: iadd           
        //   912: istore_3       
        //   913: lload           6
        //   915: lstore          4
        //   917: lload           6
        //   919: lload           8
        //   921: lsub           
        //   922: lstore          6
        //   924: goto            804
        //   927: astore_1       
        //   928: aload_0        
        //   929: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   932: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //   935: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
        //   937: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   940: pop            
        //   941: aload_0        
        //   942: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   945: iconst_1       
        //   946: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
        //   949: pop            
        //   950: goto            860
        //   953: aload_0        
        //   954: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   957: iload_3        
        //   958: i2l            
        //   959: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$514:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;J)J
        //   962: pop2           
        //   963: aload_0        
        //   964: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   967: iload_3        
        //   968: i2l            
        //   969: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$614:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;J)J
        //   972: pop2           
        //   973: aload_0        
        //   974: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   977: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //   980: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //   983: pop            
        //   984: aload_0        
        //   985: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   988: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   991: iload_2        
        //   992: aconst_null    
        //   993: aastore        
        //   994: aload_0        
        //   995: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //   998: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //  1001: iload_2        
        //  1002: iconst_1       
        //  1003: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //  1006: aload_0        
        //  1007: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //  1010: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //  1013: iconst_3       
        //  1014: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //  1017: ifeq            1206
        //  1020: aload_0        
        //  1021: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //  1024: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //  1027: new             Ljava/lang/StringBuilder;
        //  1030: dup            
        //  1031: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1034: ldc             "STAT:REND frame "
        //  1036: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1039: aload_0        
        //  1040: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //  1043: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)J
        //  1046: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1049: ldc             " skipped "
        //  1051: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1054: iload_3        
        //  1055: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1058: ldc             " @"
        //  1060: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1063: lload           4
        //  1065: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1068: ldc             ", with delta "
        //  1070: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1073: lload           6
        //  1075: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1078: ldc             ", next after "
        //  1080: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1083: ldc2_w          10
        //  1086: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1089: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1092: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1095: pop            
        //  1096: goto            1206
        //  1099: astore          12
        //  1101: aload_0        
        //  1102: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //  1105: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;)Ljava/lang/String;
        //  1108: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
        //  1110: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1113: pop            
        //  1114: aload_0        
        //  1115: getfield        com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;
        //  1118: iconst_1       
        //  1119: invokestatic    com/netflix/mediaclient/media/JPlayer/VideoDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/VideoDecoderPipe;Z)Z
        //  1122: pop            
        //  1123: goto            1006
        //  1126: lload           8
        //  1128: ldc2_w          5
        //  1131: lsub           
        //  1132: lstore          6
        //  1134: lload           6
        //  1136: lstore          4
        //  1138: lload           6
        //  1140: ldc2_w          50
        //  1143: lcmp           
        //  1144: ifle            177
        //  1147: ldc2_w          50
        //  1150: lstore          4
        //  1152: goto            177
        //  1155: goto            650
        //  1158: ldc2_w          20
        //  1161: lstore          4
        //  1163: goto            177
        //  1166: ldc2_w          30
        //  1169: lstore          4
        //  1171: goto            456
        //  1174: lload           10
        //  1176: lstore          4
        //  1178: lload           10
        //  1180: lconst_0       
        //  1181: lcmp           
        //  1182: ifge            517
        //  1185: ldc2_w          10
        //  1188: lstore          4
        //  1190: goto            517
        //  1193: iconst_0       
        //  1194: istore_3       
        //  1195: lload           6
        //  1197: lstore          4
        //  1199: lload           8
        //  1201: lstore          6
        //  1203: goto            804
        //  1206: ldc2_w          10
        //  1209: lstore          8
        //  1211: lload           4
        //  1213: lstore          6
        //  1215: lload           8
        //  1217: lstore          4
        //  1219: goto            650
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  73     86     766    772    Any
        //  91     128    766    772    Any
        //  137    172    766    772    Any
        //  177    180    766    772    Any
        //  372    387    766    772    Any
        //  407    431    766    772    Any
        //  460    481    766    772    Any
        //  481    493    777    804    Ljava/lang/Exception;
        //  481    493    766    772    Any
        //  517    561    766    772    Any
        //  570    650    766    772    Any
        //  650    668    766    772    Any
        //  668    685    766    772    Any
        //  694    759    766    772    Any
        //  767    770    766    772    Any
        //  779    801    766    772    Any
        //  804    818    766    772    Any
        //  827    848    766    772    Any
        //  848    860    927    953    Ljava/lang/Exception;
        //  848    860    766    772    Any
        //  860    909    766    772    Any
        //  928    950    766    772    Any
        //  953    994    766    772    Any
        //  994    1006   1099   1126   Ljava/lang/Exception;
        //  994    1006   766    772    Any
        //  1006   1096   766    772    Any
        //  1101   1123   766    772    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0493:
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
