// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.android;

import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookDialogException;
import android.content.Context;
import com.facebook.widget.WebDialog;
import com.facebook.FacebookException;
import android.os.Bundle;
import com.facebook.widget.WebDialog$OnCompleteListener;

class FbDialog$1 implements WebDialog$OnCompleteListener
{
    final /* synthetic */ FbDialog this$0;
    
    FbDialog$1(final FbDialog this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onComplete(final Bundle bundle, final FacebookException ex) {
        this.this$0.callDialogListener(bundle, ex);
    }
}
