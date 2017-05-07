// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.widget.EditText;
import android.webkit.JsPromptResult;
import android.content.DialogInterface$OnClickListener;

final class zziu$6 implements DialogInterface$OnClickListener
{
    final /* synthetic */ JsPromptResult zzJy;
    final /* synthetic */ EditText zzJz;
    
    zziu$6(final JsPromptResult zzJy, final EditText zzJz) {
        this.zzJy = zzJy;
        this.zzJz = zzJz;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.zzJy.confirm(this.zzJz.getText().toString());
    }
}
