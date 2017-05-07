// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.content.ActivityNotFoundException;
import android.content.Intent;

abstract class AuthorizationClient$KatanaAuthHandler extends AuthorizationClient$AuthHandler
{
    final /* synthetic */ AuthorizationClient this$0;
    
    AuthorizationClient$KatanaAuthHandler(final AuthorizationClient this$0) {
        this.this$0 = this$0;
        super(this$0);
    }
    
    protected boolean tryIntent(final Intent intent, final int n) {
        if (intent == null) {
            return false;
        }
        try {
            this.this$0.getStartActivityDelegate().startActivityForResult(intent, n);
            return true;
        }
        catch (ActivityNotFoundException ex) {
            return false;
        }
    }
}
