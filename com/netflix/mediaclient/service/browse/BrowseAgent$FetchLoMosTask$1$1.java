// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;

class BrowseAgent$FetchLoMosTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$FetchLoMosTask$1 this$2;
    final /* synthetic */ List val$requestedLoMos;
    final /* synthetic */ Status val$res;
    
    BrowseAgent$FetchLoMosTask$1$1(final BrowseAgent$FetchLoMosTask$1 this$2, final List val$requestedLoMos, final Status val$res) {
        this.this$2 = this$2;
        this.val$requestedLoMos = val$requestedLoMos;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onLoMosFetched(this.val$requestedLoMos, this.val$res);
    }
}
