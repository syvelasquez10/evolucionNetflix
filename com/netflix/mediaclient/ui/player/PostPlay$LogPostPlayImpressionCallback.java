// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class PostPlay$LogPostPlayImpressionCallback extends LoggingManagerCallback
{
    final /* synthetic */ PostPlay this$0;
    
    public PostPlay$LogPostPlayImpressionCallback(final PostPlay this$0) {
        this.this$0 = this$0;
        super("nf_postplay");
    }
    
    @Override
    public void onPostPlayImpressionLogged(final boolean b, final Status status) {
        super.onPostPlayImpressionLogged(b, status);
    }
}
