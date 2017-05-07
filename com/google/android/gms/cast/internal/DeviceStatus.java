// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.cast.ApplicationMetadata;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DeviceStatus implements SafeParcelable
{
    public static final Parcelable$Creator<DeviceStatus> CREATOR;
    private final int zzCY;
    private double zzSg;
    private boolean zzSh;
    private ApplicationMetadata zzUE;
    private int zzUt;
    private int zzUu;
    
    static {
        CREATOR = (Parcelable$Creator)new zzg();
    }
    
    public DeviceStatus() {
        this(3, Double.NaN, false, -1, null, -1);
    }
    
    DeviceStatus(final int zzCY, final double zzSg, final boolean zzSh, final int zzUt, final ApplicationMetadata zzUE, final int zzUu) {
        this.zzCY = zzCY;
        this.zzSg = zzSg;
        this.zzSh = zzSh;
        this.zzUt = zzUt;
        this.zzUE = zzUE;
        this.zzUu = zzUu;
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
            if (this.zzSg != deviceStatus.zzSg || this.zzSh != deviceStatus.zzSh || this.zzUt != deviceStatus.zzUt || !zzf.zza(this.zzUE, deviceStatus.zzUE) || this.zzUu != deviceStatus.zzUu) {
                return false;
            }
        }
        return true;
    }
    
    public ApplicationMetadata getApplicationMetadata() {
        return this.zzUE;
    }
    
    public int getVersionCode() {
        return this.zzCY;
    }
    
    @Override
    public int hashCode() {
        return zzt.hashCode(this.zzSg, this.zzSh, this.zzUt, this.zzUE, this.zzUu);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzg.zza(this, parcel, n);
    }
    
    public double zzlM() {
        return this.zzSg;
    }
    
    public int zzlN() {
        return this.zzUt;
    }
    
    public int zzlO() {
        return this.zzUu;
    }
    
    public boolean zzlV() {
        return this.zzSh;
    }
}
