// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;

class BrowseAgent$HideVideoTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$HideVideoTask this$1;
    
    BrowseAgent$HideVideoTask$1(final BrowseAgent$HideVideoTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onVideoHide(final Status status) {
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$HideVideoTask$1$1(this, status));
    }
}
