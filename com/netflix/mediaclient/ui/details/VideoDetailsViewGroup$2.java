// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.PlayLocationType;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.view.View;
import android.view.View$OnClickListener;

class VideoDetailsViewGroup$2 implements View$OnClickListener
{
    final /* synthetic */ VideoDetailsViewGroup this$0;
    
    VideoDetailsViewGroup$2(final VideoDetailsViewGroup this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        final PlayContext playContext = ((PlayContextProvider)this.this$0.getContext()).getPlayContext();
        playContext.setPlayLocation(PlayLocationType.STORY_ART);
        PlaybackLauncher.startPlaybackAfterPIN((NetflixActivity)this.this$0.getContext(), this.this$0.details.getPlayable(), playContext);
    }
}
