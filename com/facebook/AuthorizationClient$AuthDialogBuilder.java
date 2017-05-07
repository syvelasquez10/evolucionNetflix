// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import com.facebook.widget.WebDialog;
import android.os.Bundle;
import android.content.Context;
import com.facebook.widget.WebDialog$Builder;

class AuthorizationClient$AuthDialogBuilder extends WebDialog$Builder
{
    private static final String OAUTH_DIALOG = "oauth";
    static final String REDIRECT_URI = "fbconnect://success";
    private String e2e;
    private boolean isRerequest;
    
    public AuthorizationClient$AuthDialogBuilder(final Context context, final String s, final Bundle bundle) {
        super(context, s, "oauth", bundle);
    }
    
    @Override
    public WebDialog build() {
        final Bundle parameters = this.getParameters();
        parameters.putString("redirect_uri", "fbconnect://success");
        parameters.putString("client_id", this.getApplicationId());
        parameters.putString("e2e", this.e2e);
        parameters.putString("response_type", "token");
        parameters.putString("return_scopes", "true");
        if (this.isRerequest && !Settings.getPlatformCompatibilityEnabled()) {
            parameters.putString("auth_type", "rerequest");
        }
        return new WebDialog(this.getContext(), "oauth", parameters, this.getTheme(), this.getListener());
    }
    
    public AuthorizationClient$AuthDialogBuilder setE2E(final String e2e) {
        this.e2e = e2e;
        return this;
    }
    
    public AuthorizationClient$AuthDialogBuilder setIsRerequest(final boolean isRerequest) {
        this.isRerequest = isRerequest;
        return this;
    }
}
