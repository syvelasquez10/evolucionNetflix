// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.content.Context;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;

class MovieDetailsFrag$4 implements VideoDetailsViewGroup$DetailsStringProvider
{
    final /* synthetic */ MovieDetailsFrag this$0;
    final /* synthetic */ MovieDetails val$details;
    
    MovieDetailsFrag$4(final MovieDetailsFrag this$0, final MovieDetails val$details) {
        this.this$0 = this$0;
        this.val$details = val$details;
    }
    
    @Override
    public CharSequence getBasicInfoString() {
        return StringUtils.getBasicMovieInfoString((Context)this.this$0.getActivity(), this.val$details);
    }
    
    @Override
    public CharSequence getCreatorsText() {
        if (StringUtils.isEmpty(this.val$details.getDirectors())) {
            return null;
        }
        return StringUtils.createBoldLabeledText((Context)this.this$0.getActivity(), this.this$0.getActivity().getResources().getQuantityString(2131623936, this.val$details.getNumDirectors()), this.val$details.getDirectors());
    }
    
    @Override
    public CharSequence getGenresText() {
        if (StringUtils.isEmpty(this.val$details.getGenres())) {
            return null;
        }
        return StringUtils.createBoldLabeledText((Context)this.this$0.getActivity(), 2131493172, this.val$details.getGenres());
    }
    
    @Override
    public CharSequence getStarringText() {
        return StringUtils.createBoldLabeledText((Context)this.this$0.getActivity(), 2131493170, this.val$details.getActors());
    }
}
