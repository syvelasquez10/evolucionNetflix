// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.internal.n;
import java.util.ArrayList;
import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import java.util.Collections;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class SessionReadRequest implements SafeParcelable
{
    public static final Parcelable$Creator<SessionReadRequest> CREATOR;
    private final int BR;
    private final long KL;
    private final long Si;
    private final List<DataType> Su;
    private final List<DataSource> TZ;
    private final String UC;
    private boolean UD;
    private final List<String> UE;
    private final boolean Uk;
    private final String vL;
    
    static {
        CREATOR = (Parcelable$Creator)new s();
    }
    
    SessionReadRequest(final int br, final String uc, final String vl, final long kl, final long si, final List<DataType> list, final List<DataSource> list2, final boolean ud, final boolean uk, final List<String> ue) {
        this.BR = br;
        this.UC = uc;
        this.vL = vl;
        this.KL = kl;
        this.Si = si;
        this.Su = Collections.unmodifiableList((List<? extends DataType>)list);
        this.TZ = Collections.unmodifiableList((List<? extends DataSource>)list2);
        this.UD = ud;
        this.Uk = uk;
        this.UE = ue;
    }
    
    private SessionReadRequest(final Builder builder) {
        this.BR = 3;
        this.UC = builder.UC;
        this.vL = builder.vL;
        this.KL = builder.KL;
        this.Si = builder.Si;
        this.Su = Collections.unmodifiableList((List<? extends DataType>)builder.Su);
        this.TZ = Collections.unmodifiableList((List<? extends DataSource>)builder.TZ);
        this.UD = builder.UD;
        this.Uk = builder.Uk;
        this.UE = builder.UE;
    }
    
    private boolean a(final SessionReadRequest sessionReadRequest) {
        return m.equal(this.UC, sessionReadRequest.UC) && this.vL.equals(sessionReadRequest.vL) && this.KL == sessionReadRequest.KL && this.Si == sessionReadRequest.Si && m.equal(this.Su, sessionReadRequest.Su) && m.equal(this.TZ, sessionReadRequest.TZ) && this.UD == sessionReadRequest.UD && this.UE.equals(sessionReadRequest.UE) && this.Uk == sessionReadRequest.Uk;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof SessionReadRequest && this.a((SessionReadRequest)o));
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
    
    public String getSessionId() {
        return this.vL;
    }
    
    public long getStartTimeMillis() {
        return this.KL;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.UC, this.vL, this.KL, this.Si);
    }
    
    public boolean jg() {
        return this.Uk;
    }
    
    public String ju() {
        return this.UC;
    }
    
    public boolean jv() {
        return this.UD;
    }
    
    public List<String> jw() {
        return this.UE;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("sessionName", this.UC).a("sessionId", this.vL).a("startTimeMillis", this.KL).a("endTimeMillis", this.Si).a("dataTypes", this.Su).a("dataSources", this.TZ).a("sessionsFromAllApps", this.UD).a("excludedPackages", this.UE).a("useServer", this.Uk).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        s.a(this, parcel, n);
    }
    
    public static class Builder
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
        
        public Builder() {
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
        
        public Builder enableServerQueries() {
            this.Uk = true;
            return this;
        }
        
        public Builder excludePackage(final String s) {
            n.b(s, (Object)"Attempting to use a null package name");
            if (!this.UE.contains(s)) {
                this.UE.add(s);
            }
            return this;
        }
        
        public Builder read(final DataSource dataSource) {
            n.b(dataSource, "Attempting to add a null data source");
            if (!this.TZ.contains(dataSource)) {
                this.TZ.add(dataSource);
            }
            return this;
        }
        
        public Builder read(final DataType dataType) {
            n.b(dataType, "Attempting to use a null data type");
            if (!this.Su.contains(dataType)) {
                this.Su.add(dataType);
            }
            return this;
        }
        
        public Builder readSessionsFromAllApps() {
            this.UD = true;
            return this;
        }
        
        public Builder setSessionId(final String vl) {
            this.vL = vl;
            return this;
        }
        
        public Builder setSessionName(final String uc) {
            this.UC = uc;
            return this;
        }
        
        public Builder setTimeInterval(final long kl, final long si) {
            this.KL = kl;
            this.Si = si;
            return this;
        }
        
        public Builder setTimeInterval(final long n, final long n2, final TimeUnit timeUnit) {
            return this.setTimeInterval(timeUnit.toMillis(n), timeUnit.toMillis(n2));
        }
    }
}
