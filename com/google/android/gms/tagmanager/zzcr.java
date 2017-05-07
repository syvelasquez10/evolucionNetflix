// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Build$VERSION;
import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzad;

class zzcr extends zzak
{
    private static final String ID;
    
    static {
        ID = zzad.zzbF.toString();
    }
    
    public zzcr() {
        super(zzcr.ID, new String[0]);
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        return zzdf.zzK(Build$VERSION.SDK_INT);
    }
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
