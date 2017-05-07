// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import com.google.android.gms.ads.internal.zzp;
import java.util.LinkedList;
import java.util.List;

@zzgr
public class zzdu implements Iterable<zzdt>
{
    private final List<zzdt> zzyb;
    
    public zzdu() {
        this.zzyb = new LinkedList<zzdt>();
    }
    
    private zzdt zzc(final zziz zziz) {
        for (final zzdt zzdt : zzp.zzbI()) {
            if (zzdt.zzoM == zziz) {
                return zzdt;
            }
        }
        return null;
    }
    
    @Override
    public Iterator<zzdt> iterator() {
        return this.zzyb.iterator();
    }
    
    public void zza(final zzdt zzdt) {
        this.zzyb.add(zzdt);
    }
    
    public boolean zza(final zziz zziz) {
        final zzdt zzc = this.zzc(zziz);
        if (zzc != null) {
            zzc.zzxY.abort();
            return true;
        }
        return false;
    }
    
    public void zzb(final zzdt zzdt) {
        this.zzyb.remove(zzdt);
    }
    
    public boolean zzb(final zziz zziz) {
        return this.zzc(zziz) != null;
    }
}
