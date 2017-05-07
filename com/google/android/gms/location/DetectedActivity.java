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
    public static final int RUNNING = 8;
    public static final int STILL = 3;
    public static final int TILTING = 5;
    public static final int UNKNOWN = 4;
    public static final int WALKING = 7;
    int NS;
    int NT;
    private final int xH;
    
    static {
        CREATOR = new DetectedActivityCreator();
    }
    
    public DetectedActivity(final int ns, final int nt) {
        this.xH = 1;
        this.NS = ns;
        this.NT = nt;
    }
    
    public DetectedActivity(final int xh, final int ns, final int nt) {
        this.xH = xh;
        this.NS = ns;
        this.NT = nt;
    }
    
    private int bv(final int n) {
        int n2 = n;
        if (n > 8) {
            n2 = 4;
        }
        return n2;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getConfidence() {
        return this.NT;
    }
    
    public int getType() {
        return this.bv(this.NS);
    }
    
    public int getVersionCode() {
        return this.xH;
    }
    
    @Override
    public String toString() {
        return "DetectedActivity [type=" + this.getType() + ", confidence=" + this.NT + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        DetectedActivityCreator.a(this, parcel, n);
    }
}
