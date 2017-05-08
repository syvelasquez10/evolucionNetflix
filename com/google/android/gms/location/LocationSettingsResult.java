// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.api.Result;

public final class LocationSettingsResult implements Result, SafeParcelable
{
    public static final Parcelable$Creator<LocationSettingsResult> CREATOR;
    private final int mVersionCode;
    private final Status zzSC;
    private final LocationSettingsStates zzaEP;
    
    static {
        CREATOR = (Parcelable$Creator)new zzg();
    }
    
    LocationSettingsResult(final int mVersionCode, final Status zzSC, final LocationSettingsStates zzaEP) {
        this.mVersionCode = mVersionCode;
        this.zzSC = zzSC;
        this.zzaEP = zzaEP;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public LocationSettingsStates getLocationSettingsStates() {
        return this.zzaEP;
    }
    
    @Override
    public Status getStatus() {
        return this.zzSC;
    }
    
    public int getVersionCode() {
        return this.mVersionCode;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzg.zza(this, parcel, n);
    }
}
