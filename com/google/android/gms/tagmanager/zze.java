// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzad;
import android.content.Context;

class zze extends zzak
{
    private static final String ID;
    private static final String zzaOJ;
    private static final String zzaOK;
    private final Context context;
    
    static {
        ID = zzad.zzbJ.toString();
        zzaOJ = zzae.zzeb.toString();
        zzaOK = zzae.zzee.toString();
    }
    
    public zze(final Context context) {
        super(zze.ID, new String[] { zze.zzaOK });
        this.context = context;
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        final zzag$zza zzag$zza = map.get(zze.zzaOK);
        if (zzag$zza == null) {
            return zzdf.zzBg();
        }
        final String zzg = zzdf.zzg(zzag$zza);
        final zzag$zza zzag$zza2 = map.get(zze.zzaOJ);
        String zzg2;
        if (zzag$zza2 != null) {
            zzg2 = zzdf.zzg(zzag$zza2);
        }
        else {
            zzg2 = null;
        }
        final String zzg3 = zzax.zzg(this.context, zzg, zzg2);
        if (zzg3 != null) {
            return zzdf.zzK(zzg3);
        }
        return zzdf.zzBg();
    }
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
