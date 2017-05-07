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
import com.netflix.mediaclient.util.SocialNotificationsUtils;
import android.support.v4.content.LocalBroadcastManager;
import java.util.Iterator;
import java.util.List;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.model.leafs.social.SocialNotificationsList;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.model.Root;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.falkor.ServiceProvider;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class FalkorAgent$2 extends BroadcastReceiver
{
    final /* synthetic */ FalkorAgent this$0;
    
    FalkorAgent$2(final FalkorAgent this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            Log.i("FalkorAgent", "UserAgentIntentReceiver inovoked and received Intent with Action " + intent.getAction());
            if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_ACTIVE".equals(action)) {
                this.this$0.handleProfileActive();
                return;
            }
            if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_DEACTIVE".equals(action)) {
                this.this$0.handleProfileDeactive();
            }
        }
    }
}
