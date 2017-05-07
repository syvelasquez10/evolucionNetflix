// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.OutputStream;
import com.facebook.LoggingBehavior;
import java.io.IOException;
import java.io.Closeable;
import java.net.URI;
import android.content.Context;

class UrlRedirectCache
{
    private static final String REDIRECT_CONTENT_TAG;
    static final String TAG;
    private static volatile FileLruCache urlRedirectCache;
    
    static {
        TAG = UrlRedirectCache.class.getSimpleName();
        REDIRECT_CONTENT_TAG = UrlRedirectCache.TAG + "_Redirect";
    }
    
    static void cacheUriRedirect(Context ex, final URI uri, final URI uri2) {
        if (uri == null || uri2 == null) {
            return;
        }
        Closeable openPutStream = null;
        try {
            final Object o;
            ex = (IOException)(o = (openPutStream = getCache((Context)ex).openPutStream(uri.toString(), UrlRedirectCache.REDIRECT_CONTENT_TAG)));
            final URI uri3 = uri2;
            final String s = uri3.toString();
            final byte[] array = s.getBytes();
            ((OutputStream)o).write(array);
            final IOException ex2 = ex;
            Utility.closeQuietly((Closeable)ex2);
            return;
        }
        catch (IOException ex) {
            Utility.closeQuietly(openPutStream);
            return;
        }
        finally {
            final IOException ex3;
            ex = ex3;
            final Object o2 = null;
            final IOException ex4 = ex;
        }
        while (true) {
            try {
                final Object o = ex;
                final URI uri3 = uri2;
                final String s = uri3.toString();
                final byte[] array = s.getBytes();
                ((OutputStream)o).write(array);
                final IOException ex2 = ex;
                Utility.closeQuietly((Closeable)ex2);
                return;
                final Object o2;
                Utility.closeQuietly((Closeable)o2);
                throw;
            }
            finally {
                final Object o2 = ex;
                continue;
            }
            break;
        }
    }
    
    static void clearCache(final Context context) {
        try {
            getCache(context).clearCache();
        }
        catch (IOException ex) {
            Logger.log(LoggingBehavior.CACHE, 5, UrlRedirectCache.TAG, "clearCache failed " + ex.getMessage());
        }
    }
    
    static FileLruCache getCache(final Context context) {
        synchronized (UrlRedirectCache.class) {
            if (UrlRedirectCache.urlRedirectCache == null) {
                UrlRedirectCache.urlRedirectCache = new FileLruCache(context.getApplicationContext(), UrlRedirectCache.TAG, new FileLruCache$Limits());
            }
            return UrlRedirectCache.urlRedirectCache;
        }
    }
    
