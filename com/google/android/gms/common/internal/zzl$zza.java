// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.Intent;
import android.content.ComponentName;

final class zzl$zza
{
    private final ComponentName zzaaN;
    private final String zzuO;
    
    public zzl$zza(final String s) {
        this.zzuO = zzu.zzcj(s);
        this.zzaaN = null;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzl$zza)) {
                return false;
            }
            final zzl$zza zzl$zza = (zzl$zza)o;
            if (!zzt.equal(this.zzuO, zzl$zza.zzuO) || !zzt.equal(this.zzaaN, zzl$zza.zzaaN)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return zzt.hashCode(this.zzuO, this.zzaaN);
    }
    
    @Override
    public String toString() {
        if (this.zzuO == null) {
            return this.zzaaN.flattenToString();
        }
        return this.zzuO;
    }
    
    public Intent zznT() {
        if (this.zzuO != null) {
            return new Intent(this.zzuO).setPackage("com.google.android.gms");
        }
        return new Intent().setComponent(this.zzaaN);
    }
}
