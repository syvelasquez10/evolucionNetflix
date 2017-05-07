// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzad;

class zzv extends zzak
{
    private static final String ID;
    private static final String NAME;
    private static final String zzaPI;
    private final DataLayer zzaOT;
    
    static {
        ID = zzad.zzbn.toString();
        NAME = zzae.zzfR.toString();
        zzaPI = zzae.zzeC.toString();
    }
    
    public zzv(final DataLayer zzaOT) {
        super(zzv.ID, new String[] { zzv.NAME });
        this.zzaOT = zzaOT;
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        final Object value = this.zzaOT.get(zzdf.zzg(map.get(zzv.NAME)));
        if (value != null) {
            return zzdf.zzK(value);
        }
        final zzag$zza zzag$zza = map.get(zzv.zzaPI);
        if (zzag$zza != null) {
            return zzag$zza;
        }
        return zzdf.zzBg();
    }
    
    @Override
    public boolean zzzx() {
        return false;
    }
}
