// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzad;
import android.content.Context;

class zzaw extends zzak
{
    private static final String ID;
    private static final String zzaOJ;
    private final Context context;
    
    static {
        ID = zzad.zzbO.toString();
        zzaOJ = zzae.zzeb.toString();
    }
    
    public zzaw(final Context context) {
        super(zzaw.ID, new String[0]);
        this.context = context;
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        String zzg;
        if (map.get(zzaw.zzaOJ) != null) {
            zzg = zzdf.zzg(map.get(zzaw.zzaOJ));
        }
        else {
            zzg = null;
        }
        final String zzk = zzax.zzk(this.context, zzg);
        if (zzk != null) {
            return zzdf.zzK(zzk);
        }
        return zzdf.zzBg();
    }
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
