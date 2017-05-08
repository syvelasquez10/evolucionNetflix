// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.provider;

import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.net.Uri;
import android.content.Context;

class DocumentsContractApi19
{
    private static final String TAG = "DocumentFile";
    
    public static boolean canRead(final Context context, final Uri uri) {
        return context.checkCallingOrSelfUriPermission(uri, 1) == 0 && !TextUtils.isEmpty((CharSequence)getRawType(context, uri));
    }
    
    public static boolean canWrite(final Context context, final Uri uri) {
        if (context.checkCallingOrSelfUriPermission(uri, 2) == 0) {
            final String rawType = getRawType(context, uri);
            final int queryForInt = queryForInt(context, uri, "flags", 0);
            if (!TextUtils.isEmpty((CharSequence)rawType)) {
                if ((queryForInt & 0x4) != 0x0) {
                    return true;
                }
                if ("vnd.android.document/directory".equals(rawType) && (queryForInt & 0x8) != 0x0) {
                    return true;
                }
                if (!TextUtils.isEmpty((CharSequence)rawType) && (queryForInt & 0x2) != 0x0) {
                    return true;
                }
            }
        }
        return false;
    }
    
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
    
    public static boolean delete(final Context context, final Uri uri) {
        return DocumentsContract.deleteDocument(context.getContentResolver(), uri);
    }
    
