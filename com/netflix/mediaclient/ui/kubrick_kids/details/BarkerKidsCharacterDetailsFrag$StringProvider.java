// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.content.Context;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup$DetailsStringProvider;

public class BarkerKidsCharacterDetailsFrag$StringProvider implements VideoDetailsViewGroup$DetailsStringProvider
{
    private final Context context;
    private final VideoDetails details;
    
    public BarkerKidsCharacterDetailsFrag$StringProvider(final Context context, final VideoDetails details) {
        this.context = context;
        this.details = details;
    }
    
    @Override
    public CharSequence getBasicInfoString() {
        return StringUtils.getBasicMovieInfoString(this.context, this.details);
    }
    
    @Override
    public CharSequence getCreatorsText() {
        return null;
    }
    
    @Override
    public CharSequence getGenresText() {
        if (StringUtils.isEmpty(this.details.getGenres())) {
            return null;
        }
        return StringUtils.createBoldLabeledText(this.context, 2131231109, this.details.getGenres());
    }
    
    @Override
    public CharSequence getStarringText() {
        return StringUtils.createBoldLabeledText(this.context, 2131231259, this.details.getActors());
    }
}
