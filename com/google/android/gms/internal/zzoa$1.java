// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Comparator;

class zzoa$1 implements Comparator<zzod>
{
    final /* synthetic */ zzoa zzaHP;
    
    zzoa$1(final zzoa zzaHP) {
        this.zzaHP = zzaHP;
    }
    
    public int zza(final zzod zzod, final zzod zzod2) {
        return zzod.getClass().getCanonicalName().compareTo(zzod2.getClass().getCanonicalName());
    }
}
