// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.Playable;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.view.View;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.Trackable;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.view.ViewGroup;
import android.app.Activity;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.CWVideo;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import android.widget.RelativeLayout;

@Deprecated
@SuppressLint({ "ViewConstructor" })
public class KidsOneToOneCwView extends RelativeLayout implements IVideoView<CWVideo>
{
    private static final String TAG = "KidsOneToOneCwView";
    private AdvancedImageView mainImg;
    private PlayContext playContext;
    private AdvancedImageView tvCard;
    
    public KidsOneToOneCwView(final Context context, final boolean b) {
        super(context);
        this.setFocusable(true);
        this.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, KidsUtils.computeRowHeight((NetflixActivity)this.getContext(), b)));
        final int dimensionPixelSize = this.getResources().getDimensionPixelSize(2131361836);
        this.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        ((Activity)this.getContext()).getLayoutInflater().inflate(2130903098, (ViewGroup)this);
        (this.mainImg = (AdvancedImageView)this.findViewById(2131165391)).setCornerRadius(this.getResources().getDimensionPixelSize(2131361905));
        (this.tvCard = (AdvancedImageView)this.findViewById(2131165398)).setPressedStateHandlerEnabled(false);
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.mainImg, null, null, null, false, false);
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.tvCard, null, null, null, false, false);
        this.setVisibility(4);
    }
    
    public void update(final CWVideo cwVideo, final Trackable trackable, int n, final boolean b) {
        if (Log.isLoggable("KidsOneToOneCwView", 2)) {
            Log.v("KidsOneToOneCwView", "Updating for video: " + cwVideo.toString());
        }
        this.playContext = new PlayContextImp(trackable, n);
        this.setVisibility(0);
        final String format = String.format(this.getResources().getString(2131493186), cwVideo.getTitle());
        this.setContentDescription((CharSequence)format);
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        final AdvancedImageView mainImg = this.mainImg;
        final String squareUrl = cwVideo.getSquareUrl();
        final IClientLogging.AssetType boxArt = IClientLogging.AssetType.boxArt;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        imageLoader.showImg(mainImg, squareUrl, boxArt, format, false, true, n);
        final ImageLoader imageLoader2 = NetflixActivity.getImageLoader(this.getContext());
        final AdvancedImageView tvCard = this.tvCard;
        final String tvCardUrl = cwVideo.getTvCardUrl();
        final IClientLogging.AssetType boxArt2 = IClientLogging.AssetType.boxArt;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        imageLoader2.showImg(tvCard, tvCardUrl, boxArt2, format, false, true, n);
        this.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                PlaybackLauncher.startPlaybackAfterPIN((NetflixActivity)KidsOneToOneCwView.this.getContext(), cwVideo, KidsOneToOneCwView.this.playContext);
            }
        });
    }
}
