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
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import android.view.View$OnClickListener;

class PreReleaseDetailsFrag$PreReleaseVideoDetailsViewGroup$FetchSupplementalsCallback$1 implements View$OnClickListener
{
    final /* synthetic */ PreReleaseDetailsFrag$PreReleaseVideoDetailsViewGroup$FetchSupplementalsCallback this$2;
    final /* synthetic */ Playable val$playable;
    
    PreReleaseDetailsFrag$PreReleaseVideoDetailsViewGroup$FetchSupplementalsCallback$1(final PreReleaseDetailsFrag$PreReleaseVideoDetailsViewGroup$FetchSupplementalsCallback this$2, final Playable val$playable) {
        this.this$2 = this$2;
        this.val$playable = val$playable;
    }
    
    public void onClick(final View view) {
        final PlayContext playContext = ((PlayContextProvider)this.this$2.this$1.this$0.getActivity()).getPlayContext();
        playContext.setPlayLocation(PlayLocationType.STORY_ART);
        PlaybackLauncher.startPlaybackAfterPIN((NetflixActivity)this.this$2.this$1.getContext(), this.val$playable, playContext, 0);
    }
}
