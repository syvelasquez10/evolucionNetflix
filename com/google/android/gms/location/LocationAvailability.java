// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LocationAvailability implements SafeParcelable
{
    public static final LocationAvailabilityCreator CREATOR;
    private final int mVersionCode;
    int zzaEA;
    int zzaEB;
    long zzaEC;
    int zzaED;
    
    static {
        CREATOR = new LocationAvailabilityCreator();
    }
    
    LocationAvailability(final int mVersionCode, final int zzaED, final int zzaEA, final int zzaEB, final long zzaEC) {
        this.mVersionCode = mVersionCode;
        this.zzaED = zzaED;
        this.zzaEA = zzaEA;
        this.zzaEB = zzaEB;
        this.zzaEC = zzaEC;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof LocationAvailability) {
            final LocationAvailability locationAvailability = (LocationAvailability)o;
            if (this.zzaED == locationAvailability.zzaED && this.zzaEA == locationAvailability.zzaEA && this.zzaEB == locationAvailability.zzaEB && this.zzaEC == locationAvailability.zzaEC) {
                return true;
            }
        }
        return false;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzaED, this.zzaEA, this.zzaEB, this.zzaEC);
    }
    
    public boolean isLocationAvailable() {
        return this.zzaED < 1000;
    }
    
    @Override
    public String toString() {
        return "LocationAvailability[isLocationAvailable: " + this.isLocationAvailable() + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        LocationAvailabilityCreator.zza(this, parcel, n);
    }
}
