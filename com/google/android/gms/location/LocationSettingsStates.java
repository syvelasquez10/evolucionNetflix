// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LocationSettingsStates implements SafeParcelable
{
    public static final Parcelable$Creator<LocationSettingsStates> CREATOR;
    private final int mVersionCode;
    private final boolean zzaEQ;
    private final boolean zzaER;
    private final boolean zzaES;
    private final boolean zzaET;
    private final boolean zzaEU;
    private final boolean zzaEV;
    private final boolean zzaEW;
    
    static {
        CREATOR = (Parcelable$Creator)new zzh();
    }
    
    LocationSettingsStates(final int mVersionCode, final boolean zzaEQ, final boolean zzaER, final boolean zzaES, final boolean zzaET, final boolean zzaEU, final boolean zzaEV, final boolean zzaEW) {
        this.mVersionCode = mVersionCode;
        this.zzaEQ = zzaEQ;
        this.zzaER = zzaER;
        this.zzaES = zzaES;
        this.zzaET = zzaET;
        this.zzaEU = zzaEU;
        this.zzaEV = zzaEV;
        this.zzaEW = zzaEW;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.mVersionCode;
    }
    
    public boolean isBlePresent() {
        return this.zzaEV;
    }
    
    public boolean isBleUsable() {
        return this.zzaES;
    }
    
    public boolean isGpsPresent() {
        return this.zzaET;
    }
    
    public boolean isGpsUsable() {
        return this.zzaEQ;
    }
    
    public boolean isNetworkLocationPresent() {
        return this.zzaEU;
    }
    
    public boolean isNetworkLocationUsable() {
        return this.zzaER;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzh.zza(this, parcel, n);
    }
    
    public boolean zzwA() {
        return this.zzaEW;
    }
}
