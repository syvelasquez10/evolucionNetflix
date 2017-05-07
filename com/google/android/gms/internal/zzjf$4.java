// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import android.content.DialogInterface$OnCancelListener;

final class zzjf$4 implements DialogInterface$OnCancelListener
{
    final /* synthetic */ JsPromptResult zzKE;
    
    zzjf$4(final JsPromptResult zzKE) {
        this.zzKE = zzKE;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        this.zzKE.cancel();
    }
}
