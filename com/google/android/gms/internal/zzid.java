// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzp;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.app.Activity;

public final class zzid
{
    private Activity zzIs;
    private boolean zzIt;
    private boolean zzIu;
    private boolean zzIv;
    private ViewTreeObserver$OnGlobalLayoutListener zzIw;
    private ViewTreeObserver$OnScrollChangedListener zzIx;
    
    public zzid(final Activity zzIs, final ViewTreeObserver$OnGlobalLayoutListener zzIw, final ViewTreeObserver$OnScrollChangedListener zzIx) {
        this.zzIs = zzIs;
        this.zzIw = zzIw;
        this.zzIx = zzIx;
    }
    
    private void zzgF() {
        if (this.zzIs != null && !this.zzIt) {
            if (this.zzIw != null) {
                zzp.zzbx().zza(this.zzIs, this.zzIw);
            }
            if (this.zzIx != null) {
                zzp.zzbx().zza(this.zzIs, this.zzIx);
            }
            this.zzIt = true;
        }
    }
    
    private void zzgG() {
        if (this.zzIs != null && this.zzIt) {
            if (this.zzIw != null) {
                zzp.zzbz().zzb(this.zzIs, this.zzIw);
            }
            if (this.zzIx != null) {
                zzp.zzbx().zzb(this.zzIs, this.zzIx);
            }
            this.zzIt = false;
        }
    }
    
    public void onAttachedToWindow() {
        this.zzIu = true;
        if (this.zzIv) {
            this.zzgF();
        }
    }
    
    public void onDetachedFromWindow() {
        this.zzIu = false;
        this.zzgG();
    }
    
    public void zzgD() {
        this.zzIv = true;
        if (this.zzIu) {
            this.zzgF();
        }
    }
    
    public void zzgE() {
        this.zzIv = false;
        this.zzgG();
    }
    
    public void zzl(final Activity zzIs) {
        this.zzIs = zzIs;
    }
}
