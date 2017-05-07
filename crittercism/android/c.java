// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.util.Log;
import java.io.IOException;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import android.os.ConditionVariable;
import java.net.URL;
import java.util.List;

public final class c implements Runnable
{
    private List a;
    private URL b;
    private long c;
    private ConditionVariable d;
    private g e;
    private ConditionVariable f;
    private volatile boolean g;
    private final Object h;
    private int i;
    
    private long a() {
        final long n = 0L;
        final long n2 = this.i;
        final long n3 = System.currentTimeMillis() - this.c;
        long n4 = n2;
        if (n3 > 0L && (n4 = n2 - n3) < 0L) {
            n4 = n;
        }
        final int i = this.i;
        return n4;
    }
    
    private static boolean a(final HttpURLConnection httpURLConnection, final JSONObject jsonObject) {
        boolean b = false;
        try {
            httpURLConnection.getOutputStream().write(jsonObject.toString().getBytes("UTF8"));
            final int responseCode = httpURLConnection.getResponseCode();
            httpURLConnection.disconnect();
            if (responseCode == 202) {
                b = true;
            }
            return b;
        }
        catch (Exception ex) {
            return false;
        }
        catch (IOException ex2) {
            return false;
        }
    }
    
    private HttpURLConnection b() {
        HttpURLConnection httpURLConnection;
        try {
            final HttpURLConnection httpURLConnection2;
            httpURLConnection = (httpURLConnection2 = (HttpURLConnection)this.b.openConnection());
            final int n = 2500;
            httpURLConnection2.setConnectTimeout(n);
            final HttpURLConnection httpURLConnection3 = httpURLConnection;
            final String s = "User-Agent";
            final String s2 = "3.1.4";
            httpURLConnection3.setRequestProperty(s, s2);
            final HttpURLConnection httpURLConnection4 = httpURLConnection;
            final String s3 = "Content-Type";
            final String s4 = "application/json";
            httpURLConnection4.setRequestProperty(s3, s4);
            final HttpURLConnection httpURLConnection5 = httpURLConnection;
            final boolean b = true;
            httpURLConnection5.setDoOutput(b);
            final HttpURLConnection httpURLConnection6 = httpURLConnection;
            final String s5 = "POST";
            httpURLConnection6.setRequestMethod(s5);
            return httpURLConnection;
        }
        catch (IOException ex) {
            httpURLConnection = null;
        }
        while (true) {
            try {
                final HttpURLConnection httpURLConnection2 = httpURLConnection;
                final int n = 2500;
                httpURLConnection2.setConnectTimeout(n);
                final HttpURLConnection httpURLConnection3 = httpURLConnection;
                final String s = "User-Agent";
                final String s2 = "3.1.4";
                httpURLConnection3.setRequestProperty(s, s2);
                final HttpURLConnection httpURLConnection4 = httpURLConnection;
                final String s3 = "Content-Type";
                final String s4 = "application/json";
                httpURLConnection4.setRequestProperty(s3, s4);
                final HttpURLConnection httpURLConnection5 = httpURLConnection;
                final boolean b = true;
                httpURLConnection5.setDoOutput(b);
                final HttpURLConnection httpURLConnection6 = httpURLConnection;
                final String s5 = "POST";
                httpURLConnection6.setRequestMethod(s5);
                return httpURLConnection;
                final IOException ex;
                Log.e("Crittercism", "Failed to instantiate URLConnection to APM server: " + ex.getMessage());
                return httpURLConnection;
            }
            catch (IOException ex) {
                continue;
            }
            break;
        }
    }
    
