// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.view.ViewParent;
import com.google.android.gms.internal.zziz;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import com.google.android.gms.internal.zzgr;

@zzgr
public class zzd$zzc
{
    public final Context context;
    public final int index;
    public final ViewGroup$LayoutParams zzBw;
    public final ViewGroup zzBx;
    
    public zzd$zzc(final zziz zziz) {
        this.zzBw = zziz.getLayoutParams();
        final ViewParent parent = zziz.getParent();
        this.context = zziz.zzha();
        if (parent != null && parent instanceof ViewGroup) {
            this.zzBx = (ViewGroup)parent;
            this.index = this.zzBx.indexOfChild(zziz.getView());
            this.zzBx.removeView(zziz.getView());
            zziz.zzC(true);
            return;
        }
        throw new zzd$zza("Could not get the parent of the WebView for an overlay.");
    }
}
