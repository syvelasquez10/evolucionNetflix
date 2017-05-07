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
    
    private DataDeleteRequest(final DataDeleteRequest$Builder dataDeleteRequest$Builder) {
        this.BR = 1;
        this.KL = dataDeleteRequest$Builder.KL;
        this.Si = dataDeleteRequest$Builder.Si;
        this.TZ = Collections.unmodifiableList((List<? extends DataSource>)dataDeleteRequest$Builder.TZ);
        this.Su = Collections.unmodifiableList((List<? extends DataType>)dataDeleteRequest$Builder.Su);
        this.Ua = Collections.unmodifiableList((List<? extends Session>)dataDeleteRequest$Builder.Ua);
        this.Ub = dataDeleteRequest$Builder.Ub;
        this.Uc = dataDeleteRequest$Builder.Uc;
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
}
