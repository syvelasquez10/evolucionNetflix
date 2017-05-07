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
import com.android.volley.Request$Priority;
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
    private Request$Priority mPriority;
    
    public FileDownloadRequest(final String s, final ResourceFetcherCallback mCallback, final Response$ErrorListener response$ErrorListener, final int n, final Request$Priority mPriority, final File mDirectory) {
        super(0, s, response$ErrorListener);
        this.mCallback = mCallback;
        this.mDirectory = mDirectory;
        this.mPriority = mPriority;
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
    public Request$Priority getPriority() {
        return this.mPriority;
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
        //    22: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    25: ifeq            67
        //    28: ldc             "nf_service_filedownloadrequest"
        //    30: new             Ljava/lang/StringBuilder;
        //    33: dup            
        //    34: ldc             "Saving "
        //    36: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    39: aload_1        
        //    40: getfield        com/android/volley/NetworkResponse.data:[B
        //    43: arraylength    
        //    44: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    47: ldc             "bytes to file "
        //    49: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    52: aload           5
        //    54: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    57: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    60: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    63: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    66: pop            
        //    67: new             Ljava/io/BufferedOutputStream;
        //    70: dup            
        //    71: new             Ljava/io/FileOutputStream;
        //    74: dup            
        //    75: aload           5
        //    77: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //    80: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    83: astore_3       
        //    84: aload_3        
        //    85: astore_2       
        //    86: aload_3        
        //    87: aload_1        
        //    88: getfield        com/android/volley/NetworkResponse.data:[B
        //    91: invokevirtual   java/io/BufferedOutputStream.write:([B)V
        //    94: aload_3        
        //    95: astore_2       
        //    96: aload_3        
        //    97: invokevirtual   java/io/BufferedOutputStream.flush:()V
        //   100: aload_3        
        //   101: astore_2       
        //   102: new             Ljava/lang/StringBuilder;
        //   105: dup            
        //   106: invokespecial   java/lang/StringBuilder.<init>:()V
        //   109: ldc             "file://"
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: aload           5
        //   116: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   119: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   122: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   125: aconst_null    
        //   126: invokestatic    com/android/volley/Response.success:(Ljava/lang/Object;Lcom/android/volley/Cache$Entry;)Lcom/android/volley/Response;
        //   129: astore_1       
        //   130: aload_1        
        //   131: astore_2       
        //   132: aload_3        
        //   133: ifnull          142
        //   136: aload_3        
        //   137: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   140: aload_1        
        //   141: astore_2       
        //   142: aload_2        
        //   143: areturn        
        //   144: astore_2       
        //   145: ldc             "nf_service_filedownloadrequest"
        //   147: new             Ljava/lang/StringBuilder;
        //   150: dup            
        //   151: invokespecial   java/lang/StringBuilder.<init>:()V
        //   154: ldc             "Could not close the stream for "
        //   156: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   159: aload           5
        //   161: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   164: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   167: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   170: aload_2        
        //   171: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   174: pop            
        //   175: aload_1        
        //   176: areturn        
        //   177: astore          4
        //   179: aconst_null    
        //   180: astore_1       
        //   181: aload_1        
        //   182: astore_2       
        //   183: new             Lcom/android/volley/VolleyError;
        //   186: dup            
        //   187: new             Ljava/lang/StringBuilder;
        //   190: dup            
        //   191: invokespecial   java/lang/StringBuilder.<init>:()V
        //   194: ldc             "Could not save bytes to "
        //   196: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   199: aload           5
        //   201: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   204: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   207: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   210: aload           4
        //   212: invokespecial   com/android/volley/VolleyError.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   215: invokestatic    com/android/volley/Response.error:(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
        //   218: astore_3       
        //   219: aload_3        
        //   220: astore_2       
        //   221: aload_1        
        //   222: ifnull          142
        //   225: aload_1        
        //   226: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   229: aload_3        
        //   230: areturn        
        //   231: astore_1       
        //   232: ldc             "nf_service_filedownloadrequest"
        //   234: new             Ljava/lang/StringBuilder;
        //   237: dup            
        //   238: invokespecial   java/lang/StringBuilder.<init>:()V
        //   241: ldc             "Could not close the stream for "
        //   243: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   246: aload           5
        //   248: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   251: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   254: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   257: aload_1        
        //   258: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   261: pop            
        //   262: aload_3        
        //   263: areturn        
        //   264: astore_1       
        //   265: aconst_null    
        //   266: astore_2       
        //   267: aload_2        
        //   268: ifnull          275
        //   271: aload_2        
        //   272: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   275: aload_1        
        //   276: athrow         
        //   277: astore_2       
        //   278: ldc             "nf_service_filedownloadrequest"
        //   280: new             Ljava/lang/StringBuilder;
        //   283: dup            
        //   284: invokespecial   java/lang/StringBuilder.<init>:()V
        //   287: ldc             "Could not close the stream for "
        //   289: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   292: aload           5
        //   294: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   297: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   300: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   303: aload_2        
        //   304: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   307: pop            
        //   308: goto            275
        //   311: astore_1       
        //   312: goto            267
        //   315: astore          4
        //   317: aload_3        
        //   318: astore_1       
        //   319: goto            181
        //    Signature:
        //  (Lcom/android/volley/NetworkResponse;)Lcom/android/volley/Response<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  67     84     177    181    Ljava/io/IOException;
        //  67     84     264    267    Any
        //  86     94     315    322    Ljava/io/IOException;
        //  86     94     311    315    Any
        //  96     100    315    322    Ljava/io/IOException;
        //  96     100    311    315    Any
        //  102    130    315    322    Ljava/io/IOException;
        //  102    130    311    315    Any
        //  136    140    144    177    Ljava/io/IOException;
        //  183    219    311    315    Any
        //  225    229    231    264    Ljava/io/IOException;
        //  271    275    277    311    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0142:
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
