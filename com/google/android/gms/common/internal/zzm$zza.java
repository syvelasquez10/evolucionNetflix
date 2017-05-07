// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.Intent;
import android.content.ComponentName;

final class zzm$zza
{
    private final String zzPp;
    private final ComponentName zzagb;
    
    public zzm$zza(final String s) {
        this.zzPp = zzx.zzcr(s);
        this.zzagb = null;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzm$zza)) {
                return false;
            }
            final zzm$zza zzm$zza = (zzm$zza)o;
            if (!zzw.equal(this.zzPp, zzm$zza.zzPp) || !zzw.equal(this.zzagb, zzm$zza.zzagb)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return zzw.hashCode(this.zzPp, this.zzagb);
    }
    
    @Override
    public String toString() {
        if (this.zzPp == null) {
            return this.zzagb.flattenToString();
        }
        return this.zzPp;
    }
    
    public Intent zzpm() {
        if (this.zzPp != null) {
            return new Intent(this.zzPp).setPackage("com.google.android.gms");
        }
        return new Intent().setComponent(this.zzagb);
    }
}
