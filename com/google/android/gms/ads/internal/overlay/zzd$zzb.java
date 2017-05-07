// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.view.MotionEvent;
import android.content.Context;
import com.google.android.gms.internal.zzif;
import com.google.android.gms.internal.zzgr;
import android.widget.RelativeLayout;

@zzgr
final class zzd$zzb extends RelativeLayout
{
    zzif zzqQ;
    
    public zzd$zzb(final Context context, final String s) {
        super(context);
        this.zzqQ = new zzif(context, s);
    }
    
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        this.zzqQ.zze(motionEvent);
        return false;
    }
}
