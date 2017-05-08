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
        //     3: astore_1       
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
        //    25: astore_3       
        //    26: aload_3        
        //    27: aload_0        
        //    28: getfield        com/facebook/soloader/UnpackingSoSource$1.val$deps:[B
        //    31: invokevirtual   java/io/RandomAccessFile.write:([B)V
        //    34: aload_3        
        //    35: aload_3        
        //    36: invokevirtual   java/io/RandomAccessFile.getFilePointer:()J
        //    39: invokevirtual   java/io/RandomAccessFile.setLength:(J)V
        //    42: aload_3        
        //    43: ifnull          54
        //    46: iconst_0       
        //    47: ifeq            227
        //    50: aload_3        
        //    51: invokevirtual   java/io/RandomAccessFile.close:()V
        //    54: new             Ljava/io/RandomAccessFile;
        //    57: dup            
        //    58: new             Ljava/io/File;
        //    61: dup            
        //    62: aload_0        
        //    63: getfield        com/facebook/soloader/UnpackingSoSource$1.this$0:Lcom/facebook/soloader/UnpackingSoSource;
        //    66: getfield        com/facebook/soloader/UnpackingSoSource.soDirectory:Ljava/io/File;
        //    69: ldc             "dso_manifest"
        //    71: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    74: ldc             "rw"
        //    76: invokespecial   java/io/RandomAccessFile.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    79: astore_3       
        //    80: aload_0        
        //    81: getfield        com/facebook/soloader/UnpackingSoSource$1.val$manifest:Lcom/facebook/soloader/UnpackingSoSource$DsoManifest;
        //    84: aload_3        
        //    85: invokevirtual   com/facebook/soloader/UnpackingSoSource$DsoManifest.write:(Ljava/io/DataOutput;)V
        //    88: aload_3        
        //    89: ifnull          100
        //    92: iconst_0       
        //    93: ifeq            277
        //    96: aload_3        
        //    97: invokevirtual   java/io/RandomAccessFile.close:()V
        //   100: aload_0        
        //   101: getfield        com/facebook/soloader/UnpackingSoSource$1.this$0:Lcom/facebook/soloader/UnpackingSoSource;
        //   104: getfield        com/facebook/soloader/UnpackingSoSource.soDirectory:Ljava/io/File;
        //   107: invokestatic    com/facebook/soloader/SysUtil.fsyncRecursive:(Ljava/io/File;)V
        //   110: aload_0        
        //   111: getfield        com/facebook/soloader/UnpackingSoSource$1.val$stateFileName:Ljava/io/File;
        //   114: iconst_1       
        //   115: invokestatic    com/facebook/soloader/UnpackingSoSource.access$000:(Ljava/io/File;B)V
        //   118: ldc             "fb-UnpackingSoSource"
        //   120: new             Ljava/lang/StringBuilder;
        //   123: dup            
        //   124: invokespecial   java/lang/StringBuilder.<init>:()V
        //   127: ldc             "releasing dso store lock for "
        //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   132: aload_0        
        //   133: getfield        com/facebook/soloader/UnpackingSoSource$1.this$0:Lcom/facebook/soloader/UnpackingSoSource;
        //   136: getfield        com/facebook/soloader/UnpackingSoSource.soDirectory:Ljava/io/File;
        //   139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   142: ldc             " (from syncer thread)"
        //   144: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   147: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   150: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //   153: pop            
        //   154: aload_0        
        //   155: getfield        com/facebook/soloader/UnpackingSoSource$1.val$lock:Lcom/facebook/soloader/FileLocker;
        //   158: invokevirtual   com/facebook/soloader/FileLocker.close:()V
        //   161: return         
        //   162: astore_1       
        //   163: new             Ljava/lang/NullPointerException;
        //   166: dup            
        //   167: invokespecial   java/lang/NullPointerException.<init>:()V
        //   170: athrow         
        //   171: astore_1       
        //   172: ldc             "fb-UnpackingSoSource"
        //   174: new             Ljava/lang/StringBuilder;
        //   177: dup            
        //   178: invokespecial   java/lang/StringBuilder.<init>:()V
        //   181: ldc             "releasing dso store lock for "
        //   183: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   186: aload_0        
        //   187: getfield        com/facebook/soloader/UnpackingSoSource$1.this$0:Lcom/facebook/soloader/UnpackingSoSource;
        //   190: getfield        com/facebook/soloader/UnpackingSoSource.soDirectory:Ljava/io/File;
        //   193: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   196: ldc             " (from syncer thread)"
        //   198: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   201: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   204: invokestatic    android/util/Log.v:(Ljava/lang/String;Ljava/lang/String;)I
        //   207: pop            
        //   208: aload_0        
        //   209: getfield        com/facebook/soloader/UnpackingSoSource$1.val$lock:Lcom/facebook/soloader/FileLocker;
        //   212: invokevirtual   com/facebook/soloader/FileLocker.close:()V
        //   215: aload_1        
        //   216: athrow         
        //   217: astore_1       
        //   218: new             Ljava/lang/RuntimeException;
        //   221: dup            
        //   222: aload_1        
        //   223: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //   226: athrow         
        //   227: aload_3        
        //   228: invokevirtual   java/io/RandomAccessFile.close:()V
        //   231: goto            54
        //   234: astore_1       
        //   235: aload_1        
        //   236: athrow         
        //   237: astore_2       
        //   238: aload_3        
        //   239: ifnull          250
        //   242: aload_1        
        //   243: ifnull          261
        //   246: aload_3        
        //   247: invokevirtual   java/io/RandomAccessFile.close:()V
        //   250: aload_2        
        //   251: athrow         
        //   252: astore_3       
        //   253: aload_1        
        //   254: aload_3        
        //   255: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   258: goto            250
        //   261: aload_3        
        //   262: invokevirtual   java/io/RandomAccessFile.close:()V
        //   265: goto            250
        //   268: astore_1       
        //   269: new             Ljava/lang/NullPointerException;
        //   272: dup            
        //   273: invokespecial   java/lang/NullPointerException.<init>:()V
        //   276: athrow         
        //   277: aload_3        
        //   278: invokevirtual   java/io/RandomAccessFile.close:()V
        //   281: goto            100
        //   284: astore_2       
        //   285: aload_2        
        //   286: athrow         
        //   287: astore_1       
        //   288: aload_3        
        //   289: ifnull          300
        //   292: aload_2        
        //   293: ifnull          311
        //   296: aload_3        
        //   297: invokevirtual   java/io/RandomAccessFile.close:()V
        //   300: aload_1        
        //   301: athrow         
        //   302: astore_3       
        //   303: aload_2        
        //   304: aload_3        
        //   305: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   308: goto            300
        //   311: aload_3        
        //   312: invokevirtual   java/io/RandomAccessFile.close:()V
        //   315: goto            300
        //   318: astore_1       
        //   319: goto            288
        //   322: astore_2       
        //   323: goto            238
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  4      26     171    217    Any
        //  26     42     234    238    Ljava/lang/Throwable;
        //  26     42     322    326    Any
        //  50     54     162    171    Ljava/lang/Throwable;
        //  50     54     171    217    Any
        //  54     80     171    217    Any
        //  80     88     284    288    Ljava/lang/Throwable;
        //  80     88     318    322    Any
        //  96     100    268    277    Ljava/lang/Throwable;
        //  96     100    171    217    Any
        //  100    118    171    217    Any
        //  118    161    217    227    Ljava/io/IOException;
        //  163    171    171    217    Any
        //  172    217    217    227    Ljava/io/IOException;
        //  227    231    171    217    Any
        //  235    237    237    238    Any
        //  246    250    252    261    Ljava/lang/Throwable;
        //  246    250    171    217    Any
        //  250    252    171    217    Any
        //  253    258    171    217    Any
        //  261    265    171    217    Any
        //  269    277    171    217    Any
        //  277    281    171    217    Any
        //  285    287    287    288    Any
        //  296    300    302    311    Ljava/lang/Throwable;
        //  296    300    171    217    Any
        //  300    302    171    217    Any
        //  303    308    171    217    Any
        //  311    315    171    217    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 165, Size: 165
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
