// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import java.util.Collections;
import com.google.android.gms.location.LocationRequest;
import java.util.List;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class lz implements SafeParcelable
{
    public static final ma CREATOR;
    static final List<lr> aeW;
    private final int BR;
    LocationRequest Ux;
    boolean aeX;
    boolean aeY;
    boolean aeZ;
    List<lr> afa;
    final String mTag;
    
    static {
        aeW = Collections.emptyList();
        CREATOR = new ma();
    }
    
    lz(final int br, final LocationRequest ux, final boolean aeX, final boolean aeY, final boolean aeZ, final List<lr> afa, final String mTag) {
        this.BR = br;
        this.Ux = ux;
        this.aeX = aeX;
        this.aeY = aeY;
        this.aeZ = aeZ;
        this.afa = afa;
        this.mTag = mTag;
    }
    
    private lz(final String s, final LocationRequest locationRequest) {
        this(1, locationRequest, false, true, true, lz.aeW, s);
    }
    
    public static lz a(final String s, final LocationRequest locationRequest) {
        return new lz(s, locationRequest);
    }
    
    public static lz b(final LocationRequest locationRequest) {
        return a(null, locationRequest);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o instanceof lz) {
            final lz lz = (lz)o;
            if (m.equal(this.Ux, lz.Ux) && this.aeX == lz.aeX && this.aeY == lz.aeY && this.aeZ == lz.aeZ && m.equal(this.afa, lz.afa)) {
                return true;
            }
        }
        return false;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return this.Ux.hashCode();
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.Ux.toString());
        sb.append(" requestNlpDebugInfo=");
        sb.append(this.aeX);
        sb.append(" restorePendingIntentListeners=");
        sb.append(this.aeY);
        sb.append(" triggerUpdate=");
        sb.append(this.aeZ);
        sb.append(" clients=");
        sb.append(this.afa);
        if (this.mTag != null) {
            sb.append(" tag=");
            sb.append(this.mTag);
        }
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        ma.a(this, parcel, n);
    }
}
