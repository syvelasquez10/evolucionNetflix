// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class LocationRequest implements SafeParcelable
{
    public static final LocationRequestCreator CREATOR;
    int mPriority;
    private final int mVersionCode;
    long zzaEE;
    long zzaEF;
    int zzaEG;
    float zzaEH;
    long zzaEI;
    long zzaEj;
    boolean zzasP;
    
    static {
        CREATOR = new LocationRequestCreator();
    }
    
    public LocationRequest() {
        this.mVersionCode = 1;
        this.mPriority = 102;
        this.zzaEE = 3600000L;
        this.zzaEF = 600000L;
        this.zzasP = false;
        this.zzaEj = Long.MAX_VALUE;
        this.zzaEG = Integer.MAX_VALUE;
        this.zzaEH = 0.0f;
        this.zzaEI = 0L;
    }
    
    LocationRequest(final int mVersionCode, final int mPriority, final long zzaEE, final long zzaEF, final boolean zzasP, final long zzaEj, final int zzaEG, final float zzaEH, final long zzaEI) {
        this.mVersionCode = mVersionCode;
        this.mPriority = mPriority;
        this.zzaEE = zzaEE;
        this.zzaEF = zzaEF;
        this.zzasP = zzasP;
        this.zzaEj = zzaEj;
        this.zzaEG = zzaEG;
        this.zzaEH = zzaEH;
        this.zzaEI = zzaEI;
    }
    
    public static String zzgQ(final int n) {
        switch (n) {
            default: {
                return "???";
            }
            case 100: {
                return "PRIORITY_HIGH_ACCURACY";
            }
            case 102: {
                return "PRIORITY_BALANCED_POWER_ACCURACY";
            }
            case 104: {
                return "PRIORITY_LOW_POWER";
            }
            case 105: {
                return "PRIORITY_NO_POWER";
            }
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof LocationRequest)) {
                return false;
            }
            final LocationRequest locationRequest = (LocationRequest)o;
            if (this.mPriority != locationRequest.mPriority || this.zzaEE != locationRequest.zzaEE || this.zzaEF != locationRequest.zzaEF || this.zzasP != locationRequest.zzasP || this.zzaEj != locationRequest.zzaEj || this.zzaEG != locationRequest.zzaEG || this.zzaEH != locationRequest.zzaEH) {
                return false;
            }
        }
        return true;
    }
    
    int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.mPriority, this.zzaEE, this.zzaEF, this.zzasP, this.zzaEj, this.zzaEG, this.zzaEH);
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Request[").append(zzgQ(this.mPriority));
        if (this.mPriority != 105) {
            sb.append(" requested=");
            sb.append(this.zzaEE).append("ms");
        }
        sb.append(" fastest=");
        sb.append(this.zzaEF).append("ms");
        if (this.zzaEI > this.zzaEE) {
            sb.append(" maxWait=");
            sb.append(this.zzaEI).append("ms");
        }
        if (this.zzaEj != Long.MAX_VALUE) {
            final long zzaEj = this.zzaEj;
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            sb.append(" expireIn=");
            sb.append(zzaEj - elapsedRealtime).append("ms");
        }
        if (this.zzaEG != Integer.MAX_VALUE) {
            sb.append(" num=").append(this.zzaEG);
        }
        sb.append(']');
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        LocationRequestCreator.zza(this, parcel, n);
    }
}
