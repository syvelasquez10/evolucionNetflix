// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.util.client;

import android.os.Parcel;
import com.google.android.gms.internal.zzgk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@zzgk
public final class VersionInfoParcel implements SafeParcelable
{
    public static final zzc CREATOR;
    public final int versionCode;
    public int zzIA;
    public int zzIB;
    public boolean zzIC;
    public String zzIz;
    
    static {
        CREATOR = new zzc();
    }
    
    VersionInfoParcel(final int versionCode, final String zzIz, final int zzIA, final int zzIB, final boolean zzIC) {
        this.versionCode = versionCode;
        this.zzIz = zzIz;
        this.zzIA = zzIA;
        this.zzIB = zzIB;
        this.zzIC = zzIC;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
