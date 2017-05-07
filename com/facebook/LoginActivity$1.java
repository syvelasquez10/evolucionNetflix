// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.util.Log;
import com.facebook.android.R$id;
import com.facebook.android.R$layout;
import android.content.Intent;
import java.io.Serializable;
import android.os.Bundle;
import android.app.Activity;

class LoginActivity$1 implements AuthorizationClient$OnCompletedListener
{
    final /* synthetic */ LoginActivity this$0;
    
    LoginActivity$1(final LoginActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onCompleted(final AuthorizationClient$Result authorizationClient$Result) {
        this.this$0.onAuthClientCompleted(authorizationClient$Result);
    }
}
