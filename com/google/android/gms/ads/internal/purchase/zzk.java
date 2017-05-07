// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.purchase;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzp;
import android.content.Intent;
import com.google.android.gms.internal.zzgk;

@zzgk
public class zzk
{
    private final String zztn;
    
    public boolean zza(final String s, final int n, final Intent intent) {
        if (s != null && intent != null) {
            final String zze = zzp.zzbH().zze(intent);
            final String zzf = zzp.zzbH().zzf(intent);
            if (zze != null && zzf != null) {
                if (!s.equals(zzp.zzbH().zzal(zze))) {
                    zzb.zzaE("Developer payload not match.");
                    return false;
                }
                if (this.zztn != null && !zzl.zzc(this.zztn, zze, zzf)) {
                    zzb.zzaE("Fail to verify signature.");
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    
    public String zzfk() {
        return zzp.zzbx().zzgs();
    }
}
