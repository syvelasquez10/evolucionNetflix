// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Iterator;
import java.util.Map;
import java.util.List;
import com.google.android.gms.internal.zzag$zza;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzad;

class zzx extends zzdd
{
    private static final String ID;
    private static final String VALUE;
    private static final String zzaPT;
    private final DataLayer zzaOT;
    
    static {
        ID = zzad.zzbS.toString();
        VALUE = zzae.zzhF.toString();
        zzaPT = zzae.zzdW.toString();
    }
    
    public zzx(final DataLayer zzaOT) {
        super(zzx.ID, new String[] { zzx.VALUE });
        this.zzaOT = zzaOT;
    }
    
    private void zza(final zzag$zza zzag$zza) {
        if (zzag$zza != null && zzag$zza != zzdf.zzBa()) {
            final String zzg = zzdf.zzg(zzag$zza);
            if (zzg != zzdf.zzBf()) {
                this.zzaOT.zzeC(zzg);
            }
        }
    }
    
    private void zzb(final zzag$zza zzag$zza) {
        if (zzag$zza != null && zzag$zza != zzdf.zzBa()) {
            final Object zzl = zzdf.zzl(zzag$zza);
            if (zzl instanceof List) {
                for (final Map<String, Object> next : (List<Object>)zzl) {
                    if (next instanceof Map) {
                        this.zzaOT.push(next);
                    }
                }
            }
        }
    }
    
    @Override
    public void zzI(final Map<String, zzag$zza> map) {
        this.zzb(map.get(zzx.VALUE));
        this.zza(map.get(zzx.zzaPT));
    }
}
