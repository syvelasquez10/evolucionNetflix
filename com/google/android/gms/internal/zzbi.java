// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Iterator;
import java.util.List;

@zzgr
public class zzbi
{
    private final Object zzpd;
    private int zzrX;
    private List<zzbh> zzrY;
    
    public boolean zza(final zzbh zzbh) {
        synchronized (this.zzpd) {
            return this.zzrY.contains(zzbh);
        }
    }
    
    public boolean zzb(final zzbh zzbh) {
        synchronized (this.zzpd) {
            final Iterator<zzbh> iterator = this.zzrY.iterator();
            while (iterator.hasNext()) {
                final zzbh zzbh2 = iterator.next();
                if (zzbh != zzbh2 && zzbh2.zzcm().equals(zzbh.zzcm())) {
                    iterator.remove();
                    return true;
                }
            }
            return false;
        }
    }
    
    public void zzc(final zzbh zzbh) {
        synchronized (this.zzpd) {
            if (this.zzrY.size() >= 10) {
                zzb.zzaF("Queue is full, current size = " + this.zzrY.size());
                this.zzrY.remove(0);
            }
            zzbh.zzg(this.zzrX++);
            this.zzrY.add(zzbh);
        }
    }
}
