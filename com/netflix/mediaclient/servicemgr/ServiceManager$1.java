// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.webclient.model.leafs.social.FriendForRecommendation;
import java.util.Set;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import java.util.List;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import android.widget.TextView;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.android.activity.NetflixActivity;
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
            this.this$0.mCallback.onManagerUnavailable(this.this$0, CommonStatus.INTERNAL_ERROR);
            this.this$0.mCallback = null;
        }
        this.this$0.mLocalService = null;
        this.this$0.mService = null;
    }
}
