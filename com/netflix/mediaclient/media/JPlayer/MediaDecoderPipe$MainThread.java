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
        //   106: ifeq            2001
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
        //   627: ifne            1989
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
        //   714: ifne            1989
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
        //   776: ifeq            1343
        //   779: iconst_0       
        //   780: istore_3       
        //   781: aload           13
        //   783: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.flags:I
        //   786: lookupswitch {
        //                0: 1181
        //                1: 1184
        //                2: 1189
        //              256: 1194
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
        //   867: ifgt            974
        //   870: aload_0        
        //   871: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   874: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //   877: iconst_3       
        //   878: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   881: ifeq            974
        //   884: aload_0        
        //   885: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   888: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //   891: new             Ljava/lang/StringBuilder;
        //   894: dup            
        //   895: invokespecial   java/lang/StringBuilder.<init>:()V
        //   898: ldc_w           "QueueInput "
        //   901: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   904: iload           4
        //   906: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   909: ldc_w           " from "
        //   912: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   915: aload           13
        //   917: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.offset:I
        //   920: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   923: ldc_w           " size= "
        //   926: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   929: aload           13
        //   931: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
        //   934: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   937: ldc_w           " @"
        //   940: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   943: aload           13
        //   945: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
        //   948: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   951: ldc_w           " ms"
        //   954: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   957: ldc_w           " flags "
        //   960: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   963: iload_3        
        //   964: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   967: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   970: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   973: pop            
        //   974: aload_0        
        //   975: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   978: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   981: ifnull          1099
        //   984: aload           13
        //   986: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
        //   989: aload_0        
        //   990: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //   993: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //   996: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //   999: lcmp           
        //  1000: ifge            1099
        //  1003: aload_0        
        //  1004: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1007: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1010: iconst_3       
        //  1011: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //  1014: ifeq            1099
        //  1017: aload_0        
        //  1018: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1021: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1024: new             Ljava/lang/StringBuilder;
        //  1027: dup            
        //  1028: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1031: ldc_w           "STAT:DEC input late "
        //  1034: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1037: lload           7
        //  1039: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1042: ldc_w           " at "
        //  1045: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1048: aload_0        
        //  1049: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1052: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1055: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //  1058: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1061: ldc_w           " by "
        //  1064: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1067: aload           13
        //  1069: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
        //  1072: aload_0        
        //  1073: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1076: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1079: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //  1082: lsub           
        //  1083: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1086: ldc_w           " ms"
        //  1089: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1092: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1095: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1098: pop            
        //  1099: aload_0        
        //  1100: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1103: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //  1106: iload           4
        //  1108: aload           13
        //  1110: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.offset:I
        //  1113: aload           13
        //  1115: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
        //  1118: aload           13
        //  1120: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.timestamp:J
        //  1123: ldc2_w          1000
        //  1126: lmul           
        //  1127: iload_3        
        //  1128: invokevirtual   android/media/MediaCodec.queueInputBuffer:(IIIJI)V
        //  1131: aload_0        
        //  1132: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1135: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$600:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/util/LinkedList;
        //  1138: invokevirtual   java/util/LinkedList.removeFirst:()Ljava/lang/Object;
        //  1141: pop            
        //  1142: iload_1        
        //  1143: istore_3       
        //  1144: aload_0        
        //  1145: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1148: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
        //  1151: ifne            1236
        //  1154: iload_1        
        //  1155: istore_3       
        //  1156: iload_1        
        //  1157: ifeq            1236
        //  1160: iload_2        
        //  1161: ifeq            1234
        //  1164: aload_0        
        //  1165: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1168: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1171: ldc_w           "Had EOS after flush"
        //  1174: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1177: pop            
        //  1178: goto            398
        //  1181: goto            863
        //  1184: iconst_2       
        //  1185: istore_3       
        //  1186: goto            863
        //  1189: iconst_1       
        //  1190: istore_3       
        //  1191: goto            863
        //  1194: iconst_4       
        //  1195: istore_3       
        //  1196: aload_0        
        //  1197: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1200: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1203: ldc_w           "Had EOS"
        //  1206: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1209: pop            
        //  1210: iconst_1       
        //  1211: istore_2       
        //  1212: goto            863
        //  1215: astore          13
        //  1217: aload_0        
        //  1218: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1221: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1224: ldc_w           "get un-documented exception as a result of queueInputBuffer() "
        //  1227: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1230: pop            
        //  1231: goto            398
        //  1234: iconst_0       
        //  1235: istore_3       
        //  1236: lload           7
        //  1238: lconst_1       
        //  1239: ladd           
        //  1240: lstore          11
        //  1242: iload_2        
        //  1243: istore          4
        //  1245: new             Landroid/media/MediaCodec$BufferInfo;
        //  1248: dup            
        //  1249: invokespecial   android/media/MediaCodec$BufferInfo.<init>:()V
        //  1252: astore          14
        //  1254: aload_0        
        //  1255: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1258: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //  1261: aload           14
        //  1263: lconst_0       
        //  1264: invokevirtual   android/media/MediaCodec.dequeueOutputBuffer:(Landroid/media/MediaCodec$BufferInfo;J)I
        //  1267: istore_1       
        //  1268: iload_1        
        //  1269: iconst_m1      
        //  1270: if_icmpne       1387
        //  1273: lload           5
        //  1275: lstore          9
        //  1277: aload_0        
        //  1278: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1281: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
        //  1284: ifeq            1908
        //  1287: lload           9
        //  1289: lstore          5
        //  1291: lload           11
        //  1293: lstore          7
        //  1295: iload_3        
        //  1296: istore_1       
        //  1297: iload           4
        //  1299: istore_2       
        //  1300: lload           9
        //  1302: aload_0        
        //  1303: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1306: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
        //  1309: iconst_2       
        //  1310: idiv           
        //  1311: i2l            
        //  1312: lcmp           
        //  1313: iflt            65
        //  1316: lload           9
        //  1318: lstore          5
        //  1320: lload           11
        //  1322: lstore          7
        //  1324: iload_3        
        //  1325: istore_1       
        //  1326: iload           4
        //  1328: istore_2       
        //  1329: aload_0        
        //  1330: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1333: iconst_1       
        //  1334: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
        //  1337: ifne            65
        //  1340: goto            398
        //  1343: aload           13
        //  1345: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$InputDataSource$BufferMeta.size:I
        //  1348: ifge            1989
        //  1351: aload_0        
        //  1352: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1355: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1358: ldc_w           "Had error endPlayback"
        //  1361: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1364: pop            
        //  1365: goto            398
        //  1368: astore          13
        //  1370: aload_0        
        //  1371: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1374: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1377: ldc_w           "get un-documented exception as a result of dequeueOutputBuffer() "
        //  1380: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1383: pop            
        //  1384: goto            398
        //  1387: iload_1        
        //  1388: bipush          -3
        //  1390: if_icmpne       1421
        //  1393: aload_0        
        //  1394: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1397: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1400: ldc_w           "OUTPUT_BUFFERS_CHANGED"
        //  1403: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1406: pop            
        //  1407: aload_0        
        //  1408: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1411: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1100:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)V
        //  1414: lload           5
        //  1416: lstore          9
        //  1418: goto            1277
        //  1421: iload_1        
        //  1422: bipush          -2
        //  1424: if_icmpne       1492
        //  1427: aload_0        
        //  1428: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1431: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mDecoder:Landroid/media/MediaCodec;
        //  1434: invokevirtual   android/media/MediaCodec.getOutputFormat:()Landroid/media/MediaFormat;
        //  1437: astore          13
        //  1439: aload_0        
        //  1440: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1443: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1446: iconst_3       
        //  1447: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //  1450: ifeq            1485
        //  1453: aload_0        
        //  1454: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1457: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1460: new             Ljava/lang/StringBuilder;
        //  1463: dup            
        //  1464: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1467: ldc_w           "OUTPUT_FORMAT_CHANGED "
        //  1470: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1473: aload           13
        //  1475: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //  1478: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1481: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1484: pop            
        //  1485: lload           5
        //  1487: lstore          9
        //  1489: goto            1277
        //  1492: iload_1        
        //  1493: iflt            1870
        //  1496: aload_0        
        //  1497: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1500: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //  1503: astore          13
        //  1505: aload           13
        //  1507: monitorenter   
        //  1508: getstatic       com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.$assertionsDisabled:Z
        //  1511: ifne            1541
        //  1514: iload_1        
        //  1515: aload_0        
        //  1516: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1519: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
        //  1522: if_icmplt       1541
        //  1525: new             Ljava/lang/AssertionError;
        //  1528: dup            
        //  1529: invokespecial   java/lang/AssertionError.<init>:()V
        //  1532: athrow         
        //  1533: astore          14
        //  1535: aload           13
        //  1537: monitorexit    
        //  1538: aload           14
        //  1540: athrow         
        //  1541: aload_0        
        //  1542: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1545: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBuffersQ:Ljava/util/LinkedList;
        //  1548: iload_1        
        //  1549: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1552: invokevirtual   java/util/LinkedList.add:(Ljava/lang/Object;)Z
        //  1555: pop            
        //  1556: aload_0        
        //  1557: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1560: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mOutputBufferInfo:[Landroid/media/MediaCodec$BufferInfo;
        //  1563: iload_1        
        //  1564: aload           14
        //  1566: aastore        
        //  1567: aload           13
        //  1569: monitorexit    
        //  1570: lload           5
        //  1572: lconst_0       
        //  1573: lcmp           
        //  1574: ifgt            1660
        //  1577: aload_0        
        //  1578: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1581: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1584: iconst_3       
        //  1585: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //  1588: ifeq            1660
        //  1591: aload_0        
        //  1592: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1595: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1598: new             Ljava/lang/StringBuilder;
        //  1601: dup            
        //  1602: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1605: ldc_w           "DequeueOutputBuffer "
        //  1608: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1611: iload_1        
        //  1612: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1615: ldc_w           " size= "
        //  1618: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1621: aload           14
        //  1623: getfield        android/media/MediaCodec$BufferInfo.size:I
        //  1626: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1629: ldc_w           " @"
        //  1632: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1635: aload           14
        //  1637: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //  1640: ldc2_w          1000
        //  1643: ldiv           
        //  1644: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1647: ldc_w           " ms"
        //  1650: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1653: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1656: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1659: pop            
        //  1660: aload_0        
        //  1661: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1664: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1667: ifnull          1793
        //  1670: aload           14
        //  1672: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //  1675: ldc2_w          1000
        //  1678: ldiv           
        //  1679: aload_0        
        //  1680: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1683: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1686: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //  1689: lcmp           
        //  1690: ifgt            1793
        //  1693: aload_0        
        //  1694: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1697: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1700: iconst_3       
        //  1701: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //  1704: ifeq            1793
        //  1707: aload_0        
        //  1708: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1711: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1714: new             Ljava/lang/StringBuilder;
        //  1717: dup            
        //  1718: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1721: ldc_w           "STAT:DEC output late "
        //  1724: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1727: lload           5
        //  1729: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1732: ldc_w           " at "
        //  1735: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1738: aload_0        
        //  1739: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1742: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1745: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //  1748: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1751: ldc_w           " by "
        //  1754: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1757: aload           14
        //  1759: getfield        android/media/MediaCodec$BufferInfo.presentationTimeUs:J
        //  1762: ldc2_w          1000
        //  1765: ldiv           
        //  1766: aload_0        
        //  1767: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1770: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mRefClock:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock;
        //  1773: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$Clock.get:()J
        //  1776: lsub           
        //  1777: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //  1780: ldc_w           " ms"
        //  1783: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1786: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1789: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1792: pop            
        //  1793: lload           5
        //  1795: lconst_1       
        //  1796: ladd           
        //  1797: lstore          9
        //  1799: aload_0        
        //  1800: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1803: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$1200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)I
        //  1806: iconst_3       
        //  1807: isub           
        //  1808: istore_2       
        //  1809: iload_2        
        //  1810: ifgt            1858
        //  1813: iconst_1       
        //  1814: istore_1       
        //  1815: lload           9
        //  1817: iload_1        
        //  1818: i2l            
        //  1819: lcmp           
        //  1820: ifne            1855
        //  1823: aload_0        
        //  1824: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1827: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
        //  1830: ifnull          1855
        //  1833: aload_0        
        //  1834: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1837: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$200:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Z
        //  1840: ifne            1855
        //  1843: aload_0        
        //  1844: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1847: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mEventListener:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener;
        //  1850: invokeinterface com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$EventListener.onDecoderStarted:()V
        //  1855: goto            1277
        //  1858: iload_2        
        //  1859: istore_1       
        //  1860: iload_2        
        //  1861: iconst_4       
        //  1862: if_icmplt       1815
        //  1865: iconst_4       
        //  1866: istore_1       
        //  1867: goto            1815
        //  1870: aload_0        
        //  1871: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1874: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1877: new             Ljava/lang/StringBuilder;
        //  1880: dup            
        //  1881: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1884: iload_1        
        //  1885: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1888: ldc_w           " is not valid"
        //  1891: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1894: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1897: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //  1900: pop            
        //  1901: lload           5
        //  1903: lstore          9
        //  1905: goto            1277
        //  1908: lload           9
        //  1910: lstore          5
        //  1912: lload           11
        //  1914: lstore          7
        //  1916: iload_3        
        //  1917: istore_1       
        //  1918: iload           4
        //  1920: istore_2       
        //  1921: aload_0        
        //  1922: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1925: iconst_1       
        //  1926: invokevirtual   com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.renderOutput:(Z)Z
        //  1929: ifne            65
        //  1932: goto            398
        //  1935: astore          13
        //  1937: aload_0        
        //  1938: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1941: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.mLock:Ljava/util/concurrent/locks/Lock;
        //  1944: invokeinterface java/util/concurrent/locks/Lock.unlock:()V
        //  1949: aload           13
        //  1951: athrow         
        //  1952: astore          13
        //  1954: aload_0        
        //  1955: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1958: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1961: ldc             "Thead interrupted"
        //  1963: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //  1966: pop            
        //  1967: goto            471
        //  1970: astore          13
        //  1972: aload_0        
        //  1973: getfield        com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe$MainThread.this$0:Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;
        //  1976: invokestatic    com/netflix/mediaclient/media/JPlayer/MediaDecoderPipe.access$500:(Lcom/netflix/mediaclient/media/JPlayer/MediaDecoderPipe;)Ljava/lang/String;
        //  1979: ldc_w           "get un-documented exception as a result of stop/releas() "
        //  1982: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1985: pop            
        //  1986: goto            490
        //  1989: lload           7
        //  1991: lstore          11
        //  1993: iload_1        
        //  1994: istore_3       
        //  1995: iload_2        
        //  1996: istore          4
        //  1998: goto            1245
        //  2001: goto            202
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  10     55     227    258    Ljava/lang/SecurityException;
        //  176    188    591    608    Any
        //  213    224    608    626    Ljava/lang/InterruptedException;
        //  309    319    535    565    Ljava/lang/Exception;
        //  341    354    565    573    Any
        //  441    453    1935   1952   Any
        //  465    471    1952   1970   Ljava/lang/InterruptedException;
        //  480    490    1970   1989   Ljava/lang/Exception;
        //  567    570    565    573    Any
        //  630    642    671    689    Ljava/lang/Exception;
        //  1099   1131   1215   1234   Ljava/lang/Exception;
        //  1254   1268   1368   1387   Ljava/lang/Exception;
        //  1508   1533   1533   1541   Any
        //  1535   1538   1533   1541   Any
        //  1541   1570   1533   1541   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 877, Size: 877
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
