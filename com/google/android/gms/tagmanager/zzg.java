// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzad;
import android.content.Context;

class zzg extends zzak
{
    private static final String ID;
    private final Context mContext;
    
    static {
        ID = zzad.zzbi.toString();
    }
    
    public zzg(final Context mContext) {
        super(zzg.ID, new String[0]);
        this.mContext = mContext;
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        try {
            final PackageManager packageManager = this.mContext.getPackageManager();
            return zzdf.zzK(packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.mContext.getPackageName(), 0)).toString());
        }
        catch (PackageManager$NameNotFoundException ex) {
            zzbg.zzb("App name is not found.", (Throwable)ex);
            return zzdf.zzBg();
        }
    }
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
