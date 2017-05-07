// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import android.view.View;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import android.os.Bundle;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import android.view.View$OnTouchListener;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import com.netflix.mediaclient.StatusCode;
import android.annotation.TargetApi;
import com.netflix.mediaclient.Log;
import android.os.Build$VERSION;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.Intent;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import android.webkit.WebView;
import com.netflix.mediaclient.servicemgr.SignUpParams;
import android.os.Handler;
import android.widget.ViewFlipper;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.login.AccountActivity;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

class SignupActivity$8 implements Runnable
{
    final /* synthetic */ SignupActivity this$0;
    
    SignupActivity$8(final SignupActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.displayDialog(AlertDialogFactory.createDialog((Context)this.this$0, this.this$0.handler, new AlertDialogFactory$AlertDialogDescriptor(null, this.this$0.getString(2131493120), this.this$0.getString(17039370), null)));
    }
}
