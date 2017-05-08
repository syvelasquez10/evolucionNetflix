// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.net.InetAddress;
import java.net.Socket;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import com.android.org.conscrypt.SSLParametersImpl;
import javax.net.ssl.SSLSocketFactory;

public final class r extends p
{
    private static boolean a;
    private static SSLSocketFactory b;
    private SSLParametersImpl c;
    private e d;
    private SSLSocketFactory delegate;
    private d e;
    
    static {
        r.a = false;
    }
    
    public r(final SSLSocketFactory delegate, final e d, final d e) {
        this.delegate = delegate;
        this.d = d;
        this.e = e;
        this.c = a(delegate);
    }
    
    private static SSLParametersImpl a(SSLParametersImpl b) {
        try {
            b = b(b);
            return b;
        }
        catch (cj cj) {
            return null;
        }
    }
    
    private static SSLParametersImpl a(final SSLSocketFactory sslSocketFactory) {
        try {
            final SSLParametersImpl sslParametersImpl = (SSLParametersImpl)j.a(j.a(sslSocketFactory.getClass(), SSLParametersImpl.class, false), sslSocketFactory);
            return a(sslParametersImpl);
        }
        catch (cj cj) {
            dw.b(cj);
            final SSLParametersImpl sslParametersImpl = null;
            return a(sslParametersImpl);
        }
    }
    
    public static boolean a(final e p0, final d p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/crittercism/internal/r.a:Z
        //     3: ifeq            10
        //     6: getstatic       com/crittercism/internal/r.a:Z
        //     9: ireturn        
        //    10: invokestatic    javax/net/ssl/HttpsURLConnection.getDefaultSSLSocketFactory:()Ljavax/net/ssl/SSLSocketFactory;
        //    13: astore_2       
        //    14: new             Lcom/crittercism/internal/r;
        //    17: dup            
        //    18: aload_2        
        //    19: aload_0        
        //    20: aload_1        
        //    21: invokespecial   com/crittercism/internal/r.<init>:(Ljavax/net/ssl/SSLSocketFactory;Lcom/crittercism/internal/e;Lcom/crittercism/internal/d;)V
        //    24: astore_0       
        //    25: aload_0        
        //    26: invokevirtual   javax/net/ssl/SSLSocketFactory.createSocket:()Ljava/net/Socket;
        //    29: astore_1       
        //    30: aload_0        
        //    31: aload_1        
        //    32: ldc             "localhost"
        //    34: sipush          6895
        //    37: iconst_1       
        //    38: invokevirtual   javax/net/ssl/SSLSocketFactory.createSocket:(Ljava/net/Socket;Ljava/lang/String;IZ)Ljava/net/Socket;
        //    41: pop            
        //    42: aload_0        
        //    43: invokestatic    javax/net/ssl/HttpsURLConnection.setDefaultSSLSocketFactory:(Ljavax/net/ssl/SSLSocketFactory;)V
        //    46: aload_2        
        //    47: putstatic       com/crittercism/internal/r.b:Ljavax/net/ssl/SSLSocketFactory;
        //    50: iconst_1       
        //    51: putstatic       com/crittercism/internal/r.a:Z
        //    54: iconst_1       
        //    55: ireturn        
        //    56: astore_0       
        //    57: aload_0        
        //    58: athrow         
        //    59: astore_0       
        //    60: ldc             "Unable to instrument https connections."
        //    62: aload_0        
        //    63: invokestatic    com/crittercism/internal/dw.a:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    66: iconst_0       
        //    67: ireturn        
        //    68: astore_1       
        //    69: goto            42
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                      
        //  -----  -----  -----  -----  --------------------------
        //  14     30     56     59     Ljava/lang/ThreadDeath;
        //  14     30     59     68     Ljava/lang/Throwable;
        //  30     42     68     72     Ljava/net/SocketException;
        //  30     42     56     59     Ljava/lang/ThreadDeath;
        //  30     42     59     68     Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0042:
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
    
    private static SSLParametersImpl b(SSLParametersImpl sslParametersImpl) {
        try {
            final Method declaredMethod = SSLParametersImpl.class.getDeclaredMethod("clone", (Class<?>[])new Class[0]);
            declaredMethod.setAccessible(true);
            sslParametersImpl = (SSLParametersImpl)declaredMethod.invoke(sslParametersImpl, new Object[0]);
            return sslParametersImpl;
        }
        catch (NoSuchMethodException ex) {
            throw new cj(ex);
        }
        catch (IllegalArgumentException ex2) {
            throw new cj(ex2);
        }
        catch (IllegalAccessException ex3) {
            throw new cj(ex3);
        }
        catch (InvocationTargetException ex4) {
            throw new cj(ex4);
        }
    }
    
    @Override
    public final SSLSocketFactory a() {
        return this.delegate;
    }
    
    @Override
    public final Socket createSocket() {
        return (Socket)new t(this.d, this.e, a(this.c));
    }
    
    @Override
    public final Socket createSocket(final String s, final int n) {
        return (Socket)new t(this.d, this.e, s, n, a(this.c));
    }
    
    @Override
    public final Socket createSocket(final String s, final int n, final InetAddress inetAddress, final int n2) {
        return (Socket)new t(this.d, this.e, s, n, inetAddress, n2, a(this.c));
    }
    
    @Override
    public final Socket createSocket(final InetAddress inetAddress, final int n) {
        return (Socket)new t(this.d, this.e, inetAddress, n, a(this.c));
    }
    
    @Override
    public final Socket createSocket(final InetAddress inetAddress, final int n, final InetAddress inetAddress2, final int n2) {
        return (Socket)new t(this.d, this.e, inetAddress, n, inetAddress2, n2, a(this.c));
    }
    
    @Override
    public final Socket createSocket(final Socket socket, final String s, final int n, final boolean b) {
        return (Socket)new v(this.d, this.e, socket, s, n, b, a(this.c));
    }
    
    @Override
    public final String[] getDefaultCipherSuites() {
        return this.delegate.getDefaultCipherSuites();
    }
    
    @Override
    public final String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }
}
