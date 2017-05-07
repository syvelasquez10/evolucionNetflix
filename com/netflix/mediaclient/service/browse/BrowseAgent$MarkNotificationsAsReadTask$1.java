// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

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
import com.netflix.mediaclient.Log;
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
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.util.SocialUtils;
import com.netflix.mediaclient.android.app.Status;

class BrowseAgent$MarkNotificationsAsReadTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$MarkNotificationsAsReadTask this$1;
    
    BrowseAgent$MarkNotificationsAsReadTask$1(final BrowseAgent$MarkNotificationsAsReadTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onSocialNotificationsMarkedAsRead(final Status status) {
        if (status.isSucces()) {
            SocialUtils.notifyOthersOfUnreadNotifications(this.this$1.this$0.getContext(), false, true);
            return;
        }
        this.this$1.this$0.refreshSocialNotifications(true, false, null);
    }
}
