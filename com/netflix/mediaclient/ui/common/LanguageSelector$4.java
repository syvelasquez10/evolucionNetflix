// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.Log;
import android.content.DialogInterface;
import android.content.DialogInterface$OnCancelListener;

class LanguageSelector$4 implements DialogInterface$OnCancelListener
{
    final /* synthetic */ LanguageSelector this$0;
    
    LanguageSelector$4(final LanguageSelector this$0) {
        this.this$0 = this$0;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        Log.d("nf_language_selector", "Languages::cancel");
        this.this$0.mCallback.userCanceled();
    }
}
