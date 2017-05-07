// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Iterator;
import java.util.List;

@zzgk
public class zzbi
{
    private final Object zzpc;
    private int zzrM;
    private List<zzbh> zzrN;
    
    public boolean zza(final zzbh zzbh) {
        synchronized (this.zzpc) {
            return this.zzrN.contains(zzbh);
        }
    }
    
    public boolean zzb(final zzbh zzbh) {
        synchronized (this.zzpc) {
            final Iterator<zzbh> iterator = this.zzrN.iterator();
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
        synchronized (this.zzpc) {
            if (this.zzrN.size() >= 10) {
                zzb.zzaC("Queue is full, current size = " + this.zzrN.size());
                this.zzrN.remove(0);
            }
            zzbh.zzg(this.zzrM++);
            this.zzrN.add(zzbh);
        }
    }
}
