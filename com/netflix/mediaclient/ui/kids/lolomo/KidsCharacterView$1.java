// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import android.widget.RelativeLayout;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.Video;
import android.view.View$OnClickListener;

class KidsCharacterView$1 implements View$OnClickListener
{
    final /* synthetic */ KidsCharacterView this$0;
    final /* synthetic */ Video val$video;
    
    KidsCharacterView$1(final KidsCharacterView this$0, final Video val$video) {
        this.this$0 = this$0;
        this.val$video = val$video;
    }
    
    public void onClick(final View view) {
        DetailsActivity.show((NetflixActivity)this.this$0.getContext(), this.val$video, this.this$0.playContext);
    }
}
