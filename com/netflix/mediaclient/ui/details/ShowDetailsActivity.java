// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.os.Bundle;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.service.configuration.persistent.Config_Ab7994;
import android.app.Fragment;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;

public class ShowDetailsActivity extends DetailsActivity implements AbsEpisodeView$EpisodeRowListener, AbsEpisodeView$EpisodeRowListenerProvider
{
    private boolean shouldHideDetailsView() {
        return DeviceUtils.isTabletByContext((Context)this) && DeviceUtils.isLandscape((Context)this);
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        if (Config_Ab7994.shouldRenderTabs((Context)this)) {
            return ShowDetailsFrag_Ab7994.create(this.getVideoId(), this.getEpisodeId());
        }
        return ShowDetailsFrag.create(this.getVideoId(), this.getEpisodeId());
    }
    
    @Override
    protected Fragment createSecondaryFrag() {
        final boolean b = true;
        boolean b2 = true;
        if (Config_Ab7994.shouldRenderTabs((Context)this)) {
            final String videoId = this.getVideoId();
            final String episodeId = this.getEpisodeId();
            if (this.shouldHideDetailsView()) {
                b2 = false;
            }
            return (Fragment)EpisodesFrag_Ab7994.create(videoId, episodeId, b2);
        }
        return (Fragment)EpisodesFrag.create(this.getVideoId(), this.getEpisodeId(), !this.shouldHideDetailsView() && b);
    }
    
    protected void dataReady(final ShowDetails showDetails) {
        if (this.getPrimaryFrag() instanceof ShowDetailsFrag) {
            ((ShowDetailsFrag)this.getPrimaryFrag()).dataReady(showDetails);
        }
    }
    
    @Override
    public boolean destroyed() {
        return AndroidUtils.isActivityFinishedOrDestroyed((Context)this);
    }
    
    @Override
    public AbsEpisodeView$EpisodeRowListener getEpisodeRowListener() {
        final AbsEpisodeView$EpisodeRowListener episodeRowListener = super.getEpisodeRowListener();
        AbsEpisodeView$EpisodeRowListener absEpisodeView$EpisodeRowListener = this;
        if (episodeRowListener != null) {
            absEpisodeView$EpisodeRowListener = episodeRowListener;
        }
        return absEpisodeView$EpisodeRowListener;
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
        final EpisodesFrag episodesFrag = (EpisodesFrag)this.getSecondaryFrag();
        if (this.shouldHideDetailsView()) {
            this.getPrimaryFragContainer().setVisibility(0);
            episodesFrag.setDetailViewGroupVisibility(8);
            return;
        }
        this.getPrimaryFragContainer().setVisibility(8);
        episodesFrag.setDetailViewGroupVisibility(0);
    }
}
