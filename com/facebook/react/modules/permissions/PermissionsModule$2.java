// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.permissions;

import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.bridge.Promise;
import java.util.ArrayList;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Callback;

class PermissionsModule$2 implements Callback
{
    final /* synthetic */ PermissionsModule this$0;
    final /* synthetic */ WritableMap val$grantedPermissions;
    final /* synthetic */ ArrayList val$permissionsToCheck;
    final /* synthetic */ Promise val$promise;
    
    PermissionsModule$2(final PermissionsModule this$0, final ArrayList val$permissionsToCheck, final WritableMap val$grantedPermissions, final Promise val$promise) {
        this.this$0 = this$0;
        this.val$permissionsToCheck = val$permissionsToCheck;
        this.val$grantedPermissions = val$grantedPermissions;
        this.val$promise = val$promise;
    }
    
    @Override
    public void invoke(final Object... array) {
        final int[] array2 = (int[])array[0];
        final PermissionAwareActivity permissionAwareActivity = (PermissionAwareActivity)array[1];
        for (int i = 0; i < this.val$permissionsToCheck.size(); ++i) {
            final String s = this.val$permissionsToCheck.get(i);
            if (array2[i] == 0) {
                this.val$grantedPermissions.putString(s, "granted");
            }
            else if (permissionAwareActivity.shouldShowRequestPermissionRationale(s)) {
                this.val$grantedPermissions.putString(s, "denied");
            }
            else {
                this.val$grantedPermissions.putString(s, "never_ask_again");
            }
        }
        this.val$promise.resolve(this.val$grantedPermissions);
    }
}
