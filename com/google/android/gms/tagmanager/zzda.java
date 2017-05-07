// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzad;

class zzda extends zzak
{
    private static final String ID;
    
    static {
        ID = zzad.zzbH.toString();
    }
    
    public zzda() {
        super(zzda.ID, new String[0]);
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        return zzdf.zzK(System.currentTimeMillis());
    }
    
    @Override
    public boolean zzzx() {
        return false;
    }
}
