// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.zza;
import android.os.Parcel;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import android.content.Context;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgr
public class AdSizeParcel implements SafeParcelable
{
    public static final zzi CREATOR;
    public final int height;
    public final int heightPixels;
    public final int versionCode;
    public final int width;
    public final int widthPixels;
    public final String zzte;
    public final boolean zztf;
    public final AdSizeParcel[] zztg;
    public final boolean zzth;
    public final boolean zzti;
    
    static {
        CREATOR = new zzi();
    }
    
    public AdSizeParcel() {
        this(4, "interstitial_mb", 0, 0, true, 0, 0, null, false, false);
    }
    
    AdSizeParcel(final int versionCode, final String zzte, final int height, final int heightPixels, final boolean zztf, final int width, final int widthPixels, final AdSizeParcel[] zztg, final boolean zzth, final boolean zzti) {
        this.versionCode = versionCode;
        this.zzte = zzte;
        this.height = height;
        this.heightPixels = heightPixels;
        this.zztf = zztf;
        this.width = width;
        this.widthPixels = widthPixels;
        this.zztg = zztg;
        this.zzth = zzth;
        this.zzti = zzti;
    }
    
    public AdSizeParcel(final Context context, final AdSize adSize) {
        this(context, new AdSize[] { adSize });
    }
    
    public AdSizeParcel(final Context context, final AdSize[] array) {
        final AdSize adSize = array[0];
        this.versionCode = 4;
        this.zztf = false;
        this.zzti = adSize.isFluid();
        if (this.zzti) {
            this.width = AdSize.BANNER.getWidth();
            this.height = AdSize.BANNER.getHeight();
        }
        else {
            this.width = adSize.getWidth();
            this.height = adSize.getHeight();
        }
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
            if (zzl.zzcF().zzS(context) && zzl.zzcF().zzT(context)) {
                this.widthPixels = zza(displayMetrics) - zzl.zzcF().zzU(context);
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
            this.widthPixels = zzl.zzcF().zza(displayMetrics, this.width);
        }
        int n2;
        if (b2) {
            n2 = zzc(displayMetrics);
        }
        else {
            n2 = this.height;
        }
        this.heightPixels = zzl.zzcF().zza(displayMetrics, n2);
        if (b || b2) {
            this.zzte = width + "x" + n2 + "_as";
        }
        else if (this.zzti) {
            this.zzte = "320x50_mb";
        }
        else {
            this.zzte = adSize.toString();
        }
        if (array.length > 1) {
            this.zztg = new AdSizeParcel[array.length];
            for (int i = 0; i < array.length; ++i) {
                this.zztg[i] = new AdSizeParcel(context, array[i]);
            }
        }
        else {
            this.zztg = null;
        }
        this.zzth = false;
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
        zzi.zza(this, parcel, n);
    }
    
    public AdSize zzcD() {
        return zza.zza(this.width, this.height, this.zzte);
    }
}
