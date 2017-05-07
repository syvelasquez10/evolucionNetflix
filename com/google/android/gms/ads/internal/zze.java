// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzby;
import com.google.android.gms.internal.zzgk;

@zzgk
public class zze
{
    private zze$zza zzoH;
    private boolean zzoI;
    private boolean zzoJ;
    
    public zze() {
        this.zzoJ = zzby.zzud.get();
    }
    
    public zze(final boolean zzoJ) {
        this.zzoJ = zzoJ;
    }
    
    public boolean zzbe() {
        return !this.zzoJ || this.zzoI;
    }
    
    public void zzp(final String s) {
        zzb.zzaC("Action was blocked because no click was detected.");
        if (this.zzoH != null) {
            this.zzoH.zzq(s);
        }
    }
}
