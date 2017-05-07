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
    
    private SessionReadRequest(final SessionReadRequest$Builder sessionReadRequest$Builder) {
        this.BR = 3;
        this.UC = sessionReadRequest$Builder.UC;
        this.vL = sessionReadRequest$Builder.vL;
        this.KL = sessionReadRequest$Builder.KL;
        this.Si = sessionReadRequest$Builder.Si;
        this.Su = Collections.unmodifiableList((List<? extends DataType>)sessionReadRequest$Builder.Su);
        this.TZ = Collections.unmodifiableList((List<? extends DataSource>)sessionReadRequest$Builder.TZ);
        this.UD = sessionReadRequest$Builder.UD;
        this.Uk = sessionReadRequest$Builder.Uk;
        this.UE = sessionReadRequest$Builder.UE;
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
}
