// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.browse.BrowseAgent$BillboardActivityType;
import com.netflix.mediaclient.service.NetflixService;
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
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import java.util.List;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

class FalkorAgent$9 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ FalkorAgent this$0;
    final /* synthetic */ BrowseAgentCallback val$cb;
    final /* synthetic */ int val$n;
    
    FalkorAgent$9(final FalkorAgent this$0, final int val$n, final BrowseAgentCallback val$cb) {
        this.this$0 = this$0;
        this.val$n = val$n;
        this.val$cb = val$cb;
    }
    
    @Override
    public void onLoMosFetched(final List<LoMo> list, final Status status) {
        boolean b = false;
        Label_0134: {
            if (status.isSucces()) {
                final Iterator<LoMo> iterator = list.iterator();
                LoMo loMo;
                do {
                    b = b;
                    if (!iterator.hasNext()) {
                        break Label_0134;
                    }
                    loMo = iterator.next();
                } while (loMo.getType() != LoMoType.STANDARD);
                if (Log.isLoggable("FalkorAgent", 3)) {
                    Log.d("FalkorAgent", String.format("fetchRecommendedListFromCache listTitle: %s, listId: %s", loMo.getTitle(), loMo.getId()));
                }
                this.this$0.cmp.fetchVideos(loMo, 0, this.val$n - 1, FalkorAgent.USE_CACHE_ONLY, false, this.val$cb);
                b = true;
            }
        }
        if (!b) {
            this.val$cb.onVideosFetched(null, status);
        }
    }
}
