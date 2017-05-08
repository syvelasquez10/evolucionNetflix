// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import android.widget.Toast;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$TwoButtonAlertDialogDescriptor;
import com.netflix.mediaclient.android.widget.AlertDialogFactory;
import com.netflix.mediaclient.android.widget.AlertDialogFactory$AlertDialogDescriptor;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.util.concurrent.TimeUnit;
import android.annotation.SuppressLint;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import android.webkit.JavascriptInterface;

public class OnRampActivity$OnRampJSBridge
{
    final /* synthetic */ OnRampActivity this$0;
    
    public OnRampActivity$OnRampJSBridge(final OnRampActivity this$0) {
        this.this$0 = this$0;
    }
    
    @JavascriptInterface
    public void notifyReady() {
    }
    
    @JavascriptInterface
    public void onLoaded() {
        this.this$0.onLoadedBeenCalled = true;
    }
    
    @JavascriptInterface
    public void onRampCompleted(final int n) {
        Log.d("OnRampActivity", "onRampCompleted");
        if (n > 0) {
            OnRampActivity.onRampLatch.onRampSelectionMade = true;
            this.this$0.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO"));
        }
        this.this$0.finish();
    }
    
    @JavascriptInterface
    public void onRampInitiated() {
    }
    
    @JavascriptInterface
    public void passNonMemberInfo(final String s) {
    }
    
    @JavascriptInterface
    public void setLanguage(final String s) {
    }
    
    @JavascriptInterface
    public void showSignIn() {
    }
    
    @JavascriptInterface
    public void showSignOut() {
    }
    
    @JavascriptInterface
    public void supportsSignUp(final String s) {
    }
}
