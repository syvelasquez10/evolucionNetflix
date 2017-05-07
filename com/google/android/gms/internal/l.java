// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.content.Context;
import android.net.Uri;

public class l
{
    private String kd;
    private String ke;
    private String[] kf;
    private h kg;
    private final g kh;
    
    public l(final h kg) {
        this.kd = "googleads.g.doubleclick.net";
        this.ke = "/pagead/ads";
        this.kf = new String[] { ".doubleclick.net", ".googleadservices.com", ".googlesyndication.com" };
        this.kh = new g();
        this.kg = kg;
    }
    
    private Uri a(final Uri uri, final Context context, final String s, final boolean b) throws m {
        try {
            if (uri.getQueryParameter("ms") != null) {
                throw new m("Query parameter already exists: ms");
            }
        }
        catch (UnsupportedOperationException ex) {
            throw new m("Provided Uri is not in a valid state");
        }
        String s2;
        if (b) {
            s2 = this.kg.a(context, s);
        }
        else {
            s2 = this.kg.a(context);
        }
        return this.a(uri, "ms", s2);
    }
    
    private Uri a(final Uri uri, final String s, final String s2) throws UnsupportedOperationException {
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
    
    public Uri a(Uri a, final Context context) throws m {
        try {
            a = this.a(a, context, a.getQueryParameter("ai"), true);
            return a;
        }
        catch (UnsupportedOperationException ex) {
            throw new m("Provided Uri is not in a valid state");
        }
    }
    
    public void a(final MotionEvent motionEvent) {
        this.kg.a(motionEvent);
    }
    
    public boolean a(final Uri uri) {
        final boolean b = false;
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            final String host = uri.getHost();
            final String[] kf = this.kf;
            final int length = kf.length;
            int n = 0;
            boolean b2;
            while (true) {
                b2 = b;
                if (n >= length) {
                    break;
                }
                if (host.endsWith(kf[n])) {
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
    
    public h y() {
        return this.kg;
    }
}
