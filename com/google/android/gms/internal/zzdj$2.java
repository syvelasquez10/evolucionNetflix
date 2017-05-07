// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

final class zzdj$2 implements zzdk
{
    @Override
    public void zza(final zziz zziz, final Map<String, String> map) {
        if (!zzby.zzvs.get()) {
            return;
        }
        zziz.zzE(!Boolean.parseBoolean(map.get("disabled")));
    }
}
