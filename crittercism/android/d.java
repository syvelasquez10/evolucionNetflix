// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.text.ParseException;

public enum d
{
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
    w("UNDEFINED_EXCEPTION", 22, -1, "__UNKNOWN__");
    
    private int x;
    private String y;
    
    private d(final String s, final int n, final int x, final String y) {
        this.x = x;
        this.y = y;
    }
    
    public static d a(final int n) {
        final d[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final d d = values[i];
            if (d.x == n) {
                return d;
            }
        }
        throw new ParseException("Unknown status code: " + Integer.toString(n), 0);
    }
    
    public final int a() {
        return this.x;
    }
}