    public static boolean exists(final Context p0, final Uri p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //     4: astore_0       
        //     5: aload_0        
        //     6: aload_1        
        //     7: iconst_1       
        //     8: anewarray       Ljava/lang/String;
        //    11: dup            
        //    12: iconst_0       
        //    13: ldc             "document_id"
        //    15: aastore        
        //    16: aconst_null    
        //    17: aconst_null    
        //    18: aconst_null    
        //    19: invokevirtual   android/content/ContentResolver.query:(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    22: astore_1       
        //    23: aload_1        
        //    24: astore_0       
        //    25: aload_1        
        //    26: invokeinterface android/database/Cursor.getCount:()I
        //    31: istore_2       
        //    32: iload_2        
        //    33: ifle            44
        //    36: iconst_1       
        //    37: istore_3       
        //    38: aload_1        
        //    39: invokestatic    android/support/v4/provider/DocumentsContractApi19.closeQuietly:(Ljava/lang/AutoCloseable;)V
        //    42: iload_3        
        //    43: ireturn        
        //    44: iconst_0       
        //    45: istore_3       
        //    46: goto            38
        //    49: astore          4
        //    51: aconst_null    
        //    52: astore_1       
        //    53: aload_1        
        //    54: astore_0       
        //    55: ldc             "DocumentFile"
        //    57: new             Ljava/lang/StringBuilder;
        //    60: dup            
        //    61: invokespecial   java/lang/StringBuilder.<init>:()V
        //    64: ldc             "Failed query: "
        //    66: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    69: aload           4
        //    71: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    74: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    77: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    80: pop            
        //    81: aload_1        
        //    82: invokestatic    android/support/v4/provider/DocumentsContractApi19.closeQuietly:(Ljava/lang/AutoCloseable;)V
        //    85: iconst_0       
        //    86: ireturn        
        //    87: astore_1       
        //    88: aconst_null    
        //    89: astore_0       
        //    90: aload_0        
        //    91: invokestatic    android/support/v4/provider/DocumentsContractApi19.closeQuietly:(Ljava/lang/AutoCloseable;)V
        //    94: aload_1        
        //    95: athrow         
        //    96: astore_1       
        //    97: goto            90
        //   100: astore          4
        //   102: goto            53
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  5      23     49     53     Ljava/lang/Exception;
        //  5      23     87     90     Any
        //  25     32     100    105    Ljava/lang/Exception;
        //  25     32     96     100    Any
        //  55     81     96     100    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0038:
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
    
    public static String getName(final Context context, final Uri uri) {
        return queryForString(context, uri, "_display_name", null);
    }
    
    private static String getRawType(final Context context, final Uri uri) {
        return queryForString(context, uri, "mime_type", null);
    }
    
    public static String getType(final Context context, final Uri uri) {
        String rawType;
        if ("vnd.android.document/directory".equals(rawType = getRawType(context, uri))) {
            rawType = null;
        }
        return rawType;
    }
    
    public static boolean isDirectory(final Context context, final Uri uri) {
        return "vnd.android.document/directory".equals(getRawType(context, uri));
    }
    
    public static boolean isDocumentUri(final Context context, final Uri uri) {
        return DocumentsContract.isDocumentUri(context, uri);
    }
    
    public static boolean isFile(final Context context, final Uri uri) {
        final String rawType = getRawType(context, uri);
        return !"vnd.android.document/directory".equals(rawType) && !TextUtils.isEmpty((CharSequence)rawType);
    }
    
    public static long lastModified(final Context context, final Uri uri) {
        return queryForLong(context, uri, "last_modified", 0L);
    }
    
    public static long length(final Context context, final Uri uri) {
        return queryForLong(context, uri, "_size", 0L);
    }
    
    private static int queryForInt(final Context context, final Uri uri, final String s, final int n) {
        return (int)queryForLong(context, uri, s, n);
    }
    
    private static long queryForLong(final Context p0, final Uri p1, final String p2, final long p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //     4: astore_0       
        //     5: aload_0        
        //     6: aload_1        
        //     7: iconst_1       
        //     8: anewarray       Ljava/lang/String;
        //    11: dup            
        //    12: iconst_0       
        //    13: aload_2        
        //    14: aastore        
        //    15: aconst_null    
        //    16: aconst_null    
        //    17: aconst_null    
        //    18: invokevirtual   android/content/ContentResolver.query:(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    21: astore_1       
        //    22: aload_1        
        //    23: astore_0       
        //    24: aload_1        
        //    25: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    30: ifeq            63
        //    33: aload_1        
        //    34: astore_0       
        //    35: aload_1        
        //    36: iconst_0       
        //    37: invokeinterface android/database/Cursor.isNull:(I)Z
        //    42: ifne            63
        //    45: aload_1        
        //    46: astore_0       
        //    47: aload_1        
        //    48: iconst_0       
        //    49: invokeinterface android/database/Cursor.getLong:(I)J
        //    54: lstore          5
        //    56: aload_1        
        //    57: invokestatic    android/support/v4/provider/DocumentsContractApi19.closeQuietly:(Ljava/lang/AutoCloseable;)V
        //    60: lload           5
        //    62: lreturn        
        //    63: aload_1        
        //    64: invokestatic    android/support/v4/provider/DocumentsContractApi19.closeQuietly:(Ljava/lang/AutoCloseable;)V
        //    67: lload_3        
        //    68: lreturn        
        //    69: astore_2       
        //    70: aconst_null    
        //    71: astore_1       
        //    72: aload_1        
        //    73: astore_0       
        //    74: ldc             "DocumentFile"
        //    76: new             Ljava/lang/StringBuilder;
        //    79: dup            
        //    80: invokespecial   java/lang/StringBuilder.<init>:()V
        //    83: ldc             "Failed query: "
        //    85: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    88: aload_2        
        //    89: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    92: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    95: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    98: pop            
        //    99: aload_1        
        //   100: invokestatic    android/support/v4/provider/DocumentsContractApi19.closeQuietly:(Ljava/lang/AutoCloseable;)V
        //   103: lload_3        
        //   104: lreturn        
        //   105: astore_1       
        //   106: aconst_null    
        //   107: astore_0       
        //   108: aload_0        
        //   109: invokestatic    android/support/v4/provider/DocumentsContractApi19.closeQuietly:(Ljava/lang/AutoCloseable;)V
        //   112: aload_1        
        //   113: athrow         
        //   114: astore_1       
        //   115: goto            108
        //   118: astore_2       
        //   119: goto            72
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  5      22     69     72     Ljava/lang/Exception;
        //  5      22     105    108    Any
        //  24     33     118    122    Ljava/lang/Exception;
        //  24     33     114    118    Any
        //  35     45     118    122    Ljava/lang/Exception;
        //  35     45     114    118    Any
        //  47     56     118    122    Ljava/lang/Exception;
        //  47     56     114    118    Any
        //  74     99     114    118    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0063:
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
    
    private static String queryForString(final Context p0, final Uri p1, final String p2, final String p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   android/content/Context.getContentResolver:()Landroid/content/ContentResolver;
        //     4: astore_0       
        //     5: aload_0        
        //     6: aload_1        
        //     7: iconst_1       
        //     8: anewarray       Ljava/lang/String;
        //    11: dup            
        //    12: iconst_0       
        //    13: aload_2        
        //    14: aastore        
        //    15: aconst_null    
        //    16: aconst_null    
        //    17: aconst_null    
        //    18: invokevirtual   android/content/ContentResolver.query:(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    21: astore_1       
        //    22: aload_1        
        //    23: astore_0       
        //    24: aload_1        
        //    25: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    30: ifeq            61
        //    33: aload_1        
        //    34: astore_0       
        //    35: aload_1        
        //    36: iconst_0       
        //    37: invokeinterface android/database/Cursor.isNull:(I)Z
        //    42: ifne            61
        //    45: aload_1        
        //    46: astore_0       
        //    47: aload_1        
        //    48: iconst_0       
        //    49: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //    54: astore_2       
        //    55: aload_1        
        //    56: invokestatic    android/support/v4/provider/DocumentsContractApi19.closeQuietly:(Ljava/lang/AutoCloseable;)V
        //    59: aload_2        
        //    60: areturn        
        //    61: aload_1        
        //    62: invokestatic    android/support/v4/provider/DocumentsContractApi19.closeQuietly:(Ljava/lang/AutoCloseable;)V
        //    65: aload_3        
        //    66: areturn        
        //    67: astore_2       
        //    68: aconst_null    
        //    69: astore_1       
        //    70: aload_1        
        //    71: astore_0       
        //    72: ldc             "DocumentFile"
        //    74: new             Ljava/lang/StringBuilder;
        //    77: dup            
        //    78: invokespecial   java/lang/StringBuilder.<init>:()V
        //    81: ldc             "Failed query: "
        //    83: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    86: aload_2        
        //    87: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    90: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    93: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    96: pop            
        //    97: aload_1        
        //    98: invokestatic    android/support/v4/provider/DocumentsContractApi19.closeQuietly:(Ljava/lang/AutoCloseable;)V
        //   101: aload_3        
        //   102: areturn        
        //   103: astore_1       
        //   104: aconst_null    
        //   105: astore_0       
        //   106: aload_0        
        //   107: invokestatic    android/support/v4/provider/DocumentsContractApi19.closeQuietly:(Ljava/lang/AutoCloseable;)V
        //   110: aload_1        
        //   111: athrow         
        //   112: astore_1       
        //   113: goto            106
        //   116: astore_2       
        //   117: goto            70
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  5      22     67     70     Ljava/lang/Exception;
        //  5      22     103    106    Any
        //  24     33     116    120    Ljava/lang/Exception;
        //  24     33     112    116    Any
        //  35     45     116    120    Ljava/lang/Exception;
        //  35     45     112    116    Any
        //  47     55     116    120    Ljava/lang/Exception;
        //  47     55     112    116    Any
        //  72     97     112    116    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0061:
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
