// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Map;

final class zzdf$6 implements zzdg
{
    @Override
    public void zza(final zzip zzip, final Map<String, String> map) {
        final zzd zzgQ = zzip.zzgQ();
        if (zzgQ != null) {
            zzgQ.close();
            return;
        }
        final zzd zzgR = zzip.zzgR();
        if (zzgR != null) {
            zzgR.close();
            return;
        }
        zzb.zzaE("A GMSG tried to close something that wasn't an overlay.");
    }
}
