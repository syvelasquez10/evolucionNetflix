// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.camera;

import com.facebook.react.bridge.ReactContext;
import android.net.Uri;
import com.facebook.react.bridge.Promise;
import android.content.Context;
import com.facebook.react.bridge.GuardedAsyncTask;

class CameraRollManager$SaveToCameraRoll extends GuardedAsyncTask<Void, Void>
{
    private final Context mContext;
    private final Promise mPromise;
    private final CameraRollManager$MediaType mType;
    private final Uri mUri;
    
    public CameraRollManager$SaveToCameraRoll(final ReactContext mContext, final Uri mUri, final CameraRollManager$MediaType mType, final Promise mPromise) {
        super(mContext);
        this.mContext = (Context)mContext;
        this.mUri = mUri;
        this.mPromise = mPromise;
        this.mType = mType;
    }
    
    @Override
    protected void doInBackgroundGuarded(final Void... p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          6
        //     3: aconst_null    
        //     4: astore          7
        //     6: new             Ljava/io/File;
        //     9: dup            
        //    10: aload_0        
        //    11: getfield        com/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll.mUri:Landroid/net/Uri;
        //    14: invokevirtual   android/net/Uri.getPath:()Ljava/lang/String;
        //    17: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    20: astore          9
        //    22: aload_0        
        //    23: getfield        com/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll.mType:Lcom/facebook/react/modules/camera/CameraRollManager$MediaType;
        //    26: getstatic       com/facebook/react/modules/camera/CameraRollManager$MediaType.PHOTO:Lcom/facebook/react/modules/camera/CameraRollManager$MediaType;
        //    29: if_acmpne       89
        //    32: getstatic       android/os/Environment.DIRECTORY_PICTURES:Ljava/lang/String;
        //    35: invokestatic    android/os/Environment.getExternalStoragePublicDirectory:(Ljava/lang/String;)Ljava/io/File;
        //    38: astore_1       
        //    39: aload_1        
        //    40: invokevirtual   java/io/File.mkdirs:()Z
        //    43: pop            
        //    44: aload_1        
        //    45: invokevirtual   java/io/File.isDirectory:()Z
        //    48: ifne            99
        //    51: aload_0        
        //    52: getfield        com/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll.mPromise:Lcom/facebook/react/bridge/Promise;
        //    55: ldc             "E_UNABLE_TO_LOAD"
        //    57: ldc             "External media storage directory not available"
        //    59: invokeinterface com/facebook/react/bridge/Promise.reject:(Ljava/lang/String;Ljava/lang/String;)V
        //    64: iconst_0       
        //    65: ifeq            76
        //    68: new             Ljava/lang/NullPointerException;
        //    71: dup            
        //    72: invokespecial   java/lang/NullPointerException.<init>:()V
        //    75: athrow         
        //    76: iconst_0       
        //    77: ifeq            88
        //    80: new             Ljava/lang/NullPointerException;
        //    83: dup            
        //    84: invokespecial   java/lang/NullPointerException.<init>:()V
        //    87: athrow         
        //    88: return         
        //    89: getstatic       android/os/Environment.DIRECTORY_MOVIES:Ljava/lang/String;
        //    92: invokestatic    android/os/Environment.getExternalStoragePublicDirectory:(Ljava/lang/String;)Ljava/io/File;
        //    95: astore_1       
        //    96: goto            39
        //    99: new             Ljava/io/File;
        //   102: dup            
        //   103: aload_1        
        //   104: aload           9
        //   106: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   109: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   112: astore_3       
        //   113: aload           9
        //   115: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   118: astore          8
        //   120: aload           8
        //   122: bipush          46
        //   124: invokevirtual   java/lang/String.indexOf:(I)I
        //   127: iflt            546
        //   130: aload           8
        //   132: iconst_0       
        //   133: aload           8
        //   135: bipush          46
        //   137: invokevirtual   java/lang/String.lastIndexOf:(I)I
        //   140: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   143: astore          5
        //   145: aload           8
        //   147: aload           8
        //   149: bipush          46
        //   151: invokevirtual   java/lang/String.lastIndexOf:(I)I
        //   154: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   157: astore          4
        //   159: iconst_0       
        //   160: istore_2       
        //   161: aload_3        
        //   162: invokevirtual   java/io/File.createNewFile:()Z
        //   165: ifne            213
        //   168: new             Ljava/io/File;
        //   171: dup            
        //   172: aload_1        
        //   173: new             Ljava/lang/StringBuilder;
        //   176: dup            
        //   177: invokespecial   java/lang/StringBuilder.<init>:()V
        //   180: aload           5
        //   182: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   185: ldc             "_"
        //   187: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   190: iload_2        
        //   191: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   194: aload           4
        //   196: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   199: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   202: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   205: astore_3       
        //   206: iload_2        
        //   207: iconst_1       
        //   208: iadd           
        //   209: istore_2       
        //   210: goto            161
        //   213: new             Ljava/io/FileInputStream;
        //   216: dup            
        //   217: aload           9
        //   219: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //   222: invokevirtual   java/io/FileInputStream.getChannel:()Ljava/nio/channels/FileChannel;
        //   225: astore_1       
        //   226: new             Ljava/io/FileOutputStream;
        //   229: dup            
        //   230: aload_3        
        //   231: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //   234: invokevirtual   java/io/FileOutputStream.getChannel:()Ljava/nio/channels/FileChannel;
        //   237: astore          4
        //   239: aload           4
        //   241: aload_1        
        //   242: lconst_0       
        //   243: aload_1        
        //   244: invokevirtual   java/nio/channels/FileChannel.size:()J
        //   247: invokevirtual   java/nio/channels/FileChannel.transferFrom:(Ljava/nio/channels/ReadableByteChannel;JJ)J
        //   250: pop2           
        //   251: aload_1        
        //   252: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   255: aload           4
        //   257: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   260: aload_0        
        //   261: getfield        com/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll.mContext:Landroid/content/Context;
        //   264: astore          5
        //   266: aload_3        
        //   267: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   270: astore_3       
        //   271: new             Lcom/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll$1;
        //   274: dup            
        //   275: aload_0        
        //   276: invokespecial   com/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll$1.<init>:(Lcom/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll;)V
        //   279: astore          6
        //   281: aload           5
        //   283: iconst_1       
        //   284: anewarray       Ljava/lang/String;
        //   287: dup            
        //   288: iconst_0       
        //   289: aload_3        
        //   290: aastore        
        //   291: aconst_null    
        //   292: aload           6
        //   294: invokestatic    android/media/MediaScannerConnection.scanFile:(Landroid/content/Context;[Ljava/lang/String;[Ljava/lang/String;Landroid/media/MediaScannerConnection$OnScanCompletedListener;)V
        //   297: aload_1        
        //   298: ifnull          312
        //   301: aload_1        
        //   302: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   305: ifeq            312
        //   308: aload_1        
        //   309: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   312: aload           4
        //   314: ifnull          88
        //   317: aload           4
        //   319: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   322: ifeq            88
        //   325: aload           4
        //   327: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   330: return         
        //   331: astore_1       
        //   332: ldc             "React"
        //   334: ldc             "Could not close output channel"
        //   336: aload_1        
        //   337: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   340: return         
        //   341: astore_1       
        //   342: ldc             "React"
        //   344: ldc             "Could not close input channel"
        //   346: aload_1        
        //   347: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   350: goto            312
        //   353: astore          4
        //   355: aconst_null    
        //   356: astore_1       
        //   357: aload           7
        //   359: astore_3       
        //   360: aload_0        
        //   361: getfield        com/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll.mPromise:Lcom/facebook/react/bridge/Promise;
        //   364: aload           4
        //   366: invokeinterface com/facebook/react/bridge/Promise.reject:(Ljava/lang/Throwable;)V
        //   371: aload_3        
        //   372: ifnull          386
        //   375: aload_3        
        //   376: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   379: ifeq            386
        //   382: aload_3        
        //   383: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   386: aload_1        
        //   387: ifnull          88
        //   390: aload_1        
        //   391: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   394: ifeq            88
        //   397: aload_1        
        //   398: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   401: return         
        //   402: astore_1       
        //   403: ldc             "React"
        //   405: ldc             "Could not close output channel"
        //   407: aload_1        
        //   408: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   411: return         
        //   412: astore_3       
        //   413: ldc             "React"
        //   415: ldc             "Could not close input channel"
        //   417: aload_3        
        //   418: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   421: goto            386
        //   424: astore_3       
        //   425: aconst_null    
        //   426: astore_1       
        //   427: aload           6
        //   429: astore          4
        //   431: aload_1        
        //   432: ifnull          446
        //   435: aload_1        
        //   436: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   439: ifeq            446
        //   442: aload_1        
        //   443: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   446: aload           4
        //   448: ifnull          464
        //   451: aload           4
        //   453: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   456: ifeq            464
        //   459: aload           4
        //   461: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   464: aload_3        
        //   465: athrow         
        //   466: astore_1       
        //   467: ldc             "React"
        //   469: ldc             "Could not close input channel"
        //   471: aload_1        
        //   472: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   475: goto            446
        //   478: astore_1       
        //   479: ldc             "React"
        //   481: ldc             "Could not close output channel"
        //   483: aload_1        
        //   484: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   487: goto            464
        //   490: astore_3       
        //   491: aload           6
        //   493: astore          4
        //   495: goto            431
        //   498: astore_3       
        //   499: goto            431
        //   502: astore          6
        //   504: aload_3        
        //   505: astore          4
        //   507: aload_1        
        //   508: astore          5
        //   510: aload           6
        //   512: astore_3       
        //   513: aload           4
        //   515: astore_1       
        //   516: aload           5
        //   518: astore          4
        //   520: goto            431
        //   523: astore          4
        //   525: aload_1        
        //   526: astore_3       
        //   527: aconst_null    
        //   528: astore_1       
        //   529: goto            360
        //   532: astore          5
        //   534: aload_1        
        //   535: astore_3       
        //   536: aload           4
        //   538: astore_1       
        //   539: aload           5
        //   541: astore          4
        //   543: goto            360
        //   546: ldc             ""
        //   548: astore          4
        //   550: aload           8
        //   552: astore          5
        //   554: iconst_0       
        //   555: istore_2       
        //   556: goto            161
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  22     39     353    360    Ljava/io/IOException;
        //  22     39     424    431    Any
        //  39     64     353    360    Ljava/io/IOException;
        //  39     64     424    431    Any
        //  89     96     353    360    Ljava/io/IOException;
        //  89     96     424    431    Any
        //  99     159    353    360    Ljava/io/IOException;
        //  99     159    424    431    Any
        //  161    206    353    360    Ljava/io/IOException;
        //  161    206    424    431    Any
        //  213    226    353    360    Ljava/io/IOException;
        //  213    226    424    431    Any
        //  226    239    523    532    Ljava/io/IOException;
        //  226    239    490    498    Any
        //  239    297    532    546    Ljava/io/IOException;
        //  239    297    498    502    Any
        //  308    312    341    353    Ljava/io/IOException;
        //  325    330    331    341    Ljava/io/IOException;
        //  360    371    502    523    Any
        //  382    386    412    424    Ljava/io/IOException;
        //  397    401    402    412    Ljava/io/IOException;
        //  442    446    466    478    Ljava/io/IOException;
        //  459    464    478    490    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 279, Size: 279
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
