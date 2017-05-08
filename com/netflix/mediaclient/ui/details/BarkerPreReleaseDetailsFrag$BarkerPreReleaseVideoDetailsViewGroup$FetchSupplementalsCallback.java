// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.text.TextUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.netflix.mediaclient.ui.kubrick.details.BarkerHelper$BarkerBars;
import android.widget.TextView;
import android.widget.Button;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.os.Bundle;
import com.netflix.mediaclient.ui.kubrick.details.BarkerMovieDetailsFrag;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class BarkerPreReleaseDetailsFrag$BarkerPreReleaseVideoDetailsViewGroup$FetchSupplementalsCallback extends LoggingManagerCallback
{
    private final long requestId;
    final /* synthetic */ BarkerPreReleaseDetailsFrag$BarkerPreReleaseVideoDetailsViewGroup this$1;
    
    public BarkerPreReleaseDetailsFrag$BarkerPreReleaseVideoDetailsViewGroup$FetchSupplementalsCallback(final BarkerPreReleaseDetailsFrag$BarkerPreReleaseVideoDetailsViewGroup this$1, final long requestId) {
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
        this.this$1.playButton.setOnClickListener((View$OnClickListener)new BarkerPreReleaseDetailsFrag$BarkerPreReleaseVideoDetailsViewGroup$FetchSupplementalsCallback$1(this, playable));
    }
}
