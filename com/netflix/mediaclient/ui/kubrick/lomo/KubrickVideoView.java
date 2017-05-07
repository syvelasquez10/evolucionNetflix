// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ImageView$ScaleType;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import com.netflix.mediaclient.servicemgr.model.KubrickVideo;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;

public class KubrickVideoView extends AdvancedImageView implements VideoViewGroup$IVideoView<KubrickVideo>
{
    protected VideoDetailsClickListener clicker;
    protected PlayContext playContext;
    
    public KubrickVideoView(final Context context) {
        super(context);
        this.init();
    }
    
    public KubrickVideoView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public KubrickVideoView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void init() {
        this.playContext = PlayContext.EMPTY_CONTEXT;
        this.setFocusable(true);
        this.setBackgroundResource(2130837838);
        this.setScaleType(ImageView$ScaleType.CENTER_CROP);
        this.clicker = new VideoDetailsClickListener((NetflixActivity)this.getContext(), this);
    }
    
    @Override
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    @Override
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).showImg(this, null, null, null, false, false);
        this.setVisibility(4);
        this.clicker.remove((View)this);
    }
    
    @Override
    public void update(final KubrickVideo kubrickVideo, final Trackable trackable, int visibility, final boolean b) {
        this.playContext = new PlayContextImp(trackable, visibility);
        final String kubrickHorzImgUrl = kubrickVideo.getKubrickHorzImgUrl();
        if (StringUtils.isEmpty(kubrickHorzImgUrl)) {
            visibility = 4;
        }
        else {
            visibility = 0;
        }
        this.setVisibility(visibility);
        this.clicker.update((View)this, kubrickVideo);
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        final IClientLogging$AssetType boxArt = IClientLogging$AssetType.boxArt;
        final String title = kubrickVideo.getTitle();
        if (b) {
            visibility = 1;
        }
        else {
            visibility = 0;
        }
        imageLoader.showImg(this, kubrickHorzImgUrl, boxArt, title, true, true, visibility);
    }
}