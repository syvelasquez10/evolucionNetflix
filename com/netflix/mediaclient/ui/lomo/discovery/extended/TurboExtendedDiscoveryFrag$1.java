// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery.extended;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class TurboExtendedDiscoveryFrag$1 extends LoggingManagerCallback
{
    final /* synthetic */ TurboExtendedDiscoveryFrag this$0;
    
    TurboExtendedDiscoveryFrag$1(final TurboExtendedDiscoveryFrag this$0, final String s) {
        this.this$0 = this$0;
        super(s);
    }
    
    @Override
    public void onVideosFetched(final List<Video> collectionData, final Status status) {
        super.onVideosFetched(collectionData, status);
        this.this$0.collectionData = collectionData;
        if (this.this$0.adapter != null) {
            this.this$0.adapter.notifyDataSetChanged();
            this.this$0.leWrapper.hide(true);
        }
    }
}
