// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.os.Bundle;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import android.app.Fragment;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;

public class ShowDetailsActivity extends DetailsActivity implements EpisodeRowView$EpisodeRowListener, EpisodeRowView$EpisodeRowListenerProvider
{
    private boolean shouldHideDetailsView() {
        return DeviceUtils.isTabletByContext((Context)this) && DeviceUtils.isLandscape((Context)this);
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        return ShowDetailsFrag.create(this.getVideoId(), this.getEpisodeId());
    }
    
    @Override
    protected Fragment createSecondaryFrag() {
        return (Fragment)EpisodeListFrag.create(this.getVideoId(), this.getEpisodeId(), !this.shouldHideDetailsView());
    }
    
    @Override
    public EpisodeRowView$EpisodeRowListener getEpisodeRowListener() {
        final EpisodeRowView$EpisodeRowListener episodeRowListener = super.getEpisodeRowListener();
        EpisodeRowView$EpisodeRowListener episodeRowView$EpisodeRowListener = this;
        if (episodeRowListener != null) {
            episodeRowView$EpisodeRowListener = episodeRowListener;
        }
        return episodeRowView$EpisodeRowListener;
    }
    
    @Override
    public VideoType getVideoType() {
        return VideoType.SHOW;
    }
    
    @Override
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        if (menu2 != null) {
            menu2.add((CharSequence)"Display episodes dialog").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new ShowDetailsActivity$1(this));
        }
        super.onCreateOptionsMenu(menu, menu2);
    }
    
    @Override
    public void onEpisodeSelectedForPlayback(final EpisodeDetails episodeDetails, final PlayContext playContext) {
        PlaybackLauncher.startPlaybackAfterPIN(this, episodeDetails.getPlayable(), playContext);
    }
    
    @Override
    protected void onPostCreate(final Bundle bundle) {
        super.onPostCreate(bundle);
        this.setupFrags();
    }
    
    protected void setupFrags() {
        final EpisodeListFrag episodeListFrag = (EpisodeListFrag)this.getSecondaryFrag();
        if (this.shouldHideDetailsView()) {
            this.getPrimaryFragContainer().setVisibility(0);
            episodeListFrag.hideDetailViewHeader();
            return;
        }
        this.getPrimaryFragContainer().setVisibility(8);
        episodeListFrag.showDetailViewHeader();
    }
}
