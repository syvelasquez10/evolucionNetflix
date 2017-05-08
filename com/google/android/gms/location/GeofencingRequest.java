// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.location.internal.ParcelableGeofence;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class GeofencingRequest implements SafeParcelable
{
    public static final Parcelable$Creator<GeofencingRequest> CREATOR;
    private final int mVersionCode;
    private final List<ParcelableGeofence> zzaEt;
    private final int zzaEu;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    GeofencingRequest(final int mVersionCode, final List<ParcelableGeofence> zzaEt, final int zzaEu) {
        this.mVersionCode = mVersionCode;
        this.zzaEt = zzaEt;
        this.zzaEu = zzaEu;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getInitialTrigger() {
        return this.zzaEu;
    }
    
    public int getVersionCode() {
        return this.mVersionCode;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zza.zza(this, parcel, n);
    }
    
    public List<ParcelableGeofence> zzwv() {
        return this.zzaEt;
    }
}
