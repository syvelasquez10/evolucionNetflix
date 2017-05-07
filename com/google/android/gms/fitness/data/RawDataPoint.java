// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import java.util.Arrays;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class RawDataPoint implements SafeParcelable
{
    public static final Parcelable$Creator<RawDataPoint> CREATOR;
    final int BR;
    final long SA;
    final Value[] SB;
    final long SD;
    final long SE;
    final long Sz;
    final int Tb;
    final int Tc;
    
    static {
        CREATOR = (Parcelable$Creator)new n();
    }
    
    RawDataPoint(final int br, final long sz, final long sa, final Value[] sb, final int tb, final int tc, final long sd, final long se) {
        this.BR = br;
        this.Sz = sz;
        this.SA = sa;
        this.Tb = tb;
        this.Tc = tc;
        this.SD = sd;
        this.SE = se;
        this.SB = sb;
    }
    
    RawDataPoint(final DataPoint dataPoint, final List<DataSource> list) {
        this.BR = 4;
        this.Sz = dataPoint.getTimestampNanos();
        this.SA = dataPoint.getStartTimeNanos();
        this.SB = dataPoint.iC();
        this.Tb = t.a(dataPoint.getDataSource(), list);
        this.Tc = t.a(dataPoint.getOriginalDataSource(), list);
        this.SD = dataPoint.iD();
        this.SE = dataPoint.iE();
    }
    
    private boolean a(final RawDataPoint rawDataPoint) {
        return this.Sz == rawDataPoint.Sz && this.SA == rawDataPoint.SA && Arrays.equals(this.SB, rawDataPoint.SB) && this.Tb == rawDataPoint.Tb && this.Tc == rawDataPoint.Tc && this.SD == rawDataPoint.SD;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof RawDataPoint && this.a((RawDataPoint)o));
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Sz, this.SA);
    }
    
    @Override
    public String toString() {
        return String.format("RawDataPoint{%s@[%s, %s](%d,%d)}", Arrays.toString(this.SB), this.SA, this.Sz, this.Tb, this.Tc);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        n.a(this, parcel, n);
    }
}
