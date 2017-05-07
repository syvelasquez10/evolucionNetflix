// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.login;

import android.view.View$OnClickListener;
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
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.Log;
import android.os.Handler;
import com.netflix.mediaclient.android.app.Status;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import android.view.View;
import android.widget.EditText;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

class LoginActivity$5 implements Runnable
{
    final /* synthetic */ LoginActivity this$0;
    
    LoginActivity$5(final LoginActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.displayDialog(AlertDialogFactory.createDialog((Context)this.this$0, this.this$0.handler, new AlertDialogFactory$AlertDialogDescriptor(null, this.this$0.getString(2131493244), this.this$0.getString(17039370), null)));
    }
}
