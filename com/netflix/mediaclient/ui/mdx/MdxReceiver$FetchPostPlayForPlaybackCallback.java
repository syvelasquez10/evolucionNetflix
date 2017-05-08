// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.ui.player.MDXControllerActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
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
            if (BrowseExperience.shouldShowMemento((Context)this.mActivity)) {
                final Intent intent = new Intent("com.netflix.mediaclient.intent.action.MINI_PLAYER_POST_PLAY_TITLE_NEXT");
                intent.putExtra("id", episodeDetails.getId());
                LocalBroadcastManager.getInstance((Context)this.mActivity).sendBroadcast(intent);
                LocalBroadcastManager.getInstance((Context)this.mActivity).sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.RDP_CLOSE"));
            }
            else {
                MDXControllerActivity.showMDXController(this.mActivity, episodeDetails.getId(), true, PlayContext.DFLT_MDX_CONTEXT);
            }
            this.processed = true;
        }
    }
}
