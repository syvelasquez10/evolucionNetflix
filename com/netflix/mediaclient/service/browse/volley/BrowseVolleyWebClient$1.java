// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.servicemgr.model.LoMoUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

class BrowseVolleyWebClient$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseVolleyWebClient this$0;
    final /* synthetic */ BrowseAgentCallback val$cb;
    final /* synthetic */ int val$fromVideoFinal;
    final /* synthetic */ LoMo val$lomo;
    
    BrowseVolleyWebClient$1(final BrowseVolleyWebClient this$0, final int val$fromVideoFinal, final LoMo val$lomo, final BrowseAgentCallback val$cb) {
        this.this$0 = this$0;
        this.val$fromVideoFinal = val$fromVideoFinal;
        this.val$lomo = val$lomo;
        this.val$cb = val$cb;
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
        if (status.isSucces() && this.val$fromVideoFinal == 0) {
            LoMoUtils.injectSocialData(this.val$lomo, list);
        }
        this.val$cb.onVideosFetched(list, status);
    }
}
