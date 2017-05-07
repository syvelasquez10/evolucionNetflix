// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.request;

import java.util.Iterator;
import com.google.android.gms.common.internal.n;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import com.google.android.gms.fitness.data.DataSource;
import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import java.util.Collections;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import java.util.List;
import com.google.android.gms.fitness.data.Session;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class SessionInsertRequest implements SafeParcelable
{
    public static final Parcelable$Creator<SessionInsertRequest> CREATOR;
    private final int BR;
    private final Session Sk;
    private final List<DataSet> Sw;
    private final List<DataPoint> UA;
    
    static {
        CREATOR = (Parcelable$Creator)new r();
    }
    
    SessionInsertRequest(final int br, final Session sk, final List<DataSet> list, final List<DataPoint> list2) {
        this.BR = br;
        this.Sk = sk;
        this.Sw = Collections.unmodifiableList((List<? extends DataSet>)list);
        this.UA = Collections.unmodifiableList((List<? extends DataPoint>)list2);
    }
    
    private SessionInsertRequest(final SessionInsertRequest$Builder sessionInsertRequest$Builder) {
        this.BR = 1;
        this.Sk = sessionInsertRequest$Builder.Sk;
        this.Sw = Collections.unmodifiableList((List<? extends DataSet>)sessionInsertRequest$Builder.Sw);
        this.UA = Collections.unmodifiableList((List<? extends DataPoint>)sessionInsertRequest$Builder.UA);
    }
    
    private boolean a(final SessionInsertRequest sessionInsertRequest) {
        return m.equal(this.Sk, sessionInsertRequest.Sk) && m.equal(this.Sw, sessionInsertRequest.Sw) && m.equal(this.UA, sessionInsertRequest.UA);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof SessionInsertRequest && this.a((SessionInsertRequest)o));
    }
    
    public List<DataSet> getDataSets() {
        return this.Sw;
    }
    
    public Session getSession() {
        return this.Sk;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Sk, this.Sw, this.UA);
    }
    
    public List<DataPoint> js() {
        return this.UA;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("session", this.Sk).a("dataSets", this.Sw).a("aggregateDataPoints", this.UA).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        r.a(this, parcel, n);
    }
}
