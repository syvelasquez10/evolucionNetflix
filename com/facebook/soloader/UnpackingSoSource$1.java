// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import java.io.File;

class UnpackingSoSource$1 implements Runnable
{
    final /* synthetic */ UnpackingSoSource this$0;
    final /* synthetic */ byte[] val$deps;
    final /* synthetic */ File val$depsFileName;
    final /* synthetic */ FileLocker val$lock;
    final /* synthetic */ UnpackingSoSource$DsoManifest val$manifest;
    final /* synthetic */ File val$stateFileName;
    
    UnpackingSoSource$1(final UnpackingSoSource this$0, final File val$depsFileName, final byte[] val$deps, final UnpackingSoSource$DsoManifest val$manifest, final File val$stateFileName, final FileLocker val$lock) {
        this.this$0 = this$0;
        this.val$depsFileName = val$depsFileName;
        this.val$deps = val$deps;
        this.val$manifest = val$manifest;
        this.val$stateFileName = val$stateFileName;
        this.val$lock = val$lock;
    }
    
    @Override
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_2       
        //     2: aconst_null    
        //     3: astore_3       
        //     4: ldc             "fb-UnpackingSoSource"
        //     6: ldc             "starting syncer worker"
        //     8: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //    11: pop            
        //    12: new             Ljava/io/RandomAccessFile;
        //    15: dup            
        //    16: aload_0        
        //    17: getfield        com/facebook/soloader/UnpackingSoSource$1.val$depsFileName:Ljava/io/File;
        //    20: ldc             "rw"
        //    22: invokespecial   java/io/RandomAccessFile.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    25: astore          4
        //    27: aload           4
        //    29: aload_0        
        //    30: getfield        com/facebook/soloader/UnpackingSoSource$1.val$deps:[B
        //    33: invokevirtual   java/io/RandomAccessFile.write:([B)V
        //    36: aload           4
        //    38: aload           4
        //    40: invokevirtual   java/io/RandomAccessFile.getFilePointer:()J
        //    43: invokevirtual   java/io/RandomAccessFile.setLength:(J)V
        //    46: aload           4
        //    48: ifnull          60
        //    51: iconst_0       
        //    52: ifeq            233
        //    55: aload           4
        //    57: invokevirtual   java/io/RandomAccessFile.close:()V
        //    60: new             Ljava/io/RandomAccessFile;
        //    63: dup            
        //    64: new             Ljava/io/File;
        //    67: dup            
        //    68: aload_0        
        //    69: getfield        com/facebook/soloader/UnpackingSoSource$1.this$0:Lcom/facebook/soloader/UnpackingSoSource;
        //    72: getfield        com/facebook/soloader/UnpackingSoSource.soDirectory:Ljava/io/File;
        //    75: ldc             "dso_manifest"
        //    77: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    80: ldc             "rw"
        //    82: invokespecial   java/io/RandomAccessFile.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    85: astore_3       
        //    86: aload_0        
        //    87: getfield        com/facebook/soloader/UnpackingSoSource$1.val$manifest:Lcom/facebook/soloader/UnpackingSoSource$DsoManifest;
        //    90: aload_3        
        //    91: invokevirtual   com/facebook/soloader/UnpackingSoSource$DsoManifest.write:(Ljava/io/DataOutput;)V
        //    94: aload_3        
        //    95: ifnull          106
        //    98: iconst_0       
        //    99: ifeq            287
        //   102: aload_3        
        //   103: invokevirtual   java/io/RandomAccessFile.close:()V
        //   106: aload_0        
        //   107: getfield        com/facebook/soloader/UnpackingSoSource$1.this$0:Lcom/facebook/soloader/UnpackingSoSource;
        //   110: getfield        com/facebook/soloader/UnpackingSoSource.soDirectory:Ljava/io/File;
        //   113: invokestatic    com/facebook/soloader/SysUtil.fsyncRecursive:(Ljava/io/File;)V
        //   116: aload_0        
        //   117: getfield        com/facebook/soloader/UnpackingSoSource$1.val$stateFileName:Ljava/io/File;
        //   120: iconst_1       
        //   121: invokestatic    com/facebook/soloader/UnpackingSoSource.access$000:(Ljava/io/File;B)V
        //   124: ldc             "fb-UnpackingSoSource"
        //   126: new             Ljava/lang/StringBuilder;
        //   129: dup            
        //   130: invokespecial   java/lang/StringBuilder.<init>:()V
        //   133: ldc             "releasing dso store lock for "
        //   135: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   138: aload_0        
        //   139: getfield        com/facebook/soloader/UnpackingSoSource$1.this$0:Lcom/facebook/soloader/UnpackingSoSource;
        //   142: getfield        com/facebook/soloader/UnpackingSoSource.soDirectory:Ljava/io/File;
        //   145: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   148: ldc             " (from syncer thread)"
        //   150: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   153: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   156: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //   159: pop            
        //   160: aload_0        
        //   161: getfield        com/facebook/soloader/UnpackingSoSource$1.val$lock:Lcom/facebook/soloader/FileLocker;
        //   164: invokevirtual   com/facebook/soloader/FileLocker.close:()V
        //   167: return         
        //   168: astore_1       
        //   169: new             Ljava/lang/NullPointerException;
        //   172: dup            
        //   173: invokespecial   java/lang/NullPointerException.<init>:()V
        //   176: athrow         
        //   177: astore_1       
        //   178: ldc             "fb-UnpackingSoSource"
        //   180: new             Ljava/lang/StringBuilder;
        //   183: dup            
        //   184: invokespecial   java/lang/StringBuilder.<init>:()V
        //   187: ldc             "releasing dso store lock for "
        //   189: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   192: aload_0        
        //   193: getfield        com/facebook/soloader/UnpackingSoSource$1.this$0:Lcom/facebook/soloader/UnpackingSoSource;
        //   196: getfield        com/facebook/soloader/UnpackingSoSource.soDirectory:Ljava/io/File;
        //   199: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   202: ldc             " (from syncer thread)"
        //   204: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   207: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   210: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //   213: pop            
        //   214: aload_0        
        //   215: getfield        com/facebook/soloader/UnpackingSoSource$1.val$lock:Lcom/facebook/soloader/FileLocker;
        //   218: invokevirtual   com/facebook/soloader/FileLocker.close:()V
        //   221: aload_1        
        //   222: athrow         
        //   223: astore_1       
        //   224: new             Ljava/lang/RuntimeException;
        //   227: dup            
        //   228: aload_1        
        //   229: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //   232: athrow         
        //   233: aload           4
        //   235: invokevirtual   java/io/RandomAccessFile.close:()V
        //   238: goto            60
        //   241: astore_2       
        //   242: aload_2        
        //   243: athrow         
        //   244: astore_1       
        //   245: aload           4
        //   247: ifnull          259
        //   250: aload_2        
        //   251: ifnull          270
        //   254: aload           4
        //   256: invokevirtual   java/io/RandomAccessFile.close:()V
        //   259: aload_1        
        //   260: athrow         
        //   261: astore_3       
        //   262: aload_2        
        //   263: aload_3        
        //   264: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   267: goto            259
        //   270: aload           4
        //   272: invokevirtual   java/io/RandomAccessFile.close:()V
        //   275: goto            259
        //   278: astore_1       
        //   279: new             Ljava/lang/NullPointerException;
        //   282: dup            
        //   283: invokespecial   java/lang/NullPointerException.<init>:()V
        //   286: athrow         
        //   287: aload_3        
        //   288: invokevirtual   java/io/RandomAccessFile.close:()V
        //   291: goto            106
        //   294: astore_2       
        //   295: aload_2        
        //   296: athrow         
        //   297: astore_1       
        //   298: aload_3        
        //   299: ifnull          310
        //   302: aload_2        
        //   303: ifnull          321
        //   306: aload_3        
        //   307: invokevirtual   java/io/RandomAccessFile.close:()V
        //   310: aload_1        
        //   311: athrow         
        //   312: astore_3       
        //   313: aload_2        
        //   314: aload_3        
        //   315: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   318: goto            310
        //   321: aload_3        
        //   322: invokevirtual   java/io/RandomAccessFile.close:()V
        //   325: goto            310
        //   328: astore_1       
        //   329: goto            298
        //   332: astore_1       
        //   333: aload_3        
        //   334: astore_2       
        //   335: goto            245
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  4      27     177    223    Any
        //  27     46     241    245    Ljava/lang/Throwable;
        //  27     46     332    338    Any
        //  55     60     168    177    Ljava/lang/Throwable;
        //  55     60     177    223    Any
        //  60     86     177    223    Any
        //  86     94     294    298    Ljava/lang/Throwable;
        //  86     94     328    332    Any
        //  102    106    278    287    Ljava/lang/Throwable;
        //  102    106    177    223    Any
        //  106    124    177    223    Any
        //  124    167    223    233    Ljava/io/IOException;
        //  169    177    177    223    Any
        //  178    223    223    233    Ljava/io/IOException;
        //  233    238    177    223    Any
        //  242    244    244    245    Any
        //  254    259    261    270    Ljava/lang/Throwable;
        //  254    259    177    223    Any
        //  259    261    177    223    Any
        //  262    267    177    223    Any
        //  270    275    177    223    Any
        //  279    287    177    223    Any
        //  287    291    177    223    Any
        //  295    297    297    298    Any
        //  306    310    312    321    Ljava/lang/Throwable;
        //  306    310    177    223    Any
        //  310    312    177    223    Any
        //  313    318    177    223    Any
        //  321    325    177    223    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 167, Size: 167
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
