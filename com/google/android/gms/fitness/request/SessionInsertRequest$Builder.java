// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import java.util.Iterator;
import com.google.android.gms.common.internal.n;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import java.util.List;
import com.google.android.gms.fitness.data.Session;

public class SessionInsertRequest$Builder
{
    private Session Sk;
    private List<DataSet> Sw;
    private List<DataPoint> UA;
    private List<DataSource> UB;
    
    public SessionInsertRequest$Builder() {
        this.Sw = new ArrayList<DataSet>();
        this.UA = new ArrayList<DataPoint>();
        this.UB = new ArrayList<DataSource>();
    }
    
    private void c(final DataPoint dataPoint) {
        final long nanos = TimeUnit.MILLISECONDS.toNanos(this.Sk.getStartTimeMillis());
        final long nanos2 = TimeUnit.MILLISECONDS.toNanos(this.Sk.getEndTimeMillis());
        final long timestampNanos = dataPoint.getTimestampNanos();
        if (timestampNanos != 0L) {
            n.a(timestampNanos >= nanos && timestampNanos <= nanos2, "Data point %s has time stamp outside session interval [%d, %d]", dataPoint, nanos, nanos2);
        }
        final long startTimeNanos = dataPoint.getStartTimeNanos();
        final long endTimeNanos = dataPoint.getEndTimeNanos();
        if (startTimeNanos != 0L && endTimeNanos != 0L) {
            n.a(startTimeNanos >= nanos && endTimeNanos <= nanos2, "Data point %s has start and end times outside session interval [%d, %d]", dataPoint, nanos, nanos2);
        }
    }
    
    private void jt() {
        final Iterator<DataSet> iterator = this.Sw.iterator();
        while (iterator.hasNext()) {
            final Iterator<DataPoint> iterator2 = iterator.next().getDataPoints().iterator();
            while (iterator2.hasNext()) {
                this.c(iterator2.next());
            }
        }
        final Iterator<DataPoint> iterator3 = this.UA.iterator();
        while (iterator3.hasNext()) {
            this.c(iterator3.next());
        }
    }
    
    public SessionInsertRequest$Builder addAggregateDataPoint(final DataPoint dataPoint) {
        n.b(dataPoint != null, (Object)"Must specify a valid aggregate data point.");
        final long startTimeNanos = dataPoint.getStartTimeNanos();
        final long endTimeNanos = dataPoint.getEndTimeNanos();
        n.b(startTimeNanos > 0L && endTimeNanos > startTimeNanos, "Aggregate data point should have valid start and end times: %s", dataPoint);
        final DataSource dataSource = dataPoint.getDataSource();
        n.a(!this.UB.contains(dataSource), "Data set/Aggregate data point for this data source %s is already added.", dataSource);
        this.UB.add(dataSource);
        this.UA.add(dataPoint);
        return this;
    }
    
    public SessionInsertRequest$Builder addDataSet(final DataSet set) {
        final boolean b = true;
        n.b(set != null, (Object)"Must specify a valid data set.");
        final DataSource dataSource = set.getDataSource();
        n.a(!this.UB.contains(dataSource), "Data set for this data source %s is already added.", dataSource);
        n.b(!set.getDataPoints().isEmpty() && b, (Object)"No data points specified in the input data set.");
        this.UB.add(dataSource);
        this.Sw.add(set);
        return this;
    }
    
    public SessionInsertRequest build() {
        final boolean b = true;
        n.a(this.Sk != null, (Object)"Must specify a valid session.");
        n.a(this.Sk.getEndTimeMillis() != 0L && b, (Object)"Must specify a valid end time, cannot insert a continuing session.");
        this.jt();
        return new SessionInsertRequest(this, null);
    }
    
    public SessionInsertRequest$Builder setSession(final Session sk) {
        this.Sk = sk;
        return this;
    }
}
