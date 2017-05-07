// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import android.content.Context;
import com.google.android.gms.internal.zzad;

class zzb extends zzak
{
    private static final String ID;
    private final zza zzaOI;
    
    static {
        ID = zzad.zzbf.toString();
    }
    
    public zzb(final Context context) {
        this(zza.zzaL(context));
    }
    
    zzb(final zza zzaOI) {
        super(zzb.ID, new String[0]);
        this.zzaOI = zzaOI;
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        final String zzzt = this.zzaOI.zzzt();
        if (zzzt == null) {
            return zzdf.zzBg();
        }
        return zzdf.zzK(zzzt);
    }
    
    @Override
    public boolean zzzx() {
        return false;
    }
}
