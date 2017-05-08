// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.util.gfx.ImageLoader;
import android.graphics.Bitmap$Config;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.util.TimeUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.FeatureEnabledProvider;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$RatingBarDataProvider;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.RelativeLayout$LayoutParams;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.ViewUtils;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.ui.details.NetflixRatingBar;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.TopCropImageView;
import android.view.View;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;
import com.netflix.mediaclient.ui.lomo.VideoViewGroup$IVideoView;
import android.widget.RelativeLayout;

public class KubrickHeroView extends RelativeLayout implements VideoViewGroup$IVideoView<KubrickVideo>
{
    private static final String TAG = "KubrickHeroView";
    private TextView certification;
    protected VideoDetailsClickListener clicker;
    private TextView durationInfo;
    private View hdIcon;
    private TopCropImageView heroImg;
    private View infoGroup;
    protected PlayContext playContext;
    private NetflixRatingBar rating;
    private final BroadcastReceiver ratingsUpdateBroadcastReceiver;
    private View shadow;
    private TextView synopsis;
    private TextView title;
    private AdvancedImageView titleImg;
    private String videoId;
    private TextView year;
    
    public KubrickHeroView(final Context context) {
        super(context);
        this.ratingsUpdateBroadcastReceiver = new KubrickHeroView$2(this);
        this.init();
    }
    
    public KubrickHeroView(final Context context, final AttributeSet set) {
        super(context, set);
        this.ratingsUpdateBroadcastReceiver = new KubrickHeroView$2(this);
        this.init();
    }
    
