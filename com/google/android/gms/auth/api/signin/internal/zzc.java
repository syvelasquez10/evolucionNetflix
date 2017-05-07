// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth.api.signin.internal;

public class zzc
{
    static int zzTo;
    private int zzTp;
    
    static {
        zzc.zzTo = 31;
    }
    
    public zzc() {
        this.zzTp = 1;
    }
    
    public zzc zzP(final boolean b) {
        final int zzTo = zzc.zzTo;
        final int zzTp = this.zzTp;
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        this.zzTp = n + zzTp * zzTo;
        return this;
    }
    
    public zzc zzl(final Object o) {
        final int zzTo = zzc.zzTo;
        final int zzTp = this.zzTp;
        int hashCode;
        if (o == null) {
            hashCode = 0;
        }
        else {
            hashCode = o.hashCode();
        }
        this.zzTp = hashCode + zzTp * zzTo;
        return this;
    }
    
    public int zzmd() {
        return this.zzTp;
    }
}
