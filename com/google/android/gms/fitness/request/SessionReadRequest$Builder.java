// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.internal.n;
import java.util.ArrayList;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.List;

public class SessionReadRequest$Builder
{
    private long KL;
    private long Si;
    private List<DataType> Su;
    private List<DataSource> TZ;
    private String UC;
    private boolean UD;
    private List<String> UE;
    private boolean Uk;
    private String vL;
    
    public SessionReadRequest$Builder() {
        this.KL = 0L;
        this.Si = 0L;
        this.Su = new ArrayList<DataType>();
        this.TZ = new ArrayList<DataSource>();
        this.UD = false;
        this.Uk = false;
        this.UE = new ArrayList<String>();
    }
    
    public SessionReadRequest build() {
        n.b(this.KL > 0L, "Invalid start time: %s", this.KL);
        n.b(this.Si > 0L && this.Si > this.KL, "Invalid end time: %s", this.Si);
        return new SessionReadRequest(this, null);
    }
    
    public SessionReadRequest$Builder enableServerQueries() {
        this.Uk = true;
        return this;
    }
    
    public SessionReadRequest$Builder excludePackage(final String s) {
        n.b(s, (Object)"Attempting to use a null package name");
        if (!this.UE.contains(s)) {
            this.UE.add(s);
        }
        return this;
    }
    
    public SessionReadRequest$Builder read(final DataSource dataSource) {
        n.b(dataSource, "Attempting to add a null data source");
        if (!this.TZ.contains(dataSource)) {
            this.TZ.add(dataSource);
        }
        return this;
    }
    
    public SessionReadRequest$Builder read(final DataType dataType) {
        n.b(dataType, "Attempting to use a null data type");
        if (!this.Su.contains(dataType)) {
            this.Su.add(dataType);
        }
        return this;
    }
    
    public SessionReadRequest$Builder readSessionsFromAllApps() {
        this.UD = true;
        return this;
    }
    
    public SessionReadRequest$Builder setSessionId(final String vl) {
        this.vL = vl;
        return this;
    }
    
    public SessionReadRequest$Builder setSessionName(final String uc) {
        this.UC = uc;
        return this;
    }
    
    public SessionReadRequest$Builder setTimeInterval(final long kl, final long si) {
        this.KL = kl;
        this.Si = si;
        return this;
    }
    
    public SessionReadRequest$Builder setTimeInterval(final long n, final long n2, final TimeUnit timeUnit) {
        return this.setTimeInterval(timeUnit.toMillis(n), timeUnit.toMillis(n2));
    }
}
