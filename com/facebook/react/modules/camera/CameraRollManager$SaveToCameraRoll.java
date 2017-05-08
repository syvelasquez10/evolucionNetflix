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
    private final Uri mUri;
    
    public CameraRollManager$SaveToCameraRoll(final ReactContext mContext, final Uri mUri, final Promise mPromise) {
        super(mContext);
        this.mContext = (Context)mContext;
        this.mUri = mUri;
        this.mPromise = mPromise;
    }
    
    @Override
    protected void doInBackgroundGuarded(final Void... p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          5
        //     3: aconst_null    
        //     4: astore          6
        //     6: new             Ljava/io/File;
        //     9: dup            
        //    10: aload_0        
        //    11: getfield        com/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll.mUri:Landroid/net/Uri;
        //    14: invokevirtual   android/net/Uri.getPath:()Ljava/lang/String;
        //    17: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    20: astore          9
        //    22: getstatic       android/os/Environment.DIRECTORY_DCIM:Ljava/lang/String;
        //    25: invokestatic    android/os/Environment.getExternalStoragePublicDirectory:(Ljava/lang/String;)Ljava/io/File;
        //    28: astore          8
        //    30: aload           8
        //    32: invokevirtual   java/io/File.mkdirs:()Z
        //    35: pop            
        //    36: aload           8
        //    38: invokevirtual   java/io/File.isDirectory:()Z
        //    41: ifne            82
        //    44: aload_0        
        //    45: getfield        com/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll.mPromise:Lcom/facebook/react/bridge/Promise;
        //    48: ldc             "E_UNABLE_TO_LOAD"
        //    50: ldc             "External media storage directory not available"
        //    52: invokeinterface com/facebook/react/bridge/Promise.reject:(Ljava/lang/String;Ljava/lang/String;)V
        //    57: iconst_0       
        //    58: ifeq            69
        //    61: new             Ljava/lang/NullPointerException;
        //    64: dup            
        //    65: invokespecial   java/lang/NullPointerException.<init>:()V
        //    68: athrow         
        //    69: iconst_0       
        //    70: ifeq            81
        //    73: new             Ljava/lang/NullPointerException;
        //    76: dup            
        //    77: invokespecial   java/lang/NullPointerException.<init>:()V
        //    80: athrow         
        //    81: return         
        //    82: new             Ljava/io/File;
        //    85: dup            
        //    86: aload           8
        //    88: aload           9
        //    90: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //    93: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    96: astore_1       
        //    97: aload           9
        //    99: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   102: astore_3       
        //   103: aload_3        
        //   104: bipush          46
        //   106: invokevirtual   java/lang/String.indexOf:(I)I
        //   109: iflt            525
        //   112: aload_3        
        //   113: iconst_0       
        //   114: aload_3        
        //   115: bipush          46
        //   117: invokevirtual   java/lang/String.lastIndexOf:(I)I
        //   120: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   123: astore          4
        //   125: aload_3        
        //   126: aload_3        
        //   127: bipush          46
        //   129: invokevirtual   java/lang/String.lastIndexOf:(I)I
        //   132: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   135: astore          7
        //   137: iconst_0       
        //   138: istore_2       
        //   139: aload_1        
        //   140: astore_3       
        //   141: aload           7
        //   143: astore_1       
        //   144: aload_3        
        //   145: invokevirtual   java/io/File.createNewFile:()Z
        //   148: ifne            196
        //   151: new             Ljava/io/File;
        //   154: dup            
        //   155: aload           8
        //   157: new             Ljava/lang/StringBuilder;
        //   160: dup            
        //   161: invokespecial   java/lang/StringBuilder.<init>:()V
        //   164: aload           4
        //   166: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   169: ldc             "_"
        //   171: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   174: iload_2        
        //   175: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   178: aload_1        
        //   179: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   182: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   185: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   188: astore_3       
        //   189: iload_2        
        //   190: iconst_1       
        //   191: iadd           
        //   192: istore_2       
        //   193: goto            144
        //   196: new             Ljava/io/FileInputStream;
        //   199: dup            
        //   200: aload           9
        //   202: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //   205: invokevirtual   java/io/FileInputStream.getChannel:()Ljava/nio/channels/FileChannel;
        //   208: astore_1       
        //   209: new             Ljava/io/FileOutputStream;
        //   212: dup            
        //   213: aload_3        
        //   214: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //   217: invokevirtual   java/io/FileOutputStream.getChannel:()Ljava/nio/channels/FileChannel;
        //   220: astore          4
        //   222: aload           4
        //   224: aload_1        
        //   225: lconst_0       
        //   226: aload_1        
        //   227: invokevirtual   java/nio/channels/FileChannel.size:()J
        //   230: invokevirtual   java/nio/channels/FileChannel.transferFrom:(Ljava/nio/channels/ReadableByteChannel;JJ)J
        //   233: pop2           
        //   234: aload_1        
        //   235: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   238: aload           4
        //   240: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   243: aload_0        
        //   244: getfield        com/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll.mContext:Landroid/content/Context;
        //   247: astore          5
        //   249: aload_3        
        //   250: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   253: astore          6
        //   255: new             Lcom/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll$1;
        //   258: dup            
        //   259: aload_0        
        //   260: invokespecial   com/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll$1.<init>:(Lcom/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll;)V
        //   263: astore_3       
        //   264: aload           5
        //   266: iconst_1       
        //   267: anewarray       Ljava/lang/String;
        //   270: dup            
        //   271: iconst_0       
        //   272: aload           6
        //   274: aastore        
        //   275: aconst_null    
        //   276: aload_3        
        //   277: invokestatic    android/media/MediaScannerConnection.scanFile:(Landroid/content/Context;[Ljava/lang/String;[Ljava/lang/String;Landroid/media/MediaScannerConnection$OnScanCompletedListener;)V
        //   280: aload_1        
        //   281: ifnull          295
        //   284: aload_1        
        //   285: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   288: ifeq            295
        //   291: aload_1        
        //   292: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   295: aload           4
        //   297: ifnull          81
        //   300: aload           4
        //   302: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   305: ifeq            81
        //   308: aload           4
        //   310: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   313: return         
        //   314: astore_1       
        //   315: ldc             "React"
        //   317: ldc             "Could not close output channel"
        //   319: aload_1        
        //   320: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   323: return         
        //   324: astore_1       
        //   325: ldc             "React"
        //   327: ldc             "Could not close input channel"
        //   329: aload_1        
        //   330: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   333: goto            295
        //   336: astore          4
        //   338: aconst_null    
        //   339: astore_1       
        //   340: aload           6
        //   342: astore_3       
        //   343: aload_0        
        //   344: getfield        com/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll.mPromise:Lcom/facebook/react/bridge/Promise;
        //   347: aload           4
        //   349: invokeinterface com/facebook/react/bridge/Promise.reject:(Ljava/lang/Throwable;)V
        //   354: aload_3        
        //   355: ifnull          369
        //   358: aload_3        
        //   359: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   362: ifeq            369
        //   365: aload_3        
        //   366: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   369: aload_1        
        //   370: ifnull          81
        //   373: aload_1        
        //   374: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   377: ifeq            81
        //   380: aload_1        
        //   381: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   384: return         
        //   385: astore_1       
        //   386: ldc             "React"
        //   388: ldc             "Could not close output channel"
        //   390: aload_1        
        //   391: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   394: return         
        //   395: astore_3       
        //   396: ldc             "React"
        //   398: ldc             "Could not close input channel"
        //   400: aload_3        
        //   401: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   404: goto            369
        //   407: astore_3       
        //   408: aconst_null    
        //   409: astore_1       
        //   410: aload           5
        //   412: astore          4
        //   414: aload_1        
        //   415: ifnull          429
        //   418: aload_1        
        //   419: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   422: ifeq            429
        //   425: aload_1        
        //   426: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   429: aload           4
        //   431: ifnull          447
        //   434: aload           4
        //   436: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   439: ifeq            447
        //   442: aload           4
        //   444: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   447: aload_3        
        //   448: athrow         
        //   449: astore_1       
        //   450: ldc             "React"
        //   452: ldc             "Could not close input channel"
        //   454: aload_1        
        //   455: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   458: goto            429
        //   461: astore_1       
        //   462: ldc             "React"
        //   464: ldc             "Could not close output channel"
        //   466: aload_1        
        //   467: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   470: goto            447
        //   473: astore_3       
        //   474: aload           5
        //   476: astore          4
        //   478: goto            414
        //   481: astore_3       
        //   482: goto            414
        //   485: astore          6
        //   487: aload_3        
        //   488: astore          5
        //   490: aload_1        
        //   491: astore          4
        //   493: aload           6
        //   495: astore_3       
        //   496: aload           5
        //   498: astore_1       
        //   499: goto            414
        //   502: astore          4
        //   504: aload_1        
        //   505: astore_3       
        //   506: aconst_null    
        //   507: astore_1       
        //   508: goto            343
        //   511: astore          5
        //   513: aload_1        
        //   514: astore_3       
        //   515: aload           4
        //   517: astore_1       
        //   518: aload           5
        //   520: astore          4
        //   522: goto            343
        //   525: ldc             ""
        //   527: astore          7
        //   529: aload_3        
        //   530: astore          4
        //   532: aload_1        
        //   533: astore_3       
        //   534: iconst_0       
        //   535: istore_2       
        //   536: aload           7
        //   538: astore_1       
        //   539: goto            144
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  22     57     336    343    Ljava/io/IOException;
        //  22     57     407    414    Any
        //  82     137    336    343    Ljava/io/IOException;
        //  82     137    407    414    Any
        //  144    189    336    343    Ljava/io/IOException;
        //  144    189    407    414    Any
        //  196    209    336    343    Ljava/io/IOException;
        //  196    209    407    414    Any
        //  209    222    502    511    Ljava/io/IOException;
        //  209    222    473    481    Any
        //  222    280    511    525    Ljava/io/IOException;
        //  222    280    481    485    Any
        //  291    295    324    336    Ljava/io/IOException;
        //  308    313    314    324    Ljava/io/IOException;
        //  343    354    485    502    Any
        //  365    369    395    407    Ljava/io/IOException;
        //  380    384    385    395    Ljava/io/IOException;
        //  425    429    449    461    Ljava/io/IOException;
        //  442    447    461    473    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 277, Size: 277
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
