// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.os.Bundle;

class AuthorizationClient$GetTokenAuthHandler$1 implements GetTokenClient$CompletedListener
{
    final /* synthetic */ AuthorizationClient$GetTokenAuthHandler this$1;
    final /* synthetic */ AuthorizationClient$AuthorizationRequest val$request;
    
    AuthorizationClient$GetTokenAuthHandler$1(final AuthorizationClient$GetTokenAuthHandler this$1, final AuthorizationClient$AuthorizationRequest val$request) {
        this.this$1 = this$1;
        this.val$request = val$request;
    }
    
    @Override
    public void completed(final Bundle bundle) {
        this.this$1.getTokenCompleted(this.val$request, bundle);
    }
}
