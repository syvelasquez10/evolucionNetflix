// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.Collection;
import java.util.ArrayList;

class AuthorizationClient$4 implements Request$Callback
{
    final /* synthetic */ AuthorizationClient this$0;
    final /* synthetic */ ArrayList val$declinedPermissions;
    final /* synthetic */ ArrayList val$grantedPermissions;
    
    AuthorizationClient$4(final AuthorizationClient this$0, final ArrayList val$grantedPermissions, final ArrayList val$declinedPermissions) {
        this.this$0 = this$0;
        this.val$grantedPermissions = val$grantedPermissions;
        this.val$declinedPermissions = val$declinedPermissions;
    }
    
    @Override
    public void onCompleted(final Response response) {
        try {
            final Session$PermissionsPair handlePermissionResponse = Session.handlePermissionResponse(response);
            if (handlePermissionResponse != null) {
                this.val$grantedPermissions.addAll(handlePermissionResponse.getGrantedPermissions());
                this.val$declinedPermissions.addAll(handlePermissionResponse.getDeclinedPermissions());
            }
        }
        catch (Exception ex) {}
    }
}
