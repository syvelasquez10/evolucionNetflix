// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import android.content.DialogInterface$OnCancelListener;

final class zziu$4 implements DialogInterface$OnCancelListener
{
    final /* synthetic */ JsPromptResult zzJy;
    
    zziu$4(final JsPromptResult zzJy) {
        this.zzJy = zzJy;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        this.zzJy.cancel();
    }
}
