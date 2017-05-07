// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.lolomo.KidsOneToOneVideoView;
import com.netflix.mediaclient.ui.kids.lolomo.KidsHorizontalVideoView;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.trackable.TrackableObject;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.BaseAdapter;

public class KidsMovieDetailsAdapter extends BaseAdapter
{
    private final NetflixActivity activity;
    private final MovieDetails movieDetails;
    private final Trackable trackable;
    
    public KidsMovieDetailsAdapter(final NetflixFrag netflixFrag, final MovieDetails movieDetails) {
        this.activity = netflixFrag.getNetflixActivity();
        this.movieDetails = movieDetails;
        this.trackable = new TrackableObject(this.movieDetails.getSimilarsRequestId(), this.movieDetails.getSimilarsTrackId(), this.movieDetails.getSimilarsListPos());
    }
    
    public int getCount() {
        return this.movieDetails.getSimilars().size();
    }
    
    public Video getItem(final int n) {
        return this.movieDetails.getSimilars().get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, View o, final ViewGroup viewGroup) {
        if (o == null) {
            if (KidsUtils.shouldShowHorizontalImages(this.activity)) {
                o = new KidsHorizontalVideoView(this.activity, false);
            }
            else {
                o = new KidsOneToOneVideoView((Context)this.activity, false);
            }
            final int dimensionPixelSize = this.activity.getResources().getDimensionPixelSize(2131361974);
            final int dimensionPixelSize2 = this.activity.getResources().getDimensionPixelSize(2131361975);
            ((View)o).setPadding(dimensionPixelSize, 0, dimensionPixelSize, dimensionPixelSize2);
            ((View)o).setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, KidsUtils.computeSkidmarkRowHeight(this.activity, dimensionPixelSize, 0, dimensionPixelSize, dimensionPixelSize2, false)));
        }
        ((VideoViewGroup$IVideoView)o).update(this.getItem(n), this.trackable, n, false);
        return (View)o;
    }
}
