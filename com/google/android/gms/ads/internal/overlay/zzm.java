// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.overlay;

import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import com.google.android.gms.ads.internal.client.zzk;
import android.content.Context;
import android.widget.ImageButton;
import com.google.android.gms.internal.zzgk;
import android.view.View$OnClickListener;
import android.widget.FrameLayout;

@zzgk
public class zzm extends FrameLayout implements View$OnClickListener
{
    private final ImageButton zzBk;
    private final zzo zzBl;
    
    public zzm(final Context context, int zzb, final zzo zzBl) {
        super(context);
        this.zzBl = zzBl;
        this.setOnClickListener((View$OnClickListener)this);
        (this.zzBk = new ImageButton(context)).setImageResource(17301527);
        this.zzBk.setBackgroundColor(0);
        this.zzBk.setOnClickListener((View$OnClickListener)this);
        this.zzBk.setPadding(0, 0, 0, 0);
        this.zzBk.setContentDescription((CharSequence)"Interstitial close button");
        zzb = zzk.zzcE().zzb(context, zzb);
        this.addView((View)this.zzBk, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(zzb, zzb, 17));
    }
    
    public void onClick(final View view) {
        if (this.zzBl != null) {
            this.zzBl.zzey();
        }
    }
    
    public void zza(final boolean b, final boolean b2) {
        if (!b2) {
            this.zzBk.setVisibility(0);
            return;
        }
        if (b) {
            this.zzBk.setVisibility(4);
            return;
        }
        this.zzBk.setVisibility(8);
    }
}
