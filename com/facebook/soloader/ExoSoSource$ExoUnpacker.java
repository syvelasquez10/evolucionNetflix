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
        //    62: astore_1       
        //    63: aload_1        
        //    64: arraylength    
        //    65: istore          4
        //    67: iconst_0       
        //    68: istore_2       
        //    69: iload_2        
        //    70: iload           4
        //    72: if_icmpge       491
        //    75: new             Ljava/io/File;
        //    78: dup            
        //    79: aload           10
        //    81: aload_1        
        //    82: iload_2        
        //    83: aaload         
        //    84: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    87: astore          11
        //    89: aload           11
        //    91: invokevirtual   java/io/File.isDirectory:()Z
        //    94: ifne            104
        //    97: iload_2        
        //    98: iconst_1       
        //    99: iadd           
        //   100: istore_2       
        //   101: goto            69
        //   104: new             Ljava/io/File;
        //   107: dup            
        //   108: aload           11
        //   110: ldc             "metadata.txt"
        //   112: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   115: astore          7
        //   117: aload           7
        //   119: invokevirtual   java/io/File.isFile:()Z
        //   122: ifeq            97
        //   125: new             Ljava/io/FileReader;
        //   128: dup            
        //   129: aload           7
        //   131: invokespecial   java/io/FileReader.<init>:(Ljava/io/File;)V
        //   134: astore          8
        //   136: new             Ljava/io/BufferedReader;
        //   139: dup            
        //   140: aload           8
        //   142: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //   145: astore          9
        //   147: aconst_null    
        //   148: astore          7
        //   150: aload           9
        //   152: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   155: astore          14
        //   157: aload           14
        //   159: ifnull          386
        //   162: aload           14
        //   164: invokevirtual   java/lang/String.length:()I
        //   167: ifeq            150
        //   170: aload           14
        //   172: bipush          32
        //   174: invokevirtual   java/lang/String.indexOf:(I)I
        //   177: istore          5
        //   179: iload           5
        //   181: iconst_m1      
        //   182: if_icmpne       264
        //   185: new             Ljava/lang/RuntimeException;
        //   188: dup            
        //   189: new             Ljava/lang/StringBuilder;
        //   192: dup            
        //   193: invokespecial   java/lang/StringBuilder.<init>:()V
        //   196: ldc             "illegal line in exopackage metadata: ["
        //   198: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   201: aload           14
        //   203: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   206: ldc             "]"
        //   208: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   211: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   214: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //   217: athrow         
        //   218: astore          7
        //   220: aload           7
        //   222: athrow         
        //   223: astore_1       
        //   224: aload           9
        //   226: ifnull          239
        //   229: aload           7
        //   231: ifnull          455
        //   234: aload           9
        //   236: invokevirtual   java/io/BufferedReader.close:()V
        //   239: aload_1        
        //   240: athrow         
        //   241: astore          7
        //   243: aload           7
        //   245: athrow         
        //   246: astore_1       
        //   247: aload           8
        //   249: ifnull          262
        //   252: aload           7
        //   254: ifnull          483
        //   257: aload           8
        //   259: invokevirtual   java/io/FileReader.close:()V
        //   262: aload_1        
        //   263: athrow         
        //   264: new             Ljava/lang/StringBuilder;
        //   267: dup            
        //   268: invokespecial   java/lang/StringBuilder.<init>:()V
        //   271: aload           14
        //   273: iconst_0       
        //   274: iload           5
        //   276: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   279: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   282: ldc             ".so"
        //   284: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   287: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   290: astore          13
        //   292: aload           12
        //   294: invokevirtual   java/util/ArrayList.size:()I
        //   297: istore          6
        //   299: iconst_0       
        //   300: istore_3       
        //   301: iload_3        
        //   302: iload           6
        //   304: if_icmpge       512
        //   307: aload           12
        //   309: iload_3        
        //   310: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   313: checkcast       Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   316: getfield        com/facebook/soloader/ExoSoSource$FileDso.name:Ljava/lang/String;
        //   319: aload           13
        //   321: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   324: ifeq            379
        //   327: iconst_1       
        //   328: istore_3       
        //   329: iload_3        
        //   330: ifne            150
        //   333: aload           14
        //   335: iload           5
        //   337: iconst_1       
        //   338: iadd           
        //   339: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   342: astore          14
        //   344: aload           12
        //   346: new             Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   349: dup            
        //   350: aload           13
        //   352: aload           14
        //   354: new             Ljava/io/File;
        //   357: dup            
        //   358: aload           11
        //   360: aload           14
        //   362: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   365: invokespecial   com/facebook/soloader/ExoSoSource$FileDso.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)V
        //   368: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   371: pop            
        //   372: goto            150
        //   375: astore_1       
        //   376: goto            224
        //   379: iload_3        
        //   380: iconst_1       
        //   381: iadd           
        //   382: istore_3       
        //   383: goto            301
        //   386: aload           9
        //   388: ifnull          400
        //   391: iconst_0       
        //   392: ifeq            435
        //   395: aload           9
        //   397: invokevirtual   java/io/BufferedReader.close:()V
        //   400: aload           8
        //   402: ifnull          97
        //   405: iconst_0       
        //   406: ifeq            463
        //   409: aload           8
        //   411: invokevirtual   java/io/FileReader.close:()V
        //   414: goto            97
        //   417: astore_1       
        //   418: new             Ljava/lang/NullPointerException;
        //   421: dup            
        //   422: invokespecial   java/lang/NullPointerException.<init>:()V
        //   425: athrow         
        //   426: astore_1       
        //   427: new             Ljava/lang/NullPointerException;
        //   430: dup            
        //   431: invokespecial   java/lang/NullPointerException.<init>:()V
        //   434: athrow         
        //   435: aload           9
        //   437: invokevirtual   java/io/BufferedReader.close:()V
        //   440: goto            400
        //   443: astore          9
        //   445: aload           7
        //   447: aload           9
        //   449: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   452: goto            239
        //   455: aload           9
        //   457: invokevirtual   java/io/BufferedReader.close:()V
        //   460: goto            239
        //   463: aload           8
        //   465: invokevirtual   java/io/FileReader.close:()V
        //   468: goto            97
        //   471: astore          8
        //   473: aload           7
        //   475: aload           8
        //   477: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   480: goto            262
        //   483: aload           8
        //   485: invokevirtual   java/io/FileReader.close:()V
        //   488: goto            262
        //   491: aload_0        
        //   492: aload           12
        //   494: aload           12
        //   496: invokevirtual   java/util/ArrayList.size:()I
        //   499: anewarray       Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   502: invokevirtual   java/util/ArrayList.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   505: checkcast       [Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   508: putfield        com/facebook/soloader/ExoSoSource$ExoUnpacker.mDsos:[Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   511: return         
        //   512: iconst_0       
        //   513: istore_3       
        //   514: goto            329
        //   517: astore_1       
        //   518: aconst_null    
        //   519: astore          7
        //   521: goto            247
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  136    147    241    247    Ljava/lang/Throwable;
        //  136    147    517    524    Any
        //  150    157    218    224    Ljava/lang/Throwable;
        //  150    157    375    379    Any
        //  162    179    218    224    Ljava/lang/Throwable;
        //  162    179    375    379    Any
        //  185    218    218    224    Ljava/lang/Throwable;
        //  185    218    375    379    Any
        //  220    223    223    224    Any
        //  234    239    443    455    Ljava/lang/Throwable;
        //  234    239    517    524    Any
        //  239    241    241    247    Ljava/lang/Throwable;
        //  239    241    517    524    Any
        //  243    246    246    247    Any
        //  257    262    471    483    Ljava/lang/Throwable;
        //  264    299    218    224    Ljava/lang/Throwable;
        //  264    299    375    379    Any
        //  307    327    218    224    Ljava/lang/Throwable;
        //  307    327    375    379    Any
        //  333    372    218    224    Ljava/lang/Throwable;
        //  333    372    375    379    Any
        //  395    400    426    435    Ljava/lang/Throwable;
        //  395    400    517    524    Any
        //  409    414    417    426    Ljava/lang/Throwable;
        //  427    435    241    247    Ljava/lang/Throwable;
        //  427    435    517    524    Any
        //  435    440    241    247    Ljava/lang/Throwable;
        //  435    440    517    524    Any
        //  445    452    241    247    Ljava/lang/Throwable;
        //  445    452    517    524    Any
        //  455    460    241    247    Ljava/lang/Throwable;
        //  455    460    517    524    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
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
