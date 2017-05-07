// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.ads.a;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import android.content.Context;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class x implements SafeParcelable
{
    public static final y CREATOR;
    public final String eF;
    public final boolean eG;
    public final x[] eH;
    public final int height;
    public final int heightPixels;
    public final int versionCode;
    public final int width;
    public final int widthPixels;
    
    static {
        CREATOR = new y();
    }
    
    public x() {
        this(2, "interstitial_mb", 0, 0, true, 0, 0, null);
    }
    
    x(final int versionCode, final String ef, final int height, final int heightPixels, final boolean eg, final int width, final int widthPixels, final x[] eh) {
        this.versionCode = versionCode;
        this.eF = ef;
        this.height = height;
        this.heightPixels = heightPixels;
        this.eG = eg;
        this.width = width;
        this.widthPixels = widthPixels;
        this.eH = eh;
    }
    
    public x(final Context context, final AdSize adSize) {
        this(context, new AdSize[] { adSize });
    }
    
    public x(final Context context, final AdSize[] array) {
        final int n = 0;
        final AdSize adSize = array[0];
        this.versionCode = 2;
        this.eG = false;
        this.width = adSize.getWidth();
        this.height = adSize.getHeight();
        boolean b;
        if (this.width == -1) {
            b = true;
        }
        else {
            b = false;
        }
        boolean b2;
        if (this.height == -2) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width;
        if (b) {
            this.widthPixels = a(displayMetrics);
            width = (int)(this.widthPixels / displayMetrics.density);
        }
        else {
            width = this.width;
            this.widthPixels = cs.a(displayMetrics, this.width);
        }
        int n2;
        if (b2) {
            n2 = c(displayMetrics);
        }
        else {
            n2 = this.height;
        }
        this.heightPixels = cs.a(displayMetrics, n2);
        if (b || b2) {
            this.eF = width + "x" + n2 + "_as";
        }
        else {
            this.eF = adSize.toString();
        }
        if (array.length > 1) {
            this.eH = new x[array.length];
            for (int i = n; i < array.length; ++i) {
                this.eH[i] = new x(context, array[i]);
            }
        }
        else {
            this.eH = null;
        }
    }
    
    public x(final x x, final x[] array) {
        this(2, x.eF, x.height, x.heightPixels, x.eG, x.width, x.widthPixels, array);
    }
    
    public static int a(final DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }
    
    public static int b(final DisplayMetrics displayMetrics) {
        return (int)(c(displayMetrics) * displayMetrics.density);
    }
    
    private static int c(final DisplayMetrics displayMetrics) {
        final int n = (int)(displayMetrics.heightPixels / displayMetrics.density);
        if (n <= 400) {
            return 32;
        }
        if (n <= 720) {
            return 50;
        }
        return 90;
    }
    
    public AdSize P() {
        return a.a(this.width, this.height, this.eF);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        y.a(this, parcel, n);
    }
}
