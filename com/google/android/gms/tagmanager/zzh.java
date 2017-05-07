// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzad;
import android.content.Context;

class zzh extends zzak
{
    private static final String ID;
    private final Context mContext;
    
    static {
        ID = zzad.zzbj.toString();
    }
    
    public zzh(final Context mContext) {
        super(zzh.ID, new String[0]);
        this.mContext = mContext;
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        try {
            return zzdf.zzK(this.mContext.getPackageManager().getPackageInfo(this.mContext.getPackageName(), 0).versionCode);
        }
        catch (PackageManager$NameNotFoundException ex) {
            zzbg.e("Package name " + this.mContext.getPackageName() + " not found. " + ex.getMessage());
            return zzdf.zzBg();
        }
    }
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
