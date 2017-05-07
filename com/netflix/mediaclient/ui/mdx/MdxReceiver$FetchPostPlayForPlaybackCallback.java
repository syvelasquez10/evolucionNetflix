// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class MdxReceiver$FetchPostPlayForPlaybackCallback extends LoggingManagerCallback
{
    private final NetflixActivity mActivity;
    private boolean processed;
    
    public MdxReceiver$FetchPostPlayForPlaybackCallback(final String s, final NetflixActivity mActivity) {
        super(s);
        this.processed = false;
        this.mActivity = mActivity;
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
        super.onEpisodeDetailsFetched(episodeDetails, status);
        if (status.isSucces() && episodeDetails != null && !this.processed) {
            MDXControllerActivity.showMDXController(this.mActivity, episodeDetails.getId(), PlayContext.DFLT_MDX_CONTEXT);
            this.processed = true;
        }
    }
}
