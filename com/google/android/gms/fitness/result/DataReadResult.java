// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.result;

import android.os.Parcel;
import com.google.android.gms.common.internal.m$a;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.fitness.data.DataPoint;
import java.util.Collections;
import com.google.android.gms.fitness.data.DataSource$Builder;
import com.google.android.gms.fitness.request.DataReadRequest;
import java.util.Iterator;
import java.util.ArrayList;
import com.google.android.gms.fitness.data.RawBucket;
import com.google.android.gms.fitness.data.RawDataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import java.util.List;
import com.google.android.gms.common.api.Status;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.api.Result;

public class DataReadResult implements Result, SafeParcelable
{
    public static final Parcelable$Creator<DataReadResult> CREATOR;
    private final int BR;
    private final Status CM;
    private final List<DataSource> SH;
    private final List<DataSet> Sw;
    private final List<Bucket> UK;
    private int UL;
    private final List<DataType> UM;
    
    static {
        CREATOR = (Parcelable$Creator)new b();
    }
    
    DataReadResult(final int br, final List<RawDataSet> list, final Status cm, final List<RawBucket> list2, final int ul, final List<DataSource> sh, final List<DataType> um) {
        this.BR = br;
        this.CM = cm;
        this.UL = ul;
        this.SH = sh;
        this.UM = um;
        this.Sw = new ArrayList<DataSet>(list.size());
        final Iterator<RawDataSet> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.Sw.add(new DataSet(iterator.next(), sh, um));
        }
        this.UK = new ArrayList<Bucket>(list2.size());
        final Iterator<RawBucket> iterator2 = list2.iterator();
        while (iterator2.hasNext()) {
            this.UK.add(new Bucket(iterator2.next(), sh, um));
        }
    }
    
    public DataReadResult(final List<DataSet> sw, final List<Bucket> uk, final Status cm) {
        this.BR = 5;
        this.Sw = sw;
        this.CM = cm;
        this.UK = uk;
        this.UL = 1;
        this.SH = new ArrayList<DataSource>();
        this.UM = new ArrayList<DataType>();
    }
    
    public static DataReadResult a(final Status status, final DataReadRequest dataReadRequest) {
        final ArrayList<DataSet> list = new ArrayList<DataSet>();
        final Iterator<DataSource> iterator = dataReadRequest.getDataSources().iterator();
        while (iterator.hasNext()) {
            list.add(DataSet.create(iterator.next()));
        }
        final Iterator<DataType> iterator2 = dataReadRequest.getDataTypes().iterator();
        while (iterator2.hasNext()) {
            list.add(DataSet.create(new DataSource$Builder().setDataType(iterator2.next()).setType(1).setName("Default").build()));
        }
        return new DataReadResult(list, Collections.emptyList(), status);
    }
    
    private void a(final Bucket bucket, final List<Bucket> list) {
        for (final Bucket bucket2 : list) {
            if (bucket2.b(bucket)) {
                final Iterator<DataSet> iterator2 = bucket.getDataSets().iterator();
                while (iterator2.hasNext()) {
                    this.a(iterator2.next(), bucket2.getDataSets());
                }
                return;
            }
        }
        this.UK.add(bucket);
    }
    
    private void a(final DataSet set, final List<DataSet> list) {
        for (final DataSet set2 : list) {
            if (set2.getDataSource().equals(set.getDataSource())) {
                set2.a(set.getDataPoints());
                return;
            }
        }
        list.add(set);
    }
    
    private boolean c(final DataReadResult dataReadResult) {
        return this.CM.equals(dataReadResult.CM) && m.equal(this.Sw, dataReadResult.Sw) && m.equal(this.UK, dataReadResult.UK);
    }
    
    public void b(final DataReadResult dataReadResult) {
        final Iterator<DataSet> iterator = dataReadResult.getDataSets().iterator();
        while (iterator.hasNext()) {
            this.a(iterator.next(), this.Sw);
        }
        final Iterator<Bucket> iterator2 = dataReadResult.getBuckets().iterator();
        while (iterator2.hasNext()) {
            this.a(iterator2.next(), this.UK);
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof DataReadResult && this.c((DataReadResult)o));
    }
    
    public List<Bucket> getBuckets() {
        return this.UK;
    }
    
    public DataSet getDataSet(final DataSource dataSource) {
        for (final DataSet set : this.Sw) {
            if (dataSource.equals(set.getDataSource())) {
                return set;
            }
        }
        throw new IllegalArgumentException(String.format("Attempting to read data for %s, which was not requested", dataSource.getStreamIdentifier()));
    }
    
    public DataSet getDataSet(final DataType dataType) {
        for (final DataSet set : this.Sw) {
            if (dataType.equals(set.getDataType())) {
                return set;
            }
        }
        throw new IllegalArgumentException(String.format("Attempting to read data for %s, which was not requested", dataType.getName()));
    }
    
    public List<DataSet> getDataSets() {
        return this.Sw;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.CM, this.Sw, this.UK);
    }
    
    List<DataSource> iG() {
        return this.SH;
    }
    
    public int jF() {
        return this.UL;
    }
    
    List<RawBucket> jG() {
        final ArrayList<RawBucket> list = new ArrayList<RawBucket>(this.UK.size());
        final Iterator<Bucket> iterator = this.UK.iterator();
        while (iterator.hasNext()) {
            list.add(new RawBucket(iterator.next(), this.SH, this.UM));
        }
        return list;
    }
    
    List<RawDataSet> jH() {
        final ArrayList<RawDataSet> list = new ArrayList<RawDataSet>(this.Sw.size());
        final Iterator<DataSet> iterator = this.Sw.iterator();
        while (iterator.hasNext()) {
            list.add(new RawDataSet(iterator.next(), this.SH, this.UM));
        }
        return list;
    }
    
    List<DataType> jI() {
        return this.UM;
    }
    
    @Override
    public String toString() {
        final m$a a = m.h(this).a("status", this.CM);
        Object o;
        if (this.Sw.size() > 5) {
            o = this.Sw.size() + " data sets";
        }
        else {
            o = this.Sw;
        }
        final m$a a2 = a.a("dataSets", o);
        Object o2;
        if (this.UK.size() > 5) {
            o2 = this.UK.size() + " buckets";
        }
        else {
            o2 = this.UK;
        }
        return a2.a("buckets", o2).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        b.a(this, parcel, n);
    }
}
