// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.credentials.internal;

import android.os.Parcel;
import com.google.android.gms.auth.api.credentials.Credential;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class SaveRequest implements SafeParcelable
{
    public static final Parcelable$Creator<SaveRequest> CREATOR;
    final int zzCY;
    private final Credential zzPa;
    
    static {
        CREATOR = (Parcelable$Creator)new zzf();
    }
    
    SaveRequest(final int zzCY, final Credential zzPa) {
        this.zzCY = zzCY;
        this.zzPa = zzPa;
    }
    
    public SaveRequest(final Credential credential) {
        this(1, credential);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Credential getCredential() {
        return this.zzPa;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzf.zza(this, parcel, n);
    }
}
