// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.io.OutputStream;
import java.io.Closeable;
import com.facebook.internal.Utility;
import java.io.IOException;
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
    
    static void cacheUrlRedirect(final Context context, final URL url, final URL url2) {
        if (url == null || url2 == null) {
            return;
        }
        Closeable openPutStream = null;
        try {
            ((OutputStream)(openPutStream = getCache(context).openPutStream(url.toString(), UrlRedirectCache.REDIRECT_CONTENT_TAG))).write(url2.toString().getBytes());
        }
        catch (IOException ex) {}
        finally {
            Utility.closeQuietly(openPutStream);
        }
    }
    
    static FileLruCache getCache(final Context context) throws IOException {
        synchronized (UrlRedirectCache.class) {
            if (UrlRedirectCache.urlRedirectCache == null) {
                UrlRedirectCache.urlRedirectCache = new FileLruCache(context.getApplicationContext(), UrlRedirectCache.TAG, new FileLruCache.Limits());
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
        //     0: aload_1        
        //     1: ifnonnull       6
        //     4: aconst_null    
        //     5: areturn        
        //     6: aload_1        
        //     7: invokevirtual   java/net/URL.toString:()Ljava/lang/String;
        //    10: astore_1       
        //    11: aconst_null    
        //    12: astore          7
        //    14: aconst_null    
        //    15: astore          5
        //    17: aconst_null    
        //    18: astore          6
        //    20: aconst_null    
        //    21: astore          4
        //    23: aload_0        
        //    24: invokestatic    com/facebook/widget/UrlRedirectCache.getCache:(Landroid/content/Context;)Lcom/facebook/internal/FileLruCache;
        //    27: astore          8
        //    29: iconst_0       
        //    30: istore_2       
        //    31: aconst_null    
        //    32: astore_0       
        //    33: aload           8
        //    35: aload_1        
        //    36: getstatic       com/facebook/widget/UrlRedirectCache.REDIRECT_CONTENT_TAG:Ljava/lang/String;
        //    39: invokevirtual   com/facebook/internal/FileLruCache.get:(Ljava/lang/String;Ljava/lang/String;)Ljava/io/InputStream;
        //    42: astore          4
        //    44: aload           4
        //    46: ifnull          184
        //    49: iconst_1       
        //    50: istore_2       
        //    51: new             Ljava/io/InputStreamReader;
        //    54: dup            
        //    55: aload           4
        //    57: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    60: astore_1       
        //    61: aload_1        
        //    62: astore          4
        //    64: aload_1        
        //    65: astore          5
        //    67: aload_1        
        //    68: astore          6
        //    70: sipush          128
        //    73: newarray        C
        //    75: astore_0       
        //    76: aload_1        
        //    77: astore          4
        //    79: aload_1        
        //    80: astore          5
        //    82: aload_1        
        //    83: astore          6
        //    85: new             Ljava/lang/StringBuilder;
        //    88: dup            
        //    89: invokespecial   java/lang/StringBuilder.<init>:()V
        //    92: astore          9
        //    94: aload_1        
        //    95: astore          4
        //    97: aload_1        
        //    98: astore          5
        //   100: aload_1        
        //   101: astore          6
        //   103: aload_1        
        //   104: aload_0        
        //   105: iconst_0       
        //   106: aload_0        
        //   107: arraylength    
        //   108: invokevirtual   java/io/InputStreamReader.read:([CII)I
        //   111: istore_3       
        //   112: iload_3        
        //   113: ifle            145
        //   116: aload_1        
        //   117: astore          4
        //   119: aload_1        
        //   120: astore          5
        //   122: aload_1        
        //   123: astore          6
        //   125: aload           9
        //   127: aload_0        
        //   128: iconst_0       
        //   129: iload_3        
        //   130: invokevirtual   java/lang/StringBuilder.append:([CII)Ljava/lang/StringBuilder;
        //   133: pop            
        //   134: goto            94
        //   137: astore_0       
        //   138: aload           4
        //   140: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   143: aconst_null    
        //   144: areturn        
        //   145: aload_1        
        //   146: astore          4
        //   148: aload_1        
        //   149: astore          5
        //   151: aload_1        
        //   152: astore          6
        //   154: aload_1        
        //   155: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   158: aload_1        
        //   159: astore          4
        //   161: aload_1        
        //   162: astore          5
        //   164: aload_1        
        //   165: astore          6
        //   167: aload           9
        //   169: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   172: astore_0       
        //   173: aload_0        
        //   174: astore          4
        //   176: aload_1        
        //   177: astore_0       
        //   178: aload           4
        //   180: astore_1       
        //   181: goto            33
        //   184: aload           7
        //   186: astore          4
        //   188: iload_2        
        //   189: ifeq            202
        //   192: new             Ljava/net/URL;
        //   195: dup            
        //   196: aload_1        
        //   197: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   200: astore          4
        //   202: aload_0        
        //   203: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   206: aload           4
        //   208: areturn        
        //   209: astore_0       
        //   210: aload           5
        //   212: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   215: aconst_null    
        //   216: areturn        
        //   217: astore_0       
        //   218: aload           6
        //   220: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   223: aload_0        
        //   224: athrow         
        //   225: astore_1       
        //   226: aload_0        
        //   227: astore          6
        //   229: aload_1        
        //   230: astore_0       
        //   231: goto            218
        //   234: astore_1       
        //   235: aload_0        
        //   236: astore          5
        //   238: goto            210
        //   241: astore_1       
        //   242: aload_0        
        //   243: astore          4
        //   245: goto            138
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  23     29     137    138    Ljava/net/MalformedURLException;
        //  23     29     209    210    Ljava/io/IOException;
        //  23     29     217    218    Any
        //  33     44     241    248    Ljava/net/MalformedURLException;
        //  33     44     234    241    Ljava/io/IOException;
        //  33     44     225    234    Any
        //  51     61     241    248    Ljava/net/MalformedURLException;
        //  51     61     234    241    Ljava/io/IOException;
        //  51     61     225    234    Any
        //  70     76     137    138    Ljava/net/MalformedURLException;
        //  70     76     209    210    Ljava/io/IOException;
        //  70     76     217    218    Any
        //  85     94     137    138    Ljava/net/MalformedURLException;
        //  85     94     209    210    Ljava/io/IOException;
        //  85     94     217    218    Any
        //  103    112    137    138    Ljava/net/MalformedURLException;
        //  103    112    209    210    Ljava/io/IOException;
        //  103    112    217    218    Any
        //  125    134    137    138    Ljava/net/MalformedURLException;
        //  125    134    209    210    Ljava/io/IOException;
        //  125    134    217    218    Any
        //  154    158    137    138    Ljava/net/MalformedURLException;
        //  154    158    209    210    Ljava/io/IOException;
        //  154    158    217    218    Any
        //  167    173    137    138    Ljava/net/MalformedURLException;
        //  167    173    209    210    Ljava/io/IOException;
        //  167    173    217    218    Any
        //  192    202    241    248    Ljava/net/MalformedURLException;
        //  192    202    234    241    Ljava/io/IOException;
        //  192    202    225    234    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0033:
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
