// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import com.google.android.gms.ads.internal.util.client.zza;
import java.util.ArrayList;
import java.util.List;

class zzii
{
    private final Object zzIJ;
    private final List<Runnable> zzIK;
    private final List<Runnable> zzIL;
    private boolean zzIM;
    
    public zzii() {
        this.zzIJ = new Object();
        this.zzIK = new ArrayList<Runnable>();
        this.zzIL = new ArrayList<Runnable>();
        this.zzIM = false;
    }
    
    private void zzd(final Runnable runnable) {
        zzht.zza(runnable);
    }
    
    private void zze(final Runnable runnable) {
        zza.zzIy.post(runnable);
    }
    
    public void zzgK() {
        synchronized (this.zzIJ) {
            if (this.zzIM) {
                return;
            }
            final Iterator<Runnable> iterator = this.zzIK.iterator();
            while (iterator.hasNext()) {
                this.zzd(iterator.next());
            }
        }
        final Iterator<Runnable> iterator2 = this.zzIL.iterator();
        while (iterator2.hasNext()) {
            this.zze(iterator2.next());
        }
        this.zzIK.clear();
        this.zzIL.clear();
        this.zzIM = true;
    }
    // monitorexit(o)
}
