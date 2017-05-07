// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.browse.BrowseAgent$BillboardActivityType;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import java.util.Iterator;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.model.leafs.social.SocialNotificationsList;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.model.Root;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.falkor.ServiceProvider;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.util.SocialNotificationsUtils;

class FalkorAgent$7 implements Runnable
{
    final /* synthetic */ FalkorAgent this$0;
    
    FalkorAgent$7(final FalkorAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.getService() != null && SocialNotificationsUtils.isSocialNotificationsFeatureSupported(this.this$0.getService().getCurrentProfile(), this.this$0.getContext())) {
            this.this$0.refreshSocialNotifications(true, true, null);
        }
    }
}
