// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import android.os.Parcel;
import com.google.android.gms.common.api.Status;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.api.Result;

public final class FusedLocationProviderResult implements Result, SafeParcelable
{
    public static final Parcelable$Creator<FusedLocationProviderResult> CREATOR;
    public static final FusedLocationProviderResult zzaFr;
    private final int mVersionCode;
    private final Status zzSC;
    
    static {
        zzaFr = new FusedLocationProviderResult(Status.zzabb);
        CREATOR = (Parcelable$Creator)new zze();
    }
    
    FusedLocationProviderResult(final int mVersionCode, final Status zzSC) {
        this.mVersionCode = mVersionCode;
        this.zzSC = zzSC;
    }
    
    public FusedLocationProviderResult(final Status status) {
        this(1, status);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public Status getStatus() {
        return this.zzSC;
    }
    
    public int getVersionCode() {
        return this.mVersionCode;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zze.zza(this, parcel, n);
    }
}
