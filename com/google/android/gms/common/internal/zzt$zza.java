// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.List;

public final class zzt$zza
{
    private final Object zzGE;
    private final List<String> zzaba;
    
    private zzt$zza(final Object o) {
        this.zzGE = zzu.zzu(o);
        this.zzaba = new ArrayList<String>();
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder(100).append(this.zzGE.getClass().getSimpleName()).append('{');
        for (int size = this.zzaba.size(), i = 0; i < size; ++i) {
            append.append(this.zzaba.get(i));
            if (i < size - 1) {
                append.append(", ");
            }
        }
        return append.append('}').toString();
    }
    
    public zzt$zza zzg(final String s, final Object o) {
        this.zzaba.add(zzu.zzu(s) + "=" + String.valueOf(o));
        return this;
    }
}
