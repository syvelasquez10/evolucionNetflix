// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.result;

import android.os.Parcel;
import com.google.android.gms.fitness.data.DataType;
import java.util.Iterator;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.common.internal.m;
import java.util.ArrayList;
import java.util.Collections;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.data.q;
import java.util.List;
import com.google.android.gms.common.api.Status;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.api.Result;

public class SessionReadResult implements Result, SafeParcelable
{
    public static final Parcelable$Creator<SessionReadResult> CREATOR;
    private final int BR;
    private final Status CM;
    private final List<q> UO;
    private final List<Session> Ua;
    
    static {
        CREATOR = (Parcelable$Creator)new f();
    }
    
    SessionReadResult(final int br, final List<Session> ua, final List<q> list, final Status cm) {
        this.BR = br;
        this.Ua = ua;
        this.UO = Collections.unmodifiableList((List<? extends q>)list);
        this.CM = cm;
    }
    
    public SessionReadResult(final List<Session> ua, final List<q> list, final Status cm) {
        this.BR = 3;
        this.Ua = ua;
        this.UO = Collections.unmodifiableList((List<? extends q>)list);
        this.CM = cm;
    }
    
    public static SessionReadResult H(final Status status) {
        return new SessionReadResult(new ArrayList<Session>(), new ArrayList<q>(), status);
    }
    
    private boolean b(final SessionReadResult sessionReadResult) {
        return this.CM.equals(sessionReadResult.CM) && m.equal(this.Ua, sessionReadResult.Ua) && m.equal(this.UO, sessionReadResult.UO);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof SessionReadResult && this.b((SessionReadResult)o));
    }
    
    public List<DataSet> getDataSet(final Session session) {
        n.b(this.Ua.contains(session), "Attempting to read data for session %s which was not returned", session);
        final ArrayList<DataSet> list = new ArrayList<DataSet>();
        for (final q q : this.UO) {
            if (m.equal(session, q.getSession())) {
                list.add(q.iP());
            }
        }
        return list;
    }
    
    public List<DataSet> getDataSet(final Session session, final DataType dataType) {
        n.b(this.Ua.contains(session), "Attempting to read data for session %s which was not returned", session);
        final ArrayList<DataSet> list = new ArrayList<DataSet>();
        for (final q q : this.UO) {
            if (m.equal(session, q.getSession()) && dataType.equals(q.iP().getDataType())) {
                list.add(q.iP());
            }
        }
        return list;
    }
    
    public List<Session> getSessions() {
        return this.Ua;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.CM, this.Ua, this.UO);
    }
    
    public List<q> jJ() {
        return this.UO;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("status", this.CM).a("sessions", this.Ua).a("sessionDataSets", this.UO).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        f.a(this, parcel, n);
    }
}
