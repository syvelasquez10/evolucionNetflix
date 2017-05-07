// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import com.android.volley.Response;
import com.android.volley.NetworkResponse;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.android.volley.RetryPolicy;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response$ErrorListener;
import java.io.File;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.android.volley.Request;

public class FileDownloadRequest extends Request<String>
{
    private static final float IMAGE_BACKOFF_MULT = 2.0f;
    private static final int IMAGE_MAX_RETRIES = 2;
    private static final String TAG = "nf_service_filedownloadrequest";
    private ResourceFetcherCallback mCallback;
    private File mDirectory;
    
    public FileDownloadRequest(final String s, final ResourceFetcherCallback mCallback, final Response$ErrorListener response$ErrorListener, final int n, final File mDirectory) {
        super(0, s, response$ErrorListener);
        this.mCallback = mCallback;
        this.mDirectory = mDirectory;
        this.setShouldCache(false);
        this.setRetryPolicy(new DefaultRetryPolicy(n, 2, 2.0f));
    }
    
    @Override
    protected void deliverResponse(final String s) {
        if (this.mCallback != null) {
            this.mCallback.onResourceFetched(this.getUrl(), s, CommonStatus.OK);
        }
    }
    
    @Override
    protected Response<String> parseNetworkResponse(final NetworkResponse p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/netflix/mediaclient/service/resfetcher/volley/FileDownloadRequest.getUrl:()Ljava/lang/String;
        //     4: invokestatic    com/netflix/mediaclient/util/StringUtils.getFilenameFromUri:(Ljava/lang/String;)Ljava/lang/String;
        //     7: astore_2       
        //     8: new             Ljava/io/File;
        //    11: dup            
        //    12: aload_0        
        //    13: getfield        com/netflix/mediaclient/service/resfetcher/volley/FileDownloadRequest.mDirectory:Ljava/io/File;
        //    16: aload_2        
        //    17: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    20: astore          5
        //    22: ldc             "nf_service_filedownloadrequest"
        //    24: iconst_3       
        //    25: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //    28: ifeq            70
        //    31: ldc             "nf_service_filedownloadrequest"
        //    33: new             Ljava/lang/StringBuilder;
        //    36: dup            
        //    37: ldc             "Saving "
        //    39: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    42: aload_1        
        //    43: getfield        com/android/volley/NetworkResponse.data:[B
        //    46: arraylength    
        //    47: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    50: ldc             "bytes to file "
        //    52: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    55: aload           5
        //    57: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    60: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    63: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    66: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    69: pop            
        //    70: new             Ljava/io/BufferedOutputStream;
        //    73: dup            
        //    74: new             Ljava/io/FileOutputStream;
        //    77: dup            
        //    78: aload           5
        //    80: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //    83: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    86: astore_3       
        //    87: aload_3        
        //    88: astore_2       
        //    89: aload_3        
        //    90: aload_1        
        //    91: getfield        com/android/volley/NetworkResponse.data:[B
        //    94: invokevirtual   java/io/BufferedOutputStream.write:([B)V
        //    97: aload_3        
        //    98: astore_2       
        //    99: aload_3        
        //   100: invokevirtual   java/io/BufferedOutputStream.flush:()V
        //   103: aload_3        
        //   104: astore_2       
        //   105: new             Ljava/lang/StringBuilder;
        //   108: dup            
        //   109: invokespecial   java/lang/StringBuilder.<init>:()V
        //   112: ldc             "file://"
        //   114: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   117: aload           5
        //   119: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   122: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   125: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   128: aconst_null    
        //   129: invokestatic    com/android/volley/Response.success:(Ljava/lang/Object;Lcom/android/volley/Cache$Entry;)Lcom/android/volley/Response;
        //   132: astore_1       
        //   133: aload_1        
        //   134: astore_2       
        //   135: aload_3        
        //   136: ifnull          145
        //   139: aload_3        
        //   140: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   143: aload_1        
        //   144: astore_2       
        //   145: aload_2        
        //   146: areturn        
        //   147: astore_2       
        //   148: ldc             "nf_service_filedownloadrequest"
        //   150: new             Ljava/lang/StringBuilder;
        //   153: dup            
        //   154: invokespecial   java/lang/StringBuilder.<init>:()V
        //   157: ldc             "Could not close the stream for "
        //   159: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   162: aload           5
        //   164: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   167: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   173: aload_2        
        //   174: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   177: pop            
        //   178: aload_1        
        //   179: areturn        
        //   180: astore          4
        //   182: aconst_null    
        //   183: astore_1       
        //   184: aload_1        
        //   185: astore_2       
        //   186: new             Lcom/android/volley/VolleyError;
        //   189: dup            
        //   190: new             Ljava/lang/StringBuilder;
        //   193: dup            
        //   194: invokespecial   java/lang/StringBuilder.<init>:()V
        //   197: ldc             "Could not save bytes to "
        //   199: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   202: aload           5
        //   204: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   207: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   210: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   213: aload           4
        //   215: invokespecial   com/android/volley/VolleyError.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   218: invokestatic    com/android/volley/Response.error:(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
        //   221: astore_3       
        //   222: aload_3        
        //   223: astore_2       
        //   224: aload_1        
        //   225: ifnull          145
        //   228: aload_1        
        //   229: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   232: aload_3        
        //   233: areturn        
        //   234: astore_1       
        //   235: ldc             "nf_service_filedownloadrequest"
        //   237: new             Ljava/lang/StringBuilder;
        //   240: dup            
        //   241: invokespecial   java/lang/StringBuilder.<init>:()V
        //   244: ldc             "Could not close the stream for "
        //   246: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   249: aload           5
        //   251: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   254: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   257: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   260: aload_1        
        //   261: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   264: pop            
        //   265: aload_3        
        //   266: areturn        
        //   267: astore_1       
        //   268: aconst_null    
        //   269: astore_2       
        //   270: aload_2        
        //   271: ifnull          278
        //   274: aload_2        
        //   275: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   278: aload_1        
        //   279: athrow         
        //   280: astore_2       
        //   281: ldc             "nf_service_filedownloadrequest"
        //   283: new             Ljava/lang/StringBuilder;
        //   286: dup            
        //   287: invokespecial   java/lang/StringBuilder.<init>:()V
        //   290: ldc             "Could not close the stream for "
        //   292: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   295: aload           5
        //   297: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   300: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   303: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   306: aload_2        
        //   307: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   310: pop            
        //   311: goto            278
        //   314: astore_1       
        //   315: goto            270
        //   318: astore          4
        //   320: aload_3        
        //   321: astore_1       
        //   322: goto            184
        //    Signature:
        //  (Lcom/android/volley/NetworkResponse;)Lcom/android/volley/Response<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  70     87     180    184    Ljava/io/IOException;
        //  70     87     267    270    Any
        //  89     97     318    325    Ljava/io/IOException;
        //  89     97     314    318    Any
        //  99     103    318    325    Ljava/io/IOException;
        //  99     103    314    318    Any
        //  105    133    318    325    Ljava/io/IOException;
        //  105    133    314    318    Any
        //  139    143    147    180    Ljava/io/IOException;
        //  186    222    314    318    Any
        //  228    232    234    267    Ljava/io/IOException;
        //  274    278    280    314    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0145:
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
