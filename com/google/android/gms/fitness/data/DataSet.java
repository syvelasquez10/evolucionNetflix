// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import com.google.android.gms.common.internal.n;
import java.util.Iterator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class DataSet implements SafeParcelable
{
    public static final Parcelable$Creator<DataSet> CREATOR;
    private final int BR;
    private final DataType SF;
    private final List<DataPoint> SG;
    private final List<DataSource> SH;
    private final DataSource Sh;
    private boolean Sy;
    
    static {
        CREATOR = (Parcelable$Creator)new f();
    }
    
    DataSet(final int br, final DataSource sh, final DataType sf, final List<RawDataPoint> list, List<DataSource> singletonList, final boolean sy) {
        this.Sy = false;
        this.BR = br;
        this.Sh = sh;
        this.SF = sf;
        this.Sy = sy;
        this.SG = new ArrayList<DataPoint>(list.size());
        if (br < 2) {
            singletonList = Collections.singletonList(sh);
        }
        this.SH = singletonList;
        final Iterator<RawDataPoint> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.SG.add(new DataPoint(this.SH, iterator.next()));
        }
    }
    
    private DataSet(final DataSource dataSource, final DataType dataType) {
        this.Sy = false;
        this.BR = 3;
        this.Sh = n.i(dataSource);
        this.SF = n.i(dataType);
        this.SG = new ArrayList<DataPoint>();
        (this.SH = new ArrayList<DataSource>()).add(this.Sh);
    }
    
    public DataSet(final RawDataSet set, final List<DataSource> list, final List<DataType> list2) {
        this(3, b(list, set.Tb), b(list2, set.Td), set.Te, list, set.Sy);
    }
    
    private boolean a(final DataSet set) {
        return m.equal(this.SF, set.SF) && m.equal(this.Sh, set.Sh) && m.equal(this.SG, set.SG) && this.Sy == set.Sy;
    }
    
    private static <T> T b(final List<T> list, final int n) {
        if (n >= 0 && n < list.size()) {
            return list.get(n);
        }
        return null;
    }
    
    public static DataSet create(final DataSource dataSource) {
        return new DataSet(dataSource, dataSource.getDataType());
    }
    
    public void a(final Iterable<DataPoint> iterable) {
        final Iterator<DataPoint> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            this.b(iterator.next());
        }
    }
    
    public void add(final DataPoint dataPoint) {
        final DataSource dataSource = dataPoint.getDataSource();
        n.b(dataSource.getStreamIdentifier().equals(this.Sh.getStreamIdentifier()), "Conflicting data sources found %s vs %s", dataSource, this.Sh);
        n.b(dataPoint.getDataType().getName().equals(this.SF.getName()), "Conflicting data types found %s vs %s", dataPoint.getDataType(), this.SF);
        n.b(dataPoint.getTimestampNanos() > 0L, "Data point does not have the timestamp set: %s", dataPoint);
        n.b(dataPoint.getStartTimeNanos() <= dataPoint.getEndTimeNanos(), "Data point with start time greater than end time found: %s", dataPoint);
        this.b(dataPoint);
    }
    
    public void addAll(final Iterable<DataPoint> iterable) {
        final Iterator<DataPoint> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            this.add(iterator.next());
        }
    }
    
    public void b(final DataPoint dataPoint) {
        this.SG.add(dataPoint);
        final DataSource originalDataSource = dataPoint.getOriginalDataSource();
        if (originalDataSource != null && !this.SH.contains(originalDataSource)) {
            this.SH.add(originalDataSource);
        }
    }
    
    public DataPoint createDataPoint() {
        return DataPoint.create(this.Sh);
    }
    
    public int describeContents() {
        return 0;
    }
    
    List<RawDataPoint> e(final List<DataSource> list) {
        final ArrayList<RawDataPoint> list2 = new ArrayList<RawDataPoint>(this.SG.size());
        final Iterator<DataPoint> iterator = this.SG.iterator();
        while (iterator.hasNext()) {
            list2.add(new RawDataPoint(iterator.next(), list));
        }
        return list2;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof DataSet && this.a((DataSet)o));
    }
    
    public List<DataPoint> getDataPoints() {
        return Collections.unmodifiableList((List<? extends DataPoint>)this.SG);
    }
    
    public DataSource getDataSource() {
        return this.Sh;
    }
    
    public DataType getDataType() {
        return this.SF;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.SF, this.Sh);
    }
    
    public boolean iB() {
        return this.Sy;
    }
    
    List<RawDataPoint> iF() {
        return this.e(this.SH);
    }
    
    List<DataSource> iG() {
        return this.SH;
    }
    
    @Override
    public String toString() {
        Object o = this.iF();
        final String debugString = this.Sh.toDebugString();
        if (this.SG.size() >= 10) {
            o = String.format("%d data points, first 5: %s", this.SG.size(), ((List)o).subList(0, 5));
        }
        return String.format("DataSet{%s %s}", debugString, o);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        f.a(this, parcel, n);
    }
}
