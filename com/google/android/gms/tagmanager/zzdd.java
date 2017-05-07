// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.Map;

abstract class zzdd extends zzak
{
    public zzdd(final String s, final String... array) {
        super(s, array);
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        this.zzI(map);
        return zzdf.zzBg();
    }
    
    public abstract void zzI(final Map<String, zzag$zza> p0);
    
    @Override
    public boolean zzzx() {
        return false;
    }
}
