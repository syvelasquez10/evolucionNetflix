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
    private String zzWF;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    public ApplicationStatus() {
        this(1, null);
    }
    
    ApplicationStatus(final int mVersionCode, final String zzWF) {
        this.mVersionCode = mVersionCode;
        this.zzWF = zzWF;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof ApplicationStatus && zzf.zza(this.zzWF, ((ApplicationStatus)o).zzWF));
    }
    
    public int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzWF);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public String zzms() {
        return this.zzWF;
    }
}
