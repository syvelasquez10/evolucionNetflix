// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class RawDataSet implements SafeParcelable
{
    public static final Parcelable$Creator<RawDataSet> CREATOR;
    final int BR;
    final boolean Sy;
    final int Tb;
    final int Td;
    final List<RawDataPoint> Te;
    
    static {
        CREATOR = (Parcelable$Creator)new o();
    }
    
    RawDataSet(final int br, final int tb, final int td, final List<RawDataPoint> te, final boolean sy) {
        this.BR = br;
        this.Tb = tb;
        this.Td = td;
        this.Te = te;
        this.Sy = sy;
    }
    
    public RawDataSet(final DataSet set, final List<DataSource> list, final List<DataType> list2) {
        this.BR = 2;
        this.Te = set.e(list);
        this.Sy = set.iB();
        this.Tb = t.a(set.getDataSource(), list);
        this.Td = t.a(set.getDataType(), list2);
    }
    
    private boolean a(final RawDataSet set) {
        return this.Tb == set.Tb && this.Td == set.Td && this.Sy == set.Sy && m.equal(this.Te, set.Te);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof RawDataSet && this.a((RawDataSet)o));
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Tb, this.Td);
    }
    
    @Override
    public String toString() {
        return String.format("RawDataSet{%s@[%s, %s]}", this.Tb, this.Td, this.Te);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        o.a(this, parcel, n);
    }
}
