// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.zza;
import android.os.Parcel;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import android.content.Context;
import com.google.android.gms.internal.zzgk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgk
public final class AdSizeParcel implements SafeParcelable
{
    public static final zzh CREATOR;
    public final int height;
    public final int heightPixels;
    public final int versionCode;
    public final int width;
    public final int widthPixels;
    public final String zzsG;
    public final boolean zzsH;
    public final AdSizeParcel[] zzsI;
    public final boolean zzsJ;
    
    static {
        CREATOR = new zzh();
    }
    
    public AdSizeParcel() {
        this(3, "interstitial_mb", 0, 0, true, 0, 0, null, false);
    }
    
    AdSizeParcel(final int versionCode, final String zzsG, final int height, final int heightPixels, final boolean zzsH, final int width, final int widthPixels, final AdSizeParcel[] zzsI, final boolean zzsJ) {
        this.versionCode = versionCode;
        this.zzsG = zzsG;
        this.height = height;
        this.heightPixels = heightPixels;
        this.zzsH = zzsH;
        this.width = width;
        this.widthPixels = widthPixels;
        this.zzsI = zzsI;
        this.zzsJ = zzsJ;
    }
    
    public AdSizeParcel(final Context context, final AdSize adSize) {
        this(context, new AdSize[] { adSize });
    }
    
    public AdSizeParcel(final Context context, final AdSize[] array) {
        final AdSize adSize = array[0];
        this.versionCode = 3;
        this.zzsH = false;
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
            if (zzk.zzcE().zzS(context) && zzk.zzcE().zzT(context)) {
                this.widthPixels = zza(displayMetrics) - zzk.zzcE().zzU(context);
            }
            else {
                this.widthPixels = zza(displayMetrics);
            }
            final double n = this.widthPixels / displayMetrics.density;
            width = (int)n;
            if (n - (int)n >= 0.01) {
                ++width;
            }
        }
        else {
            width = this.width;
            this.widthPixels = zzk.zzcE().zza(displayMetrics, this.width);
        }
        int n2;
        if (b2) {
            n2 = zzc(displayMetrics);
        }
        else {
            n2 = this.height;
        }
        this.heightPixels = zzk.zzcE().zza(displayMetrics, n2);
        if (b || b2) {
            this.zzsG = width + "x" + n2 + "_as";
        }
        else {
            this.zzsG = adSize.toString();
        }
        if (array.length > 1) {
            this.zzsI = new AdSizeParcel[array.length];
            for (int i = 0; i < array.length; ++i) {
                this.zzsI[i] = new AdSizeParcel(context, array[i]);
            }
        }
        else {
            this.zzsI = null;
        }
        this.zzsJ = false;
    }
    
    public static int zza(final DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }
    
    public static int zzb(final DisplayMetrics displayMetrics) {
        return (int)(zzc(displayMetrics) * displayMetrics.density);
    }
    
    private static int zzc(final DisplayMetrics displayMetrics) {
        final int n = (int)(displayMetrics.heightPixels / displayMetrics.density);
        if (n <= 400) {
            return 32;
        }
        if (n <= 720) {
            return 50;
        }
        return 90;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzh.zza(this, parcel, n);
    }
    
    public AdSize zzcC() {
        return zza.zza(this.width, this.height, this.zzsG);
    }
}
