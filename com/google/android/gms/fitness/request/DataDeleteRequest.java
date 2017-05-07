// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import com.google.android.gms.common.internal.n;
import java.util.ArrayList;
import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import java.util.Collections;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class DataDeleteRequest implements SafeParcelable
{
    public static final Parcelable$Creator<DataDeleteRequest> CREATOR;
    private final int BR;
    private final long KL;
    private final long Si;
    private final List<DataType> Su;
    private final List<DataSource> TZ;
    private final List<Session> Ua;
    private final boolean Ub;
    private final boolean Uc;
    
    static {
        CREATOR = (Parcelable$Creator)new d();
    }
    
    DataDeleteRequest(final int br, final long kl, final long si, final List<DataSource> list, final List<DataType> list2, final List<Session> ua, final boolean ub, final boolean uc) {
        this.BR = br;
        this.KL = kl;
        this.Si = si;
        this.TZ = Collections.unmodifiableList((List<? extends DataSource>)list);
        this.Su = Collections.unmodifiableList((List<? extends DataType>)list2);
        this.Ua = ua;
        this.Ub = ub;
        this.Uc = uc;
    }
    
    private DataDeleteRequest(final Builder builder) {
        this.BR = 1;
        this.KL = builder.KL;
        this.Si = builder.Si;
        this.TZ = Collections.unmodifiableList((List<? extends DataSource>)builder.TZ);
        this.Su = Collections.unmodifiableList((List<? extends DataType>)builder.Su);
        this.Ua = Collections.unmodifiableList((List<? extends Session>)builder.Ua);
        this.Ub = builder.Ub;
        this.Uc = builder.Uc;
    }
    
    private boolean a(final DataDeleteRequest dataDeleteRequest) {
        return this.KL == dataDeleteRequest.KL && this.Si == dataDeleteRequest.Si && m.equal(this.TZ, dataDeleteRequest.TZ) && m.equal(this.Su, dataDeleteRequest.Su) && m.equal(this.Ua, dataDeleteRequest.Ua) && this.Ub == dataDeleteRequest.Ub && this.Uc == dataDeleteRequest.Uc;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof DataDeleteRequest && this.a((DataDeleteRequest)o));
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
    
    public List<Session> getSessions() {
        return this.Ua;
    }
    
    public long getStartTimeMillis() {
        return this.KL;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.KL, this.Si);
    }
    
    public boolean iX() {
        return this.Ub;
    }
    
    public boolean iY() {
        return this.Uc;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("startTimeMillis", this.KL).a("endTimeMillis", this.Si).a("dataSources", this.TZ).a("dateTypes", this.Su).a("sessions", this.Ua).a("deleteAllData", this.Ub).a("deleteAllSessions", this.Uc).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        d.a(this, parcel, n);
    }
    
    public static class Builder
    {
        private long KL;
        private long Si;
        private List<DataType> Su;
        private List<DataSource> TZ;
        private List<Session> Ua;
        private boolean Ub;
        private boolean Uc;
        
        public Builder() {
            this.TZ = new ArrayList<DataSource>();
            this.Su = new ArrayList<DataType>();
            this.Ua = new ArrayList<Session>();
            this.Ub = false;
            this.Uc = false;
        }
        
        private void iZ() {
            if (!this.Ua.isEmpty()) {
                for (final Session session : this.Ua) {
                    n.a(session.getStartTimeMillis() >= this.KL && session.getEndTimeMillis() <= this.Si, "Session %s is outside the time interval [%d, %d]", session, this.KL, this.Si);
                }
            }
        }
        
        public Builder addDataSource(final DataSource dataSource) {
            final boolean b = true;
            n.b(!this.Ub, (Object)"All data is already marked for deletion");
            n.b(dataSource != null && b, (Object)"Must specify a valid data source");
            if (!this.TZ.contains(dataSource)) {
                this.TZ.add(dataSource);
            }
            return this;
        }
        
        public Builder addDataType(final DataType dataType) {
            final boolean b = true;
            n.b(!this.Ub, (Object)"All data is already marked for deletion");
            n.b(dataType != null && b, (Object)"Must specify a valid data type");
            if (!this.Su.contains(dataType)) {
                this.Su.add(dataType);
            }
            return this;
        }
        
        public Builder addSession(final Session session) {
            final boolean b = true;
            n.b(!this.Uc, (Object)"All sessions already marked for deletion");
            n.b(session != null, (Object)"Must specify a valid session");
            n.b(session.getEndTimeMillis() > 0L && b, (Object)"Must specify a session that has already ended");
            this.Ua.add(session);
            return this;
        }
        
        public DataDeleteRequest build() {
            final boolean b = false;
            n.a(this.KL > 0L && this.Si > this.KL, (Object)"Must specify a valid time interval");
            int n;
            if (this.Ub || !this.TZ.isEmpty() || !this.Su.isEmpty()) {
                n = 1;
            }
            else {
                n = 0;
            }
            int n2;
            if (this.Uc || !this.Ua.isEmpty()) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            boolean b2 = false;
            Label_0099: {
                if (n == 0) {
                    b2 = b;
                    if (n2 == 0) {
                        break Label_0099;
                    }
                }
                b2 = true;
            }
            com.google.android.gms.common.internal.n.a(b2, (Object)"No data or session marked for deletion");
            this.iZ();
            return new DataDeleteRequest(this, null);
        }
        
        public Builder deleteAllData() {
            n.b(this.Su.isEmpty() && this.TZ.isEmpty(), "Specific data source/type already specified for deletion. DataSources: %s DataTypes: %s", this.TZ, this.Su);
            this.Ub = true;
            return this;
        }
        
        public Builder deleteAllSessions() {
            n.b(this.Ua.isEmpty(), "Specific sessions already added for deletion: %s", this.Ua);
            this.Uc = true;
            return this;
        }
        
        public Builder setTimeInterval(final long n, final long n2, final TimeUnit timeUnit) {
            n.b(n > 0L, "Invalid start time :%d", n);
            n.b(n2 > n, "Invalid end time :%d", n2);
            this.KL = timeUnit.toMillis(n);
            this.Si = timeUnit.toMillis(n2);
            return this;
        }
    }
}
