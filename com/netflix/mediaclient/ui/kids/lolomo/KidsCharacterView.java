// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import android.view.View;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.Trackable;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.TopCropImageView;
import android.widget.ImageView;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import android.widget.RelativeLayout;

@SuppressLint({ "ViewConstructor" })
public class KidsCharacterView extends RelativeLayout implements IVideoView<Video>
{
    private static final String TAG = "KidsCharacterView";
    private ImageView bg;
    private TopCropImageView img;
    private PlayContext playContext;
    
    public KidsCharacterView(final Context context, final boolean b) {
        super(context);
        this.setFocusable(true);
        this.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, KidsUtils.computeRowHeight((NetflixActivity)this.getContext(), b)));
        this.playContext = PlayContext.EMPTY_CONTEXT;
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        netflixActivity.getLayoutInflater().inflate(2130903090, (ViewGroup)this);
        this.bg = (ImageView)this.findViewById(2131165374);
        final boolean shouldShowHorizontalImages = KidsUtils.shouldShowHorizontalImages(netflixActivity);
        final ImageView bg = this.bg;
        int imageResource;
        if (shouldShowHorizontalImages) {
            imageResource = 2130837720;
        }
        else {
            imageResource = 2130837721;
        }
        bg.setImageResource(imageResource);
        (this.img = (TopCropImageView)this.findViewById(2131165375)).setCornerRadius(this.getResources().getDimensionPixelSize(2131361904));
        if (shouldShowHorizontalImages) {
            final int dimensionPixelSize = this.getResources().getDimensionPixelSize(2131361907);
            this.img.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        }
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, null, IClientLogging.AssetType.bif, null, false, false);
        this.setVisibility(4);
    }
    
    public void update(final Video video, final Trackable trackable, int n, final boolean b) {
        if (Log.isLoggable("KidsCharacterView", 2)) {
            Log.v("KidsCharacterView", "Updating for video: " + video.toString());
        }
        this.playContext = new PlayContextImp(trackable, n);
        this.setVisibility(0);
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        final TopCropImageView img = this.img;
        final String boxshotURL = video.getBoxshotURL();
        final IClientLogging.AssetType bif = IClientLogging.AssetType.bif;
        final String title = video.getTitle();
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        imageLoader.showImg(img, boxshotURL, bif, title, false, true, n);
        this.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                DetailsActivity.show((NetflixActivity)KidsCharacterView.this.getContext(), video, KidsCharacterView.this.playContext);
            }
        });
    }
}
