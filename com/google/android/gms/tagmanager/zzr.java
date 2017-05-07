// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzad;

class zzr extends zzcz
{
    private static final String ID;
    
    static {
        ID = zzad.zzch.toString();
    }
    
    public zzr() {
        super(zzr.ID);
    }
    
    @Override
    protected boolean zza(final String s, final String s2, final Map<String, zzag$zza> map) {
        return s.contains(s2);
    }
}
