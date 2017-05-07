// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import com.google.android.gms.internal.zzgk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgk
public final class MobileAdsSettingsParcel implements SafeParcelable
{
    public static final zzad CREATOR;
    public final int versionCode;
    public final boolean zzty;
    public final String zztz;
    
    static {
        CREATOR = new zzad();
    }
    
    public MobileAdsSettingsParcel(final int versionCode, final boolean zzty, final String zztz) {
        this.versionCode = versionCode;
        this.zzty = zzty;
        this.zztz = zztz;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzad.zza(this, parcel, n);
    }
}
