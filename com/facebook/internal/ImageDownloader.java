// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.InputStream;
import java.net.URI;
import java.io.Closeable;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.os.Looper;
import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import android.os.Handler;

public class ImageDownloader
{
    private static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
    private static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
    private static WorkQueue cacheReadQueue;
    private static WorkQueue downloadQueue;
    private static Handler handler;
    private static final Map<ImageDownloader$RequestKey, ImageDownloader$DownloaderContext> pendingRequests;
    
    static {
        ImageDownloader.downloadQueue = new WorkQueue(8);
        ImageDownloader.cacheReadQueue = new WorkQueue(2);
        pendingRequests = new HashMap<ImageDownloader$RequestKey, ImageDownloader$DownloaderContext>();
    }
    
    public static boolean cancelRequest(final ImageRequest imageRequest) {
        while (true) {
            final ImageDownloader$RequestKey imageDownloader$RequestKey = new ImageDownloader$RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
            synchronized (ImageDownloader.pendingRequests) {
                final ImageDownloader$DownloaderContext imageDownloader$DownloaderContext = ImageDownloader.pendingRequests.get(imageDownloader$RequestKey);
                if (imageDownloader$DownloaderContext != null) {
                    boolean b;
                    if (imageDownloader$DownloaderContext.workItem.cancel()) {
                        ImageDownloader.pendingRequests.remove(imageDownloader$RequestKey);
                        b = true;
                    }
                    else {
                        imageDownloader$DownloaderContext.isCancelled = true;
                        b = true;
                    }
                    return b;
                }
            }
            return false;
        }
    }
    
    public static void clearCache(final Context context) {
        ImageResponseCache.clearCache(context);
        UrlRedirectCache.clearCache(context);
    }
    
