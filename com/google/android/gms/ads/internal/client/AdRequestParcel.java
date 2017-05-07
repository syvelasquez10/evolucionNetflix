// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzw;
import android.location.Location;
import java.util.List;
import android.os.Bundle;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgr
public final class AdRequestParcel implements SafeParcelable
{
    public static final zzg CREATOR;
    public final Bundle extras;
    public final int versionCode;
    public final long zzsB;
    public final int zzsC;
    public final List<String> zzsD;
    public final boolean zzsE;
    public final int zzsF;
    public final boolean zzsG;
    public final String zzsH;
    public final SearchAdRequestParcel zzsI;
    public final Location zzsJ;
    public final String zzsK;
    public final Bundle zzsL;
    public final Bundle zzsM;
    public final List<String> zzsN;
    public final String zzsO;
    public final String zzsP;
    
    static {
        CREATOR = new zzg();
    }
    
    public AdRequestParcel(final int versionCode, final long zzsB, final Bundle bundle, final int zzsC, final List<String> zzsD, final boolean zzsE, final int zzsF, final boolean zzsG, final String zzsH, final SearchAdRequestParcel zzsI, final Location zzsJ, final String zzsK, final Bundle zzsL, final Bundle zzsM, final List<String> zzsN, final String zzsO, final String zzsP) {
        this.versionCode = versionCode;
        this.zzsB = zzsB;
        Bundle extras = bundle;
        if (bundle == null) {
            extras = new Bundle();
        }
        this.extras = extras;
        this.zzsC = zzsC;
        this.zzsD = zzsD;
        this.zzsE = zzsE;
        this.zzsF = zzsF;
        this.zzsG = zzsG;
        this.zzsH = zzsH;
        this.zzsI = zzsI;
        this.zzsJ = zzsJ;
        this.zzsK = zzsK;
        this.zzsL = zzsL;
        this.zzsM = zzsM;
        this.zzsN = zzsN;
        this.zzsO = zzsO;
        this.zzsP = zzsP;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof AdRequestParcel) {
            final AdRequestParcel adRequestParcel = (AdRequestParcel)o;
            if (this.versionCode == adRequestParcel.versionCode && this.zzsB == adRequestParcel.zzsB && zzw.equal(this.extras, adRequestParcel.extras) && this.zzsC == adRequestParcel.zzsC && zzw.equal(this.zzsD, adRequestParcel.zzsD) && this.zzsE == adRequestParcel.zzsE && this.zzsF == adRequestParcel.zzsF && this.zzsG == adRequestParcel.zzsG && zzw.equal(this.zzsH, adRequestParcel.zzsH) && zzw.equal(this.zzsI, adRequestParcel.zzsI) && zzw.equal(this.zzsJ, adRequestParcel.zzsJ) && zzw.equal(this.zzsK, adRequestParcel.zzsK) && zzw.equal(this.zzsL, adRequestParcel.zzsL) && zzw.equal(this.zzsM, adRequestParcel.zzsM) && zzw.equal(this.zzsN, adRequestParcel.zzsN) && zzw.equal(this.zzsO, adRequestParcel.zzsO) && zzw.equal(this.zzsP, adRequestParcel.zzsP)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.versionCode, this.zzsB, this.extras, this.zzsC, this.zzsD, this.zzsE, this.zzsF, this.zzsG, this.zzsH, this.zzsI, this.zzsJ, this.zzsK, this.zzsL, this.zzsM, this.zzsN, this.zzsO, this.zzsP);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzg.zza(this, parcel, n);
    }
}
