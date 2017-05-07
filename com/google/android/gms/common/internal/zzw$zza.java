// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.List;

public final class zzw$zza
{
    private final Object zzIr;
    private final List<String> zzael;
    
    private zzw$zza(final Object o) {
        this.zzIr = zzx.zzv(o);
        this.zzael = new ArrayList<String>();
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder(100).append(this.zzIr.getClass().getSimpleName()).append('{');
        for (int size = this.zzael.size(), i = 0; i < size; ++i) {
            append.append(this.zzael.get(i));
            if (i < size - 1) {
                append.append(", ");
            }
        }
        return append.append('}').toString();
    }
    
    public zzw$zza zzg(final String s, final Object o) {
        this.zzael.add(zzx.zzv(s) + "=" + String.valueOf(o));
        return this;
    }
}
