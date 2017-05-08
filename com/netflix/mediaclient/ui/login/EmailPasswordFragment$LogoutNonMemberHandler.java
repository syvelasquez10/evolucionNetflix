// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import android.view.View$OnClickListener;
import android.widget.TextView$OnEditorActionListener;
import android.view.View$OnFocusChangeListener;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import java.util.Locale;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.google.android.gms.common.ConnectionResult;
import android.content.IntentSender$SendIntentException;
import android.app.Activity;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import android.net.Uri;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.StatusCode;
import android.os.Bundle;
import com.netflix.mediaclient.service.webclient.model.leafs.SignInConfigData;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.auth.api.credentials.Credential$Builder;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.SignInLogging$CredentialService;
import com.google.android.gms.common.api.Api$ApiOptions$NotRequiredOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.netflix.mediaclient.util.LoginUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.util.log.SignInLogUtils;
import com.netflix.mediaclient.servicemgr.SignInLogging$SignInType;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import android.view.View;
import android.os.Handler;
import android.widget.EditText;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class EmailPasswordFragment$LogoutNonMemberHandler extends LoggingManagerCallback
{
    final /* synthetic */ EmailPasswordFragment this$0;
    
    public EmailPasswordFragment$LogoutNonMemberHandler(final EmailPasswordFragment this$0) {
        this.this$0 = this$0;
        super("LoginBaseFragment");
    }
    
    @Override
    public void onLogoutComplete(final Status status) {
        super.onLogoutComplete(status);
        if (status.isSucces()) {
            this.this$0.getServiceManager().loginUser(this.this$0.mEmailEditText.getText().toString(), this.this$0.mPasswordEditText.getText().toString(), this.this$0.mLoginQueryCallback);
        }
        else if (Log.isLoggable()) {
            Log.e("LoginBaseFragment", "Could not log user out - status code: " + status.getStatusCode());
        }
    }
}
