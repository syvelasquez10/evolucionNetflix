// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import com.google.android.gms.ads.internal.client.zzl;
import android.content.Context;
import android.widget.ImageButton;
import com.google.android.gms.internal.zzgr;
import android.view.View$OnClickListener;
import android.widget.FrameLayout;

@zzgr
public class zzm extends FrameLayout implements View$OnClickListener
{
    private final ImageButton zzBW;
    private final zzo zzBX;
    
    public zzm(final Context context, int zzb, final zzo zzBX) {
        super(context);
        this.zzBX = zzBX;
        this.setOnClickListener((View$OnClickListener)this);
        (this.zzBW = new ImageButton(context)).setImageResource(17301527);
        this.zzBW.setBackgroundColor(0);
        this.zzBW.setOnClickListener((View$OnClickListener)this);
        this.zzBW.setPadding(0, 0, 0, 0);
        this.zzBW.setContentDescription((CharSequence)"Interstitial close button");
        zzb = zzl.zzcF().zzb(context, zzb);
        this.addView((View)this.zzBW, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(zzb, zzb, 17));
    }
    
    public void onClick(final View view) {
        if (this.zzBX != null) {
            this.zzBX.zzeE();
        }
    }
    
    public void zza(final boolean b, final boolean b2) {
        if (!b2) {
            this.zzBW.setVisibility(0);
            return;
        }
        if (b) {
            this.zzBW.setVisibility(4);
            return;
        }
        this.zzBW.setVisibility(8);
    }
}
