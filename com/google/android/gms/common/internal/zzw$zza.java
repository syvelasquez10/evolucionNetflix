// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.List;

public final class zzw$zza
{
    private final Object zzJm;
    private final List<String> zzago;
    
    private zzw$zza(final Object o) {
        this.zzJm = zzx.zzw(o);
        this.zzago = new ArrayList<String>();
    }
    
    @Override
    public String toString() {
        final StringBuilder append = new StringBuilder(100).append(this.zzJm.getClass().getSimpleName()).append('{');
        for (int size = this.zzago.size(), i = 0; i < size; ++i) {
            append.append(this.zzago.get(i));
            if (i < size - 1) {
                append.append(", ");
            }
        }
        return append.append('}').toString();
    }
    
    public zzw$zza zzg(final String s, final Object o) {
        this.zzago.add(zzx.zzw(s) + "=" + String.valueOf(o));
        return this;
    }
}
