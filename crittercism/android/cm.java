// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.HashMap;

public enum cm
{
    A("SSL_PROTOCOL_EXCEPTION", 26, 26, "javax.net.ssl.SSLProtocolException"), 
    B("UNDEFINED_EXCEPTION", 27, -1, "__UNKNOWN__");
    
    private static HashMap C;
    
    a("OK", 0, 0, (String)null), 
    b("ASSERTION_ERROR", 1, 1, "java.lang.AssertionError"), 
    c("BIND_EXCEPTION", 2, 2, "java.net.BindException"), 
    d("CLASS_NOT_FOUND_EXCEPTION", 3, 3, "java.lang.ClassNotFoundException"), 
    e("ERROR", 4, 4, "java.lang.Error"), 
    f("IO_EXCEPTION", 5, 5, "java.io.IOException"), 
    g("ILLEGAL_ARGUMENT_EXCEPTION", 6, 6, "java.lang.IllegalArgumentException"), 
    h("ILLEGAL_STATE_EXCEPTION", 7, 7, "java.lang.IllegalStateException"), 
    i("INDEX_OUT_OF_BOUNDS_EXCEPTION", 8, 8, "java.lang.IndexOutOfBoundsException"), 
    j("MALFORMED_URL_EXCEPTION", 9, 9, "java.net.MalformedURLException"), 
    k("NO_SUCH_PROVIDER_EXCEPTION", 10, 10, "java.security.NoSuchProviderException"), 
    l("NULL_POINTER_EXCEPTION", 11, 11, "java.lang.NullPointerException"), 
    m("PROTOCOL_EXCEPTION", 12, 12, "java.net.ProtocolException"), 
    n("SECURITY_EXCEPTION", 13, 13, "java.lang.SecurityException"), 
    o("SOCKET_EXCEPTION", 14, 14, "java.net.SocketException"), 
    p("SOCKET_TIMEOUT_EXCEPTION", 15, 15, "java.net.SocketTimeoutException"), 
    q("SSL_PEER_UNVERIFIED_EXCEPTION", 16, 16, "javax.net.ssl.SSLPeerUnverifiedException"), 
    r("STRING_INDEX_OUT_OF_BOUNDS_EXCEPTION", 17, 17, "java.lang.StringIndexOutOfBoundsException"), 
    s("UNKNOWN_HOST_EXCEPTION", 18, 18, "java.net.UnknownHostException"), 
    t("UNKNOWN_SERVICE_EXCEPTION", 19, 19, "java.net.UnknownServiceException"), 
    u("UNSUPPORTED_OPERATION_EXCEPTION", 20, 20, "java.lang.UnsupportedOperationException"), 
    v("URI_SYNTAX_EXCEPTION", 21, 21, "java.net.URISyntaxException"), 
    w("CONNECT_EXCEPTION", 22, 22, "java.net.ConnectException"), 
    x("SSL_EXCEPTION", 23, 23, "javax.net.ssl.SSLException"), 
    y("SSL_HANDSHAKE_EXCEPTION", 24, 24, "javax.net.ssl.SSLHandshakeException"), 
    z("SSL_KEY_EXCEPTION", 25, 25, "javax.net.ssl.SSLKeyException");
    
    private int D;
    private String E;
    
    private cm(final String s, final int n, final int d, final String e) {
        this.D = d;
        this.E = e;
    }
    
    public static cm a(final Throwable t) {
        if (cm.C == null) {
            b();
        }
        Object name = null;
        if (t != null) {
            name = t.getClass().getName();
        }
        cm b;
        if ((b = cm.C.get(name)) == null) {
            b = cm.B;
        }
        return b;
    }
    
    private static void b() {
        synchronized (cm.class) {
            if (cm.C == null) {
                final HashMap<String, cm> c = new HashMap<String, cm>();
                final cm[] values = values();
                for (int length = values.length, i = 0; i < length; ++i) {
                    final cm cm = values[i];
                    c.put(cm.E, cm);
                }
                cm.C = c;
            }
        }
    }
    
    public final int a() {
        return this.D;
    }
}
