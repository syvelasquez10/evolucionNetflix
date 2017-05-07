// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.lolomo;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.view.ViewGroup;
import android.app.Activity;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import android.widget.TextView;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import android.widget.RelativeLayout;

@SuppressLint({ "ViewConstructor" })
public class KidsHorizontalCwView extends RelativeLayout implements VideoViewGroup$IVideoView<CWVideo>
{
    private static final String TAG = "KidsHorizontalCwView";
    private AdvancedImageView img;
    private PlayContext playContext;
    private TextView title;
    
    public KidsHorizontalCwView(final Context context, final boolean b) {
        super(context);
        this.setFocusable(true);
        this.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, KidsUtils.computeHorizontalRowHeight((NetflixActivity)this.getContext(), b)));
        this.playContext = PlayContext.EMPTY_CONTEXT;
        ((Activity)this.getContext()).getLayoutInflater().inflate(2130903103, (ViewGroup)this);
        this.title = (TextView)this.findViewById(2131165416);
        (this.img = (AdvancedImageView)this.findViewById(2131165414)).setCornerRadius(this.getResources().getDimensionPixelSize(2131361964));
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, null, IClientLogging$AssetType.bif, null, false, false);
        this.setVisibility(4);
    }
    
    public void update(final CWVideo cwVideo, final Trackable trackable, int n, final boolean b) {
        if (Log.isLoggable("KidsHorizontalCwView", 2)) {
            Log.v("KidsHorizontalCwView", "Updating for video: " + cwVideo.toString());
        }
        this.playContext = new PlayContextImp(trackable, n);
        this.setVisibility(0);
        final String format = String.format(this.getResources().getString(2131493159), cwVideo.getTitle());
        this.setContentDescription((CharSequence)format);
        if (VideoType.SHOW.equals(cwVideo.getType())) {
            this.title.setText((CharSequence)this.getContext().getString(2131493219, new Object[] { cwVideo.getTitle(), cwVideo.getSeasonNumber(), cwVideo.getEpisodeNumber() }));
        }
        else {
            this.title.setText((CharSequence)cwVideo.getTitle());
        }
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        final AdvancedImageView img = this.img;
        final String interestingUrl = cwVideo.getInterestingUrl();
        final IClientLogging$AssetType bif = IClientLogging$AssetType.bif;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        imageLoader.showImg(img, interestingUrl, bif, format, false, true, n);
        this.setOnClickListener((View$OnClickListener)new KidsHorizontalCwView$1(this, cwVideo));
    }
}
