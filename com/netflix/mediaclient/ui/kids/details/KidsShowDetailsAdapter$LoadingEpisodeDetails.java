// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.details.DummyEpisodeDetails;

class KidsShowDetailsAdapter$LoadingEpisodeDetails extends DummyEpisodeDetails
{
    private final NetflixActivity activity;
    
    public KidsShowDetailsAdapter$LoadingEpisodeDetails(final NetflixActivity activity) {
        super(-1);
        this.activity = activity;
    }
    
    @Override
    public String getTitle() {
        return this.activity.getString(2131493158);
    }
}
