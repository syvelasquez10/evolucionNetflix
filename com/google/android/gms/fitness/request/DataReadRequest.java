// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import com.google.android.gms.fitness.data.DataTypes;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.fitness.data.AggregateDataTypes;
import com.google.android.gms.common.internal.n;
import java.util.ArrayList;
import android.os.Parcel;
import java.util.Iterator;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.common.internal.m;
import java.util.Collections;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DataReadRequest implements SafeParcelable
{
    public static final Parcelable$Creator<DataReadRequest> CREATOR;
    private final int BR;
    private final long KL;
    private final long Si;
    private final List<DataType> Su;
    private final int Sx;
    private final List<DataSource> TZ;
    private final List<DataType> Ud;
    private final List<DataSource> Ue;
    private final long Uf;
    private final DataSource Ug;
    private final int Uh;
    private final boolean Ui;
    private final boolean Uj;
    private final boolean Uk;
    
    static {
        CREATOR = (Parcelable$Creator)new f();
    }
    
    DataReadRequest(final int br, final List<DataType> list, final List<DataSource> list2, final long kl, final long si, final List<DataType> list3, final List<DataSource> list4, final int sx, final long uf, final DataSource ug, final int uh, final boolean ui, final boolean uj, final boolean uk) {
        this.BR = br;
        this.Su = Collections.unmodifiableList((List<? extends DataType>)list);
        this.TZ = Collections.unmodifiableList((List<? extends DataSource>)list2);
        this.KL = kl;
        this.Si = si;
        this.Ud = Collections.unmodifiableList((List<? extends DataType>)list3);
        this.Ue = Collections.unmodifiableList((List<? extends DataSource>)list4);
        this.Sx = sx;
        this.Uf = uf;
        this.Ug = ug;
        this.Uh = uh;
        this.Ui = ui;
        this.Uj = uj;
        this.Uk = uk;
    }
    
    private DataReadRequest(final Builder builder) {
        this.BR = 2;
        this.Su = Collections.unmodifiableList((List<? extends DataType>)builder.Su);
        this.TZ = Collections.unmodifiableList((List<? extends DataSource>)builder.TZ);
        this.KL = builder.KL;
        this.Si = builder.Si;
        this.Ud = Collections.unmodifiableList((List<? extends DataType>)builder.Ud);
        this.Ue = Collections.unmodifiableList((List<? extends DataSource>)builder.Ue);
        this.Sx = builder.Sx;
        this.Uf = builder.Uf;
        this.Ug = builder.Ug;
        this.Uh = builder.Uh;
        this.Ui = builder.Ui;
        this.Uj = builder.Uj;
        this.Uk = builder.Uk;
    }
    
    private boolean a(final DataReadRequest dataReadRequest) {
        return this.Su.equals(dataReadRequest.Su) && this.TZ.equals(dataReadRequest.TZ) && this.KL == dataReadRequest.KL && this.Si == dataReadRequest.Si && this.Sx == dataReadRequest.Sx && this.Ue.equals(dataReadRequest.Ue) && this.Ud.equals(dataReadRequest.Ud) && m.equal(this.Ug, dataReadRequest.Ug) && this.Uf == dataReadRequest.Uf && this.Uk == dataReadRequest.Uk;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof DataReadRequest && this.a((DataReadRequest)o));
    }
    
    public int getBucketType() {
        return this.Sx;
    }
    
    public List<DataSource> getDataSources() {
        return this.TZ;
    }
    
    public List<DataType> getDataTypes() {
        return this.Su;
    }
    
    public long getEndTimeMillis() {
        return this.Si;
    }
    
    public long getStartTimeMillis() {
        return this.KL;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Sx, this.KL, this.Si);
    }
    
    public List<DataType> ja() {
        return this.Ud;
    }
    
    public List<DataSource> jb() {
        return this.Ue;
    }
    
    public long jc() {
        return this.Uf;
    }
    
    public DataSource jd() {
        return this.Ug;
    }
    
    public int je() {
        return this.Uh;
    }
    
    public boolean jf() {
        return this.Ui;
    }
    
    public boolean jg() {
        return this.Uk;
    }
    
    public boolean jh() {
        return this.Uj;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ReadDataRequest{");
        if (!this.Su.isEmpty()) {
            final Iterator<DataType> iterator = this.Su.iterator();
            while (iterator.hasNext()) {
                sb.append(iterator.next().iL()).append(" ");
            }
        }
        if (!this.TZ.isEmpty()) {
            final Iterator<DataSource> iterator2 = this.TZ.iterator();
            while (iterator2.hasNext()) {
                sb.append(iterator2.next().toDebugString()).append(" ");
            }
        }
        if (this.Sx != 0) {
            sb.append("bucket by ").append(Bucket.cz(this.Sx));
            if (this.Uf > 0L) {
                sb.append(" >").append(this.Uf).append("ms");
            }
            sb.append(": ");
        }
        if (!this.Ud.isEmpty()) {
            final Iterator<DataType> iterator3 = this.Ud.iterator();
            while (iterator3.hasNext()) {
                sb.append(iterator3.next().iL()).append(" ");
            }
        }
        if (!this.Ue.isEmpty()) {
            final Iterator<DataSource> iterator4 = this.Ue.iterator();
            while (iterator4.hasNext()) {
                sb.append(iterator4.next().toDebugString()).append(" ");
            }
        }
        sb.append(String.format("(%tF %tT - %tF %tT)", this.KL, this.KL, this.Si, this.Si));
        if (this.Ug != null) {
            sb.append("activities: ").append(this.Ug.toDebugString());
        }
        if (this.Uk) {
            sb.append(" +server");
        }
        sb.append("}");
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        f.a(this, parcel, n);
    }
    
    public static class Builder
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
        
        public Builder() {
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
        
        public Builder aggregate(final DataSource dataSource, final DataType dataType) {
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
        
        public Builder aggregate(final DataType dataType, final DataType dataType2) {
            n.b(dataType, "Attempting to use a null data type");
            n.a(!this.Su.contains(dataType), (Object)"Cannot add the same data type as aggregated and detailed");
            n.b(AggregateDataTypes.INPUT_TYPES.contains(dataType), "Unsupported input data type specified for aggregation: %s", dataType);
            n.b(AggregateDataTypes.getForInput(dataType).contains(dataType2), "Invalid output aggregate data type specified: %s -> %s", dataType, dataType2);
            if (!this.Ud.contains(dataType)) {
                this.Ud.add(dataType);
            }
            return this;
        }
        
        public Builder bucketByActivitySegment(final int n, final TimeUnit timeUnit) {
            n.b(this.Sx == 0, "Bucketing strategy already set to %s", this.Sx);
            n.b(n > 0, "Must specify a valid minimum duration for an activity segment: %d", n);
            this.Sx = 4;
            this.Uf = timeUnit.toMillis(n);
            return this;
        }
        
        public Builder bucketByActivitySegment(final int n, final TimeUnit timeUnit, final DataSource ug) {
            n.b(this.Sx == 0, "Bucketing strategy already set to %s", this.Sx);
            n.b(n > 0, "Must specify a valid minimum duration for an activity segment: %d", n);
            n.b(ug != null, (Object)"Invalid activity data source specified");
            n.b(ug.getDataType().equals(DataTypes.ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", ug);
            this.Ug = ug;
            this.Sx = 4;
            this.Uf = timeUnit.toMillis(n);
            return this;
        }
        
        public Builder bucketByActivityType(final int n, final TimeUnit timeUnit) {
            n.b(this.Sx == 0, "Bucketing strategy already set to %s", this.Sx);
            n.b(n > 0, "Must specify a valid minimum duration for an activity segment: %d", n);
            this.Sx = 3;
            this.Uf = timeUnit.toMillis(n);
            return this;
        }
        
        public Builder bucketByActivityType(final int n, final TimeUnit timeUnit, final DataSource ug) {
            n.b(this.Sx == 0, "Bucketing strategy already set to %s", this.Sx);
            n.b(n > 0, "Must specify a valid minimum duration for an activity segment: %d", n);
            n.b(ug != null, (Object)"Invalid activity data source specified");
            n.b(ug.getDataType().equals(DataTypes.ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", ug);
            this.Ug = ug;
            this.Sx = 3;
            this.Uf = timeUnit.toMillis(n);
            return this;
        }
        
        public Builder bucketBySession(final int n, final TimeUnit timeUnit) {
            n.b(this.Sx == 0, "Bucketing strategy already set to %s", this.Sx);
            n.b(n > 0, "Must specify a valid minimum duration for an activity segment: %d", n);
            this.Sx = 2;
            this.Uf = timeUnit.toMillis(n);
            return this;
        }
        
        public Builder bucketByTime(final int n, final TimeUnit timeUnit) {
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
        
        public Builder enableServerQueries() {
            this.Uk = true;
            return this;
        }
        
        public Builder read(final DataSource dataSource) {
            n.b(dataSource, "Attempting to add a null data source");
            n.b(!this.Ue.contains(dataSource), (Object)"Cannot add the same data source as aggregated and detailed");
            if (!this.TZ.contains(dataSource)) {
                this.TZ.add(dataSource);
            }
            return this;
        }
        
        public Builder read(final DataType dataType) {
            n.b(dataType, "Attempting to use a null data type");
            n.a(!this.Ud.contains(dataType), (Object)"Cannot add the same data type as aggregated and detailed");
            if (!this.Su.contains(dataType)) {
                this.Su.add(dataType);
            }
            return this;
        }
        
        public Builder setTimeRange(final long kl, final long si) {
            this.KL = kl;
            this.Si = si;
            return this;
        }
    }
}
