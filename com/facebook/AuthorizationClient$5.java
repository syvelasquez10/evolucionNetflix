// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.app.Activity;
import android.content.Intent;
import com.facebook.android.R$string;
import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

class AuthorizationClient$5 implements RequestBatch$Callback
{
    final /* synthetic */ AuthorizationClient this$0;
    final /* synthetic */ ArrayList val$declinedPermissions;
    final /* synthetic */ ArrayList val$fbids;
    final /* synthetic */ ArrayList val$grantedPermissions;
    final /* synthetic */ AuthorizationClient$Result val$pendingResult;
    
    AuthorizationClient$5(final AuthorizationClient this$0, final ArrayList val$fbids, final AuthorizationClient$Result val$pendingResult, final ArrayList val$grantedPermissions, final ArrayList val$declinedPermissions) {
        this.this$0 = this$0;
        this.val$fbids = val$fbids;
        this.val$pendingResult = val$pendingResult;
        this.val$grantedPermissions = val$grantedPermissions;
        this.val$declinedPermissions = val$declinedPermissions;
    }
    
    @Override
    public void onBatchCompleted(final RequestBatch requestBatch) {
        try {
            AuthorizationClient$Result authorizationClient$Result;
            if (this.val$fbids.size() == 2 && this.val$fbids.get(0) != null && this.val$fbids.get(1) != null && this.val$fbids.get(0).equals(this.val$fbids.get(1))) {
                authorizationClient$Result = AuthorizationClient$Result.createTokenResult(this.this$0.pendingRequest, AccessToken.createFromTokenWithRefreshedPermissions(this.val$pendingResult.token, this.val$grantedPermissions, this.val$declinedPermissions));
            }
            else {
                authorizationClient$Result = AuthorizationClient$Result.createErrorResult(this.this$0.pendingRequest, "User logged in as different Facebook user.", null);
            }
            this.this$0.complete(authorizationClient$Result);
        }
        catch (Exception ex) {
            this.this$0.complete(AuthorizationClient$Result.createErrorResult(this.this$0.pendingRequest, "Caught exception", ex.getMessage()));
        }
        finally {
            this.this$0.notifyBackgroundProcessingStop();
        }
    }
}
