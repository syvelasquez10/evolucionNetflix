// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.view.ViewParent;
import android.view.Window;
import android.view.ViewGroup;
import com.google.android.gms.internal.zzja$zza;
import com.google.android.gms.internal.zzfi;
import com.google.android.gms.internal.zzdo;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.internal.zzan;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import java.util.Collections;
import android.view.View;
import com.google.android.gms.internal.zzby;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.os.Bundle;
import android.graphics.Color;
import com.google.android.gms.internal.zziz;
import android.webkit.WebChromeClient$CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.app.Activity;
import com.google.android.gms.internal.zzfk$zza;
import android.graphics.Bitmap;
import com.google.android.gms.internal.zzid;
import android.content.Context;
import com.google.android.gms.ads.internal.zzp;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzhz;

@zzgr
class zzd$zzd extends zzhz
{
    final /* synthetic */ zzd zzBv;
    
    private zzd$zzd(final zzd zzBv) {
        this.zzBv = zzBv;
    }
    
    @Override
    public void zzbn() {
        final Bitmap zzg = zzp.zzbv().zzg((Context)this.zzBv.mActivity, this.zzBv.zzBi.zzBM.zzpv);
        if (zzg != null) {
            zzid.zzIE.post((Runnable)new zzd$zzd$1(this, zzp.zzbx().zza((Context)this.zzBv.mActivity, zzg, this.zzBv.zzBi.zzBM.zzpw, this.zzBv.zzBi.zzBM.zzpx)));
        }
    }
}
