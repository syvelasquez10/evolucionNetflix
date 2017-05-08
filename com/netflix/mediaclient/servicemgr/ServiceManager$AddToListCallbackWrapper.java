// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.widget.TextView;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.NetflixService;
import android.content.ServiceConnection;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.android.app.Status;

class ServiceManager$AddToListCallbackWrapper extends SimpleManagerCallback
{
    private final ManagerCallback cb;
    final /* synthetic */ ServiceManager this$0;
    private final String videoId;
    
    public ServiceManager$AddToListCallbackWrapper(final ServiceManager this$0, final ManagerCallback cb, final String videoId) {
        this.this$0 = this$0;
        this.cb = cb;
        this.videoId = videoId;
        final AddToMyListWrapper access$700 = this$0.addToMyListWrapper;
        if (access$700 != null) {
            access$700.updateToLoading(videoId);
        }
    }
    
    private void updateListeners(final Status status, final boolean b, final boolean b2) {
        final AddToMyListWrapper access$700 = this.this$0.addToMyListWrapper;
        if (access$700 == null) {
            return;
        }
        if (status.isSucces()) {
            access$700.updateState(this.videoId, b);
            return;
        }
        access$700.updateToError(status, this.videoId, b, b2);
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
        super.onMovieDetailsFetched(movieDetails, status);
        this.cb.onMovieDetailsFetched(movieDetails, status);
        this.updateListeners(status, movieDetails != null && movieDetails.isInQueue(), false);
    }
    
    @Override
    public void onQueueAdd(final Status status) {
        this.onQueueAdd(status);
        this.cb.onQueueAdd(status);
        this.updateListeners(status, true, true);
    }
    
    @Override
    public void onQueueRemove(final Status status) {
        this.onQueueRemove(status);
        this.cb.onQueueRemove(status);
        this.updateListeners(status, false, true);
    }
    
    @Override
    public void onShowDetailsAndSeasonsFetched(final ShowDetails showDetails, final List<SeasonDetails> list, final Status status) {
        super.onShowDetailsAndSeasonsFetched(showDetails, list, status);
        this.cb.onShowDetailsAndSeasonsFetched(showDetails, list, status);
        this.updateListeners(status, showDetails != null && showDetails.isInQueue(), false);
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        super.onShowDetailsFetched(showDetails, status);
        this.cb.onShowDetailsFetched(showDetails, status);
        this.updateListeners(status, showDetails != null && showDetails.isInQueue(), false);
    }
}
