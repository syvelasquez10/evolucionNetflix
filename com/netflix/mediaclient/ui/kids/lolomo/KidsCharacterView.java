// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import android.widget.RelativeLayout;

@SuppressLint({ "ViewConstructor" })
public class KidsCharacterView extends RelativeLayout implements VideoViewGroup$IVideoView<Video>
{
    private static final String TAG = "KidsCharacterView";
    private final AdvancedImageView img;
    private PlayContext playContext;
    
    public KidsCharacterView(final Context context, final boolean b) {
        super(context);
        this.setFocusable(true);
        final int computeCharacterViewSize = KidsUtils.computeCharacterViewSize((NetflixActivity)this.getContext(), b);
        this.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(computeCharacterViewSize, computeCharacterViewSize));
        this.playContext = PlayContext.EMPTY_CONTEXT;
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        netflixActivity.getLayoutInflater().inflate(2130903101, (ViewGroup)this);
        (this.img = (AdvancedImageView)this.findViewById(2131165385)).setCornerRadius(this.getResources().getDimensionPixelSize(2131361965));
        final boolean kidsWithUpDownScrolling = KidsUtils.isKidsWithUpDownScrolling(netflixActivity);
        Log.v("KidsCharacterView", "Setting padding, isSkidmark: " + kidsWithUpDownScrolling);
        if (kidsWithUpDownScrolling) {
            this.img.setPadding(AndroidUtils.dipToPixels((Context)netflixActivity, 0), AndroidUtils.dipToPixels((Context)netflixActivity, 2), AndroidUtils.dipToPixels((Context)netflixActivity, 4), AndroidUtils.dipToPixels((Context)netflixActivity, 6));
            this.setPadding(0, 0, this.getResources().getDimensionPixelSize(2131361974), this.getResources().getDimensionPixelSize(2131361975));
            return;
        }
        this.img.setPadding(AndroidUtils.dipToPixels((Context)netflixActivity, 0), AndroidUtils.dipToPixels((Context)netflixActivity, 2), AndroidUtils.dipToPixels((Context)netflixActivity, 1), AndroidUtils.dipToPixels((Context)netflixActivity, 6));
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, null, IClientLogging$AssetType.bif, null, false, false);
        this.setVisibility(4);
    }
    
    public void update(final Video video, final Trackable trackable, int n, final boolean b) {
        if (Log.isLoggable("KidsCharacterView", 2)) {
            Log.v("KidsCharacterView", "Updating for video: " + video.toString());
        }
        this.playContext = new PlayContextImp(trackable, n);
        this.setVisibility(0);
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        final AdvancedImageView img = this.img;
        final String squareUrl = video.getSquareUrl();
        final IClientLogging$AssetType bif = IClientLogging$AssetType.bif;
        final String title = video.getTitle();
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        imageLoader.showImg(img, squareUrl, bif, title, false, true, n);
        this.setOnClickListener((View$OnClickListener)new KidsCharacterView$1(this, video));
    }
}
