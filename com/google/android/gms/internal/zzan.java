// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.MotionEvent;
import android.content.Context;
import android.net.Uri;

public class zzan
{
    private static final String[] zznC;
    private String zznA;
    private String[] zznB;
    private zzaj zznD;
    
    static {
        zznC = new String[] { "/aclk", "/pcs/click" };
    }
    
    private Uri zza(Uri zza, final Context context, final String s, final boolean b) {
        boolean zza2 = false;
        Label_0064: {
            try {
                zza2 = this.zza(zza);
                if (zza2) {
                    if (zza.toString().contains("dc_ms=")) {
                        throw new zzao("Parameter already exists: dc_ms");
                    }
                    break Label_0064;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw new zzao("Provided Uri is not in a valid state");
            }
            if (zza.getQueryParameter("ms") != null) {
                throw new zzao("Query parameter already exists: ms");
            }
        }
        String s2;
        if (b) {
            s2 = this.zznD.zzb(context, s);
        }
        else {
            s2 = this.zznD.zzb(context);
        }
        if (zza2) {
            return this.zzb(zza, "dc_ms", s2);
        }
        zza = this.zza(zza, "ms", s2);
        return zza;
    }
    
    private Uri zza(final Uri uri, final String s, final String s2) {
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
    
    private Uri zzb(final Uri uri, final String s, final String s2) {
        final String string = uri.toString();
        final int index = string.indexOf(";adurl");
        if (index != -1) {
            return Uri.parse(string.substring(0, index + 1) + s + "=" + s2 + ";" + string.substring(index + 1));
        }
        final String encodedPath = uri.getEncodedPath();
        final int index2 = string.indexOf(encodedPath);
        return Uri.parse(string.substring(0, encodedPath.length() + index2) + ";" + s + "=" + s2 + ";" + string.substring(encodedPath.length() + index2));
    }
    
    public Uri zza(Uri zza, final Context context) {
        try {
            zza = this.zza(zza, context, zza.getQueryParameter("ai"), true);
            return zza;
        }
        catch (UnsupportedOperationException ex) {
            throw new zzao("Provided Uri is not in a valid state");
        }
    }
    
    public void zza(final MotionEvent motionEvent) {
        this.zznD.zza(motionEvent);
    }
    
    public boolean zza(final Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            return uri.getHost().equals(this.zznA);
        }
        catch (NullPointerException ex) {
            return false;
        }
    }
    
    public zzaj zzab() {
        return this.zznD;
    }
    
    public boolean zzb(final Uri uri) {
        final boolean b = false;
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            final String host = uri.getHost();
            final String[] zznB = this.zznB;
            final int length = zznB.length;
            int n = 0;
            boolean b2;
            while (true) {
                b2 = b;
                if (n >= length) {
                    break;
                }
                if (host.endsWith(zznB[n])) {
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
    
    public boolean zzc(final Uri uri) {
        boolean b = false;
        if (this.zzb(uri)) {
            final String[] zznC = zzan.zznC;
            final int length = zznC.length;
            int n = 0;
            while (true) {
                b = b;
                if (n >= length) {
                    break;
                }
                if (uri.getPath().endsWith(zznC[n])) {
                    b = true;
                    break;
                }
                ++n;
            }
        }
        return b;
    }
}
