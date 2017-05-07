// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.view.View;
import android.view.View$OnClickListener;

class LanguageSelectorKubrick$1 implements View$OnClickListener
{
    final /* synthetic */ LanguageSelectorKubrick this$0;
    final /* synthetic */ boolean val$bWasPlaying;
    
    LanguageSelectorKubrick$1(final LanguageSelectorKubrick this$0, final boolean val$bWasPlaying) {
        this.this$0 = this$0;
        this.val$bWasPlaying = val$bWasPlaying;
    }
    
    public void onClick(final View view) {
        this.this$0.mCallback.languageChanged(this.this$0.language, this.val$bWasPlaying);
        this.this$0.mController.removeDialogFrag();
    }
}
