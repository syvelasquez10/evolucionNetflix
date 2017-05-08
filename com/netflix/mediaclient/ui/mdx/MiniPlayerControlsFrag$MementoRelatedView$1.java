// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import android.view.View$OnClickListener;

class MiniPlayerControlsFrag$MementoRelatedView$1 implements View$OnClickListener
{
    final /* synthetic */ MiniPlayerControlsFrag$MementoRelatedView this$1;
    final /* synthetic */ Video val$video;
    
    MiniPlayerControlsFrag$MementoRelatedView$1(final MiniPlayerControlsFrag$MementoRelatedView this$1, final Video val$video) {
        this.this$1 = this$1;
        this.val$video = val$video;
    }
    
    public void onClick(final View view) {
        DetailsActivityLauncher.showMemento(this.this$1.this$0.getNetflixActivity(), this.val$video.getType(), this.val$video.getId(), this.val$video.getTitle(), PlayContext.NFLX_MDX_CONTEXT);
    }
}
