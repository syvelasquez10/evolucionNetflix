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

@ez
public final class ay implements SafeParcelable
{
    public static final az CREATOR;
    public final int height;
    public final int heightPixels;
    public final String of;
    public final boolean og;
    public final ay[] oh;
    public final int versionCode;
    public final int width;
    public final int widthPixels;
    
    static {
        CREATOR = new az();
    }
    
    public ay() {
        this(2, "interstitial_mb", 0, 0, true, 0, 0, null);
    }
    
    ay(final int versionCode, final String of, final int height, final int heightPixels, final boolean og, final int width, final int widthPixels, final ay[] oh) {
        this.versionCode = versionCode;
        this.of = of;
        this.height = height;
        this.heightPixels = heightPixels;
        this.og = og;
        this.width = width;
        this.widthPixels = widthPixels;
        this.oh = oh;
    }
    
    public ay(final Context context, final AdSize adSize) {
        this(context, new AdSize[] { adSize });
    }
    
    public ay(final Context context, final AdSize[] array) {
        final int n = 0;
        final AdSize adSize = array[0];
        this.versionCode = 2;
        this.og = false;
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
            this.widthPixels = gr.a(displayMetrics, this.width);
        }
        int n2;
        if (b2) {
            n2 = c(displayMetrics);
        }
        else {
            n2 = this.height;
        }
        this.heightPixels = gr.a(displayMetrics, n2);
        if (b || b2) {
            this.of = width + "x" + n2 + "_as";
        }
        else {
            this.of = adSize.toString();
        }
        if (array.length > 1) {
            this.oh = new ay[array.length];
            for (int i = n; i < array.length; ++i) {
                this.oh[i] = new ay(context, array[i]);
            }
        }
        else {
            this.oh = null;
        }
    }
    
    public ay(final ay ay, final ay[] array) {
        this(2, ay.of, ay.height, ay.heightPixels, ay.og, ay.width, ay.widthPixels, array);
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
    
    public AdSize bc() {
        return a.a(this.width, this.height, this.of);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        az.a(this, parcel, n);
    }
}
