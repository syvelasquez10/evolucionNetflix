// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker_kids.lolomo;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.widget.ImageView$ScaleType;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.support.v4.util.Pair;
import java.util.List;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import android.widget.FrameLayout;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import android.os.Bundle;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;

class BarkerKidsCharacterView$CharacterVideoDetailsClickListener extends VideoDetailsClickListener
{
    final /* synthetic */ BarkerKidsCharacterView this$0;
    
    public BarkerKidsCharacterView$CharacterVideoDetailsClickListener(final BarkerKidsCharacterView this$0, final NetflixActivity netflixActivity, final PlayContextProvider playContextProvider) {
        this.this$0 = this$0;
        super(netflixActivity, playContextProvider);
    }
    
    @Override
    protected void launchDetailsActivity(final NetflixActivity netflixActivity, final Video video, final PlayContext playContext) {
        final Bundle bundle = new Bundle();
        bundle.putInt("extra_kids_color_id", this.this$0.kidsColor);
        DetailsActivityLauncher.show(netflixActivity, video, playContext, "DeetsClickListener", bundle);
    }
}
