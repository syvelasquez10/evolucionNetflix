// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

class zze$zza extends zze
{
    List<zze> zzadb;
    
    zze$zza(final List<zze> zzadb) {
        this.zzadb = zzadb;
    }
    
    @Override
    public zze zza(final zze zze) {
        final ArrayList<zze> list = new ArrayList<zze>(this.zzadb);
        list.add(zzx.zzv(zze));
        return new zze$zza(list);
    }
    
    @Override
    public boolean zzd(final char c) {
        final Iterator<zze> iterator = this.zzadb.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().zzd(c)) {
                return true;
            }
        }
        return false;
    }
}
