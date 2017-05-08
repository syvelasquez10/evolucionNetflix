// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;

class MiniPlayerControlsFrag$RelatedClicks extends VideoDetailsClickListener
{
    final /* synthetic */ MiniPlayerControlsFrag this$0;
    
    public MiniPlayerControlsFrag$RelatedClicks(final MiniPlayerControlsFrag this$0, final NetflixActivity netflixActivity, final PlayContextProvider playContextProvider) {
        this.this$0 = this$0;
        super(netflixActivity, playContextProvider);
    }
    
    @Override
    protected void launchDetailsActivity(final NetflixActivity netflixActivity, final Video video, final PlayContext playContext) {
        DetailsActivityLauncher.showMemento(netflixActivity, video.getType(), video.getId(), video.getTitle(), playContext);
    }
}
