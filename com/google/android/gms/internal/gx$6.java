// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.widget.EditText;
import android.webkit.JsPromptResult;
import android.content.DialogInterface$OnClickListener;

final class gx$6 implements DialogInterface$OnClickListener
{
    final /* synthetic */ JsPromptResult wZ;
    final /* synthetic */ EditText xa;
    
    gx$6(final JsPromptResult wz, final EditText xa) {
        this.wZ = wz;
        this.xa = xa;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.wZ.confirm(this.xa.getText().toString());
    }
}
