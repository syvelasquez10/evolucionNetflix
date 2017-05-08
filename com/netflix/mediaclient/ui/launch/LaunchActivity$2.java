// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.launch;

import android.support.v7.app.ActionBar;
import android.view.View;
import com.netflix.mediaclient.android.fragment.LoadingView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.auth.api.credentials.CredentialRequest$Builder;
import android.os.Bundle;
import com.netflix.mediaclient.util.LoginUtils;
import com.netflix.mediaclient.ui.login.AccountActivity;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.IntentSender$SendIntentException;
import com.netflix.mediaclient.util.IntentUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.ProgressBar;
import com.netflix.mediaclient.ui.ums.EndOfGrandfatheringActivity;
import com.netflix.mediaclient.ui.ums.EogUtils;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.ui.signup.SignupActivity;
import com.google.android.gms.common.api.Api$ApiOptions$NotRequiredOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.SignInLogging$CredentialService;
import com.netflix.mediaclient.service.logging.apm.model.Display;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.login.LoginActivity;
import com.netflix.mediaclient.service.logging.apm.model.DeepLink;
import com.netflix.mediaclient.service.logging.apm.model.UIBrowseStartupSessionCustomData;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$UiStartupTrigger;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.util.log.SignInLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.SignInLogging$SignInType;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.ImageView;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.protocol.nflx.NflxHandlerFactory;
import com.netflix.mediaclient.protocol.netflixcom.NetflixComHandlerFactory;
import com.netflix.mediaclient.protocol.nflx.NflxHandler$Response;
import com.google.android.gms.auth.api.credentials.Credential;
import android.content.BroadcastReceiver;
import java.util.concurrent.atomic.AtomicBoolean;
import com.google.android.gms.common.api.GoogleApiClient;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.app.Activity;
import com.netflix.mediaclient.android.activity.ServiceErrorsHandler;
import java.util.Map;
import com.netflix.mediaclient.service.logging.perf.Sessions;
import com.netflix.mediaclient.service.logging.perf.PerformanceProfiler;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class LaunchActivity$2 implements ManagerStatusListener
{
    final /* synthetic */ LaunchActivity this$0;
    
    LaunchActivity$2(final LaunchActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        PerformanceProfiler.getInstance().endSession(Sessions.LAUNCH_ACTIVITY_MANAGER_LOAD, null);
        this.this$0.mManagerStatus = status;
        this.this$0.isLoading = false;
        if (ServiceErrorsHandler.handleManagerResponse(this.this$0, status)) {
            this.this$0.mIsErrorDialogVisible = true;
            return;
        }
        this.this$0.handleManagerReady(serviceManager);
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        PerformanceProfiler.getInstance().endSession(Sessions.LAUNCH_ACTIVITY_MANAGER_LOAD, PerformanceProfiler.createFailedMap());
        this.this$0.isLoading = false;
        this.this$0.mManagerStatus = status;
        this.this$0.mIsErrorDialogVisible = ServiceErrorsHandler.handleManagerResponse(this.this$0, status);
    }
}
