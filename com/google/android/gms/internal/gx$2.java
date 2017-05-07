// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.webkit.JsResult;
import android.content.DialogInterface$OnClickListener;

final class gx$2 implements DialogInterface$OnClickListener
{
    final /* synthetic */ JsResult wY;
    
    gx$2(final JsResult wy) {
        this.wY = wy;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.wY.cancel();
    }
}
