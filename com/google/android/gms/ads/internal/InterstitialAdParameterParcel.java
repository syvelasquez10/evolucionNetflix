// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import android.os.Parcel;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgr
public final class InterstitialAdParameterParcel implements SafeParcelable
{
    public static final zzl CREATOR;
    public final int versionCode;
    public final boolean zzpt;
    public final boolean zzpu;
    public final String zzpv;
    public final boolean zzpw;
    public final float zzpx;
    
    static {
        CREATOR = new zzl();
    }
    
    InterstitialAdParameterParcel(final int versionCode, final boolean zzpt, final boolean zzpu, final String zzpv, final boolean zzpw, final float zzpx) {
        this.versionCode = versionCode;
        this.zzpt = zzpt;
        this.zzpu = zzpu;
        this.zzpv = zzpv;
        this.zzpw = zzpw;
        this.zzpx = zzpx;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzl.zza(this, parcel, n);
    }
}
