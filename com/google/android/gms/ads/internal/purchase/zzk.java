// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import android.content.Intent;
import com.google.android.gms.internal.zzgr;

@zzgr
public class zzk
{
    private final String zztG;
    
    public boolean zza(final String s, final int n, final Intent intent) {
        if (s != null && intent != null) {
            final String zze = zzp.zzbF().zze(intent);
            final String zzf = zzp.zzbF().zzf(intent);
            if (zze != null && zzf != null) {
                if (!s.equals(zzp.zzbF().zzao(zze))) {
                    zzb.zzaH("Developer payload not match.");
                    return false;
                }
                if (this.zztG != null && !zzl.zzc(this.zztG, zze, zzf)) {
                    zzb.zzaH("Fail to verify signature.");
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    
    public String zzfq() {
        return zzp.zzbv().zzgD();
    }
}
