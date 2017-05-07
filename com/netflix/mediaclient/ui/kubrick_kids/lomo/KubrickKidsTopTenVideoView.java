// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.lomo;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import android.view.View;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickVideoView;

public class KubrickKidsTopTenVideoView extends KubrickVideoView
{
    public KubrickKidsTopTenVideoView(final Context context) {
        super(context);
    }
    
    public KubrickKidsTopTenVideoView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public KubrickKidsTopTenVideoView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    @Override
    public void update(final KubrickVideo kubrickVideo, final Trackable trackable, int visibility, final boolean b, final boolean b2) {
        this.playContext = new PlayContextImp(trackable, visibility);
        final String horzDispUrl = kubrickVideo.getHorzDispUrl();
        if (StringUtils.isEmpty(horzDispUrl)) {
            visibility = 4;
        }
        else {
            visibility = 0;
        }
        this.setVisibility(visibility);
        this.clicker.update((View)this, kubrickVideo, this.pressedHandler);
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        final IClientLogging$AssetType boxArt = IClientLogging$AssetType.boxArt;
        final String title = kubrickVideo.getTitle();
        final ImageLoader$StaticImgConfig light = ImageLoader$StaticImgConfig.LIGHT;
        if (b) {
            visibility = 1;
        }
        else {
            visibility = 0;
        }
        imageLoader.showImg(this, horzDispUrl, boxArt, title, light, true, visibility);
    }
}
