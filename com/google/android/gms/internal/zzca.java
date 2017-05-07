// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

@zzgr
public class zzca
{
    BlockingQueue<zzcg> zzvD;
    Map<String, zzcd> zzvG;
    
    public zzcd zzR(final String s) {
        final zzcd zzcd = this.zzvG.get(s);
        if (zzcd != null) {
            return zzcd;
        }
        return com.google.android.gms.internal.zzcd.zzvK;
    }
    
    public boolean zza(final zzcg zzcg) {
        return this.zzvD.offer(zzcg);
    }
}
