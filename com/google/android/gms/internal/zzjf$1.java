// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.webkit.JsResult;
import android.content.DialogInterface$OnCancelListener;

final class zzjf$1 implements DialogInterface$OnCancelListener
{
    final /* synthetic */ JsResult zzKD;
    
    zzjf$1(final JsResult zzKD) {
        this.zzKD = zzKD;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        this.zzKD.cancel();
    }
}
