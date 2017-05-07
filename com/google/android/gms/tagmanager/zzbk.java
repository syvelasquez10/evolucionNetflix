// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.provider.Settings$Secure;
import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzad;
import android.content.Context;

class zzbk extends zzak
{
    private static final String ID;
    private final Context mContext;
    
    static {
        ID = zzad.zzbQ.toString();
    }
    
    public zzbk(final Context mContext) {
        super(zzbk.ID, new String[0]);
        this.mContext = mContext;
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        final String zzaN = this.zzaN(this.mContext);
        if (zzaN == null) {
            return zzdf.zzBg();
        }
        return zzdf.zzK(zzaN);
    }
    
    protected String zzaN(final Context context) {
        return Settings$Secure.getString(context.getContentResolver(), "android_id");
    }
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
