// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzw;
import java.util.Collections;
import com.google.android.gms.location.LocationRequest;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class LocationRequestInternal implements SafeParcelable
{
    public static final zzm CREATOR;
    static final List<ClientIdentity> zzaFD;
    String mTag;
    private final int mVersionCode;
    boolean zzaFE;
    boolean zzaFF;
    boolean zzaFG;
    List<ClientIdentity> zzaFH;
    boolean zzaFI;
    LocationRequest zzasN;
    
    static {
        zzaFD = Collections.emptyList();
        CREATOR = new zzm();
    }
    
    LocationRequestInternal(final int mVersionCode, final LocationRequest zzasN, final boolean zzaFE, final boolean zzaFF, final boolean zzaFG, final List<ClientIdentity> zzaFH, final String mTag, final boolean zzaFI) {
        this.mVersionCode = mVersionCode;
        this.zzasN = zzasN;
        this.zzaFE = zzaFE;
        this.zzaFF = zzaFF;
        this.zzaFG = zzaFG;
        this.zzaFH = zzaFH;
        this.mTag = mTag;
        this.zzaFI = zzaFI;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof LocationRequestInternal) {
            final LocationRequestInternal locationRequestInternal = (LocationRequestInternal)o;
            if (zzw.equal(this.zzasN, locationRequestInternal.zzasN) && this.zzaFE == locationRequestInternal.zzaFE && this.zzaFF == locationRequestInternal.zzaFF && this.zzaFG == locationRequestInternal.zzaFG && this.zzaFI == locationRequestInternal.zzaFI && zzw.equal(this.zzaFH, locationRequestInternal.zzaFH)) {
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
        return this.zzasN.hashCode();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.zzasN.toString());
        if (this.mTag != null) {
            sb.append(" tag=").append(this.mTag);
        }
        sb.append(" nlpDebug=").append(this.zzaFE);
        sb.append(" trigger=").append(this.zzaFG);
        sb.append(" restorePIListeners=").append(this.zzaFF);
        sb.append(" hideAppOps=").append(this.zzaFI);
        sb.append(" clients=").append(this.zzaFH);
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        zzm.zza(this, parcel, n);
    }
}
