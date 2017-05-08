// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import android.os.Parcel;
import java.io.RandomAccessFile;
import android.util.Log;
import java.io.IOException;
import java.io.File;
import android.content.Context;

public abstract class UnpackingSoSource extends DirectorySoSource
{
    protected final Context mContext;
    
    protected UnpackingSoSource(final Context mContext, final String s) {
        super(getSoStorePath(mContext, s), 1);
        this.mContext = mContext;
    }
    
    private void deleteUnmentionedFiles(final UnpackingSoSource$Dso[] array) {
        final String[] list = this.soDirectory.list();
        if (list == null) {
            throw new IOException("unable to list directory " + this.soDirectory);
        }
        for (int i = 0; i < list.length; ++i) {
            final String s = list[i];
            if (!s.equals("dso_state") && !s.equals("dso_lock") && !s.equals("dso_deps") && !s.equals("dso_manifest")) {
                int n;
                int n2;
                for (n = 0, n2 = 0; n2 == 0 && n < array.length; ++n) {
                    if (array[n].name.equals(s)) {
                        n2 = 1;
                    }
                }
                if (n2 == 0) {
                    final File file = new File(this.soDirectory, s);
                    Log.v("fb-UnpackingSoSource", "deleting unaccounted-for file " + file);
                    SysUtil.dumbDeleteRecursive(file);
                }
            }
        }
    }
    