    static URI getRedirectedUri(final Context p0, final URI p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_2       
        //     2: aload_1        
        //     3: ifnonnull       8
        //     6: aconst_null    
        //     7: areturn        
        //     8: aload_1        
        //     9: invokevirtual   java/net/URI.toString:()Ljava/lang/String;
        //    12: astore_1       
        //    13: aload_0        
        //    14: invokestatic    com/facebook/internal/UrlRedirectCache.getCache:(Landroid/content/Context;)Lcom/facebook/internal/FileLruCache;
        //    17: astore          4
        //    19: aconst_null    
        //    20: astore_0       
        //    21: aload           4
        //    23: aload_1        
        //    24: getstatic       com/facebook/internal/UrlRedirectCache.REDIRECT_CONTENT_TAG:Ljava/lang/String;
        //    27: invokevirtual   com/facebook/internal/FileLruCache.get:(Ljava/lang/String;Ljava/lang/String;)Ljava/io/InputStream;
        //    30: astore_3       
        //    31: aload_3        
        //    32: ifnull          107
        //    35: new             Ljava/io/InputStreamReader;
        //    38: dup            
        //    39: aload_3        
        //    40: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    43: astore_1       
        //    44: sipush          128
        //    47: newarray        C
        //    49: astore_0       
        //    50: new             Ljava/lang/StringBuilder;
        //    53: dup            
        //    54: invokespecial   java/lang/StringBuilder.<init>:()V
        //    57: astore_3       
        //    58: aload_1        
        //    59: aload_0        
        //    60: iconst_0       
        //    61: aload_0        
        //    62: arraylength    
        //    63: invokevirtual   java/io/InputStreamReader.read:([CII)I
        //    66: istore_2       
        //    67: iload_2        
        //    68: ifle            89
        //    71: aload_3        
        //    72: aload_0        
        //    73: iconst_0       
        //    74: iload_2        
        //    75: invokevirtual   java/lang/StringBuilder.append:([CII)Ljava/lang/StringBuilder;
        //    78: pop            
        //    79: goto            58
        //    82: astore_0       
        //    83: aload_1        
        //    84: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    87: aconst_null    
        //    88: areturn        
        //    89: aload_1        
        //    90: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    93: aload_3        
        //    94: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    97: astore_3       
        //    98: aload_1        
        //    99: astore_0       
        //   100: iconst_1       
        //   101: istore_2       
        //   102: aload_3        
        //   103: astore_1       
        //   104: goto            21
        //   107: iload_2        
        //   108: ifeq            126
        //   111: new             Ljava/net/URI;
        //   114: dup            
        //   115: aload_1        
        //   116: invokespecial   java/net/URI.<init>:(Ljava/lang/String;)V
        //   119: astore_1       
        //   120: aload_0        
        //   121: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   124: aload_1        
        //   125: areturn        
        //   126: aload_0        
        //   127: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   130: aconst_null    
        //   131: areturn        
        //   132: astore_0       
        //   133: aconst_null    
        //   134: astore_1       
        //   135: aload_1        
        //   136: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   139: aconst_null    
        //   140: areturn        
        //   141: astore_0       
        //   142: aconst_null    
        //   143: astore_1       
        //   144: aload_1        
        //   145: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   148: aload_0        
        //   149: athrow         
        //   150: astore_0       
        //   151: goto            144
        //   154: astore_3       
        //   155: aload_0        
        //   156: astore_1       
        //   157: aload_3        
        //   158: astore_0       
        //   159: goto            144
        //   162: astore_0       
        //   163: goto            135
        //   166: astore_1       
        //   167: aload_0        
        //   168: astore_1       
        //   169: goto            135
        //   172: astore_0       
        //   173: aconst_null    
        //   174: astore_1       
        //   175: goto            83
        //   178: astore_1       
        //   179: aload_0        
        //   180: astore_1       
        //   181: goto            83
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                         
        //  -----  -----  -----  -----  -----------------------------
        //  13     19     172    178    Ljava/net/URISyntaxException;
        //  13     19     132    135    Ljava/io/IOException;
        //  13     19     141    144    Any
        //  21     31     178    184    Ljava/net/URISyntaxException;
        //  21     31     166    172    Ljava/io/IOException;
        //  21     31     154    162    Any
        //  35     44     178    184    Ljava/net/URISyntaxException;
        //  35     44     166    172    Ljava/io/IOException;
        //  35     44     154    162    Any
        //  44     58     82     83     Ljava/net/URISyntaxException;
        //  44     58     162    166    Ljava/io/IOException;
        //  44     58     150    154    Any
        //  58     67     82     83     Ljava/net/URISyntaxException;
        //  58     67     162    166    Ljava/io/IOException;
        //  58     67     150    154    Any
        //  71     79     82     83     Ljava/net/URISyntaxException;
        //  71     79     162    166    Ljava/io/IOException;
        //  71     79     150    154    Any
        //  89     98     82     83     Ljava/net/URISyntaxException;
        //  89     98     162    166    Ljava/io/IOException;
        //  89     98     150    154    Any
        //  111    120    178    184    Ljava/net/URISyntaxException;
        //  111    120    166    172    Ljava/io/IOException;
        //  111    120    154    162    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
