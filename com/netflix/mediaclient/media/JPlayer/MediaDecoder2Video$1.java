// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.os.Message;
import android.os.Looper;
import android.os.Handler;

class MediaDecoder2Video$1 extends Handler
{
    final /* synthetic */ MediaDecoder2Video this$0;
    
    MediaDecoder2Video$1(final MediaDecoder2Video this$0, final Looper looper) {
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
        //     1: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //     4: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mHearbeat:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$RenderHeartbeat;
        //     7: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$RenderHeartbeat.ShowHearbeat:()V
        //    10: aload_1        
        //    11: getfield        android/os/Message.what:I
        //    14: tableswitch {
        //                2: 45
        //                3: 1214
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
        //    77: lstore          4
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
        //   195: iload_2        
        //   196: iconst_m1      
        //   197: if_icmpeq       1300
        //   200: aload_1        
        //   201: ifnull          1300
        //   204: aload_0        
        //   205: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   208: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   211: ifnull          1300
        //   214: aload_1        
        //   215: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   218: ldc2_w          1000
        //   221: ldiv           
        //   222: lstore          6
        //   224: aload_0        
        //   225: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   228: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   231: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
        //   234: lconst_0       
        //   235: lcmp           
        //   236: ifge            419
        //   239: ldc2_w          30
        //   242: lstore          4
        //   244: aload           15
        //   246: monitorexit    
        //   247: aload_0        
        //   248: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   251: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$700:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
        //   254: ifeq            334
        //   257: aload_0        
        //   258: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   261: iconst_1       
        //   262: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
        //   265: pop            
        //   266: ldc2_w          -1
        //   269: lstore          6
        //   271: lload           6
        //   273: lstore          4
        //   275: ldc             "MediaDecoder2Video"
        //   277: iconst_3       
        //   278: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   281: ifeq            334
        //   284: ldc             "MediaDecoder2Video"
        //   286: new             Ljava/lang/StringBuilder;
        //   289: dup            
        //   290: invokespecial   java/lang/StringBuilder.<init>:()V
        //   293: ldc             "EOS: stopped, rendered frame "
        //   295: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   298: aload_0        
        //   299: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   302: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   305: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   308: ldc             ",skipped frame "
        //   310: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   313: aload_0        
        //   314: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   317: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   320: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   323: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   326: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   329: pop            
        //   330: lload           6
        //   332: lstore          4
        //   334: aload_0        
        //   335: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   338: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
        //   341: ifne            44
        //   344: lload           4
        //   346: ldc2_w          60
        //   349: lcmp           
        //   350: ifgt            364
        //   353: lload           4
        //   355: lstore          6
        //   357: lload           4
        //   359: lconst_0       
        //   360: lcmp           
        //   361: ifgt            404
        //   364: ldc             "MediaDecoder2Video"
        //   366: iconst_3       
        //   367: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   370: ifeq            399
        //   373: ldc             "MediaDecoder2Video"
        //   375: new             Ljava/lang/StringBuilder;
        //   378: dup            
        //   379: invokespecial   java/lang/StringBuilder.<init>:()V
        //   382: ldc             "unexpect loop time "
        //   384: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   387: lload           4
        //   389: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   392: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   395: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   398: pop            
        //   399: ldc2_w          30
        //   402: lstore          6
        //   404: aload_0        
        //   405: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   408: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$800:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Landroid/os/Handler;
        //   411: iconst_1       
        //   412: lload           6
        //   414: invokevirtual   android/os/Handler.sendEmptyMessageDelayed:(IJ)Z
        //   417: pop            
        //   418: return         
        //   419: lload           6
        //   421: aload_0        
        //   422: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   425: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   428: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
        //   431: lsub           
        //   432: lstore          10
        //   434: aload_0        
        //   435: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   438: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
        //   441: ifeq            1308
        //   444: lload           10
        //   446: ldc2_w          -30
        //   449: lcmp           
        //   450: ifle            833
        //   453: goto            1308
        //   456: lload           10
        //   458: ldc2_w          20
        //   461: lcmp           
        //   462: ifge            1185
        //   465: aload_0        
        //   466: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   469: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   472: lconst_0       
        //   473: lcmp           
        //   474: ifle            514
        //   477: lload           6
        //   479: aload_0        
        //   480: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   483: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   486: lsub           
        //   487: lstore          8
        //   489: lload           8
        //   491: ldc2_w          30
        //   494: lcmp           
        //   495: iflt            1313
        //   498: lload           8
        //   500: lstore          4
        //   502: lload           8
        //   504: ldc2_w          50
        //   507: lcmp           
        //   508: ifle            514
        //   511: goto            1313
        //   514: iload_3        
        //   515: ifeq            1340
        //   518: aload_0        
        //   519: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   522: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
        //   525: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //   528: pop            
        //   529: aload_0        
        //   530: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   533: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   536: iload_2        
        //   537: aconst_null    
        //   538: aastore        
        //   539: aload_0        
        //   540: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   543: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
        //   546: iload_2        
        //   547: iconst_1       
        //   548: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   551: lload           4
        //   553: lload           10
        //   555: ladd           
        //   556: lstore          8
        //   558: lload           8
        //   560: ldc2_w          5
        //   563: lcmp           
        //   564: ifle            1321
        //   567: lload           8
        //   569: ldc2_w          5
        //   572: lsub           
        //   573: lstore          4
        //   575: aload_0        
        //   576: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   579: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
        //   582: ifne            602
        //   585: ldc             "MediaDecoder2Video"
        //   587: ldc             "first buffer to render"
        //   589: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   592: pop            
        //   593: aload_0        
        //   594: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   597: iconst_1       
        //   598: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$202:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
        //   601: pop            
        //   602: aload_0        
        //   603: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   606: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   609: ifnull          634
        //   612: aload_0        
        //   613: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   616: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   619: iconst_0       
        //   620: aload_0        
        //   621: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   624: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   627: lload           6
        //   629: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onSampleRendered:(ZJJ)V
        //   634: lload           10
        //   636: ldc2_w          -20
        //   639: lcmp           
        //   640: ifge            1290
        //   643: ldc             "MediaDecoder2Video"
        //   645: iconst_3       
        //   646: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   649: ifeq            1290
        //   652: ldc             "MediaDecoder2Video"
        //   654: new             Ljava/lang/StringBuilder;
        //   657: dup            
        //   658: invokespecial   java/lang/StringBuilder.<init>:()V
        //   661: ldc             "STAT:rendered frame "
        //   663: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   666: aload_0        
        //   667: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   670: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   673: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   676: ldc             " @"
        //   678: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   681: lload           6
        //   683: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   686: ldc             ", with delta "
        //   688: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   691: lload           10
        //   693: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   696: ldc             ", next after "
        //   698: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   701: lload           4
        //   703: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   706: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   709: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   712: pop            
        //   713: lload           6
        //   715: lstore          8
        //   717: aload_1        
        //   718: astore          14
        //   720: aload_0        
        //   721: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   724: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$508:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   727: pop2           
        //   728: aload_0        
        //   729: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   732: lload           8
        //   734: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$302:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
        //   737: pop2           
        //   738: aload           14
        //   740: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   743: iconst_4       
        //   744: if_icmpne       244
        //   747: aload_0        
        //   748: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   751: iconst_1       
        //   752: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
        //   755: pop            
        //   756: ldc2_w          -1
        //   759: lstore          6
        //   761: lload           6
        //   763: lstore          4
        //   765: ldc             "MediaDecoder2Video"
        //   767: iconst_3       
        //   768: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   771: ifeq            244
        //   774: ldc             "MediaDecoder2Video"
        //   776: new             Ljava/lang/StringBuilder;
        //   779: dup            
        //   780: invokespecial   java/lang/StringBuilder.<init>:()V
        //   783: ldc             "EOS: has flag, rendered frame "
        //   785: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   788: aload_0        
        //   789: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   792: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   795: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   798: ldc             ",skipped frame "
        //   800: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   803: aload_0        
        //   804: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   807: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   810: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   813: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   816: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   819: pop            
        //   820: lload           6
        //   822: lstore          4
        //   824: goto            244
        //   827: astore_1       
        //   828: aload           15
        //   830: monitorexit    
        //   831: aload_1        
        //   832: athrow         
        //   833: iconst_0       
        //   834: istore_3       
        //   835: goto            456
        //   838: astore          14
        //   840: ldc             "MediaDecoder2Video"
        //   842: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
        //   844: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   847: pop            
        //   848: aload_0        
        //   849: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   852: iconst_1       
        //   853: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
        //   856: pop            
        //   857: goto            551
        //   860: aload_0        
        //   861: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   864: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
        //   867: invokevirtual   java/util/LinkedList.size:()I
        //   870: iconst_1       
        //   871: if_icmple       1000
        //   874: lload           10
        //   876: ldc2_w          -30
        //   879: lcmp           
        //   880: ifgt            1000
        //   883: aload_0        
        //   884: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   887: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
        //   890: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //   893: pop            
        //   894: aload_0        
        //   895: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   898: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   901: iload_2        
        //   902: aconst_null    
        //   903: aastore        
        //   904: aload_0        
        //   905: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   908: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
        //   911: iload_2        
        //   912: iconst_0       
        //   913: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   916: aload_0        
        //   917: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   920: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
        //   923: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
        //   926: checkcast       Ljava/lang/Integer;
        //   929: invokevirtual   java/lang/Integer.intValue:()I
        //   932: istore_2       
        //   933: aload_0        
        //   934: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   937: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   940: iload_2        
        //   941: aaload         
        //   942: astore_1       
        //   943: aload_1        
        //   944: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   947: ldc2_w          1000
        //   950: ldiv           
        //   951: lstore          6
        //   953: aload_0        
        //   954: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   957: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   960: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
        //   963: lstore          4
        //   965: iload_3        
        //   966: iconst_1       
        //   967: iadd           
        //   968: istore_3       
        //   969: lload           6
        //   971: lload           4
        //   973: lsub           
        //   974: lstore          10
        //   976: goto            860
        //   979: astore_1       
        //   980: ldc             "MediaDecoder2Video"
        //   982: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
        //   984: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   987: pop            
        //   988: aload_0        
        //   989: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   992: iconst_1       
        //   993: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
        //   996: pop            
        //   997: goto            916
        //  1000: aload_0        
        //  1001: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1004: iload_3        
        //  1005: i2l            
        //  1006: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$514:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
        //  1009: pop2           
        //  1010: aload_0        
        //  1011: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1014: iload_3        
        //  1015: i2l            
        //  1016: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$614:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
        //  1019: pop2           
        //  1020: aload_0        
        //  1021: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1024: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
        //  1027: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //  1030: pop            
        //  1031: aload_0        
        //  1032: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1035: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //  1038: iload_2        
        //  1039: aconst_null    
        //  1040: aastore        
        //  1041: aload_0        
        //  1042: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1045: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
        //  1048: iload_2        
        //  1049: iconst_1       
        //  1050: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //  1053: ldc2_w          10
        //  1056: lstore          12
        //  1058: lload           12
        //  1060: lstore          4
        //  1062: aload_1        
        //  1063: astore          14
        //  1065: lload           6
        //  1067: lstore          8
        //  1069: ldc             "MediaDecoder2Video"
        //  1071: iconst_3       
        //  1072: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //  1075: ifeq            720
        //  1078: ldc             "MediaDecoder2Video"
        //  1080: new             Ljava/lang/StringBuilder;
        //  1083: dup            
        //  1084: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1087: ldc             "STAT:REND frame "
        //  1089: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1092: aload_0        
        //  1093: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1096: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //  1099: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1102: ldc             " skipped "
        //  1104: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1107: iload_3        
        //  1108: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1111: ldc             " @"
        //  1113: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1116: lload           6
        //  1118: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1121: ldc             ", with delta "
        //  1123: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1126: lload           10
        //  1128: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1131: ldc             ", next after "
        //  1133: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1136: ldc2_w          10
        //  1139: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1142: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1145: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1148: pop            
        //  1149: lload           12
        //  1151: lstore          4
        //  1153: aload_1        
        //  1154: astore          14
        //  1156: lload           6
        //  1158: lstore          8
        //  1160: goto            720
        //  1163: astore          14
        //  1165: ldc             "MediaDecoder2Video"
        //  1167: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
        //  1169: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1172: pop            
        //  1173: aload_0        
        //  1174: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1177: iconst_1       
        //  1178: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
        //  1181: pop            
        //  1182: goto            1053
        //  1185: lload           10
        //  1187: ldc2_w          5
        //  1190: lsub           
        //  1191: lstore          6
        //  1193: lload           6
        //  1195: lstore          4
        //  1197: lload           6
        //  1199: ldc2_w          50
        //  1202: lcmp           
        //  1203: ifle            244
        //  1206: ldc2_w          50
        //  1209: lstore          4
        //  1211: goto            244
        //  1214: ldc             "MediaDecoder2Video"
        //  1216: ldc             "render state flushing"
        //  1218: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1221: pop            
        //  1222: aload_0        
        //  1223: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1226: iconst_0       
        //  1227: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$202:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
        //  1230: pop            
        //  1231: aload_0        
        //  1232: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1235: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
        //  1238: astore_1       
        //  1239: aload_1        
        //  1240: monitorenter   
        //  1241: aload_0        
        //  1242: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1245: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
        //  1248: invokevirtual   java/util/LinkedList.clear:()V
        //  1251: aload_1        
        //  1252: monitorexit    
        //  1253: aload_0        
        //  1254: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1257: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$900:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //  1260: astore_1       
        //  1261: aload_1        
        //  1262: monitorenter   
        //  1263: aload_0        
        //  1264: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1267: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$900:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //  1270: invokevirtual   java/lang/Object.notify:()V
        //  1273: aload_1        
        //  1274: monitorexit    
        //  1275: return         
        //  1276: astore          14
        //  1278: aload_1        
        //  1279: monitorexit    
        //  1280: aload           14
        //  1282: athrow         
        //  1283: astore          14
        //  1285: aload_1        
        //  1286: monitorexit    
        //  1287: aload           14
        //  1289: athrow         
        //  1290: aload_1        
        //  1291: astore          14
        //  1293: lload           6
        //  1295: lstore          8
        //  1297: goto            720
        //  1300: ldc2_w          20
        //  1303: lstore          4
        //  1305: goto            244
        //  1308: iconst_1       
        //  1309: istore_3       
        //  1310: goto            456
        //  1313: ldc2_w          30
        //  1316: lstore          4
        //  1318: goto            514
        //  1321: lload           8
        //  1323: lstore          4
        //  1325: lload           8
        //  1327: lconst_0       
        //  1328: lcmp           
        //  1329: ifge            575
        //  1332: ldc2_w          10
        //  1335: lstore          4
        //  1337: goto            575
        //  1340: iconst_0       
        //  1341: istore_3       
        //  1342: goto            860
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  96     109    827    833    Any
        //  114    151    827    833    Any
        //  155    195    827    833    Any
        //  204    239    827    833    Any
        //  244    247    827    833    Any
        //  419    444    827    833    Any
        //  465    489    827    833    Any
        //  518    539    827    833    Any
        //  539    551    838    860    Ljava/lang/Exception;
        //  539    551    827    833    Any
        //  575    602    827    833    Any
        //  602    634    827    833    Any
        //  643    713    827    833    Any
        //  720    738    827    833    Any
        //  738    756    827    833    Any
        //  765    820    827    833    Any
        //  828    831    827    833    Any
        //  840    857    827    833    Any
        //  860    874    827    833    Any
        //  883    904    827    833    Any
        //  904    916    979    1000   Ljava/lang/Exception;
        //  904    916    827    833    Any
        //  916    965    827    833    Any
        //  980    997    827    833    Any
        //  1000   1041   827    833    Any
        //  1041   1053   1163   1185   Ljava/lang/Exception;
        //  1041   1053   827    833    Any
        //  1069   1149   827    833    Any
        //  1165   1182   827    833    Any
        //  1241   1253   1283   1290   Any
        //  1263   1275   1276   1283   Any
        //  1278   1280   1276   1283   Any
        //  1285   1287   1283   1290   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0551:
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
