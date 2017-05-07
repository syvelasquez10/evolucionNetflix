// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.content.Intent;
import android.app.Activity;

class AuthorizationClient$2 implements AuthorizationClient$StartActivityDelegate
{
    final /* synthetic */ AuthorizationClient this$0;
    
    AuthorizationClient$2(final AuthorizationClient this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public Activity getActivityContext() {
        return this.this$0.pendingRequest.getStartActivityDelegate().getActivityContext();
    }
    
    @Override
    public void startActivityForResult(final Intent intent, final int n) {
        this.this$0.pendingRequest.getStartActivityDelegate().startActivityForResult(intent, n);
    }
}
