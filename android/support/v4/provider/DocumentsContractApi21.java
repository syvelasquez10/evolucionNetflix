// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.provider;

import android.provider.DocumentsContract;
import android.net.Uri;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(21)
class DocumentsContractApi21
{
    private static final String TAG = "DocumentFile";
    
    private static void closeQuietly(final AutoCloseable autoCloseable) {
        if (autoCloseable == null) {
            return;
        }
        try {
            autoCloseable.close();
        }
        catch (RuntimeException ex) {
            throw ex;
        }
        catch (Exception ex2) {}
    }
    
    public static Uri createDirectory(final Context context, final Uri uri, final String s) {
        return createFile(context, uri, "vnd.android.document/directory", s);
    }
    
    public static Uri createFile(final Context context, final Uri uri, final String s, final String s2) {
        return DocumentsContract.createDocument(context.getContentResolver(), uri, s, s2);
    }
    
    public static Uri[] listFiles(final Context p0, final Uri p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //     4: astore_0       
        //     5: aload_1        
        //     6: aload_1        
        //     7: invokestatic    android/provider/DocumentsContract.getDocumentId:(Landroid/net/Uri;)Ljava/lang/String;
        //    10: invokestatic    android/provider/DocumentsContract.buildChildDocumentsUriUsingTree:(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
        //    13: astore_2       
        //    14: new             Ljava/util/ArrayList;
        //    17: dup            
        //    18: invokespecial   java/util/ArrayList.<init>:()V
        //    21: astore_3       
        //    22: aload_0        
        //    23: aload_2        
        //    24: iconst_1       
        //    25: anewarray       Ljava/lang/String;
        //    28: dup            
        //    29: iconst_0       
        //    30: ldc             "document_id"
        //    32: aastore        
        //    33: aconst_null    
        //    34: aconst_null    
        //    35: aconst_null    
        //    36: invokevirtual   android/content/ContentResolver.query:(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    39: astore_2       
        //    40: aload_2        
        //    41: astore_0       
        //    42: aload_2        
        //    43: invokeinterface android/database/Cursor.moveToNext:()Z
        //    48: ifeq            119
        //    51: aload_2        
        //    52: astore_0       
        //    53: aload_3        
        //    54: aload_1        
        //    55: aload_2        
        //    56: iconst_0       
        //    57: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //    62: invokestatic    android/provider/DocumentsContract.buildDocumentUriUsingTree:(Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
        //    65: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //    68: pop            
        //    69: goto            40
        //    72: astore_1       
        //    73: aload_2        
        //    74: astore_0       
        //    75: ldc             "DocumentFile"
        //    77: new             Ljava/lang/StringBuilder;
        //    80: dup            
        //    81: invokespecial   java/lang/StringBuilder.<init>:()V
        //    84: ldc             "Failed query: "
        //    86: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    89: aload_1        
        //    90: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    93: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    96: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    99: pop            
        //   100: aload_2        
        //   101: invokestatic    android/support/v4/provider/DocumentsContractApi21.closeQuietly:(Ljava/lang/AutoCloseable;)V
        //   104: aload_3        
        //   105: aload_3        
        //   106: invokevirtual   java/util/ArrayList.size:()I
        //   109: anewarray       Landroid/net/Uri;
        //   112: invokevirtual   java/util/ArrayList.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   115: checkcast       [Landroid/net/Uri;
        //   118: areturn        
        //   119: aload_2        
        //   120: invokestatic    android/support/v4/provider/DocumentsContractApi21.closeQuietly:(Ljava/lang/AutoCloseable;)V
        //   123: goto            104
        //   126: astore_1       
        //   127: aconst_null    
        //   128: astore_0       
        //   129: aload_0        
        //   130: invokestatic    android/support/v4/provider/DocumentsContractApi21.closeQuietly:(Ljava/lang/AutoCloseable;)V
        //   133: aload_1        
        //   134: athrow         
        //   135: astore_1       
        //   136: goto            129
        //   139: astore_1       
        //   140: aconst_null    
        //   141: astore_2       
        //   142: goto            73
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  22     40     139    145    Ljava/lang/Exception;
        //  22     40     126    129    Any
        //  42     51     72     73     Ljava/lang/Exception;
        //  42     51     135    139    Any
        //  53     69     72     73     Ljava/lang/Exception;
        //  53     69     135    139    Any
        //  75     100    135    139    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0073:
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
    
    public static Uri prepareTreeUri(final Uri uri) {
        return DocumentsContract.buildDocumentUriUsingTree(uri, DocumentsContract.getTreeDocumentId(uri));
    }
    
    public static Uri renameTo(final Context context, final Uri uri, final String s) {
        return DocumentsContract.renameDocument(context.getContentResolver(), uri, s);
    }
}
