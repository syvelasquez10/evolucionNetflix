// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.io.OutputStream;
import com.facebook.internal.FileLruCache$Limits;
import java.io.IOException;
import java.io.Closeable;
import com.facebook.internal.Utility;
import java.net.URL;
import android.content.Context;
import com.facebook.internal.FileLruCache;

class UrlRedirectCache
{
    private static final String REDIRECT_CONTENT_TAG;
    static final String TAG;
    private static volatile FileLruCache urlRedirectCache;
    
    static {
        TAG = UrlRedirectCache.class.getSimpleName();
        REDIRECT_CONTENT_TAG = UrlRedirectCache.TAG + "_Redirect";
    }
    
    static void cacheUrlRedirect(Context ex, final URL url, final URL url2) {
        if (url == null || url2 == null) {
            return;
        }
        Closeable openPutStream = null;
        try {
            final Object o;
            ex = (IOException)(o = (openPutStream = getCache((Context)ex).openPutStream(url.toString(), UrlRedirectCache.REDIRECT_CONTENT_TAG)));
            final URL url3 = url2;
            final String s = url3.toString();
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
                final URL url3 = url2;
                final String s = url3.toString();
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
    
    static FileLruCache getCache(final Context context) {
        synchronized (UrlRedirectCache.class) {
            if (UrlRedirectCache.urlRedirectCache == null) {
                UrlRedirectCache.urlRedirectCache = new FileLruCache(context.getApplicationContext(), UrlRedirectCache.TAG, new FileLruCache$Limits());
            }
            return UrlRedirectCache.urlRedirectCache;
        }
    }
    
    static URL getRedirectedUrl(final Context p0, final URL p1) {
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
        //     9: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //    12: astore_1       
        //    13: aload_0        
        //    14: invokestatic    com/facebook/widget/UrlRedirectCache.getCache:(Landroid/content/Context;)Lcom/facebook/internal/FileLruCache;
        //    17: astore          4
        //    19: aconst_null    
        //    20: astore_0       
        //    21: aload           4
        //    23: aload_1        
        //    24: getstatic       com/facebook/widget/UrlRedirectCache.REDIRECT_CONTENT_TAG:Ljava/lang/String;
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
        //   108: ifeq            178
        //   111: new             Ljava/net/URL;
        //   114: dup            
        //   115: aload_1        
        //   116: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   119: astore_1       
        //   120: aload_0        
        //   121: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   124: aload_1        
        //   125: areturn        
        //   126: astore_0       
        //   127: aconst_null    
        //   128: astore_1       
        //   129: aload_1        
        //   130: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   133: aconst_null    
        //   134: areturn        
        //   135: astore_1       
        //   136: aconst_null    
        //   137: astore_0       
        //   138: aload_0        
        //   139: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   142: aload_1        
        //   143: athrow         
        //   144: astore_3       
        //   145: aload_1        
        //   146: astore_0       
        //   147: aload_3        
        //   148: astore_1       
        //   149: goto            138
        //   152: astore_1       
        //   153: goto            138
        //   156: astore_0       
        //   157: goto            129
        //   160: astore_1       
        //   161: aload_0        
        //   162: astore_1       
        //   163: goto            129
        //   166: astore_0       
        //   167: aconst_null    
        //   168: astore_1       
        //   169: goto            83
        //   172: astore_1       
        //   173: aload_0        
        //   174: astore_1       
        //   175: goto            83
        //   178: aconst_null    
        //   179: astore_1       
        //   180: goto            120
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  13     19     166    172    Ljava/net/MalformedURLException;
        //  13     19     126    129    Ljava/io/IOException;
        //  13     19     135    138    Any
        //  21     31     172    178    Ljava/net/MalformedURLException;
        //  21     31     160    166    Ljava/io/IOException;
        //  21     31     152    156    Any
        //  35     44     172    178    Ljava/net/MalformedURLException;
        //  35     44     160    166    Ljava/io/IOException;
        //  35     44     152    156    Any
        //  44     58     82     83     Ljava/net/MalformedURLException;
        //  44     58     156    160    Ljava/io/IOException;
        //  44     58     144    152    Any
        //  58     67     82     83     Ljava/net/MalformedURLException;
        //  58     67     156    160    Ljava/io/IOException;
        //  58     67     144    152    Any
        //  71     79     82     83     Ljava/net/MalformedURLException;
        //  71     79     156    160    Ljava/io/IOException;
        //  71     79     144    152    Any
        //  89     98     82     83     Ljava/net/MalformedURLException;
        //  89     98     156    160    Ljava/io/IOException;
        //  89     98     144    152    Any
        //  111    120    172    178    Ljava/net/MalformedURLException;
        //  111    120    160    166    Ljava/io/IOException;
        //  111    120    152    156    Any
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
