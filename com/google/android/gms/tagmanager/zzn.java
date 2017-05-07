// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzae;
import com.google.android.gms.internal.zzad;

class zzn extends zzak
{
    private static final String ID;
    private static final String VALUE;
    
    static {
        ID = zzad.zzbl.toString();
        VALUE = zzae.zzhF.toString();
    }
    
    public zzn() {
        super(zzn.ID, new String[] { zzn.VALUE });
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        return map.get(zzn.VALUE);
    }
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
