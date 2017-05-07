// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.result;

import android.os.Parcel;
import com.google.android.gms.common.internal.m;
import java.util.Collections;
import com.google.android.gms.fitness.data.Session;
import java.util.List;
import com.google.android.gms.common.api.Status;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.api.Result;

public class SessionStopResult implements Result, SafeParcelable
{
    public static final Parcelable$Creator<SessionStopResult> CREATOR;
    private final int BR;
    private final Status CM;
    private final List<Session> Ua;
    
    static {
        CREATOR = (Parcelable$Creator)new g();
    }
    
    SessionStopResult(final int br, final Status cm, final List<Session> list) {
        this.BR = br;
        this.CM = cm;
        this.Ua = Collections.unmodifiableList((List<? extends Session>)list);
    }
    
    public SessionStopResult(final Status cm, final List<Session> list) {
        this.BR = 3;
        this.CM = cm;
        this.Ua = Collections.unmodifiableList((List<? extends Session>)list);
    }
    
    public static SessionStopResult I(final Status status) {
        return new SessionStopResult(status, Collections.emptyList());
    }
    
    private boolean b(final SessionStopResult sessionStopResult) {
        return this.CM.equals(sessionStopResult.CM) && m.equal(this.Ua, sessionStopResult.Ua);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (o instanceof SessionStopResult && this.b((SessionStopResult)o));
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
        return m.hashCode(this.CM, this.Ua);
    }
    
    @Override
    public String toString() {
        return m.h(this).a("status", this.CM).a("sessions", this.Ua).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        g.a(this, parcel, n);
    }
}
