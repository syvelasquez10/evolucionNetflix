// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.io.InputStream;
import java.net.URL;
import java.io.Closeable;
import com.facebook.internal.Utility;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import android.os.Handler;

class ImageDownloader
{
    private static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
    private static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
    private static WorkQueue cacheReadQueue;
    private static WorkQueue downloadQueue;
    private static final Handler handler;
    private static final Map<ImageDownloader$RequestKey, ImageDownloader$DownloaderContext> pendingRequests;
    
    static {
        handler = new Handler();
        ImageDownloader.downloadQueue = new WorkQueue(8);
        ImageDownloader.cacheReadQueue = new WorkQueue(2);
        pendingRequests = new HashMap<ImageDownloader$RequestKey, ImageDownloader$DownloaderContext>();
    }
    
    static boolean cancelRequest(final ImageRequest imageRequest) {
        while (true) {
            final ImageDownloader$RequestKey imageDownloader$RequestKey = new ImageDownloader$RequestKey(imageRequest.getImageUrl(), imageRequest.getCallerTag());
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
    
    private static void download(final ImageDownloader$RequestKey p0, final Context p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          9
        //     3: aconst_null    
        //     4: astore          7
        //     6: aconst_null    
        //     7: astore          8
        //     9: iconst_1       
        //    10: istore_3       
        //    11: iconst_1       
        //    12: istore_2       
        //    13: aload_0        
        //    14: getfield        com/facebook/widget/ImageDownloader$RequestKey.url:Ljava/net/URL;
        //    17: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    20: checkcast       Ljava/net/HttpURLConnection;
        //    23: astore          5
        //    25: aload           5
        //    27: iconst_0       
        //    28: invokevirtual   java/net/HttpURLConnection.setInstanceFollowRedirects:(Z)V
        //    31: aload           5
        //    33: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //    36: lookupswitch {
        //              200: 307
        //              301: 208
        //              302: 208
        //          default: 453
        //        }
        //    72: aload           5
        //    74: invokevirtual   java/net/HttpURLConnection.getErrorStream:()Ljava/io/InputStream;
        //    77: astore          6
        //    79: aload           6
        //    81: astore_1       
        //    82: aload           6
        //    84: astore          7
        //    86: new             Ljava/io/InputStreamReader;
        //    89: dup            
        //    90: aload           6
        //    92: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    95: astore          10
        //    97: aload           6
        //    99: astore_1       
        //   100: aload           6
        //   102: astore          7
        //   104: sipush          128
        //   107: newarray        C
        //   109: astore          11
        //   111: aload           6
        //   113: astore_1       
        //   114: aload           6
        //   116: astore          7
        //   118: new             Ljava/lang/StringBuilder;
        //   121: dup            
        //   122: invokespecial   java/lang/StringBuilder.<init>:()V
        //   125: astore          12
        //   127: aload           6
        //   129: astore_1       
        //   130: aload           6
        //   132: astore          7
        //   134: aload           10
        //   136: aload           11
        //   138: iconst_0       
        //   139: aload           11
        //   141: arraylength    
        //   142: invokevirtual   java/io/InputStreamReader.read:([CII)I
        //   145: istore          4
        //   147: iload           4
        //   149: ifle            340
        //   152: aload           6
        //   154: astore_1       
        //   155: aload           6
        //   157: astore          7
        //   159: aload           12
        //   161: aload           11
        //   163: iconst_0       
        //   164: iload           4
        //   166: invokevirtual   java/lang/StringBuilder.append:([CII)Ljava/lang/StringBuilder;
        //   169: pop            
        //   170: goto            127
        //   173: astore          7
        //   175: aload_1        
        //   176: astore          6
        //   178: aload           7
        //   180: astore_1       
        //   181: aload           6
        //   183: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   186: aload           5
        //   188: invokestatic    com/facebook/internal/Utility.disconnectQuietly:(Ljava/net/URLConnection;)V
        //   191: aload           8
        //   193: astore          7
        //   195: iload_2        
        //   196: ifeq            207
        //   199: aload_0        
        //   200: aload_1        
        //   201: aload           7
        //   203: iconst_0       
        //   204: invokestatic    com/facebook/widget/ImageDownloader.issueResponse:(Lcom/facebook/widget/ImageDownloader$RequestKey;Ljava/lang/Exception;Landroid/graphics/Bitmap;Z)V
        //   207: return         
        //   208: aload           5
        //   210: ldc             "location"
        //   212: invokevirtual   java/net/HttpURLConnection.getHeaderField:(Ljava/lang/String;)Ljava/lang/String;
        //   215: astore          6
        //   217: aload           6
        //   219: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //   222: ifne            439
        //   225: new             Ljava/net/URL;
        //   228: dup            
        //   229: aload           6
        //   231: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   234: astore          6
        //   236: aload_1        
        //   237: aload_0        
        //   238: getfield        com/facebook/widget/ImageDownloader$RequestKey.url:Ljava/net/URL;
        //   241: aload           6
        //   243: invokestatic    com/facebook/widget/UrlRedirectCache.cacheUrlRedirect:(Landroid/content/Context;Ljava/net/URL;Ljava/net/URL;)V
        //   246: aload_0        
        //   247: invokestatic    com/facebook/widget/ImageDownloader.removePendingRequest:(Lcom/facebook/widget/ImageDownloader$RequestKey;)Lcom/facebook/widget/ImageDownloader$DownloaderContext;
        //   250: astore_1       
        //   251: aload_1        
        //   252: ifnull          283
        //   255: aload_1        
        //   256: getfield        com/facebook/widget/ImageDownloader$DownloaderContext.isCancelled:Z
        //   259: ifne            283
        //   262: aload_1        
        //   263: getfield        com/facebook/widget/ImageDownloader$DownloaderContext.request:Lcom/facebook/widget/ImageRequest;
        //   266: new             Lcom/facebook/widget/ImageDownloader$RequestKey;
        //   269: dup            
        //   270: aload           6
        //   272: aload_0        
        //   273: getfield        com/facebook/widget/ImageDownloader$RequestKey.tag:Ljava/lang/Object;
        //   276: invokespecial   com/facebook/widget/ImageDownloader$RequestKey.<init>:(Ljava/net/URL;Ljava/lang/Object;)V
        //   279: iconst_0       
        //   280: invokestatic    com/facebook/widget/ImageDownloader.enqueueCacheRead:(Lcom/facebook/widget/ImageRequest;Lcom/facebook/widget/ImageDownloader$RequestKey;Z)V
        //   283: iconst_0       
        //   284: istore_2       
        //   285: aconst_null    
        //   286: astore_1       
        //   287: aconst_null    
        //   288: astore          6
        //   290: aload           9
        //   292: astore          7
        //   294: aload           6
        //   296: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   299: aload           5
        //   301: invokestatic    com/facebook/internal/Utility.disconnectQuietly:(Ljava/net/URLConnection;)V
        //   304: goto            195
        //   307: aload_1        
        //   308: aload           5
        //   310: invokestatic    com/facebook/widget/ImageResponseCache.interceptAndCacheImageStream:(Landroid/content/Context;Ljava/net/HttpURLConnection;)Ljava/io/InputStream;
        //   313: astore          6
        //   315: aload           6
        //   317: astore_1       
        //   318: aload           6
        //   320: astore          7
        //   322: aload           6
        //   324: invokestatic    android/graphics/BitmapFactory.decodeStream:(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
        //   327: astore          9
        //   329: aconst_null    
        //   330: astore_1       
        //   331: aload           9
        //   333: astore          7
        //   335: iload_3        
        //   336: istore_2       
        //   337: goto            294
        //   340: aload           6
        //   342: astore_1       
        //   343: aload           6
        //   345: astore          7
        //   347: aload           10
        //   349: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   352: aload           6
        //   354: astore_1       
        //   355: aload           6
        //   357: astore          7
        //   359: new             Lcom/facebook/FacebookException;
        //   362: dup            
        //   363: aload           12
        //   365: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   368: invokespecial   com/facebook/FacebookException.<init>:(Ljava/lang/String;)V
        //   371: astore          10
        //   373: iload_3        
        //   374: istore_2       
        //   375: aload           9
        //   377: astore          7
        //   379: aload           10
        //   381: astore_1       
        //   382: goto            294
        //   385: astore_0       
        //   386: aload           5
        //   388: astore_1       
        //   389: aload           7
        //   391: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   394: aload_1        
        //   395: invokestatic    com/facebook/internal/Utility.disconnectQuietly:(Ljava/net/URLConnection;)V
        //   398: aload_0        
        //   399: athrow         
        //   400: astore_0       
        //   401: aconst_null    
        //   402: astore_1       
        //   403: goto            389
        //   406: astore_0       
        //   407: aload           5
        //   409: astore_1       
        //   410: goto            389
        //   413: astore_1       
        //   414: aconst_null    
        //   415: astore          6
        //   417: aconst_null    
        //   418: astore          5
        //   420: goto            181
        //   423: astore_1       
        //   424: aconst_null    
        //   425: astore          6
        //   427: goto            181
        //   430: astore_1       
        //   431: aconst_null    
        //   432: astore          6
        //   434: iconst_0       
        //   435: istore_2       
        //   436: goto            181
        //   439: iconst_0       
        //   440: istore_2       
        //   441: aconst_null    
        //   442: astore_1       
        //   443: aconst_null    
        //   444: astore          6
        //   446: aload           9
        //   448: astore          7
        //   450: goto            294
        //   453: goto            72
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  13     25     413    423    Ljava/io/IOException;
        //  13     25     400    406    Any
        //  25     72     423    430    Ljava/io/IOException;
        //  25     72     406    413    Any
        //  72     79     423    430    Ljava/io/IOException;
        //  72     79     406    413    Any
        //  86     97     173    181    Ljava/io/IOException;
        //  86     97     385    389    Any
        //  104    111    173    181    Ljava/io/IOException;
        //  104    111    385    389    Any
        //  118    127    173    181    Ljava/io/IOException;
        //  118    127    385    389    Any
        //  134    147    173    181    Ljava/io/IOException;
        //  134    147    385    389    Any
        //  159    170    173    181    Ljava/io/IOException;
        //  159    170    385    389    Any
        //  208    251    430    439    Ljava/io/IOException;
        //  208    251    406    413    Any
        //  255    283    430    439    Ljava/io/IOException;
        //  255    283    406    413    Any
        //  307    315    423    430    Ljava/io/IOException;
        //  307    315    406    413    Any
        //  322    329    173    181    Ljava/io/IOException;
        //  322    329    385    389    Any
        //  347    352    173    181    Ljava/io/IOException;
        //  347    352    385    389    Any
        //  359    373    173    181    Ljava/io/IOException;
        //  359    373    385    389    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 223, Size: 223
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
    
    static void downloadAsync(final ImageRequest request) {
        if (request == null) {
            return;
        }
        while (true) {
            final ImageDownloader$RequestKey imageDownloader$RequestKey = new ImageDownloader$RequestKey(request.getImageUrl(), request.getCallerTag());
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
    
    private static void issueResponse(final ImageDownloader$RequestKey imageDownloader$RequestKey, final Exception ex, final Bitmap bitmap, final boolean b) {
        final ImageDownloader$DownloaderContext removePendingRequest = removePendingRequest(imageDownloader$RequestKey);
        if (removePendingRequest != null && !removePendingRequest.isCancelled) {
            final ImageRequest request = removePendingRequest.request;
            final ImageRequest$Callback callback = request.getCallback();
            if (callback != null) {
                ImageDownloader.handler.post((Runnable)new ImageDownloader$1(request, ex, b, bitmap, callback));
            }
        }
    }
    
    static void prioritizeRequest(final ImageRequest imageRequest) {
        final ImageDownloader$RequestKey imageDownloader$RequestKey = new ImageDownloader$RequestKey(imageRequest.getImageUrl(), imageRequest.getCallerTag());
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
                final URL redirectedUrl = UrlRedirectCache.getRedirectedUrl(context, imageDownloader$RequestKey.url);
                if (redirectedUrl == null) {
                    break Label_0101;
                }
                InputStream inputStream = ImageResponseCache.getCachedImageStream(redirectedUrl, context);
                boolean b3 = b2;
                if (inputStream != null) {
                    b3 = true;
                }
                if (!b3) {
                    inputStream = ImageResponseCache.getCachedImageStream(imageDownloader$RequestKey.url, context);
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
