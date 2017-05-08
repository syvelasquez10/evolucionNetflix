// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import android.content.Intent;
import android.widget.Toast;
import com.netflix.mediaclient.NetflixApplication;
import android.webkit.WebSettings;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import android.view.View$OnTouchListener;
import android.webkit.WebViewClient;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.ArrayList;
import android.webkit.CookieSyncManager;
import android.webkit.CookieManager;
import android.annotation.TargetApi;
import com.netflix.mediaclient.Log;
import android.os.Build$VERSION;
import com.netflix.mediaclient.webapi.WebApiCommand;
import com.netflix.mediaclient.service.webclient.model.leafs.NrmConfigData;
import com.netflix.mediaclient.util.StringUtils;
import android.os.Handler;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import android.webkit.WebView;
import android.widget.ViewFlipper;
import com.netflix.mediaclient.ui.login.AccountActivity;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;

class WebViewAccountActivity$4 implements Runnable
{
    final /* synthetic */ WebViewAccountActivity this$0;
    
    WebViewAccountActivity$4(final WebViewAccountActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.displayDialog(AlertDialogFactory.createDialog((Context)this.this$0, this.this$0.handler, new AlertDialogFactory$AlertDialogDescriptor(null, this.this$0.getString(2131231209), this.this$0.getString(2131231128), null)));
    }
}
