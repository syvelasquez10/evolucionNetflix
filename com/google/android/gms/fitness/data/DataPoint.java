// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import android.os.Parcel;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.internal.safeparcel.c;
import android.content.Intent;
import java.util.Arrays;
import com.google.android.gms.common.internal.m;
import java.util.Iterator;
import java.util.List;
import com.google.android.gms.common.internal.n;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class DataPoint implements SafeParcelable
{
    public static final Parcelable$Creator<DataPoint> CREATOR;
    private final int BR;
    private long SA;
    private final Value[] SB;
    private DataSource SC;
    private long SD;
    private long SE;
    private final DataSource Sh;
    private long Sz;
    
    static {
        CREATOR = (Parcelable$Creator)new e();
    }
    
    DataPoint(final int br, final DataSource sh, final long sz, final long sa, final Value[] sb, final DataSource sc, final long sd, final long se) {
        this.BR = br;
        this.Sh = sh;
        this.SC = sc;
        this.Sz = sz;
        this.SA = sa;
        this.SB = sb;
        this.SD = sd;
        this.SE = se;
    }
    
    private DataPoint(final DataSource dataSource) {
        this.BR = 4;
        this.Sh = n.b(dataSource, "Data source cannot be null");
        final List<Field> fields = dataSource.getDataType().getFields();
        this.SB = new Value[fields.size()];
        final Iterator<Field> iterator = fields.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            this.SB[n] = new Value(iterator.next().getFormat());
            ++n;
        }
    }
    
    DataPoint(final List<DataSource> list, final RawDataPoint rawDataPoint) {
        this(4, a(list, rawDataPoint.Tb), rawDataPoint.Sz, rawDataPoint.SA, rawDataPoint.SB, a(list, rawDataPoint.Tc), rawDataPoint.SD, rawDataPoint.SE);
    }
    
    private static DataSource a(final List<DataSource> list, final int n) {
        if (n >= 0 && n < list.size()) {
            return list.get(n);
        }
        return null;
    }
    
    private boolean a(final DataPoint dataPoint) {
        return m.equal(this.Sh, dataPoint.Sh) && this.Sz == dataPoint.Sz && this.SA == dataPoint.SA && Arrays.equals(this.SB, dataPoint.SB) && m.equal(this.SC, dataPoint.SC);
    }
    
    private void cB(final int n) {
        final List<Field> fields = this.getDataType().getFields();
        final int size = fields.size();
        n.b(n == size, "Attempting to insert %s values, but needed %s: %s", n, size, fields);
    }
    
    public static DataPoint create(final DataSource dataSource) {
        return new DataPoint(dataSource);
    }
    
    public static DataPoint extract(final Intent intent) {
        if (intent == null) {
            return null;
        }
        return c.a(intent, "com.google.android.gms.fitness.EXTRA_DATA_POINT", DataPoint.CREATOR);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof DataPoint && this.a((DataPoint)o));
    }
    
    public DataSource getDataSource() {
        return this.Sh;
    }
    
    public DataType getDataType() {
        return this.Sh.getDataType();
    }
    
    public long getEndTimeNanos() {
        return this.Sz;
    }
    
    public DataSource getOriginalDataSource() {
        return this.SC;
    }
    
    public long getStartTimeNanos() {
        return this.SA;
    }
    
    public long getTimestampNanos() {
        return this.Sz;
    }
    
    public Value getValue(final Field field) {
        return this.SB[this.getDataType().indexOf(field)];
    }
    
    public int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Sh, this.Sz, this.SA);
    }
    
    public Value[] iC() {
        return this.SB;
    }
    
    public long iD() {
        return this.SD;
    }
    
    public long iE() {
        return this.SE;
    }
    
    public DataPoint setFloatValues(final float... array) {
        this.cB(array.length);
        for (int i = 0; i < array.length; ++i) {
            this.SB[i].setFloat(array[i]);
        }
        return this;
    }
    
    public DataPoint setIntValues(final int... array) {
        this.cB(array.length);
        for (int i = 0; i < array.length; ++i) {
            this.SB[i].setInt(array[i]);
        }
        return this;
    }
    
    public DataPoint setTimeInterval(final long n, final long n2, final TimeUnit timeUnit) {
        return this.setTimeIntervalNanos(timeUnit.toNanos(n), timeUnit.toNanos(n2));
    }
    
    public DataPoint setTimeIntervalNanos(final long sa, final long sz) {
        this.SA = sa;
        this.Sz = sz;
        return this;
    }
    
    public DataPoint setTimestamp(final long n, final TimeUnit timeUnit) {
        return this.setTimestampNanos(timeUnit.toNanos(n));
    }
    
    public DataPoint setTimestampNanos(final long sz) {
        this.Sz = sz;
        return this;
    }
    
    @Override
    public String toString() {
        return String.format("DataPoint{%s@[%s, %s,raw=%s,insert=%s](%s %s)}", Arrays.toString(this.SB), this.SA, this.Sz, this.SD, this.SE, this.Sh, this.SC);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        e.a(this, parcel, n);
    }
}
