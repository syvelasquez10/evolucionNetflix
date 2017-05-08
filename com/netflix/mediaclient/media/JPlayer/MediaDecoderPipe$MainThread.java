// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import android.annotation.TargetApi;

@TargetApi(16)
class MediaDecoderPipe$MainThread implements Runnable
{
    final /* synthetic */ MediaDecoderPipe this$0;
    
    static {
        $assertionsDisabled = !MediaDecoderPipe.class.desiredAssertionStatus();
    }
    
    private MediaDecoderPipe$MainThread(final MediaDecoderPipe this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //     4: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.isJPlayerThreadConfigured:()Z
        //     7: ifeq            55
        //    10: aload_0        
        //    11: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //    14: ldc             "MediaThreadPriority"
        //    16: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.getThreadPriority:(Ljava/lang/String;)I
        //    19: invokestatic    android/os/Process.setThreadPriority:(I)V
        //    22: ldc             "MediaPipe"
        //    24: new             Ljava/lang/StringBuilder;
        //    27: dup            
        //    28: invokespecial   java/lang/StringBuilder.<init>:()V
        //    31: ldc             "Updating thread priority: "
        //    33: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    36: aload_0        
        //    37: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //    40: ldc             "MediaThreadPriority"
        //    42: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.getThreadPriority:(Ljava/lang/String;)I
        //    45: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    48: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    51: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    54: pop            
        //    55: iconst_0       
        //    56: istore_2       
        //    57: iconst_0       
        //    58: istore_1       
        //    59: lconst_0       
        //    60: lstore          7
        //    62: lconst_0       
        //    63: lstore          5
        //    65: aload_0        
        //    66: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //    69: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
        //    72: ifeq            398
        //    75: aload_0        
        //    76: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //    79: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
        //    82: ifne            99
        //    85: aload_0        
        //    86: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //    89: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mHearbeat:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$DecoderHeartbeat;
        //    92: lload           7
        //    94: lload           5
        //    96: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$DecoderHeartbeat.ShowHearbeat:(JJ)V
        //    99: aload_0        
        //   100: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   103: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
        //   106: ifeq            1927
        //   109: aload_0        
        //   110: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   113: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
        //   116: invokeinterface java/util/concurrent/locks/Lock.lock:()V
        //   121: iconst_1       
        //   122: aload_0        
        //   123: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   126: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
        //   129: if_icmpne       258
        //   132: aload_0        
        //   133: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   136: iconst_2       
        //   137: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
        //   140: pop            
        //   141: aload_0        
        //   142: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   145: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   148: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.pause:()J
        //   151: pop2           
        //   152: aload_0        
        //   153: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   156: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //   159: ldc             "paused"
        //   161: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   164: pop            
        //   165: iload_1        
        //   166: istore_3       
        //   167: aload_0        
        //   168: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   171: iconst_0       
        //   172: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
        //   175: pop            
        //   176: aload_0        
        //   177: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   180: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mCommandDone:Ljava/util/concurrent/locks/Condition;
        //   183: invokeinterface java/util/concurrent/locks/Condition.signal:()V
        //   188: aload_0        
        //   189: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   192: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
        //   195: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
        //   200: iload_3        
        //   201: istore_1       
        //   202: iconst_1       
        //   203: aload_0        
        //   204: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   207: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$400:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
        //   210: if_icmpeq       626
        //   213: aload_0        
        //   214: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   217: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$700:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
        //   220: i2l            
        //   221: invokestatic    java/lang/Thread.sleep:(J)V
        //   224: goto            65
        //   227: astore          13
        //   229: ldc             "MediaPipe"
        //   231: new             Ljava/lang/StringBuilder;
        //   234: dup            
        //   235: invokespecial   java/lang/StringBuilder.<init>:()V
        //   238: ldc             "fail to setPriority "
        //   240: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   243: aload           13
        //   245: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   248: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   251: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   254: pop            
        //   255: goto            55
        //   258: iconst_2       
        //   259: aload_0        
        //   260: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   263: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
        //   266: if_icmpne       296
        //   269: aload_0        
        //   270: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   273: iconst_1       
        //   274: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
        //   277: pop            
        //   278: aload_0        
        //   279: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   282: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //   285: ldc             "unpaused"
        //   287: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   290: pop            
        //   291: iload_1        
        //   292: istore_3       
        //   293: goto            167
        //   296: iload_1        
        //   297: istore_3       
        //   298: iconst_3       
        //   299: aload_0        
        //   300: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   303: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$300:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
        //   306: if_icmpne       167
        //   309: aload_0        
        //   310: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   313: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //   316: invokevirtual   android/media/MediaCodec.flush:()V
        //   319: aload_0        
        //   320: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   323: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
        //   326: invokevirtual   java/util/LinkedList.clear:()V
        //   329: aload_0        
        //   330: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   333: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //   336: astore          13
        //   338: aload           13
        //   340: monitorenter   
        //   341: aload_0        
        //   342: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   345: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //   348: invokevirtual   java/util/LinkedList.clear:()V
        //   351: aload           13
        //   353: monitorexit    
        //   354: aload_0        
        //   355: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   358: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   361: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.flush:()V
        //   364: lconst_0       
        //   365: lstore          7
        //   367: lconst_0       
        //   368: lstore          5
        //   370: aload_0        
        //   371: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   374: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
        //   377: ifne            573
        //   380: iconst_1       
        //   381: istore_1       
        //   382: iload_2        
        //   383: ifeq            573
        //   386: aload_0        
        //   387: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   390: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
        //   393: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
        //   398: aload_0        
        //   399: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   402: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //   405: ldc             "Stopping MainThread now"
        //   407: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   410: pop            
        //   411: aload_0        
        //   412: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   415: iconst_0       
        //   416: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$102:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;Z)Z
        //   419: pop            
        //   420: aload_0        
        //   421: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   424: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
        //   427: invokeinterface java/util/concurrent/locks/Lock.lock:()V
        //   432: aload_0        
        //   433: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   436: iconst_0       
        //   437: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$302:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
        //   440: pop            
        //   441: aload_0        
        //   442: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   445: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mCommandDone:Ljava/util/concurrent/locks/Condition;
        //   448: invokeinterface java/util/concurrent/locks/Condition.signalAll:()V
        //   453: aload_0        
        //   454: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   457: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
        //   460: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
        //   465: ldc2_w          100
        //   468: invokestatic    java/lang/Thread.sleep:(J)V
        //   471: aload_0        
        //   472: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   475: iconst_0       
        //   476: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
        //   479: pop            
        //   480: aload_0        
        //   481: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   484: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //   487: invokevirtual   android/media/MediaCodec.stop:()V
        //   490: aload_0        
        //   491: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   494: iconst_0       
        //   495: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$402:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;I)I
        //   498: pop            
        //   499: aload_0        
        //   500: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   503: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //   506: ldc             "stopped"
        //   508: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   511: pop            
        //   512: aload_0        
        //   513: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   516: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
        //   519: ifnull          534
        //   522: aload_0        
        //   523: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   526: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
        //   529: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener.onStop:()V
        //   534: return         
        //   535: astore          13
        //   537: aload_0        
        //   538: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   541: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //   544: ldc             "get un-documented exception as a result of flush() "
        //   546: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   549: pop            
        //   550: aload_0        
        //   551: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   554: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
        //   557: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
        //   562: goto            398
        //   565: astore          14
        //   567: aload           13
        //   569: monitorexit    
        //   570: aload           14
        //   572: athrow         
        //   573: aload_0        
        //   574: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   577: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //   580: ldc             "flushed"
        //   582: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   585: pop            
        //   586: iload_1        
        //   587: istore_3       
        //   588: goto            167
        //   591: astore          13
        //   593: aload_0        
        //   594: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   597: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
        //   600: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
        //   605: aload           13
        //   607: athrow         
        //   608: astore          13
        //   610: aload_0        
        //   611: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   614: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //   617: ldc             "Thead interrupted"
        //   619: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   622: pop            
        //   623: goto            65
        //   626: iload_2        
        //   627: ifne            1915
        //   630: aload_0        
        //   631: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   634: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //   637: lconst_0       
        //   638: invokevirtual   android/media/MediaCodec.dequeueInputBuffer:(J)I
        //   641: istore_3       
        //   642: iload_3        
        //   643: iflt            704
        //   646: getstatic       com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.$assertionsDisabled:Z
        //   649: ifne            689
        //   652: iload_3        
        //   653: aload_0        
        //   654: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   657: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$800:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
        //   660: if_icmplt       689
        //   663: new             Ljava/lang/AssertionError;
        //   666: dup            
        //   667: invokespecial   java/lang/AssertionError.<init>:()V
        //   670: athrow         
        //   671: astore          13
        //   673: aload_0        
        //   674: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   677: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //   680: ldc             "get un-documented exception as a result of dequeueInputBuffer() "
        //   682: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   685: pop            
        //   686: goto            398
        //   689: aload_0        
        //   690: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   693: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
        //   696: iload_3        
        //   697: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   700: invokevirtual   java/util/LinkedList.add:(Ljava/lang/Object;)Z
        //   703: pop            
        //   704: aload_0        
        //   705: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   708: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
        //   711: invokevirtual   java/util/LinkedList.isEmpty:()Z
        //   714: ifne            1915
        //   717: aload_0        
        //   718: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   721: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
        //   724: invokevirtual   java/util/LinkedList.peekFirst:()Ljava/lang/Object;
        //   727: checkcast       Ljava/lang/Integer;
        //   730: invokevirtual   java/lang/Integer.intValue:()I
        //   733: istore          4
        //   735: aload_0        
        //   736: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   739: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$900:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)[Ljava/nio/ByteBuffer;
        //   742: iload           4
        //   744: aaload         
        //   745: astore          13
        //   747: aload_0        
        //   748: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   751: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1000:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource;
        //   754: aload           13
        //   756: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource.onRequestData:(Ljava/nio/ByteBuffer;)Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta;
        //   761: astore          13
        //   763: aload           13
        //   765: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
        //   768: ifgt            779
        //   771: aload           13
        //   773: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.flags:I
        //   776: ifeq            1321
        //   779: iconst_0       
        //   780: istore_3       
        //   781: aload           13
        //   783: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.flags:I
        //   786: lookupswitch {
        //                0: 1159
        //                1: 1162
        //                2: 1167
        //              256: 1172
        //          default: 828
        //        }
        //   828: aload_0        
        //   829: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   832: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //   835: new             Ljava/lang/StringBuilder;
        //   838: dup            
        //   839: invokespecial   java/lang/StringBuilder.<init>:()V
        //   842: ldc_w           "unknown flag "
        //   845: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   848: aload           13
        //   850: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.flags:I
        //   853: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   856: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   859: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   862: pop            
        //   863: lload           7
        //   865: lconst_0       
        //   866: lcmp           
        //   867: ifgt            960
        //   870: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   873: ifeq            960
        //   876: aload_0        
        //   877: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   880: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //   883: new             Ljava/lang/StringBuilder;
        //   886: dup            
        //   887: invokespecial   java/lang/StringBuilder.<init>:()V
        //   890: ldc_w           "QueueInput "
        //   893: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   896: iload           4
        //   898: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   901: ldc_w           " from "
        //   904: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   907: aload           13
        //   909: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.offset:I
        //   912: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   915: ldc_w           " size= "
        //   918: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   921: aload           13
        //   923: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
        //   926: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   929: ldc_w           " @"
        //   932: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   935: aload           13
        //   937: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
        //   940: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   943: ldc_w           " ms flags "
        //   946: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   949: iload_3        
        //   950: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   953: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   956: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   959: pop            
        //   960: aload_0        
        //   961: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   964: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   967: ifnull          1077
        //   970: aload           13
        //   972: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
        //   975: aload_0        
        //   976: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   979: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   982: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //   985: lcmp           
        //   986: ifge            1077
        //   989: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   992: ifeq            1077
        //   995: aload_0        
        //   996: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   999: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1002: new             Ljava/lang/StringBuilder;
        //  1005: dup            
        //  1006: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1009: ldc_w           "STAT:DEC input late "
        //  1012: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1015: lload           7
        //  1017: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1020: ldc_w           " at "
        //  1023: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1026: aload_0        
        //  1027: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1030: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1033: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //  1036: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1039: ldc_w           " by "
        //  1042: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1045: aload           13
        //  1047: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
        //  1050: aload_0        
        //  1051: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1054: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1057: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //  1060: lsub           
        //  1061: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1064: ldc_w           " ms"
        //  1067: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1070: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1073: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1076: pop            
        //  1077: aload_0        
        //  1078: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1081: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //  1084: iload           4
        //  1086: aload           13
        //  1088: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.offset:I
        //  1091: aload           13
        //  1093: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
        //  1096: aload           13
        //  1098: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
        //  1101: ldc2_w          1000
        //  1104: lmul           
        //  1105: iload_3        
        //  1106: invokevirtual   android/media/MediaCodec.queueInputBuffer:(IIIJI)V
        //  1109: aload_0        
        //  1110: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1113: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
        //  1116: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //  1119: pop            
        //  1120: iload_1        
        //  1121: istore_3       
        //  1122: aload_0        
        //  1123: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1126: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
        //  1129: ifne            1214
        //  1132: iload_1        
        //  1133: istore_3       
        //  1134: iload_1        
        //  1135: ifeq            1214
        //  1138: iload_2        
        //  1139: ifeq            1212
        //  1142: aload_0        
        //  1143: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1146: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1149: ldc_w           "Had EOS after flush"
        //  1152: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1155: pop            
        //  1156: goto            398
        //  1159: goto            863
        //  1162: iconst_2       
        //  1163: istore_3       
        //  1164: goto            863
        //  1167: iconst_1       
        //  1168: istore_3       
        //  1169: goto            863
        //  1172: iconst_4       
        //  1173: istore_3       
        //  1174: aload_0        
        //  1175: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1178: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1181: ldc_w           "Had EOS"
        //  1184: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1187: pop            
        //  1188: iconst_1       
        //  1189: istore_2       
        //  1190: goto            863
        //  1193: astore          13
        //  1195: aload_0        
        //  1196: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1199: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1202: ldc_w           "get un-documented exception as a result of queueInputBuffer() "
        //  1205: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1208: pop            
        //  1209: goto            398
        //  1212: iconst_0       
        //  1213: istore_3       
        //  1214: lload           7
        //  1216: lconst_1       
        //  1217: ladd           
        //  1218: lstore          11
        //  1220: iload_2        
        //  1221: istore          4
        //  1223: new             Landroid/media/MediaCodec$BufferInfo;
        //  1226: dup            
        //  1227: invokespecial   android/media/MediaCodec$BufferInfo.<init>:()V
        //  1230: astore          14
        //  1232: aload_0        
        //  1233: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1236: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //  1239: aload           14
        //  1241: lconst_0       
        //  1242: invokevirtual   android/media/MediaCodec.dequeueOutputBuffer:(Landroid/media/MediaCodec$BufferInfo;J)I
        //  1245: istore_1       
        //  1246: iload_1        
        //  1247: iconst_m1      
        //  1248: if_icmpne       1365
        //  1251: lload           5
        //  1253: lstore          9
        //  1255: aload_0        
        //  1256: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1259: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
        //  1262: ifeq            1834
        //  1265: lload           9
        //  1267: lstore          5
        //  1269: lload           11
        //  1271: lstore          7
        //  1273: iload_3        
        //  1274: istore_1       
        //  1275: iload           4
        //  1277: istore_2       
        //  1278: lload           9
        //  1280: aload_0        
        //  1281: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1284: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
        //  1287: iconst_2       
        //  1288: idiv           
        //  1289: i2l            
        //  1290: lcmp           
        //  1291: iflt            65
        //  1294: lload           9
        //  1296: lstore          5
        //  1298: lload           11
        //  1300: lstore          7
        //  1302: iload_3        
        //  1303: istore_1       
        //  1304: iload           4
        //  1306: istore_2       
        //  1307: aload_0        
        //  1308: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1311: iconst_1       
        //  1312: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
        //  1315: ifne            65
        //  1318: goto            398
        //  1321: aload           13
        //  1323: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
        //  1326: ifge            1915
        //  1329: aload_0        
        //  1330: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1333: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1336: ldc_w           "Had error endPlayback"
        //  1339: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1342: pop            
        //  1343: goto            398
        //  1346: astore          13
        //  1348: aload_0        
        //  1349: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1352: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1355: ldc_w           "get un-documented exception as a result of dequeueOutputBuffer() "
        //  1358: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1361: pop            
        //  1362: goto            398
        //  1365: iload_1        
        //  1366: bipush          -3
        //  1368: if_icmpne       1399
        //  1371: aload_0        
        //  1372: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1375: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1378: ldc_w           "OUTPUT_BUFFERS_CHANGED"
        //  1381: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1384: pop            
        //  1385: aload_0        
        //  1386: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1389: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)V
        //  1392: lload           5
        //  1394: lstore          9
        //  1396: goto            1255
        //  1399: iload_1        
        //  1400: bipush          -2
        //  1402: if_icmpne       1462
        //  1405: aload_0        
        //  1406: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1409: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //  1412: invokevirtual   android/media/MediaCodec.getOutputFormat:()Landroid/media/MediaFormat;
        //  1415: astore          13
        //  1417: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //  1420: ifeq            1455
        //  1423: aload_0        
        //  1424: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1427: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1430: new             Ljava/lang/StringBuilder;
        //  1433: dup            
        //  1434: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1437: ldc_w           "OUTPUT_FORMAT_CHANGED "
        //  1440: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1443: aload           13
        //  1445: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //  1448: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1451: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1454: pop            
        //  1455: lload           5
        //  1457: lstore          9
        //  1459: goto            1255
        //  1462: iload_1        
        //  1463: iflt            1796
        //  1466: aload_0        
        //  1467: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1470: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //  1473: astore          13
        //  1475: aload           13
        //  1477: monitorenter   
        //  1478: getstatic       com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.$assertionsDisabled:Z
        //  1481: ifne            1511
        //  1484: iload_1        
        //  1485: aload_0        
        //  1486: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1489: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
        //  1492: if_icmplt       1511
        //  1495: new             Ljava/lang/AssertionError;
        //  1498: dup            
        //  1499: invokespecial   java/lang/AssertionError.<init>:()V
        //  1502: athrow         
        //  1503: astore          14
        //  1505: aload           13
        //  1507: monitorexit    
        //  1508: aload           14
        //  1510: athrow         
        //  1511: aload_0        
        //  1512: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1515: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //  1518: iload_1        
        //  1519: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1522: invokevirtual   java/util/LinkedList.add:(Ljava/lang/Object;)Z
        //  1525: pop            
        //  1526: aload_0        
        //  1527: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1530: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //  1533: iload_1        
        //  1534: aload           14
        //  1536: aastore        
        //  1537: aload           13
        //  1539: monitorexit    
        //  1540: lload           5
        //  1542: lconst_0       
        //  1543: lcmp           
        //  1544: ifgt            1622
        //  1547: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //  1550: ifeq            1622
        //  1553: aload_0        
        //  1554: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1557: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1560: new             Ljava/lang/StringBuilder;
        //  1563: dup            
        //  1564: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1567: ldc_w           "DequeueOutputBuffer "
        //  1570: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1573: iload_1        
        //  1574: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1577: ldc_w           " size= "
        //  1580: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1583: aload           14
        //  1585: getfield        android/media/MediaCodec$BufferInfo.size:I
        //  1588: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1591: ldc_w           " @"
        //  1594: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1597: aload           14
        //  1599: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //  1602: ldc2_w          1000
        //  1605: ldiv           
        //  1606: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1609: ldc_w           " ms"
        //  1612: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1615: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1618: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1621: pop            
        //  1622: aload_0        
        //  1623: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1626: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1629: ifnull          1747
        //  1632: aload           14
        //  1634: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //  1637: ldc2_w          1000
        //  1640: ldiv           
        //  1641: aload_0        
        //  1642: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1645: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1648: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //  1651: lcmp           
        //  1652: ifgt            1747
        //  1655: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //  1658: ifeq            1747
        //  1661: aload_0        
        //  1662: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1665: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1668: new             Ljava/lang/StringBuilder;
        //  1671: dup            
        //  1672: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1675: ldc_w           "STAT:DEC output late "
        //  1678: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1681: lload           5
        //  1683: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1686: ldc_w           " at "
        //  1689: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1692: aload_0        
        //  1693: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1696: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1699: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //  1702: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1705: ldc_w           " by "
        //  1708: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1711: aload           14
        //  1713: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //  1716: ldc2_w          1000
        //  1719: ldiv           
        //  1720: aload_0        
        //  1721: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1724: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1727: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //  1730: lsub           
        //  1731: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1734: ldc_w           " ms"
        //  1737: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1740: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1743: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1746: pop            
        //  1747: lload           5
        //  1749: lconst_1       
        //  1750: ladd           
        //  1751: lstore          9
        //  1753: lload           9
        //  1755: iconst_1       
        //  1756: i2l            
        //  1757: lcmp           
        //  1758: ifne            1793
        //  1761: aload_0        
        //  1762: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1765: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
        //  1768: ifnull          1793
        //  1771: aload_0        
        //  1772: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1775: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
        //  1778: ifne            1793
        //  1781: aload_0        
        //  1782: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1785: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
        //  1788: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener.onDecoderStarted:()V
        //  1793: goto            1255
        //  1796: aload_0        
        //  1797: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1800: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1803: new             Ljava/lang/StringBuilder;
        //  1806: dup            
        //  1807: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1810: iload_1        
        //  1811: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1814: ldc_w           " is not valid"
        //  1817: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1820: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1823: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //  1826: pop            
        //  1827: lload           5
        //  1829: lstore          9
        //  1831: goto            1255
        //  1834: lload           9
        //  1836: lstore          5
        //  1838: lload           11
        //  1840: lstore          7
        //  1842: iload_3        
        //  1843: istore_1       
        //  1844: iload           4
        //  1846: istore_2       
        //  1847: aload_0        
        //  1848: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1851: iconst_1       
        //  1852: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
        //  1855: ifne            65
        //  1858: goto            398
        //  1861: astore          13
        //  1863: aload_0        
        //  1864: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1867: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
        //  1870: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
        //  1875: aload           13
        //  1877: athrow         
        //  1878: astore          13
        //  1880: aload_0        
        //  1881: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1884: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1887: ldc             "Thead interrupted"
        //  1889: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //  1892: pop            
        //  1893: goto            471
        //  1896: astore          13
        //  1898: aload_0        
        //  1899: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1902: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1905: ldc_w           "get un-documented exception as a result of stop/releas() "
        //  1908: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1911: pop            
        //  1912: goto            490
        //  1915: lload           7
        //  1917: lstore          11
        //  1919: iload_1        
        //  1920: istore_3       
        //  1921: iload_2        
        //  1922: istore          4
        //  1924: goto            1223
        //  1927: goto            202
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  10     55     227    258    Ljava/lang/SecurityException;
        //  176    188    591    608    Any
        //  213    224    608    626    Ljava/lang/InterruptedException;
        //  309    319    535    565    Ljava/lang/Exception;
        //  341    354    565    573    Any
        //  441    453    1861   1878   Any
        //  465    471    1878   1896   Ljava/lang/InterruptedException;
        //  480    490    1896   1915   Ljava/lang/Exception;
        //  567    570    565    573    Any
        //  630    642    671    689    Ljava/lang/Exception;
        //  1077   1109   1193   1212   Ljava/lang/Exception;
        //  1232   1246   1346   1365   Ljava/lang/Exception;
        //  1478   1503   1503   1511   Any
        //  1505   1508   1503   1511   Any
        //  1511   1540   1503   1511   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 837, Size: 837
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
