// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import android.os.Parcel;
import com.google.android.gms.internal.zzgk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgk
public final class InterstitialAdParameterParcel implements SafeParcelable
{
    public static final zzl CREATOR;
    public final int versionCode;
    public final boolean zzpk;
    public final boolean zzpl;
    
    static {
        CREATOR = new zzl();
    }
    
    InterstitialAdParameterParcel(final int versionCode, final boolean zzpk, final boolean zzpl) {
        this.versionCode = versionCode;
        this.zzpk = zzpk;
        this.zzpl = zzpl;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzl.zza(this, parcel, n);
    }
}
