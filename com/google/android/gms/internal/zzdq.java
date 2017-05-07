// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import com.google.android.gms.ads.internal.zzp;
import java.util.LinkedList;
import java.util.List;

@zzgk
public class zzdq implements Iterable<zzdp>
{
    private final List<zzdp> zzxu;
    
    public zzdq() {
        this.zzxu = new LinkedList<zzdp>();
    }
    
    private zzdp zzc(final zzip zzip) {
        for (final zzdp zzdp : zzp.zzbK()) {
            if (zzdp.zzoL == zzip) {
                return zzdp;
            }
        }
        return null;
    }
    
    @Override
    public Iterator<zzdp> iterator() {
        return this.zzxu.iterator();
    }
    
    public void zza(final zzdp zzdp) {
        this.zzxu.add(zzdp);
    }
    
    public boolean zza(final zzip zzip) {
        final zzdp zzc = this.zzc(zzip);
        if (zzc != null) {
            zzc.zzxr.abort();
            return true;
        }
        return false;
    }
    
    public void zzb(final zzdp zzdp) {
        this.zzxu.remove(zzdp);
    }
    
    public boolean zzb(final zzip zzip) {
        return this.zzc(zzip) != null;
    }
}
