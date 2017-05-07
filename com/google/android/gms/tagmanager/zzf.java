// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzad;
import android.content.Context;

class zzf extends zzak
{
    private static final String ID;
    private final Context mContext;
    
    static {
        ID = zzad.zzbh.toString();
    }
    
    public zzf(final Context mContext) {
        super(zzf.ID, new String[0]);
        this.mContext = mContext;
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        return zzdf.zzK(this.mContext.getPackageName());
    }
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
