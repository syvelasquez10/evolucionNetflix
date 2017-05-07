// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import android.os.Parcel;
import java.util.Comparator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DetectedActivity implements SafeParcelable
{
    public static final DetectedActivityCreator CREATOR;
    public static final int IN_VEHICLE = 0;
    public static final int ON_BICYCLE = 1;
    public static final int ON_FOOT = 2;
    public static final int RUNNING = 8;
    public static final int STILL = 3;
    public static final int TILTING = 5;
    public static final int UNKNOWN = 4;
    public static final int WALKING = 7;
    public static final Comparator<DetectedActivity> adT;
    private final int BR;
    int adU;
    int adV;
    
    static {
        adT = new DetectedActivity$1();
        CREATOR = new DetectedActivityCreator();
    }
    
    public DetectedActivity(final int adU, final int adV) {
        this.BR = 1;
        this.adU = adU;
        this.adV = adV;
    }
    
    public DetectedActivity(final int br, final int adU, final int adV) {
        this.BR = br;
        this.adU = adU;
        this.adV = adV;
    }
    
    private int cw(final int n) {
        int n2 = n;
        if (n > 9) {
            n2 = 4;
        }
        return n2;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getConfidence() {
        return this.adV;
    }
    
    public int getType() {
        return this.cw(this.adU);
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public String toString() {
        return "DetectedActivity [type=" + this.getType() + ", confidence=" + this.adV + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        DetectedActivityCreator.a(this, parcel, n);
    }
}
