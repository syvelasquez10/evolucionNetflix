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
        //    48: astore          10
        //    50: new             Ljava/util/ArrayList;
        //    53: dup            
        //    54: invokespecial   java/util/ArrayList.<init>:()V
        //    57: astore          12
        //    59: invokestatic    com/facebook/soloader/SysUtil.getSupportedAbis:()[Ljava/lang/String;
        //    62: astore          7
        //    64: aload           7
        //    66: arraylength    
        //    67: istore          4
        //    69: iconst_0       
        //    70: istore_2       
        //    71: iload_2        
        //    72: iload           4
        //    74: if_icmpge       489
        //    77: new             Ljava/io/File;
        //    80: dup            
        //    81: aload           10
        //    83: aload           7
        //    85: iload_2        
        //    86: aaload         
        //    87: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    90: astore          11
        //    92: aload           11
        //    94: invokevirtual   java/io/File.isDirectory:()Z
        //    97: ifne            107
        //   100: iload_2        
        //   101: iconst_1       
        //   102: iadd           
        //   103: istore_2       
        //   104: goto            71
        //   107: new             Ljava/io/File;
        //   110: dup            
        //   111: aload           11
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
        //   158: ifnull          385
        //   161: aload           14
        //   163: invokevirtual   java/lang/String.length:()I
        //   166: ifeq            149
        //   169: aload           14
        //   171: bipush          32
        //   173: invokevirtual   java/lang/String.indexOf:(I)I
        //   176: istore          5
        //   178: iload           5
        //   180: iconst_m1      
        //   181: if_icmpne       262
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
        //   228: ifnull          453
        //   231: aload           9
        //   233: invokevirtual   java/io/BufferedReader.close:()V
        //   236: aload           7
        //   238: athrow         
        //   239: astore          7
        //   241: aload           7
        //   243: athrow         
        //   244: astore_1       
        //   245: aload           8
        //   247: ifnull          260
        //   250: aload           7
        //   252: ifnull          481
        //   255: aload           8
        //   257: invokevirtual   java/io/FileReader.close:()V
        //   260: aload_1        
        //   261: athrow         
        //   262: new             Ljava/lang/StringBuilder;
        //   265: dup            
        //   266: invokespecial   java/lang/StringBuilder.<init>:()V
        //   269: aload           14
        //   271: iconst_0       
        //   272: iload           5
        //   274: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   277: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   280: ldc             ".so"
        //   282: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   285: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   288: astore          13
        //   290: aload           12
        //   292: invokevirtual   java/util/ArrayList.size:()I
        //   295: istore          6
        //   297: iconst_0       
        //   298: istore_3       
        //   299: iload_3        
        //   300: iload           6
        //   302: if_icmpge       510
        //   305: aload           12
        //   307: iload_3        
        //   308: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   311: checkcast       Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   314: getfield        com/facebook/soloader/ExoSoSource$FileDso.name:Ljava/lang/String;
        //   317: aload           13
        //   319: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   322: ifeq            378
        //   325: iconst_1       
        //   326: istore_3       
        //   327: iload_3        
        //   328: ifne            149
        //   331: aload           14
        //   333: iload           5
        //   335: iconst_1       
        //   336: iadd           
        //   337: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   340: astore          14
        //   342: aload           12
        //   344: new             Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   347: dup            
        //   348: aload           13
        //   350: aload           14
        //   352: new             Ljava/io/File;
        //   355: dup            
        //   356: aload           11
        //   358: aload           14
        //   360: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   363: invokespecial   com/facebook/soloader/ExoSoSource$FileDso.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)V
        //   366: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   369: pop            
        //   370: goto            149
        //   373: astore          7
        //   375: goto            222
        //   378: iload_3        
        //   379: iconst_1       
        //   380: iadd           
        //   381: istore_3       
        //   382: goto            299
        //   385: aload           9
        //   387: ifnull          399
        //   390: iconst_0       
        //   391: ifeq            434
        //   394: aload           9
        //   396: invokevirtual   java/io/BufferedReader.close:()V
        //   399: aload           8
        //   401: ifnull          100
        //   404: iconst_0       
        //   405: ifeq            461
        //   408: aload           8
        //   410: invokevirtual   java/io/FileReader.close:()V
        //   413: goto            100
        //   416: astore_1       
        //   417: new             Ljava/lang/NullPointerException;
        //   420: dup            
        //   421: invokespecial   java/lang/NullPointerException.<init>:()V
        //   424: athrow         
        //   425: astore_1       
        //   426: new             Ljava/lang/NullPointerException;
        //   429: dup            
        //   430: invokespecial   java/lang/NullPointerException.<init>:()V
        //   433: athrow         
        //   434: aload           9
        //   436: invokevirtual   java/io/BufferedReader.close:()V
        //   439: goto            399
        //   442: astore          9
        //   444: aload_1        
        //   445: aload           9
        //   447: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   450: goto            236
        //   453: aload           9
        //   455: invokevirtual   java/io/BufferedReader.close:()V
        //   458: goto            236
        //   461: aload           8
        //   463: invokevirtual   java/io/FileReader.close:()V
        //   466: goto            100
        //   469: astore          8
        //   471: aload           7
        //   473: aload           8
        //   475: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   478: goto            260
        //   481: aload           8
        //   483: invokevirtual   java/io/FileReader.close:()V
        //   486: goto            260
        //   489: aload_0        
        //   490: aload           12
        //   492: aload           12
        //   494: invokevirtual   java/util/ArrayList.size:()I
        //   497: anewarray       Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   500: invokevirtual   java/util/ArrayList.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   503: checkcast       [Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   506: putfield        com/facebook/soloader/ExoSoSource$ExoUnpacker.mDsos:[Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   509: return         
        //   510: iconst_0       
        //   511: istore_3       
        //   512: goto            327
        //   515: astore_1       
        //   516: aconst_null    
        //   517: astore          7
        //   519: goto            245
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  136    147    239    245    Ljava/lang/Throwable;
        //  136    147    515    522    Any
        //  149    156    217    222    Ljava/lang/Throwable;
        //  149    156    373    378    Any
        //  161    178    217    222    Ljava/lang/Throwable;
        //  161    178    373    378    Any
        //  184    217    217    222    Ljava/lang/Throwable;
        //  184    217    373    378    Any
        //  218    220    220    222    Any
        //  231    236    442    453    Ljava/lang/Throwable;
        //  231    236    515    522    Any
        //  236    239    239    245    Ljava/lang/Throwable;
        //  236    239    515    522    Any
        //  241    244    244    245    Any
        //  255    260    469    481    Ljava/lang/Throwable;
        //  262    297    217    222    Ljava/lang/Throwable;
        //  262    297    373    378    Any
        //  305    325    217    222    Ljava/lang/Throwable;
        //  305    325    373    378    Any
        //  331    370    217    222    Ljava/lang/Throwable;
        //  331    370    373    378    Any
        //  394    399    425    434    Ljava/lang/Throwable;
        //  394    399    515    522    Any
        //  408    413    416    425    Ljava/lang/Throwable;
        //  426    434    239    245    Ljava/lang/Throwable;
        //  426    434    515    522    Any
        //  434    439    239    245    Ljava/lang/Throwable;
        //  434    439    515    522    Any
        //  444    450    239    245    Ljava/lang/Throwable;
        //  444    450    515    522    Any
        //  453    458    239    245    Ljava/lang/Throwable;
        //  453    458    515    522    Any
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
