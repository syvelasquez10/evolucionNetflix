// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import java.util.Collections;
import android.os.Parcel;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LocationSettingsRequest implements SafeParcelable
{
    public static final Parcelable$Creator<LocationSettingsRequest> CREATOR;
    private final int mVersionCode;
    private final boolean zzaEL;
    private final boolean zzaEM;
    private final boolean zzaEN;
    private final List<LocationRequest> zzasK;
    
    static {
        CREATOR = (Parcelable$Creator)new zzf();
    }
    
    LocationSettingsRequest(final int mVersionCode, final List<LocationRequest> zzasK, final boolean zzaEL, final boolean zzaEM, final boolean zzaEN) {
        this.mVersionCode = mVersionCode;
        this.zzasK = zzasK;
        this.zzaEL = zzaEL;
        this.zzaEM = zzaEM;
        this.zzaEN = zzaEN;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getVersionCode() {
        return this.mVersionCode;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzf.zza(this, parcel, n);
    }
    
    public List<LocationRequest> zztd() {
        return Collections.unmodifiableList((List<? extends LocationRequest>)this.zzasK);
    }
    
    public boolean zzwx() {
        return this.zzaEL;
    }
    
    public boolean zzwy() {
        return this.zzaEM;
    }
    
    public boolean zzwz() {
        return this.zzaEN;
    }
}
