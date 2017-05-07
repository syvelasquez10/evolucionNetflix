// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;

class VideoDetailsViewGroup$3 implements View$OnClickListener
{
    final /* synthetic */ VideoDetailsViewGroup this$0;
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ VideoDetails val$details;
    
    VideoDetailsViewGroup$3(final VideoDetailsViewGroup this$0, final NetflixActivity val$activity, final VideoDetails val$details) {
        this.this$0 = this$0;
        this.val$activity = val$activity;
        this.val$details = val$details;
    }
    
    public void onClick(final View view) {
        PlaybackLauncher.startPlaybackAfterPIN(this.val$activity, this.val$details.getPlayable(), ((PlayContextProvider)this.val$activity).getPlayContext());
    }
}
