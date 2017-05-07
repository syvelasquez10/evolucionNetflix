// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import com.google.android.gms.ads.internal.util.client.zza;
import java.util.ArrayList;
import java.util.List;

class zzir
{
    private final Object zzJI;
    private final List<Runnable> zzJJ;
    private final List<Runnable> zzJK;
    private boolean zzJL;
    
    public zzir() {
        this.zzJI = new Object();
        this.zzJJ = new ArrayList<Runnable>();
        this.zzJK = new ArrayList<Runnable>();
        this.zzJL = false;
    }
    
    private void zze(final Runnable runnable) {
        zzic.zza(runnable);
    }
    
    private void zzf(final Runnable runnable) {
        zza.zzJt.post(runnable);
    }
    
    public void zzd(final Runnable runnable) {
        synchronized (this.zzJI) {
            if (this.zzJL) {
                this.zzf(runnable);
            }
            else {
                this.zzJK.add(runnable);
            }
        }
    }
    
    public void zzgV() {
        synchronized (this.zzJI) {
            if (this.zzJL) {
                return;
            }
            final Iterator<Runnable> iterator = this.zzJJ.iterator();
            while (iterator.hasNext()) {
                this.zze(iterator.next());
            }
        }
        final Iterator<Runnable> iterator2 = this.zzJK.iterator();
        while (iterator2.hasNext()) {
            this.zzf(iterator2.next());
        }
        this.zzJJ.clear();
        this.zzJK.clear();
        this.zzJL = true;
    }
    // monitorexit(o)
}
