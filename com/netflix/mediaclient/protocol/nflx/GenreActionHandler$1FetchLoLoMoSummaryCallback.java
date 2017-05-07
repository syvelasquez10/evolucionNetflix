// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import android.app.Activity;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfGenreSummary;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.LoLoMo;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class GenreActionHandler$1FetchLoLoMoSummaryCallback extends SimpleManagerCallback
{
    private final NetflixActivity activity;
    private final String genreId;
    final /* synthetic */ GenreActionHandler this$0;
    
    GenreActionHandler$1FetchLoLoMoSummaryCallback(final GenreActionHandler this$0, final NetflixActivity activity, final String genreId) {
        this.this$0 = this$0;
        this.genreId = genreId;
        this.activity = activity;
    }
    
    @Override
    public void onLoLoMoSummaryFetched(final LoLoMo loLoMo, final Status status) {
        if (status.isSucces()) {
            HomeActivity.showGenreList(this.activity, new ListOfGenreSummary(loLoMo.getNumLoMos(), -1, -1, "", loLoMo.getTitle(), this.genreId, false, loLoMo.getType().toString()));
        }
        NflxProtocolUtils.reportDelayedResponseHandled(this.activity);
    }
}
