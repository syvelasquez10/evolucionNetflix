// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.view.WindowManager;
import android.util.DisplayMetrics;
import com.google.android.gms.internal.zzag$zza;
import java.util.Map;
import com.google.android.gms.internal.zzad;
import android.content.Context;

class zzcg extends zzak
{
    private static final String ID;
    private final Context mContext;
    
    static {
        ID = zzad.zzbD.toString();
    }
    
    public zzcg(final Context mContext) {
        super(zzcg.ID, new String[0]);
        this.mContext = mContext;
    }
    
    @Override
    public zzag$zza zzG(final Map<String, zzag$zza> map) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return zzdf.zzK(displayMetrics.widthPixels + "x" + displayMetrics.heightPixels);
    }
    
    @Override
    public boolean zzzx() {
        return true;
    }
}
