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
    final int zzCY;
    private final String zzOW;
    private final String zzOZ;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    IdToken(final int zzCY, final String zzOW, final String zzOZ) {
        this.zzCY = zzCY;
        this.zzOW = zzOW;
        this.zzOZ = zzOZ;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAccountType() {
        return this.zzOW;
    }
    
    public String getIdToken() {
        return this.zzOZ;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzc.zza(this, parcel, n);
    }
}
