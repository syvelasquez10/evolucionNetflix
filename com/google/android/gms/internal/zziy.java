// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewGroup$LayoutParams;
import com.google.android.gms.common.internal.zzx;
import android.view.ViewGroup;
import com.google.android.gms.ads.internal.overlay.zzk;
import android.content.Context;

public class zziy
{
    private final Context mContext;
    private zzk zzCo;
    private final ViewGroup zzJT;
    private final zziz zzoM;
    
    public zziy(final Context context, final ViewGroup viewGroup, final zziz zziz) {
        this(context, viewGroup, zziz, null);
    }
    
    zziy(final Context mContext, final ViewGroup zzJT, final zziz zzoM, final zzk zzCo) {
        this.mContext = mContext;
        this.zzJT = zzJT;
        this.zzoM = zzoM;
        this.zzCo = zzCo;
    }
    
    public void onDestroy() {
        zzx.zzci("onDestroy must be called from the UI thread.");
        if (this.zzCo != null) {
            this.zzCo.destroy();
        }
    }
    
    public void zza(final int n, final int n2, final int n3, final int n4, final int n5) {
        if (this.zzCo != null) {
            return;
        }
        zzcc.zza(this.zzoM.zzhn().zzdm(), this.zzoM.zzhm(), "vpr");
        this.zzCo = new zzk(this.mContext, this.zzoM, n5, this.zzoM.zzhn().zzdm(), zzcc.zzb(this.zzoM.zzhn().zzdm()));
        this.zzJT.addView((View)this.zzCo, 0, new ViewGroup$LayoutParams(-1, -1));
        this.zzCo.zzd(n, n2, n3, n4);
        this.zzoM.zzhe().zzF(false);
    }
    
    public void zze(final int n, final int n2, final int n3, final int n4) {
        zzx.zzci("The underlay may only be modified from the UI thread.");
        if (this.zzCo != null) {
            this.zzCo.zzd(n, n2, n3, n4);
        }
    }
    
    public zzk zzgX() {
        zzx.zzci("getAdVideoUnderlay must be called from the UI thread.");
        return this.zzCo;
    }
}
