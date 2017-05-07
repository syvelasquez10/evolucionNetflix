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
        //                3: 1199
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
        //   197: if_icmpeq       1285
        //   200: aload_1        
        //   201: ifnull          1285
        //   204: aload_0        
        //   205: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   208: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   211: ifnull          1285
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
        //   236: ifge            413
        //   239: ldc2_w          30
        //   242: lstore          4
        //   244: aload           15
        //   246: monitorexit    
        //   247: aload_0        
        //   248: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   251: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$700:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
        //   254: ifeq            331
        //   257: aload_0        
        //   258: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   261: iconst_1       
        //   262: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
        //   265: pop            
        //   266: ldc2_w          -1
        //   269: lstore          6
        //   271: lload           6
        //   273: lstore          4
        //   275: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   278: ifeq            331
        //   281: ldc             "MediaDecoder2Video"
        //   283: new             Ljava/lang/StringBuilder;
        //   286: dup            
        //   287: invokespecial   java/lang/StringBuilder.<init>:()V
        //   290: ldc             "EOS: stopped, rendered frame "
        //   292: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   295: aload_0        
        //   296: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   299: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   302: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   305: ldc             ",skipped frame "
        //   307: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   310: aload_0        
        //   311: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   314: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   317: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   320: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   323: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   326: pop            
        //   327: lload           6
        //   329: lstore          4
        //   331: aload_0        
        //   332: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   335: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
        //   338: ifne            44
        //   341: lload           4
        //   343: ldc2_w          60
        //   346: lcmp           
        //   347: ifgt            361
        //   350: lload           4
        //   352: lstore          6
        //   354: lload           4
        //   356: lconst_0       
        //   357: lcmp           
        //   358: ifgt            398
        //   361: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   364: ifeq            393
        //   367: ldc             "MediaDecoder2Video"
        //   369: new             Ljava/lang/StringBuilder;
        //   372: dup            
        //   373: invokespecial   java/lang/StringBuilder.<init>:()V
        //   376: ldc             "unexpect loop time "
        //   378: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   381: lload           4
        //   383: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   386: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   389: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   392: pop            
        //   393: ldc2_w          30
        //   396: lstore          6
        //   398: aload_0        
        //   399: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   402: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$800:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Landroid/os/Handler;
        //   405: iconst_1       
        //   406: lload           6
        //   408: invokevirtual   android/os/Handler.sendEmptyMessageDelayed:(IJ)Z
        //   411: pop            
        //   412: return         
        //   413: lload           6
        //   415: aload_0        
        //   416: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   419: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   422: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
        //   425: lsub           
        //   426: lstore          10
        //   428: aload_0        
        //   429: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   432: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
        //   435: ifeq            1293
        //   438: lload           10
        //   440: ldc2_w          -30
        //   443: lcmp           
        //   444: ifle            821
        //   447: goto            1293
        //   450: lload           10
        //   452: ldc2_w          20
        //   455: lcmp           
        //   456: ifge            1170
        //   459: aload_0        
        //   460: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   463: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   466: lconst_0       
        //   467: lcmp           
        //   468: ifle            508
        //   471: lload           6
        //   473: aload_0        
        //   474: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   477: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   480: lsub           
        //   481: lstore          8
        //   483: lload           8
        //   485: ldc2_w          30
        //   488: lcmp           
        //   489: iflt            1298
        //   492: lload           8
        //   494: lstore          4
        //   496: lload           8
        //   498: ldc2_w          50
        //   501: lcmp           
        //   502: ifle            508
        //   505: goto            1298
        //   508: iload_3        
        //   509: ifeq            1325
        //   512: aload_0        
        //   513: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   516: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
        //   519: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //   522: pop            
        //   523: aload_0        
        //   524: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   527: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   530: iload_2        
        //   531: aconst_null    
        //   532: aastore        
        //   533: aload_0        
        //   534: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   537: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
        //   540: iload_2        
        //   541: iconst_1       
        //   542: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   545: lload           4
        //   547: lload           10
        //   549: ladd           
        //   550: lstore          8
        //   552: lload           8
        //   554: ldc2_w          5
        //   557: lcmp           
        //   558: ifle            1306
        //   561: lload           8
        //   563: ldc2_w          5
        //   566: lsub           
        //   567: lstore          4
        //   569: aload_0        
        //   570: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   573: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Z
        //   576: ifne            596
        //   579: ldc             "MediaDecoder2Video"
        //   581: ldc             "first buffer to render"
        //   583: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   586: pop            
        //   587: aload_0        
        //   588: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   591: iconst_1       
        //   592: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$202:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
        //   595: pop            
        //   596: aload_0        
        //   597: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   600: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   603: ifnull          628
        //   606: aload_0        
        //   607: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   610: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   613: iconst_0       
        //   614: aload_0        
        //   615: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   618: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   621: lload           6
        //   623: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onSampleRendered:(ZJJ)V
        //   628: lload           10
        //   630: ldc2_w          -20
        //   633: lcmp           
        //   634: ifge            1275
        //   637: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   640: ifeq            1275
        //   643: ldc             "MediaDecoder2Video"
        //   645: new             Ljava/lang/StringBuilder;
        //   648: dup            
        //   649: invokespecial   java/lang/StringBuilder.<init>:()V
        //   652: ldc             "STAT:rendered frame "
        //   654: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   657: aload_0        
        //   658: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   661: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   664: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   667: ldc             " @"
        //   669: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   672: lload           6
        //   674: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   677: ldc             ", with delta "
        //   679: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   682: lload           10
        //   684: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   687: ldc             ", next after "
        //   689: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   692: lload           4
        //   694: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   697: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   700: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   703: pop            
        //   704: lload           6
        //   706: lstore          8
        //   708: aload_1        
        //   709: astore          14
        //   711: aload_0        
        //   712: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   715: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$508:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   718: pop2           
        //   719: aload_0        
        //   720: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   723: lload           8
        //   725: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$302:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
        //   728: pop2           
        //   729: aload           14
        //   731: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   734: iconst_4       
        //   735: if_icmpne       244
        //   738: aload_0        
        //   739: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   742: iconst_1       
        //   743: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
        //   746: pop            
        //   747: ldc2_w          -1
        //   750: lstore          6
        //   752: lload           6
        //   754: lstore          4
        //   756: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   759: ifeq            244
        //   762: ldc             "MediaDecoder2Video"
        //   764: new             Ljava/lang/StringBuilder;
        //   767: dup            
        //   768: invokespecial   java/lang/StringBuilder.<init>:()V
        //   771: ldc             "EOS: has flag, rendered frame "
        //   773: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   776: aload_0        
        //   777: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   780: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   783: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   786: ldc             ",skipped frame "
        //   788: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   791: aload_0        
        //   792: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   795: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   798: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   801: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   804: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   807: pop            
        //   808: lload           6
        //   810: lstore          4
        //   812: goto            244
        //   815: astore_1       
        //   816: aload           15
        //   818: monitorexit    
        //   819: aload_1        
        //   820: athrow         
        //   821: iconst_0       
        //   822: istore_3       
        //   823: goto            450
        //   826: astore          14
        //   828: ldc             "MediaDecoder2Video"
        //   830: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
        //   832: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   835: pop            
        //   836: aload_0        
        //   837: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   840: iconst_1       
        //   841: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
        //   844: pop            
        //   845: goto            545
        //   848: aload_0        
        //   849: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   852: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
        //   855: invokevirtual   java/util/LinkedList.size:()I
        //   858: iconst_1       
        //   859: if_icmple       988
        //   862: lload           10
        //   864: ldc2_w          -30
        //   867: lcmp           
        //   868: ifgt            988
        //   871: aload_0        
        //   872: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   875: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
        //   878: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //   881: pop            
        //   882: aload_0        
        //   883: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   886: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   889: iload_2        
        //   890: aconst_null    
        //   891: aastore        
        //   892: aload_0        
        //   893: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   896: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
        //   899: iload_2        
        //   900: iconst_0       
        //   901: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //   904: aload_0        
        //   905: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   908: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
        //   911: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
        //   914: checkcast       Ljava/lang/Integer;
        //   917: invokevirtual   java/lang/Integer.intValue:()I
        //   920: istore_2       
        //   921: aload_0        
        //   922: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   925: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   928: iload_2        
        //   929: aaload         
        //   930: astore_1       
        //   931: aload_1        
        //   932: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //   935: ldc2_w          1000
        //   938: ldiv           
        //   939: lstore          6
        //   941: aload_0        
        //   942: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   945: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   948: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.get:()J
        //   951: lstore          4
        //   953: iload_3        
        //   954: iconst_1       
        //   955: iadd           
        //   956: istore_3       
        //   957: lload           6
        //   959: lload           4
        //   961: lsub           
        //   962: lstore          10
        //   964: goto            848
        //   967: astore_1       
        //   968: ldc             "MediaDecoder2Video"
        //   970: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
        //   972: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   975: pop            
        //   976: aload_0        
        //   977: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   980: iconst_1       
        //   981: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
        //   984: pop            
        //   985: goto            904
        //   988: aload_0        
        //   989: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   992: iload_3        
        //   993: i2l            
        //   994: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$514:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
        //   997: pop2           
        //   998: aload_0        
        //   999: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1002: iload_3        
        //  1003: i2l            
        //  1004: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$614:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
        //  1007: pop2           
        //  1008: aload_0        
        //  1009: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1012: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
        //  1015: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //  1018: pop            
        //  1019: aload_0        
        //  1020: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1023: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //  1026: iload_2        
        //  1027: aconst_null    
        //  1028: aastore        
        //  1029: aload_0        
        //  1030: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1033: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
        //  1036: iload_2        
        //  1037: iconst_1       
        //  1038: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //  1041: ldc2_w          10
        //  1044: lstore          12
        //  1046: lload           12
        //  1048: lstore          4
        //  1050: aload_1        
        //  1051: astore          14
        //  1053: lload           6
        //  1055: lstore          8
        //  1057: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //  1060: ifeq            711
        //  1063: ldc             "MediaDecoder2Video"
        //  1065: new             Ljava/lang/StringBuilder;
        //  1068: dup            
        //  1069: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1072: ldc             "STAT:REND frame "
        //  1074: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1077: aload_0        
        //  1078: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1081: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //  1084: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1087: ldc             " skipped "
        //  1089: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1092: iload_3        
        //  1093: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1096: ldc             " @"
        //  1098: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1101: lload           6
        //  1103: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1106: ldc             ", with delta "
        //  1108: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1111: lload           10
        //  1113: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1116: ldc             ", next after "
        //  1118: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1121: ldc2_w          10
        //  1124: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1127: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1130: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1133: pop            
        //  1134: lload           12
        //  1136: lstore          4
        //  1138: aload_1        
        //  1139: astore          14
        //  1141: lload           6
        //  1143: lstore          8
        //  1145: goto            711
        //  1148: astore          14
        //  1150: ldc             "MediaDecoder2Video"
        //  1152: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
        //  1154: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1157: pop            
        //  1158: aload_0        
        //  1159: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1162: iconst_1       
        //  1163: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
        //  1166: pop            
        //  1167: goto            1041
        //  1170: lload           10
        //  1172: ldc2_w          5
        //  1175: lsub           
        //  1176: lstore          6
        //  1178: lload           6
        //  1180: lstore          4
        //  1182: lload           6
        //  1184: ldc2_w          50
        //  1187: lcmp           
        //  1188: ifle            244
        //  1191: ldc2_w          50
        //  1194: lstore          4
        //  1196: goto            244
        //  1199: ldc             "MediaDecoder2Video"
        //  1201: ldc             "render state flushing"
        //  1203: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1206: pop            
        //  1207: aload_0        
        //  1208: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1211: iconst_0       
        //  1212: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$202:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
        //  1215: pop            
        //  1216: aload_0        
        //  1217: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1220: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
        //  1223: astore_1       
        //  1224: aload_1        
        //  1225: monitorenter   
        //  1226: aload_0        
        //  1227: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1230: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
        //  1233: invokevirtual   java/util/LinkedList.clear:()V
        //  1236: aload_1        
        //  1237: monitorexit    
        //  1238: aload_0        
        //  1239: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1242: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$900:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //  1245: astore_1       
        //  1246: aload_1        
        //  1247: monitorenter   
        //  1248: aload_0        
        //  1249: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1252: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$900:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //  1255: invokevirtual   java/lang/Object.notify:()V
        //  1258: aload_1        
        //  1259: monitorexit    
        //  1260: return         
        //  1261: astore          14
        //  1263: aload_1        
        //  1264: monitorexit    
        //  1265: aload           14
        //  1267: athrow         
        //  1268: astore          14
        //  1270: aload_1        
        //  1271: monitorexit    
        //  1272: aload           14
        //  1274: athrow         
        //  1275: aload_1        
        //  1276: astore          14
        //  1278: lload           6
        //  1280: lstore          8
        //  1282: goto            711
        //  1285: ldc2_w          20
        //  1288: lstore          4
        //  1290: goto            244
        //  1293: iconst_1       
        //  1294: istore_3       
        //  1295: goto            450
        //  1298: ldc2_w          30
        //  1301: lstore          4
        //  1303: goto            508
        //  1306: lload           8
        //  1308: lstore          4
        //  1310: lload           8
        //  1312: lconst_0       
        //  1313: lcmp           
        //  1314: ifge            569
        //  1317: ldc2_w          10
        //  1320: lstore          4
        //  1322: goto            569
        //  1325: iconst_0       
        //  1326: istore_3       
        //  1327: goto            848
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  96     109    815    821    Any
        //  114    151    815    821    Any
        //  155    195    815    821    Any
        //  204    239    815    821    Any
        //  244    247    815    821    Any
        //  413    438    815    821    Any
        //  459    483    815    821    Any
        //  512    533    815    821    Any
        //  533    545    826    848    Ljava/lang/Exception;
        //  533    545    815    821    Any
        //  569    596    815    821    Any
        //  596    628    815    821    Any
        //  637    704    815    821    Any
        //  711    729    815    821    Any
        //  729    747    815    821    Any
        //  756    808    815    821    Any
        //  816    819    815    821    Any
        //  828    845    815    821    Any
        //  848    862    815    821    Any
        //  871    892    815    821    Any
        //  892    904    967    988    Ljava/lang/Exception;
        //  892    904    815    821    Any
        //  904    953    815    821    Any
        //  968    985    815    821    Any
        //  988    1029   815    821    Any
        //  1029   1041   1148   1170   Ljava/lang/Exception;
        //  1029   1041   815    821    Any
        //  1057   1134   815    821    Any
        //  1150   1167   815    821    Any
        //  1226   1238   1268   1275   Any
        //  1248   1260   1261   1268   Any
        //  1263   1265   1261   1268   Any
        //  1270   1272   1268   1275   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0545:
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
