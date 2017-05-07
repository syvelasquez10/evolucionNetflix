// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzw;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ApplicationStatus implements SafeParcelable
{
    public static final Parcelable$Creator<ApplicationStatus> CREATOR;
    private final int mVersionCode;
    private String zzYx;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    public ApplicationStatus() {
        this(1, null);
    }
    
    ApplicationStatus(final int mVersionCode, final String zzYx) {
        this.mVersionCode = mVersionCode;
        this.zzYx = zzYx;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof ApplicationStatus && zzf.zza(this.zzYx, ((ApplicationStatus)o).zzYx));
    }
    
    public int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzYx);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public String zzmO() {
        return this.zzYx;
    }
}
