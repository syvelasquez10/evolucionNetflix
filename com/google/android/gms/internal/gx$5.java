// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import android.content.DialogInterface$OnClickListener;

final class gx$5 implements DialogInterface$OnClickListener
{
    final /* synthetic */ JsPromptResult wZ;
    
    gx$5(final JsPromptResult wz) {
        this.wZ = wz;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.wZ.cancel();
    }
}
