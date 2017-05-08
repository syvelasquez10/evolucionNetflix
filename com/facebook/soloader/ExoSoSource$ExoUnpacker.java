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
        //    48: astore_1       
        //    49: new             Ljava/util/ArrayList;
        //    52: dup            
        //    53: invokespecial   java/util/ArrayList.<init>:()V
        //    56: astore          12
        //    58: invokestatic    com/facebook/soloader/SysUtil.getSupportedAbis:()[Ljava/lang/String;
        //    61: astore          11
        //    63: aload           11
        //    65: arraylength    
        //    66: istore          4
        //    68: iconst_0       
        //    69: istore_2       
        //    70: iload_2        
        //    71: iload           4
        //    73: if_icmpge       492
        //    76: new             Ljava/io/File;
        //    79: dup            
        //    80: aload_1        
        //    81: aload           11
        //    83: iload_2        
        //    84: aaload         
        //    85: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    88: astore          10
        //    90: aload           10
        //    92: invokevirtual   java/io/File.isDirectory:()Z
        //    95: ifne            105
        //    98: iload_2        
        //    99: iconst_1       
        //   100: iadd           
        //   101: istore_2       
        //   102: goto            70
        //   105: new             Ljava/io/File;
        //   108: dup            
        //   109: aload           10
        //   111: ldc             "metadata.txt"
        //   113: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   116: astore          7
        //   118: aload           7
        //   120: invokevirtual   java/io/File.isFile:()Z
        //   123: ifeq            98
        //   126: new             Ljava/io/FileReader;
        //   129: dup            
        //   130: aload           7
        //   132: invokespecial   java/io/FileReader.<init>:(Ljava/io/File;)V
        //   135: astore          8
        //   137: new             Ljava/io/BufferedReader;
        //   140: dup            
        //   141: aload           8
        //   143: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //   146: astore          9
        //   148: aconst_null    
        //   149: astore          7
        //   151: aload           9
        //   153: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   156: astore          14
        //   158: aload           14
        //   160: ifnull          387
        //   163: aload           14
        //   165: invokevirtual   java/lang/String.length:()I
        //   168: ifeq            151
        //   171: aload           14
        //   173: bipush          32
        //   175: invokevirtual   java/lang/String.indexOf:(I)I
        //   178: istore          5
        //   180: iload           5
        //   182: iconst_m1      
        //   183: if_icmpne       265
        //   186: new             Ljava/lang/RuntimeException;
        //   189: dup            
        //   190: new             Ljava/lang/StringBuilder;
        //   193: dup            
        //   194: invokespecial   java/lang/StringBuilder.<init>:()V
        //   197: ldc             "illegal line in exopackage metadata: ["
        //   199: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   202: aload           14
        //   204: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   207: ldc             "]"
        //   209: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   212: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   215: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //   218: athrow         
        //   219: astore          7
        //   221: aload           7
        //   223: athrow         
        //   224: astore_1       
        //   225: aload           9
        //   227: ifnull          240
        //   230: aload           7
        //   232: ifnull          456
        //   235: aload           9
        //   237: invokevirtual   java/io/BufferedReader.close:()V
        //   240: aload_1        
        //   241: athrow         
        //   242: astore          7
        //   244: aload           7
        //   246: athrow         
        //   247: astore_1       
        //   248: aload           8
        //   250: ifnull          263
        //   253: aload           7
        //   255: ifnull          484
        //   258: aload           8
        //   260: invokevirtual   java/io/FileReader.close:()V
        //   263: aload_1        
        //   264: athrow         
        //   265: new             Ljava/lang/StringBuilder;
        //   268: dup            
        //   269: invokespecial   java/lang/StringBuilder.<init>:()V
        //   272: aload           14
        //   274: iconst_0       
        //   275: iload           5
        //   277: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   280: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   283: ldc             ".so"
        //   285: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   288: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   291: astore          13
        //   293: aload           12
        //   295: invokevirtual   java/util/ArrayList.size:()I
        //   298: istore          6
        //   300: iconst_0       
        //   301: istore_3       
        //   302: iload_3        
        //   303: iload           6
        //   305: if_icmpge       513
        //   308: aload           12
        //   310: iload_3        
        //   311: invokevirtual   java/util/ArrayList.get:(I)Ljava/lang/Object;
        //   314: checkcast       Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   317: getfield        com/facebook/soloader/ExoSoSource$FileDso.name:Ljava/lang/String;
        //   320: aload           13
        //   322: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   325: ifeq            380
        //   328: iconst_1       
        //   329: istore_3       
        //   330: iload_3        
        //   331: ifne            151
        //   334: aload           14
        //   336: iload           5
        //   338: iconst_1       
        //   339: iadd           
        //   340: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   343: astore          14
        //   345: aload           12
        //   347: new             Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   350: dup            
        //   351: aload           13
        //   353: aload           14
        //   355: new             Ljava/io/File;
        //   358: dup            
        //   359: aload           10
        //   361: aload           14
        //   363: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   366: invokespecial   com/facebook/soloader/ExoSoSource$FileDso.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)V
        //   369: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   372: pop            
        //   373: goto            151
        //   376: astore_1       
        //   377: goto            225
        //   380: iload_3        
        //   381: iconst_1       
        //   382: iadd           
        //   383: istore_3       
        //   384: goto            302
        //   387: aload           9
        //   389: ifnull          401
        //   392: iconst_0       
        //   393: ifeq            436
        //   396: aload           9
        //   398: invokevirtual   java/io/BufferedReader.close:()V
        //   401: aload           8
        //   403: ifnull          98
        //   406: iconst_0       
        //   407: ifeq            464
        //   410: aload           8
        //   412: invokevirtual   java/io/FileReader.close:()V
        //   415: goto            98
        //   418: astore_1       
        //   419: new             Ljava/lang/NullPointerException;
        //   422: dup            
        //   423: invokespecial   java/lang/NullPointerException.<init>:()V
        //   426: athrow         
        //   427: astore_1       
        //   428: new             Ljava/lang/NullPointerException;
        //   431: dup            
        //   432: invokespecial   java/lang/NullPointerException.<init>:()V
        //   435: athrow         
        //   436: aload           9
        //   438: invokevirtual   java/io/BufferedReader.close:()V
        //   441: goto            401
        //   444: astore          9
        //   446: aload           7
        //   448: aload           9
        //   450: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   453: goto            240
        //   456: aload           9
        //   458: invokevirtual   java/io/BufferedReader.close:()V
        //   461: goto            240
        //   464: aload           8
        //   466: invokevirtual   java/io/FileReader.close:()V
        //   469: goto            98
        //   472: astore          8
        //   474: aload           7
        //   476: aload           8
        //   478: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   481: goto            263
        //   484: aload           8
        //   486: invokevirtual   java/io/FileReader.close:()V
        //   489: goto            263
        //   492: aload_0        
        //   493: aload           12
        //   495: aload           12
        //   497: invokevirtual   java/util/ArrayList.size:()I
        //   500: anewarray       Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   503: invokevirtual   java/util/ArrayList.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   506: checkcast       [Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   509: putfield        com/facebook/soloader/ExoSoSource$ExoUnpacker.mDsos:[Lcom/facebook/soloader/ExoSoSource$FileDso;
        //   512: return         
        //   513: iconst_0       
        //   514: istore_3       
        //   515: goto            330
        //   518: astore_1       
        //   519: aconst_null    
        //   520: astore          7
        //   522: goto            248
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  137    148    242    248    Ljava/lang/Throwable;
        //  137    148    518    525    Any
        //  151    158    219    225    Ljava/lang/Throwable;
        //  151    158    376    380    Any
        //  163    180    219    225    Ljava/lang/Throwable;
        //  163    180    376    380    Any
        //  186    219    219    225    Ljava/lang/Throwable;
        //  186    219    376    380    Any
        //  221    224    224    225    Any
        //  235    240    444    456    Ljava/lang/Throwable;
        //  235    240    518    525    Any
        //  240    242    242    248    Ljava/lang/Throwable;
        //  240    242    518    525    Any
        //  244    247    247    248    Any
        //  258    263    472    484    Ljava/lang/Throwable;
        //  265    300    219    225    Ljava/lang/Throwable;
        //  265    300    376    380    Any
        //  308    328    219    225    Ljava/lang/Throwable;
        //  308    328    376    380    Any
        //  334    373    219    225    Ljava/lang/Throwable;
        //  334    373    376    380    Any
        //  396    401    427    436    Ljava/lang/Throwable;
        //  396    401    518    525    Any
        //  410    415    418    427    Ljava/lang/Throwable;
        //  428    436    242    248    Ljava/lang/Throwable;
        //  428    436    518    525    Any
        //  436    441    242    248    Ljava/lang/Throwable;
        //  436    441    518    525    Any
        //  446    453    242    248    Ljava/lang/Throwable;
        //  446    453    518    525    Any
        //  456    461    242    248    Ljava/lang/Throwable;
        //  456    461    518    525    Any
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
