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
        //   106: ifeq            1933
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
        //   627: ifne            1921
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
        //   714: ifne            1921
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
        //   776: ifeq            1327
        //   779: iconst_0       
        //   780: istore_3       
        //   781: aload           13
        //   783: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.flags:I
        //   786: lookupswitch {
        //                0: 1165
        //                1: 1168
        //                2: 1173
        //              256: 1178
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
        //   867: ifgt            966
        //   870: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   873: ifeq            966
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
        //   943: ldc_w           " ms"
        //   946: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   949: ldc_w           " flags "
        //   952: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   955: iload_3        
        //   956: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   959: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   962: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   965: pop            
        //   966: aload_0        
        //   967: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   970: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   973: ifnull          1083
        //   976: aload           13
        //   978: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
        //   981: aload_0        
        //   982: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   985: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   988: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //   991: lcmp           
        //   992: ifge            1083
        //   995: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   998: ifeq            1083
        //  1001: aload_0        
        //  1002: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1005: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1008: new             Ljava/lang/StringBuilder;
        //  1011: dup            
        //  1012: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1015: ldc_w           "STAT:DEC input late "
        //  1018: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1021: lload           7
        //  1023: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1026: ldc_w           " at "
        //  1029: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1032: aload_0        
        //  1033: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1036: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1039: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //  1042: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1045: ldc_w           " by "
        //  1048: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1051: aload           13
        //  1053: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
        //  1056: aload_0        
        //  1057: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1060: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1063: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //  1066: lsub           
        //  1067: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1070: ldc_w           " ms"
        //  1073: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1076: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1079: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1082: pop            
        //  1083: aload_0        
        //  1084: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1087: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //  1090: iload           4
        //  1092: aload           13
        //  1094: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.offset:I
        //  1097: aload           13
        //  1099: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
        //  1102: aload           13
        //  1104: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
        //  1107: ldc2_w          1000
        //  1110: lmul           
        //  1111: iload_3        
        //  1112: invokevirtual   android/media/MediaCodec.queueInputBuffer:(IIIJI)V
        //  1115: aload_0        
        //  1116: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1119: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
        //  1122: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //  1125: pop            
        //  1126: iload_1        
        //  1127: istore_3       
        //  1128: aload_0        
        //  1129: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1132: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
        //  1135: ifne            1220
        //  1138: iload_1        
        //  1139: istore_3       
        //  1140: iload_1        
        //  1141: ifeq            1220
        //  1144: iload_2        
        //  1145: ifeq            1218
        //  1148: aload_0        
        //  1149: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1152: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1155: ldc_w           "Had EOS after flush"
        //  1158: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1161: pop            
        //  1162: goto            398
        //  1165: goto            863
        //  1168: iconst_2       
        //  1169: istore_3       
        //  1170: goto            863
        //  1173: iconst_1       
        //  1174: istore_3       
        //  1175: goto            863
        //  1178: iconst_4       
        //  1179: istore_3       
        //  1180: aload_0        
        //  1181: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1184: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1187: ldc_w           "Had EOS"
        //  1190: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1193: pop            
        //  1194: iconst_1       
        //  1195: istore_2       
        //  1196: goto            863
        //  1199: astore          13
        //  1201: aload_0        
        //  1202: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1205: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1208: ldc_w           "get un-documented exception as a result of queueInputBuffer() "
        //  1211: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1214: pop            
        //  1215: goto            398
        //  1218: iconst_0       
        //  1219: istore_3       
        //  1220: lload           7
        //  1222: lconst_1       
        //  1223: ladd           
        //  1224: lstore          11
        //  1226: iload_2        
        //  1227: istore          4
        //  1229: new             Landroid/media/MediaCodec$BufferInfo;
        //  1232: dup            
        //  1233: invokespecial   android/media/MediaCodec$BufferInfo.<init>:()V
        //  1236: astore          14
        //  1238: aload_0        
        //  1239: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1242: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //  1245: aload           14
        //  1247: lconst_0       
        //  1248: invokevirtual   android/media/MediaCodec.dequeueOutputBuffer:(Landroid/media/MediaCodec$BufferInfo;J)I
        //  1251: istore_1       
        //  1252: iload_1        
        //  1253: iconst_m1      
        //  1254: if_icmpne       1371
        //  1257: lload           5
        //  1259: lstore          9
        //  1261: aload_0        
        //  1262: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1265: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
        //  1268: ifeq            1840
        //  1271: lload           9
        //  1273: lstore          5
        //  1275: lload           11
        //  1277: lstore          7
        //  1279: iload_3        
        //  1280: istore_1       
        //  1281: iload           4
        //  1283: istore_2       
        //  1284: lload           9
        //  1286: aload_0        
        //  1287: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1290: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
        //  1293: iconst_2       
        //  1294: idiv           
        //  1295: i2l            
        //  1296: lcmp           
        //  1297: iflt            65
        //  1300: lload           9
        //  1302: lstore          5
        //  1304: lload           11
        //  1306: lstore          7
        //  1308: iload_3        
        //  1309: istore_1       
        //  1310: iload           4
        //  1312: istore_2       
        //  1313: aload_0        
        //  1314: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1317: iconst_1       
        //  1318: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
        //  1321: ifne            65
        //  1324: goto            398
        //  1327: aload           13
        //  1329: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
        //  1332: ifge            1921
        //  1335: aload_0        
        //  1336: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1339: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1342: ldc_w           "Had error endPlayback"
        //  1345: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1348: pop            
        //  1349: goto            398
        //  1352: astore          13
        //  1354: aload_0        
        //  1355: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1358: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1361: ldc_w           "get un-documented exception as a result of dequeueOutputBuffer() "
        //  1364: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1367: pop            
        //  1368: goto            398
        //  1371: iload_1        
        //  1372: bipush          -3
        //  1374: if_icmpne       1405
        //  1377: aload_0        
        //  1378: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1381: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1384: ldc_w           "OUTPUT_BUFFERS_CHANGED"
        //  1387: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1390: pop            
        //  1391: aload_0        
        //  1392: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1395: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)V
        //  1398: lload           5
        //  1400: lstore          9
        //  1402: goto            1261
        //  1405: iload_1        
        //  1406: bipush          -2
        //  1408: if_icmpne       1468
        //  1411: aload_0        
        //  1412: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1415: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //  1418: invokevirtual   android/media/MediaCodec.getOutputFormat:()Landroid/media/MediaFormat;
        //  1421: astore          13
        //  1423: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //  1426: ifeq            1461
        //  1429: aload_0        
        //  1430: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1433: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1436: new             Ljava/lang/StringBuilder;
        //  1439: dup            
        //  1440: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1443: ldc_w           "OUTPUT_FORMAT_CHANGED "
        //  1446: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1449: aload           13
        //  1451: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //  1454: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1457: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1460: pop            
        //  1461: lload           5
        //  1463: lstore          9
        //  1465: goto            1261
        //  1468: iload_1        
        //  1469: iflt            1802
        //  1472: aload_0        
        //  1473: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1476: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //  1479: astore          13
        //  1481: aload           13
        //  1483: monitorenter   
        //  1484: getstatic       com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.$assertionsDisabled:Z
        //  1487: ifne            1517
        //  1490: iload_1        
        //  1491: aload_0        
        //  1492: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1495: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
        //  1498: if_icmplt       1517
        //  1501: new             Ljava/lang/AssertionError;
        //  1504: dup            
        //  1505: invokespecial   java/lang/AssertionError.<init>:()V
        //  1508: athrow         
        //  1509: astore          14
        //  1511: aload           13
        //  1513: monitorexit    
        //  1514: aload           14
        //  1516: athrow         
        //  1517: aload_0        
        //  1518: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1521: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //  1524: iload_1        
        //  1525: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1528: invokevirtual   java/util/LinkedList.add:(Ljava/lang/Object;)Z
        //  1531: pop            
        //  1532: aload_0        
        //  1533: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1536: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //  1539: iload_1        
        //  1540: aload           14
        //  1542: aastore        
        //  1543: aload           13
        //  1545: monitorexit    
        //  1546: lload           5
        //  1548: lconst_0       
        //  1549: lcmp           
        //  1550: ifgt            1628
        //  1553: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //  1556: ifeq            1628
        //  1559: aload_0        
        //  1560: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1563: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1566: new             Ljava/lang/StringBuilder;
        //  1569: dup            
        //  1570: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1573: ldc_w           "DequeueOutputBuffer "
        //  1576: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1579: iload_1        
        //  1580: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1583: ldc_w           " size= "
        //  1586: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1589: aload           14
        //  1591: getfield        android/media/MediaCodec$BufferInfo.size:I
        //  1594: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1597: ldc_w           " @"
        //  1600: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1603: aload           14
        //  1605: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //  1608: ldc2_w          1000
        //  1611: ldiv           
        //  1612: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1615: ldc_w           " ms"
        //  1618: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1621: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1624: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1627: pop            
        //  1628: aload_0        
        //  1629: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1632: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1635: ifnull          1753
        //  1638: aload           14
        //  1640: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //  1643: ldc2_w          1000
        //  1646: ldiv           
        //  1647: aload_0        
        //  1648: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1651: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1654: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //  1657: lcmp           
        //  1658: ifgt            1753
        //  1661: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //  1664: ifeq            1753
        //  1667: aload_0        
        //  1668: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1671: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1674: new             Ljava/lang/StringBuilder;
        //  1677: dup            
        //  1678: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1681: ldc_w           "STAT:DEC output late "
        //  1684: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1687: lload           5
        //  1689: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1692: ldc_w           " at "
        //  1695: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1698: aload_0        
        //  1699: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1702: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1705: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //  1708: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1711: ldc_w           " by "
        //  1714: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1717: aload           14
        //  1719: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //  1722: ldc2_w          1000
        //  1725: ldiv           
        //  1726: aload_0        
        //  1727: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1730: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1733: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //  1736: lsub           
        //  1737: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1740: ldc_w           " ms"
        //  1743: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1746: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1749: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1752: pop            
        //  1753: lload           5
        //  1755: lconst_1       
        //  1756: ladd           
        //  1757: lstore          9
        //  1759: lload           9
        //  1761: iconst_1       
        //  1762: i2l            
        //  1763: lcmp           
        //  1764: ifne            1799
        //  1767: aload_0        
        //  1768: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1771: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
        //  1774: ifnull          1799
        //  1777: aload_0        
        //  1778: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1781: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
        //  1784: ifne            1799
        //  1787: aload_0        
        //  1788: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1791: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
        //  1794: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener.onDecoderStarted:()V
        //  1799: goto            1261
        //  1802: aload_0        
        //  1803: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1806: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1809: new             Ljava/lang/StringBuilder;
        //  1812: dup            
        //  1813: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1816: iload_1        
        //  1817: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1820: ldc_w           " is not valid"
        //  1823: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1826: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1829: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //  1832: pop            
        //  1833: lload           5
        //  1835: lstore          9
        //  1837: goto            1261
        //  1840: lload           9
        //  1842: lstore          5
        //  1844: lload           11
        //  1846: lstore          7
        //  1848: iload_3        
        //  1849: istore_1       
        //  1850: iload           4
        //  1852: istore_2       
        //  1853: aload_0        
        //  1854: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1857: iconst_1       
        //  1858: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
        //  1861: ifne            65
        //  1864: goto            398
        //  1867: astore          13
        //  1869: aload_0        
        //  1870: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1873: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
        //  1876: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
        //  1881: aload           13
        //  1883: athrow         
        //  1884: astore          13
        //  1886: aload_0        
        //  1887: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1890: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1893: ldc             "Thead interrupted"
        //  1895: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //  1898: pop            
        //  1899: goto            471
        //  1902: astore          13
        //  1904: aload_0        
        //  1905: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1908: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1911: ldc_w           "get un-documented exception as a result of stop/releas() "
        //  1914: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1917: pop            
        //  1918: goto            490
        //  1921: lload           7
        //  1923: lstore          11
        //  1925: iload_1        
        //  1926: istore_3       
        //  1927: iload_2        
        //  1928: istore          4
        //  1930: goto            1229
        //  1933: goto            202
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  10     55     227    258    Ljava/lang/SecurityException;
        //  176    188    591    608    Any
        //  213    224    608    626    Ljava/lang/InterruptedException;
        //  309    319    535    565    Ljava/lang/Exception;
        //  341    354    565    573    Any
        //  441    453    1867   1884   Any
        //  465    471    1884   1902   Ljava/lang/InterruptedException;
        //  480    490    1902   1921   Ljava/lang/Exception;
        //  567    570    565    573    Any
        //  630    642    671    689    Ljava/lang/Exception;
        //  1083   1115   1199   1218   Ljava/lang/Exception;
        //  1238   1252   1352   1371   Ljava/lang/Exception;
        //  1484   1509   1509   1517   Any
        //  1511   1514   1509   1517   Any
        //  1517   1546   1509   1517   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 839, Size: 839
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
