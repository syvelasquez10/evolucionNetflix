// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import java.util.Iterator;
import java.util.Collections;
import android.location.Location;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LocationResult implements SafeParcelable
{
    public static final Parcelable$Creator<LocationResult> CREATOR;
    static final List<Location> zzaEJ;
    private final int mVersionCode;
    private final List<Location> zzaEK;
    
    static {
        zzaEJ = Collections.emptyList();
        CREATOR = (Parcelable$Creator)new zze();
    }
    
    LocationResult(final int mVersionCode, final List<Location> zzaEK) {
        this.mVersionCode = mVersionCode;
        this.zzaEK = zzaEK;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof LocationResult)) {
            return false;
        }
        final LocationResult locationResult = (LocationResult)o;
        if (locationResult.zzaEK.size() != this.zzaEK.size()) {
            return false;
        }
        final Iterator<Location> iterator = locationResult.zzaEK.iterator();
        final Iterator<Location> iterator2 = this.zzaEK.iterator();
        while (iterator.hasNext()) {
            if (iterator2.next().getTime() != iterator.next().getTime()) {
                return false;
            }
        }
        return true;
    }
    
    public List<Location> getLocations() {
        return this.zzaEK;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        final Iterator<Location> iterator = this.zzaEK.iterator();
        int n = 17;
        while (iterator.hasNext()) {
            final long time = iterator.next().getTime();
            n = (int)(time ^ time >>> 32) + n * 31;
        }
        return n;
    }
    
    @Override
    public String toString() {
        return "LocationResult[locations: " + this.zzaEK + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zze.zza(this, parcel, n);
    }
}
