// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.BillboardInteractionType;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.os.Parcelable;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import android.content.res.Resources;
import android.view.ViewGroup$LayoutParams;
import com.netflix.model.leafs.originals.BillboardLogo;
import com.netflix.model.leafs.originals.BillboardSummary;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper$PlaybackEventsListener;
import android.net.Uri;
import android.text.TextUtils;
import com.netflix.model.leafs.originals.BillboardBackground;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.util.AttributeSet;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import android.content.Context;
import android.widget.FrameLayout;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.TextureView;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper;
import android.view.View;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import android.widget.RelativeLayout;

public class BillboardView extends RelativeLayout implements VideoViewGroup$IVideoView<Billboard>
{
    private static final String TAG = "BillboardView";
    private VideoDetailsClickListener detailsListener;
    private TextView info;
    private View infoButton;
    private String infoText;
    private View infoViewGroup;
    private boolean isTablet;
    private TextView label;
    private String mGUID;
    private MediaPlayerWrapper mediaPlayerWrapper;
    private TextureView motionStoryArt;
    private TextView muteButton;
    private View playButton;
    private PlayContext playContext;
    private int playerCompletedLoops;
    private int playerSeekPosition;
    private View shadowOverlay;
    private View shadowOverlayGradient;
    private boolean showArtworkOnly;
    private AdvancedImageView storyArt;
    private FrameLayout storyArtFrame;
    private String storyArtUrl;
    private TextView synopsis;
    private String synopsisText;
    private AdvancedImageView tvCard;
    private String tvCardUrl;
    
    public BillboardView(final Context context) {
        super(context);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        this.isTablet = true;
        this.mGUID = UIViewLogUtils.MISSING_GUID;
        this.playerCompletedLoops = 0;
        this.init();
    }
    
    public BillboardView(final Context context, final AttributeSet set) {
        super(context, set);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        this.isTablet = true;
        this.mGUID = UIViewLogUtils.MISSING_GUID;
        this.playerCompletedLoops = 0;
        this.init();
    }
    
