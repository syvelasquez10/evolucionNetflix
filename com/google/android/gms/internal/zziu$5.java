// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.webkit.JsPromptResult;
import android.content.DialogInterface$OnClickListener;

final class zziu$5 implements DialogInterface$OnClickListener
{
    final /* synthetic */ JsPromptResult zzJy;
    
    zziu$5(final JsPromptResult zzJy) {
        this.zzJy = zzJy;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.zzJy.cancel();
    }
}
