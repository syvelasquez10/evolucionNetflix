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
    private double zzUJ;
    private boolean zzUK;
    private int zzWW;
    private int zzWX;
    private ApplicationMetadata zzXh;
    
    static {
        CREATOR = (Parcelable$Creator)new zzg();
    }
    
    public DeviceStatus() {
        this(3, Double.NaN, false, -1, null, -1);
    }
    
    DeviceStatus(final int mVersionCode, final double zzUJ, final boolean zzUK, final int zzWW, final ApplicationMetadata zzXh, final int zzWX) {
        this.mVersionCode = mVersionCode;
        this.zzUJ = zzUJ;
        this.zzUK = zzUK;
        this.zzWW = zzWW;
        this.zzXh = zzXh;
        this.zzWX = zzWX;
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
            if (this.zzUJ != deviceStatus.zzUJ || this.zzUK != deviceStatus.zzUK || this.zzWW != deviceStatus.zzWW || !zzf.zza(this.zzXh, deviceStatus.zzXh) || this.zzWX != deviceStatus.zzWX) {
                return false;
            }
        }
        return true;
    }
    
    public ApplicationMetadata getApplicationMetadata() {
        return this.zzXh;
    }
    
    public int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzUJ, this.zzUK, this.zzWW, this.zzXh, this.zzWX);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzg.zza(this, parcel, n);
    }
    
    public int zzmA() {
        return this.zzWX;
    }
    
    public boolean zzmH() {
        return this.zzUK;
    }
    
    public double zzmy() {
        return this.zzUJ;
    }
    
    public int zzmz() {
        return this.zzWW;
    }
}
