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
        //                2: 49
        //                3: 442
        //                4: 360
        //                5: 592
        //                6: 616
        //          default: 40
        //        }
        //    40: ldc             "MediaDecoder2Audio"
        //    42: ldc             "RenderThreadAudeo had unknown message"
        //    44: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    47: pop            
        //    48: return         
        //    49: aload_0        
        //    50: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //    53: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //    56: astore_1       
        //    57: aload_1        
        //    58: monitorenter   
        //    59: aload_0        
        //    60: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //    63: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //    66: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier.isPaused:()Z
        //    69: ifeq            100
        //    72: aload_0        
        //    73: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //    76: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //    79: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier.onPlaying:()V
        //    82: aload_0        
        //    83: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //    86: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //    89: invokevirtual   java/lang/Object.notify:()V
        //    92: ldc             "MediaDecoder2Audio"
        //    94: ldc             "render state play"
        //    96: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    99: pop            
        //   100: aload_1        
        //   101: monitorexit    
        //   102: iconst_m1      
        //   103: istore_2       
        //   104: aload_0        
        //   105: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   108: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   111: astore          6
        //   113: aload           6
        //   115: monitorenter   
        //   116: aload_0        
        //   117: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   120: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   123: invokevirtual   java/util/LinkedList.isEmpty:()Z
        //   126: ifne            671
        //   129: aload_0        
        //   130: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   133: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   136: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
        //   139: checkcast       Ljava/lang/Integer;
        //   142: invokevirtual   java/lang/Integer.intValue:()I
        //   145: istore_2       
        //   146: aload_0        
        //   147: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   150: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //   153: iload_2        
        //   154: aaload         
        //   155: astore_1       
        //   156: aload_0        
        //   157: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   160: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffers:[Ljava/nio/ByteBuffer;
        //   163: iload_2        
        //   164: aaload         
        //   165: astore          5
        //   167: aload           6
        //   169: monitorexit    
        //   170: aload_1        
        //   171: ifnull          228
        //   174: aload_1        
        //   175: getfield        android/media/MediaCodec$BufferInfo.flags:I
        //   178: iconst_4       
        //   179: iand           
        //   180: ifeq            228
        //   183: ldc             "MediaDecoder2Audio"
        //   185: ldc             "renderer got buffer BUFFER_FLAG_END_OF_STREAM"
        //   187: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   190: pop            
        //   191: aload_0        
        //   192: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   195: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   198: ifnull          48
        //   201: aload_0        
        //   202: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   205: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   208: iconst_1       
        //   209: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onEndOfStream:(Z)V
        //   214: return         
        //   215: astore          5
        //   217: aload_1        
        //   218: monitorexit    
        //   219: aload           5
        //   221: athrow         
        //   222: astore_1       
        //   223: aload           6
        //   225: monitorexit    
        //   226: aload_1        
        //   227: athrow         
        //   228: aload_0        
        //   229: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   232: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.startRenderer:()V
        //   235: aload_0        
        //   236: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   239: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)J
        //   242: lstore_3       
        //   243: iload_2        
        //   244: iflt            259
        //   247: aload_0        
        //   248: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   251: iload_2        
        //   252: aload_1        
        //   253: aload           5
        //   255: lload_3        
        //   256: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;ILandroid/media/MediaCodec$BufferInfo;Ljava/nio/ByteBuffer;J)V
        //   259: aload_0        
        //   260: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   263: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   266: invokevirtual   java/util/LinkedList.isEmpty:()Z
        //   269: ifeq            102
        //   272: aload_0        
        //   273: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   276: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   279: astore_1       
        //   280: aload_1        
        //   281: monitorenter   
        //   282: aload_0        
        //   283: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   286: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   289: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier.isPlaying:()Z
        //   292: ifne            342
        //   295: ldc             "MediaDecoder2Audio"
        //   297: ldc             "render state is not play"
        //   299: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   302: pop            
        //   303: aload_1        
        //   304: monitorexit    
        //   305: return         
        //   306: astore          5
        //   308: aload_1        
        //   309: monitorexit    
        //   310: aload           5
        //   312: athrow         
        //   313: astore_1       
        //   314: ldc             "MediaDecoder2Audio"
        //   316: new             Ljava/lang/StringBuilder;
        //   319: dup            
        //   320: invokespecial   java/lang/StringBuilder.<init>:()V
        //   323: ldc             "getAudioHeaderPosition() has Exception"
        //   325: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   328: aload_1        
        //   329: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   332: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   335: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   338: pop            
        //   339: goto            272
        //   342: aload_0        
        //   343: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   346: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/os/Handler;
        //   349: iconst_1       
        //   350: ldc2_w          20
        //   353: invokevirtual   android/os/Handler.sendEmptyMessageDelayed:(IJ)Z
        //   356: pop            
        //   357: goto            303
        //   360: aload_0        
        //   361: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   364: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   367: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.pause:()J
        //   370: pop2           
        //   371: aload_0        
        //   372: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   375: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   378: astore_1       
        //   379: aload_1        
        //   380: monitorenter   
        //   381: aload_0        
        //   382: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   385: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   388: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier.onPaused:()V
        //   391: aload_0        
        //   392: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   395: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   398: invokevirtual   java/lang/Object.notify:()V
        //   401: aload_1        
        //   402: monitorexit    
        //   403: ldc             "MediaDecoder2Audio"
        //   405: ldc             "render state pause"
        //   407: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   410: pop            
        //   411: aload_0        
        //   412: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   415: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   418: ifnull          48
        //   421: aload_0        
        //   422: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   425: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   428: iconst_1       
        //   429: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onPasued:(Z)V
        //   434: return         
        //   435: astore          5
        //   437: aload_1        
        //   438: monitorexit    
        //   439: aload           5
        //   441: athrow         
        //   442: ldc             "MediaDecoder2Audio"
        //   444: ldc             "render state flushing"
        //   446: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   449: pop            
        //   450: aload_0        
        //   451: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   454: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock;
        //   457: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$Clock.flush:()V
        //   460: aload_0        
        //   461: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   464: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   467: astore_1       
        //   468: aload_1        
        //   469: monitorenter   
        //   470: aload_0        
        //   471: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   474: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mOutputBuffersQ:Ljava/util/LinkedList;
        //   477: invokevirtual   java/util/LinkedList.clear:()V
        //   480: aload_1        
        //   481: monitorexit    
        //   482: aload_0        
        //   483: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   486: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/media/AudioTrack;
        //   489: ifnull          521
        //   492: aload_0        
        //   493: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   496: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/media/AudioTrack;
        //   499: invokevirtual   android/media/AudioTrack.stop:()V
        //   502: aload_0        
        //   503: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   506: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Landroid/media/AudioTrack;
        //   509: invokevirtual   android/media/AudioTrack.release:()V
        //   512: aload_0        
        //   513: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   516: aconst_null    
        //   517: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;Landroid/media/AudioTrack;)Landroid/media/AudioTrack;
        //   520: pop            
        //   521: aload_0        
        //   522: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   525: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)V
        //   528: aload_0        
        //   529: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   532: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.startRenderer:()V
        //   535: aload_0        
        //   536: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   539: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   542: astore_1       
        //   543: aload_1        
        //   544: monitorenter   
        //   545: aload_0        
        //   546: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   549: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   552: invokevirtual   java/lang/Object.notify:()V
        //   555: aload_1        
        //   556: monitorexit    
        //   557: ldc             "MediaDecoder2Audio"
        //   559: ldc             "render state flushed"
        //   561: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   564: pop            
        //   565: return         
        //   566: astore          5
        //   568: aload_1        
        //   569: monitorexit    
        //   570: aload           5
        //   572: athrow         
        //   573: astore_1       
        //   574: ldc             "MediaDecoder2Audio"
        //   576: ldc             "mAudioTrack already stopped/uninitialized"
        //   578: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   581: pop            
        //   582: goto            502
        //   585: astore          5
        //   587: aload_1        
        //   588: monitorexit    
        //   589: aload           5
        //   591: athrow         
        //   592: aload_0        
        //   593: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   596: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   599: ifnull          48
        //   602: aload_0        
        //   603: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   606: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener;
        //   609: iconst_1       
        //   610: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderBase$EventListener.onFlushed:(Z)V
        //   615: return         
        //   616: aload_0        
        //   617: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   620: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)V
        //   623: aload_0        
        //   624: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   627: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   630: astore_1       
        //   631: aload_1        
        //   632: monitorenter   
        //   633: aload_0        
        //   634: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   637: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   640: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier.onStopped:()V
        //   643: aload_0        
        //   644: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio$1.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;
        //   647: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio.access$000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoder2Audio;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe2$LocalStateNotifier;
        //   650: invokevirtual   java/lang/Object.notify:()V
        //   653: aload_1        
        //   654: monitorexit    
        //   655: ldc             "MediaDecoder2Audio"
        //   657: ldc             "render state stop"
        //   659: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   662: pop            
        //   663: return         
        //   664: astore          5
        //   666: aload_1        
        //   667: monitorexit    
        //   668: aload           5
        //   670: athrow         
        //   671: aconst_null    
        //   672: astore          5
        //   674: aconst_null    
        //   675: astore_1       
        //   676: goto            167
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  59     100    215    222    Any
        //  100    102    215    222    Any
        //  116    167    222    228    Any
        //  167    170    222    228    Any
        //  217    219    215    222    Any
        //  223    226    222    228    Any
        //  235    243    313    342    Ljava/lang/Exception;
        //  282    303    306    313    Any
        //  303    305    306    313    Any
        //  308    310    306    313    Any
        //  342    357    306    313    Any
        //  381    403    435    442    Any
        //  437    439    435    442    Any
        //  470    482    566    573    Any
        //  492    502    573    585    Ljava/lang/IllegalStateException;
        //  545    557    585    592    Any
        //  568    570    566    573    Any
        //  587    589    585    592    Any
        //  633    655    664    671    Any
        //  666    668    664    671    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 323, Size: 323
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
