// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import android.view.View;
import android.os.Bundle;

public class PreReleaseMovieDetailsFrag extends MovieDetailsFrag
{
    private static final String TAG = "PreReleaseMovieDetailsFrag";
    
    public static PreReleaseMovieDetailsFrag create(final String s) {
        final PreReleaseMovieDetailsFrag preReleaseMovieDetailsFrag = new PreReleaseMovieDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("extra_video_id", s);
        preReleaseMovieDetailsFrag.setArguments(arguments);
        return preReleaseMovieDetailsFrag;
    }
    
    @Override
    protected void initDetailsViewGroup(final View view) {
        (this.detailsViewGroup = new PreReleaseMovieDetailsFrag$PreReleaseVideoDetailsViewGroup(this, (Context)this.getActivity())).removeActionBarDummyView();
        this.detailsViewGroup.showRelatedTitle();
        if (DeviceUtils.isLandscape((Context)this.getActivity()) && DeviceUtils.isTabletByContext((Context)this.getActivity())) {
            this.detailsViewGroup.setPadding(0, this.getNetflixActivity().getActionBarHeight(), 0, 0);
        }
    }
    
    boolean isSupplementalMessageAvailable() {
        final VideoDetails videoDetails = this.getVideoDetails();
        return videoDetails != null && StringUtils.isNotEmpty(videoDetails.getSupplementalMessage());
    }
    
    @Override
    protected void showSimsItems(final MovieDetails movieDetails) {
        this.detailsViewGroup.hideRelatedTitle();
    }
}
