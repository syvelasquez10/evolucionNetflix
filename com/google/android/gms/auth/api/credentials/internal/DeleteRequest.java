// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import android.os.Parcel;
import com.google.android.gms.auth.api.credentials.Credential;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class DeleteRequest implements SafeParcelable
{
    public static final Parcelable$Creator<DeleteRequest> CREATOR;
    final int mVersionCode;
    private final Credential zzRx;
    
    static {
        CREATOR = (Parcelable$Creator)new zzf();
    }
    
    DeleteRequest(final int mVersionCode, final Credential zzRx) {
        this.mVersionCode = mVersionCode;
        this.zzRx = zzRx;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Credential getCredential() {
        return this.zzRx;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzf.zza(this, parcel, n);
    }
}
