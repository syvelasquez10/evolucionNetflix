// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import android.view.View;
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
import com.netflix.mediaclient.ui.login.LoginActivity;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import android.content.Context;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.Log;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class SignupActivity$1 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ SignupActivity this$0;
    
    SignupActivity$1(final SignupActivity this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        Log.d("SignupActivity", "User tapped sign-in button");
        UserActionLogUtils.reportLoginActionStarted((Context)this.this$0, null, this.this$0.getUiScreen());
        this.this$0.startNextActivity(LoginActivity.createStartIntent((Context)this.this$0));
        return true;
    }
}
