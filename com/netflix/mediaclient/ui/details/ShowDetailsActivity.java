// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.Playable;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.EpisodeDetails;
import android.view.Menu;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.app.Fragment;

public class ShowDetailsActivity extends DetailsActivity implements EpisodeRowListenerProvider, EpisodeRowListener
{
    @Override
    protected Fragment createPrimaryFrag() {
        return ShowDetailsFrag.create(this.getVideoId(), this.getEpisodeId());
    }
    
    @Override
    protected Fragment createSecondaryFrag() {
        boolean b = true;
        int n;
        if (DeviceUtils.isTabletByContext((Context)this) && DeviceUtils.isLandscape((Context)this)) {
            n = 1;
        }
        else {
            n = 0;
        }
        final String videoId = this.getVideoId();
        final String episodeId = this.getEpisodeId();
        if (n != 0) {
            b = false;
        }
        return (Fragment)EpisodeListFrag.create(videoId, episodeId, b);
    }
    
    @Override
    public EpisodeRowListener getEpisodeRowListener() {
        final EpisodeRowListener episodeRowListener = super.getEpisodeRowListener();
        if (episodeRowListener != null) {
            return episodeRowListener;
        }
        return this;
    }
    
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public void onEpisodeSelectedForPlayback(final EpisodeDetails episodeDetails, final PlayContext playContext) {
        PlaybackLauncher.startPlayback(this, episodeDetails, playContext);
    }
    
    @Override
    protected void onPostCreate(final Bundle bundle) {
        super.onPostCreate(bundle);
        final EpisodeListFrag episodeListFrag = (EpisodeListFrag)this.getSecondaryFrag();
        if (DeviceUtils.isTabletByContext((Context)this) && DeviceUtils.isLandscape((Context)this)) {
            this.getPrimaryFragContainer().setVisibility(0);
            episodeListFrag.hideDetailViewHeader();
            return;
        }
        this.getPrimaryFragContainer().setVisibility(8);
        episodeListFrag.showDetailViewHeader();
    }
}
