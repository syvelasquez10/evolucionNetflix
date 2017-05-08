// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.BillboardInteractionType;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.content.res.Resources;
import android.view.ViewGroup$LayoutParams;
import com.netflix.model.leafs.originals.BillboardDateBadge;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.model.leafs.originals.BillboardLogo;
import java.util.HashMap;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import android.net.Uri;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper$PlaybackEventsListener;
import android.text.TextUtils;
import com.netflix.model.leafs.originals.BillboardBackground;
import com.netflix.model.leafs.originals.BillboardSummary;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.model.leafs.originals.BillboardCTA;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import android.util.AttributeSet;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import android.content.Context;
import android.widget.FrameLayout;
import com.netflix.mediaclient.android.widget.TopCropImageView;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.TextureView;
import com.netflix.mediaclient.ui.common.MediaPlayerWrapper;
import android.view.View;
import java.util.Map;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.Button;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import android.widget.RelativeLayout;

public class BillboardView extends RelativeLayout implements VideoViewGroup$IVideoView<Billboard>
{
    public static final int MOTION_BB_MAX_LOOPS = 3;
    public static final float MUTE_VOLUME = 0.0f;
    private static final String TAG = "BillboardView";
    private AddToListData$StateListener addToListWrapper;
    private Button cta1Button;
    private Button cta2Button;
    private AdvancedImageView dateBadge;
    private VideoDetailsClickListener detailsListener;
    private TextView episodeBadge;
    private Map<String, String> impressionParams;
    private TextView info;
    private Button infoButton;
    private String infoText;
    private View infoViewGroup;
    private View infoWrapper;
    private boolean isTablet;
    private String mGUID;
    private MediaPlayerWrapper mediaPlayerWrapper;
    private boolean motionBillboardsEnabled;
    private TextureView motionStoryArt;
    private TextView muteButton;
    private Button myListButton;
    private PlayContext playContext;
    private View shadowOverlay;
    private View shadowOverlayGradient;
    private boolean showArtworkOnly;
    private TopCropImageView storyArt;
    private FrameLayout storyArtFrame;
    private String storyArtUrl;
    private TextView synopsis;
    private String synopsisText;
    private AdvancedImageView tvCard;
    private String tvCardUrl;
    private Billboard video;
    
    public BillboardView(final Context context) {
        super(context);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        this.isTablet = true;
        this.mGUID = UIViewLogUtils.MISSING_GUID;
        this.motionBillboardsEnabled = false;
        this.init();
    }
    
    public BillboardView(final Context context, final AttributeSet set) {
        super(context, set);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        this.isTablet = true;
        this.mGUID = UIViewLogUtils.MISSING_GUID;
        this.motionBillboardsEnabled = false;
        this.init();
    }
    