    private void extractDso(final UnpackingSoSource$InputDso unpackingSoSource$InputDso, final byte[] array) {
        Log.i("fb-UnpackingSoSource", "extracting DSO " + unpackingSoSource$InputDso.dso.name);
        final File file = new File(this.soDirectory, unpackingSoSource$InputDso.dso.name);
        RandomAccessFile randomAccessFile;
        while (true) {
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
                try {
                    final int available = unpackingSoSource$InputDso.content.available();
                    if (available > 1) {
                        SysUtil.fallocateIfSupported(randomAccessFile.getFD(), available);
                    }
                    SysUtil.copyBytes(randomAccessFile, unpackingSoSource$InputDso.content, Integer.MAX_VALUE, array);
                    randomAccessFile.setLength(randomAccessFile.getFilePointer());
                    if (!file.setExecutable(true, false)) {
                        throw new IOException("cannot make file executable: " + file);
                    }
                }
                finally {
                    randomAccessFile.close();
                }
            }
            catch (IOException ex) {
                Log.w("fb-UnpackingSoSource", "error overwriting " + file + " trying to delete and start over", (Throwable)ex);
                file.delete();
                randomAccessFile = new RandomAccessFile(file, "rw");
                continue;
            }
            break;
        }
        randomAccessFile.close();
    }
    
    public static File getSoStorePath(final Context context, final String s) {
        return new File(context.getApplicationInfo().dataDir + "/" + s);
    }
    
    private boolean refreshLocked(final FileLocker p0, final int p1, final byte[] p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/io/File;
        //     3: dup            
        //     4: aload_0        
        //     5: getfield        com/facebook/soloader/UnpackingSoSource.soDirectory:Ljava/io/File;
        //     8: ldc             "dso_state"
        //    10: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    13: astore          10
        //    15: new             Ljava/io/RandomAccessFile;
        //    18: dup            
        //    19: aload           10
        //    21: ldc             "rw"
        //    23: invokespecial   java/io/RandomAccessFile.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    26: astore          7
        //    28: aconst_null    
        //    29: astore          6
        //    31: aload           7
        //    33: invokevirtual   java/io/RandomAccessFile.readByte:()B
        //    36: istore          5
        //    38: iload           5
        //    40: istore          4
        //    42: iload           5
        //    44: iconst_1       
        //    45: if_icmpeq       84
        //    48: ldc             "fb-UnpackingSoSource"
        //    50: new             Ljava/lang/StringBuilder;
        //    53: dup            
        //    54: invokespecial   java/lang/StringBuilder.<init>:()V
        //    57: ldc             "dso store "
        //    59: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    62: aload_0        
        //    63: getfield        com/facebook/soloader/UnpackingSoSource.soDirectory:Ljava/io/File;
        //    66: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    69: ldc             " regeneration interrupted: wiping clean"
        //    71: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    74: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    77: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //    80: pop            
        //    81: iconst_0       
        //    82: istore          4
        //    84: aload           7
        //    86: ifnull          98
        //    89: iconst_0       
        //    90: ifeq            309
        //    93: aload           7
        //    95: invokevirtual   java/io/RandomAccessFile.close:()V
        //    98: new             Ljava/io/File;
        //   101: dup            
        //   102: aload_0        
        //   103: getfield        com/facebook/soloader/UnpackingSoSource.soDirectory:Ljava/io/File;
        //   106: ldc             "dso_deps"
        //   108: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   111: astore          11
        //   113: aconst_null    
        //   114: astore          6
        //   116: new             Ljava/io/RandomAccessFile;
        //   119: dup            
        //   120: aload           11
        //   122: ldc             "rw"
        //   124: invokespecial   java/io/RandomAccessFile.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   127: astore          8
        //   129: aload           8
        //   131: invokevirtual   java/io/RandomAccessFile.length:()J
        //   134: l2i            
        //   135: newarray        B
        //   137: astore          7
        //   139: aload           8
        //   141: aload           7
        //   143: invokevirtual   java/io/RandomAccessFile.read:([B)I
        //   146: aload           7
        //   148: arraylength    
        //   149: if_icmpeq       163
        //   152: ldc             "fb-UnpackingSoSource"
        //   154: ldc             "short read of so store deps file: marking unclean"
        //   156: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //   159: pop            
        //   160: iconst_0       
        //   161: istore          4
        //   163: aload           7
        //   165: aload_3        
        //   166: invokestatic    java/util/Arrays.equals:([B[B)Z
        //   169: ifne            183
        //   172: ldc             "fb-UnpackingSoSource"
        //   174: ldc             "deps mismatch on deps store: regenerating"
        //   176: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //   179: pop            
        //   180: iconst_0       
        //   181: istore          4
        //   183: iload           4
        //   185: ifne            271
        //   188: ldc             "fb-UnpackingSoSource"
        //   190: ldc             "so store dirty: regenerating"
        //   192: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //   195: pop            
        //   196: aload           10
        //   198: iconst_0       
        //   199: invokestatic    com/facebook/soloader/UnpackingSoSource.writeState:(Ljava/io/File;B)V
        //   202: aload_0        
        //   203: invokevirtual   com/facebook/soloader/UnpackingSoSource.makeUnpacker:()Lcom/facebook/soloader/UnpackingSoSource$Unpacker;
        //   206: astore          9
        //   208: aload           9
        //   210: invokevirtual   com/facebook/soloader/UnpackingSoSource$Unpacker.getDsoManifest:()Lcom/facebook/soloader/UnpackingSoSource$DsoManifest;
        //   213: astore          7
        //   215: aload           9
        //   217: invokevirtual   com/facebook/soloader/UnpackingSoSource$Unpacker.openDsoIterator:()Lcom/facebook/soloader/UnpackingSoSource$InputDsoIterator;
        //   220: astore          12
        //   222: aconst_null    
        //   223: astore          6
        //   225: aload_0        
        //   226: iload           4
        //   228: aload           7
        //   230: aload           12
        //   232: invokespecial   com/facebook/soloader/UnpackingSoSource.regenerate:(BLcom/facebook/soloader/UnpackingSoSource$DsoManifest;Lcom/facebook/soloader/UnpackingSoSource$InputDsoIterator;)V
        //   235: aload           12
        //   237: ifnull          249
        //   240: iconst_0       
        //   241: ifeq            405
        //   244: aload           12
        //   246: invokevirtual   com/facebook/soloader/UnpackingSoSource$InputDsoIterator.close:()V
        //   249: aload           7
        //   251: astore          6
        //   253: aload           9
        //   255: ifnull          271
        //   258: iconst_0       
        //   259: ifeq            470
        //   262: aload           9
        //   264: invokevirtual   com/facebook/soloader/UnpackingSoSource$Unpacker.close:()V
        //   267: aload           7
        //   269: astore          6
        //   271: aload           8
        //   273: ifnull          285
        //   276: iconst_0       
        //   277: ifeq            510
        //   280: aload           8
        //   282: invokevirtual   java/io/RandomAccessFile.close:()V
        //   285: aload           6
        //   287: ifnonnull       537
        //   290: iconst_0       
        //   291: ireturn        
        //   292: astore          6
        //   294: iconst_0       
        //   295: istore          4
        //   297: goto            84
        //   300: astore_1       
        //   301: new             Ljava/lang/NullPointerException;
        //   304: dup            
        //   305: invokespecial   java/lang/NullPointerException.<init>:()V
        //   308: athrow         
        //   309: aload           7
        //   311: invokevirtual   java/io/RandomAccessFile.close:()V
        //   314: goto            98
        //   317: astore_3       
        //   318: aload_3        
        //   319: athrow         
        //   320: astore_1       
        //   321: aload           7
        //   323: ifnull          335
        //   326: aload_3        
        //   327: ifnull          348
        //   330: aload           7
        //   332: invokevirtual   java/io/RandomAccessFile.close:()V
        //   335: aload_1        
        //   336: athrow         
        //   337: astore          6
        //   339: aload_3        
        //   340: aload           6
        //   342: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   345: goto            335
        //   348: aload           7
        //   350: invokevirtual   java/io/RandomAccessFile.close:()V
        //   353: goto            335
        //   356: astore_1       
        //   357: new             Ljava/lang/NullPointerException;
        //   360: dup            
        //   361: invokespecial   java/lang/NullPointerException.<init>:()V
        //   364: athrow         
        //   365: astore_1       
        //   366: aload_1        
        //   367: athrow         
        //   368: astore_3       
        //   369: aload           9
        //   371: ifnull          383
        //   374: aload_1        
        //   375: ifnull          493
        //   378: aload           9
        //   380: invokevirtual   com/facebook/soloader/UnpackingSoSource$Unpacker.close:()V
        //   383: aload_3        
        //   384: athrow         
        //   385: astore_3       
        //   386: aload_3        
        //   387: athrow         
        //   388: astore_1       
        //   389: aload           8
        //   391: ifnull          403
        //   394: aload_3        
        //   395: ifnull          529
        //   398: aload           8
        //   400: invokevirtual   java/io/RandomAccessFile.close:()V
        //   403: aload_1        
        //   404: athrow         
        //   405: aload           12
        //   407: invokevirtual   com/facebook/soloader/UnpackingSoSource$InputDsoIterator.close:()V
        //   410: goto            249
        //   413: astore_3       
        //   414: aconst_null    
        //   415: astore_1       
        //   416: goto            369
        //   419: astore_1       
        //   420: aload_1        
        //   421: astore          6
        //   423: aload_1        
        //   424: athrow         
        //   425: astore_1       
        //   426: aload           12
        //   428: ifnull          441
        //   431: aload           6
        //   433: ifnull          453
        //   436: aload           12
        //   438: invokevirtual   com/facebook/soloader/UnpackingSoSource$InputDsoIterator.close:()V
        //   441: aload_1        
        //   442: athrow         
        //   443: astore_3       
        //   444: aload           6
        //   446: aload_3        
        //   447: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   450: goto            441
        //   453: aload           12
        //   455: invokevirtual   com/facebook/soloader/UnpackingSoSource$InputDsoIterator.close:()V
        //   458: goto            441
        //   461: astore_1       
        //   462: new             Ljava/lang/NullPointerException;
        //   465: dup            
        //   466: invokespecial   java/lang/NullPointerException.<init>:()V
        //   469: athrow         
        //   470: aload           9
        //   472: invokevirtual   com/facebook/soloader/UnpackingSoSource$Unpacker.close:()V
        //   475: aload           7
        //   477: astore          6
        //   479: goto            271
        //   482: astore          6
        //   484: aload_1        
        //   485: aload           6
        //   487: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   490: goto            383
        //   493: aload           9
        //   495: invokevirtual   com/facebook/soloader/UnpackingSoSource$Unpacker.close:()V
        //   498: goto            383
        //   501: astore_1       
        //   502: new             Ljava/lang/NullPointerException;
        //   505: dup            
        //   506: invokespecial   java/lang/NullPointerException.<init>:()V
        //   509: athrow         
        //   510: aload           8
        //   512: invokevirtual   java/io/RandomAccessFile.close:()V
        //   515: goto            285
        //   518: astore          6
        //   520: aload_3        
        //   521: aload           6
        //   523: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   526: goto            403
        //   529: aload           8
        //   531: invokevirtual   java/io/RandomAccessFile.close:()V
        //   534: goto            403
        //   537: new             Lcom/facebook/soloader/UnpackingSoSource$1;
        //   540: dup            
        //   541: aload_0        
        //   542: aload           11
        //   544: aload_3        
        //   545: aload           6
        //   547: aload           10
        //   549: aload_1        
        //   550: invokespecial   com/facebook/soloader/UnpackingSoSource$1.<init>:(Lcom/facebook/soloader/UnpackingSoSource;Ljava/io/File;[BLcom/facebook/soloader/UnpackingSoSource$DsoManifest;Ljava/io/File;Lcom/facebook/soloader/FileLocker;)V
        //   553: astore_1       
        //   554: iload_2        
        //   555: iconst_1       
        //   556: iand           
        //   557: ifeq            598
        //   560: new             Ljava/lang/Thread;
        //   563: dup            
        //   564: aload_1        
        //   565: new             Ljava/lang/StringBuilder;
        //   568: dup            
        //   569: invokespecial   java/lang/StringBuilder.<init>:()V
        //   572: ldc             "SoSync:"
        //   574: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   577: aload_0        
        //   578: getfield        com/facebook/soloader/UnpackingSoSource.soDirectory:Ljava/io/File;
        //   581: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   584: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   587: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   590: invokespecial   java/lang/Thread.<init>:(Ljava/lang/Runnable;Ljava/lang/String;)V
        //   593: invokevirtual   java/lang/Thread.start:()V
        //   596: iconst_1       
        //   597: ireturn        
        //   598: aload_1        
        //   599: invokeinterface java/lang/Runnable.run:()V
        //   604: goto            596
        //   607: astore_1       
        //   608: aload           6
        //   610: astore_3       
        //   611: goto            321
        //   614: astore_1       
        //   615: aconst_null    
        //   616: astore_3       
        //   617: goto            389
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                  
        //  -----  -----  -----  -----  ----------------------
        //  31     38     292    300    Ljava/io/EOFException;
        //  31     38     317    321    Ljava/lang/Throwable;
        //  31     38     607    614    Any
        //  48     81     292    300    Ljava/io/EOFException;
        //  48     81     317    321    Ljava/lang/Throwable;
        //  48     81     607    614    Any
        //  93     98     300    309    Ljava/lang/Throwable;
        //  129    139    385    389    Ljava/lang/Throwable;
        //  129    139    614    620    Any
        //  139    160    385    389    Ljava/lang/Throwable;
        //  139    160    614    620    Any
        //  163    180    385    389    Ljava/lang/Throwable;
        //  163    180    614    620    Any
        //  188    208    385    389    Ljava/lang/Throwable;
        //  188    208    614    620    Any
        //  208    222    365    369    Ljava/lang/Throwable;
        //  208    222    413    419    Any
        //  225    235    419    425    Ljava/lang/Throwable;
        //  225    235    425    461    Any
        //  244    249    356    365    Ljava/lang/Throwable;
        //  244    249    413    419    Any
        //  262    267    461    470    Ljava/lang/Throwable;
        //  262    267    614    620    Any
        //  280    285    501    510    Ljava/lang/Throwable;
        //  318    320    320    321    Any
        //  330    335    337    348    Ljava/lang/Throwable;
        //  357    365    365    369    Ljava/lang/Throwable;
        //  357    365    413    419    Any
        //  366    368    368    369    Any
        //  378    383    482    493    Ljava/lang/Throwable;
        //  378    383    614    620    Any
        //  383    385    385    389    Ljava/lang/Throwable;
        //  383    385    614    620    Any
        //  386    388    388    389    Any
        //  398    403    518    529    Ljava/lang/Throwable;
        //  405    410    365    369    Ljava/lang/Throwable;
        //  405    410    413    419    Any
        //  423    425    425    461    Any
        //  436    441    443    453    Ljava/lang/Throwable;
        //  436    441    413    419    Any
        //  441    443    365    369    Ljava/lang/Throwable;
        //  441    443    413    419    Any
        //  444    450    365    369    Ljava/lang/Throwable;
        //  444    450    413    419    Any
        //  453    458    365    369    Ljava/lang/Throwable;
        //  453    458    413    419    Any
        //  462    470    385    389    Ljava/lang/Throwable;
        //  462    470    614    620    Any
        //  470    475    385    389    Ljava/lang/Throwable;
        //  470    475    614    620    Any
        //  484    490    385    389    Ljava/lang/Throwable;
        //  484    490    614    620    Any
        //  493    498    385    389    Ljava/lang/Throwable;
        //  493    498    614    620    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 304, Size: 304
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3551)
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
    
    private void regenerate(final byte p0, final UnpackingSoSource$DsoManifest p1, final UnpackingSoSource$InputDsoIterator p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: ldc             "fb-UnpackingSoSource"
        //     2: new             Ljava/lang/StringBuilder;
        //     5: dup            
        //     6: invokespecial   java/lang/StringBuilder.<init>:()V
        //     9: ldc_w           "regenerating DSO store "
        //    12: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    15: aload_0        
        //    16: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    19: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //    22: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    25: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    28: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //    31: pop            
        //    32: new             Ljava/io/RandomAccessFile;
        //    35: dup            
        //    36: new             Ljava/io/File;
        //    39: dup            
        //    40: aload_0        
        //    41: getfield        com/facebook/soloader/UnpackingSoSource.soDirectory:Ljava/io/File;
        //    44: ldc             "dso_manifest"
        //    46: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    49: ldc             "rw"
        //    51: invokespecial   java/io/RandomAccessFile.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    54: astore          9
        //    56: aconst_null    
        //    57: astore          8
        //    59: aload           8
        //    61: astore          7
        //    63: iload_1        
        //    64: iconst_1       
        //    65: if_icmpne       75
        //    68: aload           9
        //    70: invokestatic    com/facebook/soloader/UnpackingSoSource$DsoManifest.read:(Ljava/io/DataInput;)Lcom/facebook/soloader/UnpackingSoSource$DsoManifest;
        //    73: astore          7
        //    75: aload           7
        //    77: ifnonnull       398
        //    80: new             Lcom/facebook/soloader/UnpackingSoSource$DsoManifest;
        //    83: dup            
        //    84: iconst_0       
        //    85: anewarray       Lcom/facebook/soloader/UnpackingSoSource$Dso;
        //    88: invokespecial   com/facebook/soloader/UnpackingSoSource$DsoManifest.<init>:([Lcom/facebook/soloader/UnpackingSoSource$Dso;)V
        //    91: astore          7
        //    93: aload_0        
        //    94: aload_2        
        //    95: getfield        com/facebook/soloader/UnpackingSoSource$DsoManifest.dsos:[Lcom/facebook/soloader/UnpackingSoSource$Dso;
        //    98: invokespecial   com/facebook/soloader/UnpackingSoSource.deleteUnmentionedFiles:([Lcom/facebook/soloader/UnpackingSoSource$Dso;)V
        //   101: ldc_w           32768
        //   104: newarray        B
        //   106: astore_2       
        //   107: aload_3        
        //   108: invokevirtual   com/facebook/soloader/UnpackingSoSource$InputDsoIterator.hasNext:()Z
        //   111: ifeq            342
        //   114: aload_3        
        //   115: invokevirtual   com/facebook/soloader/UnpackingSoSource$InputDsoIterator.next:()Lcom/facebook/soloader/UnpackingSoSource$InputDso;
        //   118: astore          10
        //   120: aconst_null    
        //   121: astore          8
        //   123: iconst_1       
        //   124: istore          4
        //   126: iconst_0       
        //   127: istore_1       
        //   128: iload           4
        //   130: ifeq            257
        //   133: iload_1        
        //   134: aload           7
        //   136: getfield        com/facebook/soloader/UnpackingSoSource$DsoManifest.dsos:[Lcom/facebook/soloader/UnpackingSoSource$Dso;
        //   139: arraylength    
        //   140: if_icmpge       257
        //   143: iload           4
        //   145: istore          5
        //   147: aload           7
        //   149: getfield        com/facebook/soloader/UnpackingSoSource$DsoManifest.dsos:[Lcom/facebook/soloader/UnpackingSoSource$Dso;
        //   152: iload_1        
        //   153: aaload         
        //   154: getfield        com/facebook/soloader/UnpackingSoSource$Dso.name:Ljava/lang/String;
        //   157: aload           10
        //   159: getfield        com/facebook/soloader/UnpackingSoSource$InputDso.dso:Lcom/facebook/soloader/UnpackingSoSource$Dso;
        //   162: getfield        com/facebook/soloader/UnpackingSoSource$Dso.name:Ljava/lang/String;
        //   165: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   168: ifeq            206
        //   171: aload           7
        //   173: getfield        com/facebook/soloader/UnpackingSoSource$DsoManifest.dsos:[Lcom/facebook/soloader/UnpackingSoSource$Dso;
        //   176: iload_1        
        //   177: aaload         
        //   178: getfield        com/facebook/soloader/UnpackingSoSource$Dso.hash:Ljava/lang/String;
        //   181: aload           10
        //   183: getfield        com/facebook/soloader/UnpackingSoSource$InputDso.dso:Lcom/facebook/soloader/UnpackingSoSource$Dso;
        //   186: getfield        com/facebook/soloader/UnpackingSoSource$Dso.hash:Ljava/lang/String;
        //   189: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   192: istore          6
        //   194: iload           4
        //   196: istore          5
        //   198: iload           6
        //   200: ifeq            206
        //   203: iconst_0       
        //   204: istore          5
        //   206: iload_1        
        //   207: iconst_1       
        //   208: iadd           
        //   209: istore_1       
        //   210: iload           5
        //   212: istore          4
        //   214: goto            128
        //   217: astore          7
        //   219: ldc             "fb-UnpackingSoSource"
        //   221: ldc_w           "error reading existing DSO manifest"
        //   224: aload           7
        //   226: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   229: pop            
        //   230: aload           8
        //   232: astore          7
        //   234: goto            75
        //   237: astore_2       
        //   238: aload_2        
        //   239: athrow         
        //   240: astore_3       
        //   241: aload           9
        //   243: ifnull          255
        //   246: aload_2        
        //   247: ifnull          383
        //   250: aload           9
        //   252: invokevirtual   java/io/RandomAccessFile.close:()V
        //   255: aload_3        
        //   256: athrow         
        //   257: iload           4
        //   259: ifeq            269
        //   262: aload_0        
        //   263: aload           10
        //   265: aload_2        
        //   266: invokespecial   com/facebook/soloader/UnpackingSoSource.extractDso:(Lcom/facebook/soloader/UnpackingSoSource$InputDso;[B)V
        //   269: aload           10
        //   271: ifnull          107
        //   274: iconst_0       
        //   275: ifeq            295
        //   278: aload           10
        //   280: invokevirtual   com/facebook/soloader/UnpackingSoSource$InputDso.close:()V
        //   283: goto            107
        //   286: astore_2       
        //   287: new             Ljava/lang/NullPointerException;
        //   290: dup            
        //   291: invokespecial   java/lang/NullPointerException.<init>:()V
        //   294: athrow         
        //   295: aload           10
        //   297: invokevirtual   com/facebook/soloader/UnpackingSoSource$InputDso.close:()V
        //   300: goto            107
        //   303: astore_3       
        //   304: aload_3        
        //   305: athrow         
        //   306: astore_2       
        //   307: aload           10
        //   309: ifnull          321
        //   312: aload_3        
        //   313: ifnull          334
        //   316: aload           10
        //   318: invokevirtual   com/facebook/soloader/UnpackingSoSource$InputDso.close:()V
        //   321: aload_2        
        //   322: athrow         
        //   323: astore          7
        //   325: aload_3        
        //   326: aload           7
        //   328: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   331: goto            321
        //   334: aload           10
        //   336: invokevirtual   com/facebook/soloader/UnpackingSoSource$InputDso.close:()V
        //   339: goto            321
        //   342: aload           9
        //   344: ifnull          356
        //   347: iconst_0       
        //   348: ifeq            366
        //   351: aload           9
        //   353: invokevirtual   java/io/RandomAccessFile.close:()V
        //   356: return         
        //   357: astore_2       
        //   358: new             Ljava/lang/NullPointerException;
        //   361: dup            
        //   362: invokespecial   java/lang/NullPointerException.<init>:()V
        //   365: athrow         
        //   366: aload           9
        //   368: invokevirtual   java/io/RandomAccessFile.close:()V
        //   371: return         
        //   372: astore          7
        //   374: aload_2        
        //   375: aload           7
        //   377: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   380: goto            255
        //   383: aload           9
        //   385: invokevirtual   java/io/RandomAccessFile.close:()V
        //   388: goto            255
        //   391: astore_2       
        //   392: aload           8
        //   394: astore_3       
        //   395: goto            307
        //   398: goto            93
        //   401: astore_3       
        //   402: aconst_null    
        //   403: astore_2       
        //   404: goto            241
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  68     75     217    237    Ljava/lang/Exception;
        //  68     75     237    241    Ljava/lang/Throwable;
        //  68     75     401    407    Any
        //  80     93     237    241    Ljava/lang/Throwable;
        //  80     93     401    407    Any
        //  93     107    237    241    Ljava/lang/Throwable;
        //  93     107    401    407    Any
        //  107    120    237    241    Ljava/lang/Throwable;
        //  107    120    401    407    Any
        //  133    143    303    307    Ljava/lang/Throwable;
        //  133    143    391    398    Any
        //  147    194    303    307    Ljava/lang/Throwable;
        //  147    194    391    398    Any
        //  219    230    237    241    Ljava/lang/Throwable;
        //  219    230    401    407    Any
        //  238    240    240    241    Any
        //  250    255    372    383    Ljava/lang/Throwable;
        //  262    269    303    307    Ljava/lang/Throwable;
        //  262    269    391    398    Any
        //  278    283    286    295    Ljava/lang/Throwable;
        //  278    283    401    407    Any
        //  287    295    237    241    Ljava/lang/Throwable;
        //  287    295    401    407    Any
        //  295    300    237    241    Ljava/lang/Throwable;
        //  295    300    401    407    Any
        //  304    306    306    307    Any
        //  316    321    323    334    Ljava/lang/Throwable;
        //  316    321    401    407    Any
        //  321    323    237    241    Ljava/lang/Throwable;
        //  321    323    401    407    Any
        //  325    331    237    241    Ljava/lang/Throwable;
        //  325    331    401    407    Any
        //  334    339    237    241    Ljava/lang/Throwable;
        //  334    339    401    407    Any
        //  351    356    357    366    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 197, Size: 197
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3551)
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
    
    private static void writeState(final File file, final byte b) {
        final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        final Throwable t = null;
        while (true) {
            try {
                randomAccessFile.seek(0L);
                randomAccessFile.write(b);
                randomAccessFile.setLength(randomAccessFile.getFilePointer());
                randomAccessFile.getFD().sync();
                if (randomAccessFile == null) {
                    return;
                }
                Label_0060: {
                    if (!false) {
                        break Label_0060;
                    }
                    try {
                        randomAccessFile.close();
                        return;
                    }
                    catch (Throwable t2) {
                        throw new NullPointerException();
                    }
                }
                randomAccessFile.close();
            }
            catch (Throwable t) {
                try {
                    throw t;
                }
                finally {}
                while (true) {
                    if (randomAccessFile == null) {
                        break Label_0081;
                    }
                    Label_0092: {
                        if (t == null) {
                            break Label_0092;
                        }
                        try {
                            randomAccessFile.close();
                            throw file;
                        }
                        catch (Throwable randomAccessFile) {
                            t.addSuppressed((Throwable)randomAccessFile);
                            throw file;
                        }
                    }
                    randomAccessFile.close();
                    continue;
                }
            }
            finally {
                continue;
            }
            break;
        }
    }
    
    protected byte[] getDepsBlock() {
        final Parcel obtain = Parcel.obtain();
        final UnpackingSoSource$Unpacker unpacker = this.makeUnpacker();
        Object marshall = null;
        while (true) {
            try {
                final UnpackingSoSource$Dso[] dsos = unpacker.getDsoManifest().dsos;
                obtain.writeByte((byte)1);
                obtain.writeInt(dsos.length);
                for (int i = 0; i < dsos.length; ++i) {
                    obtain.writeString(dsos[i].name);
                    obtain.writeString(dsos[i].hash);
                }
                while (true) {
                    if (unpacker == null) {
                        break Label_0086;
                    }
                    Label_0106: {
                        if (!false) {
                            break Label_0106;
                        }
                        try {
                            unpacker.close();
                            marshall = obtain.marshall();
                            obtain.recycle();
                            return (byte[])marshall;
                        }
                        catch (Throwable t2) {
                            throw new NullPointerException();
                        }
                    }
                    unpacker.close();
                    continue;
                }
            }
            catch (Throwable marshall) {
                try {
                    throw marshall;
                }
                finally {}
                while (true) {
                    if (unpacker == null) {
                        break Label_0132;
                    }
                    Label_0145: {
                        if (marshall == null) {
                            break Label_0145;
                        }
                        try {
                            unpacker.close();
                            throw;
                        }
                        catch (Throwable unpacker) {
                            ((Throwable)marshall).addSuppressed((Throwable)unpacker);
                            throw;
                        }
                    }
                    unpacker.close();
                    continue;
                }
            }
            finally {
                continue;
            }
            break;
        }
    }
    
    protected abstract UnpackingSoSource$Unpacker makeUnpacker();
    
    @Override
    protected void prepare(final int n) {
        SysUtil.mkdirOrThrow(this.soDirectory);
        FileLocker lock = FileLocker.lock(new File(this.soDirectory, "dso_lock"));
        try {
            Log.v("fb-UnpackingSoSource", "locked dso store " + this.soDirectory);
            if (this.refreshLocked(lock, n, this.getDepsBlock())) {
                lock = null;
            }
            else {
                Log.i("fb-UnpackingSoSource", "dso store is up-to-date: " + this.soDirectory);
            }
            if (lock != null) {
                Log.v("fb-UnpackingSoSource", "releasing dso store lock for " + this.soDirectory);
                lock.close();
                return;
            }
            Log.v("fb-UnpackingSoSource", "not releasing dso store lock for " + this.soDirectory + " (syncer thread started)");
        }
        finally {
            while (true) {
                if (lock != null) {
                    Log.v("fb-UnpackingSoSource", "releasing dso store lock for " + this.soDirectory);
                    lock.close();
                    break Label_0215;
                }
                Log.v("fb-UnpackingSoSource", "not releasing dso store lock for " + this.soDirectory + " (syncer thread started)");
                break Label_0215;
                continue;
            }
        }
    }
}
