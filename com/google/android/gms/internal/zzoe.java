// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.ArrayList;
import com.google.android.gms.common.internal.zzx;
import java.util.List;

public abstract class zzoe<T extends zzoe>
{
    private final zzof zzaIa;
    protected final zzob zzaIb;
    private final List<zzoc> zzaIc;
    
    protected zzoe(final zzof zzaIa, final zzlm zzlm) {
        zzx.zzv(zzaIa);
        this.zzaIa = zzaIa;
        this.zzaIc = new ArrayList<zzoc>();
        final zzob zzaIb = new zzob(this, zzlm);
        zzaIb.zzxr();
        this.zzaIb = zzaIb;
    }
    
    protected void zza(final zzob zzob) {
    }
    
    protected void zzd(final zzob zzob) {
        final Iterator<zzoc> iterator = this.zzaIc.iterator();
        while (iterator.hasNext()) {
            iterator.next().zza(this, zzob);
        }
    }
    
    public zzob zzhq() {
        final zzob zzxh = this.zzaIb.zzxh();
        this.zzd(zzxh);
        return zzxh;
    }
    
    protected zzof zzxp() {
        return this.zzaIa;
    }
    
    public zzob zzxs() {
        return this.zzaIb;
    }
    
    public List<zzoh> zzxt() {
        return this.zzaIb.zzxj();
    }
}
