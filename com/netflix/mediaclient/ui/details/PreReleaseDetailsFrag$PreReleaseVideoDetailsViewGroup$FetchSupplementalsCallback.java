// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.text.TextUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.TextView;
import android.widget.Button;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.os.Bundle;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class PreReleaseDetailsFrag$PreReleaseVideoDetailsViewGroup$FetchSupplementalsCallback extends LoggingManagerCallback
{
    private final long requestId;
    final /* synthetic */ PreReleaseDetailsFrag$PreReleaseVideoDetailsViewGroup this$1;
    
    public PreReleaseDetailsFrag$PreReleaseVideoDetailsViewGroup$FetchSupplementalsCallback(final PreReleaseDetailsFrag$PreReleaseVideoDetailsViewGroup this$1, final long requestId) {
        this.this$1 = this$1;
        super("PreReleaseVideoDetailsViewGroup");
        this.requestId = requestId;
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
        super.onMovieDetailsFetched(movieDetails, status);
        if (this.requestId != this.this$1.this$0.requestId || this.this$1.this$0.isDestroyed()) {
            Log.v("PreReleaseVideoDetailsViewGroup", "Ignoring stale callback");
        }
        else if (movieDetails != null) {
            this.setupTrailerPlay(movieDetails.getPlayable());
        }
    }
    
    void setupTrailerPlay(final Playable playable) {
        this.this$1.playButton.setVisibility(0);
        this.this$1.playButton.requestFocus();
        this.this$1.playButton.setOnClickListener((View$OnClickListener)new PreReleaseDetailsFrag$PreReleaseVideoDetailsViewGroup$FetchSupplementalsCallback$1(this, playable));
    }
}
