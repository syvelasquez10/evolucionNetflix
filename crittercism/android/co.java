// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import com.crittercism.error.CRXMLHttpRequestException;

public enum co
{
    a("Generic", 0), 
    b("NSURLConnection", 1), 
    c("ASI", 2), 
    d("Android", 3), 
    e("XMLHttpRequest", 4);
    
    private co(final String s, final int n) {
    }
    
    public static int a(final Throwable t) {
        int n = co.d.ordinal();
        if (t instanceof CRXMLHttpRequestException) {
            n = co.e.ordinal();
        }
        return n;
    }
}
