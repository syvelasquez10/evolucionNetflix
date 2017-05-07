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
    
    private Session(final Builder builder) {
        this.BR = 2;
        this.KL = builder.KL;
        this.Si = builder.Si;
        this.mName = builder.mName;
        this.Tf = builder.Tf;
        this.Tg = builder.Tg;
        this.Sv = builder.Sv;
        this.SJ = builder.SJ;
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
    
    public static class Builder
    {
        private long KL;
        private a SJ;
        private long Si;
        private int Sv;
        private String Tf;
        private String Tg;
        private String mName;
        
        public Builder() {
            this.KL = 0L;
            this.Si = 0L;
            this.mName = null;
            this.Sv = 4;
        }
        
        public Session build() {
            final boolean b = false;
            n.a(this.KL > 0L, (Object)"Start time should be specified.");
            boolean b2 = false;
            Label_0044: {
                if (this.Si != 0L) {
                    b2 = b;
                    if (this.Si <= this.KL) {
                        break Label_0044;
                    }
                }
                b2 = true;
            }
            n.a(b2, (Object)"End time should be later than start time.");
            if (this.Tf == null) {
                final StringBuilder sb = new StringBuilder();
                String mName;
                if (this.mName == null) {
                    mName = "";
                }
                else {
                    mName = this.mName;
                }
                this.Tf = sb.append(mName).append(this.KL).toString();
            }
            return new Session(this, null);
        }
        
        public Builder setActivity(final int n) {
            this.Sv = FitnessActivities.cw(n);
            return this;
        }
        
        public Builder setDescription(final String tg) {
            n.b(tg.length() <= 1000, "Session description cannot exceed %d characters", 1000);
            this.Tg = tg;
            return this;
        }
        
        public Builder setEndTimeMillis(final long si) {
            n.a(si >= 0L, (Object)"End time should be positive.");
            this.Si = si;
            return this;
        }
        
        public Builder setIdentifier(final String tf) {
            this.Tf = tf;
            return this;
        }
        
        public Builder setName(final String mName) {
            n.b(mName.length() <= 100, "Session name cannot exceed %d characters", 100);
            this.mName = mName;
            return this;
        }
        
        public Builder setStartTimeMillis(final long kl) {
            n.a(kl > 0L, (Object)"Start time should be positive.");
            this.KL = kl;
            return this;
        }
    }
}
