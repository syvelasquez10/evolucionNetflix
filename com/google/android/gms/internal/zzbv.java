// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

@zzgr
public class zzbv
{
    private final Collection<zzbu> zzug;
    private final Collection<zzbu<String>> zzuh;
    private final Collection<zzbu<String>> zzui;
    
    public zzbv() {
        this.zzug = new ArrayList<zzbu>();
        this.zzuh = new ArrayList<zzbu<String>>();
        this.zzui = new ArrayList<zzbu<String>>();
    }
    
    public void zza(final zzbu zzbu) {
        this.zzug.add(zzbu);
    }
    
    public void zzb(final zzbu<String> zzbu) {
        this.zzuh.add(zzbu);
    }
    
    public void zzc(final zzbu<String> zzbu) {
        this.zzui.add(zzbu);
    }
    
    public List<String> zzdf() {
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<zzbu<String>> iterator = this.zzuh.iterator();
        while (iterator.hasNext()) {
            final String s = iterator.next().get();
            if (s != null) {
                list.add(s);
            }
        }
        return list;
    }
}
