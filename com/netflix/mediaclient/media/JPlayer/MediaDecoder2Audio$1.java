// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.os.Message;
import android.os.Looper;
import android.os.Handler;

class MediaDecoder2Audio$1 extends Handler
{
    final /* synthetic */ MediaDecoder2Audio this$0;
    
    MediaDecoder2Audio$1(final MediaDecoder2Audio this$0, final Looper looper) {
        this.this$0 = this$0;
        super(looper);
    }
    
    public void handleMessage(final Message p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: getfield        android/os/Message.what:I
        //     4: tableswitch {
        //                2: 45
        //                3: 440
        //                4: 358
        //                5: 590
        //          default: 36
        //        }
        //    36: ldc             "MediaDecoder2Audio"
        //    38: ldc             "RenderThreadAudeo had unknown message"
        //    40: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    43: pop            
        //    44: return         
        //    45: aload_0        
        //    46: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //    49: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //    52: astore_1       
        //    53: aload_1        
        //    54: monitorenter   
        //    55: aload_0        
        //    56: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //    59: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //    62: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier.isPaused:()Z
        //    65: ifeq            96
        //    68: aload_0        
        //    69: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //    72: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //    75: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier.onPlaying:()V
        //    78: aload_0        
        //    79: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //    82: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //    85: invokevirtual   java/lang/Object.notify:()V
        //    88: ldc             "MediaDecoder2Audio"
        //    90: ldc             "render state play"
        //    92: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    95: pop            
        //    96: aload_1        
        //    97: monitorexit    
        //    98: iconst_m1      
        //    99: istore_2       
        //   100: aload_0        
        //   101: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   104: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   107: astore          6
        //   109: aload           6
        //   111: monitorenter   
        //   112: aload_0        
        //   113: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   116: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   119: invokevirtual   java/util/LinkedList.isEmpty:()Z
        //   122: ifne            614
        //   125: aload_0        
        //   126: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   129: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   132: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
        //   135: checkcast       Ljava/lang/Integer;
        //   138: invokevirtual   java/lang/Integer.intValue:()I
        //   141: istore_2       
        //   142: aload_0        
        //   143: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   146: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   149: iload_2        
        //   150: aaload         
        //   151: astore_1       
        //   152: aload_0        
        //   153: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   156: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffers:[Ljava/nio/ByteBuffer;
        //   159: iload_2        
        //   160: aaload         
        //   161: astore          5
        //   163: aload           6
        //   165: monitorexit    
        //   166: aload_1        
        //   167: ifnull          264
        //   170: aload_1        
        //   171: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   174: iconst_4       
        //   175: iand           
        //   176: ifeq            264
        //   179: ldc             "MediaDecoder2Audio"
        //   181: ldc             "renderer got buffer BUFFER_FLAG_END_OF_STREAM"
        //   183: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   186: pop            
        //   187: aload_0        
        //   188: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   191: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   194: ifnull          210
        //   197: aload_0        
        //   198: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   201: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   204: iconst_1       
        //   205: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onEndOfStream:(Z)V
        //   210: aload_0        
        //   211: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   214: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   217: astore_1       
        //   218: aload_1        
        //   219: monitorenter   
        //   220: aload_0        
        //   221: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   224: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   227: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier.isPlaying:()Z
        //   230: ifne            340
        //   233: ldc             "MediaDecoder2Audio"
        //   235: ldc             "render state is not play"
        //   237: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   240: pop            
        //   241: aload_1        
        //   242: monitorexit    
        //   243: return         
        //   244: astore          5
        //   246: aload_1        
        //   247: monitorexit    
        //   248: aload           5
        //   250: athrow         
        //   251: astore          5
        //   253: aload_1        
        //   254: monitorexit    
        //   255: aload           5
        //   257: athrow         
        //   258: astore_1       
        //   259: aload           6
        //   261: monitorexit    
        //   262: aload_1        
        //   263: athrow         
        //   264: aload_0        
        //   265: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   268: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.startRenderer:()V
        //   271: aload_0        
        //   272: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   275: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)J
        //   278: lstore_3       
        //   279: iload_2        
        //   280: iflt            295
        //   283: aload_0        
        //   284: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   287: iload_2        
        //   288: aload_1        
        //   289: aload           5
        //   291: lload_3        
        //   292: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;ILandroid/media/MediaCodec$BufferInfo;Ljava/nio/ByteBuffer;J)V
        //   295: aload_0        
        //   296: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   299: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   302: invokevirtual   java/util/LinkedList.isEmpty:()Z
        //   305: ifeq            98
        //   308: goto            210
        //   311: astore_1       
        //   312: ldc             "MediaDecoder2Audio"
        //   314: new             Ljava/lang/StringBuilder;
        //   317: dup            
        //   318: invokespecial   java/lang/StringBuilder.<init>:()V
        //   321: ldc             "getAudioHeaderPosition() has Exception"
        //   323: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   326: aload_1        
        //   327: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   330: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   333: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   336: pop            
        //   337: goto            210
        //   340: aload_0        
        //   341: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   344: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/os/Handler;
        //   347: iconst_1       
        //   348: ldc2_w          20
        //   351: invokevirtual   android/os/Handler.sendEmptyMessageDelayed:(IJ)Z
        //   354: pop            
        //   355: goto            241
        //   358: aload_0        
        //   359: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   362: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   365: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.pause:()J
        //   368: pop2           
        //   369: aload_0        
        //   370: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   373: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   376: astore_1       
        //   377: aload_1        
        //   378: monitorenter   
        //   379: aload_0        
        //   380: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   383: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   386: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier.onPaused:()V
        //   389: aload_0        
        //   390: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   393: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   396: invokevirtual   java/lang/Object.notify:()V
        //   399: aload_1        
        //   400: monitorexit    
        //   401: ldc             "MediaDecoder2Audio"
        //   403: ldc             "render state pause"
        //   405: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   408: pop            
        //   409: aload_0        
        //   410: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   413: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   416: ifnull          44
        //   419: aload_0        
        //   420: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   423: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   426: iconst_1       
        //   427: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onPasued:(Z)V
        //   432: return         
        //   433: astore          5
        //   435: aload_1        
        //   436: monitorexit    
        //   437: aload           5
        //   439: athrow         
        //   440: ldc             "MediaDecoder2Audio"
        //   442: ldc             "render state flushing"
        //   444: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   447: pop            
        //   448: aload_0        
        //   449: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   452: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   455: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.flush:()V
        //   458: aload_0        
        //   459: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   462: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   465: astore_1       
        //   466: aload_1        
        //   467: monitorenter   
        //   468: aload_0        
        //   469: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   472: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   475: invokevirtual   java/util/LinkedList.clear:()V
        //   478: aload_1        
        //   479: monitorexit    
        //   480: aload_0        
        //   481: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   484: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/media/AudioTrack;
        //   487: ifnull          519
        //   490: aload_0        
        //   491: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   494: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/media/AudioTrack;
        //   497: invokevirtual   android/media/AudioTrack.stop:()V
        //   500: aload_0        
        //   501: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   504: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/media/AudioTrack;
        //   507: invokevirtual   android/media/AudioTrack.release:()V
        //   510: aload_0        
        //   511: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   514: aconst_null    
        //   515: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;Landroid/media/AudioTrack;)Landroid/media/AudioTrack;
        //   518: pop            
        //   519: aload_0        
        //   520: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   523: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)V
        //   526: aload_0        
        //   527: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   530: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.startRenderer:()V
        //   533: aload_0        
        //   534: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   537: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   540: astore_1       
        //   541: aload_1        
        //   542: monitorenter   
        //   543: aload_0        
        //   544: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   547: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   550: invokevirtual   java/lang/Object.notify:()V
        //   553: aload_1        
        //   554: monitorexit    
        //   555: ldc             "MediaDecoder2Audio"
        //   557: ldc             "render state flushed"
        //   559: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   562: pop            
        //   563: return         
        //   564: astore          5
        //   566: aload_1        
        //   567: monitorexit    
        //   568: aload           5
        //   570: athrow         
        //   571: astore_1       
        //   572: ldc             "MediaDecoder2Audio"
        //   574: ldc             "mAudioTrack already stopped/uninitialized"
        //   576: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   579: pop            
        //   580: goto            500
        //   583: astore          5
        //   585: aload_1        
        //   586: monitorexit    
        //   587: aload           5
        //   589: athrow         
        //   590: aload_0        
        //   591: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   594: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   597: ifnull          44
        //   600: aload_0        
        //   601: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   604: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   607: iconst_1       
        //   608: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onFlushed:(Z)V
        //   613: return         
        //   614: aconst_null    
        //   615: astore          5
        //   617: aconst_null    
        //   618: astore_1       
        //   619: goto            163
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  55     96     251    258    Any
        //  96     98     251    258    Any
        //  112    163    258    264    Any
        //  163    166    258    264    Any
        //  220    241    244    251    Any
        //  241    243    244    251    Any
        //  246    248    244    251    Any
        //  253    255    251    258    Any
        //  259    262    258    264    Any
        //  271    279    311    340    Ljava/lang/Exception;
        //  340    355    244    251    Any
        //  379    401    433    440    Any
        //  435    437    433    440    Any
        //  468    480    564    571    Any
        //  490    500    571    583    Ljava/lang/IllegalStateException;
        //  543    555    583    590    Any
        //  566    568    564    571    Any
        //  585    587    583    590    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 294, Size: 294
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
