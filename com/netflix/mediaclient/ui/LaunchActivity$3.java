// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui;

import android.support.v7.app.ActionBar;
import android.view.View;
import com.netflix.mediaclient.android.fragment.LoadingView;
import android.os.Bundle;
import android.content.Intent;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.util.IntentUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.ProgressBar;
import com.netflix.mediaclient.service.logging.apm.model.Display;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.ui.login.LoginActivity;
import com.netflix.mediaclient.ui.signup.SignupActivity;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging$UiStartupTrigger;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.ImageView;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.protocol.nflx.NflxHandlerFactory;
import com.netflix.mediaclient.protocol.nflx.NflxHandler$Response;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.app.Activity;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;

class LaunchActivity$3 implements ManagerStatusListener
{
    final /* synthetic */ LaunchActivity this$0;
    
    LaunchActivity$3(final LaunchActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        this.this$0.isLoading = false;
        if (ServiceErrorsHandler.handleManagerResponse(this.this$0, status)) {
            return;
        }
        this.this$0.handleManagerReady(serviceManager);
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
        this.this$0.isLoading = false;
        ServiceErrorsHandler.handleManagerResponse(this.this$0, status);
    }
}
