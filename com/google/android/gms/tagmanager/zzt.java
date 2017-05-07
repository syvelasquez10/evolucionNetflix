// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Iterator;
import java.util.HashMap;
import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzad;

class zzt extends zzak
{
    private static final String ID;
    private static final String zzaOL;
    private static final String zzaPx;
    private final zzt$zza zzaPy;
    
    static {
        ID = zzad.zzbw.toString();
        zzaPx = zzae.zzfk.toString();
        zzaOL = zzae.zzdn.toString();
    }
    
    public zzt(final zzt$zza zzaPy) {
        super(zzt.ID, new String[] { zzt.zzaPx });
        this.zzaPy = zzaPy;
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        final String zzg = zzdf.zzg(map.get(zzt.zzaPx));
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        final zzag$zza zzag$zza = map.get(zzt.zzaOL);
        if (zzag$zza != null) {
            final Object zzl = zzdf.zzl(zzag$zza);
            if (!(zzl instanceof Map)) {
                zzbg.zzaE("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return zzdf.zzBg();
            }
            for (final Map.Entry<Object, V> entry : ((Map<Object, V>)zzl).entrySet()) {
                hashMap.put(entry.getKey().toString(), entry.getValue());
            }
        }
        try {
            return zzdf.zzK(this.zzaPy.zzd(zzg, hashMap));
        }
        catch (Exception ex) {
            zzbg.zzaE("Custom macro/tag " + zzg + " threw exception " + ex.getMessage());
            return zzdf.zzBg();
        }
    }
    
    @Override
    public boolean zzzx() {
        return false;
    }
}
