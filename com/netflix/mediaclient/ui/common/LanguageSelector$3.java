// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.Log;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class LanguageSelector$3 implements DialogInterface$OnClickListener
{
    final /* synthetic */ LanguageSelector this$0;
    final /* synthetic */ boolean val$bWasPlaying;
    final /* synthetic */ LanguageSelector$LanguageAlertDialog val$dialog;
    
    LanguageSelector$3(final LanguageSelector this$0, final boolean val$bWasPlaying, final LanguageSelector$LanguageAlertDialog val$dialog) {
        this.this$0 = this$0;
        this.val$bWasPlaying = val$bWasPlaying;
        this.val$dialog = val$dialog;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        Log.d("nf_language_selector", "Languages::apply");
        this.this$0.mCallback.languageChanged(this.this$0.language, this.val$bWasPlaying);
        this.val$dialog.dismiss();
    }
}
