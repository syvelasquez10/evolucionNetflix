// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.netflixcom;

import android.app.Activity;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfGenreSummary;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.LoLoMo;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class NetflixComBrowseHandler$1FetchLoLoMoSummaryCallback extends SimpleManagerCallback
{
    final /* synthetic */ NetflixComBrowseHandler this$0;
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ String val$genreId;
    
    NetflixComBrowseHandler$1FetchLoLoMoSummaryCallback(final NetflixComBrowseHandler this$0, final NetflixActivity val$activity, final String val$genreId) {
        this.this$0 = this$0;
        this.val$activity = val$activity;
        this.val$genreId = val$genreId;
    }
    
    @Override
    public void onLoLoMoSummaryFetched(final LoLoMo loLoMo, final Status status) {
        if (status.isSucces()) {
            HomeActivity.showGenreList(this.val$activity, new ListOfGenreSummary(loLoMo.getNumLoMos(), -1, -1, "", loLoMo.getTitle(), this.val$genreId, false, loLoMo.getType().toString()));
        }
        else {
            NetflixComUtils.startHomeActivity(this.val$activity);
            Log.w("NetflixComBrowseHandler", "Couldn't fetch genre details, launching HomeActivity");
        }
        NflxProtocolUtils.reportDelayedResponseHandled(this.val$activity);
    }
}
