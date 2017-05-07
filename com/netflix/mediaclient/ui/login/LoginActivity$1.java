// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import android.view.View$OnClickListener;
import com.google.android.gms.common.api.Api$ApiOptions$NotRequiredOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.IntentSender$SendIntentException;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.List;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import android.app.Activity;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.Credential$Builder;
import java.io.Serializable;
import com.netflix.mediaclient.util.StringUtils;
import com.google.android.gms.auth.api.credentials.Credential;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.os.Handler;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import android.view.View;
import android.widget.EditText;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.netflix.mediaclient.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView$OnEditorActionListener;

class LoginActivity$1 implements TextView$OnEditorActionListener
{
    final /* synthetic */ LoginActivity this$0;
    
    LoginActivity$1(final LoginActivity this$0) {
        this.this$0 = this$0;
    }
    
    private boolean isLoginId(final int n) {
        return n == 2131427620 || n == 0 || n == 6;
    }
    
    public boolean onEditorAction(final TextView textView, final int n, final KeyEvent keyEvent) {
        if (Log.isLoggable()) {
            Log.v("LoginActivity", "Editor action: " + n + ", keyevent: " + keyEvent);
        }
        if (this.isLoginId(n)) {
            this.this$0.attemptLogin();
            return true;
        }
        return false;
    }
}
