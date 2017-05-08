// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.permissions;

import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.Callback;

class PermissionsModule$1 implements Callback
{
    final /* synthetic */ PermissionsModule this$0;
    final /* synthetic */ String val$permission;
    final /* synthetic */ Promise val$promise;
    
    PermissionsModule$1(final PermissionsModule this$0, final Promise val$promise, final String val$permission) {
        this.this$0 = this$0;
        this.val$promise = val$promise;
        this.val$permission = val$permission;
    }
    
    @Override
    public void invoke(final Object... array) {
        if (((int[])array[0])[0] == 0) {
            this.val$promise.resolve("granted");
            return;
        }
        if (((PermissionAwareActivity)array[1]).shouldShowRequestPermissionRationale(this.val$permission)) {
            this.val$promise.resolve("denied");
            return;
        }
        this.val$promise.resolve("never_ask_again");
    }
}
