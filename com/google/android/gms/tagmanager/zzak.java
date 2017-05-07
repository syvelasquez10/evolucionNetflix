// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Collection;
import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

abstract class zzak
{
    private final Set<String> zzaQc;
    private final String zzaQd;
    
    public zzak(String zzaQd, final String... array) {
        this.zzaQd = zzaQd;
        this.zzaQc = new HashSet<String>(array.length);
        for (int length = array.length, i = 0; i < length; ++i) {
            zzaQd = array[i];
            this.zzaQc.add(zzaQd);
        }
    }
    
    public String zzAc() {
        return this.zzaQd;
    }
    
    public Set<String> zzAd() {
        return this.zzaQc;
    }
    
    public abstract zzag$zza zzG(final Map<String, zzag$zza> p0);
    
    boolean zzf(final Set<String> set) {
        return set.containsAll(this.zzaQc);
    }
    
    public abstract boolean zzzx();
}
