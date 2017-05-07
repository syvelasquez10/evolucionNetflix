// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.browse.BrowseAgent$BillboardActivityType;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import com.netflix.mediaclient.util.IntentUtils;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.util.SocialUtils;
import com.netflix.mediaclient.NetflixApplication;
import java.util.Iterator;
import java.util.List;
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
import com.netflix.mediaclient.service.preapp.PreAppAgent;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

class FalkorAgent$3 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ FalkorAgent this$0;
    final /* synthetic */ BrowseAgentCallback val$cb;
    
    FalkorAgent$3(final FalkorAgent this$0, final BrowseAgentCallback val$cb) {
        this.this$0 = this$0;
        this.val$cb = val$cb;
    }
    
    @Override
    public void onLoLoMoPrefetched(final Status status) {
        this.val$cb.onLoLoMoPrefetched(status);
        boolean wasAllDataLocalToCache = false;
        if (status instanceof FalkorAgentStatus) {
            wasAllDataLocalToCache = ((FalkorAgentStatus)status).wasAllDataLocalToCache();
        }
        else {
            Log.w("FalkorAgent", "status is not FalkorAgentStatus");
        }
        if (!wasAllDataLocalToCache && StatusCode.OK.equals(status.getStatusCode())) {
            PreAppAgent.informPrefetched(this.this$0.getContext());
        }
    }
}
