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

@SuppressLint({ "SetJavaScriptEnabled" })
public class OnRampActivity extends WebViewAccountActivity
{
    private static final String BOOT_URL = "https://www.netflix.com/welcome/onramp?isProfilesOnRamp=true";
    private static final long PAGE_LOAD_TIMEOUT;
    private static final String TAG = "OnRampActivity";
    Runnable mAbortOnRamp;
    private String mBootUrl;
    Runnable mHandleError;
    private boolean onLoadedBeenCalled;
    
    static {
        PAGE_LOAD_TIMEOUT = TimeUnit.SECONDS.toMillis(5L);
    }
    
    public OnRampActivity() {
        this.mAbortOnRamp = new OnRampActivity$1(this);
        this.mHandleError = new OnRampActivity$3(this);
    }
    
    public static boolean shouldShowOnRamp(final ServiceManager serviceManager, final Activity activity) {
        return serviceManager.isReady() && activity != null && serviceManager.getCurrentProfile() != null && PersistentConfig.isOnRampTest((Context)activity) && !serviceManager.getCurrentProfile().isPrimaryProfile();
    }
    
    @Override
    public Object createJSBridge() {
        return new OnRampActivity$OnRampJSBridge(this);
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new OnRampActivity$2(this);
    }
    
    @Override
    public String getBootUrl() {
        return this.mBootUrl;
    }
    
    public Context getContext() {
        return (Context)this;
    }
    
    @Override
    protected CustomerServiceLogging$EntryPoint getEntryPoint() {
        return CustomerServiceLogging$EntryPoint.profileGate;
    }
    
    @Override
    public Runnable getErrorHandler() {
        return this.mHandleError;
    }
    
    @Override
    public Runnable getNextTask() {
        return this.mAbortOnRamp;
    }
    
    @Override
    public long getTimeout() {
        return OnRampActivity.PAGE_LOAD_TIMEOUT;
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.onramp;
    }
    
    @Override
    public boolean isLoadingData() {
        return false;
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.clearCookies();
    }
    
    @Override
    public void onWebViewLoaded() {
        super.onWebViewLoaded();
        PerformanceProfiler.getInstance().endSession(Sessions.ONRAMP_TTR, null);
    }
    
    @Override
    public void provideDialog(final String s, final Runnable runnable) {
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory$AlertDialogDescriptor(null, s, this.getString(2131231167), runnable)));
    }
    
    @Override
    public void provideTwoButtonDialog(final String s, final Runnable runnable) {
        this.displayDialog(AlertDialogFactory.createDialog((Context)this, this.handler, new AlertDialogFactory$TwoButtonAlertDialogDescriptor(null, s, this.getString(2131231167), runnable, this.getString(2131231008), null)));
    }
    
    @Override
    public boolean showAboutInMenu() {
        return false;
    }
    
    @Override
    protected boolean showHelpInMenu() {
        return false;
    }
    
    public void showToast(final String s) {
        Toast.makeText(this.getContext(), (CharSequence)s, 1).show();
    }
}
