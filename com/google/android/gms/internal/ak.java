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

public final class ak implements SafeParcelable
{
    public static final al CREATOR;
    public final int height;
    public final int heightPixels;
    public final String lS;
    public final boolean lT;
    public final ak[] lU;
    public final int versionCode;
    public final int width;
    public final int widthPixels;
    
    static {
        CREATOR = new al();
    }
    
    public ak() {
        this(2, "interstitial_mb", 0, 0, true, 0, 0, null);
    }
    
    ak(final int versionCode, final String ls, final int height, final int heightPixels, final boolean lt, final int width, final int widthPixels, final ak[] lu) {
        this.versionCode = versionCode;
        this.lS = ls;
        this.height = height;
        this.heightPixels = heightPixels;
        this.lT = lt;
        this.width = width;
        this.widthPixels = widthPixels;
        this.lU = lu;
    }
    
    public ak(final Context context, final AdSize adSize) {
        this(context, new AdSize[] { adSize });
    }
    
    public ak(final Context context, final AdSize[] array) {
        final int n = 0;
        final AdSize adSize = array[0];
        this.versionCode = 2;
        this.lT = false;
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
            this.widthPixels = dv.a(displayMetrics, this.width);
        }
        int n2;
        if (b2) {
            n2 = c(displayMetrics);
        }
        else {
            n2 = this.height;
        }
        this.heightPixels = dv.a(displayMetrics, n2);
        if (b || b2) {
            this.lS = width + "x" + n2 + "_as";
        }
        else {
            this.lS = adSize.toString();
        }
        if (array.length > 1) {
            this.lU = new ak[array.length];
            for (int i = n; i < array.length; ++i) {
                this.lU[i] = new ak(context, array[i]);
            }
        }
        else {
            this.lU = null;
        }
    }
    
    public ak(final ak ak, final ak[] array) {
        this(2, ak.lS, ak.height, ak.heightPixels, ak.lT, ak.width, ak.widthPixels, array);
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
    
    public AdSize aA() {
        return a.a(this.width, this.height, this.lS);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        al.a(this, parcel, n);
    }
}
