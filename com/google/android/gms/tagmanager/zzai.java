// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzad;

class zzai extends zzak
{
    private static final String ID;
    private final zzcp zzaOU;
    
    static {
        ID = zzad.zzbv.toString();
    }
    
    public zzai(final zzcp zzaOU) {
        super(zzai.ID, new String[0]);
        this.zzaOU = zzaOU;
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        final String zzAF = this.zzaOU.zzAF();
        if (zzAF == null) {
            return zzdf.zzBg();
        }
        return zzdf.zzK(zzAF);
    }
    
    @Override
    public boolean zzzx() {
        return false;
    }
}
