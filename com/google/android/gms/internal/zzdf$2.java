// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

final class zzdf$2 implements zzdg
{
    @Override
    public void zza(final zzip zzip, final Map<String, String> map) {
        if (!zzby.zzvd.get()) {
            return;
        }
        zzip.zzE(!Boolean.parseBoolean(map.get("disabled")));
    }
}
