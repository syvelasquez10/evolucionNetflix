// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzad;

class zzq extends zzak
{
    private static final String ID;
    private final String zzWs;
    
    static {
        ID = zzad.zzbo.toString();
    }
    
    public zzq(final String zzWs) {
        super(zzq.ID, new String[0]);
        this.zzWs = zzWs;
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        if (this.zzWs == null) {
            return zzdf.zzBg();
        }
        return zzdf.zzK(this.zzWs);
    }
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
