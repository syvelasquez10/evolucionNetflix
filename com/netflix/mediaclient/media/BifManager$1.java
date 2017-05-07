// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

class BifManager$1 implements Runnable
{
    final /* synthetic */ BifManager this$0;
    
    BifManager$1(final BifManager this$0) {
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
        //     1: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //     4: invokestatic    com/netflix/mediaclient/media/BifManager.access$000:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/ArrayList;
        //     7: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //    10: astore          13
        //    12: aload           13
        //    14: invokeinterface java/util/Iterator.hasNext:()Z
        //    19: ifeq            87
        //    22: aload           13
        //    24: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    29: checkcast       Ljava/lang/String;
        //    32: astore          6
        //    34: ldc             "BifManager"
        //    36: iconst_3       
        //    37: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //    40: ifeq            69
        //    43: ldc             "BifManager"
        //    45: new             Ljava/lang/StringBuilder;
        //    48: dup            
        //    49: invokespecial   java/lang/StringBuilder.<init>:()V
        //    52: ldc             "try url@ "
        //    54: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    57: aload           6
        //    59: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    62: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    65: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    68: pop            
        //    69: aload_0        
        //    70: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //    73: invokestatic    com/netflix/mediaclient/media/BifManager.access$100:(Lcom/netflix/mediaclient/media/BifManager;)Z
        //    76: ifeq            88
        //    79: ldc             "BifManager"
        //    81: ldc             "stopped"
        //    83: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    86: pop            
        //    87: return         
        //    88: aconst_null    
        //    89: astore          8
        //    91: aconst_null    
        //    92: astore          7
        //    94: aconst_null    
        //    95: astore          9
        //    97: aconst_null    
        //    98: astore          11
        //   100: aconst_null    
        //   101: astore          12
        //   103: new             Ljava/net/URL;
        //   106: dup            
        //   107: aload           6
        //   109: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   112: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //   115: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
        //   118: astore          6
        //   120: new             Ljava/io/BufferedInputStream;
        //   123: dup            
        //   124: aload           6
        //   126: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //   129: astore          10
        //   131: aload           12
        //   133: astore          8
        //   135: aload           11
        //   137: astore          9
        //   139: bipush          64
        //   141: newarray        B
        //   143: astore          14
        //   145: aload           12
        //   147: astore          8
        //   149: aload           11
        //   151: astore          9
        //   153: aload           10
        //   155: aload           14
        //   157: iconst_0       
        //   158: bipush          64
        //   160: invokevirtual   java/io/BufferedInputStream.read:([BII)I
        //   163: istore_1       
        //   164: aload           12
        //   166: astore          8
        //   168: aload           11
        //   170: astore          9
        //   172: ldc             "BifManager"
        //   174: iconst_3       
        //   175: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   178: ifeq            219
        //   181: aload           12
        //   183: astore          8
        //   185: aload           11
        //   187: astore          9
        //   189: ldc             "BifManager"
        //   191: new             Ljava/lang/StringBuilder;
        //   194: dup            
        //   195: invokespecial   java/lang/StringBuilder.<init>:()V
        //   198: ldc             "read "
        //   200: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   203: iload_1        
        //   204: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   207: ldc             " bytes"
        //   209: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   212: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   215: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   218: pop            
        //   219: iload_1        
        //   220: bipush          64
        //   222: if_icmpge       322
        //   225: aload           12
        //   227: astore          8
        //   229: aload           11
        //   231: astore          9
        //   233: aload           10
        //   235: invokevirtual   java/io/BufferedInputStream.close:()V
        //   238: aload           12
        //   240: astore          8
        //   242: aload           11
        //   244: astore          9
        //   246: aload           6
        //   248: invokevirtual   java/io/InputStream.close:()V
        //   251: iconst_0       
        //   252: ifeq            263
        //   255: new             Ljava/lang/NullPointerException;
        //   258: dup            
        //   259: invokespecial   java/lang/NullPointerException.<init>:()V
        //   262: athrow         
        //   263: iconst_0       
        //   264: ifeq            275
        //   267: new             Ljava/lang/NullPointerException;
        //   270: dup            
        //   271: invokespecial   java/lang/NullPointerException.<init>:()V
        //   274: athrow         
        //   275: iconst_0       
        //   276: ifeq            287
        //   279: new             Ljava/lang/NullPointerException;
        //   282: dup            
        //   283: invokespecial   java/lang/NullPointerException.<init>:()V
        //   286: athrow         
        //   287: ldc             "BifManager"
        //   289: iconst_3       
        //   290: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   293: ifeq            12
        //   296: ldc             "BifManager"
        //   298: ldc             "bif download complete"
        //   300: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   303: pop            
        //   304: goto            12
        //   307: astore          6
        //   309: ldc             "BifManager"
        //   311: ldc             "Failed downlaod"
        //   313: aload           6
        //   315: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   318: pop            
        //   319: goto            287
        //   322: aload           12
        //   324: astore          8
        //   326: aload           11
        //   328: astore          9
        //   330: aload           14
        //   332: invokestatic    java/nio/ByteBuffer.wrap:([B)Ljava/nio/ByteBuffer;
        //   335: astore          14
        //   337: aload           12
        //   339: astore          8
        //   341: aload           11
        //   343: astore          9
        //   345: aload           14
        //   347: invokestatic    java/nio/ByteOrder.nativeOrder:()Ljava/nio/ByteOrder;
        //   350: invokevirtual   java/nio/ByteBuffer.order:(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;
        //   353: pop            
        //   354: aload           12
        //   356: astore          8
        //   358: aload           11
        //   360: astore          9
        //   362: aload_0        
        //   363: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   366: aload           14
        //   368: bipush          8
        //   370: invokevirtual   java/nio/ByteBuffer.getInt:(I)I
        //   373: invokestatic    com/netflix/mediaclient/media/BifManager.access$202:(Lcom/netflix/mediaclient/media/BifManager;I)I
        //   376: pop            
        //   377: aload           12
        //   379: astore          8
        //   381: aload           11
        //   383: astore          9
        //   385: aload_0        
        //   386: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   389: aload           14
        //   391: bipush          12
        //   393: invokevirtual   java/nio/ByteBuffer.getInt:(I)I
        //   396: invokestatic    com/netflix/mediaclient/media/BifManager.access$302:(Lcom/netflix/mediaclient/media/BifManager;I)I
        //   399: pop            
        //   400: aload           12
        //   402: astore          8
        //   404: aload           11
        //   406: astore          9
        //   408: aload_0        
        //   409: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   412: aload           14
        //   414: bipush          16
        //   416: invokevirtual   java/nio/ByteBuffer.getInt:(I)I
        //   419: invokestatic    com/netflix/mediaclient/media/BifManager.access$402:(Lcom/netflix/mediaclient/media/BifManager;I)I
        //   422: pop            
        //   423: aload           12
        //   425: astore          8
        //   427: aload           11
        //   429: astore          9
        //   431: ldc             "BifManager"
        //   433: iconst_3       
        //   434: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   437: ifeq            509
        //   440: aload           12
        //   442: astore          8
        //   444: aload           11
        //   446: astore          9
        //   448: ldc             "BifManager"
        //   450: new             Ljava/lang/StringBuilder;
        //   453: dup            
        //   454: invokespecial   java/lang/StringBuilder.<init>:()V
        //   457: ldc             "mVersion= "
        //   459: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   462: aload_0        
        //   463: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   466: invokestatic    com/netflix/mediaclient/media/BifManager.access$200:(Lcom/netflix/mediaclient/media/BifManager;)I
        //   469: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   472: ldc             ", mBifCount= "
        //   474: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   477: aload_0        
        //   478: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   481: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
        //   484: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   487: ldc             ",mInterval= "
        //   489: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   492: aload_0        
        //   493: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   496: invokestatic    com/netflix/mediaclient/media/BifManager.access$400:(Lcom/netflix/mediaclient/media/BifManager;)I
        //   499: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   502: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   505: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   508: pop            
        //   509: aload           12
        //   511: astore          8
        //   513: aload           11
        //   515: astore          9
        //   517: aload_0        
        //   518: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   521: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
        //   524: ifle            1013
        //   527: aload           12
        //   529: astore          8
        //   531: aload           11
        //   533: astore          9
        //   535: aload_0        
        //   536: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   539: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
        //   542: sipush          28800
        //   545: if_icmpgt       1013
        //   548: aload           12
        //   550: astore          8
        //   552: aload           11
        //   554: astore          9
        //   556: aload_0        
        //   557: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   560: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
        //   563: iconst_1       
        //   564: iadd           
        //   565: bipush          8
        //   567: imul           
        //   568: istore_3       
        //   569: aload           12
        //   571: astore          8
        //   573: aload           11
        //   575: astore          9
        //   577: aload_0        
        //   578: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   581: iload_3        
        //   582: bipush          64
        //   584: iadd           
        //   585: invokestatic    com/netflix/mediaclient/media/BifManager.access$502:(Lcom/netflix/mediaclient/media/BifManager;I)I
        //   588: pop            
        //   589: aload           12
        //   591: astore          8
        //   593: aload           11
        //   595: astore          9
        //   597: iload_3        
        //   598: newarray        B
        //   600: astore          14
        //   602: aload           12
        //   604: astore          8
        //   606: aload           11
        //   608: astore          9
        //   610: ldc             "BifManager"
        //   612: iconst_3       
        //   613: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   616: ifeq            1821
        //   619: aload           12
        //   621: astore          8
        //   623: aload           11
        //   625: astore          9
        //   627: ldc             "BifManager"
        //   629: new             Ljava/lang/StringBuilder;
        //   632: dup            
        //   633: invokespecial   java/lang/StringBuilder.<init>:()V
        //   636: ldc             "try to read index "
        //   638: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   641: iload_3        
        //   642: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   645: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   648: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   651: pop            
        //   652: goto            1821
        //   655: iload_1        
        //   656: iload_3        
        //   657: if_icmpge       694
        //   660: aload           12
        //   662: astore          8
        //   664: aload           11
        //   666: astore          9
        //   668: aload_0        
        //   669: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   672: invokestatic    com/netflix/mediaclient/media/BifManager.access$100:(Lcom/netflix/mediaclient/media/BifManager;)Z
        //   675: ifeq            1826
        //   678: aload           12
        //   680: astore          8
        //   682: aload           11
        //   684: astore          9
        //   686: ldc             "BifManager"
        //   688: ldc             "stopped"
        //   690: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   693: pop            
        //   694: aload           12
        //   696: astore          8
        //   698: aload           11
        //   700: astore          9
        //   702: aload           14
        //   704: invokestatic    java/nio/ByteBuffer.wrap:([B)Ljava/nio/ByteBuffer;
        //   707: astore          14
        //   709: aload           12
        //   711: astore          8
        //   713: aload           11
        //   715: astore          9
        //   717: aload           14
        //   719: invokestatic    java/nio/ByteOrder.nativeOrder:()Ljava/nio/ByteOrder;
        //   722: invokevirtual   java/nio/ByteBuffer.order:(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;
        //   725: pop            
        //   726: iconst_0       
        //   727: istore_2       
        //   728: iload_2        
        //   729: iload_1        
        //   730: if_icmpge       1075
        //   733: aload           12
        //   735: astore          8
        //   737: aload           11
        //   739: astore          9
        //   741: aload           14
        //   743: invokevirtual   java/nio/ByteBuffer.getInt:()I
        //   746: istore          4
        //   748: aload           12
        //   750: astore          8
        //   752: aload           11
        //   754: astore          9
        //   756: aload           14
        //   758: invokevirtual   java/nio/ByteBuffer.getInt:()I
        //   761: istore          5
        //   763: iload           4
        //   765: istore_3       
        //   766: iload           4
        //   768: iconst_m1      
        //   769: if_icmpne       775
        //   772: ldc             2147483647
        //   774: istore_3       
        //   775: aload           12
        //   777: astore          8
        //   779: aload           11
        //   781: astore          9
        //   783: aload_0        
        //   784: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   787: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
        //   790: iload_3        
        //   791: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   794: iload           5
        //   796: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   799: invokeinterface java/util/SortedMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   804: pop            
        //   805: iload_2        
        //   806: bipush          8
        //   808: iadd           
        //   809: istore_2       
        //   810: goto            728
        //   813: aload           12
        //   815: astore          8
        //   817: aload           11
        //   819: astore          9
        //   821: aload           10
        //   823: aload           14
        //   825: iload_1        
        //   826: iload_2        
        //   827: invokevirtual   java/io/BufferedInputStream.read:([BII)I
        //   830: istore          4
        //   832: iload           4
        //   834: iload_2        
        //   835: if_icmpeq       903
        //   838: aload           12
        //   840: astore          8
        //   842: aload           11
        //   844: astore          9
        //   846: ldc             "BifManager"
        //   848: iconst_3       
        //   849: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   852: ifeq            903
        //   855: aload           12
        //   857: astore          8
        //   859: aload           11
        //   861: astore          9
        //   863: ldc             "BifManager"
        //   865: new             Ljava/lang/StringBuilder;
        //   868: dup            
        //   869: invokespecial   java/lang/StringBuilder.<init>:()V
        //   872: ldc             "attempt to read "
        //   874: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   877: iload_2        
        //   878: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   881: ldc             ", actual "
        //   883: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   886: iload           4
        //   888: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   891: ldc             " bytes"
        //   893: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   896: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   899: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   902: pop            
        //   903: iload           4
        //   905: ifgt            1005
        //   908: aload           12
        //   910: astore          8
        //   912: aload           11
        //   914: astore          9
        //   916: aload           10
        //   918: invokevirtual   java/io/BufferedInputStream.close:()V
        //   921: aload           12
        //   923: astore          8
        //   925: aload           11
        //   927: astore          9
        //   929: aload           6
        //   931: invokevirtual   java/io/InputStream.close:()V
        //   934: iconst_0       
        //   935: ifeq            946
        //   938: new             Ljava/lang/NullPointerException;
        //   941: dup            
        //   942: invokespecial   java/lang/NullPointerException.<init>:()V
        //   945: athrow         
        //   946: iconst_0       
        //   947: ifeq            958
        //   950: new             Ljava/lang/NullPointerException;
        //   953: dup            
        //   954: invokespecial   java/lang/NullPointerException.<init>:()V
        //   957: athrow         
        //   958: iconst_0       
        //   959: ifeq            970
        //   962: new             Ljava/lang/NullPointerException;
        //   965: dup            
        //   966: invokespecial   java/lang/NullPointerException.<init>:()V
        //   969: athrow         
        //   970: ldc             "BifManager"
        //   972: iconst_3       
        //   973: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   976: ifeq            12
        //   979: ldc             "BifManager"
        //   981: ldc             "bif download complete"
        //   983: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   986: pop            
        //   987: goto            12
        //   990: astore          6
        //   992: ldc             "BifManager"
        //   994: ldc             "Failed downlaod"
        //   996: aload           6
        //   998: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //  1001: pop            
        //  1002: goto            970
        //  1005: iload_1        
        //  1006: iload           4
        //  1008: iadd           
        //  1009: istore_1       
        //  1010: goto            655
        //  1013: aload           12
        //  1015: astore          8
        //  1017: aload           11
        //  1019: astore          9
        //  1021: aload_0        
        //  1022: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1025: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
        //  1028: ifgt            1382
        //  1031: aload           12
        //  1033: astore          8
        //  1035: aload           11
        //  1037: astore          9
        //  1039: ldc             "BifManager"
        //  1041: new             Ljava/lang/StringBuilder;
        //  1044: dup            
        //  1045: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1048: ldc             "Index size is not positive, but "
        //  1050: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1053: aload_0        
        //  1054: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1057: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
        //  1060: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1063: ldc             ". Try next IRL if exist"
        //  1065: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1068: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1071: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //  1074: pop            
        //  1075: aload           12
        //  1077: astore          8
        //  1079: aload           11
        //  1081: astore          9
        //  1083: aload_0        
        //  1084: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1087: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
        //  1090: invokeinterface java/util/SortedMap.isEmpty:()Z
        //  1095: ifne            1591
        //  1098: aload           12
        //  1100: astore          8
        //  1102: aload           11
        //  1104: astore          9
        //  1106: aload_0        
        //  1107: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1110: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
        //  1113: aload_0        
        //  1114: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1117: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
        //  1120: invokeinterface java/util/SortedMap.firstKey:()Ljava/lang/Object;
        //  1125: invokeinterface java/util/SortedMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1130: checkcast       Ljava/lang/Integer;
        //  1133: invokevirtual   java/lang/Integer.intValue:()I
        //  1136: istore_3       
        //  1137: aload           12
        //  1139: astore          8
        //  1141: aload           11
        //  1143: astore          9
        //  1145: aload_0        
        //  1146: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1149: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
        //  1152: aload_0        
        //  1153: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1156: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
        //  1159: invokeinterface java/util/SortedMap.lastKey:()Ljava/lang/Object;
        //  1164: invokeinterface java/util/SortedMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1169: checkcast       Ljava/lang/Integer;
        //  1172: invokevirtual   java/lang/Integer.intValue:()I
        //  1175: istore_2       
        //  1176: aload           12
        //  1178: astore          8
        //  1180: aload           11
        //  1182: astore          9
        //  1184: ldc             "BifManager"
        //  1186: iconst_3       
        //  1187: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //  1190: ifeq            1235
        //  1193: aload           12
        //  1195: astore          8
        //  1197: aload           11
        //  1199: astore          9
        //  1201: ldc             "BifManager"
        //  1203: new             Ljava/lang/StringBuilder;
        //  1206: dup            
        //  1207: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1210: ldc             "first offset "
        //  1212: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1215: iload_3        
        //  1216: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1219: ldc             ", end @"
        //  1221: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1224: iload_2        
        //  1225: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1228: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1231: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1234: pop            
        //  1235: aload           12
        //  1237: astore          8
        //  1239: aload           11
        //  1241: astore          9
        //  1243: iload_3        
        //  1244: aload_0        
        //  1245: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1248: invokestatic    com/netflix/mediaclient/media/BifManager.access$500:(Lcom/netflix/mediaclient/media/BifManager;)I
        //  1251: if_icmple       1278
        //  1254: aload           12
        //  1256: astore          8
        //  1258: aload           11
        //  1260: astore          9
        //  1262: aload           10
        //  1264: iload_3        
        //  1265: aload_0        
        //  1266: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1269: invokestatic    com/netflix/mediaclient/media/BifManager.access$500:(Lcom/netflix/mediaclient/media/BifManager;)I
        //  1272: isub           
        //  1273: i2l            
        //  1274: invokevirtual   java/io/BufferedInputStream.skip:(J)J
        //  1277: pop2           
        //  1278: aload           12
        //  1280: astore          8
        //  1282: aload           11
        //  1284: astore          9
        //  1286: aload_0        
        //  1287: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1290: invokestatic    com/netflix/mediaclient/media/BifManager.access$800:(Lcom/netflix/mediaclient/media/BifManager;)Landroid/content/Context;
        //  1293: aload_0        
        //  1294: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1297: invokestatic    com/netflix/mediaclient/media/BifManager.access$700:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/lang/String;
        //  1300: iconst_0       
        //  1301: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
        //  1304: astore          7
        //  1306: aload           7
        //  1308: astore          8
        //  1310: aload           7
        //  1312: astore          9
        //  1314: sipush          1024
        //  1317: newarray        B
        //  1319: astore          11
        //  1321: iconst_0       
        //  1322: istore_1       
        //  1323: iload_1        
        //  1324: iload_2        
        //  1325: iload_3        
        //  1326: isub           
        //  1327: if_icmpge       1519
        //  1330: aload           7
        //  1332: astore          8
        //  1334: aload           7
        //  1336: astore          9
        //  1338: aload           10
        //  1340: aload           11
        //  1342: iconst_0       
        //  1343: sipush          1024
        //  1346: invokevirtual   java/io/BufferedInputStream.read:([BII)I
        //  1349: istore          4
        //  1351: iload           4
        //  1353: ifle            1519
        //  1356: aload           7
        //  1358: astore          8
        //  1360: aload           7
        //  1362: astore          9
        //  1364: aload           7
        //  1366: aload           11
        //  1368: iconst_0       
        //  1369: iload           4
        //  1371: invokevirtual   java/io/FileOutputStream.write:([BII)V
        //  1374: iload_1        
        //  1375: iload           4
        //  1377: iadd           
        //  1378: istore_1       
        //  1379: goto            1323
        //  1382: aload           12
        //  1384: astore          8
        //  1386: aload           11
        //  1388: astore          9
        //  1390: ldc             "BifManager"
        //  1392: new             Ljava/lang/StringBuilder;
        //  1395: dup            
        //  1396: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1399: ldc             "Index size is higher than maximal positibility "
        //  1401: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1404: aload_0        
        //  1405: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1408: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
        //  1411: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1414: ldc             " > "
        //  1416: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1419: sipush          28800
        //  1422: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1425: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1428: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //  1431: pop            
        //  1432: goto            1075
        //  1435: astore          9
        //  1437: aload           6
        //  1439: astore          7
        //  1441: aload           10
        //  1443: astore          6
        //  1445: aload_0        
        //  1446: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1449: iconst_0       
        //  1450: invokestatic    com/netflix/mediaclient/media/BifManager.access$902:(Lcom/netflix/mediaclient/media/BifManager;Z)Z
        //  1453: pop            
        //  1454: ldc             "BifManager"
        //  1456: ldc             "Failed downlaod"
        //  1458: aload           9
        //  1460: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //  1463: pop            
        //  1464: aload           8
        //  1466: ifnull          1479
        //  1469: aload           8
        //  1471: invokevirtual   java/io/FileOutputStream.flush:()V
        //  1474: aload           8
        //  1476: invokevirtual   java/io/FileOutputStream.close:()V
        //  1479: aload           7
        //  1481: ifnull          1489
        //  1484: aload           7
        //  1486: invokevirtual   java/io/InputStream.close:()V
        //  1489: aload           6
        //  1491: ifnull          1499
        //  1494: aload           6
        //  1496: invokevirtual   java/io/BufferedInputStream.close:()V
        //  1499: ldc             "BifManager"
        //  1501: iconst_3       
        //  1502: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //  1505: ifeq            12
        //  1508: ldc             "BifManager"
        //  1510: ldc             "bif download complete"
        //  1512: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1515: pop            
        //  1516: goto            12
        //  1519: aload           7
        //  1521: astore          8
        //  1523: aload           7
        //  1525: astore          9
        //  1527: ldc             "BifManager"
        //  1529: iconst_3       
        //  1530: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //  1533: ifeq            1574
        //  1536: aload           7
        //  1538: astore          8
        //  1540: aload           7
        //  1542: astore          9
        //  1544: ldc             "BifManager"
        //  1546: new             Ljava/lang/StringBuilder;
        //  1549: dup            
        //  1550: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1553: ldc             "read "
        //  1555: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1558: iload_1        
        //  1559: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1562: ldc             " bytes"
        //  1564: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1567: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1570: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1573: pop            
        //  1574: aload           7
        //  1576: astore          8
        //  1578: aload           7
        //  1580: astore          9
        //  1582: aload_0        
        //  1583: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1586: iconst_1       
        //  1587: invokestatic    com/netflix/mediaclient/media/BifManager.access$902:(Lcom/netflix/mediaclient/media/BifManager;Z)Z
        //  1590: pop            
        //  1591: aload           7
        //  1593: ifnull          1606
        //  1596: aload           7
        //  1598: invokevirtual   java/io/FileOutputStream.flush:()V
        //  1601: aload           7
        //  1603: invokevirtual   java/io/FileOutputStream.close:()V
        //  1606: aload           6
        //  1608: ifnull          1616
        //  1611: aload           6
        //  1613: invokevirtual   java/io/InputStream.close:()V
        //  1616: aload           10
        //  1618: ifnull          1626
        //  1621: aload           10
        //  1623: invokevirtual   java/io/BufferedInputStream.close:()V
        //  1626: ldc             "BifManager"
        //  1628: iconst_3       
        //  1629: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //  1632: ifeq            87
        //  1635: ldc             "BifManager"
        //  1637: ldc             "bif download complete"
        //  1639: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1642: pop            
        //  1643: return         
        //  1644: astore          6
        //  1646: ldc             "BifManager"
        //  1648: ldc             "Failed downlaod"
        //  1650: aload           6
        //  1652: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //  1655: pop            
        //  1656: goto            1626
        //  1659: astore          6
        //  1661: ldc             "BifManager"
        //  1663: ldc             "Failed downlaod"
        //  1665: aload           6
        //  1667: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //  1670: pop            
        //  1671: goto            1499
        //  1674: astore          7
        //  1676: aconst_null    
        //  1677: astore          6
        //  1679: aconst_null    
        //  1680: astore          8
        //  1682: aload           9
        //  1684: ifnull          1697
        //  1687: aload           9
        //  1689: invokevirtual   java/io/FileOutputStream.flush:()V
        //  1692: aload           9
        //  1694: invokevirtual   java/io/FileOutputStream.close:()V
        //  1697: aload           6
        //  1699: ifnull          1707
        //  1702: aload           6
        //  1704: invokevirtual   java/io/InputStream.close:()V
        //  1707: aload           8
        //  1709: ifnull          1717
        //  1712: aload           8
        //  1714: invokevirtual   java/io/BufferedInputStream.close:()V
        //  1717: ldc             "BifManager"
        //  1719: iconst_3       
        //  1720: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //  1723: ifeq            1734
        //  1726: ldc             "BifManager"
        //  1728: ldc             "bif download complete"
        //  1730: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1733: pop            
        //  1734: aload           7
        //  1736: athrow         
        //  1737: astore          6
        //  1739: ldc             "BifManager"
        //  1741: ldc             "Failed downlaod"
        //  1743: aload           6
        //  1745: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //  1748: pop            
        //  1749: goto            1717
        //  1752: astore          7
        //  1754: aconst_null    
        //  1755: astore          8
        //  1757: goto            1682
        //  1760: astore          7
        //  1762: aload           10
        //  1764: astore          8
        //  1766: goto            1682
        //  1769: astore          9
        //  1771: aload           7
        //  1773: astore          10
        //  1775: aload           9
        //  1777: astore          7
        //  1779: aload           8
        //  1781: astore          9
        //  1783: aload           6
        //  1785: astore          8
        //  1787: aload           10
        //  1789: astore          6
        //  1791: goto            1682
        //  1794: astore          9
        //  1796: aconst_null    
        //  1797: astore          7
        //  1799: aconst_null    
        //  1800: astore          6
        //  1802: goto            1445
        //  1805: astore          9
        //  1807: aconst_null    
        //  1808: astore          10
        //  1810: aload           6
        //  1812: astore          7
        //  1814: aload           10
        //  1816: astore          6
        //  1818: goto            1445
        //  1821: iconst_0       
        //  1822: istore_1       
        //  1823: goto            655
        //  1826: sipush          1024
        //  1829: istore_2       
        //  1830: iload_3        
        //  1831: iload_1        
        //  1832: isub           
        //  1833: sipush          1024
        //  1836: if_icmpge       813
        //  1839: iload_3        
        //  1840: iload_1        
        //  1841: isub           
        //  1842: istore_2       
        //  1843: goto            813
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  103    120    1794   1805   Ljava/io/IOException;
        //  103    120    1674   1682   Any
        //  120    131    1805   1821   Ljava/io/IOException;
        //  120    131    1752   1760   Any
        //  139    145    1435   1445   Ljava/io/IOException;
        //  139    145    1760   1769   Any
        //  153    164    1435   1445   Ljava/io/IOException;
        //  153    164    1760   1769   Any
        //  172    181    1435   1445   Ljava/io/IOException;
        //  172    181    1760   1769   Any
        //  189    219    1435   1445   Ljava/io/IOException;
        //  189    219    1760   1769   Any
        //  233    238    1435   1445   Ljava/io/IOException;
        //  233    238    1760   1769   Any
        //  246    251    1435   1445   Ljava/io/IOException;
        //  246    251    1760   1769   Any
        //  255    263    307    322    Ljava/io/IOException;
        //  267    275    307    322    Ljava/io/IOException;
        //  279    287    307    322    Ljava/io/IOException;
        //  330    337    1435   1445   Ljava/io/IOException;
        //  330    337    1760   1769   Any
        //  345    354    1435   1445   Ljava/io/IOException;
        //  345    354    1760   1769   Any
        //  362    377    1435   1445   Ljava/io/IOException;
        //  362    377    1760   1769   Any
        //  385    400    1435   1445   Ljava/io/IOException;
        //  385    400    1760   1769   Any
        //  408    423    1435   1445   Ljava/io/IOException;
        //  408    423    1760   1769   Any
        //  431    440    1435   1445   Ljava/io/IOException;
        //  431    440    1760   1769   Any
        //  448    509    1435   1445   Ljava/io/IOException;
        //  448    509    1760   1769   Any
        //  517    527    1435   1445   Ljava/io/IOException;
        //  517    527    1760   1769   Any
        //  535    548    1435   1445   Ljava/io/IOException;
        //  535    548    1760   1769   Any
        //  556    569    1435   1445   Ljava/io/IOException;
        //  556    569    1760   1769   Any
        //  577    589    1435   1445   Ljava/io/IOException;
        //  577    589    1760   1769   Any
        //  597    602    1435   1445   Ljava/io/IOException;
        //  597    602    1760   1769   Any
        //  610    619    1435   1445   Ljava/io/IOException;
        //  610    619    1760   1769   Any
        //  627    652    1435   1445   Ljava/io/IOException;
        //  627    652    1760   1769   Any
        //  668    678    1435   1445   Ljava/io/IOException;
        //  668    678    1760   1769   Any
        //  686    694    1435   1445   Ljava/io/IOException;
        //  686    694    1760   1769   Any
        //  702    709    1435   1445   Ljava/io/IOException;
        //  702    709    1760   1769   Any
        //  717    726    1435   1445   Ljava/io/IOException;
        //  717    726    1760   1769   Any
        //  741    748    1435   1445   Ljava/io/IOException;
        //  741    748    1760   1769   Any
        //  756    763    1435   1445   Ljava/io/IOException;
        //  756    763    1760   1769   Any
        //  783    805    1435   1445   Ljava/io/IOException;
        //  783    805    1760   1769   Any
        //  821    832    1435   1445   Ljava/io/IOException;
        //  821    832    1760   1769   Any
        //  846    855    1435   1445   Ljava/io/IOException;
        //  846    855    1760   1769   Any
        //  863    903    1435   1445   Ljava/io/IOException;
        //  863    903    1760   1769   Any
        //  916    921    1435   1445   Ljava/io/IOException;
        //  916    921    1760   1769   Any
        //  929    934    1435   1445   Ljava/io/IOException;
        //  929    934    1760   1769   Any
        //  938    946    990    1005   Ljava/io/IOException;
        //  950    958    990    1005   Ljava/io/IOException;
        //  962    970    990    1005   Ljava/io/IOException;
        //  1021   1031   1435   1445   Ljava/io/IOException;
        //  1021   1031   1760   1769   Any
        //  1039   1075   1435   1445   Ljava/io/IOException;
        //  1039   1075   1760   1769   Any
        //  1083   1098   1435   1445   Ljava/io/IOException;
        //  1083   1098   1760   1769   Any
        //  1106   1137   1435   1445   Ljava/io/IOException;
        //  1106   1137   1760   1769   Any
        //  1145   1176   1435   1445   Ljava/io/IOException;
        //  1145   1176   1760   1769   Any
        //  1184   1193   1435   1445   Ljava/io/IOException;
        //  1184   1193   1760   1769   Any
        //  1201   1235   1435   1445   Ljava/io/IOException;
        //  1201   1235   1760   1769   Any
        //  1243   1254   1435   1445   Ljava/io/IOException;
        //  1243   1254   1760   1769   Any
        //  1262   1278   1435   1445   Ljava/io/IOException;
        //  1262   1278   1760   1769   Any
        //  1286   1306   1435   1445   Ljava/io/IOException;
        //  1286   1306   1760   1769   Any
        //  1314   1321   1435   1445   Ljava/io/IOException;
        //  1314   1321   1760   1769   Any
        //  1338   1351   1435   1445   Ljava/io/IOException;
        //  1338   1351   1760   1769   Any
        //  1364   1374   1435   1445   Ljava/io/IOException;
        //  1364   1374   1760   1769   Any
        //  1390   1432   1435   1445   Ljava/io/IOException;
        //  1390   1432   1760   1769   Any
        //  1445   1464   1769   1794   Any
        //  1469   1479   1659   1674   Ljava/io/IOException;
        //  1484   1489   1659   1674   Ljava/io/IOException;
        //  1494   1499   1659   1674   Ljava/io/IOException;
        //  1527   1536   1435   1445   Ljava/io/IOException;
        //  1527   1536   1760   1769   Any
        //  1544   1574   1435   1445   Ljava/io/IOException;
        //  1544   1574   1760   1769   Any
        //  1582   1591   1435   1445   Ljava/io/IOException;
        //  1582   1591   1760   1769   Any
        //  1596   1606   1644   1659   Ljava/io/IOException;
        //  1611   1616   1644   1659   Ljava/io/IOException;
        //  1621   1626   1644   1659   Ljava/io/IOException;
        //  1687   1697   1737   1752   Ljava/io/IOException;
        //  1702   1707   1737   1752   Ljava/io/IOException;
        //  1712   1717   1737   1752   Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 862, Size: 862
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
