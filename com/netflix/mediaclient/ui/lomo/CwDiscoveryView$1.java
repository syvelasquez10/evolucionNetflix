// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.support.v4.content.ContextCompat;
import android.content.Context;
import com.netflix.mediaclient.ui.lomo.discovery.extended.CWExtendedDiscoveryFrag$RemotePlaybackListener;
import com.netflix.mediaclient.ui.common.PlaybackLauncher$PlaybackTarget;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import android.view.View$OnClickListener;

class CwDiscoveryView$1 implements View$OnClickListener
{
    final /* synthetic */ CwDiscoveryView this$0;
    final /* synthetic */ CWVideo val$video;
    
    CwDiscoveryView$1(final CwDiscoveryView this$0, final CWVideo val$video) {
        this.this$0 = this$0;
        this.val$video = val$video;
    }
    
    public void onClick(final View view) {
        final NetflixActivity netflixActivity = (NetflixActivity)this.this$0.getContext();
        PlaybackLauncher.startPlaybackAfterPIN(netflixActivity, this.val$video, this.this$0.playContext);
        if (PlaybackLauncher.whereToPlay(netflixActivity.getServiceManager()) == PlaybackLauncher$PlaybackTarget.remote && this.this$0.remotePlaybackListener != null) {
            this.this$0.remotePlaybackListener.onRemotePlaybackInitiated();
        }
    }
}
