// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Map;
import com.google.android.gms.internal.zzad;
import com.google.android.gms.internal.zzag$zza;

class zzbz extends zzak
{
    private static final String ID;
    private static final zzag$zza zzaQV;
    
    static {
        ID = zzad.zzbA.toString();
        zzaQV = zzdf.zzK("Android");
    }
    
    public zzbz() {
        super(zzbz.ID, new String[0]);
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        return zzbz.zzaQV;
    }
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
