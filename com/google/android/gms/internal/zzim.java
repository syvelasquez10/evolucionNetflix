// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzp;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.app.Activity;

public final class zzim
{
    private Activity zzJn;
    private boolean zzJo;
    private boolean zzJp;
    private boolean zzJq;
    private ViewTreeObserver$OnGlobalLayoutListener zzJr;
    private ViewTreeObserver$OnScrollChangedListener zzJs;
    
    public zzim(final Activity zzJn, final ViewTreeObserver$OnGlobalLayoutListener zzJr, final ViewTreeObserver$OnScrollChangedListener zzJs) {
        this.zzJn = zzJn;
        this.zzJr = zzJr;
        this.zzJs = zzJs;
    }
    
    private void zzgQ() {
        if (this.zzJn != null && !this.zzJo) {
            if (this.zzJr != null) {
                zzp.zzbv().zza(this.zzJn, this.zzJr);
            }
            if (this.zzJs != null) {
                zzp.zzbv().zza(this.zzJn, this.zzJs);
            }
            this.zzJo = true;
        }
    }
    
    private void zzgR() {
        if (this.zzJn != null && this.zzJo) {
            if (this.zzJr != null) {
                zzp.zzbx().zzb(this.zzJn, this.zzJr);
            }
            if (this.zzJs != null) {
                zzp.zzbv().zzb(this.zzJn, this.zzJs);
            }
            this.zzJo = false;
        }
    }
    
    public void onAttachedToWindow() {
        this.zzJp = true;
        if (this.zzJq) {
            this.zzgQ();
        }
    }
    
    public void onDetachedFromWindow() {
        this.zzJp = false;
        this.zzgR();
    }
    
    public void zzgO() {
        this.zzJq = true;
        if (this.zzJp) {
            this.zzgQ();
        }
    }
    
    public void zzgP() {
        this.zzJq = false;
        this.zzgR();
    }
    
    public void zzk(final Activity zzJn) {
        this.zzJn = zzJn;
    }
}
