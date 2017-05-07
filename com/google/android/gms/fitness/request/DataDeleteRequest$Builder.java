// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import com.google.android.gms.common.internal.n;
import java.util.ArrayList;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.List;

public class DataDeleteRequest$Builder
{
    private long KL;
    private long Si;
    private List<DataType> Su;
    private List<DataSource> TZ;
    private List<Session> Ua;
    private boolean Ub;
    private boolean Uc;
    
    public DataDeleteRequest$Builder() {
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
    
    public DataDeleteRequest$Builder addDataSource(final DataSource dataSource) {
        final boolean b = true;
        n.b(!this.Ub, (Object)"All data is already marked for deletion");
        n.b(dataSource != null && b, (Object)"Must specify a valid data source");
        if (!this.TZ.contains(dataSource)) {
            this.TZ.add(dataSource);
        }
        return this;
    }
    
    public DataDeleteRequest$Builder addDataType(final DataType dataType) {
        final boolean b = true;
        n.b(!this.Ub, (Object)"All data is already marked for deletion");
        n.b(dataType != null && b, (Object)"Must specify a valid data type");
        if (!this.Su.contains(dataType)) {
            this.Su.add(dataType);
        }
        return this;
    }
    
    public DataDeleteRequest$Builder addSession(final Session session) {
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
    
    public DataDeleteRequest$Builder deleteAllData() {
        n.b(this.Su.isEmpty() && this.TZ.isEmpty(), "Specific data source/type already specified for deletion. DataSources: %s DataTypes: %s", this.TZ, this.Su);
        this.Ub = true;
        return this;
    }
    
    public DataDeleteRequest$Builder deleteAllSessions() {
        n.b(this.Ua.isEmpty(), "Specific sessions already added for deletion: %s", this.Ua);
        this.Uc = true;
        return this;
    }
    
    public DataDeleteRequest$Builder setTimeInterval(final long n, final long n2, final TimeUnit timeUnit) {
        n.b(n > 0L, "Invalid start time :%d", n);
        n.b(n2 > n, "Invalid end time :%d", n2);
        this.KL = timeUnit.toMillis(n);
        this.Si = timeUnit.toMillis(n2);
        return this;
    }
}
