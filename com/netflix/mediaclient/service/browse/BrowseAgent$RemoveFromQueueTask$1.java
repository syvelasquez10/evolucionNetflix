// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import android.content.Intent;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.webclient.model.ShowDetails;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.android.app.Status;

class BrowseAgent$RemoveFromQueueTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$RemoveFromQueueTask this$1;
    
    BrowseAgent$RemoveFromQueueTask$1(final BrowseAgent$RemoveFromQueueTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onQueueRemove(final Status status) {
        if (status.isSucces()) {
            if (this.this$1.iqInCache) {
                BrowseAgent.sendIqRefreshBrodcast(this.this$1.this$0.getContext());
            }
            UserActionLogUtils.reportRemoveFromQueueActionEnded(this.this$1.this$0.getContext(), IClientLogging$CompletionReason.success, null);
        }
        else {
            int n = 2131493337;
            if (status.getStatusCode() == StatusCode.NOT_IN_QUEUE) {
                Log.w("nf_service_browseagent", "It was already removed");
                n = 2131493336;
            }
            UserActionLogUtils.reportRemoveFromQueueActionEnded(this.this$1.this$0.getContext(), IClientLogging$CompletionReason.failed, ConsolidatedLoggingUtils.createUIError(status, this.this$1.this$0.getContext().getString(n), ActionOnUIError.displayedError));
        }
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$RemoveFromQueueTask$1$1(this, status));
    }
}
