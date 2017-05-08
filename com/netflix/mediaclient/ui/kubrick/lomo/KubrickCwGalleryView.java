// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import android.content.res.Resources;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.android.widget.PressedStateHandler;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import android.widget.ProgressBar;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.View;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import android.widget.FrameLayout;

public class KubrickCwGalleryView extends FrameLayout implements VideoViewGroup$IVideoView<CWVideo>
{
    private static final String TAG = "KubrickCwGalleryView";
    private VideoDetailsClickListener clicker;
    private AdvancedImageView img;
    private View infoIcon;
    private PlayContext playContext;
    private View playIcon;
    private ProgressBar progress;
    private TextView seasonInfo;
    private TextView title;
    
    public KubrickCwGalleryView(final Context context) {
        super(context);
        this.init();
    }
    
    public KubrickCwGalleryView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public KubrickCwGalleryView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void init() {
        this.setFocusable(true);
        this.setBackgroundResource(2130837936);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        netflixActivity.getLayoutInflater().inflate(2130903143, (ViewGroup)this);
        this.title = (TextView)this.findViewById(2131689740);
        this.seasonInfo = (TextView)this.findViewById(2131689868);
        this.img = (AdvancedImageView)this.findViewById(2131689737);
        this.progress = (ProgressBar)this.findViewById(2131689739);
        this.infoIcon = this.findViewById(2131689741);
        this.playIcon = this.findViewById(2131689867);
        this.clicker = new VideoDetailsClickListener(netflixActivity, this);
    }
    
    public String getImageUrl(final CWVideo cwVideo, final boolean b) {
        int n = 0;
        if (b) {
            n = 2;
        }
        return BrowseExperience.getLomoVideoViewImageUrl(this.getContext(), cwVideo, KubrickCwGalleryView.class, n);
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).clear(this.img);
        this.setVisibility(4);
        this.clicker.remove(this.infoIcon);
    }
    
    public void update(final CWVideo cwVideo, final Trackable trackable, int n, final boolean b, final boolean b2) {
        final int n2 = 1;
        final String imageUrl = this.getImageUrl(cwVideo, b2);
        if (Log.isLoggable()) {
            Log.v("KubrickCwGalleryView", "Updating for video: " + cwVideo.getTitle() + ", videoPos: " + n + ", imgUrl: " + imageUrl);
        }
        this.playContext = new PlayContextImp(trackable, n);
        this.setVisibility(0);
        final String format = String.format(this.getResources().getString(2131230893), cwVideo.getTitle());
        this.title.setText((CharSequence)cwVideo.getTitle());
        if (VideoType.SHOW.equals(cwVideo.getType())) {
            this.seasonInfo.setText((CharSequence)this.getContext().getString(2131231172, new Object[] { cwVideo.getSeasonAbbrSeqLabel(), cwVideo.getEpisodeNumber() }));
        }
        else {
            this.seasonInfo.setText((CharSequence)"");
        }
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        final AdvancedImageView img = this.img;
        final IClientLogging$AssetType bif = IClientLogging$AssetType.bif;
        final ImageLoader$StaticImgConfig dark = ImageLoader$StaticImgConfig.DARK;
        int n3;
        if (b) {
            n3 = 1;
        }
        else {
            n3 = 0;
        }
        imageLoader.showImg(img, imageUrl, bif, format, dark, true, n3);
        int progress;
        if (cwVideo.getRuntime() > 0) {
            progress = cwVideo.getPlayableBookmarkPosition() * 100 / cwVideo.getRuntime();
        }
        else {
            progress = 0;
        }
        this.progress.setProgress(progress);
        this.img.setOnClickListener((View$OnClickListener)new KubrickCwGalleryView$1(this, cwVideo));
        this.infoIcon.setContentDescription((CharSequence)String.format(this.getResources().getString(2131231046), cwVideo.getTitle()));
        this.clicker.update(this.infoIcon, cwVideo, null);
        if (n == 0) {
            n = n2;
        }
        else {
            n = 0;
        }
        final Resources resources = this.getResources();
        int n4;
        if (n != 0) {
            n4 = 2131362173;
        }
        else {
            n4 = 2131362174;
        }
        final int dimensionPixelSize = resources.getDimensionPixelSize(n4);
        final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(dimensionPixelSize, dimensionPixelSize, 17);
        if (n == 0) {
            layoutParams.setMargins(0, 0, 0, this.getResources().getDimensionPixelSize(2131362067));
        }
        this.playIcon.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
}
