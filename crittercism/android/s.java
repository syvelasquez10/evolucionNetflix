// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.net.URL;
import java.security.Permission;
import java.io.OutputStream;
import java.security.Principal;
import java.security.cert.Certificate;
import javax.net.ssl.HostnameVerifier;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.io.IOException;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.HttpsURLConnection;

public final class s extends HttpsURLConnection
{
    private e a;
    private HttpsURLConnection b;
    private c c;
    private d d;
    private boolean e;
    private boolean f;
    
    public s(final HttpsURLConnection b, final e a, final d d) {
        super(b.getURL());
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = false;
        this.a = a;
        this.b = b;
        this.d = d;
        this.c = new c(b.getURL());
        final SSLSocketFactory sslSocketFactory = this.b.getSSLSocketFactory();
        if (sslSocketFactory instanceof ab) {
            this.b.setSSLSocketFactory(((ab)sslSocketFactory).a());
        }
    }
    
    private void a() {
        try {
            if (!this.f) {
                this.f = true;
                this.c.f = this.b.getRequestMethod();
                this.c.b();
                this.c.j = this.d.a();
                if (bc.b()) {
                    this.c.a(bc.a());
                }
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
        }
    }
    
    private void a(final Throwable t) {
        try {
            if (this.e) {
                return;
            }
            this.e = true;
            this.c.c();
            this.c.a(t);
            this.a.a(this.c);
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
        }
    }
    
    private void b() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_2       
        //     2: iconst_0       
        //     3: istore_1       
        //     4: aload_0        
        //     5: getfield        crittercism/android/s.e:Z
        //     8: ifne            159
        //    11: aload_0        
        //    12: iconst_1       
        //    13: putfield        crittercism/android/s.e:Z
        //    16: aload_0        
        //    17: getfield        crittercism/android/s.c:Lcrittercism/android/c;
        //    20: invokevirtual   crittercism/android/c.c:()V
        //    23: aload_0        
        //    24: getfield        crittercism/android/s.b:Ljavax/net/ssl/HttpsURLConnection;
        //    27: invokevirtual   javax/net/ssl/HttpsURLConnection.getHeaderFields:()Ljava/util/Map;
        //    30: ifnull          130
        //    33: new             Lcrittercism/android/p;
        //    36: dup            
        //    37: aload_0        
        //    38: getfield        crittercism/android/s.b:Ljavax/net/ssl/HttpsURLConnection;
        //    41: invokevirtual   javax/net/ssl/HttpsURLConnection.getHeaderFields:()Ljava/util/Map;
        //    44: invokespecial   crittercism/android/p.<init>:(Ljava/util/Map;)V
        //    47: astore          7
        //    49: aload           7
        //    51: ldc             "Content-Length"
        //    53: invokevirtual   crittercism/android/p.b:(Ljava/lang/String;)I
        //    56: istore_2       
        //    57: iload_2        
        //    58: iconst_m1      
        //    59: if_icmpeq       73
        //    62: aload_0        
        //    63: getfield        crittercism/android/s.c:Lcrittercism/android/c;
        //    66: iload_2        
        //    67: i2l            
        //    68: invokevirtual   crittercism/android/c.b:(J)V
        //    71: iconst_1       
        //    72: istore_1       
        //    73: aload           7
        //    75: ldc             "X-Android-Sent-Millis"
        //    77: invokevirtual   crittercism/android/p.a:(Ljava/lang/String;)J
        //    80: lstore_3       
        //    81: aload           7
        //    83: ldc             "X-Android-Received-Millis"
        //    85: invokevirtual   crittercism/android/p.a:(Ljava/lang/String;)J
        //    88: lstore          5
        //    90: iload_1        
        //    91: istore_2       
        //    92: lload_3        
        //    93: ldc2_w          9223372036854775807
        //    96: lcmp           
        //    97: ifeq            130
        //   100: iload_1        
        //   101: istore_2       
        //   102: lload           5
        //   104: ldc2_w          9223372036854775807
        //   107: lcmp           
        //   108: ifeq            130
        //   111: aload_0        
        //   112: getfield        crittercism/android/s.c:Lcrittercism/android/c;
        //   115: lload_3        
        //   116: invokevirtual   crittercism/android/c.e:(J)V
        //   119: aload_0        
        //   120: getfield        crittercism/android/s.c:Lcrittercism/android/c;
        //   123: lload           5
        //   125: invokevirtual   crittercism/android/c.f:(J)V
        //   128: iload_1        
        //   129: istore_2       
        //   130: aload_0        
        //   131: getfield        crittercism/android/s.c:Lcrittercism/android/c;
        //   134: aload_0        
        //   135: getfield        crittercism/android/s.b:Ljavax/net/ssl/HttpsURLConnection;
        //   138: invokevirtual   javax/net/ssl/HttpsURLConnection.getResponseCode:()I
        //   141: putfield        crittercism/android/c.e:I
        //   144: iload_2        
        //   145: ifeq            159
        //   148: aload_0        
        //   149: getfield        crittercism/android/s.a:Lcrittercism/android/e;
        //   152: aload_0        
        //   153: getfield        crittercism/android/s.c:Lcrittercism/android/c;
        //   156: invokevirtual   crittercism/android/e.a:(Lcrittercism/android/c;)V
        //   159: return         
        //   160: astore          7
        //   162: aload           7
        //   164: athrow         
        //   165: astore          7
        //   167: aload           7
        //   169: invokestatic    crittercism/android/dy.a:(Ljava/lang/Throwable;)V
        //   172: return         
        //   173: astore          7
        //   175: goto            144
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                   
        //  -----  -----  -----  -----  -----------------------
        //  4      23     160    165    Ljava/lang/ThreadDeath;
        //  4      23     165    173    Ljava/lang/Throwable;
        //  23     57     160    165    Ljava/lang/ThreadDeath;
        //  23     57     165    173    Ljava/lang/Throwable;
        //  62     71     160    165    Ljava/lang/ThreadDeath;
        //  62     71     165    173    Ljava/lang/Throwable;
        //  73     90     160    165    Ljava/lang/ThreadDeath;
        //  73     90     165    173    Ljava/lang/Throwable;
        //  111    128    160    165    Ljava/lang/ThreadDeath;
        //  111    128    165    173    Ljava/lang/Throwable;
        //  130    144    173    178    Ljava/io/IOException;
        //  130    144    160    165    Ljava/lang/ThreadDeath;
        //  130    144    165    173    Ljava/lang/Throwable;
        //  148    159    160    165    Ljava/lang/ThreadDeath;
        //  148    159    165    173    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0130:
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
    
    @Override
    public final void addRequestProperty(final String s, final String s2) {
        this.b.addRequestProperty(s, s2);
    }
    
    @Override
    public final void connect() {
        this.b.connect();
    }
    
    @Override
    public final void disconnect() {
        this.b.disconnect();
        try {
            if (this.e && !this.c.b) {
                this.a.a(this.c);
            }
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t) {
            dy.a(t);
        }
    }
    
    @Override
    public final boolean equals(final Object o) {
        return this.b.equals(o);
    }
    
    @Override
    public final boolean getAllowUserInteraction() {
        return this.b.getAllowUserInteraction();
    }
    
    @Override
    public final String getCipherSuite() {
        return this.b.getCipherSuite();
    }
    
    @Override
    public final int getConnectTimeout() {
        return this.b.getConnectTimeout();
    }
    
    @Override
    public final Object getContent() {
        this.a();
        try {
            final Object content = this.b.getContent();
            this.b();
            return content;
        }
        catch (IOException ex) {
            this.a(ex);
            throw ex;
        }
    }
    
    @Override
    public final Object getContent(final Class[] array) {
        this.a();
        try {
            final Object content = this.b.getContent(array);
            this.b();
            return content;
        }
        catch (IOException ex) {
            this.a(ex);
            throw ex;
        }
    }
    
    @Override
    public final String getContentEncoding() {
        this.a();
        final String contentEncoding = this.b.getContentEncoding();
        this.b();
        return contentEncoding;
    }
    
    @Override
    public final int getContentLength() {
        return this.b.getContentLength();
    }
    
    @Override
    public final String getContentType() {
        this.a();
        final String contentType = this.b.getContentType();
        this.b();
        return contentType;
    }
    
    @Override
    public final long getDate() {
        return this.b.getDate();
    }
    
    @Override
    public final boolean getDefaultUseCaches() {
        return this.b.getDefaultUseCaches();
    }
    
    @Override
    public final boolean getDoInput() {
        return this.b.getDoInput();
    }
    
    @Override
    public final boolean getDoOutput() {
        return this.b.getDoOutput();
    }
    
    @Override
    public final InputStream getErrorStream() {
        this.a();
        final InputStream errorStream = this.b.getErrorStream();
        this.b();
        if (errorStream != null) {
            try {
                return new t(errorStream, this.a, this.c);
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                dy.a(t);
            }
        }
        return errorStream;
    }
    
    @Override
    public final long getExpiration() {
        return this.b.getExpiration();
    }
    
    @Override
    public final String getHeaderField(final int n) {
        this.a();
        final String headerField = this.b.getHeaderField(n);
        this.b();
        return headerField;
    }
    
    @Override
    public final String getHeaderField(String headerField) {
        this.a();
        headerField = this.b.getHeaderField(headerField);
        this.b();
        return headerField;
    }
    
    @Override
    public final long getHeaderFieldDate(final String s, long headerFieldDate) {
        this.a();
        headerFieldDate = this.b.getHeaderFieldDate(s, headerFieldDate);
        this.b();
        return headerFieldDate;
    }
    
    @Override
    public final int getHeaderFieldInt(final String s, int headerFieldInt) {
        this.a();
        headerFieldInt = this.b.getHeaderFieldInt(s, headerFieldInt);
        this.b();
        return headerFieldInt;
    }
    
    @Override
    public final String getHeaderFieldKey(final int n) {
        this.a();
        final String headerFieldKey = this.b.getHeaderFieldKey(n);
        this.b();
        return headerFieldKey;
    }
    
    @Override
    public final Map getHeaderFields() {
        this.a();
        final Map<String, List<String>> headerFields = this.b.getHeaderFields();
        this.b();
        return headerFields;
    }
    
    @Override
    public final HostnameVerifier getHostnameVerifier() {
        return this.b.getHostnameVerifier();
    }
    
    @Override
    public final long getIfModifiedSince() {
        return this.b.getIfModifiedSince();
    }
    
    @Override
    public final InputStream getInputStream() {
        this.a();
        InputStream inputStream = null;
        try {
            inputStream = this.b.getInputStream();
            this.b();
            if (inputStream != null) {
                final InputStream inputStream2 = inputStream;
                final s s = this;
                final e e = s.a;
                final s s2 = this;
                final c c = s2.c;
                final t t = new t(inputStream2, e, c);
                return t;
            }
            return inputStream;
        }
        catch (IOException inputStream) {
            this.a((Throwable)inputStream);
            throw inputStream;
        }
        try {
            final InputStream inputStream2 = inputStream;
            final s s = this;
            final e e = s.a;
            final s s2 = this;
            final c c = s2.c;
            final t t2;
            final t t = t2 = new t(inputStream2, e, c);
            return t2;
        }
        catch (ThreadDeath threadDeath) {
            throw threadDeath;
        }
        catch (Throwable t3) {
            dy.a(t3);
        }
        return inputStream;
    }
    
    @Override
    public final boolean getInstanceFollowRedirects() {
        return this.b.getInstanceFollowRedirects();
    }
    
    @Override
    public final long getLastModified() {
        return this.b.getLastModified();
    }
    
    @Override
    public final Certificate[] getLocalCertificates() {
        return this.b.getLocalCertificates();
    }
    
    @Override
    public final Principal getLocalPrincipal() {
        return this.b.getLocalPrincipal();
    }
    
    @Override
    public final OutputStream getOutputStream() {
        final OutputStream outputStream = this.b.getOutputStream();
        if (outputStream != null) {
            try {
                return new u(outputStream, this.c);
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                dy.a(t);
            }
        }
        return outputStream;
    }
    
    @Override
    public final Principal getPeerPrincipal() {
        return this.b.getPeerPrincipal();
    }
    
    @Override
    public final Permission getPermission() {
        return this.b.getPermission();
    }
    
    @Override
    public final int getReadTimeout() {
        return this.b.getReadTimeout();
    }
    
    @Override
    public final String getRequestMethod() {
        return this.b.getRequestMethod();
    }
    
    @Override
    public final Map getRequestProperties() {
        return this.b.getRequestProperties();
    }
    
    @Override
    public final String getRequestProperty(final String s) {
        return this.b.getRequestProperty(s);
    }
    
    @Override
    public final int getResponseCode() {
        this.a();
        try {
            final int responseCode = this.b.getResponseCode();
            this.b();
            return responseCode;
        }
        catch (IOException ex) {
            this.a(ex);
            throw ex;
        }
    }
    
    @Override
    public final String getResponseMessage() {
        this.a();
        try {
            final String responseMessage = this.b.getResponseMessage();
            this.b();
            return responseMessage;
        }
        catch (IOException ex) {
            this.a(ex);
            throw ex;
        }
    }
    
    @Override
    public final SSLSocketFactory getSSLSocketFactory() {
        return this.b.getSSLSocketFactory();
    }
    
    @Override
    public final Certificate[] getServerCertificates() {
        return this.b.getServerCertificates();
    }
    
    @Override
    public final URL getURL() {
        return this.b.getURL();
    }
    
    @Override
    public final boolean getUseCaches() {
        return this.b.getUseCaches();
    }
    
    @Override
    public final int hashCode() {
        return this.b.hashCode();
    }
    
    @Override
    public final void setAllowUserInteraction(final boolean allowUserInteraction) {
        this.b.setAllowUserInteraction(allowUserInteraction);
    }
    
    @Override
    public final void setChunkedStreamingMode(final int chunkedStreamingMode) {
        this.b.setChunkedStreamingMode(chunkedStreamingMode);
    }
    
    @Override
    public final void setConnectTimeout(final int connectTimeout) {
        this.b.setConnectTimeout(connectTimeout);
    }
    
    @Override
    public final void setDefaultUseCaches(final boolean defaultUseCaches) {
        this.b.setDefaultUseCaches(defaultUseCaches);
    }
    
    @Override
    public final void setDoInput(final boolean doInput) {
        this.b.setDoInput(doInput);
    }
    
    @Override
    public final void setDoOutput(final boolean doOutput) {
        this.b.setDoOutput(doOutput);
    }
    
    @Override
    public final void setFixedLengthStreamingMode(final int fixedLengthStreamingMode) {
        this.b.setFixedLengthStreamingMode(fixedLengthStreamingMode);
    }
    
    @Override
    public final void setHostnameVerifier(final HostnameVerifier hostnameVerifier) {
        this.b.setHostnameVerifier(hostnameVerifier);
    }
    
    @Override
    public final void setIfModifiedSince(final long ifModifiedSince) {
        this.b.setIfModifiedSince(ifModifiedSince);
    }
    
    @Override
    public final void setInstanceFollowRedirects(final boolean instanceFollowRedirects) {
        this.b.setInstanceFollowRedirects(instanceFollowRedirects);
    }
    
    @Override
    public final void setReadTimeout(final int readTimeout) {
        this.b.setReadTimeout(readTimeout);
    }
    
    @Override
    public final void setRequestMethod(final String requestMethod) {
        this.b.setRequestMethod(requestMethod);
    }
    
    @Override
    public final void setRequestProperty(final String s, final String s2) {
        this.b.setRequestProperty(s, s2);
    }
    
    @Override
    public final void setSSLSocketFactory(final SSLSocketFactory sslSocketFactory) {
        SSLSocketFactory a = sslSocketFactory;
        while (true) {
            try {
                if (sslSocketFactory instanceof ab) {
                    a = ((ab)sslSocketFactory).a();
                }
                this.b.setSSLSocketFactory(a);
            }
            catch (ThreadDeath threadDeath) {
                throw threadDeath;
            }
            catch (Throwable t) {
                dy.a(t);
                a = sslSocketFactory;
                continue;
            }
            break;
        }
    }
    
    @Override
    public final void setUseCaches(final boolean useCaches) {
        this.b.setUseCaches(useCaches);
    }
    
    @Override
    public final String toString() {
        return this.b.toString();
    }
    
    @Override
    public final boolean usingProxy() {
        return this.b.usingProxy();
    }
}