    private static void download(final ImageDownloader$RequestKey p0, final Context p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          12
        //     3: aconst_null    
        //     4: astore          10
        //     6: aconst_null    
        //     7: astore          11
        //     9: iconst_1       
        //    10: istore_3       
        //    11: iconst_1       
        //    12: istore          4
        //    14: iconst_1       
        //    15: istore_2       
        //    16: new             Ljava/net/URL;
        //    19: dup            
        //    20: aload_0        
        //    21: getfield        com/facebook/internal/ImageDownloader$RequestKey.uri:Ljava/net/URI;
        //    24: invokevirtual   java/net/URI.toString:()Ljava/lang/String;
        //    27: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    30: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    33: checkcast       Ljava/net/HttpURLConnection;
        //    36: astore          6
        //    38: aload           6
        //    40: iconst_0       
        //    41: invokevirtual   java/net/HttpURLConnection.setInstanceFollowRedirects:(Z)V
        //    44: aload           6
        //    46: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //    49: lookupswitch {
        //              200: 346
        //              301: 245
        //              302: 245
        //          default: 589
        //        }
        //    84: aload           6
        //    86: invokevirtual   java/net/HttpURLConnection.getErrorStream:()Ljava/io/InputStream;
        //    89: astore          7
        //    91: aload           7
        //    93: astore          8
        //    95: aload           7
        //    97: astore          9
        //    99: aload           7
        //   101: astore          10
        //   103: new             Ljava/lang/StringBuilder;
        //   106: dup            
        //   107: invokespecial   java/lang/StringBuilder.<init>:()V
        //   110: astore          13
        //   112: aload           7
        //   114: ifnull          461
        //   117: aload           7
        //   119: astore          8
        //   121: aload           7
        //   123: astore          9
        //   125: aload           7
        //   127: astore          10
        //   129: new             Ljava/io/InputStreamReader;
        //   132: dup            
        //   133: aload           7
        //   135: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //   138: astore_1       
        //   139: aload           7
        //   141: astore          8
        //   143: aload           7
        //   145: astore          9
        //   147: aload           7
        //   149: astore          10
        //   151: sipush          128
        //   154: newarray        C
        //   156: astore          14
        //   158: aload           7
        //   160: astore          8
        //   162: aload           7
        //   164: astore          9
        //   166: aload           7
        //   168: astore          10
        //   170: aload_1        
        //   171: aload           14
        //   173: iconst_0       
        //   174: aload           14
        //   176: arraylength    
        //   177: invokevirtual   java/io/InputStreamReader.read:([CII)I
        //   180: istore          5
        //   182: iload           5
        //   184: ifle            380
        //   187: aload           7
        //   189: astore          8
        //   191: aload           7
        //   193: astore          9
        //   195: aload           7
        //   197: astore          10
        //   199: aload           13
        //   201: aload           14
        //   203: iconst_0       
        //   204: iload           5
        //   206: invokevirtual   java/lang/StringBuilder.append:([CII)Ljava/lang/StringBuilder;
        //   209: pop            
        //   210: goto            158
        //   213: astore_1       
        //   214: aload           8
        //   216: astore          7
        //   218: aload           7
        //   220: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   223: aload           6
        //   225: invokestatic    com/facebook/internal/Utility.disconnectQuietly:(Ljava/net/URLConnection;)V
        //   228: aload           11
        //   230: astore          9
        //   232: iload_2        
        //   233: ifeq            244
        //   236: aload_0        
        //   237: aload_1        
        //   238: aload           9
        //   240: iconst_0       
        //   241: invokestatic    com/facebook/internal/ImageDownloader.issueResponse:(Lcom/facebook/internal/ImageDownloader$RequestKey;Ljava/lang/Exception;Landroid/graphics/Bitmap;Z)V
        //   244: return         
        //   245: aload           6
        //   247: ldc             "location"
        //   249: invokevirtual   java/net/HttpURLConnection.getHeaderField:(Ljava/lang/String;)Ljava/lang/String;
        //   252: astore          7
        //   254: aload           7
        //   256: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //   259: ifne            575
        //   262: new             Ljava/net/URI;
        //   265: dup            
        //   266: aload           7
        //   268: invokespecial   java/net/URI.<init>:(Ljava/lang/String;)V
        //   271: astore          7
        //   273: aload_1        
        //   274: aload_0        
        //   275: getfield        com/facebook/internal/ImageDownloader$RequestKey.uri:Ljava/net/URI;
        //   278: aload           7
        //   280: invokestatic    com/facebook/internal/UrlRedirectCache.cacheUriRedirect:(Landroid/content/Context;Ljava/net/URI;Ljava/net/URI;)V
        //   283: aload_0        
        //   284: invokestatic    com/facebook/internal/ImageDownloader.removePendingRequest:(Lcom/facebook/internal/ImageDownloader$RequestKey;)Lcom/facebook/internal/ImageDownloader$DownloaderContext;
        //   287: astore_1       
        //   288: aload_1        
        //   289: ifnull          320
        //   292: aload_1        
        //   293: getfield        com/facebook/internal/ImageDownloader$DownloaderContext.isCancelled:Z
        //   296: ifne            320
        //   299: aload_1        
        //   300: getfield        com/facebook/internal/ImageDownloader$DownloaderContext.request:Lcom/facebook/internal/ImageRequest;
        //   303: new             Lcom/facebook/internal/ImageDownloader$RequestKey;
        //   306: dup            
        //   307: aload           7
        //   309: aload_0        
        //   310: getfield        com/facebook/internal/ImageDownloader$RequestKey.tag:Ljava/lang/Object;
        //   313: invokespecial   com/facebook/internal/ImageDownloader$RequestKey.<init>:(Ljava/net/URI;Ljava/lang/Object;)V
        //   316: iconst_0       
        //   317: invokestatic    com/facebook/internal/ImageDownloader.enqueueCacheRead:(Lcom/facebook/internal/ImageRequest;Lcom/facebook/internal/ImageDownloader$RequestKey;Z)V
        //   320: iconst_0       
        //   321: istore_2       
        //   322: aconst_null    
        //   323: astore          8
        //   325: aconst_null    
        //   326: astore_1       
        //   327: aload           12
        //   329: astore          9
        //   331: aload_1        
        //   332: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   335: aload           6
        //   337: invokestatic    com/facebook/internal/Utility.disconnectQuietly:(Ljava/net/URLConnection;)V
        //   340: aload           8
        //   342: astore_1       
        //   343: goto            232
        //   346: aload_1        
        //   347: aload           6
        //   349: invokestatic    com/facebook/internal/ImageResponseCache.interceptAndCacheImageStream:(Landroid/content/Context;Ljava/net/HttpURLConnection;)Ljava/io/InputStream;
        //   352: astore_1       
        //   353: aload_1        
        //   354: astore          8
        //   356: aload_1        
        //   357: astore          9
        //   359: aload_1        
        //   360: astore          10
        //   362: aload_1        
        //   363: invokestatic    android/graphics/BitmapFactory.decodeStream:(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
        //   366: astore          7
        //   368: aconst_null    
        //   369: astore          8
        //   371: aload           7
        //   373: astore          9
        //   375: iload_3        
        //   376: istore_2       
        //   377: goto            331
        //   380: aload           7
        //   382: astore          8
        //   384: aload           7
        //   386: astore          9
        //   388: aload           7
        //   390: astore          10
        //   392: aload_1        
        //   393: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   396: aload           7
        //   398: astore          8
        //   400: aload           7
        //   402: astore          9
        //   404: aload           7
        //   406: astore          10
        //   408: new             Lcom/facebook/FacebookException;
        //   411: dup            
        //   412: aload           13
        //   414: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   417: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;)V
        //   420: astore_1       
        //   421: iload_3        
        //   422: istore_2       
        //   423: aload           12
        //   425: astore          9
        //   427: aload_1        
        //   428: astore          8
        //   430: aload           7
        //   432: astore_1       
        //   433: goto            331
        //   436: astore_1       
        //   437: aload           9
        //   439: astore          7
        //   441: iload           4
        //   443: istore_2       
        //   444: aload           7
        //   446: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   449: aload           6
        //   451: invokestatic    com/facebook/internal/Utility.disconnectQuietly:(Ljava/net/URLConnection;)V
        //   454: aload           11
        //   456: astore          9
        //   458: goto            232
        //   461: aload           7
        //   463: astore          8
        //   465: aload           7
        //   467: astore          9
        //   469: aload           7
        //   471: astore          10
        //   473: aload           13
        //   475: aload_1        
        //   476: getstatic       com/facebook/android/R$string.com_facebook_image_download_unknown_error:I
        //   479: invokevirtual   android/content/Context.getString:(I)Ljava/lang/String;
        //   482: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   485: pop            
        //   486: goto            396
        //   489: astore_0       
        //   490: aload           6
        //   492: astore_1       
        //   493: aload           10
        //   495: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   498: aload_1        
        //   499: invokestatic    com/facebook/internal/Utility.disconnectQuietly:(Ljava/net/URLConnection;)V
        //   502: aload_0        
        //   503: athrow         
        //   504: astore_0       
        //   505: aconst_null    
        //   506: astore_1       
        //   507: goto            493
        //   510: astore_0       
        //   511: aload           6
        //   513: astore_1       
        //   514: goto            493
        //   517: astore_1       
        //   518: aconst_null    
        //   519: astore          7
        //   521: aconst_null    
        //   522: astore          6
        //   524: iload           4
        //   526: istore_2       
        //   527: goto            444
        //   530: astore_1       
        //   531: aconst_null    
        //   532: astore          7
        //   534: iload           4
        //   536: istore_2       
        //   537: goto            444
        //   540: astore_1       
        //   541: aconst_null    
        //   542: astore          7
        //   544: iconst_0       
        //   545: istore_2       
        //   546: goto            444
        //   549: astore_1       
        //   550: aconst_null    
        //   551: astore          7
        //   553: aconst_null    
        //   554: astore          6
        //   556: goto            218
        //   559: astore_1       
        //   560: aconst_null    
        //   561: astore          7
        //   563: goto            218
        //   566: astore_1       
        //   567: aconst_null    
        //   568: astore          7
        //   570: iconst_0       
        //   571: istore_2       
        //   572: goto            218
        //   575: iconst_0       
        //   576: istore_2       
        //   577: aconst_null    
        //   578: astore          8
        //   580: aconst_null    
        //   581: astore_1       
        //   582: aload           12
        //   584: astore          9
        //   586: goto            331
        //   589: goto            84
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                         
        //  -----  -----  -----  -----  -----------------------------
        //  16     38     549    559    Ljava/io/IOException;
        //  16     38     517    530    Ljava/net/URISyntaxException;
        //  16     38     504    510    Any
        //  38     84     559    566    Ljava/io/IOException;
        //  38     84     530    540    Ljava/net/URISyntaxException;
        //  38     84     510    517    Any
        //  84     91     559    566    Ljava/io/IOException;
        //  84     91     530    540    Ljava/net/URISyntaxException;
        //  84     91     510    517    Any
        //  103    112    213    218    Ljava/io/IOException;
        //  103    112    436    444    Ljava/net/URISyntaxException;
        //  103    112    489    493    Any
        //  129    139    213    218    Ljava/io/IOException;
        //  129    139    436    444    Ljava/net/URISyntaxException;
        //  129    139    489    493    Any
        //  151    158    213    218    Ljava/io/IOException;
        //  151    158    436    444    Ljava/net/URISyntaxException;
        //  151    158    489    493    Any
        //  170    182    213    218    Ljava/io/IOException;
        //  170    182    436    444    Ljava/net/URISyntaxException;
        //  170    182    489    493    Any
        //  199    210    213    218    Ljava/io/IOException;
        //  199    210    436    444    Ljava/net/URISyntaxException;
        //  199    210    489    493    Any
        //  245    288    566    575    Ljava/io/IOException;
        //  245    288    540    549    Ljava/net/URISyntaxException;
        //  245    288    510    517    Any
        //  292    320    566    575    Ljava/io/IOException;
        //  292    320    540    549    Ljava/net/URISyntaxException;
        //  292    320    510    517    Any
        //  346    353    559    566    Ljava/io/IOException;
        //  346    353    530    540    Ljava/net/URISyntaxException;
        //  346    353    510    517    Any
        //  362    368    213    218    Ljava/io/IOException;
        //  362    368    436    444    Ljava/net/URISyntaxException;
        //  362    368    489    493    Any
        //  392    396    213    218    Ljava/io/IOException;
        //  392    396    436    444    Ljava/net/URISyntaxException;
        //  392    396    489    493    Any
        //  408    421    213    218    Ljava/io/IOException;
        //  408    421    436    444    Ljava/net/URISyntaxException;
        //  408    421    489    493    Any
        //  473    486    213    218    Ljava/io/IOException;
        //  473    486    436    444    Ljava/net/URISyntaxException;
        //  473    486    489    493    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 294, Size: 294
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
    
    public static void downloadAsync(final ImageRequest request) {
        if (request == null) {
            return;
        }
        while (true) {
            final ImageDownloader$RequestKey imageDownloader$RequestKey = new ImageDownloader$RequestKey(request.getImageUri(), request.getCallerTag());
            synchronized (ImageDownloader.pendingRequests) {
                final ImageDownloader$DownloaderContext imageDownloader$DownloaderContext = ImageDownloader.pendingRequests.get(imageDownloader$RequestKey);
                if (imageDownloader$DownloaderContext != null) {
                    imageDownloader$DownloaderContext.request = request;
                    imageDownloader$DownloaderContext.isCancelled = false;
                    imageDownloader$DownloaderContext.workItem.moveToFront();
                    return;
                }
            }
            final ImageRequest imageRequest;
            enqueueCacheRead(imageRequest, imageDownloader$RequestKey, imageRequest.isCachedRedirectAllowed());
        }
    }
    
    private static void enqueueCacheRead(final ImageRequest imageRequest, final ImageDownloader$RequestKey imageDownloader$RequestKey, final boolean b) {
        enqueueRequest(imageRequest, imageDownloader$RequestKey, ImageDownloader.cacheReadQueue, new ImageDownloader$CacheReadWorkItem(imageRequest.getContext(), imageDownloader$RequestKey, b));
    }
    
    private static void enqueueDownload(final ImageRequest imageRequest, final ImageDownloader$RequestKey imageDownloader$RequestKey) {
        enqueueRequest(imageRequest, imageDownloader$RequestKey, ImageDownloader.downloadQueue, new ImageDownloader$DownloadImageWorkItem(imageRequest.getContext(), imageDownloader$RequestKey));
    }
    
    private static void enqueueRequest(final ImageRequest request, final ImageDownloader$RequestKey imageDownloader$RequestKey, final WorkQueue workQueue, final Runnable runnable) {
        synchronized (ImageDownloader.pendingRequests) {
            final ImageDownloader$DownloaderContext imageDownloader$DownloaderContext = new ImageDownloader$DownloaderContext(null);
            imageDownloader$DownloaderContext.request = request;
            ImageDownloader.pendingRequests.put(imageDownloader$RequestKey, imageDownloader$DownloaderContext);
            imageDownloader$DownloaderContext.workItem = workQueue.addActiveWorkItem(runnable);
        }
    }
    
    private static Handler getHandler() {
        synchronized (ImageDownloader.class) {
            if (ImageDownloader.handler == null) {
                ImageDownloader.handler = new Handler(Looper.getMainLooper());
            }
            return ImageDownloader.handler;
        }
    }
    
    private static void issueResponse(final ImageDownloader$RequestKey imageDownloader$RequestKey, final Exception ex, final Bitmap bitmap, final boolean b) {
        final ImageDownloader$DownloaderContext removePendingRequest = removePendingRequest(imageDownloader$RequestKey);
        if (removePendingRequest != null && !removePendingRequest.isCancelled) {
            final ImageRequest request = removePendingRequest.request;
            final ImageRequest$Callback callback = request.getCallback();
            if (callback != null) {
                getHandler().post((Runnable)new ImageDownloader$1(request, ex, b, bitmap, callback));
            }
        }
    }
    
    public static void prioritizeRequest(final ImageRequest imageRequest) {
        final ImageDownloader$RequestKey imageDownloader$RequestKey = new ImageDownloader$RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        synchronized (ImageDownloader.pendingRequests) {
            final ImageDownloader$DownloaderContext imageDownloader$DownloaderContext = ImageDownloader.pendingRequests.get(imageDownloader$RequestKey);
            if (imageDownloader$DownloaderContext != null) {
                imageDownloader$DownloaderContext.workItem.moveToFront();
            }
        }
    }
    
    private static void readFromCache(final ImageDownloader$RequestKey imageDownloader$RequestKey, final Context context, final boolean b) {
        final boolean b2 = false;
        while (true) {
            Label_0101: {
                if (!b) {
                    break Label_0101;
                }
                final URI redirectedUri = UrlRedirectCache.getRedirectedUri(context, imageDownloader$RequestKey.uri);
                if (redirectedUri == null) {
                    break Label_0101;
                }
                InputStream inputStream = ImageResponseCache.getCachedImageStream(redirectedUri, context);
                boolean b3 = b2;
                if (inputStream != null) {
                    b3 = true;
                }
                if (!b3) {
                    inputStream = ImageResponseCache.getCachedImageStream(imageDownloader$RequestKey.uri, context);
                }
                if (inputStream != null) {
                    final Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                    Utility.closeQuietly(inputStream);
                    issueResponse(imageDownloader$RequestKey, null, decodeStream, b3);
                }
                else {
                    final ImageDownloader$DownloaderContext removePendingRequest = removePendingRequest(imageDownloader$RequestKey);
                    if (removePendingRequest != null && !removePendingRequest.isCancelled) {
                        enqueueDownload(removePendingRequest.request, imageDownloader$RequestKey);
                        return;
                    }
                }
                return;
            }
            boolean b3 = false;
            InputStream inputStream = null;
            continue;
        }
    }
    
    private static ImageDownloader$DownloaderContext removePendingRequest(final ImageDownloader$RequestKey imageDownloader$RequestKey) {
        synchronized (ImageDownloader.pendingRequests) {
            return ImageDownloader.pendingRequests.remove(imageDownloader$RequestKey);
        }
    }
}
