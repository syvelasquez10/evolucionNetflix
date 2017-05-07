// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.cast.ApplicationMetadata;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DeviceStatus implements SafeParcelable
{
    public static final Parcelable$Creator<DeviceStatus> CREATOR;
    private final int mVersionCode;
    private double zzWA;
    private boolean zzWB;
    private int zzYO;
    private int zzYP;
    private ApplicationMetadata zzYZ;
    
    static {
        CREATOR = (Parcelable$Creator)new zzg();
    }
    
    public DeviceStatus() {
        this(3, Double.NaN, false, -1, null, -1);
    }
    
    DeviceStatus(final int mVersionCode, final double zzWA, final boolean zzWB, final int zzYO, final ApplicationMetadata zzYZ, final int zzYP) {
        this.mVersionCode = mVersionCode;
        this.zzWA = zzWA;
        this.zzWB = zzWB;
        this.zzYO = zzYO;
        this.zzYZ = zzYZ;
        this.zzYP = zzYP;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof DeviceStatus)) {
                return false;
            }
            final DeviceStatus deviceStatus = (DeviceStatus)o;
            if (this.zzWA != deviceStatus.zzWA || this.zzWB != deviceStatus.zzWB || this.zzYO != deviceStatus.zzYO || !zzf.zza(this.zzYZ, deviceStatus.zzYZ) || this.zzYP != deviceStatus.zzYP) {
                return false;
            }
        }
        return true;
    }
    
    public ApplicationMetadata getApplicationMetadata() {
        return this.zzYZ;
    }
    
    public int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzWA, this.zzWB, this.zzYO, this.zzYZ, this.zzYP);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzg.zza(this, parcel, n);
    }
    
    public double zzmU() {
        return this.zzWA;
    }
    
    public int zzmV() {
        return this.zzYO;
    }
    
    public int zzmW() {
        return this.zzYP;
    }
    
    public boolean zznd() {
        return this.zzWB;
    }
}
