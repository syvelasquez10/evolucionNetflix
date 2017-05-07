// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.ViewGroup;
import android.app.Activity;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.ui.lomo.BasePaginatedAdapter;
import android.content.Context;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import android.widget.RelativeLayout;

@SuppressLint({ "ViewConstructor" })
public class KidsHorizontalVideoView extends RelativeLayout implements IVideoView<Video>
{
    private VideoDetailsClickListener clicker;
    private AdvancedImageView img;
    private PlayContext playContext;
    
    public KidsHorizontalVideoView(final Context context, final boolean b) {
        super(context);
        this.setFocusable(true);
        this.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, (int)(BasePaginatedAdapter.computeViewPagerWidth(this.getContext(), b) * 0.5625f)));
        this.playContext = PlayContext.EMPTY_CONTEXT;
        ((Activity)this.getContext()).getLayoutInflater().inflate(2130903094, (ViewGroup)this);
        (this.img = (AdvancedImageView)this.findViewById(2131165390)).setCornerRadius(context.getResources().getDimensionPixelSize(2131361904));
        this.clicker = new VideoDetailsClickListener((NetflixActivity)this.getContext(), this);
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, null, IClientLogging.AssetType.bif, null, false, false);
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
        final IClientLogging.AssetType boxArt = IClientLogging.AssetType.boxArt;
        final String title = video.getTitle();
        if (b) {
            visibility = 1;
        }
        else {
            visibility = 0;
        }
        imageLoader.showImg(img, horzDispUrl, boxArt, title, true, true, visibility);
    }
}
