// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Iterator;
import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import java.util.Set;
import com.google.android.gms.internal.zzae;

public abstract class zzca extends zzak
{
    private static final String zzaPY;
    private static final String zzaQW;
    
    static {
        zzaPY = zzae.zzdz.toString();
        zzaQW = zzae.zzdA.toString();
    }
    
    public zzca(final String s) {
        super(s, new String[] { zzca.zzaPY, zzca.zzaQW });
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        final Iterator<zzag$zza> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == zzdf.zzBg()) {
                return zzdf.zzK(false);
            }
        }
        final zzag$zza zzag$zza = map.get(zzca.zzaPY);
        final zzag$zza zzag$zza2 = map.get(zzca.zzaQW);
        return zzdf.zzK(zzag$zza != null && zzag$zza2 != null && this.zza(zzag$zza, zzag$zza2, map));
    }
    
    protected abstract boolean zza(final zzag$zza p0, final zzag$zza p1, final Map<String, zzag$zza> p2);
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