    public BillboardView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        this.isTablet = true;
        this.mGUID = UIViewLogUtils.MISSING_GUID;
        this.playerCompletedLoops = 0;
        this.init();
    }
    
    private String getDefaultInfoText(final Billboard billboard) {
        CharSequence charSequence;
        if (billboard.getType() == VideoType.MOVIE) {
            charSequence = StringUtils.getBasicMovieInfoString(this.getContext(), billboard.getYear(), billboard.getCertification(), billboard.getRuntime());
        }
        else {
            charSequence = StringUtils.getBasicShowInfoString(this.getContext(), billboard.getYear(), billboard.getCertification(), billboard.getNumOfSeasons());
        }
        return (String)charSequence;
    }
    
    private void hideMotionBB() {
        this.storyArt.setVisibility(0);
        this.motionStoryArt.setVisibility(8);
        this.muteButton.setOnClickListener((View$OnClickListener)null);
        this.muteButton.setVisibility(8);
    }
    
    private void init() {
        this.setFocusable(true);
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        this.detailsListener = new VideoDetailsClickListener(netflixActivity, this);
        netflixActivity.getLayoutInflater().inflate(2130903073, (ViewGroup)this);
        if (netflixActivity.getServiceManager() != null) {
            this.isTablet = netflixActivity.getServiceManager().isTablet();
        }
        this.showArtworkOnly = shouldShowArtworkOnly(netflixActivity);
        if (Log.isLoggable()) {
            Log.v("BillboardView", "isTablet: " + this.isTablet + ", showArtworkOnly: " + this.showArtworkOnly);
        }
        this.infoViewGroup = this.findViewById(2131624079);
        this.label = (TextView)this.findViewById(2131624080);
        this.info = (TextView)this.findViewById(2131624082);
        this.synopsis = (TextView)this.findViewById(2131624083);
        this.tvCard = (AdvancedImageView)this.findViewById(2131624081);
        this.storyArtFrame = (FrameLayout)this.findViewById(2131624074);
        this.storyArt = (AdvancedImageView)this.findViewById(2131624075);
        this.motionStoryArt = (TextureView)this.findViewById(2131624076);
        this.muteButton = (TextView)this.findViewById(2131624087);
        int n;
        if (this.showArtworkOnly) {
            n = 0;
        }
        else {
            n = DeviceUtils.getScreenWidthInPixels((Context)netflixActivity) / 3;
        }
        this.storyArtFrame.setPadding(n, 0, 0, 0);
        this.shadowOverlay = this.findViewById(2131624077);
        ((RelativeLayout$LayoutParams)this.shadowOverlay.getLayoutParams()).width = n;
        this.shadowOverlayGradient = this.findViewById(2131624078);
        ((RelativeLayout$LayoutParams)this.shadowOverlayGradient.getLayoutParams()).leftMargin = n;
        this.playButton = this.findViewById(2131624085);
        this.infoButton = this.findViewById(2131624086);
        this.updateViewVisibility();
        this.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new BillboardView$1(this));
    }
    
    private void setCustomBackground(final BillboardBackground billboardBackground) {
        if (billboardBackground != null) {
            this.storyArtUrl = billboardBackground.getUrl();
            if (!billboardBackground.getTone().equals("light")) {
                this.synopsis.setTextColor(this.getResources().getColor(2131558414));
                this.synopsis.setShadowLayer(2.0f, 0.0f, -1.0f, 2131558573);
                this.info.setTextColor(this.getResources().getColor(2131558592));
                this.info.setShadowLayer(2.0f, 0.0f, -1.0f, 2131558573);
                return;
            }
            this.synopsis.setTextColor(this.getResources().getColor(2131558415));
            this.synopsis.setShadowLayer(2.0f, 0.0f, -1.0f, 2131558416);
            this.info.setTextColor(this.getResources().getColor(2131558416));
            this.info.setShadowLayer(2.0f, 0.0f, -1.0f, 2131558416);
        }
    }
    
    private void setUpMotionBillboard(final String s, final boolean b) {
        if (!TextUtils.isEmpty((CharSequence)s) && (this.mediaPlayerWrapper == null || !this.mediaPlayerWrapper.donePlaying())) {
            if (Log.isLoggable()) {
                Log.v("BillboardView", "Showing motion billboard with action buttons, video url: " + s);
            }
            this.storyArt.setVisibility(0);
            this.muteButton.setOnClickListener((View$OnClickListener)new BillboardView$4(this));
            if (this.mediaPlayerWrapper == null) {
                this.mediaPlayerWrapper = new MediaPlayerWrapper((NetflixActivity)this.getContext(), this.motionStoryArt, Uri.parse(s).buildUpon().clearQuery().build().toString(), b, 3, new BillboardView$5(this));
            }
            this.mediaPlayerWrapper.initParams(this.playerSeekPosition, this.playerCompletedLoops);
            return;
        }
        this.hideMotionBB();
    }
    
    public static boolean shouldShowArtworkOnly(final NetflixActivity netflixActivity) {
        boolean b;
        if (DeviceUtils.getScreenResolutionDpi((Context)netflixActivity) <= 240) {
            b = true;
        }
        else {
            b = false;
        }
        final boolean b2 = netflixActivity.getServiceManager() == null || netflixActivity.getServiceManager().isTablet();
        return (b && DeviceUtils.isPortrait((Context)netflixActivity)) || (!b2 && DeviceUtils.isPortrait((Context)netflixActivity));
    }
    
    private void showMotionBB() {
        this.storyArt.setVisibility(8);
        this.motionStoryArt.setVisibility(0);
        this.updateMuteButton();
        this.muteButton.setVisibility(0);
    }
    
    private void showOriginalBillboard(final BillboardSummary billboardSummary, final VideoType videoType) {
        if (billboardSummary != null) {
            int n = 2131296420;
            int n2 = 2131296421;
            if (billboardSummary.isOriginal()) {
                this.storyArtFrame.setPadding(0, 0, 0, 0);
                this.shadowOverlay.setVisibility(8);
                this.shadowOverlayGradient.setVisibility(8);
                n = 2131296418;
                n2 = 2131296419;
                this.setCustomBackground(billboardSummary.getBackground());
                final BillboardLogo logo = billboardSummary.getLogo();
                if (logo != null) {
                    this.tvCardUrl = logo.getUrl();
                }
                this.synopsisText = billboardSummary.getSynopsis();
            }
            final ViewGroup$LayoutParams layoutParams = this.tvCard.getLayoutParams();
            layoutParams.height = this.getResources().getDimensionPixelSize(n);
            layoutParams.width = this.getResources().getDimensionPixelSize(n2);
            this.tvCard.setLayoutParams(layoutParams);
            if (!TextUtils.isEmpty((CharSequence)billboardSummary.getSupplementalMessage())) {
                this.infoText = billboardSummary.getSupplementalMessage();
                this.info.setTypeface(this.info.getTypeface(), 1);
                this.info.setSingleLine(false);
                this.info.setMaxLines(2);
            }
            else {
                this.info.setSingleLine(true);
                if (videoType == VideoType.MOVIE) {
                    this.infoText = StringUtils.getBasicMovieInfoString(this.getContext(), Integer.parseInt(billboardSummary.getYear()), billboardSummary.getMaturityRating(), Integer.parseInt(billboardSummary.getRuntime())).toString();
                }
                else {
                    this.infoText = StringUtils.getBasicShowInfoString(this.getContext(), Integer.parseInt(billboardSummary.getYear()), billboardSummary.getMaturityRating(), Integer.parseInt(billboardSummary.getSeasons())).toString();
                }
            }
            this.setUpMotionBillboard(billboardSummary.getMotionUrl(), billboardSummary.getMotionShouldLoop());
        }
    }
    
    private void updateMuteButton() {
        final TextView muteButton = this.muteButton;
        final Resources resources = this.getResources();
        int n;
        if (this.mediaPlayerWrapper.isMuted()) {
            n = 2131165753;
        }
        else {
            n = 2131165755;
        }
        muteButton.setText((CharSequence)resources.getString(n));
    }
    
    private void updateViewVisibility() {
        final boolean b = false;
        final View infoViewGroup = this.infoViewGroup;
        int visibility;
        if (this.showArtworkOnly) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        infoViewGroup.setVisibility(visibility);
        final View shadowOverlay = this.shadowOverlay;
        int visibility2;
        if (this.showArtworkOnly) {
            visibility2 = 8;
        }
        else {
            visibility2 = 0;
        }
        shadowOverlay.setVisibility(visibility2);
        final View shadowOverlayGradient = this.shadowOverlayGradient;
        int visibility3;
        if (this.showArtworkOnly) {
            visibility3 = 8;
        }
        else {
            visibility3 = 0;
        }
        shadowOverlayGradient.setVisibility(visibility3);
        this.storyArt.setVisibility(0);
        final TextView label = this.label;
        int visibility4;
        if (this.isTablet) {
            visibility4 = 8;
        }
        else {
            visibility4 = 0;
        }
        label.setVisibility(visibility4);
        final TextView synopsis = this.synopsis;
        int visibility5;
        if (this.isTablet) {
            visibility5 = (b ? 1 : 0);
        }
        else {
            visibility5 = 8;
        }
        synopsis.setVisibility(visibility5);
    }
    
    public String getGUID() {
        if (this.mGUID == UIViewLogUtils.MISSING_GUID) {
            this.mGUID = ConsolidatedLoggingUtils.createGUID();
        }
        return this.mGUID;
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).clear(this.tvCard);
        NetflixActivity.getImageLoader(this.getContext()).clear(this.storyArt);
        this.setVisibility(4);
        this.detailsListener.remove((View)this.storyArt);
        this.mediaPlayerWrapper.releaseResources();
    }
    
    public void onRestoreInstanceState(final Parcelable parcelable) {
        final BillboardView$PlayerSavedState billboardView$PlayerSavedState = (BillboardView$PlayerSavedState)parcelable;
        super.onRestoreInstanceState(billboardView$PlayerSavedState.getSuperState());
        this.playerCompletedLoops = billboardView$PlayerSavedState.completedLoops;
        this.playerSeekPosition = billboardView$PlayerSavedState.seekPosition;
        this.requestLayout();
    }
    
    public Parcelable onSaveInstanceState() {
        final BillboardView$PlayerSavedState billboardView$PlayerSavedState = new BillboardView$PlayerSavedState(super.onSaveInstanceState());
        billboardView$PlayerSavedState.seekPosition = this.mediaPlayerWrapper.getCurrentPosition();
        billboardView$PlayerSavedState.completedLoops = this.mediaPlayerWrapper.getCompletedLoops();
        return (Parcelable)billboardView$PlayerSavedState;
    }
    
    public void update(final Billboard billboard, final Trackable trackable, final int n, final boolean b, final boolean b2) {
        final ServiceManager serviceManager = ((NetflixActivity)this.getContext()).getServiceManager();
        this.playContext = new PlayContextImp(trackable, n);
        if (Log.isLoggable()) {
            Log.v("BillboardView", "updating billboard, title: " + billboard.getTitle() + ", story url: " + billboard.getStoryUrl());
        }
        this.setVisibility(0);
        final String format = String.format(this.getResources().getString(2131165355), billboard.getTitle());
        this.setContentDescription((CharSequence)format);
        if (this.showArtworkOnly) {
            this.storyArt.setPressedStateHandlerEnabled(true);
            this.detailsListener.update((View)this.storyArt, billboard, this.storyArt.getPressedStateHandler());
            String s;
            if (this.isTablet) {
                s = billboard.getHighResolutionLandscapeBoxArtUrl();
            }
            else {
                s = billboard.getHorzDispUrl();
            }
            if (Log.isLoggable()) {
                Log.v("BillboardView", "Showing artwork only, image url: " + s);
            }
            NetflixActivity.getImageLoader(this.getContext()).showImg(this.storyArt, s, IClientLogging$AssetType.merchStill, format, ImageLoader$StaticImgConfig.DARK, true, 1);
        }
        else {
            this.storyArtUrl = billboard.getStoryUrl();
            this.tvCardUrl = billboard.getTvCardUrl();
            this.synopsisText = billboard.getSynopsis();
            this.infoText = this.getDefaultInfoText(billboard);
            this.showOriginalBillboard(billboard.getBillboardSummary(), billboard.getType());
            if (Log.isLoggable()) {
                Log.v("BillboardView", "Showing static billboard with action buttons, image url: " + this.storyArtUrl);
            }
            NetflixActivity.getImageLoader(this.getContext()).showImg(this.storyArt, this.storyArtUrl, IClientLogging$AssetType.merchStill, format, ImageLoader$StaticImgConfig.DARK, true, 1);
            NetflixActivity.getImageLoader(this.getContext()).showImg(this.tvCard, this.tvCardUrl, IClientLogging$AssetType.boxArt, format, ImageLoader$StaticImgConfig.DARK_NO_PLACEHOLDER, true, 1);
            this.storyArt.setPressedStateHandlerEnabled(false);
            this.detailsListener.remove((View)this.storyArt);
            this.info.setText((CharSequence)this.infoText);
            this.synopsis.setText((CharSequence)this.synopsisText);
            ServiceManagerUtils.cacheManifestIfEnabled(serviceManager, billboard, this.playContext);
            this.playButton.setOnClickListener((View$OnClickListener)new BillboardView$2(this, serviceManager, billboard));
            this.infoButton.setOnClickListener((View$OnClickListener)new BillboardView$3(this, serviceManager, billboard));
        }
        if (serviceManager != null && serviceManager.isReady()) {
            Log.v("BillboardView", "Loggin billboard impression for video: " + billboard.getId());
            serviceManager.getBrowse().logBillboardActivity(billboard, BillboardInteractionType.IMPRESSION);
        }
    }
}
