// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import com.netflix.mediaclient.servicemgr.AddToListData$AddToListState;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;

class MovieDetailsFrag$MovieDetailaStateListener implements AddToListData$StateListener
{
    final /* synthetic */ MovieDetailsFrag this$0;
    
    MovieDetailsFrag$MovieDetailaStateListener(final MovieDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void update(final AddToListData$AddToListState addToListData$AddToListState) {
        ((StickyGridHeadersGridView)this.this$0.gridView).invalidateViews();
    }
}
