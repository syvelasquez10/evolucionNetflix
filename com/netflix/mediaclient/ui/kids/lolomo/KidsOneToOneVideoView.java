// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ImageView$ScaleType;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.view.ViewGroup;
import android.app.Activity;
import android.content.Context;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import android.widget.RelativeLayout;

@SuppressLint({ "ViewConstructor" })
public class KidsOneToOneVideoView extends RelativeLayout implements IVideoView<Video>
{
    private VideoDetailsClickListener clicker;
    private AdvancedImageView mainImage;
    private PlayContext playContext;
    private AdvancedImageView tvCard;
    
    public KidsOneToOneVideoView(final Context context, final boolean b) {
        super(context);
        ((Activity)context).getLayoutInflater().inflate(2130903105, (ViewGroup)this);
        this.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, -1));
        (this.mainImage = (AdvancedImageView)this.findViewById(2131165420)).setCornerRadius(context.getResources().getDimensionPixelSize(2131361923));
        this.mainImage.setScaleType(ImageView$ScaleType.CENTER_CROP);
        (this.tvCard = (AdvancedImageView)this.findViewById(2131165422)).setPressedStateHandlerEnabled(false);
        this.mainImage.setFocusable(true);
        this.tvCard.setFocusable(true);
        this.clicker = new VideoDetailsClickListener((NetflixActivity)this.getContext(), this);
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.mainImage, null, null, null, false, false);
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.tvCard, null, null, null, false, false);
        this.setVisibility(4);
        this.clicker.remove((View)this);
    }
    
    public void update(final Video video, final Trackable trackable, int n, final boolean b) {
        this.playContext = new PlayContextImp(trackable, n);
        this.clicker.update((View)this, video);
        this.setVisibility(0);
        this.setContentDescription((CharSequence)video.getTitle());
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        final AdvancedImageView mainImage = this.mainImage;
        final String squareUrl = video.getSquareUrl();
        final IClientLogging.AssetType boxArt = IClientLogging.AssetType.boxArt;
        final String title = video.getTitle();
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        imageLoader.showImg(mainImage, squareUrl, boxArt, title, false, true, n);
        final ImageLoader imageLoader2 = NetflixActivity.getImageLoader(this.getContext());
        final AdvancedImageView tvCard = this.tvCard;
        final String tvCardUrl = video.getTvCardUrl();
        final IClientLogging.AssetType boxArt2 = IClientLogging.AssetType.boxArt;
        final String title2 = video.getTitle();
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        imageLoader2.showImg(tvCard, tvCardUrl, boxArt2, title2, false, true, n);
    }
}
