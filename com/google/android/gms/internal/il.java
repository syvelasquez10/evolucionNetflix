// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.cast.ApplicationMetadata;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class il implements SafeParcelable
{
    public static final Parcelable$Creator<il> CREATOR;
    private final int BR;
    private double FA;
    private boolean FB;
    private int GB;
    private int GC;
    private ApplicationMetadata GN;
    
    static {
        CREATOR = (Parcelable$Creator)new im();
    }
    
    public il() {
        this(3, Double.NaN, false, -1, null, -1);
    }
    
    il(final int br, final double fa, final boolean fb, final int gb, final ApplicationMetadata gn, final int gc) {
        this.BR = br;
        this.FA = fa;
        this.FB = fb;
        this.GB = gb;
        this.GN = gn;
        this.GC = gc;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof il)) {
                return false;
            }
            final il il = (il)o;
            if (this.FA != il.FA || this.FB != il.FB || this.GB != il.GB || !ik.a(this.GN, il.GN) || this.GC != il.GC) {
                return false;
            }
        }
        return true;
    }
    
    public double fF() {
        return this.FA;
    }
    
    public boolean fN() {
        return this.FB;
    }
    
    public int fO() {
        return this.GB;
    }
    
    public int fP() {
        return this.GC;
    }
    
    public ApplicationMetadata getApplicationMetadata() {
        return this.GN;
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.FA, this.FB, this.GB, this.GN, this.GC);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        im.a(this, parcel, n);
    }
}
