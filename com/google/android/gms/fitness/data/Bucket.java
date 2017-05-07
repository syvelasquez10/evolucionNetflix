// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Bucket implements SafeParcelable
{
    public static final Parcelable$Creator<Bucket> CREATOR;
    public static final int TYPE_ACTIVITY_SEGMENT = 4;
    public static final int TYPE_ACTIVITY_TYPE = 3;
    public static final int TYPE_SESSION = 2;
    public static final int TYPE_TIME = 1;
    private final int BR;
    private final long KL;
    private final long Si;
    private final Session Sk;
    private final int Sv;
    private final List<DataSet> Sw;
    private final int Sx;
    private boolean Sy;
    
    static {
        CREATOR = (Parcelable$Creator)new d();
    }
    
    Bucket(final int br, final long kl, final long si, final Session sk, final int sv, final List<DataSet> sw, final int sx, final boolean sy) {
        this.Sy = false;
        this.BR = br;
        this.KL = kl;
        this.Si = si;
        this.Sk = sk;
        this.Sv = sv;
        this.Sw = sw;
        this.Sx = sx;
        this.Sy = sy;
    }
    
    public Bucket(final RawBucket rawBucket, final List<DataSource> list, final List<DataType> list2) {
        this(2, rawBucket.KL, rawBucket.Si, rawBucket.Sk, rawBucket.Sv, a(rawBucket.Sw, list, list2), rawBucket.Sx, rawBucket.Sy);
    }
    
    private static List<DataSet> a(final List<RawDataSet> list, final List<DataSource> list2, final List<DataType> list3) {
        final ArrayList<DataSet> list4 = new ArrayList<DataSet>(list.size());
        final Iterator<RawDataSet> iterator = list.iterator();
        while (iterator.hasNext()) {
            list4.add(new DataSet(iterator.next(), list2, list3));
        }
        return list4;
    }
    
    private boolean a(final Bucket bucket) {
        return this.KL == bucket.KL && this.Si == bucket.Si && this.Sv == bucket.Sv && m.equal(this.Sw, bucket.Sw) && this.Sx == bucket.Sx && this.Sy == bucket.Sy;
    }
    
    public static String cz(final int n) {
        switch (n) {
            default: {
                return "bug";
            }
            case 1: {
                return "time";
            }
            case 3: {
                return "type";
            }
            case 4: {
                return "segment";
            }
            case 2: {
                return "session";
            }
            case 0: {
                return "unknown";
            }
        }
    }
    
    public boolean b(final Bucket bucket) {
        return this.KL == bucket.KL && this.Si == bucket.Si && this.Sv == bucket.Sv && this.Sx == bucket.Sx;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof Bucket && this.a((Bucket)o));
    }
    
    public int getActivity() {
        return this.Sv;
    }
    
    public int getBucketType() {
        return this.Sx;
    }
    
    public DataSet getDataSet(final DataType dataType) {
        for (final DataSet set : this.Sw) {
            if (set.getDataType().equals(dataType)) {
                return set;
            }
        }
        return null;
    }
    
    public List<DataSet> getDataSets() {
        return this.Sw;
    }
    
    public long getEndTimeMillis() {
        return this.Si;
    }
    
    public Session getSession() {
        return this.Sk;
    }
    
    public long getStartTimeMillis() {
        return this.KL;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.KL, this.Si, this.Sv, this.Sx);
    }
    
    public boolean iB() {
        if (this.Sy) {
            return true;
        }
        final Iterator<DataSet> iterator = this.Sw.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().iB()) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("startTime", this.KL).a("endTime", this.Si).a("activity", this.Sv).a("dataSets", this.Sw).a("bucketType", cz(this.Sx)).a("serverHasMoreData", this.Sy).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        d.a(this, parcel, n);
    }
}
