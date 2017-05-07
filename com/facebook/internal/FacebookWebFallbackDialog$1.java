// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import com.facebook.widget.FacebookDialog;
import android.content.Intent;
import com.facebook.FacebookException;
import android.os.Bundle;
import android.content.Context;
import com.facebook.widget.FacebookDialog$Callback;
import com.facebook.widget.FacebookDialog$PendingCall;
import com.facebook.widget.WebDialog$OnCompleteListener;

final class FacebookWebFallbackDialog$1 implements WebDialog$OnCompleteListener
{
    final /* synthetic */ FacebookDialog$PendingCall val$appCall;
    final /* synthetic */ FacebookDialog$Callback val$callback;
    final /* synthetic */ Context val$context;
    
    FacebookWebFallbackDialog$1(final Context val$context, final FacebookDialog$PendingCall val$appCall, final FacebookDialog$Callback val$callback) {
        this.val$context = val$context;
        this.val$appCall = val$appCall;
        this.val$callback = val$callback;
    }
    
    @Override
    public void onComplete(final Bundle bundle, final FacebookException ex) {
        final Intent intent = new Intent();
        Bundle bundle2 = bundle;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        intent.putExtras(bundle2);
        FacebookDialog.handleActivityResult(this.val$context, this.val$appCall, this.val$appCall.getRequestCode(), intent, this.val$callback);
    }
}
