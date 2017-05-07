// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzby;
import com.google.android.gms.internal.zzgr;

@zzgr
public class zze
{
    private zze$zza zzoI;
    private boolean zzoJ;
    private boolean zzoK;
    
    public zze() {
        this.zzoK = zzby.zzus.get();
    }
    
    public zze(final boolean zzoK) {
        this.zzoK = zzoK;
    }
    
    public boolean zzbe() {
        return !this.zzoK || this.zzoJ;
    }
    
    public void zzp(final String s) {
        zzb.zzaF("Action was blocked because no click was detected.");
        if (this.zzoI != null) {
            this.zzoI.zzq(s);
        }
    }
}
