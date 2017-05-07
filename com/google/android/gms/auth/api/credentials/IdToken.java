// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class IdToken implements SafeParcelable
{
    public static final Parcelable$Creator<IdToken> CREATOR;
    final int mVersionCode;
    private final String zzRf;
    private final String zzRn;
    
    static {
        CREATOR = (Parcelable$Creator)new zzd();
    }
    
    IdToken(final int mVersionCode, final String zzRf, final String zzRn) {
        this.mVersionCode = mVersionCode;
        this.zzRf = zzRf;
        this.zzRn = zzRn;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAccountType() {
        return this.zzRf;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzd.zza(this, parcel, n);
    }
    
    public String zzlv() {
        return this.zzRn;
    }
}