    public BillboardView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        this.isTablet = true;
        this.mGUID = UIViewLogUtils.MISSING_GUID;
        this.motionBillboardsEnabled = false;
        this.init();
    }
    
    private void downloadVideo(final String s) {
        if (Log.isLoggable()) {
            Log.d("BillboardView", "Downloading motion billboard video - url: " + s);
        }
        ((NetflixActivity)this.getContext()).getServiceManager().fetchAndCacheResource(s, IClientLogging$AssetType.motionBillboard, new BillboardView$5(this));
    }
    
    private void generateCallsToAction(final Billboard billboard) {
        final ServiceManager serviceManager = ((NetflixActivity)this.getContext()).getServiceManager();
        if (serviceManager == null || !serviceManager.isReady()) {
            Log.w("BillboardView", "Manager is null/notReady - can't reload data");
            return;
        }
        List<BillboardCTA> actions;
        if (billboard == null || billboard.getBillboardSummary() == null || billboard.getBillboardSummary().getActions() == null) {
            actions = new ArrayList<BillboardCTA>();
            actions.add(new BillboardCTA("", false, "play", "", false, "play", this.video.getId()));
        }
        else {
            actions = billboard.getBillboardSummary().getActions();
        }
        this.infoButton.setVisibility(8);
        if (actions.size() >= 2) {
            this.setupCTAButton(actions.get(1), this.cta2Button);
            this.myListButton.setVisibility(8);
        }
        else {
            this.showMyListButton(this.video.getId(), this.video.getType());
            this.cta2Button.setVisibility(8);
        }
        if (actions.size() >= 1) {
            this.setupCTAButton(actions.get(0), this.cta1Button);
            return;
        }
        this.cta1Button.setVisibility(8);
    }
    
    private String getDefaultInfoText(final Billboard billboard) {
        CharSequence charSequence;
        if (billboard.getType() == VideoType.MOVIE) {
            charSequence = StringUtils.getBasicMovieInfoString(this.getContext(), billboard.getYear(), billboard.getCertification(), billboard.getRuntime());
        }
        else {
            charSequence = StringUtils.getBasicShowInfoString(this.getContext(), billboard.getYear(), billboard.getCertification(), billboard.getNumSeasonsLabel());
        }
        return (String)charSequence;
    }
    
    private void hideMotionBB() {
        Log.v("BillboardView", "Hiding motion BB");
        this.storyArt.setVisibility(0);
        this.motionStoryArt.setVisibility(8);
        this.muteButton.setOnClickListener((View$OnClickListener)null);
        this.muteButton.setVisibility(8);
    }
    
    private void init() {
        boolean motionBillboardsEnabled = false;
        this.setFocusable(true);
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        this.detailsListener = new VideoDetailsClickListener(netflixActivity, this);
        netflixActivity.getLayoutInflater().inflate(2130903071, (ViewGroup)this);
        this.infoViewGroup = this.findViewById(2131624074);
        this.info = (TextView)this.findViewById(2131624078);
        this.infoWrapper = this.findViewById(2131624076);
        this.episodeBadge = (TextView)this.findViewById(2131624077);
        this.dateBadge = (AdvancedImageView)this.findViewById(2131624079);
        this.synopsis = (TextView)this.findViewById(2131624080);
        this.tvCard = (AdvancedImageView)this.findViewById(2131624075);
        this.storyArtFrame = (FrameLayout)this.findViewById(2131624069);
        this.storyArt = (TopCropImageView)this.findViewById(2131624070);
        this.motionStoryArt = (TextureView)this.findViewById(2131624071);
        this.muteButton = (TextView)this.findViewById(2131624086);
        if (netflixActivity.getServiceManager() != null) {
            this.isTablet = netflixActivity.getServiceManager().isTablet();
        }
        this.showArtworkOnly = shouldShowArtworkOnly(netflixActivity);
        if (Log.isLoggable()) {
            Log.v("BillboardView", "isTablet: " + this.isTablet + ", showArtworkOnly: " + this.showArtworkOnly);
        }
        int n;
        if (this.showArtworkOnly) {
            n = 0;
        }
        else {
            n = DeviceUtils.getScreenWidthInPixels((Context)netflixActivity) / 3;
        }
        ViewUtils.setPaddingStart((View)this.storyArtFrame, n);
        this.shadowOverlay = this.findViewById(2131624072);
        ((RelativeLayout$LayoutParams)this.shadowOverlay.getLayoutParams()).width = n;
        this.shadowOverlayGradient = this.findViewById(2131624073);
        ((RelativeLayout$LayoutParams)this.shadowOverlayGradient.getLayoutParams()).setMarginStart(n);
        this.cta1Button = (Button)this.findViewById(2131624082);
        this.cta2Button = (Button)this.findViewById(2131624083);
        this.myListButton = (Button)this.findViewById(2131624084);
        this.infoButton = (Button)this.findViewById(2131624085);
        this.updateViewVisibility();
        if (PersistentConfig.getMotionBBTestCell(this.getContext()).ordinal() == ABTestConfig$Cell.CELL_TWO.ordinal()) {
            motionBillboardsEnabled = true;
        }
        this.motionBillboardsEnabled = motionBillboardsEnabled;
        this.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new BillboardView$1(this));
    }
    
    private boolean isBillboardType(final BillboardSummary billboardSummary, final BillboardView$BillboardType billboardView$BillboardType) {
        return billboardSummary != null && billboardSummary.getBillboardType() != null && billboardSummary.getBillboardType().equals(billboardView$BillboardType.toString());
    }
    
    private boolean isNSREEpisodic(final BillboardSummary billboardSummary) {
        return this.isBillboardType(billboardSummary, BillboardView$BillboardType.NSRE_EPISODIC);
    }
    
    private boolean isNSREShow(final BillboardSummary billboardSummary) {
        return this.isBillboardType(billboardSummary, BillboardView$BillboardType.NSRE_SHOW);
    }
    
    private void setCustomBackground(final BillboardBackground billboardBackground) {
        if (billboardBackground != null) {
            this.storyArtUrl = billboardBackground.getUrl();
            if (!billboardBackground.getTone().equals("light")) {
                this.synopsis.setTextColor(this.getResources().getColor(2131558415));
                this.synopsis.setShadowLayer(2.0f, 0.0f, -1.0f, 2131558586);
                this.info.setTextColor(this.getResources().getColor(2131558608));
                this.info.setShadowLayer(2.0f, 0.0f, -1.0f, 2131558586);
                return;
            }
            this.synopsis.setTextColor(this.getResources().getColor(2131558416));
            this.info.setTextColor(this.getResources().getColor(2131558417));
            this.info.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            this.synopsis.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        }
    }
    
    private void setUpMotionBillboard(final String s, final boolean b) {
        if (!this.isTablet || TextUtils.isEmpty((CharSequence)s) || (this.mediaPlayerWrapper != null && this.mediaPlayerWrapper.isDonePlaying())) {
            this.hideMotionBB();
            return;
        }
        if (Log.isLoggable()) {
            Log.v("BillboardView", "Showing motion billboard with action buttons, video url: " + s);
        }
        this.storyArt.setVisibility(0);
        if (this.mediaPlayerWrapper == null) {
            this.mediaPlayerWrapper = new MediaPlayerWrapper(this.motionStoryArt, b, 3, 0.0f, new BillboardView$4(this));
            this.downloadVideo(Uri.parse(s).buildUpon().clearQuery().build().toString());
            return;
        }
        this.showMotionBB();
    }
    
    private void setupCTAButton(final BillboardCTA billboardCTA, final Button button) {
        final String type = billboardCTA.getType();
        switch (type) {
            default: {
                button.setVisibility(8);
            }
            case "mdp": {
                button.setVisibility(8);
                this.showMDPButton();
            }
            case "addToPlaylist": {
                button.setVisibility(8);
                this.showMyListButton(this.video.getId(), this.video.getType());
            }
            case "details": {
                button.setVisibility(8);
                this.showListEpisodesButton(billboardCTA);
            }
            case "play": {
                button.setVisibility(0);
                button.setText((CharSequence)LoMoUtils.getTextForCTA(this.getContext(), billboardCTA.getName(), billboardCTA.getSequenceNumber()));
                String bookmarkPosition;
                if (billboardCTA.ignoreBookmark()) {
                    bookmarkPosition = "0";
                }
                else {
                    bookmarkPosition = billboardCTA.getBookmarkPosition();
                }
                final ServiceManager serviceManager = ((NetflixActivity)this.getContext()).getServiceManager();
                if (TextUtils.equals((CharSequence)this.video.getId(), (CharSequence)billboardCTA.getVideoId())) {
                    this.AddPlayableToCTA(this.video, button, bookmarkPosition);
                    return;
                }
                if (billboardCTA.getName().toLowerCase().contains("trailer") || billboardCTA.getName().toLowerCase().contains("recap")) {
                    serviceManager.getBrowse().fetchMovieDetails(billboardCTA.getVideoId(), null, new BillboardView$FetchSupplementalsCallback(this, button, bookmarkPosition));
                    return;
                }
                if (billboardCTA.getName().toLowerCase().contains("season")) {
                    final boolean contains = billboardCTA.getName().toLowerCase().contains("continue");
                    if (contains) {
                        serviceManager.getBrowse().fetchShowDetails(this.video.getId(), null, false, new BillboardView$FetchSupplementalsCallback(this, button, bookmarkPosition, contains));
                        return;
                    }
                    serviceManager.getBrowse().fetchEpisodes(billboardCTA.getVideoId(), VideoType.SEASON, 0, 1, new BillboardView$FetchSupplementalsCallback(this, button, bookmarkPosition));
                    return;
                }
                else {
                    if (billboardCTA.getName().toLowerCase().contains("episode") || billboardCTA.getName().toLowerCase().contains("show")) {
                        serviceManager.getBrowse().fetchEpisodeDetails(billboardCTA.getVideoId(), null, new BillboardView$FetchSupplementalsCallback(this, button, bookmarkPosition));
                        return;
                    }
                    if (this.video.getType() == VideoType.MOVIE) {
                        serviceManager.getBrowse().fetchMovieDetails(billboardCTA.getVideoId(), null, new BillboardView$FetchSupplementalsCallback(this, button, bookmarkPosition));
                        return;
                    }
                    serviceManager.getBrowse().fetchShowDetails(billboardCTA.getVideoId(), null, false, new BillboardView$FetchSupplementalsCallback(this, button, bookmarkPosition));
                    return;
                }
                break;
            }
        }
    }
    
    public static boolean shouldShowArtworkOnly(final NetflixActivity netflixActivity) {
        return netflixActivity.getServiceManager() != null && !netflixActivity.getServiceManager().isTablet() && DeviceUtils.isPortrait((Context)netflixActivity);
    }
    
    private void showListEpisodesButton(final BillboardCTA billboardCTA) {
        this.showMDPButton();
        this.infoButton.setText((CharSequence)LoMoUtils.getTextForCTA(this.getContext(), billboardCTA.getName(), billboardCTA.getSequenceNumber()));
    }
    
    private void showMDPButton() {
        this.infoButton.setVisibility(0);
        this.infoButton.setOnClickListener((View$OnClickListener)new BillboardView$2(this, ((NetflixActivity)this.getContext()).getServiceManager()));
    }
    
    private void showMotionBB() {
        Log.v("BillboardView", "Showing motion BB");
        this.storyArt.setVisibility(8);
        this.motionStoryArt.setVisibility(0);
        this.muteButton.setVisibility(0);
        this.updateMuteButton();
    }
    
    private void showMyListButton(final String s, final VideoType videoType) {
        this.myListButton.setVisibility(0);
        final ServiceManager serviceManager = ((NetflixActivity)this.getContext()).getServiceManager();
        if (this.myListButton != null && serviceManager != null) {
            serviceManager.registerAddToMyListListener(s, this.addToListWrapper = serviceManager.createAddToMyListWrapper((NetflixActivity)this.getContext(), (TextView)this.myListButton, s, videoType, this.getTrackId(), false));
        }
        this.updateMyListState();
    }
    
    private void showOriginalBillboard(final Billboard billboard, final VideoType videoType) {
        final BillboardSummary billboardSummary = billboard.getBillboardSummary();
        if (billboardSummary != null) {
            (this.impressionParams = new HashMap<String, String>()).put("awardCampaign", billboardSummary.getAwardTrackId());
            this.impressionParams.put("billboardTheme", billboardSummary.getBillboardTheme());
            this.impressionParams.put("billboardType", billboardSummary.getBillboardType());
            if (billboardSummary.isOriginal() && !this.isNSREEpisodic(billboardSummary)) {
                this.storyArtFrame.setPadding(0, 0, 0, 0);
                this.shadowOverlay.setVisibility(8);
                this.shadowOverlayGradient.setVisibility(8);
                this.setCustomBackground(billboardSummary.getBackground());
                final BillboardLogo logo = billboardSummary.getLogo();
                if (logo != null) {
                    this.tvCardUrl = logo.getUrl();
                }
                this.synopsisText = billboardSummary.getSynopsis();
                if (this.isTablet && !DeviceUtils.isPortrait(this.getContext())) {
                    ((RelativeLayout$LayoutParams)this.infoViewGroup.getLayoutParams()).setMarginStart(Math.round(this.getContext().getResources().getDimension(2131296269) * 2.0f));
                }
            }
            this.updateLogoSize(billboardSummary);
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
                    this.infoText = StringUtils.getBasicShowInfoString(this.getContext(), Integer.parseInt(billboardSummary.getYear()), billboardSummary.getMaturityRating(), billboard.getNumSeasonsLabel()).toString();
                }
            }
            this.updateDateBadge(billboardSummary);
            if (this.motionBillboardsEnabled) {
                this.setUpMotionBillboard(billboardSummary.getMotionUrl(), billboardSummary.getMotionShouldLoop());
            }
        }
    }
    
    private void updateDateBadge(final BillboardSummary billboardSummary) {
        BillboardDateBadge dateBadge;
        if (billboardSummary != null) {
            dateBadge = billboardSummary.getDateBadge();
        }
        else {
            dateBadge = null;
        }
        String url;
        if (dateBadge != null) {
            url = dateBadge.getUrl();
        }
        else {
            url = "";
        }
        if (billboardSummary != null && this.isNSREShow(billboardSummary) && !url.isEmpty()) {
            NetflixActivity.getImageLoader(this.getContext()).showImg(this.dateBadge, url, IClientLogging$AssetType.merchStill, this.infoText, ImageLoader$StaticImgConfig.DARK, true, 1);
            this.dateBadge.setVisibility(0);
            this.infoWrapper.setVisibility(8);
            return;
        }
        this.info.setText((CharSequence)this.infoText);
        this.infoWrapper.setVisibility(0);
    }
    
    private void updateLogoSize(final BillboardSummary billboardSummary) {
        final int n = 2131296451;
        int n2 = 2131296452;
        int n3 = n;
        if (billboardSummary != null) {
            n2 = n2;
            n3 = n;
            if (billboardSummary.isOriginal()) {
                n3 = 2131296449;
                n2 = 2131296450;
            }
        }
        final ViewGroup$LayoutParams layoutParams = this.tvCard.getLayoutParams();
        layoutParams.height = this.getResources().getDimensionPixelSize(n3);
        layoutParams.width = this.getResources().getDimensionPixelSize(n2);
        this.tvCard.setLayoutParams(layoutParams);
    }
    
    private void updateMuteButton() {
        if (this.mediaPlayerWrapper != null) {
            final TextView muteButton = this.muteButton;
            final Resources resources = this.getResources();
            int n;
            if (this.mediaPlayerWrapper.isMuted()) {
                n = 2131165781;
            }
            else {
                n = 2131165783;
            }
            muteButton.setText((CharSequence)resources.getString(n));
            this.muteButton.setOnClickListener((View$OnClickListener)new BillboardView$6(this));
        }
    }
    
    private void updateMyListState() {
        final ServiceManager serviceManager = ((NetflixActivity)this.getContext()).getServiceManager();
        if (serviceManager != null) {
            if (this.video.getType() != VideoType.MOVIE) {
                serviceManager.getBrowse().fetchShowDetails(this.video.getId(), null, false, new BillboardView$UpdateMyListCallback(this));
                return;
            }
            serviceManager.getBrowse().fetchMovieDetails(this.video.getId(), null, new BillboardView$UpdateMyListCallback(this));
        }
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
        this.storyArtFrame.setVisibility(0);
        final TextView synopsis = this.synopsis;
        int visibility4;
        if (this.isTablet) {
            visibility4 = (b ? 1 : 0);
        }
        else {
            visibility4 = 8;
        }
        synopsis.setVisibility(visibility4);
    }
    
    void AddPlayableToCTA(final Playable playable, final Button button, final String s) {
        final ServiceManager serviceManager = ((NetflixActivity)this.getContext()).getServiceManager();
        button.requestFocus();
        button.setOnClickListener((View$OnClickListener)new BillboardView$3(this, serviceManager, s, playable));
    }
    
    void AddPlayableToCTA(final VideoDetails videoDetails, final Button button, final String s) {
        this.AddPlayableToCTA(videoDetails.getPlayable(), button, s);
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
    
    public int getTrackId() {
        if (this.playContext != null) {
            Log.d("BillboardView", "TrackId found in PlayContextImpl");
            return this.playContext.getTrackId();
        }
        Log.d("BillboardView", "TrackId not found!");
        return -1;
    }
    
    public void hide() {
        NetflixActivity.getImageLoader(this.getContext()).clear(this.tvCard);
        NetflixActivity.getImageLoader(this.getContext()).clear(this.storyArt);
        this.setVisibility(4);
        this.detailsListener.remove((View)this.storyArt);
        if (this.mediaPlayerWrapper != null) {
            this.mediaPlayerWrapper.releaseResources();
        }
        final ServiceManager serviceManager = ((NetflixActivity)this.getContext()).getServiceManager();
        if (serviceManager != null && this.addToListWrapper != null) {
            serviceManager.unregisterAddToMyListListener(this.video.getId(), this.addToListWrapper);
        }
    }
    
    public void onWindowFocusChanged(final boolean b) {
        if (this.motionBillboardsEnabled) {
            final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
            if (netflixActivity != null && !netflixActivity.isFinishing()) {
                if (Log.isLoggable()) {
                    Log.d("BillboardView", "Billboard has focus: " + b);
                }
                netflixActivity.runOnUiThread((Runnable)new BillboardView$7(this, b));
            }
        }
    }
    
    public void update(final Billboard video, final Trackable trackable, final int n, final boolean b, final boolean b2) {
        final ServiceManager serviceManager = ((NetflixActivity)this.getContext()).getServiceManager();
        final BillboardSummary billboardSummary = video.getBillboardSummary();
        this.playContext = new PlayContextImp(trackable, n);
        this.video = video;
        if (Log.isLoggable()) {
            Log.v("BillboardView", "updating billboard, title: " + video.getTitle() + ", story url: " + video.getStoryUrl());
        }
        this.setVisibility(0);
        final String format = String.format(this.getResources().getString(2131165354), video.getTitle());
        this.setContentDescription((CharSequence)format);
        if (this.showArtworkOnly) {
            this.storyArt.setPressedStateHandlerEnabled(true);
            this.detailsListener.update((View)this.storyArt, video, this.storyArt.getPressedStateHandler());
            String s;
            if (this.isTablet) {
                s = video.getHighResolutionLandscapeBoxArtUrl();
            }
            else {
                s = video.getHorzDispUrl();
            }
            if (Log.isLoggable()) {
                Log.v("BillboardView", "Showing artwork only, image url: " + s);
            }
            NetflixActivity.getImageLoader(this.getContext()).showImg(this.storyArt, s, IClientLogging$AssetType.merchStill, format, ImageLoader$StaticImgConfig.DARK, true, 1);
        }
        else {
            this.storyArtUrl = video.getStoryUrl();
            this.tvCardUrl = video.getTvCardUrl();
            this.synopsisText = video.getSynopsis();
            this.infoText = this.getDefaultInfoText(video);
            this.storyArt.setTopCroppingEnabled(video.getBillboardSummary() != null && video.getBillboardSummary().isOriginal());
            this.showOriginalBillboard(video, video.getType());
            if (this.isNSREEpisodic(billboardSummary)) {
                String storyArtUrl;
                if (billboardSummary.getBackground() != null) {
                    storyArtUrl = billboardSummary.getBackground().getUrl();
                }
                else {
                    storyArtUrl = this.storyArtUrl;
                }
                this.storyArtUrl = storyArtUrl;
                String tvCardUrl;
                if (billboardSummary.getLogo() != null) {
                    tvCardUrl = billboardSummary.getLogo().getUrl();
                }
                else {
                    tvCardUrl = this.tvCardUrl;
                }
                this.tvCardUrl = tvCardUrl;
                String infoText;
                if (!TextUtils.isEmpty((CharSequence)billboardSummary.getTitle())) {
                    infoText = this.getResources().getString(2131165527, new Object[] { billboardSummary.getTitle() });
                }
                else {
                    infoText = this.infoText;
                }
                this.infoText = infoText;
                String synopsisText;
                if (!TextUtils.isEmpty((CharSequence)billboardSummary.getSynopsis())) {
                    synopsisText = billboardSummary.getSynopsis();
                }
                else {
                    synopsisText = this.infoText;
                }
                this.synopsisText = synopsisText;
                this.info.setSingleLine(false);
                this.info.setMaxLines(2);
                LoMoUtils.toggleEpisodeBadge(billboardSummary.getBadgeKeys(), this.episodeBadge);
            }
            else {
                this.info.setSingleLine(true);
                this.info.setMaxLines(1);
                this.episodeBadge.setVisibility(8);
            }
            if (Log.isLoggable()) {
                Log.v("BillboardView", "Showing static billboard with action buttons, image url: " + this.storyArtUrl);
            }
            NetflixActivity.getImageLoader(this.getContext()).showImg(this.storyArt, this.storyArtUrl, IClientLogging$AssetType.merchStill, format, ImageLoader$StaticImgConfig.DARK, true, 1);
            NetflixActivity.getImageLoader(this.getContext()).showImg(this.tvCard, this.tvCardUrl, IClientLogging$AssetType.boxArt, format, ImageLoader$StaticImgConfig.DARK_NO_PLACEHOLDER, true, 1);
            this.storyArt.setPressedStateHandlerEnabled(false);
            this.detailsListener.remove((View)this.storyArt);
            this.info.setText((CharSequence)this.infoText);
            this.synopsis.setText((CharSequence)this.synopsisText);
            ServiceManagerUtils.castPrefetchAndCacheManifestIfEnabled(serviceManager, video, this.playContext);
            this.generateCallsToAction(video);
        }
        if (serviceManager != null && serviceManager.isReady()) {
            Log.v("BillboardView", "Loggin billboard impression for video: " + video.getId());
            serviceManager.getBrowse().logBillboardActivity(video, BillboardInteractionType.IMPRESSION, this.impressionParams);
        }
    }
}
