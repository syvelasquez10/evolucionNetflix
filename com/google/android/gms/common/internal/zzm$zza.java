// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.Intent;
import android.content.ComponentName;

final class zzm$zza
{
    private final String zzOj;
    private final ComponentName zzadY;
    
    public zzm$zza(final String s) {
        this.zzOj = zzx.zzcs(s);
        this.zzadY = null;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzm$zza)) {
                return false;
            }
            final zzm$zza zzm$zza = (zzm$zza)o;
            if (!zzw.equal(this.zzOj, zzm$zza.zzOj) || !zzw.equal(this.zzadY, zzm$zza.zzadY)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzOj, this.zzadY);
    }
    
    @Override
    public String toString() {
        if (this.zzOj == null) {
            return this.zzadY.flattenToString();
        }
        return this.zzOj;
    }
    
    public Intent zzoK() {
        if (this.zzOj != null) {
            return new Intent(this.zzOj).setPackage("com.google.android.gms");
        }
        return new Intent().setComponent(this.zzadY);
    }
}
