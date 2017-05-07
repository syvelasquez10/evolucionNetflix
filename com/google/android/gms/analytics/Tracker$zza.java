// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzd;

class Tracker$zza extends zzd
{
    final /* synthetic */ Tracker zzKU;
    private long zzKX;
    private boolean zzKY;
    
    protected Tracker$zza(final Tracker zzKU, final zzf zzf) {
        this.zzKU = zzKU;
        super(zzf);
        this.zzKX = -1L;
    }
    
    @Override
    protected void zzhB() {
    }
    
    public boolean zzhE() {
        synchronized (this) {
            final boolean zzKY = this.zzKY;
            this.zzKY = false;
            return zzKY;
        }
    }
}
