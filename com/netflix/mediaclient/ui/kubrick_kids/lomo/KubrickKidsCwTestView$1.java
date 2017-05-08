// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.lomo;

import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.util.CWTestUtil;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickHighDensityCwView;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import android.view.View$OnClickListener;

class KubrickKidsCwTestView$1 implements View$OnClickListener
{
    final /* synthetic */ KubrickKidsCwTestView this$0;
    final /* synthetic */ CWVideo val$video;
    
    KubrickKidsCwTestView$1(final KubrickKidsCwTestView this$0, final CWVideo val$video) {
        this.this$0 = this$0;
        this.val$video = val$video;
    }
    
    public void onClick(final View view) {
        PlaybackLauncher.startPlaybackAfterPIN((NetflixActivity)this.this$0.getContext(), this.val$video, this.this$0.playContext);
    }
}
