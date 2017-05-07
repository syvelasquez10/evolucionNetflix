// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class RawBucket implements SafeParcelable
{
    public static final Parcelable$Creator<RawBucket> CREATOR;
    final int BR;
    final long KL;
    final long Si;
    final Session Sk;
    final int Sv;
    final List<RawDataSet> Sw;
    final int Sx;
    final boolean Sy;
    
    static {
        CREATOR = (Parcelable$Creator)new m();
    }
    
    RawBucket(final int br, final long kl, final long si, final Session sk, final int sv, final List<RawDataSet> sw, final int sx, final boolean sy) {
        this.BR = br;
        this.KL = kl;
        this.Si = si;
        this.Sk = sk;
        this.Sv = sv;
        this.Sw = sw;
        this.Sx = sx;
        this.Sy = sy;
    }
    
    public RawBucket(final Bucket bucket, final List<DataSource> list, final List<DataType> list2) {
        this.BR = 2;
        this.KL = bucket.getStartTimeMillis();
        this.Si = bucket.getEndTimeMillis();
        this.Sk = bucket.getSession();
        this.Sv = bucket.getActivity();
        this.Sx = bucket.getBucketType();
        this.Sy = bucket.iB();
        final List<DataSet> dataSets = bucket.getDataSets();
        this.Sw = new ArrayList<RawDataSet>(dataSets.size());
        final Iterator<DataSet> iterator = dataSets.iterator();
        while (iterator.hasNext()) {
            this.Sw.add(new RawDataSet(iterator.next(), list, list2));
        }
    }
    
    private boolean a(final RawBucket rawBucket) {
        return this.KL == rawBucket.KL && this.Si == rawBucket.Si && this.Sv == rawBucket.Sv && com.google.android.gms.common.internal.m.equal(this.Sw, rawBucket.Sw) && this.Sx == rawBucket.Sx && this.Sy == rawBucket.Sy;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof RawBucket && this.a((RawBucket)o));
    }
    
    @Override
    public int hashCode() {
        return com.google.android.gms.common.internal.m.hashCode(this.KL, this.Si, this.Sx);
    }
    
    @Override
    public String toString() {
        return com.google.android.gms.common.internal.m.h(this).a("startTime", this.KL).a("endTime", this.Si).a("activity", this.Sv).a("dataSets", this.Sw).a("bucketType", this.Sx).a("serverHasMoreData", this.Sy).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        m.a(this, parcel, n);
    }
}
