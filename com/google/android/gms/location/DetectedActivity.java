// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzw;
import java.util.Comparator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DetectedActivity implements SafeParcelable
{
    public static final DetectedActivityCreator CREATOR;
    public static final Comparator<DetectedActivity> zzaEf;
    private final int mVersionCode;
    int zzaEg;
    int zzaEh;
    
    static {
        zzaEf = new DetectedActivity$1();
        CREATOR = new DetectedActivityCreator();
    }
    
    public DetectedActivity(final int mVersionCode, final int zzaEg, final int zzaEh) {
        this.mVersionCode = mVersionCode;
        this.zzaEg = zzaEg;
        this.zzaEh = zzaEh;
    }
    
    private int zzgK(final int n) {
        int n2 = n;
        if (n > 15) {
            n2 = 4;
        }
        return n2;
    }
    
    public static String zzgL(final int n) {
        switch (n) {
            default: {
                return Integer.toString(n);
            }
            case 0: {
                return "IN_VEHICLE";
            }
            case 1: {
                return "ON_BICYCLE";
            }
            case 2: {
                return "ON_FOOT";
            }
            case 3: {
                return "STILL";
            }
            case 4: {
                return "UNKNOWN";
            }
            case 5: {
                return "TILTING";
            }
            case 7: {
                return "WALKING";
            }
            case 8: {
                return "RUNNING";
            }
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final DetectedActivity detectedActivity = (DetectedActivity)o;
            if (this.zzaEg != detectedActivity.zzaEg || this.zzaEh != detectedActivity.zzaEh) {
                return false;
            }
        }
        return true;
    }
    
    public int getConfidence() {
        return this.zzaEh;
    }
    
    public int getType() {
        return this.zzgK(this.zzaEg);
    }
    
    public int getVersionCode() {
        return this.mVersionCode;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzaEg, this.zzaEh);
    }
    
    @Override
    public String toString() {
        return "DetectedActivity [type=" + zzgL(this.getType()) + ", confidence=" + this.zzaEh + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        DetectedActivityCreator.zza(this, parcel, n);
    }
}
