// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.DialogInterface;
import android.widget.EditText;
import android.webkit.JsPromptResult;
import android.content.DialogInterface$OnClickListener;

final class zzjf$6 implements DialogInterface$OnClickListener
{
    final /* synthetic */ JsPromptResult zzKE;
    final /* synthetic */ EditText zzKF;
    
    zzjf$6(final JsPromptResult zzKE, final EditText zzKF) {
        this.zzKE = zzKE;
        this.zzKF = zzKF;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        this.zzKE.confirm(this.zzKF.getText().toString());
    }
}
