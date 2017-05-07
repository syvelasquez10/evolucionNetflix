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
    
    private DataReadRequest(final DataReadRequest$Builder dataReadRequest$Builder) {
        this.BR = 2;
        this.Su = Collections.unmodifiableList((List<? extends DataType>)dataReadRequest$Builder.Su);
        this.TZ = Collections.unmodifiableList((List<? extends DataSource>)dataReadRequest$Builder.TZ);
        this.KL = dataReadRequest$Builder.KL;
        this.Si = dataReadRequest$Builder.Si;
        this.Ud = Collections.unmodifiableList((List<? extends DataType>)dataReadRequest$Builder.Ud);
        this.Ue = Collections.unmodifiableList((List<? extends DataSource>)dataReadRequest$Builder.Ue);
        this.Sx = dataReadRequest$Builder.Sx;
        this.Uf = dataReadRequest$Builder.Uf;
        this.Ug = dataReadRequest$Builder.Ug;
        this.Uh = dataReadRequest$Builder.Uh;
        this.Ui = dataReadRequest$Builder.Ui;
        this.Uj = dataReadRequest$Builder.Uj;
        this.Uk = dataReadRequest$Builder.Uk;
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
}
