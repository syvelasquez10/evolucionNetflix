// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.lolomo;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.widget.ImageView$ScaleType;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import java.util.List;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import android.widget.FrameLayout;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.Video;
import android.view.View$OnClickListener;

class KubrickKidsCharacterView$2 implements View$OnClickListener
{
    final /* synthetic */ KubrickKidsCharacterView this$0;
    final /* synthetic */ Video val$video;
    
    KubrickKidsCharacterView$2(final KubrickKidsCharacterView this$0, final Video val$video) {
        this.this$0 = this$0;
        this.val$video = val$video;
    }
    
    public void onClick(final View view) {
        DetailsActivityLauncher.show((NetflixActivity)this.this$0.getContext(), this.val$video, this.this$0.playContext);
    }
}
