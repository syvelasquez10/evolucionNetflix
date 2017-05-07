// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.content.Context;
import android.net.Uri;

public class k
{
    private String kR;
    private String kS;
    private String kT;
    private String[] kU;
    private g kV;
    
    public k(final g kv) {
        this.kR = "googleads.g.doubleclick.net";
        this.kS = "/pagead/ads";
        this.kT = "ad.doubleclick.net";
        this.kU = new String[] { ".doubleclick.net", ".googleadservices.com", ".googlesyndication.com" };
        this.kV = kv;
    }
    
    private Uri a(Uri a, final Context context, final String s, final boolean b) {
        boolean a2 = false;
        Label_0064: {
            try {
                a2 = this.a(a);
                if (a2) {
                    if (a.toString().contains("dc_ms=")) {
                        throw new l("Parameter already exists: dc_ms");
                    }
                    break Label_0064;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw new l("Provided Uri is not in a valid state");
            }
            if (a.getQueryParameter("ms") != null) {
                throw new l("Query parameter already exists: ms");
            }
        }
        String s2;
        if (b) {
            s2 = this.kV.a(context, s);
        }
        else {
            s2 = this.kV.a(context);
        }
        if (a2) {
            return this.b(a, "dc_ms", s2);
        }
        a = this.a(a, "ms", s2);
        return a;
    }
    
    private Uri a(final Uri uri, final String s, final String s2) {
        final String string = uri.toString();
        int n;
        if ((n = string.indexOf("&adurl")) == -1) {
            n = string.indexOf("?adurl");
        }
        if (n != -1) {
            return Uri.parse(string.substring(0, n + 1) + s + "=" + s2 + "&" + string.substring(n + 1));
        }
        return uri.buildUpon().appendQueryParameter(s, s2).build();
    }
    
    private Uri b(final Uri uri, final String s, final String s2) {
        final String string = uri.toString();
        final int index = string.indexOf(";adurl");
        if (index != -1) {
            return Uri.parse(string.substring(0, index + 1) + s + "=" + s2 + ";" + string.substring(index + 1));
        }
        final String encodedPath = uri.getEncodedPath();
        final int index2 = string.indexOf(encodedPath);
        return Uri.parse(string.substring(0, encodedPath.length() + index2) + ";" + s + "=" + s2 + ";" + string.substring(encodedPath.length() + index2));
    }
    
    public Uri a(Uri a, final Context context) {
        try {
            a = this.a(a, context, a.getQueryParameter("ai"), true);
            return a;
        }
        catch (UnsupportedOperationException ex) {
            throw new l("Provided Uri is not in a valid state");
        }
    }
    
    public void a(final MotionEvent motionEvent) {
        this.kV.a(motionEvent);
    }
    
    public boolean a(final Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            return uri.getHost().equals(this.kT);
        }
        catch (NullPointerException ex) {
            return false;
        }
    }
    
    public boolean b(final Uri uri) {
        final boolean b = false;
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            final String host = uri.getHost();
            final String[] ku = this.kU;
            final int length = ku.length;
            int n = 0;
            boolean b2;
            while (true) {
                b2 = b;
                if (n >= length) {
                    break;
                }
                if (host.endsWith(ku[n])) {
                    b2 = true;
                    break;
                }
                ++n;
            }
            return b2;
        }
        catch (NullPointerException ex) {
            return false;
        }
    }
    
    public g z() {
        return this.kV;
    }
}
