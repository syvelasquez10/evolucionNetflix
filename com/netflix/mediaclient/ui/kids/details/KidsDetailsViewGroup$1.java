// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import android.view.View$OnClickListener;

class KidsDetailsViewGroup$1 implements View$OnClickListener
{
    final /* synthetic */ KidsDetailsViewGroup this$0;
    final /* synthetic */ VideoDetails val$details;
    
    KidsDetailsViewGroup$1(final KidsDetailsViewGroup this$0, final VideoDetails val$details) {
        this.this$0 = this$0;
        this.val$details = val$details;
    }
    
    public void onClick(final View view) {
        final NetflixActivity netflixActivity = (NetflixActivity)this.this$0.getContext();
        PlaybackLauncher.startPlaybackAfterPIN(netflixActivity, this.val$details.getPlayable(), ((PlayContextProvider)netflixActivity).getPlayContext());
    }
}
