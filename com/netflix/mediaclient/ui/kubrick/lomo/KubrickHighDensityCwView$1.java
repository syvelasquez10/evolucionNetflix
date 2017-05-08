// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import android.view.View$OnClickListener;

class KubrickHighDensityCwView$1 implements View$OnClickListener
{
    final /* synthetic */ KubrickHighDensityCwView this$0;
    final /* synthetic */ CWVideo val$video;
    
    KubrickHighDensityCwView$1(final KubrickHighDensityCwView this$0, final CWVideo val$video) {
        this.this$0 = this$0;
        this.val$video = val$video;
    }
    
    public void onClick(final View view) {
        PlaybackLauncher.startPlaybackAfterPIN((NetflixActivity)this.this$0.getContext(), this.val$video, this.this$0.playContext);
    }
}
