// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.Trackable;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.lolomo.KidsHorizontalVideoView;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.MovieDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.BaseAdapter;

public class KidsMovieDetailsAdapter extends BaseAdapter
{
    private final NetflixActivity activity;
    private final MovieDetails movieDetails;
    
    public KidsMovieDetailsAdapter(final NetflixFrag netflixFrag, final MovieDetails movieDetails) {
        this.activity = netflixFrag.getNetflixActivity();
        this.movieDetails = movieDetails;
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
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        Object o = view;
        if (view == null) {
            o = new KidsHorizontalVideoView((Context)this.activity, false);
            final int dimensionPixelSize = this.activity.getResources().getDimensionPixelSize(2131361835);
            ((View)o).setPadding(dimensionPixelSize, 0, dimensionPixelSize, dimensionPixelSize);
        }
        final PlayContext playContext = ((PlayContextProvider)this.activity).getPlayContext();
        ((KidsHorizontalVideoView)o).update(this.getItem(n), playContext, playContext.getVideoPos(), false);
        return (View)o;
    }
}
