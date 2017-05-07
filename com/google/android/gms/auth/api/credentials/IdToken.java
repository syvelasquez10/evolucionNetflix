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
    private final String zzSk;
    private final String zzSs;
    
    static {
        CREATOR = (Parcelable$Creator)new zzd();
    }
    
    IdToken(final int mVersionCode, final String zzSk, final String zzSs) {
        this.mVersionCode = mVersionCode;
        this.zzSk = zzSk;
        this.zzSs = zzSs;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAccountType() {
        return this.zzSk;
    }
    
    public String getIdToken() {
        return this.zzSs;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzd.zza(this, parcel, n);
    }
}
