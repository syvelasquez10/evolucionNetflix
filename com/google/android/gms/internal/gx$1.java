// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.webkit.JsResult;
import android.content.DialogInterface$OnCancelListener;

final class gx$1 implements DialogInterface$OnCancelListener
{
    final /* synthetic */ JsResult wY;
    
    gx$1(final JsResult wy) {
        this.wY = wy;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        this.wY.cancel();
    }
}
