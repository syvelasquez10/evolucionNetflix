// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.os.Bundle;
import com.facebook.widget.WebDialog$OnCompleteListener;

class AuthorizationClient$WebViewAuthHandler$1 implements WebDialog$OnCompleteListener
{
    final /* synthetic */ AuthorizationClient$WebViewAuthHandler this$1;
    final /* synthetic */ AuthorizationClient$AuthorizationRequest val$request;
    
    AuthorizationClient$WebViewAuthHandler$1(final AuthorizationClient$WebViewAuthHandler this$1, final AuthorizationClient$AuthorizationRequest val$request) {
        this.this$1 = this$1;
        this.val$request = val$request;
    }
    
    @Override
    public void onComplete(final Bundle bundle, final FacebookException ex) {
        this.this$1.onWebDialogComplete(this.val$request, bundle, ex);
    }
}
