// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.view.ViewParent;
import android.view.View;
import com.google.android.gms.internal.zzip;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import com.google.android.gms.internal.zzgk;

@zzgk
public class zzd$zzc
{
    public final Context context;
    public final int index;
    public final ViewGroup$LayoutParams zzAM;
    public final ViewGroup zzAN;
    
    public zzd$zzc(final zzip zzip) {
        this.zzAM = zzip.getLayoutParams();
        final ViewParent parent = zzip.getParent();
        this.context = zzip.zzgO();
        if (parent != null && parent instanceof ViewGroup) {
            this.zzAN = (ViewGroup)parent;
            this.index = this.zzAN.indexOfChild((View)zzip.getWebView());
            this.zzAN.removeView((View)zzip.getWebView());
            zzip.zzC(true);
            return;
        }
        throw new zzd$zza("Could not get the parent of the WebView for an overlay.");
    }
}
