// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;

class BrowseAgent$PrefetchLoLoMoTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$PrefetchLoLoMoTask this$1;
    
    BrowseAgent$PrefetchLoLoMoTask$1(final BrowseAgent$PrefetchLoLoMoTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onLoLoMoPrefetched(final Status status) {
        if (this.this$1.mIncludeBoxshots) {
            throw new RuntimeException("Unimplemented exception");
        }
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$PrefetchLoLoMoTask$1$1(this, status));
    }
}
