// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;

class zzcp$zzb
{
    private zzbw<zzag$zza> zzaRP;
    private zzag$zza zzaRQ;
    
    public zzcp$zzb(final zzbw<zzag$zza> zzaRP, final zzag$zza zzaRQ) {
        this.zzaRP = zzaRP;
        this.zzaRQ = zzaRQ;
    }
    
    public int getSize() {
        final int zzDw = this.zzaRP.getObject().zzDw();
        int zzDw2;
        if (this.zzaRQ == null) {
            zzDw2 = 0;
        }
        else {
            zzDw2 = this.zzaRQ.zzDw();
        }
        return zzDw2 + zzDw;
    }
    
    public zzbw<zzag$zza> zzAH() {
        return this.zzaRP;
    }
    
    public zzag$zza zzAI() {
        return this.zzaRQ;
    }
}
