// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.webkit.JsResult;
import android.content.DialogInterface$OnClickListener;

final class zzjf$2 implements DialogInterface$OnClickListener
{
    final /* synthetic */ JsResult zzKD;
    
    zzjf$2(final JsResult zzKD) {
        this.zzKD = zzKD;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.zzKD.cancel();
    }
}