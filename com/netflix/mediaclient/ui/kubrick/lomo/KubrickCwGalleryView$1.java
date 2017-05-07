// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import android.content.res.Resources;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.android.widget.PressedStateHandler;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.view.ViewGroup;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import android.widget.ProgressBar;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import android.widget.FrameLayout;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import android.view.View$OnClickListener;

class KubrickCwGalleryView$1 implements View$OnClickListener
{
    final /* synthetic */ KubrickCwGalleryView this$0;
    final /* synthetic */ CWVideo val$video;
    
    KubrickCwGalleryView$1(final KubrickCwGalleryView this$0, final CWVideo val$video) {
        this.this$0 = this$0;
        this.val$video = val$video;
    }
    
    public void onClick(final View view) {
        PlaybackLauncher.startPlaybackAfterPIN((NetflixActivity)this.this$0.getContext(), this.val$video, this.this$0.playContext);
    }
}