    @Override
    public final void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        crittercism/android/c.g:Z
        //     4: ifne            182
        //     7: aload_0        
        //     8: getfield        crittercism/android/c.f:Landroid/os/ConditionVariable;
        //    11: invokevirtual   android/os/ConditionVariable.block:()V
        //    14: aload_0        
        //    15: getfield        crittercism/android/c.d:Landroid/os/ConditionVariable;
        //    18: invokevirtual   android/os/ConditionVariable.block:()V
        //    21: aload_0        
        //    22: getfield        crittercism/android/c.g:Z
        //    25: istore_1       
        //    26: iload_1        
        //    27: ifne            182
        //    30: aload_0        
        //    31: invokespecial   crittercism/android/c.a:()J
        //    34: lconst_0       
        //    35: lcmp           
        //    36: ifle            46
        //    39: aload_0        
        //    40: invokespecial   crittercism/android/c.a:()J
        //    43: invokestatic    java/lang/Thread.sleep:(J)V
        //    46: aload_0        
        //    47: invokestatic    java/lang/System.currentTimeMillis:()J
        //    50: putfield        crittercism/android/c.c:J
        //    53: aload_0        
        //    54: invokespecial   crittercism/android/c.b:()Ljava/net/HttpURLConnection;
        //    57: astore_2       
        //    58: aload_2        
        //    59: ifnonnull       76
        //    62: aload_0        
        //    63: iconst_1       
        //    64: putfield        crittercism/android/c.g:Z
        //    67: ldc             "Crittercism"
        //    69: ldc             "Disabling APM due to failure instantiating connection"
        //    71: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    74: pop            
        //    75: return         
        //    76: aload_0        
        //    77: getfield        crittercism/android/c.h:Ljava/lang/Object;
        //    80: astore_3       
        //    81: aload_3        
        //    82: monitorenter   
        //    83: aload_0        
        //    84: getfield        crittercism/android/c.a:Ljava/util/List;
        //    87: astore          4
        //    89: aload_0        
        //    90: new             Ljava/util/LinkedList;
        //    93: dup            
        //    94: invokespecial   java/util/LinkedList.<init>:()V
        //    97: putfield        crittercism/android/c.a:Ljava/util/List;
        //   100: aload_0        
        //   101: getfield        crittercism/android/c.d:Landroid/os/ConditionVariable;
        //   104: invokevirtual   android/os/ConditionVariable.close:()V
        //   107: aload_3        
        //   108: monitorexit    
        //   109: aload_0        
        //   110: getfield        crittercism/android/c.e:Lcrittercism/android/g;
        //   113: aload           4
        //   115: invokestatic    crittercism/android/a.a:(Lcrittercism/android/g;Ljava/util/List;)Lcrittercism/android/a;
        //   118: astore_3       
        //   119: aload_3        
        //   120: ifnonnull       166
        //   123: aload_0        
        //   124: iconst_1       
        //   125: putfield        crittercism/android/c.g:Z
        //   128: ldc             "Crittercism"
        //   130: ldc             "Disabling APM due to failure building request"
        //   132: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   135: pop            
        //   136: return         
        //   137: astore_2       
        //   138: ldc             "Crittercism"
        //   140: new             Ljava/lang/StringBuilder;
        //   143: dup            
        //   144: ldc             "Exited APM send task due to: \n"
        //   146: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //   149: aload_2        
        //   150: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   153: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   156: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   159: pop            
        //   160: return         
        //   161: astore_2       
        //   162: aload_3        
        //   163: monitorexit    
        //   164: aload_2        
        //   165: athrow         
        //   166: aload_2        
        //   167: aload_3        
        //   168: getfield        crittercism/android/a.a:Lorg/json/JSONObject;
        //   171: invokestatic    crittercism/android/c.a:(Ljava/net/HttpURLConnection;Lorg/json/JSONObject;)Z
        //   174: pop            
        //   175: goto            0
        //   178: astore_2       
        //   179: goto            46
        //   182: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      26     137    161    Ljava/lang/Exception;
        //  30     46     178    182    Ljava/lang/InterruptedException;
        //  30     46     137    161    Ljava/lang/Exception;
        //  46     58     137    161    Ljava/lang/Exception;
        //  62     75     137    161    Ljava/lang/Exception;
        //  76     83     137    161    Ljava/lang/Exception;
        //  83     109    161    166    Any
        //  109    119    137    161    Ljava/lang/Exception;
        //  123    136    137    161    Ljava/lang/Exception;
        //  162    166    137    161    Ljava/lang/Exception;
        //  166    175    137    161    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0046:
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
