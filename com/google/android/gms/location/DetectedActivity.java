// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DetectedActivity implements SafeParcelable
{
    public static final DetectedActivityCreator CREATOR;
    public static final int IN_VEHICLE = 0;
    public static final int ON_BICYCLE = 1;
    public static final int ON_FOOT = 2;
    public static final int STILL = 3;
    public static final int TILTING = 5;
    public static final int UNKNOWN = 4;
    private final int kg;
    int xp;
    int xq;
    
    static {
        CREATOR = new DetectedActivityCreator();
    }
    
    public DetectedActivity(final int xp, final int xq) {
        this.kg = 1;
        this.xp = xp;
        this.xq = xq;
    }
    
    public DetectedActivity(final int kg, final int xp, final int xq) {
        this.kg = kg;
        this.xp = xp;
        this.xq = xq;
    }
    
    private int aM(final int n) {
        int n2 = n;
        if (n > 6) {
            n2 = 4;
        }
        return n2;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getConfidence() {
        return this.xq;
    }
    
    public int getType() {
        return this.aM(this.xp);
    }
    
    public int getVersionCode() {
        return this.kg;
    }
    
    @Override
    public String toString() {
        return "DetectedActivity [type=" + this.getType() + ", confidence=" + this.xq + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        DetectedActivityCreator.a(this, parcel, n);
    }
}
