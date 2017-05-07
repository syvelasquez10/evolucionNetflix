// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzad;

class zzdh extends zzak
{
    private static final String ID;
    private static final String zzaPY;
    
    static {
        ID = zzad.zzbU.toString();
        zzaPY = zzae.zzdz.toString();
    }
    
    public zzdh() {
        super(zzdh.ID, new String[] { zzdh.zzaPY });
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        return zzdf.zzK(zzdf.zzg(map.get(zzdh.zzaPY)).toUpperCase());
    }
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
