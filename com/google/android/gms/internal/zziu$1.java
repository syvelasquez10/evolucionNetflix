// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.webkit.JsResult;
import android.content.DialogInterface$OnCancelListener;

final class zziu$1 implements DialogInterface$OnCancelListener
{
    final /* synthetic */ JsResult zzJx;
    
    zziu$1(final JsResult zzJx) {
        this.zzJx = zzJx;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        this.zzJx.cancel();
    }
}