    public KubrickHeroView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.ratingsUpdateBroadcastReceiver = new KubrickHeroView$2(this);
        this.init();
    }
    
    private void findViews() {
        this.heroImg = (TopCropImageView)this.findViewById(2131689899);
        this.title = (TextView)this.findViewById(2131689902);
        this.titleImg = (AdvancedImageView)this.findViewById(2131689662);
        this.rating = (NetflixRatingBar)this.findViewById(2131689625);
        this.year = (TextView)this.findViewById(2131689626);
        this.certification = (TextView)this.findViewById(2131689627);
        this.durationInfo = (TextView)this.findViewById(2131689628);
        this.hdIcon = this.findViewById(2131689629);
        this.synopsis = (TextView)this.findViewById(2131689904);
        this.infoGroup = this.findViewById(2131689901);
        this.shadow = this.findViewById(2131689900);
    }
    
    private void init() {
        this.setFocusable(true);
        this.setBackgroundResource(2130837990);
        ViewUtils.setPaddingBottom((View)this, this.getResources().getDimensionPixelOffset(2131362347));
        this.playContext = PlayContext.EMPTY_CONTEXT;
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        netflixActivity.getLayoutInflater().inflate(2130903157, (ViewGroup)this);
        this.findViews();
        this.heroImg.setCropPointYOffsetPx(0);
        ((RelativeLayout$LayoutParams)this.heroImg.getLayoutParams()).height = (int)(DeviceUtils.getScreenWidthInPixels((Context)netflixActivity) * 0.5625f);
        final float n = DeviceUtils.getScreenWidthInPixels(this.getContext());
        float n2;
        if (DeviceUtils.isPortrait(this.getContext())) {
            n2 = 0.667f;
        }
        else {
            n2 = 0.5f;
        }
        this.synopsis.getLayoutParams().width = (int)(n2 * n);
        this.clicker = new VideoDetailsClickListener(netflixActivity, this);
        ViewUtils.setPaddingStart(this.infoGroup, LoMoUtils.getLomoFragImageOffsetLeftPx(netflixActivity));
        this.infoGroup.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new KubrickHeroView$1(this));
    }
    
    private void updateBasicInfo(final KubrickVideo kubrickVideo) {
        this.rating.update(null, kubrickVideo);
        final View hdIcon = this.hdIcon;
        int visibility;
        if (DeviceUtils.shouldShowHdIcon((NetflixActivity)this.getContext(), kubrickVideo)) {
            visibility = 0;
        }
        else {
            visibility = 8;
        }
        hdIcon.setVisibility(visibility);
        this.updateYear(kubrickVideo);
        this.updateCertification(kubrickVideo);
        this.updateDuration(kubrickVideo);
    }
    
    private void updateCertification(final KubrickVideo kubrickVideo) {
        final String certification = kubrickVideo.getCertification();
        this.certification.setText((CharSequence)certification);
        final TextView certification2 = this.certification;
        int visibility;
        if (StringUtils.isEmpty(certification)) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        certification2.setVisibility(visibility);
    }
    
    private void updateDuration(final KubrickVideo kubrickVideo) {
        if (kubrickVideo.getType() == VideoType.SHOW) {
            if (kubrickVideo.getSeasonCount() > 0) {
                this.durationInfo.setText((CharSequence)kubrickVideo.getNumSeasonsLabel());
                this.durationInfo.setVisibility(0);
                return;
            }
            this.durationInfo.setVisibility(8);
        }
        else {
            final int runtime = kubrickVideo.getRuntime();
            if (runtime > 0) {
                this.durationInfo.setText((CharSequence)TimeUtils.getFormattedTime(runtime, this.getContext()));
                this.durationInfo.setVisibility(0);
                return;
            }
            this.durationInfo.setVisibility(8);
        }
    }
    
    private void updateSynopsis(final KubrickVideo kubrickVideo) {
        final String narrative = kubrickVideo.getNarrative();
        this.synopsis.setText((CharSequence)narrative);
        final TextView synopsis = this.synopsis;
        int visibility;
        if (StringUtils.isEmpty(narrative)) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        synopsis.setVisibility(visibility);
    }
    
    private void updateYear(final KubrickVideo kubrickVideo) {
        final String value = String.valueOf(kubrickVideo.getYear());
        this.year.setText((CharSequence)value);
        final TextView year = this.year;
        int visibility;
        if (StringUtils.isEmpty(value)) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        year.setVisibility(visibility);
    }
    
    public String getImageUrl(final KubrickVideo kubrickVideo, final boolean b) {
        return BrowseExperience.getLomoVideoViewImageUrl(this.getContext(), kubrickVideo, KubrickHeroView.class, 0);
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).clear(this.heroImg);
        this.setVisibility(4);
        this.clicker.remove((View)this);
        this.videoId = null;
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(this.ratingsUpdateBroadcastReceiver, new IntentFilter("com.netflix.falkor.ACTION_NOTIFY_OF_RATINGS_CHANGE"));
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(this.ratingsUpdateBroadcastReceiver);
    }
    
    public void update(final KubrickVideo kubrickVideo, final Trackable trackable, int visibility, final boolean b, final boolean b2) {
        final String imageUrl = this.getImageUrl(kubrickVideo, b2);
        if (Log.isLoggable()) {
            Log.v("KubrickHeroView", "update - imgUrl: " + imageUrl);
        }
        this.playContext = new PlayContextImp(trackable, visibility);
        this.videoId = kubrickVideo.getId();
        this.updateBasicInfo(kubrickVideo);
        this.updateSynopsis(kubrickVideo);
        final String titleImgUrl = kubrickVideo.getTitleImgUrl();
        if (StringUtils.isEmpty(titleImgUrl) || !AndroidUtils.canDisplayTransparentWebpImages()) {
            this.title.setText((CharSequence)kubrickVideo.getTitle());
            this.title.setVisibility(0);
            this.titleImg.setVisibility(8);
        }
        else {
            this.title.setVisibility(8);
            this.titleImg.setVisibility(0);
            NetflixActivity.getImageLoader(this.getContext()).showImg(this.titleImg, titleImgUrl, IClientLogging$AssetType.heroImage, kubrickVideo.getTitle(), ImageLoader$StaticImgConfig.DARK_NO_PLACEHOLDER, true, 1, Bitmap$Config.RGB_565);
        }
        final TopCropImageView heroImg = this.heroImg;
        if (StringUtils.isEmpty(imageUrl)) {
            visibility = 4;
        }
        else {
            visibility = 0;
        }
        heroImg.setVisibility(visibility);
        final ImageLoader imageLoader = NetflixActivity.getImageLoader(this.getContext());
        final TopCropImageView heroImg2 = this.heroImg;
        final IClientLogging$AssetType heroImage = IClientLogging$AssetType.heroImage;
        final String title = kubrickVideo.getTitle();
        final ImageLoader$StaticImgConfig dark = ImageLoader$StaticImgConfig.DARK;
        if (b) {
            visibility = 1;
        }
        else {
            visibility = 0;
        }
        imageLoader.showImg(heroImg2, imageUrl, heroImage, title, dark, true, visibility, Bitmap$Config.ARGB_8888);
        this.clicker.update((View)this, kubrickVideo, this.heroImg.getPressedStateHandler());
    }
}
