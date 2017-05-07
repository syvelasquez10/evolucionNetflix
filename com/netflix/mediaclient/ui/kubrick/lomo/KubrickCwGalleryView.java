// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import android.content.res.Resources;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.android.widget.PressedStateHandler;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
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
        this.setBackgroundResource(2130837899);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        netflixActivity.getLayoutInflater().inflate(2130903102, (ViewGroup)this);
        this.title = (TextView)this.findViewById(2131427495);
        this.seasonInfo = (TextView)this.findViewById(2131427548);
        this.img = (AdvancedImageView)this.findViewById(2131427493);
        this.progress = (ProgressBar)this.findViewById(2131427497);
        this.infoIcon = this.findViewById(2131427496);
        this.playIcon = this.findViewById(2131427547);
        this.clicker = new VideoDetailsClickListener(netflixActivity, this);
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
        String s;
        if (b2) {
            s = cwVideo.createModifiedBigStillUrl();
        }
        else {
            s = cwVideo.createModifiedStillUrl();
        }
        if (Log.isLoggable()) {
            Log.v("KubrickCwGalleryView", "Updating for video: " + cwVideo.getTitle() + ", videoPos: " + n + ", imgUrl: " + s);
        }
        this.playContext = new PlayContextImp(trackable, n);
        this.setVisibility(0);
        final String format = String.format(this.getResources().getString(2131493174), cwVideo.getTitle());
        this.title.setText((CharSequence)cwVideo.getTitle());
        if (VideoType.SHOW.equals(cwVideo.getType())) {
            this.seasonInfo.setText((CharSequence)this.getContext().getString(2131493241, new Object[] { cwVideo.getSeasonNumber(), cwVideo.getEpisodeNumber() }));
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
        imageLoader.showImg(img, s, bif, format, dark, true, n3);
        int progress;
        if (cwVideo.getRuntime() > 0) {
            progress = cwVideo.getPlayableBookmarkPosition() * 100 / cwVideo.getRuntime();
        }
        else {
            progress = 0;
        }
        this.progress.setProgress(progress);
        this.img.setOnClickListener((View$OnClickListener)new KubrickCwGalleryView$1(this, cwVideo));
        this.infoIcon.setContentDescription((CharSequence)String.format(this.getResources().getString(2131493211), cwVideo.getTitle()));
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
            n4 = 2131296454;
        }
        else {
            n4 = 2131296453;
        }
        final int dimensionPixelSize = resources.getDimensionPixelSize(n4);
        final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(dimensionPixelSize, dimensionPixelSize, 17);
        if (n == 0) {
            layoutParams.setMargins(0, 0, 0, this.getResources().getDimensionPixelSize(2131296342));
        }
        this.playIcon.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
}
