// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzad;

class zzam extends zzbv
{
    private static final String ID;
    
    static {
        ID = zzad.zzcm.toString();
    }
    
    public zzam() {
        super(zzam.ID);
    }
    
    @Override
    protected boolean zza(final zzde zzde, final zzde zzde2, final Map<String, zzag$zza> map) {
        return zzde.zza(zzde2) >= 0;
    }
}
