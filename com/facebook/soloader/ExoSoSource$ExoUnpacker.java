// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

final class ExoSoSource$ExoUnpacker extends UnpackingSoSource$Unpacker
{
    private final ExoSoSource$FileDso[] mDsos;
    
    ExoSoSource$ExoUnpacker(final ExoSoSource p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aload_1        
        //     2: putfield        com/facebook/soloader/ExoSoSource$ExoUnpacker.this$0:Lcom/facebook/soloader/ExoSoSource;
        //     5: aload_0        
        //     6: invokespecial   com/facebook/soloader/UnpackingSoSource$Unpacker.<init>:()V
        //     9: aload_1        
        //    10: getfield        com/facebook/soloader/ExoSoSource.mContext:Landroid/content/Context;
        //    13: astore_1       
        //    14: new             Ljava/io/File;
        //    17: dup            
        //    18: new             Ljava/lang/StringBuilder;
        //    21: dup            
        //    22: invokespecial   java/lang/StringBuilder.<init>:()V
        //    25: ldc             "/data/local/tmp/exopackage/"
        //    27: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    30: aload_1        
        //    31: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    34: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    37: ldc             "/native-libs/"
        //    39: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    42: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    45: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    48: astore          7
        //    50: new             Ljava/util/ArrayList;
        //    53: dup            
        //    54: invokespecial   java/util/ArrayList.<init>:()V
        //    57: astore          11
        //    59: invokestatic    com/facebook/soloader/SysUtil.getSupportedAbis:()[Ljava/lang/String;
        //    62: astore          12
        //    64: aload           12
        //    66: arraylength    
        //    67: istore          4
        //    69: iconst_0       
        //    70: istore_2       
        //    71: iload_2        
        //    72: iload           4
        //    74: if_icmpge       487
        //    77: new             Ljava/io/File;
        //    80: dup            
        //    81: aload           7
        //    83: aload           12
        //    85: iload_2        
        //    86: aaload         
        //    87: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    90: astore          10
        //    92: aload           10
        //    94: invokevirtual   java/io/File.isDirectory:()Z
        //    97: ifne            107
        //   100: iload_2        
        //   101: iconst_1       
        //   102: iadd           
        //   103: istore_2       
        //   104: goto            71
        //   107: new             Ljava/io/File;
        //   110: dup            
        //   111: aload           10
        //   113: ldc             "metadata.txt"
        //   115: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   118: astore_1       
        //   119: aload_1        
        //   120: invokevirtual   java/io/File.isFile:()Z
        //   123: ifeq            100
        //   126: new             Ljava/io/FileReader;
        //   129: dup            
        //   130: aload_1        
        //   131: invokespecial   java/io/FileReader.<init>:(Ljava/io/File;)V
        //   134: astore          8
        //   136: new             Ljava/io/BufferedReader;
        //   139: dup            
        //   140: aload           8
        //   142: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //   145: astore          9
        //   147: aconst_null    
        //   148: astore_1       
        //   149: aload           9
        //   151: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   154: astore          14
        //   156: aload           14
        //   158: ifnull          384
        //   161: aload           14
        //   163: invokevirtual   java/lang/String.length:()I
        //   166: ifeq            149
        //   169: aload           14
        //   171: bipush          32
        //   173: invokevirtual   java/lang/String.indexOf:(I)I
        //   176: istore          5
        //   178: iload           5
        //   180: iconst_m1      
        //   181: if_icmpne       261
        //   184: new             Ljava/lang/RuntimeException;
        //   187: dup            
        //   188: new             Ljava/lang/StringBuilder;
        //   191: dup            
        //   192: invokespecial   java/lang/StringBuilder.<init>:()V
        //   195: ldc             "illegal line in exopackage metadata: ["
        //   197: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   200: aload           14
        //   202: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   205: ldc             "]"
        //   207: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   210: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   213: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //   216: athrow         
        //   217: astore_1       
        //   218: aload_1        
        //   219: athrow         
        //   220: astore          7
        //   222: aload           9
        //   224: ifnull          236
        //   227: aload_1        
        //   228: ifnull          452
        //   231: aload           9
        //   233: invokevirtual   java/io/BufferedReader.close:()V
        //   236: aload           7
        //   238: athrow         
        //   239: astore_1       
        //   240: aload_1        
        //   241: athrow         
        //   242: astore          7
        //   244: aload           8
        //   246: ifnull          258
        //   249: aload_1        
        //   250: ifnull          479
        //   253: aload           8
        //   255: invokevirtual   java/io/FileReader.close:()V
        //   258: aload           7
        //   260: athrow         
        //   261: new             Ljava/lang/StringBuilder;
        //   264: dup            
        //   265: invokespecial   java/lang/StringBuilder.<init>:()V
        //   268: aload           14
        //   270: iconst_0       
        //   271: iload           5
        //   273: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   276: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   279: ldc             ".so"
        //   281: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   284: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   287: astore          13
        //   289: aload           11
        //   291: invokevirtual   java/util/ArrayList.size:()I
        //   294: istore          6
        //   296: iconst_0       
        //   297: istore_3       
        //   298: iload_3        
        //   299: iload           6
        //   301: if_icmpge       508
        //   304: aload           11
        //   306: iload_3        
        //   307: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   310: checkcast       Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   313: getfield        com/facebook/soloader/ExoSoSource$FileDso.name:Ljava/lang/String;
        //   316: aload           13
        //   318: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   321: ifeq            377
        //   324: iconst_1       
        //   325: istore_3       
        //   326: iload_3        
        //   327: ifne            149
        //   330: aload           14
        //   332: iload           5
        //   334: iconst_1       
        //   335: iadd           
        //   336: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   339: astore          14
        //   341: aload           11
        //   343: new             Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   346: dup            
        //   347: aload           13
        //   349: aload           14
        //   351: new             Ljava/io/File;
        //   354: dup            
        //   355: aload           10
        //   357: aload           14
        //   359: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   362: invokespecial   com/facebook/soloader/ExoSoSource$FileDso.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)V
        //   365: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   368: pop            
        //   369: goto            149
        //   372: astore          7
        //   374: goto            222
        //   377: iload_3        
        //   378: iconst_1       
        //   379: iadd           
        //   380: istore_3       
        //   381: goto            298
        //   384: aload           9
        //   386: ifnull          398
        //   389: iconst_0       
        //   390: ifeq            433
        //   393: aload           9
        //   395: invokevirtual   java/io/BufferedReader.close:()V
        //   398: aload           8
        //   400: ifnull          100
        //   403: iconst_0       
        //   404: ifeq            460
        //   407: aload           8
        //   409: invokevirtual   java/io/FileReader.close:()V
        //   412: goto            100
        //   415: astore_1       
        //   416: new             Ljava/lang/NullPointerException;
        //   419: dup            
        //   420: invokespecial   java/lang/NullPointerException.<init>:()V
        //   423: athrow         
        //   424: astore_1       
        //   425: new             Ljava/lang/NullPointerException;
        //   428: dup            
        //   429: invokespecial   java/lang/NullPointerException.<init>:()V
        //   432: athrow         
        //   433: aload           9
        //   435: invokevirtual   java/io/BufferedReader.close:()V
        //   438: goto            398
        //   441: astore          9
        //   443: aload_1        
        //   444: aload           9
        //   446: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   449: goto            236
        //   452: aload           9
        //   454: invokevirtual   java/io/BufferedReader.close:()V
        //   457: goto            236
        //   460: aload           8
        //   462: invokevirtual   java/io/FileReader.close:()V
        //   465: goto            100
        //   468: astore          8
        //   470: aload_1        
        //   471: aload           8
        //   473: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   476: goto            258
        //   479: aload           8
        //   481: invokevirtual   java/io/FileReader.close:()V
        //   484: goto            258
        //   487: aload_0        
        //   488: aload           11
        //   490: aload           11
        //   492: invokevirtual   java/util/ArrayList.size:()I
        //   495: anewarray       Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   498: invokevirtual   java/util/ArrayList.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   501: checkcast       [Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   504: putfield        com/facebook/soloader/ExoSoSource$ExoUnpacker.mDsos:[Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   507: return         
        //   508: iconst_0       
        //   509: istore_3       
        //   510: goto            326
        //   513: astore          7
        //   515: aconst_null    
        //   516: astore_1       
        //   517: goto            244
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  136    147    239    244    Ljava/lang/Throwable;
        //  136    147    513    520    Any
        //  149    156    217    222    Ljava/lang/Throwable;
        //  149    156    372    377    Any
        //  161    178    217    222    Ljava/lang/Throwable;
        //  161    178    372    377    Any
        //  184    217    217    222    Ljava/lang/Throwable;
        //  184    217    372    377    Any
        //  218    220    220    222    Any
        //  231    236    441    452    Ljava/lang/Throwable;
        //  231    236    513    520    Any
        //  236    239    239    244    Ljava/lang/Throwable;
        //  236    239    513    520    Any
        //  240    242    242    244    Any
        //  253    258    468    479    Ljava/lang/Throwable;
        //  261    296    217    222    Ljava/lang/Throwable;
        //  261    296    372    377    Any
        //  304    324    217    222    Ljava/lang/Throwable;
        //  304    324    372    377    Any
        //  330    369    217    222    Ljava/lang/Throwable;
        //  330    369    372    377    Any
        //  393    398    424    433    Ljava/lang/Throwable;
        //  393    398    513    520    Any
        //  407    412    415    424    Ljava/lang/Throwable;
        //  425    433    239    244    Ljava/lang/Throwable;
        //  425    433    513    520    Any
        //  433    438    239    244    Ljava/lang/Throwable;
        //  433    438    513    520    Any
        //  443    449    239    244    Ljava/lang/Throwable;
        //  443    449    513    520    Any
        //  452    457    239    244    Ljava/lang/Throwable;
        //  452    457    513    520    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 247, Size: 247
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3551)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    
    @Override
    protected UnpackingSoSource$DsoManifest getDsoManifest() {
        return new UnpackingSoSource$DsoManifest(this.mDsos);
    }
    
    @Override
    protected UnpackingSoSource$InputDsoIterator openDsoIterator() {
        return new ExoSoSource$ExoUnpacker$FileBackedInputDsoIterator(this, null);
    }
}
