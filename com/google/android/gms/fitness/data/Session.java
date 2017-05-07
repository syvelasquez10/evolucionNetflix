// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.common.internal.n;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.c;
import android.content.Intent;
import com.google.android.gms.common.internal.m;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class Session implements SafeParcelable
{
    public static final Parcelable$Creator<Session> CREATOR;
    private final int BR;
    private final long KL;
    private final a SJ;
    private final long Si;
    private final int Sv;
    private final String Tf;
    private final String Tg;
    private final String mName;
    
    static {
        CREATOR = (Parcelable$Creator)new p();
    }
    
    Session(final int br, final long kl, final long si, final String mName, final String tf, final String tg, final int sv, final a sj) {
        this.BR = br;
        this.KL = kl;
        this.Si = si;
        this.mName = mName;
        this.Tf = tf;
        this.Tg = tg;
        this.Sv = sv;
        this.SJ = sj;
    }
    
    private Session(final Session$Builder session$Builder) {
        this.BR = 2;
        this.KL = session$Builder.KL;
        this.Si = session$Builder.Si;
        this.mName = session$Builder.mName;
        this.Tf = session$Builder.Tf;
        this.Tg = session$Builder.Tg;
        this.Sv = session$Builder.Sv;
        this.SJ = session$Builder.SJ;
    }
    
    private boolean a(final Session session) {
        return this.KL == session.KL && this.Si == session.Si && m.equal(this.mName, session.mName) && m.equal(this.Tf, session.Tf) && m.equal(this.Tg, session.Tg) && m.equal(this.SJ, session.SJ) && this.Sv == session.Sv;
    }
    
    public static Session extract(final Intent intent) {
        if (intent == null) {
            return null;
        }
        return c.a(intent, "vnd.google.fitness.session", Session.CREATOR);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof Session && this.a((Session)o));
    }
    
    public int getActivity() {
        return this.Sv;
    }
    
    public String getAppPackageName() {
        if (this.SJ == null) {
            return null;
        }
        return this.SJ.getPackageName();
    }
    
    public String getDescription() {
        return this.Tg;
    }
    
    public long getEndTimeMillis() {
        return this.Si;
    }
    
    public String getIdentifier() {
        return this.Tf;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public long getStartTimeMillis() {
        return this.KL;
    }
    
    int getVersionCode() {
        return this.BR;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.KL, this.Si, this.mName, this.Tf, this.Sv, this.SJ, this.Tg);
    }
    
    public a iH() {
        return this.SJ;
    }
    
    public boolean isOngoing() {
        return this.Si == 0L;
    }
    
    @Override
    public String toString() {
        return m.h(this).a("startTime", this.KL).a("endTime", this.Si).a("name", this.mName).a("identifier", this.Tf).a("description", this.Tg).a("activity", this.Sv).a("application", this.SJ).toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        p.a(this, parcel, n);
    }
}
