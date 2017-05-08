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
        //    19: ifeq            84
        //    22: aload           13
        //    24: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    29: checkcast       Ljava/lang/String;
        //    32: astore          6
        //    34: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    37: ifeq            66
        //    40: ldc             "BifManager"
        //    42: new             Ljava/lang/StringBuilder;
        //    45: dup            
        //    46: invokespecial   java/lang/StringBuilder.<init>:()V
        //    49: ldc             "try url@ "
        //    51: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    54: aload           6
        //    56: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    59: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    62: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    65: pop            
        //    66: aload_0        
        //    67: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //    70: invokestatic    com/netflix/mediaclient/media/BifManager.access$100:(Lcom/netflix/mediaclient/media/BifManager;)Z
        //    73: ifeq            85
        //    76: ldc             "BifManager"
        //    78: ldc             "stopped"
        //    80: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    83: pop            
        //    84: return         
        //    85: aconst_null    
        //    86: astore          8
        //    88: aconst_null    
        //    89: astore          7
        //    91: aconst_null    
        //    92: astore          9
        //    94: aconst_null    
        //    95: astore          12
        //    97: aconst_null    
        //    98: astore          11
        //   100: new             Ljava/net/URL;
        //   103: dup            
        //   104: aload           6
        //   106: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   109: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //   112: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
        //   115: astore          6
        //   117: new             Ljava/io/BufferedInputStream;
        //   120: dup            
        //   121: aload           6
        //   123: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //   126: astore          10
        //   128: aload           11
        //   130: astore          8
        //   132: aload           12
        //   134: astore          9
        //   136: bipush          64
        //   138: newarray        B
        //   140: astore          14
        //   142: aload           11
        //   144: astore          8
        //   146: aload           12
        //   148: astore          9
        //   150: aload           10
        //   152: aload           14
        //   154: iconst_0       
        //   155: bipush          64
        //   157: invokevirtual   java/io/BufferedInputStream.read:([BII)I
        //   160: istore_1       
        //   161: aload           11
        //   163: astore          8
        //   165: aload           12
        //   167: astore          9
        //   169: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   172: ifeq            213
        //   175: aload           11
        //   177: astore          8
        //   179: aload           12
        //   181: astore          9
        //   183: ldc             "BifManager"
        //   185: new             Ljava/lang/StringBuilder;
        //   188: dup            
        //   189: invokespecial   java/lang/StringBuilder.<init>:()V
        //   192: ldc             "read "
        //   194: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   197: iload_1        
        //   198: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   201: ldc             " bytes"
        //   203: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   206: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   209: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   212: pop            
        //   213: iload_1        
        //   214: bipush          64
        //   216: if_icmpge       313
        //   219: aload           11
        //   221: astore          8
        //   223: aload           12
        //   225: astore          9
        //   227: aload           10
        //   229: invokevirtual   java/io/BufferedInputStream.close:()V
        //   232: aload           11
        //   234: astore          8
        //   236: aload           12
        //   238: astore          9
        //   240: aload           6
        //   242: invokevirtual   java/io/InputStream.close:()V
        //   245: iconst_0       
        //   246: ifeq            257
        //   249: new             Ljava/lang/NullPointerException;
        //   252: dup            
        //   253: invokespecial   java/lang/NullPointerException.<init>:()V
        //   256: athrow         
        //   257: iconst_0       
        //   258: ifeq            269
        //   261: new             Ljava/lang/NullPointerException;
        //   264: dup            
        //   265: invokespecial   java/lang/NullPointerException.<init>:()V
        //   268: athrow         
        //   269: iconst_0       
        //   270: ifeq            281
        //   273: new             Ljava/lang/NullPointerException;
        //   276: dup            
        //   277: invokespecial   java/lang/NullPointerException.<init>:()V
        //   280: athrow         
        //   281: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   284: ifeq            12
        //   287: ldc             "BifManager"
        //   289: ldc             "bif download complete"
        //   291: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   294: pop            
        //   295: goto            12
        //   298: astore          6
        //   300: ldc             "BifManager"
        //   302: ldc             "Failed downlaod"
        //   304: aload           6
        //   306: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   309: pop            
        //   310: goto            281
        //   313: aload           11
        //   315: astore          8
        //   317: aload           12
        //   319: astore          9
        //   321: aload           14
        //   323: invokestatic    java/nio/ByteBuffer.wrap:([B)Ljava/nio/ByteBuffer;
        //   326: astore          14
        //   328: aload           11
        //   330: astore          8
        //   332: aload           12
        //   334: astore          9
        //   336: aload           14
        //   338: invokestatic    java/nio/ByteOrder.nativeOrder:()Ljava/nio/ByteOrder;
        //   341: invokevirtual   java/nio/ByteBuffer.order:(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;
        //   344: pop            
        //   345: aload           11
        //   347: astore          8
        //   349: aload           12
        //   351: astore          9
        //   353: aload_0        
        //   354: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   357: aload           14
        //   359: bipush          8
        //   361: invokevirtual   java/nio/ByteBuffer.getInt:(I)I
        //   364: invokestatic    com/netflix/mediaclient/media/BifManager.access$202:(Lcom/netflix/mediaclient/media/BifManager;I)I
        //   367: pop            
        //   368: aload           11
        //   370: astore          8
        //   372: aload           12
        //   374: astore          9
        //   376: aload_0        
        //   377: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   380: aload           14
        //   382: bipush          12
        //   384: invokevirtual   java/nio/ByteBuffer.getInt:(I)I
        //   387: invokestatic    com/netflix/mediaclient/media/BifManager.access$302:(Lcom/netflix/mediaclient/media/BifManager;I)I
        //   390: pop            
        //   391: aload           11
        //   393: astore          8
        //   395: aload           12
        //   397: astore          9
        //   399: aload_0        
        //   400: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   403: aload           14
        //   405: bipush          16
        //   407: invokevirtual   java/nio/ByteBuffer.getInt:(I)I
        //   410: invokestatic    com/netflix/mediaclient/media/BifManager.access$402:(Lcom/netflix/mediaclient/media/BifManager;I)I
        //   413: pop            
        //   414: aload           11
        //   416: astore          8
        //   418: aload           12
        //   420: astore          9
        //   422: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   425: ifeq            497
        //   428: aload           11
        //   430: astore          8
        //   432: aload           12
        //   434: astore          9
        //   436: ldc             "BifManager"
        //   438: new             Ljava/lang/StringBuilder;
        //   441: dup            
        //   442: invokespecial   java/lang/StringBuilder.<init>:()V
        //   445: ldc             "mVersion= "
        //   447: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   450: aload_0        
        //   451: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   454: invokestatic    com/netflix/mediaclient/media/BifManager.access$200:(Lcom/netflix/mediaclient/media/BifManager;)I
        //   457: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   460: ldc             ", mBifCount= "
        //   462: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   465: aload_0        
        //   466: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   469: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
        //   472: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   475: ldc             ",mInterval= "
        //   477: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   480: aload_0        
        //   481: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   484: invokestatic    com/netflix/mediaclient/media/BifManager.access$400:(Lcom/netflix/mediaclient/media/BifManager;)I
        //   487: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   490: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   493: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   496: pop            
        //   497: aload           11
        //   499: astore          8
        //   501: aload           12
        //   503: astore          9
        //   505: aload_0        
        //   506: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   509: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
        //   512: ifle            992
        //   515: aload           11
        //   517: astore          8
        //   519: aload           12
        //   521: astore          9
        //   523: aload_0        
        //   524: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   527: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
        //   530: sipush          28800
        //   533: if_icmpgt       992
        //   536: aload           11
        //   538: astore          8
        //   540: aload           12
        //   542: astore          9
        //   544: aload_0        
        //   545: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   548: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
        //   551: iconst_1       
        //   552: iadd           
        //   553: bipush          8
        //   555: imul           
        //   556: istore_3       
        //   557: aload           11
        //   559: astore          8
        //   561: aload           12
        //   563: astore          9
        //   565: aload_0        
        //   566: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   569: iload_3        
        //   570: bipush          64
        //   572: iadd           
        //   573: invokestatic    com/netflix/mediaclient/media/BifManager.access$502:(Lcom/netflix/mediaclient/media/BifManager;I)I
        //   576: pop            
        //   577: aload           11
        //   579: astore          8
        //   581: aload           12
        //   583: astore          9
        //   585: iload_3        
        //   586: newarray        B
        //   588: astore          14
        //   590: aload           11
        //   592: astore          8
        //   594: aload           12
        //   596: astore          9
        //   598: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   601: ifeq            1785
        //   604: aload           11
        //   606: astore          8
        //   608: aload           12
        //   610: astore          9
        //   612: ldc             "BifManager"
        //   614: new             Ljava/lang/StringBuilder;
        //   617: dup            
        //   618: invokespecial   java/lang/StringBuilder.<init>:()V
        //   621: ldc             "try to read index "
        //   623: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   626: iload_3        
        //   627: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   630: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   633: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   636: pop            
        //   637: goto            1785
        //   640: iload_1        
        //   641: iload_3        
        //   642: if_icmpge       679
        //   645: aload           11
        //   647: astore          8
        //   649: aload           12
        //   651: astore          9
        //   653: aload_0        
        //   654: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   657: invokestatic    com/netflix/mediaclient/media/BifManager.access$100:(Lcom/netflix/mediaclient/media/BifManager;)Z
        //   660: ifeq            1790
        //   663: aload           11
        //   665: astore          8
        //   667: aload           12
        //   669: astore          9
        //   671: ldc             "BifManager"
        //   673: ldc             "stopped"
        //   675: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   678: pop            
        //   679: aload           11
        //   681: astore          8
        //   683: aload           12
        //   685: astore          9
        //   687: aload           14
        //   689: invokestatic    java/nio/ByteBuffer.wrap:([B)Ljava/nio/ByteBuffer;
        //   692: astore          14
        //   694: aload           11
        //   696: astore          8
        //   698: aload           12
        //   700: astore          9
        //   702: aload           14
        //   704: invokestatic    java/nio/ByteOrder.nativeOrder:()Ljava/nio/ByteOrder;
        //   707: invokevirtual   java/nio/ByteBuffer.order:(Ljava/nio/ByteOrder;)Ljava/nio/ByteBuffer;
        //   710: pop            
        //   711: iconst_0       
        //   712: istore_2       
        //   713: iload_2        
        //   714: iload_1        
        //   715: if_icmpge       1054
        //   718: aload           11
        //   720: astore          8
        //   722: aload           12
        //   724: astore          9
        //   726: aload           14
        //   728: invokevirtual   java/nio/ByteBuffer.getInt:()I
        //   731: istore          4
        //   733: aload           11
        //   735: astore          8
        //   737: aload           12
        //   739: astore          9
        //   741: aload           14
        //   743: invokevirtual   java/nio/ByteBuffer.getInt:()I
        //   746: istore          5
        //   748: iload           4
        //   750: istore_3       
        //   751: iload           4
        //   753: iconst_m1      
        //   754: if_icmpne       760
        //   757: ldc             2147483647
        //   759: istore_3       
        //   760: aload           11
        //   762: astore          8
        //   764: aload           12
        //   766: astore          9
        //   768: aload_0        
        //   769: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //   772: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
        //   775: iload_3        
        //   776: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   779: iload           5
        //   781: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   784: invokeinterface java/util/SortedMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   789: pop            
        //   790: iload_2        
        //   791: bipush          8
        //   793: iadd           
        //   794: istore_2       
        //   795: goto            713
        //   798: aload           11
        //   800: astore          8
        //   802: aload           12
        //   804: astore          9
        //   806: aload           10
        //   808: aload           14
        //   810: iload_1        
        //   811: iload_2        
        //   812: invokevirtual   java/io/BufferedInputStream.read:([BII)I
        //   815: istore          4
        //   817: iload           4
        //   819: iload_2        
        //   820: if_icmpeq       885
        //   823: aload           11
        //   825: astore          8
        //   827: aload           12
        //   829: astore          9
        //   831: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   834: ifeq            885
        //   837: aload           11
        //   839: astore          8
        //   841: aload           12
        //   843: astore          9
        //   845: ldc             "BifManager"
        //   847: new             Ljava/lang/StringBuilder;
        //   850: dup            
        //   851: invokespecial   java/lang/StringBuilder.<init>:()V
        //   854: ldc             "attempt to read "
        //   856: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   859: iload_2        
        //   860: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   863: ldc             ", actual "
        //   865: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   868: iload           4
        //   870: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   873: ldc             " bytes"
        //   875: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   878: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   881: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   884: pop            
        //   885: iload           4
        //   887: ifgt            984
        //   890: aload           11
        //   892: astore          8
        //   894: aload           12
        //   896: astore          9
        //   898: aload           10
        //   900: invokevirtual   java/io/BufferedInputStream.close:()V
        //   903: aload           11
        //   905: astore          8
        //   907: aload           12
        //   909: astore          9
        //   911: aload           6
        //   913: invokevirtual   java/io/InputStream.close:()V
        //   916: iconst_0       
        //   917: ifeq            928
        //   920: new             Ljava/lang/NullPointerException;
        //   923: dup            
        //   924: invokespecial   java/lang/NullPointerException.<init>:()V
        //   927: athrow         
        //   928: iconst_0       
        //   929: ifeq            940
        //   932: new             Ljava/lang/NullPointerException;
        //   935: dup            
        //   936: invokespecial   java/lang/NullPointerException.<init>:()V
        //   939: athrow         
        //   940: iconst_0       
        //   941: ifeq            952
        //   944: new             Ljava/lang/NullPointerException;
        //   947: dup            
        //   948: invokespecial   java/lang/NullPointerException.<init>:()V
        //   951: athrow         
        //   952: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   955: ifeq            12
        //   958: ldc             "BifManager"
        //   960: ldc             "bif download complete"
        //   962: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   965: pop            
        //   966: goto            12
        //   969: astore          6
        //   971: ldc             "BifManager"
        //   973: ldc             "Failed downlaod"
        //   975: aload           6
        //   977: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   980: pop            
        //   981: goto            952
        //   984: iload_1        
        //   985: iload           4
        //   987: iadd           
        //   988: istore_1       
        //   989: goto            640
        //   992: aload           11
        //   994: astore          8
        //   996: aload           12
        //   998: astore          9
        //  1000: aload_0        
        //  1001: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1004: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
        //  1007: ifgt            1358
        //  1010: aload           11
        //  1012: astore          8
        //  1014: aload           12
        //  1016: astore          9
        //  1018: ldc             "BifManager"
        //  1020: new             Ljava/lang/StringBuilder;
        //  1023: dup            
        //  1024: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1027: ldc             "Index size is not positive, but "
        //  1029: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1032: aload_0        
        //  1033: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1036: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
        //  1039: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1042: ldc             ". Try next IRL if exist"
        //  1044: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1047: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1050: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //  1053: pop            
        //  1054: aload           11
        //  1056: astore          8
        //  1058: aload           12
        //  1060: astore          9
        //  1062: aload_0        
        //  1063: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1066: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
        //  1069: invokeinterface java/util/SortedMap.isEmpty:()Z
        //  1074: ifne            1561
        //  1077: aload           11
        //  1079: astore          8
        //  1081: aload           12
        //  1083: astore          9
        //  1085: aload_0        
        //  1086: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1089: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
        //  1092: aload_0        
        //  1093: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1096: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
        //  1099: invokeinterface java/util/SortedMap.firstKey:()Ljava/lang/Object;
        //  1104: invokeinterface java/util/SortedMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1109: checkcast       Ljava/lang/Integer;
        //  1112: invokevirtual   java/lang/Integer.intValue:()I
        //  1115: istore_2       
        //  1116: aload           11
        //  1118: astore          8
        //  1120: aload           12
        //  1122: astore          9
        //  1124: aload_0        
        //  1125: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1128: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
        //  1131: aload_0        
        //  1132: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1135: invokestatic    com/netflix/mediaclient/media/BifManager.access$600:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/util/SortedMap;
        //  1138: invokeinterface java/util/SortedMap.lastKey:()Ljava/lang/Object;
        //  1143: invokeinterface java/util/SortedMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //  1148: checkcast       Ljava/lang/Integer;
        //  1151: invokevirtual   java/lang/Integer.intValue:()I
        //  1154: istore_3       
        //  1155: aload           11
        //  1157: astore          8
        //  1159: aload           12
        //  1161: astore          9
        //  1163: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //  1166: ifeq            1211
        //  1169: aload           11
        //  1171: astore          8
        //  1173: aload           12
        //  1175: astore          9
        //  1177: ldc             "BifManager"
        //  1179: new             Ljava/lang/StringBuilder;
        //  1182: dup            
        //  1183: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1186: ldc             "first offset "
        //  1188: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1191: iload_2        
        //  1192: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1195: ldc             ", end @"
        //  1197: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1200: iload_3        
        //  1201: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1204: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1207: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1210: pop            
        //  1211: aload           11
        //  1213: astore          8
        //  1215: aload           12
        //  1217: astore          9
        //  1219: iload_2        
        //  1220: aload_0        
        //  1221: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1224: invokestatic    com/netflix/mediaclient/media/BifManager.access$500:(Lcom/netflix/mediaclient/media/BifManager;)I
        //  1227: if_icmple       1254
        //  1230: aload           11
        //  1232: astore          8
        //  1234: aload           12
        //  1236: astore          9
        //  1238: aload           10
        //  1240: iload_2        
        //  1241: aload_0        
        //  1242: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1245: invokestatic    com/netflix/mediaclient/media/BifManager.access$500:(Lcom/netflix/mediaclient/media/BifManager;)I
        //  1248: isub           
        //  1249: i2l            
        //  1250: invokevirtual   java/io/BufferedInputStream.skip:(J)J
        //  1253: pop2           
        //  1254: aload           11
        //  1256: astore          8
        //  1258: aload           12
        //  1260: astore          9
        //  1262: aload_0        
        //  1263: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1266: invokestatic    com/netflix/mediaclient/media/BifManager.access$800:(Lcom/netflix/mediaclient/media/BifManager;)Landroid/content/Context;
        //  1269: aload_0        
        //  1270: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1273: invokestatic    com/netflix/mediaclient/media/BifManager.access$700:(Lcom/netflix/mediaclient/media/BifManager;)Ljava/lang/String;
        //  1276: iconst_0       
        //  1277: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
        //  1280: astore          7
        //  1282: aload           7
        //  1284: astore          8
        //  1286: aload           7
        //  1288: astore          9
        //  1290: sipush          1024
        //  1293: newarray        B
        //  1295: astore          11
        //  1297: iconst_0       
        //  1298: istore_1       
        //  1299: iload_1        
        //  1300: iload_3        
        //  1301: iload_2        
        //  1302: isub           
        //  1303: if_icmpge       1492
        //  1306: aload           7
        //  1308: astore          8
        //  1310: aload           7
        //  1312: astore          9
        //  1314: aload           10
        //  1316: aload           11
        //  1318: iconst_0       
        //  1319: sipush          1024
        //  1322: invokevirtual   java/io/BufferedInputStream.read:([BII)I
        //  1325: istore          4
        //  1327: iload           4
        //  1329: ifle            1492
        //  1332: aload           7
        //  1334: astore          8
        //  1336: aload           7
        //  1338: astore          9
        //  1340: aload           7
        //  1342: aload           11
        //  1344: iconst_0       
        //  1345: iload           4
        //  1347: invokevirtual   java/io/FileOutputStream.write:([BII)V
        //  1350: iload_1        
        //  1351: iload           4
        //  1353: iadd           
        //  1354: istore_1       
        //  1355: goto            1299
        //  1358: aload           11
        //  1360: astore          8
        //  1362: aload           12
        //  1364: astore          9
        //  1366: ldc             "BifManager"
        //  1368: new             Ljava/lang/StringBuilder;
        //  1371: dup            
        //  1372: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1375: ldc             "Index size is higher than maximal positibility "
        //  1377: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1380: aload_0        
        //  1381: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1384: invokestatic    com/netflix/mediaclient/media/BifManager.access$300:(Lcom/netflix/mediaclient/media/BifManager;)I
        //  1387: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1390: ldc             " > "
        //  1392: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1395: sipush          28800
        //  1398: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1401: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1404: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //  1407: pop            
        //  1408: goto            1054
        //  1411: astore          9
        //  1413: aload           6
        //  1415: astore          7
        //  1417: aload           10
        //  1419: astore          6
        //  1421: aload_0        
        //  1422: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1425: iconst_0       
        //  1426: invokestatic    com/netflix/mediaclient/media/BifManager.access$902:(Lcom/netflix/mediaclient/media/BifManager;Z)Z
        //  1429: pop            
        //  1430: ldc             "BifManager"
        //  1432: ldc             "Failed downlaod"
        //  1434: aload           9
        //  1436: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //  1439: pop            
        //  1440: aload           8
        //  1442: ifnull          1455
        //  1445: aload           8
        //  1447: invokevirtual   java/io/FileOutputStream.flush:()V
        //  1450: aload           8
        //  1452: invokevirtual   java/io/FileOutputStream.close:()V
        //  1455: aload           7
        //  1457: ifnull          1465
        //  1460: aload           7
        //  1462: invokevirtual   java/io/InputStream.close:()V
        //  1465: aload           6
        //  1467: ifnull          1475
        //  1470: aload           6
        //  1472: invokevirtual   java/io/BufferedInputStream.close:()V
        //  1475: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //  1478: ifeq            12
        //  1481: ldc             "BifManager"
        //  1483: ldc             "bif download complete"
        //  1485: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1488: pop            
        //  1489: goto            12
        //  1492: aload           7
        //  1494: astore          8
        //  1496: aload           7
        //  1498: astore          9
        //  1500: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //  1503: ifeq            1544
        //  1506: aload           7
        //  1508: astore          8
        //  1510: aload           7
        //  1512: astore          9
        //  1514: ldc             "BifManager"
        //  1516: new             Ljava/lang/StringBuilder;
        //  1519: dup            
        //  1520: invokespecial   java/lang/StringBuilder.<init>:()V
        //  1523: ldc             "read "
        //  1525: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1528: iload_1        
        //  1529: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1532: ldc             " bytes"
        //  1534: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1537: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1540: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1543: pop            
        //  1544: aload           7
        //  1546: astore          8
        //  1548: aload           7
        //  1550: astore          9
        //  1552: aload_0        
        //  1553: getfield        com/netflix/mediaclient/media/BifManager$1.this$0:Lcom/netflix/mediaclient/media/BifManager;
        //  1556: iconst_1       
        //  1557: invokestatic    com/netflix/mediaclient/media/BifManager.access$902:(Lcom/netflix/mediaclient/media/BifManager;Z)Z
        //  1560: pop            
        //  1561: aload           7
        //  1563: ifnull          1576
        //  1566: aload           7
        //  1568: invokevirtual   java/io/FileOutputStream.flush:()V
        //  1571: aload           7
        //  1573: invokevirtual   java/io/FileOutputStream.close:()V
        //  1576: aload           6
        //  1578: ifnull          1586
        //  1581: aload           6
        //  1583: invokevirtual   java/io/InputStream.close:()V
        //  1586: aload           10
        //  1588: ifnull          1596
        //  1591: aload           10
        //  1593: invokevirtual   java/io/BufferedInputStream.close:()V
        //  1596: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //  1599: ifeq            84
        //  1602: ldc             "BifManager"
        //  1604: ldc             "bif download complete"
        //  1606: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1609: pop            
        //  1610: return         
        //  1611: astore          6
        //  1613: ldc             "BifManager"
        //  1615: ldc             "Failed downlaod"
        //  1617: aload           6
        //  1619: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //  1622: pop            
        //  1623: goto            1596
        //  1626: astore          6
        //  1628: ldc             "BifManager"
        //  1630: ldc             "Failed downlaod"
        //  1632: aload           6
        //  1634: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //  1637: pop            
        //  1638: goto            1475
        //  1641: astore          7
        //  1643: aconst_null    
        //  1644: astore          6
        //  1646: aconst_null    
        //  1647: astore          8
        //  1649: aload           9
        //  1651: ifnull          1664
        //  1654: aload           9
        //  1656: invokevirtual   java/io/FileOutputStream.flush:()V
        //  1659: aload           9
        //  1661: invokevirtual   java/io/FileOutputStream.close:()V
        //  1664: aload           6
        //  1666: ifnull          1674
        //  1669: aload           6
        //  1671: invokevirtual   java/io/InputStream.close:()V
        //  1674: aload           8
        //  1676: ifnull          1684
        //  1679: aload           8
        //  1681: invokevirtual   java/io/BufferedInputStream.close:()V
        //  1684: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //  1687: ifeq            1698
        //  1690: ldc             "BifManager"
        //  1692: ldc             "bif download complete"
        //  1694: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //  1697: pop            
        //  1698: aload           7
        //  1700: athrow         
        //  1701: astore          6
        //  1703: ldc             "BifManager"
        //  1705: ldc             "Failed downlaod"
        //  1707: aload           6
        //  1709: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //  1712: pop            
        //  1713: goto            1684
        //  1716: astore          7
        //  1718: aconst_null    
        //  1719: astore          8
        //  1721: goto            1649
        //  1724: astore          7
        //  1726: aload           10
        //  1728: astore          8
        //  1730: goto            1649
        //  1733: astore          9
        //  1735: aload           7
        //  1737: astore          10
        //  1739: aload           9
        //  1741: astore          7
        //  1743: aload           8
        //  1745: astore          9
        //  1747: aload           6
        //  1749: astore          8
        //  1751: aload           10
        //  1753: astore          6
        //  1755: goto            1649
        //  1758: astore          9
        //  1760: aconst_null    
        //  1761: astore          7
        //  1763: aconst_null    
        //  1764: astore          6
        //  1766: goto            1421
        //  1769: astore          9
        //  1771: aconst_null    
        //  1772: astore          10
        //  1774: aload           6
        //  1776: astore          7
        //  1778: aload           10
        //  1780: astore          6
        //  1782: goto            1421
        //  1785: iconst_0       
        //  1786: istore_1       
        //  1787: goto            640
        //  1790: sipush          1024
        //  1793: istore_2       
        //  1794: iload_3        
        //  1795: iload_1        
        //  1796: isub           
        //  1797: sipush          1024
        //  1800: if_icmpge       798
        //  1803: iload_3        
        //  1804: iload_1        
        //  1805: isub           
        //  1806: istore_2       
        //  1807: goto            798
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  100    117    1758   1769   Ljava/io/IOException;
        //  100    117    1641   1649   Any
        //  117    128    1769   1785   Ljava/io/IOException;
        //  117    128    1716   1724   Any
        //  136    142    1411   1421   Ljava/io/IOException;
        //  136    142    1724   1733   Any
        //  150    161    1411   1421   Ljava/io/IOException;
        //  150    161    1724   1733   Any
        //  169    175    1411   1421   Ljava/io/IOException;
        //  169    175    1724   1733   Any
        //  183    213    1411   1421   Ljava/io/IOException;
        //  183    213    1724   1733   Any
        //  227    232    1411   1421   Ljava/io/IOException;
        //  227    232    1724   1733   Any
        //  240    245    1411   1421   Ljava/io/IOException;
        //  240    245    1724   1733   Any
        //  249    257    298    313    Ljava/io/IOException;
        //  261    269    298    313    Ljava/io/IOException;
        //  273    281    298    313    Ljava/io/IOException;
        //  321    328    1411   1421   Ljava/io/IOException;
        //  321    328    1724   1733   Any
        //  336    345    1411   1421   Ljava/io/IOException;
        //  336    345    1724   1733   Any
        //  353    368    1411   1421   Ljava/io/IOException;
        //  353    368    1724   1733   Any
        //  376    391    1411   1421   Ljava/io/IOException;
        //  376    391    1724   1733   Any
        //  399    414    1411   1421   Ljava/io/IOException;
        //  399    414    1724   1733   Any
        //  422    428    1411   1421   Ljava/io/IOException;
        //  422    428    1724   1733   Any
        //  436    497    1411   1421   Ljava/io/IOException;
        //  436    497    1724   1733   Any
        //  505    515    1411   1421   Ljava/io/IOException;
        //  505    515    1724   1733   Any
        //  523    536    1411   1421   Ljava/io/IOException;
        //  523    536    1724   1733   Any
        //  544    557    1411   1421   Ljava/io/IOException;
        //  544    557    1724   1733   Any
        //  565    577    1411   1421   Ljava/io/IOException;
        //  565    577    1724   1733   Any
        //  585    590    1411   1421   Ljava/io/IOException;
        //  585    590    1724   1733   Any
        //  598    604    1411   1421   Ljava/io/IOException;
        //  598    604    1724   1733   Any
        //  612    637    1411   1421   Ljava/io/IOException;
        //  612    637    1724   1733   Any
        //  653    663    1411   1421   Ljava/io/IOException;
        //  653    663    1724   1733   Any
        //  671    679    1411   1421   Ljava/io/IOException;
        //  671    679    1724   1733   Any
        //  687    694    1411   1421   Ljava/io/IOException;
        //  687    694    1724   1733   Any
        //  702    711    1411   1421   Ljava/io/IOException;
        //  702    711    1724   1733   Any
        //  726    733    1411   1421   Ljava/io/IOException;
        //  726    733    1724   1733   Any
        //  741    748    1411   1421   Ljava/io/IOException;
        //  741    748    1724   1733   Any
        //  768    790    1411   1421   Ljava/io/IOException;
        //  768    790    1724   1733   Any
        //  806    817    1411   1421   Ljava/io/IOException;
        //  806    817    1724   1733   Any
        //  831    837    1411   1421   Ljava/io/IOException;
        //  831    837    1724   1733   Any
        //  845    885    1411   1421   Ljava/io/IOException;
        //  845    885    1724   1733   Any
        //  898    903    1411   1421   Ljava/io/IOException;
        //  898    903    1724   1733   Any
        //  911    916    1411   1421   Ljava/io/IOException;
        //  911    916    1724   1733   Any
        //  920    928    969    984    Ljava/io/IOException;
        //  932    940    969    984    Ljava/io/IOException;
        //  944    952    969    984    Ljava/io/IOException;
        //  1000   1010   1411   1421   Ljava/io/IOException;
        //  1000   1010   1724   1733   Any
        //  1018   1054   1411   1421   Ljava/io/IOException;
        //  1018   1054   1724   1733   Any
        //  1062   1077   1411   1421   Ljava/io/IOException;
        //  1062   1077   1724   1733   Any
        //  1085   1116   1411   1421   Ljava/io/IOException;
        //  1085   1116   1724   1733   Any
        //  1124   1155   1411   1421   Ljava/io/IOException;
        //  1124   1155   1724   1733   Any
        //  1163   1169   1411   1421   Ljava/io/IOException;
        //  1163   1169   1724   1733   Any
        //  1177   1211   1411   1421   Ljava/io/IOException;
        //  1177   1211   1724   1733   Any
        //  1219   1230   1411   1421   Ljava/io/IOException;
        //  1219   1230   1724   1733   Any
        //  1238   1254   1411   1421   Ljava/io/IOException;
        //  1238   1254   1724   1733   Any
        //  1262   1282   1411   1421   Ljava/io/IOException;
        //  1262   1282   1724   1733   Any
        //  1290   1297   1411   1421   Ljava/io/IOException;
        //  1290   1297   1724   1733   Any
        //  1314   1327   1411   1421   Ljava/io/IOException;
        //  1314   1327   1724   1733   Any
        //  1340   1350   1411   1421   Ljava/io/IOException;
        //  1340   1350   1724   1733   Any
        //  1366   1408   1411   1421   Ljava/io/IOException;
        //  1366   1408   1724   1733   Any
        //  1421   1440   1733   1758   Any
        //  1445   1455   1626   1641   Ljava/io/IOException;
        //  1460   1465   1626   1641   Ljava/io/IOException;
        //  1470   1475   1626   1641   Ljava/io/IOException;
        //  1500   1506   1411   1421   Ljava/io/IOException;
        //  1500   1506   1724   1733   Any
        //  1514   1544   1411   1421   Ljava/io/IOException;
        //  1514   1544   1724   1733   Any
        //  1552   1561   1411   1421   Ljava/io/IOException;
        //  1552   1561   1724   1733   Any
        //  1566   1576   1611   1626   Ljava/io/IOException;
        //  1581   1586   1611   1626   Ljava/io/IOException;
        //  1591   1596   1611   1626   Ljava/io/IOException;
        //  1654   1664   1701   1716   Ljava/io/IOException;
        //  1669   1674   1701   1716   Ljava/io/IOException;
        //  1679   1684   1701   1716   Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 838, Size: 838
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
