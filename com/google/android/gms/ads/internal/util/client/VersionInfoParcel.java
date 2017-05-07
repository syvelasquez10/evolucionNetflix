// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.util.client;

import android.os.Parcel;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgr
public final class VersionInfoParcel implements SafeParcelable
{
    public static final zzc CREATOR;
    public final int versionCode;
    public String zzJu;
    public int zzJv;
    public int zzJw;
    public boolean zzJx;
    
    static {
        CREATOR = new zzc();
    }
    
    VersionInfoParcel(final int versionCode, final String zzJu, final int zzJv, final int zzJw, final boolean zzJx) {
        this.versionCode = versionCode;
        this.zzJu = zzJu;
        this.zzJv = zzJv;
        this.zzJw = zzJw;
        this.zzJx = zzJx;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
