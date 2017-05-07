// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.reward.mediation.client;

import android.os.Parcel;
import com.google.android.gms.internal.zzgk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgk
public final class RewardItemParcel implements SafeParcelable
{
    public static final zzc CREATOR;
    public final String type;
    public final int versionCode;
    public final int zzGE;
    
    static {
        CREATOR = new zzc();
    }
    
    public RewardItemParcel(final int versionCode, final String type, final int zzGE) {
        this.versionCode = versionCode;
        this.type = type;
        this.zzGE = zzGE;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
