// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.netflix.mediaclient.ui.verifyplay.PinVerifier$PinType;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import com.netflix.mediaclient.service.webclient.model.leafs.ThumbMessaging;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.media.BookmarkStore;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import java.util.List;
import com.netflix.model.leafs.OnRampEligibility$Action;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.widget.TextView;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.NetflixService$LocalBinder;
import com.netflix.mediaclient.Log;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;

class ServiceManager$1 implements ServiceConnection
{
    final /* synthetic */ ServiceManager this$0;
    
    ServiceManager$1(final ServiceManager this$0) {
        this.this$0 = this$0;
    }
    
    public final void onServiceConnected(final ComponentName componentName, final IBinder binder) {
        Log.d("ServiceManager", "ServiceConnected with IBinder: " + binder);
        final NetflixService$LocalBinder netflixService$LocalBinder = (NetflixService$LocalBinder)binder;
        this.this$0.mService = netflixService$LocalBinder.getService();
        this.this$0.addToMyListWrapper = new AddToMyListWrapper(this.this$0);
        this.this$0.mLocalService = netflixService$LocalBinder.getService();
        if (this.this$0.mServiceListener == null) {
            this.this$0.mServiceListener = new ServiceManager$ServiceListener(this.this$0, null);
        }
        this.this$0.mService.registerCallback(this.this$0.mServiceListener);
    }
    
    public final void onServiceDisconnected(final ComponentName componentName) {
        Log.d("ServiceManager", "onServiceDisconnected");
        if (this.this$0.mCallback != null) {
            this.this$0.mCallback.onManagerUnavailable(this.this$0, CommonStatus.INT_ERR_CB_NULL);
            this.this$0.mCallback = null;
        }
        this.this$0.mLocalService = null;
        this.this$0.mService = null;
        this.this$0.mReady = false;
        this.this$0.mClientId = -1;
    }
}
