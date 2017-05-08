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
        //   170: iconst_1       
        //   171: anewarray       Ljava/lang/Object;
        //   174: dup            
        //   175: iconst_0       
        //   176: aload_2        
        //   177: aastore        
        //   178: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)I
        //   181: pop            
        //   182: aload_1        
        //   183: areturn        
        //   184: astore          4
        //   186: aconst_null    
        //   187: astore_1       
        //   188: aload_1        
        //   189: astore_2       
        //   190: new             Lcom/android/volley/VolleyError;
        //   193: dup            
        //   194: new             Ljava/lang/StringBuilder;
        //   197: dup            
        //   198: invokespecial   java/lang/StringBuilder.<init>:()V
        //   201: ldc             "Could not save bytes to "
        //   203: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   206: aload           5
        //   208: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   211: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   214: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   217: aload           4
        //   219: invokespecial   com/android/volley/VolleyError.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   222: invokestatic    com/android/volley/Response.error:(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
        //   225: astore_3       
        //   226: aload_3        
        //   227: astore_2       
        //   228: aload_1        
        //   229: ifnull          142
        //   232: aload_1        
        //   233: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   236: aload_3        
        //   237: areturn        
        //   238: astore_1       
        //   239: ldc             "nf_service_filedownloadrequest"
        //   241: new             Ljava/lang/StringBuilder;
        //   244: dup            
        //   245: invokespecial   java/lang/StringBuilder.<init>:()V
        //   248: ldc             "Could not close the stream for "
        //   250: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   253: aload           5
        //   255: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   258: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   261: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   264: iconst_1       
        //   265: anewarray       Ljava/lang/Object;
        //   268: dup            
        //   269: iconst_0       
        //   270: aload_1        
        //   271: aastore        
        //   272: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)I
        //   275: pop            
        //   276: aload_3        
        //   277: areturn        
        //   278: astore_1       
        //   279: aconst_null    
        //   280: astore_2       
        //   281: aload_2        
        //   282: ifnull          289
        //   285: aload_2        
        //   286: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   289: aload_1        
        //   290: athrow         
        //   291: astore_2       
        //   292: ldc             "nf_service_filedownloadrequest"
        //   294: new             Ljava/lang/StringBuilder;
        //   297: dup            
        //   298: invokespecial   java/lang/StringBuilder.<init>:()V
        //   301: ldc             "Could not close the stream for "
        //   303: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   306: aload           5
        //   308: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   311: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   314: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   317: iconst_1       
        //   318: anewarray       Ljava/lang/Object;
        //   321: dup            
        //   322: iconst_0       
        //   323: aload_2        
        //   324: aastore        
        //   325: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)I
        //   328: pop            
        //   329: goto            289
        //   332: astore_1       
        //   333: goto            281
        //   336: astore          4
        //   338: aload_3        
        //   339: astore_1       
        //   340: goto            188
        //    Signature:
        //  (Lcom/android/volley/NetworkResponse;)Lcom/android/volley/Response<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  67     84     184    188    Ljava/io/IOException;
        //  67     84     278    281    Any
        //  86     94     336    343    Ljava/io/IOException;
        //  86     94     332    336    Any
        //  96     100    336    343    Ljava/io/IOException;
        //  96     100    332    336    Any
        //  102    130    336    343    Ljava/io/IOException;
        //  102    130    332    336    Any
        //  136    140    144    184    Ljava/io/IOException;
        //  190    226    332    336    Any
        //  232    236    238    278    Ljava/io/IOException;
        //  285    289    291    332    Ljava/io/IOException;
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
