// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo;
import java.util.List;

class BrowseAgent$FetchPostPlayTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$FetchPostPlayTask this$1;
    
    BrowseAgent$FetchPostPlayTask$1(final BrowseAgent$FetchPostPlayTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onPostPlayVideosFetched(final List<PostPlayVideo> list, final Status status) {
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchPostPlayTask$1$1(this, list, status));
    }
}
