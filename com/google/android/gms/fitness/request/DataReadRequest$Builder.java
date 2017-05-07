// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.fitness.data.DataTypes;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.fitness.data.AggregateDataTypes;
import com.google.android.gms.common.internal.n;
import java.util.ArrayList;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.List;

public class DataReadRequest$Builder
{
    private long KL;
    private long Si;
    private List<DataType> Su;
    private int Sx;
    private List<DataSource> TZ;
    private List<DataType> Ud;
    private List<DataSource> Ue;
    private long Uf;
    private DataSource Ug;
    private int Uh;
    private boolean Ui;
    private boolean Uj;
    private boolean Uk;
    
    public DataReadRequest$Builder() {
        this.Su = new ArrayList<DataType>();
        this.TZ = new ArrayList<DataSource>();
        this.Ud = new ArrayList<DataType>();
        this.Ue = new ArrayList<DataSource>();
        this.Sx = 0;
        this.Uf = 0L;
        this.Uh = 0;
        this.Ui = false;
        this.Uj = false;
        this.Uk = false;
    }
    
    public DataReadRequest$Builder aggregate(final DataSource dataSource, final DataType dataType) {
        n.b(dataSource, "Attempting to add a null data source");
        n.a(!this.TZ.contains(dataSource), (Object)"Cannot add the same data source for aggregated and detailed");
        final DataType dataType2 = dataSource.getDataType();
        n.b(AggregateDataTypes.INPUT_TYPES.contains(dataType2), "Unsupported input data type specified for aggregation: %s", dataType2);
        n.b(AggregateDataTypes.getForInput(dataType2).contains(dataType), "Invalid output aggregate data type specified: %s -> %s", dataType2, dataType);
        if (!this.Ue.contains(dataSource)) {
            this.Ue.add(dataSource);
        }
        return this;
    }
    
    public DataReadRequest$Builder aggregate(final DataType dataType, final DataType dataType2) {
        n.b(dataType, "Attempting to use a null data type");
        n.a(!this.Su.contains(dataType), (Object)"Cannot add the same data type as aggregated and detailed");
        n.b(AggregateDataTypes.INPUT_TYPES.contains(dataType), "Unsupported input data type specified for aggregation: %s", dataType);
        n.b(AggregateDataTypes.getForInput(dataType).contains(dataType2), "Invalid output aggregate data type specified: %s -> %s", dataType, dataType2);
        if (!this.Ud.contains(dataType)) {
            this.Ud.add(dataType);
        }
        return this;
    }
    
    public DataReadRequest$Builder bucketByActivitySegment(final int n, final TimeUnit timeUnit) {
        n.b(this.Sx == 0, "Bucketing strategy already set to %s", this.Sx);
        n.b(n > 0, "Must specify a valid minimum duration for an activity segment: %d", n);
        this.Sx = 4;
        this.Uf = timeUnit.toMillis(n);
        return this;
    }
    
    public DataReadRequest$Builder bucketByActivitySegment(final int n, final TimeUnit timeUnit, final DataSource ug) {
        n.b(this.Sx == 0, "Bucketing strategy already set to %s", this.Sx);
        n.b(n > 0, "Must specify a valid minimum duration for an activity segment: %d", n);
        n.b(ug != null, (Object)"Invalid activity data source specified");
        n.b(ug.getDataType().equals(DataTypes.ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", ug);
        this.Ug = ug;
        this.Sx = 4;
        this.Uf = timeUnit.toMillis(n);
        return this;
    }
    
    public DataReadRequest$Builder bucketByActivityType(final int n, final TimeUnit timeUnit) {
        n.b(this.Sx == 0, "Bucketing strategy already set to %s", this.Sx);
        n.b(n > 0, "Must specify a valid minimum duration for an activity segment: %d", n);
        this.Sx = 3;
        this.Uf = timeUnit.toMillis(n);
        return this;
    }
    
    public DataReadRequest$Builder bucketByActivityType(final int n, final TimeUnit timeUnit, final DataSource ug) {
        n.b(this.Sx == 0, "Bucketing strategy already set to %s", this.Sx);
        n.b(n > 0, "Must specify a valid minimum duration for an activity segment: %d", n);
        n.b(ug != null, (Object)"Invalid activity data source specified");
        n.b(ug.getDataType().equals(DataTypes.ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", ug);
        this.Ug = ug;
        this.Sx = 3;
        this.Uf = timeUnit.toMillis(n);
        return this;
    }
    
    public DataReadRequest$Builder bucketBySession(final int n, final TimeUnit timeUnit) {
        n.b(this.Sx == 0, "Bucketing strategy already set to %s", this.Sx);
        n.b(n > 0, "Must specify a valid minimum duration for an activity segment: %d", n);
        this.Sx = 2;
        this.Uf = timeUnit.toMillis(n);
        return this;
    }
    
    public DataReadRequest$Builder bucketByTime(final int n, final TimeUnit timeUnit) {
        n.b(this.Sx == 0, "Bucketing strategy already set to %s", this.Sx);
        n.b(n > 0, "Must specify a valid minimum duration for an activity segment: %d", n);
        this.Sx = 1;
        this.Uf = timeUnit.toMillis(n);
        return this;
    }
    
    public DataReadRequest build() {
        final boolean b = true;
        n.a(!this.TZ.isEmpty() || !this.Su.isEmpty() || !this.Ue.isEmpty() || !this.Ud.isEmpty(), (Object)"Must add at least one data source (aggregated or detailed)");
        n.a(this.KL > 0L, "Invalid start time: %s", this.KL);
        n.a(this.Si > 0L && this.Si > this.KL, "Invalid end time: %s", this.Si);
        boolean b2;
        if (this.Ue.isEmpty() && this.Ud.isEmpty()) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        boolean b3 = false;
        Label_0184: {
            if (b2) {
                b3 = b;
                if (this.Sx == 0) {
                    break Label_0184;
                }
            }
            b3 = (!b2 && this.Sx != 0 && b);
        }
        n.a(b3, (Object)"Must specify a valid bucketing strategy while requesting aggregation");
        return new DataReadRequest(this, null);
    }
    
    public DataReadRequest$Builder enableServerQueries() {
        this.Uk = true;
        return this;
    }
    
    public DataReadRequest$Builder read(final DataSource dataSource) {
        n.b(dataSource, "Attempting to add a null data source");
        n.b(!this.Ue.contains(dataSource), (Object)"Cannot add the same data source as aggregated and detailed");
        if (!this.TZ.contains(dataSource)) {
            this.TZ.add(dataSource);
        }
        return this;
    }
    
    public DataReadRequest$Builder read(final DataType dataType) {
        n.b(dataType, "Attempting to use a null data type");
        n.a(!this.Ud.contains(dataType), (Object)"Cannot add the same data type as aggregated and detailed");
        if (!this.Su.contains(dataType)) {
            this.Su.add(dataType);
        }
        return this;
    }
    
    public DataReadRequest$Builder setTimeRange(final long kl, final long si) {
        this.KL = kl;
        this.Si = si;
        return this;
    }
}
