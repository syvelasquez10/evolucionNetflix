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
        //   102: astore          5
        //   104: aload           5
        //   106: bipush          46
        //   108: invokevirtual   java/lang/String.indexOf:(I)I
        //   111: iflt            535
        //   114: aload           5
        //   116: iconst_0       
        //   117: aload           5
        //   119: bipush          46
        //   121: invokevirtual   java/lang/String.lastIndexOf:(I)I
        //   124: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   127: astore          4
        //   129: aload           5
        //   131: aload           5
        //   133: bipush          46
        //   135: invokevirtual   java/lang/String.lastIndexOf:(I)I
        //   138: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   141: astore          5
        //   143: iconst_0       
        //   144: istore_2       
        //   145: aload_1        
        //   146: astore_3       
        //   147: aload           4
        //   149: astore_1       
        //   150: aload           5
        //   152: astore          4
        //   154: aload_3        
        //   155: invokevirtual   java/io/File.createNewFile:()Z
        //   158: ifne            206
        //   161: new             Ljava/io/File;
        //   164: dup            
        //   165: aload           8
        //   167: new             Ljava/lang/StringBuilder;
        //   170: dup            
        //   171: invokespecial   java/lang/StringBuilder.<init>:()V
        //   174: aload_1        
        //   175: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   178: ldc             "_"
        //   180: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   183: iload_2        
        //   184: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   187: aload           4
        //   189: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   192: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   195: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   198: astore_3       
        //   199: iload_2        
        //   200: iconst_1       
        //   201: iadd           
        //   202: istore_2       
        //   203: goto            154
        //   206: new             Ljava/io/FileInputStream;
        //   209: dup            
        //   210: aload           9
        //   212: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //   215: invokevirtual   java/io/FileInputStream.getChannel:()Ljava/nio/channels/FileChannel;
        //   218: astore_1       
        //   219: new             Ljava/io/FileOutputStream;
        //   222: dup            
        //   223: aload_3        
        //   224: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //   227: invokevirtual   java/io/FileOutputStream.getChannel:()Ljava/nio/channels/FileChannel;
        //   230: astore          4
        //   232: aload           4
        //   234: aload_1        
        //   235: lconst_0       
        //   236: aload_1        
        //   237: invokevirtual   java/nio/channels/FileChannel.size:()J
        //   240: invokevirtual   java/nio/channels/FileChannel.transferFrom:(Ljava/nio/channels/ReadableByteChannel;JJ)J
        //   243: pop2           
        //   244: aload_1        
        //   245: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   248: aload           4
        //   250: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   253: aload_0        
        //   254: getfield        com/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll.mContext:Landroid/content/Context;
        //   257: astore          5
        //   259: aload_3        
        //   260: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   263: astore_3       
        //   264: new             Lcom/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll$1;
        //   267: dup            
        //   268: aload_0        
        //   269: invokespecial   com/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll$1.<init>:(Lcom/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll;)V
        //   272: astore          6
        //   274: aload           5
        //   276: iconst_1       
        //   277: anewarray       Ljava/lang/String;
        //   280: dup            
        //   281: iconst_0       
        //   282: aload_3        
        //   283: aastore        
        //   284: aconst_null    
        //   285: aload           6
        //   287: invokestatic    android/media/MediaScannerConnection.scanFile:(Landroid/content/Context;[Ljava/lang/String;[Ljava/lang/String;Landroid/media/MediaScannerConnection$OnScanCompletedListener;)V
        //   290: aload_1        
        //   291: ifnull          305
        //   294: aload_1        
        //   295: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   298: ifeq            305
        //   301: aload_1        
        //   302: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   305: aload           4
        //   307: ifnull          81
        //   310: aload           4
        //   312: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   315: ifeq            81
        //   318: aload           4
        //   320: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   323: return         
        //   324: astore_1       
        //   325: ldc             "React"
        //   327: ldc             "Could not close output channel"
        //   329: aload_1        
        //   330: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   333: return         
        //   334: astore_1       
        //   335: ldc             "React"
        //   337: ldc             "Could not close input channel"
        //   339: aload_1        
        //   340: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   343: goto            305
        //   346: astore          4
        //   348: aconst_null    
        //   349: astore_1       
        //   350: aload           7
        //   352: astore_3       
        //   353: aload_0        
        //   354: getfield        com/facebook/react/modules/camera/CameraRollManager$SaveToCameraRoll.mPromise:Lcom/facebook/react/bridge/Promise;
        //   357: aload           4
        //   359: invokeinterface com/facebook/react/bridge/Promise.reject:(Ljava/lang/Throwable;)V
        //   364: aload_3        
        //   365: ifnull          379
        //   368: aload_3        
        //   369: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   372: ifeq            379
        //   375: aload_3        
        //   376: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   379: aload_1        
        //   380: ifnull          81
        //   383: aload_1        
        //   384: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   387: ifeq            81
        //   390: aload_1        
        //   391: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   394: return         
        //   395: astore_1       
        //   396: ldc             "React"
        //   398: ldc             "Could not close output channel"
        //   400: aload_1        
        //   401: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   404: return         
        //   405: astore_3       
        //   406: ldc             "React"
        //   408: ldc             "Could not close input channel"
        //   410: aload_3        
        //   411: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   414: goto            379
        //   417: astore_3       
        //   418: aconst_null    
        //   419: astore_1       
        //   420: aload           6
        //   422: astore          4
        //   424: aload_1        
        //   425: ifnull          439
        //   428: aload_1        
        //   429: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   432: ifeq            439
        //   435: aload_1        
        //   436: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   439: aload           4
        //   441: ifnull          457
        //   444: aload           4
        //   446: invokevirtual   java/nio/channels/FileChannel.isOpen:()Z
        //   449: ifeq            457
        //   452: aload           4
        //   454: invokevirtual   java/nio/channels/FileChannel.close:()V
        //   457: aload_3        
        //   458: athrow         
        //   459: astore_1       
        //   460: ldc             "React"
        //   462: ldc             "Could not close input channel"
        //   464: aload_1        
        //   465: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   468: goto            439
        //   471: astore_1       
        //   472: ldc             "React"
        //   474: ldc             "Could not close output channel"
        //   476: aload_1        
        //   477: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   480: goto            457
        //   483: astore_3       
        //   484: aload           6
        //   486: astore          4
        //   488: goto            424
        //   491: astore_3       
        //   492: goto            424
        //   495: astore          6
        //   497: aload_3        
        //   498: astore          5
        //   500: aload_1        
        //   501: astore          4
        //   503: aload           6
        //   505: astore_3       
        //   506: aload           5
        //   508: astore_1       
        //   509: goto            424
        //   512: astore          4
        //   514: aload_1        
        //   515: astore_3       
        //   516: aconst_null    
        //   517: astore_1       
        //   518: goto            353
        //   521: astore          5
        //   523: aload_1        
        //   524: astore_3       
        //   525: aload           4
        //   527: astore_1       
        //   528: aload           5
        //   530: astore          4
        //   532: goto            353
        //   535: ldc             ""
        //   537: astore          4
        //   539: aload_1        
        //   540: astore_3       
        //   541: iconst_0       
        //   542: istore_2       
        //   543: aload           5
        //   545: astore_1       
        //   546: goto            154
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  22     57     346    353    Ljava/io/IOException;
        //  22     57     417    424    Any
        //  82     143    346    353    Ljava/io/IOException;
        //  82     143    417    424    Any
        //  154    199    346    353    Ljava/io/IOException;
        //  154    199    417    424    Any
        //  206    219    346    353    Ljava/io/IOException;
        //  206    219    417    424    Any
        //  219    232    512    521    Ljava/io/IOException;
        //  219    232    483    491    Any
        //  232    290    521    535    Ljava/io/IOException;
        //  232    290    491    495    Any
        //  301    305    334    346    Ljava/io/IOException;
        //  318    323    324    334    Ljava/io/IOException;
        //  353    364    495    512    Any
        //  375    379    405    417    Ljava/io/IOException;
        //  390    394    395    405    Ljava/io/IOException;
        //  435    439    459    471    Ljava/io/IOException;
        //  452    457    471    483    Ljava/io/IOException;
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
