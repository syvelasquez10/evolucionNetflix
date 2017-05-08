// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.ui.kubrick.details.BarkerMovieDetailsFrag;

public class BarkerPreReleaseDetailsFrag extends BarkerMovieDetailsFrag
{
    private static final String TAG = "PreReleaseDetailsFrag";
    private boolean isMovie;
    
    public BarkerPreReleaseDetailsFrag() {
        this.isMovie = true;
    }
    
    public static BarkerPreReleaseDetailsFrag create(final String s, final boolean b) {
        final BarkerPreReleaseDetailsFrag barkerPreReleaseDetailsFrag = new BarkerPreReleaseDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("video_id", s);
        arguments.putBoolean("extra_is_movie", b);
        barkerPreReleaseDetailsFrag.setArguments(arguments);
        return barkerPreReleaseDetailsFrag;
    }
    
    @Override
    protected void fetchMovieData() {
        if (this.isMovie) {
            super.fetchMovieData();
            return;
        }
        this.fetchShowData();
    }
    
    protected void fetchShowData() {
        final ServiceManager serviceManager = this.getServiceManager();
        if (serviceManager == null || !serviceManager.isReady()) {
            Log.w("PreReleaseDetailsFrag", "Manager is null/notReady - can't reload data");
            return;
        }
        this.isLoading = true;
        this.requestId = System.nanoTime();
        serviceManager.getBrowse().fetchShowDetails(this.videoId, null, false, new BarkerPreReleaseDetailsFrag$FetchShowDetailsCallback(this, this.requestId));
    }
    
    @Override
    protected void initDetailsViewGroup(final View view) {
        (this.detailsViewGroup = new BarkerPreReleaseDetailsFrag$BarkerPreReleaseVideoDetailsViewGroup(this, (Context)this.getActivity())).removeActionBarDummyView();
        this.detailsViewGroup.showRelatedTitle();
    }
    
    boolean isSupplementalMessageAvailable() {
        final VideoDetails videoDetails = this.getVideoDetails();
        return videoDetails != null && StringUtils.isNotEmpty(videoDetails.getSupplementalMessage());
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        this.isMovie = this.getArguments().getBoolean("extra_is_movie", false);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    @Override
    protected void showSimsItems(final MovieDetails movieDetails) {
        this.detailsViewGroup.hideRelatedTitle();
    }
    
    @Override
    protected void updateBookmark(final MovieDetails movieDetails) {
    }
}
