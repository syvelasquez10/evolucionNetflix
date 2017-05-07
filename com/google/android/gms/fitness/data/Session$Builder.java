// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.data;

import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.common.internal.n;

public class Session$Builder
{
    private long KL;
    private a SJ;
    private long Si;
    private int Sv;
    private String Tf;
    private String Tg;
    private String mName;
    
    public Session$Builder() {
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
    
    public Session$Builder setActivity(final int n) {
        this.Sv = FitnessActivities.cw(n);
        return this;
    }
    
    public Session$Builder setDescription(final String tg) {
        n.b(tg.length() <= 1000, "Session description cannot exceed %d characters", 1000);
        this.Tg = tg;
        return this;
    }
    
    public Session$Builder setEndTimeMillis(final long si) {
        n.a(si >= 0L, (Object)"End time should be positive.");
        this.Si = si;
        return this;
    }
    
    public Session$Builder setIdentifier(final String tf) {
        this.Tf = tf;
        return this;
    }
    
    public Session$Builder setName(final String mName) {
        n.b(mName.length() <= 100, "Session name cannot exceed %d characters", 100);
        this.mName = mName;
        return this;
    }
    
    public Session$Builder setStartTimeMillis(final long kl) {
        n.a(kl > 0L, (Object)"Start time should be positive.");
        this.KL = kl;
        return this;
    }
}
