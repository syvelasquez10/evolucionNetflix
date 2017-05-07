// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzad;

class zzcc extends zzak
{
    private static final String ID;
    private static final String zzaRg;
    private static final String zzaRh;
    
    static {
        ID = zzad.zzbB.toString();
        zzaRg = zzae.zzfP.toString();
        zzaRh = zzae.zzfN.toString();
    }
    
    public zzcc() {
        super(zzcc.ID, new String[0]);
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        final zzag$zza zzag$zza = map.get(zzcc.zzaRg);
        final zzag$zza zzag$zza2 = map.get(zzcc.zzaRh);
        Label_0118: {
            if (zzag$zza == null || zzag$zza == zzdf.zzBg() || zzag$zza2 == null || zzag$zza2 == zzdf.zzBg()) {
                break Label_0118;
            }
            final zzde zzh = zzdf.zzh(zzag$zza);
            final zzde zzh2 = zzdf.zzh(zzag$zza2);
            if (zzh == zzdf.zzBe() || zzh2 == zzdf.zzBe()) {
                break Label_0118;
            }
            final double doubleValue = zzh.doubleValue();
            final double doubleValue2 = zzh2.doubleValue();
            if (doubleValue > doubleValue2) {
                break Label_0118;
            }
            return zzdf.zzK(Math.round((doubleValue2 - doubleValue) * Math.random() + doubleValue));
        }
        final double doubleValue2 = 2.147483647E9;
        final double doubleValue = 0.0;
        return zzdf.zzK(Math.round((doubleValue2 - doubleValue) * Math.random() + doubleValue));
    }
    
    @Override
    public boolean zzzx() {
        return false;
    }
}
