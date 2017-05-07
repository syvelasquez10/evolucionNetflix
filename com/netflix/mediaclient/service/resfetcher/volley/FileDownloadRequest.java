// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.RetryPolicy;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
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
    
    public FileDownloadRequest(final String s, final ResourceFetcherCallback mCallback, final Response.ErrorListener errorListener, final int n, final File mDirectory) {
        super(0, s, errorListener);
        this.mCallback = mCallback;
        this.mDirectory = mDirectory;
        this.setShouldCache(false);
        this.setRetryPolicy(new DefaultRetryPolicy(n, 2, 2.0f));
    }
    
    @Override
    protected void deliverResponse(final String s) {
        if (this.mCallback != null) {
            this.mCallback.onResourceFetched(this.getUrl(), s, 0);
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
        //    70: aconst_null    
        //    71: astore_2       
        //    72: aconst_null    
        //    73: astore          4
        //    75: new             Ljava/io/BufferedOutputStream;
        //    78: dup            
        //    79: new             Ljava/io/FileOutputStream;
        //    82: dup            
        //    83: aload           5
        //    85: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //    88: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    91: astore_3       
        //    92: aload_3        
        //    93: aload_1        
        //    94: getfield        com/android/volley/NetworkResponse.data:[B
        //    97: invokevirtual   java/io/BufferedOutputStream.write:([B)V
        //   100: aload_3        
        //   101: invokevirtual   java/io/BufferedOutputStream.flush:()V
        //   104: new             Ljava/lang/StringBuilder;
        //   107: dup            
        //   108: invokespecial   java/lang/StringBuilder.<init>:()V
        //   111: ldc             "file://"
        //   113: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   116: aload           5
        //   118: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   121: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   124: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   127: aconst_null    
        //   128: invokestatic    com/android/volley/Response.success:(Ljava/lang/Object;Lcom/android/volley/Cache$Entry;)Lcom/android/volley/Response;
        //   131: astore_1       
        //   132: aload_3        
        //   133: ifnull          140
        //   136: aload_3        
        //   137: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   140: aload_1        
        //   141: astore_3       
        //   142: aload_3        
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
        //   175: goto            140
        //   178: astore_3       
        //   179: aload           4
        //   181: astore_1       
        //   182: aload_1        
        //   183: astore_2       
        //   184: new             Lcom/android/volley/VolleyError;
        //   187: dup            
        //   188: new             Ljava/lang/StringBuilder;
        //   191: dup            
        //   192: invokespecial   java/lang/StringBuilder.<init>:()V
        //   195: ldc             "Could not save bytes to "
        //   197: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   200: aload           5
        //   202: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   205: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   208: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   211: aload_3        
        //   212: invokespecial   com/android/volley/VolleyError.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   215: invokestatic    com/android/volley/Response.error:(Lcom/android/volley/VolleyError;)Lcom/android/volley/Response;
        //   218: astore_3       
        //   219: aload_3        
        //   220: astore_2       
        //   221: aload_2        
        //   222: astore_3       
        //   223: aload_1        
        //   224: ifnull          142
        //   227: aload_1        
        //   228: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   231: aload_2        
        //   232: areturn        
        //   233: astore_1       
        //   234: ldc             "nf_service_filedownloadrequest"
        //   236: new             Ljava/lang/StringBuilder;
        //   239: dup            
        //   240: invokespecial   java/lang/StringBuilder.<init>:()V
        //   243: ldc             "Could not close the stream for "
        //   245: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   248: aload           5
        //   250: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   253: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   256: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   259: aload_1        
        //   260: invokestatic    com/netflix/mediaclient/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   263: pop            
        //   264: aload_2        
        //   265: areturn        
        //   266: astore_1       
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
        //   312: aload_3        
        //   313: astore_2       
        //   314: goto            267
        //   317: astore_2       
        //   318: aload_3        
        //   319: astore_1       
        //   320: aload_2        
        //   321: astore_3       
        //   322: goto            182
        //    Signature:
        //  (Lcom/android/volley/NetworkResponse;)Lcom/android/volley/Response<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  75     92     178    182    Ljava/io/IOException;
        //  75     92     266    267    Any
        //  92     132    317    325    Ljava/io/IOException;
        //  92     132    311    317    Any
        //  136    140    144    178    Ljava/io/IOException;
        //  184    219    266    267    Any
        //  227    231    233    266    Ljava/io/IOException;
        //  271    275    277    311    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0140:
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
