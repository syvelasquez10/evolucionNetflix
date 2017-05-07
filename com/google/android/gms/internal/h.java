// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.content.Context;
import android.net.Uri;

public class h
{
    private String dK;
    private String dL;
    private String[] dM;
    private d dN;
    private final c dO;
    
    public h(final d dn) {
        this.dK = "googleads.g.doubleclick.net";
        this.dL = "/pagead/ads";
        this.dM = new String[] { ".doubleclick.net", ".googleadservices.com", ".googlesyndication.com" };
        this.dO = new c();
        this.dN = dn;
    }
    
    private Uri a(final Uri uri, final Context context, final String s, final boolean b) throws i {
        try {
            if (uri.getQueryParameter("ms") != null) {
                throw new i("Query parameter already exists: ms");
            }
        }
        catch (UnsupportedOperationException ex) {
            throw new i("Provided Uri is not in a valid state");
        }
        String s2;
        if (b) {
            s2 = this.dN.a(context, s);
        }
        else {
            s2 = this.dN.a(context);
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
    
    public Uri a(Uri a, final Context context) throws i {
        try {
            a = this.a(a, context, a.getQueryParameter("ai"), true);
            return a;
        }
        catch (UnsupportedOperationException ex) {
            throw new i("Provided Uri is not in a valid state");
        }
    }
    
    public void a(final MotionEvent motionEvent) {
        this.dN.a(motionEvent);
    }
    
    public boolean a(final Uri uri) {
        final boolean b = false;
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            final String host = uri.getHost();
            final String[] dm = this.dM;
            final int length = dm.length;
            int n = 0;
            boolean b2;
            while (true) {
                b2 = b;
                if (n >= length) {
                    break;
                }
                if (host.endsWith(dm[n])) {
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
    
    public d g() {
        return this.dN;
    }
}
