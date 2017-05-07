// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import java.util.List;

class BrowseAgent$FetchLoMosTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$FetchLoMosTask this$1;
    
    BrowseAgent$FetchLoMosTask$1(final BrowseAgent$FetchLoMosTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onLoMosFetched(final List<LoMo> list, final Status status) {
        this.this$1.updateCacheIfNecessary(list, status);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchLoMosTask$1$1(this, list, status));
    }
}
