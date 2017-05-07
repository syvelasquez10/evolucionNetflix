// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import android.content.DialogInterface$OnClickListener;

final class zzjf$5 implements DialogInterface$OnClickListener
{
    final /* synthetic */ JsPromptResult zzKE;
    
    zzjf$5(final JsPromptResult zzKE) {
        this.zzKE = zzKE;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.zzKE.cancel();
    }
}
