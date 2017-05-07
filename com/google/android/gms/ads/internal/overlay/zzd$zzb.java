// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.view.MotionEvent;
import android.content.Context;
import com.google.android.gms.internal.zzhw;
import com.google.android.gms.internal.zzgk;
import android.widget.RelativeLayout;

@zzgk
final class zzd$zzb extends RelativeLayout
{
    zzhw zzqF;
    
    public zzd$zzb(final Context context, final String s) {
        super(context);
        this.zzqF = new zzhw(context, s);
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        this.zzqF.zze(motionEvent);
        return false;
    }
}
