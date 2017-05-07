// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import android.content.Context;
import com.google.android.gms.internal.zzad;

class zzc extends zzak
{
    private static final String ID;
    private final zza zzaOI;
    
    static {
        ID = zzad.zzbg.toString();
    }
    
    public zzc(final Context context) {
        this(zza.zzaL(context));
    }
    
    zzc(final zza zzaOI) {
        super(zzc.ID, new String[0]);
        this.zzaOI = zzaOI;
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        return zzdf.zzK(!this.zzaOI.isLimitAdTrackingEnabled());
    }
    
    @Override
    public boolean zzzx() {
        return false;
    }
}
