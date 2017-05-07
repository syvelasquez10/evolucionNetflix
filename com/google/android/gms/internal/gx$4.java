// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import android.content.DialogInterface$OnCancelListener;

final class gx$4 implements DialogInterface$OnCancelListener
{
    final /* synthetic */ JsPromptResult wZ;
    
    gx$4(final JsPromptResult wz) {
        this.wZ = wz;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        this.wZ.cancel();
    }
}
