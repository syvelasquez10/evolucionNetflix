// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.camera;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableArray;
import android.content.Context;
import com.facebook.react.bridge.GuardedAsyncTask;

class CameraRollManager$GetPhotosTask extends GuardedAsyncTask<Void, Void>
{
    private final String mAfter;
    private final Context mContext;
    private final int mFirst;
    private final String mGroupName;
    private final ReadableArray mMimeTypes;
    private final Promise mPromise;
    
    private CameraRollManager$GetPhotosTask(final ReactContext mContext, final int mFirst, final String mAfter, final String mGroupName, final ReadableArray mMimeTypes, final Promise mPromise) {
        super(mContext);
        this.mContext = (Context)mContext;
        this.mFirst = mFirst;
        this.mAfter = mAfter;
        this.mGroupName = mGroupName;
        this.mMimeTypes = mMimeTypes;
        this.mPromise = mPromise;
    }
    
    @Override
    protected void doInBackgroundGuarded(final Void... p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/lang/StringBuilder;
        //     3: dup            
        //     4: ldc             "1"
        //     6: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //     9: astore          4
        //    11: new             Ljava/util/ArrayList;
        //    14: dup            
        //    15: invokespecial   java/util/ArrayList.<init>:()V
        //    18: astore          5
        //    20: aload_0        
        //    21: getfield        com/facebook/react/modules/camera/CameraRollManager$GetPhotosTask.mAfter:Ljava/lang/String;
        //    24: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    27: ifne            50
        //    30: aload           4
        //    32: ldc             " AND datetaken < ?"
        //    34: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    37: pop            
        //    38: aload           5
        //    40: aload_0        
        //    41: getfield        com/facebook/react/modules/camera/CameraRollManager$GetPhotosTask.mAfter:Ljava/lang/String;
        //    44: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    49: pop            
        //    50: aload_0        
        //    51: getfield        com/facebook/react/modules/camera/CameraRollManager$GetPhotosTask.mGroupName:Ljava/lang/String;
        //    54: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    57: ifne            80
        //    60: aload           4
        //    62: ldc             " AND bucket_display_name = ?"
        //    64: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    67: pop            
        //    68: aload           5
        //    70: aload_0        
        //    71: getfield        com/facebook/react/modules/camera/CameraRollManager$GetPhotosTask.mGroupName:Ljava/lang/String;
        //    74: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    79: pop            
        //    80: aload_0        
        //    81: getfield        com/facebook/react/modules/camera/CameraRollManager$GetPhotosTask.mMimeTypes:Lcom/facebook/react/bridge/ReadableArray;
        //    84: ifnull          175
        //    87: aload_0        
        //    88: getfield        com/facebook/react/modules/camera/CameraRollManager$GetPhotosTask.mMimeTypes:Lcom/facebook/react/bridge/ReadableArray;
        //    91: invokeinterface com/facebook/react/bridge/ReadableArray.size:()I
        //    96: ifle            175
        //    99: aload           4
        //   101: ldc             " AND mime_type IN ("
        //   103: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   106: pop            
        //   107: iconst_0       
        //   108: istore_2       
        //   109: iload_2        
        //   110: aload_0        
        //   111: getfield        com/facebook/react/modules/camera/CameraRollManager$GetPhotosTask.mMimeTypes:Lcom/facebook/react/bridge/ReadableArray;
        //   114: invokeinterface com/facebook/react/bridge/ReadableArray.size:()I
        //   119: if_icmpge       155
        //   122: aload           4
        //   124: ldc             "?,"
        //   126: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   129: pop            
        //   130: aload           5
        //   132: aload_0        
        //   133: getfield        com/facebook/react/modules/camera/CameraRollManager$GetPhotosTask.mMimeTypes:Lcom/facebook/react/bridge/ReadableArray;
        //   136: iload_2        
        //   137: invokeinterface com/facebook/react/bridge/ReadableArray.getString:(I)Ljava/lang/String;
        //   142: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   147: pop            
        //   148: iload_2        
        //   149: iconst_1       
        //   150: iadd           
        //   151: istore_2       
        //   152: goto            109
        //   155: aload           4
        //   157: aload           4
        //   159: invokevirtual   java/lang/StringBuilder.length:()I
        //   162: iconst_1       
        //   163: isub           
        //   164: aload           4
        //   166: invokevirtual   java/lang/StringBuilder.length:()I
        //   169: ldc             ")"
        //   171: invokevirtual   java/lang/StringBuilder.replace:(IILjava/lang/String;)Ljava/lang/StringBuilder;
        //   174: pop            
        //   175: new             Lcom/facebook/react/bridge/WritableNativeMap;
        //   178: dup            
        //   179: invokespecial   com/facebook/react/bridge/WritableNativeMap.<init>:()V
        //   182: astore_1       
        //   183: aload_0        
        //   184: getfield        com/facebook/react/modules/camera/CameraRollManager$GetPhotosTask.mContext:Landroid/content/Context;
        //   187: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //   190: astore_3       
        //   191: aload_3        
        //   192: getstatic       android/provider/MediaStore$Images$Media.EXTERNAL_CONTENT_URI:Landroid/net/Uri;
        //   195: invokestatic    com/facebook/react/modules/camera/CameraRollManager.access$200:()[Ljava/lang/String;
        //   198: aload           4
        //   200: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   203: aload           5
        //   205: aload           5
        //   207: invokeinterface java/util/List.size:()I
        //   212: anewarray       Ljava/lang/String;
        //   215: invokeinterface java/util/List.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   220: checkcast       [Ljava/lang/String;
        //   223: new             Ljava/lang/StringBuilder;
        //   226: dup            
        //   227: invokespecial   java/lang/StringBuilder.<init>:()V
        //   230: ldc             "datetaken DESC, date_modified DESC LIMIT "
        //   232: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   235: aload_0        
        //   236: getfield        com/facebook/react/modules/camera/CameraRollManager$GetPhotosTask.mFirst:I
        //   239: iconst_1       
        //   240: iadd           
        //   241: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   244: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   247: invokevirtual   android/content/ContentResolver.query:(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   250: astore          4
        //   252: aload           4
        //   254: ifnonnull       271
        //   257: aload_0        
        //   258: getfield        com/facebook/react/modules/camera/CameraRollManager$GetPhotosTask.mPromise:Lcom/facebook/react/bridge/Promise;
        //   261: ldc             "E_UNABLE_TO_LOAD"
        //   263: ldc             "Could not get photos"
        //   265: invokeinterface com/facebook/react/bridge/Promise.reject:(Ljava/lang/String;Ljava/lang/String;)V
        //   270: return         
        //   271: aload_3        
        //   272: aload           4
        //   274: aload_1        
        //   275: aload_0        
        //   276: getfield        com/facebook/react/modules/camera/CameraRollManager$GetPhotosTask.mFirst:I
        //   279: invokestatic    com/facebook/react/modules/camera/CameraRollManager.access$300:(Landroid/content/ContentResolver;Landroid/database/Cursor;Lcom/facebook/react/bridge/WritableMap;I)V
        //   282: aload           4
        //   284: aload_1        
        //   285: aload_0        
        //   286: getfield        com/facebook/react/modules/camera/CameraRollManager$GetPhotosTask.mFirst:I
        //   289: invokestatic    com/facebook/react/modules/camera/CameraRollManager.access$400:(Landroid/database/Cursor;Lcom/facebook/react/bridge/WritableMap;I)V
        //   292: aload           4
        //   294: invokeinterface android/database/Cursor.close:()V
        //   299: aload_0        
        //   300: getfield        com/facebook/react/modules/camera/CameraRollManager$GetPhotosTask.mPromise:Lcom/facebook/react/bridge/Promise;
        //   303: aload_1        
        //   304: invokeinterface com/facebook/react/bridge/Promise.resolve:(Ljava/lang/Object;)V
        //   309: return         
        //   310: astore_1       
        //   311: aload_0        
        //   312: getfield        com/facebook/react/modules/camera/CameraRollManager$GetPhotosTask.mPromise:Lcom/facebook/react/bridge/Promise;
        //   315: ldc             "E_UNABLE_TO_LOAD_PERMISSION"
        //   317: ldc             "Could not get photos: need READ_EXTERNAL_STORAGE permission"
        //   319: aload_1        
        //   320: invokeinterface com/facebook/react/bridge/Promise.reject:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   325: return         
        //   326: astore_3       
        //   327: aload           4
        //   329: invokeinterface android/database/Cursor.close:()V
        //   334: aload_0        
        //   335: getfield        com/facebook/react/modules/camera/CameraRollManager$GetPhotosTask.mPromise:Lcom/facebook/react/bridge/Promise;
        //   338: aload_1        
        //   339: invokeinterface com/facebook/react/bridge/Promise.resolve:(Ljava/lang/Object;)V
        //   344: aload_3        
        //   345: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                         
        //  -----  -----  -----  -----  -----------------------------
        //  191    252    310    326    Ljava/lang/SecurityException;
        //  257    270    310    326    Ljava/lang/SecurityException;
        //  271    292    326    346    Any
        //  292    309    310    326    Ljava/lang/SecurityException;
        //  327    346    310    326    Ljava/lang/SecurityException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0271:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
