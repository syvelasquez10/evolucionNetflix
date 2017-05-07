// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;

class BrowseAgent$HideVideoTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$HideVideoTask$1 this$2;
    final /* synthetic */ Status val$res;
    
    BrowseAgent$HideVideoTask$1$1(final BrowseAgent$HideVideoTask$1 this$2, final Status val$res) {
        this.this$2 = this$2;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onVideoHide(this.val$res);
    }
}
