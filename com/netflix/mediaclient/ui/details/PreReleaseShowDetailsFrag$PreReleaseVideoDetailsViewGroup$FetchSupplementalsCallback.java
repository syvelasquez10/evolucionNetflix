// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.text.TextUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.TextView;
import android.widget.Button;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import android.view.View;
import android.os.Bundle;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class PreReleaseShowDetailsFrag$PreReleaseVideoDetailsViewGroup$FetchSupplementalsCallback extends LoggingManagerCallback
{
    private final long requestId;
    final /* synthetic */ PreReleaseShowDetailsFrag$PreReleaseVideoDetailsViewGroup this$1;
    
    public PreReleaseShowDetailsFrag$PreReleaseVideoDetailsViewGroup$FetchSupplementalsCallback(final PreReleaseShowDetailsFrag$PreReleaseVideoDetailsViewGroup this$1, final long requestId) {
        this.this$1 = this$1;
        super("PreReleaseVideoDetailsViewGroup");
        this.requestId = requestId;
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        super.onShowDetailsFetched(showDetails, status);
        if (this.requestId != this.this$1.this$0.requestId || this.this$1.this$0.isDestroyed()) {
            Log.v("PreReleaseVideoDetailsViewGroup", "Ignoring stale callback");
        }
        else if (showDetails != null) {
            this.setupTrailerPlay(showDetails.getPlayable());
        }
    }
    
    void setupTrailerPlay(final Playable playable) {
        this.this$1.playButton.setVisibility(0);
        this.this$1.playButton.requestFocus();
        this.this$1.playButton.setOnClickListener((View$OnClickListener)new PreReleaseShowDetailsFrag$PreReleaseVideoDetailsViewGroup$FetchSupplementalsCallback$1(this, playable));
    }
}
