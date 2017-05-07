// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import android.widget.RelativeLayout;

@SuppressLint({ "ViewConstructor" })
public class KidsHorizontalVideoView extends RelativeLayout implements VideoViewGroup$IVideoView<Video>
{
    private VideoDetailsClickListener clicker;
    private AdvancedImageView img;
    private PlayContext playContext;
    
    public KidsHorizontalVideoView(final NetflixActivity netflixActivity, final boolean b) {
        super((Context)netflixActivity);
        this.setFocusable(true);
        this.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, -1));
        this.playContext = PlayContext.EMPTY_CONTEXT;
        netflixActivity.getLayoutInflater().inflate(2130903104, (ViewGroup)this);
        (this.img = (AdvancedImageView)this.findViewById(2131165417)).setCornerRadius(netflixActivity.getResources().getDimensionPixelSize(2131361964));
        this.clicker = new VideoDetailsClickListener((NetflixActivity)this.getContext(), this);
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, null, IClientLogging$AssetType.bif, null, false, false);
        this.setVisibility(4);
        this.clicker.remove((View)this);
    }
    
    public void update(final Video video, final Trackable trackable, int visibility, final boolean b) {
        this.playContext = new PlayContextImp(trackable, visibility);
        this.clicker.update((View)this, video);
        if (video.getHorzDispUrl() == null) {
            visibility = 4;
        }
        else {
            visibility = 0;
        }
        this.setVisibility(visibility);
        this.setContentDescription((CharSequence)video.getTitle());
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        final AdvancedImageView img = this.img;
        final String horzDispUrl = video.getHorzDispUrl();
        final IClientLogging$AssetType boxArt = IClientLogging$AssetType.boxArt;
        final String title = video.getTitle();
        if (b) {
            visibility = 1;
        }
        else {
            visibility = 0;
        }
        imageLoader.showImg(img, horzDispUrl, boxArt, title, false, true, visibility);
    }
}
