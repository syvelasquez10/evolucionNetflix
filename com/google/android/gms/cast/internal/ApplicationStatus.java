// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzt;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ApplicationStatus implements SafeParcelable
{
    public static final Parcelable$Creator<ApplicationStatus> CREATOR;
    private final int zzCY;
    private String zzUc;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    public ApplicationStatus() {
        this(1, null);
    }
    
    ApplicationStatus(final int zzCY, final String zzUc) {
        this.zzCY = zzCY;
        this.zzUc = zzUc;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof ApplicationStatus && zzf.zza(this.zzUc, ((ApplicationStatus)o).zzUc));
    }
    
    public int getVersionCode() {
        return this.zzCY;
    }
    
    @Override
    public int hashCode() {
        return zzt.hashCode(this.zzUc);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public String zzlG() {
        return this.zzUc;
    }
}
