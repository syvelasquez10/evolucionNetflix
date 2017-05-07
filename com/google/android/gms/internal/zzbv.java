// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

@zzgk
public class zzbv
{
    private final Collection<zzbu> zztR;
    private final Collection<zzbu<String>> zztS;
    private final Collection<zzbu<String>> zztT;
    
    public zzbv() {
        this.zztR = new ArrayList<zzbu>();
        this.zztS = new ArrayList<zzbu<String>>();
        this.zztT = new ArrayList<zzbu<String>>();
    }
    
    public void zza(final zzbu zzbu) {
        this.zztR.add(zzbu);
    }
    
    public void zzb(final zzbu<String> zzbu) {
        this.zztS.add(zzbu);
    }
    
    public void zzc(final zzbu<String> zzbu) {
        this.zztT.add(zzbu);
    }
    
    public List<String> zzde() {
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<zzbu<String>> iterator = this.zztS.iterator();
        while (iterator.hasNext()) {
            final String s = iterator.next().get();
            if (s != null) {
                list.add(s);
            }
        }
        return list;
    }
}
