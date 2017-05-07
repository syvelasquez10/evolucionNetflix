// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.reward.mediation.client;

import android.os.Parcel;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgr
public final class RewardItemParcel implements SafeParcelable
{
    public static final zzc CREATOR;
    public final String type;
    public final int versionCode;
    public final int zzHv;
    
    static {
        CREATOR = new zzc();
    }
    
    public RewardItemParcel(final int versionCode, final String type, final int zzHv) {
        this.versionCode = versionCode;
        this.type = type;
        this.zzHv = zzHv;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
