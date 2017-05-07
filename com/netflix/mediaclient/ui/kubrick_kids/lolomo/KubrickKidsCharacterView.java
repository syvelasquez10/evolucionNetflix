// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.lolomo;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.widget.ImageView$ScaleType;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import java.util.List;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import android.widget.FrameLayout;

@SuppressLint({ "ViewConstructor" })
public class KubrickKidsCharacterView extends FrameLayout implements VideoViewGroup$IVideoView<Video>
{
    private static final List<Integer> BACKGROUND_DRAWABLE_IDS;
    private static final String TAG = "KubrickKidsCharacterView";
    private final AdvancedImageView bg;
    private final AdvancedImageView img;
    private PlayContext playContext;
    
    static {
        BACKGROUND_DRAWABLE_IDS = new KubrickKidsCharacterView$1();
    }
    
    public KubrickKidsCharacterView(final Context context) {
        super(context);
        this.setFocusable(true);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        final int dimensionPixelSize = this.getResources().getDimensionPixelSize(2131362008);
        this.addView((View)(this.bg = new AdvancedImageView(context)), (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(dimensionPixelSize, dimensionPixelSize, 17));
        final int dimensionPixelSize2 = this.getResources().getDimensionPixelSize(2131362007);
        final FrameLayout$LayoutParams frameLayout$LayoutParams = new FrameLayout$LayoutParams(dimensionPixelSize2, dimensionPixelSize2, 17);
        (this.img = new AdvancedImageView(context)).setCornerRadius(dimensionPixelSize2 / 2);
        this.img.setScaleType(ImageView$ScaleType.CENTER_CROP);
        this.addView((View)this.img, (ViewGroup$LayoutParams)frameLayout$LayoutParams);
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, null, IClientLogging$AssetType.bif, null, false, false);
        this.setVisibility(4);
    }
    
    public void update(final Video video, final Trackable trackable, int n, final boolean b, final boolean b2) {
        final String boxshotUrl = video.getBoxshotUrl();
        if (Log.isLoggable("KubrickKidsCharacterView", 2)) {
            Log.v("KubrickKidsCharacterView", "Updating for video: " + video.toString() + ", videoPos: " + n + ", img url: " + boxshotUrl);
        }
        this.playContext = new PlayContextImp(trackable, n);
        this.setVisibility(0);
        this.bg.setBackgroundResource((int)KubrickKidsCharacterView.BACKGROUND_DRAWABLE_IDS.get(n % KubrickKidsCharacterView.BACKGROUND_DRAWABLE_IDS.size()));
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        final AdvancedImageView img = this.img;
        final IClientLogging$AssetType bif = IClientLogging$AssetType.bif;
        final String title = video.getTitle();
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        imageLoader.showImg(img, boxshotUrl, bif, title, false, true, n);
        this.setOnClickListener((View$OnClickListener)new KubrickKidsCharacterView$2(this, video));
    }
}
