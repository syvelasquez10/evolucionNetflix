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
import com.google.android.gms.ads.internal.zzp;
import android.content.Context;
import com.google.android.gms.internal.zzby;
import com.google.android.gms.ads.internal.util.client.zzb;
import android.os.Bundle;
import android.graphics.Color;
import com.google.android.gms.internal.zziz;
import android.webkit.WebChromeClient$CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.app.Activity;
import com.google.android.gms.internal.zzgr;
import com.google.android.gms.internal.zzfk$zza;
import android.graphics.drawable.Drawable;

class zzd$zzd$1 implements Runnable
{
    final /* synthetic */ Drawable zzBy;
    final /* synthetic */ zzd$zzd zzBz;
    
    zzd$zzd$1(final zzd$zzd zzBz, final Drawable zzBy) {
        this.zzBz = zzBz;
        this.zzBy = zzBy;
    }
    
    @Override
    public void run() {
        this.zzBz.zzBv.mActivity.getWindow().setBackgroundDrawable(this.zzBy);
    }
}
