// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.Window;
import android.app.Activity;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewTreeObserver;

public class zzie$zzf extends zzie$zzc
{
    @Override
    public void zza(final ViewTreeObserver viewTreeObserver, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
        viewTreeObserver.removeOnGlobalLayoutListener(viewTreeObserver$OnGlobalLayoutListener);
    }
    
    @Override
    public void zzb(final Activity activity, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
        final Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            this.zza(window.getDecorView().getViewTreeObserver(), viewTreeObserver$OnGlobalLayoutListener);
        }
    }
}
