// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import java.util.List;

class BrowseAgent$FetchBillboardVideosTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$FetchBillboardVideosTask this$1;
    
    BrowseAgent$FetchBillboardVideosTask$1(final BrowseAgent$FetchBillboardVideosTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onBBVideosFetched(final List<Billboard> list, final Status status) {
        this.this$1.updateCacheIfNecessary(list, status);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchBillboardVideosTask$1$1(this, list, status));
    }
}
