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
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.app.DialogFragment;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;
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
    protected void onCreateOptionsMenu(final Menu menu, final Menu menu2) {
        if (menu2 != null) {
            menu2.add((CharSequence)"Display episodes dialog").setOnMenuItemClickListener((MenuItem$OnMenuItemClickListener)new MenuItem$OnMenuItemClickListener() {
                public boolean onMenuItemClick(final MenuItem menuItem) {
                    final NetflixDialogFrag create = EpisodeListFrag.create(ShowDetailsActivity.this.getVideoId(), ShowDetailsActivity.this.getEpisodeId(), false);
                    create.onManagerReady(ShowDetailsActivity.this.getServiceManager(), 0);
                    create.setCancelable(true);
                    ShowDetailsActivity.this.showDialog(create);
                    return true;
                }
            });
        }
        super.onCreateOptionsMenu(menu, menu2);
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
