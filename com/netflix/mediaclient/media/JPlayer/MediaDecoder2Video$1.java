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
        //                3: 1215
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
        //   197: if_icmpeq       1301
        //   200: aload_1        
        //   201: ifnull          1301
        //   204: aload_0        
        //   205: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   208: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   211: ifnull          1301
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
        //   435: ifeq            1309
        //   438: lload           10
        //   440: ldc2_w          -30
        //   443: lcmp           
        //   444: ifle            821
        //   447: goto            1309
        //   450: lload           10
        //   452: ldc2_w          20
        //   455: lcmp           
        //   456: ifge            1186
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
        //   489: iflt            1314
        //   492: lload           8
        //   494: lstore          4
        //   496: lload           8
        //   498: ldc2_w          50
        //   501: lcmp           
        //   502: ifle            508
        //   505: goto            1314
        //   508: iload_3        
        //   509: ifeq            1341
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
        //   558: ifle            1322
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
        //   634: ifge            1291
        //   637: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   640: ifeq            1291
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
        //   992: aload_0        
        //   993: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //   996: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //   999: iload_3        
        //  1000: i2l            
        //  1001: ladd           
        //  1002: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$502:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
        //  1005: pop2           
        //  1006: aload_0        
        //  1007: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1010: aload_0        
        //  1011: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1014: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //  1017: iload_3        
        //  1018: i2l            
        //  1019: ladd           
        //  1020: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$602:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;J)J
        //  1023: pop2           
        //  1024: aload_0        
        //  1025: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1028: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
        //  1031: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //  1034: pop            
        //  1035: aload_0        
        //  1036: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1039: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //  1042: iload_2        
        //  1043: aconst_null    
        //  1044: aastore        
        //  1045: aload_0        
        //  1046: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1049: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mDecoder:Landroid/media/MediaCodec;
        //  1052: iload_2        
        //  1053: iconst_1       
        //  1054: invokevirtual   android/media/MediaCodec.releaseOutputBuffer:(IZ)V
        //  1057: ldc2_w          10
        //  1060: lstore          12
        //  1062: lload           12
        //  1064: lstore          4
        //  1066: aload_1        
        //  1067: astore          14
        //  1069: lload           6
        //  1071: lstore          8
        //  1073: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //  1076: ifeq            711
        //  1079: ldc             "MediaDecoder2Video"
        //  1081: new             Ljava/lang/StringBuilder;
        //  1084: dup            
        //  1085: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1088: ldc             "STAT:REND frame "
        //  1090: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1093: aload_0        
        //  1094: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1097: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)J
        //  1100: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1103: ldc             " skipped "
        //  1105: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1108: iload_3        
        //  1109: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1112: ldc             " @"
        //  1114: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1117: lload           6
        //  1119: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1122: ldc             ", with delta "
        //  1124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1127: lload           10
        //  1129: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1132: ldc             ", next after "
        //  1134: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1137: ldc2_w          10
        //  1140: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1143: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1146: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1149: pop            
        //  1150: lload           12
        //  1152: lstore          4
        //  1154: aload_1        
        //  1155: astore          14
        //  1157: lload           6
        //  1159: lstore          8
        //  1161: goto            711
        //  1164: astore          14
        //  1166: ldc             "MediaDecoder2Video"
        //  1168: ldc             "get un-documented exception as a result of releaseOutputBuffer()"
        //  1170: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1173: pop            
        //  1174: aload_0        
        //  1175: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1178: iconst_1       
        //  1179: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
        //  1182: pop            
        //  1183: goto            1057
        //  1186: lload           10
        //  1188: ldc2_w          5
        //  1191: lsub           
        //  1192: lstore          6
        //  1194: lload           6
        //  1196: lstore          4
        //  1198: lload           6
        //  1200: ldc2_w          50
        //  1203: lcmp           
        //  1204: ifle            244
        //  1207: ldc2_w          50
        //  1210: lstore          4
        //  1212: goto            244
        //  1215: ldc             "MediaDecoder2Video"
        //  1217: ldc             "render state flushing"
        //  1219: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1222: pop            
        //  1223: aload_0        
        //  1224: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1227: iconst_0       
        //  1228: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$202:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;Z)Z
        //  1231: pop            
        //  1232: aload_0        
        //  1233: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1236: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
        //  1239: astore_1       
        //  1240: aload_1        
        //  1241: monitorenter   
        //  1242: aload_0        
        //  1243: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1246: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.mOutputBuffersQ:Ljava/util/LinkedList;
        //  1249: invokevirtual   java/util/LinkedList.clear:()V
        //  1252: aload_1        
        //  1253: monitorexit    
        //  1254: aload_0        
        //  1255: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1258: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$900:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //  1261: astore_1       
        //  1262: aload_1        
        //  1263: monitorenter   
        //  1264: aload_0        
        //  1265: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;
        //  1268: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Video.access$900:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Video;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //  1271: invokevirtual   java/lang/Object.notify:()V
        //  1274: aload_1        
        //  1275: monitorexit    
        //  1276: return         
        //  1277: astore          14
        //  1279: aload_1        
        //  1280: monitorexit    
        //  1281: aload           14
        //  1283: athrow         
        //  1284: astore          14
        //  1286: aload_1        
        //  1287: monitorexit    
        //  1288: aload           14
        //  1290: athrow         
        //  1291: aload_1        
        //  1292: astore          14
        //  1294: lload           6
        //  1296: lstore          8
        //  1298: goto            711
        //  1301: ldc2_w          20
        //  1304: lstore          4
        //  1306: goto            244
        //  1309: iconst_1       
        //  1310: istore_3       
        //  1311: goto            450
        //  1314: ldc2_w          30
        //  1317: lstore          4
        //  1319: goto            508
        //  1322: lload           8
        //  1324: lstore          4
        //  1326: lload           8
        //  1328: lconst_0       
        //  1329: lcmp           
        //  1330: ifge            569
        //  1333: ldc2_w          10
        //  1336: lstore          4
        //  1338: goto            569
        //  1341: iconst_0       
        //  1342: istore_3       
        //  1343: goto            848
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
        //  988    1045   815    821    Any
        //  1045   1057   1164   1186   Ljava/lang/Exception;
        //  1045   1057   815    821    Any
        //  1073   1150   815    821    Any
        //  1166   1183   815    821    Any
        //  1242   1254   1284   1291   Any
        //  1264   1276   1277   1284   Any
        //  1279   1281   1277   1284   Any
        //  1286   1288   1284   1291   Any
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
