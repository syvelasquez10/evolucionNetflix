// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzw;
import android.location.Location;
import java.util.List;
import android.os.Bundle;
import com.google.android.gms.internal.zzgk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgk
public final class AdRequestParcel implements SafeParcelable
{
    public static final zzf CREATOR;
    public final Bundle extras;
    public final int versionCode;
    public final Bundle zzsA;
    public final Bundle zzsB;
    public final List<String> zzsC;
    public final String zzsD;
    public final String zzsE;
    public final long zzsq;
    public final int zzsr;
    public final List<String> zzss;
    public final boolean zzst;
    public final int zzsu;
    public final boolean zzsv;
    public final String zzsw;
    public final SearchAdRequestParcel zzsx;
    public final Location zzsy;
    public final String zzsz;
    
    static {
        CREATOR = new zzf();
    }
    
    public AdRequestParcel(final int versionCode, final long zzsq, final Bundle bundle, final int zzsr, final List<String> zzss, final boolean zzst, final int zzsu, final boolean zzsv, final String zzsw, final SearchAdRequestParcel zzsx, final Location zzsy, final String zzsz, final Bundle zzsA, final Bundle zzsB, final List<String> zzsC, final String zzsD, final String zzsE) {
        this.versionCode = versionCode;
        this.zzsq = zzsq;
        Bundle extras = bundle;
        if (bundle == null) {
            extras = new Bundle();
        }
        this.extras = extras;
        this.zzsr = zzsr;
        this.zzss = zzss;
        this.zzst = zzst;
        this.zzsu = zzsu;
        this.zzsv = zzsv;
        this.zzsw = zzsw;
        this.zzsx = zzsx;
        this.zzsy = zzsy;
        this.zzsz = zzsz;
        this.zzsA = zzsA;
        this.zzsB = zzsB;
        this.zzsC = zzsC;
        this.zzsD = zzsD;
        this.zzsE = zzsE;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof AdRequestParcel) {
            final AdRequestParcel adRequestParcel = (AdRequestParcel)o;
            if (this.versionCode == adRequestParcel.versionCode && this.zzsq == adRequestParcel.zzsq && zzw.equal(this.extras, adRequestParcel.extras) && this.zzsr == adRequestParcel.zzsr && zzw.equal(this.zzss, adRequestParcel.zzss) && this.zzst == adRequestParcel.zzst && this.zzsu == adRequestParcel.zzsu && this.zzsv == adRequestParcel.zzsv && zzw.equal(this.zzsw, adRequestParcel.zzsw) && zzw.equal(this.zzsx, adRequestParcel.zzsx) && zzw.equal(this.zzsy, adRequestParcel.zzsy) && zzw.equal(this.zzsz, adRequestParcel.zzsz) && zzw.equal(this.zzsA, adRequestParcel.zzsA) && zzw.equal(this.zzsB, adRequestParcel.zzsB) && zzw.equal(this.zzsC, adRequestParcel.zzsC) && zzw.equal(this.zzsD, adRequestParcel.zzsD) && zzw.equal(this.zzsE, adRequestParcel.zzsE)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.versionCode, this.zzsq, this.extras, this.zzsr, this.zzss, this.zzst, this.zzsu, this.zzsv, this.zzsw, this.zzsx, this.zzsy, this.zzsz, this.zzsA, this.zzsB, this.zzsC, this.zzsD, this.zzsE);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzf.zza(this, parcel, n);
    }
}
