// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import android.widget.TextView$OnEditorActionListener;
import android.os.Bundle;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
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
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.Log;
import android.os.Handler;
import com.netflix.mediaclient.android.app.Status;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import android.widget.EditText;
import android.net.Uri;
import android.content.Intent;
import android.view.View;
import android.view.View$OnClickListener;

class LoginActivity$3 implements View$OnClickListener
{
    final /* synthetic */ LoginActivity this$0;
    
    LoginActivity$3(final LoginActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        final Intent setData = new Intent("android.intent.action.VIEW").setData(Uri.parse("https://signup.netflix.com/loginhelp"));
        if (setData.resolveActivity(this.this$0.getPackageManager()) != null) {
            this.this$0.startActivityForResult(setData, 0);
            return;
        }
        this.this$0.displayUserAgentDialog(this.this$0.getString(2131493305, new Object[] { "https://signup.netflix.com/loginhelp" }), null, false);
    }
}
