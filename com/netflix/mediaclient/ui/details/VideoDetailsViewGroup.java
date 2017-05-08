// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.text.Html;
import java.util.List;
import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import java.util.Iterator;
import java.util.Map;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.android.widgetry.widget.UserRatingButton$OnRateListener;
import android.support.design.widget.CoordinatorLayout;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.FrameLayout;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import android.text.TextUtils$TruncateAt;
import android.view.ViewGroup$MarginLayoutParams;
import android.support.v4.content.ContextCompat;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.ui.rating.Ratings;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.interface_.FeatureEnabledProvider;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.android.widgetry.widget.UserRatingButton;
import android.content.BroadcastReceiver;
import android.view.View$OnClickListener;
import android.view.ViewStub;
import com.netflix.android.widgetry.widget.ThumbsToMatchPercentageAnimator;
import com.netflix.mediaclient.ui.offline.DownloadButton;
import com.netflix.mediaclient.android.widget.TopCropImageView;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import java.util.EnumMap;
import android.widget.LinearLayout;

public class VideoDetailsViewGroup extends LinearLayout
{
    private static final String TAG = "VideoDetailsViewGroup";
    public static final String UPDATE_CAPABILITIES_BADGES = "com.netflix.mediaclient.intent.action.UPDATE_CAPABILITIES_BADGES";
    private static final EnumMap<VideoDetailsViewGroup$SupportedCapabilities, Integer> mCapabilityBadgesMap;
    private View actionBarDummyView;
    private boolean actionButtonsCreated;
    private TextView addToMyList;
    private View addToMyListGroup;
    private TextView addToMyListLabel;
    private ImageView backgroundImg;
    private int badgesPadding;
    private TextView basicInfo;
    protected TextView basicInfoBadges;
    protected ViewGroup copyright;
    private TextView creators;
    protected VideoDetails details;
    protected TextView episodeBadge;
    protected TextView episodeTitle;
    private ViewGroup footerViewGroup;
    protected TopCropImageView horzDispImg;
    protected ViewGroup imgGroup;
    protected View infoContainer;
    private DownloadButton mMovieDownloadButton;
    private ThumbsToMatchPercentageAnimator mThumbsToMatchPercentageAnimator;
    private TextView matchPercentage;
    private ViewStub myListMdp;
    private ViewStub myListSdp;
    protected View$OnClickListener onCWClickListener;
    protected View play;
    protected NetflixRatingBar ratingBar;
    private final BroadcastReceiver ratingsUpdateBroadcastReceiver;
    protected TextView relatedTitle;
    private TextView starring;
    protected TextView supplemental;
    protected TextView synopsis;
    protected TextView title;
    private final BroadcastReceiver updateCapabilityBadges;
    protected UserRatingButton userRatingButton;
    private LinearLayout videoActionsContainer;
    private String videoId;
    
    static {
        mCapabilityBadgesMap = new VideoDetailsViewGroup$1(VideoDetailsViewGroup$SupportedCapabilities.class);
    }
    
    public VideoDetailsViewGroup(final Context context) {
        super(context);
        this.ratingsUpdateBroadcastReceiver = new VideoDetailsViewGroup$5(this);
        this.updateCapabilityBadges = new VideoDetailsViewGroup$6(this);
        this.init();
    }
    
    public VideoDetailsViewGroup(final Context context, final AttributeSet set) {
        super(context, set);
        this.ratingsUpdateBroadcastReceiver = new VideoDetailsViewGroup$5(this);
        this.updateCapabilityBadges = new VideoDetailsViewGroup$6(this);
        this.init();
    }
    
    public VideoDetailsViewGroup(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.ratingsUpdateBroadcastReceiver = new VideoDetailsViewGroup$5(this);
        this.updateCapabilityBadges = new VideoDetailsViewGroup$6(this);
        this.init();
    }
    
    private void addIconFontBadges(final String text) {
        if (this.basicInfoBadges != null) {
            final TextView basicInfoBadges = this.basicInfoBadges;
            int badgesPadding;
            if (StringUtils.isNotEmpty(text)) {
                badgesPadding = this.badgesPadding;
            }
            else {
                badgesPadding = 0;
            }
            basicInfoBadges.setPadding(badgesPadding, 0, 0, 0);
            this.basicInfoBadges.setText((CharSequence)text);
            KidsUtils.manageSectionTextColor(this.basicInfoBadges, VideoDetailsViewGroup$Section.INFO);
        }
    }
    
    private void createActionButtons(final boolean b) {
        if (!this.actionButtonsCreated) {
            Object inflate;
            if (this.myListMdp == null || this.myListSdp == null) {
                inflate = this;
            }
            else {
                ViewStub viewStub;
                if (b) {
                    viewStub = this.myListMdp;
                }
                else {
                    viewStub = this.myListSdp;
                }
                inflate = viewStub.inflate();
            }
            this.videoActionsContainer = (LinearLayout)((View)inflate).findViewById(2131821528);
            this.addToMyListGroup = ((View)inflate).findViewById(2131821160);
            this.addToMyList = (TextView)((View)inflate).findViewById(2131820925);
            this.addToMyListLabel = (TextView)((View)inflate).findViewById(2131821161);
            this.mMovieDownloadButton = (DownloadButton)((View)inflate).findViewById(2131821076);
            this.userRatingButton = (UserRatingButton)((View)inflate).findViewById(2131821529);
            if (this.userRatingButton != null) {
                this.userRatingButton.setDark(KidsUtils.getSectionTextColor(this.getContext(), VideoDetailsViewGroup$Section.ACTIONS) == -1);
            }
            LocalizationUtils.setRtlLayoutDirectionIfApplicable((View)this.userRatingButton);
            this.setupVideoActionsBar();
            this.actionButtonsCreated = true;
            KidsUtils.manageSectionTextColor(this.addToMyList, VideoDetailsViewGroup$Section.ACTIONS);
            KidsUtils.manageSectionTextColor(this.addToMyListLabel, VideoDetailsViewGroup$Section.ACTION_LABEL);
        }
    }
    
    private EnumMap<VideoDetailsViewGroup$SupportedCapabilities, Boolean> createCapabilitiesMap(final VideoDetails videoDetails, final NetflixActivity netflixActivity) {
        while (true) {
            Label_0185: {
                if (!MdxUtils.isCurrentMdxTargetAvailable(netflixActivity.getServiceManager())) {
                    break Label_0185;
                }
                final Object currentTargetCapabilities = netflixActivity.getServiceManager().getMdx().getCurrentTargetCapabilities();
                if (currentTargetCapabilities == null) {
                    break Label_0185;
                }
                DeviceCapabilityProvider localCapabilities;
                if ((localCapabilities = (DeviceCapabilityProvider)currentTargetCapabilities) == null) {
                    localCapabilities = DeviceUtils.getLocalCapabilities(netflixActivity.getServiceManager());
                }
                final EnumMap<VideoDetailsViewGroup$SupportedCapabilities, Boolean> enumMap = new EnumMap<VideoDetailsViewGroup$SupportedCapabilities, Boolean>(VideoDetailsViewGroup$SupportedCapabilities.class);
                enumMap.put(VideoDetailsViewGroup$SupportedCapabilities._5dot1, Boolean.valueOf(DeviceUtils.shouldShow5dot1Icon(localCapabilities, (FeatureEnabledProvider)videoDetails)));
                if (DeviceUtils.shouldShowDolbyVisionIcon(localCapabilities, (FeatureEnabledProvider)videoDetails)) {
                    enumMap.put(VideoDetailsViewGroup$SupportedCapabilities.DOLBY_VISION, Boolean.valueOf(true));
                }
                else {
                    if (DeviceUtils.shouldShowHdr10Icon(localCapabilities, (FeatureEnabledProvider)videoDetails)) {
                        enumMap.put(VideoDetailsViewGroup$SupportedCapabilities.HDR10, Boolean.valueOf(true));
                        return enumMap;
                    }
                    if (DeviceUtils.shouldShowUhdIcon(localCapabilities, (FeatureEnabledProvider)videoDetails)) {
                        enumMap.put(VideoDetailsViewGroup$SupportedCapabilities.UHD, Boolean.valueOf(true));
                        return enumMap;
                    }
                    if (DeviceUtils.shouldShowHdIcon(localCapabilities, (FeatureEnabledProvider)videoDetails)) {
                        enumMap.put(VideoDetailsViewGroup$SupportedCapabilities.HD, Boolean.valueOf(true));
                        return enumMap;
                    }
                    if (DeviceUtils.shouldShow3DIcon(localCapabilities, (FeatureEnabledProvider)videoDetails)) {
                        enumMap.put(VideoDetailsViewGroup$SupportedCapabilities._3D, Boolean.valueOf(true));
                        return enumMap;
                    }
                }
                return enumMap;
            }
            final Object currentTargetCapabilities = null;
            continue;
        }
    }
    
    private int getBadgesPadding() {
        return this.getResources().getDimensionPixelSize(2131427707);
    }
    
    private String getIfValidOrFallback(final String s, final String s2) {
        if (s != null && !s.isEmpty()) {
            return s;
        }
        return s2;
    }
    
    private String getInterBadgePadding() {
        return "  ";
    }
    
    private boolean hasWatched(final VideoDetails videoDetails) {
        return videoDetails != null && videoDetails.hasWatched();
    }
    
    private void init() {
        LayoutInflater.from(this.getContext()).inflate(this.getlayoutId(), (ViewGroup)this, true);
        this.setOrientation(1);
        this.addView(this.actionBarDummyView = ViewUtils.createActionBarDummyView((NetflixActivity)this.getContext()), 0);
        this.findViews();
        if (this.matchPercentage != null && Ratings.isAndroidThumbActive()) {
            if (KidsUtils.isKidsParity(this.getContext())) {
                this.matchPercentage.setTextColor(KidsUtils.getSectionTextColor(this.getContext(), VideoDetailsViewGroup$Section.MATCH));
            }
            else {
                final TextView matchPercentage = this.matchPercentage;
                final Context context = this.getContext();
                int n;
                if (BrowseExperience.showKidsExperience()) {
                    n = 2131755033;
                }
                else {
                    n = 2131755034;
                }
                matchPercentage.setTextColor(ContextCompat.getColor(context, n));
            }
            this.mThumbsToMatchPercentageAnimator = new ThumbsToMatchPercentageAnimator(this.matchPercentage, 2131296975, 2131296976, KidsUtils.getSectionTextColor(this.getContext(), VideoDetailsViewGroup$Section.ACTIONS));
        }
        this.badgesPadding = this.getBadgesPadding();
        this.setImgLayoutListener();
        this.onCWClickListener = (View$OnClickListener)new VideoDetailsViewGroup$2(this);
        if (this.infoContainer != null && KidsUtils.shouldPadInfo(this.getContext())) {
            final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams = (ViewGroup$MarginLayoutParams)this.infoContainer.getLayoutParams();
            viewGroup$MarginLayoutParams.topMargin += this.getResources().getDimensionPixelSize(2131427636);
            this.infoContainer.invalidate();
            if (this.title != null) {
                this.title.setSingleLine();
                this.title.setEllipsize(TextUtils$TruncateAt.END);
            }
        }
    }
    
    private boolean isNSREShow(final VideoDetails videoDetails) {
        return videoDetails instanceof ShowDetails && VideoType.SHOW.equals(videoDetails.getType()) && videoDetails.isNSRE();
    }
    
    private void setImgLayoutListener() {
        if (Log.isLoggable()) {
            Log.v("VideoDetailsViewGroup", "setImgLayoutListener()");
        }
        this.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new VideoDetailsViewGroup$3(this));
    }
    
    private void setupVideoActionsBar() {
        if (!Ratings.isAndroidThumbActive()) {
            ViewUtils.setVisibleOrGone((View)this.userRatingButton, false);
            ViewUtils.setVisibleOrGone((View)this.matchPercentage, false);
        }
        else {
            ViewUtils.setVisibleOrGone((View)this.ratingBar, false);
        }
        this.updateVideoActionsLayout();
    }
    
    private boolean showCurrentEpisodeDetails(final VideoDetails videoDetails) {
        return this.isNSREShow(videoDetails) && this.hasWatched(videoDetails);
    }
    
    private static void updateButtonLayout(final View view, final int n) {
        if (view != null && view.getParent() != null) {
            final FrameLayout frameLayout = (FrameLayout)view.getParent();
            frameLayout.setVisibility(view.getVisibility());
            frameLayout.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(n, -1));
        }
    }
    
    private void updateMatchPercentage(final int n, final boolean b) {
        if (this.matchPercentage != null && this.mThumbsToMatchPercentageAnimator != null) {
            this.mThumbsToMatchPercentageAnimator.update(n, this.details.getMatchPercentage(), this.details.isNewForPvr(), b);
        }
    }
    
    private void updatePlayButton(final VideoDetails videoDetails) {
        if (this.play != null) {
            final View play = this.play;
            int visibility;
            if (videoDetails.isPreRelease()) {
                visibility = 4;
            }
            else {
                visibility = 0;
            }
            play.setVisibility(visibility);
        }
    }
    
    private void updateRating(final VideoDetails videoDetails) {
        final NetflixRatingBar$RatingBarDataProvider ratingBarDataProviderSafely = ViewUtils.getRatingBarDataProviderSafely((View)this);
        if (this.ratingBar != null) {
            this.ratingBar.update(ratingBarDataProviderSafely, videoDetails);
        }
        if (this.userRatingButton != null) {
            final NetflixActivity netflixActivity = AndroidUtils.getContextAs(this.getContext(), NetflixActivity.class);
            if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)netflixActivity)) {
                final CoordinatorLayout coordinatorLayout = (CoordinatorLayout)netflixActivity.findViewById(2131820945);
                this.userRatingButton.setRating(videoDetails.getUserThumbRating());
                this.userRatingButton.setOnRateListener(coordinatorLayout, new VideoDetailsViewGroup$4(this, netflixActivity, ratingBarDataProviderSafely, videoDetails), true, 1);
                this.updateMatchPercentage(videoDetails.getUserThumbRating(), false);
            }
        }
    }
    
    private void updateVideoActionsLayout() {
        if (this.videoActionsContainer != null) {
            if (this.videoActionsContainer.getChildCount() != 3) {
                throw new IllegalStateException("Only 3 buttons expected in the video actions");
            }
            final int max = Math.max(Math.min(this.getMeasuredWidth() / 4, this.getResources().getDimensionPixelSize(2131427937)), this.getResources().getDimensionPixelSize(2131427938));
            for (int i = 0; i < this.videoActionsContainer.getChildCount(); ++i) {
                this.videoActionsContainer.getChildAt(i).setVisibility(4);
            }
            updateButtonLayout((View)this.mMovieDownloadButton, max);
            updateButtonLayout((View)this.userRatingButton, max);
            updateButtonLayout(this.addToMyListGroup, max);
        }
    }
    
    protected void alignViews() {
        if (Coppola1Utils.isCoppolaContext(this.getContext())) {
            final View viewById = this.findViewById(2131820922);
            if (viewById != null) {
                ((LinearLayout$LayoutParams)viewById.getLayoutParams()).topMargin = 0;
            }
            this.imgGroup.setVisibility(8);
            this.horzDispImg.setVisibility(8);
            this.requestLayout();
            Log.v("VideoDetailsViewGroup", "img group width zero height!");
            return;
        }
        this.horzDispImg.getLayoutParams().height = this.calculateImageHeight();
    }
    
    protected int calculateImageHeight() {
        int n;
        if ((n = this.imgGroup.getMeasuredWidth()) <= 0) {
            n = DeviceUtils.getScreenWidthInPixels(this.getContext());
        }
        final int n2 = (int)(n * 0.5625f);
        if (Log.isLoggable()) {
            Log.v("VideoDetailsViewGroup", "imgGroup.getMeasuredWidth(): " + this.imgGroup.getMeasuredWidth() + ", width: " + n + ", height: " + n2);
        }
        return n2;
    }
    
    protected void findViews() {
        this.myListMdp = (ViewStub)this.findViewById(2131821543);
        this.myListSdp = (ViewStub)this.findViewById(2131821540);
        this.matchPercentage = (TextView)this.findViewById(2131820710);
        this.ratingBar = (NetflixRatingBar)this.findViewById(2131820712);
        this.basicInfo = (TextView)this.findViewById(2131820923);
        this.episodeBadge = (TextView)this.findViewById(2131820736);
        this.episodeTitle = (TextView)this.findViewById(2131820737);
        this.supplemental = (TextView)this.findViewById(2131820750);
        this.synopsis = (TextView)this.findViewById(2131820752);
        this.starring = (TextView)this.findViewById(2131821097);
        this.creators = (TextView)this.findViewById(2131821098);
        this.horzDispImg = (TopCropImageView)this.findViewById(2131820743);
        this.title = (TextView)this.findViewById(2131820757);
        this.imgGroup = (ViewGroup)this.findViewById(2131820926);
        this.backgroundImg = (ImageView)this.findViewById(2131821034);
        this.relatedTitle = (TextView)this.findViewById(2131820760);
        this.basicInfoBadges = (TextView)this.findViewById(2131820924);
        this.footerViewGroup = (ViewGroup)this.findViewById(2131820759);
        this.copyright = (ViewGroup)this.findViewById(2131821530);
        this.play = this.findViewById(2131820758);
        this.infoContainer = this.findViewById(2131820922);
    }
    
    public TextView getAddToMyListButton() {
        return this.addToMyList;
    }
    
    public TextView getAddToMyListButtonLabel() {
        return this.addToMyListLabel;
    }
    
    public ImageView getBackgroundImage() {
        return this.backgroundImg;
    }
    
    public DownloadButton getDownloadButton() {
        return this.mMovieDownloadButton;
    }
    
    public ViewGroup getFooterViewGroup() {
        return this.footerViewGroup;
    }
    
    public AdvancedImageView getHeroImage() {
        return this.horzDispImg;
    }
    
    protected int getlayoutId() {
        return 2130903336;
    }
    
    public void hideRelatedTitle() {
        if (this.relatedTitle != null) {
            this.relatedTitle.setVisibility(8);
        }
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(this.ratingsUpdateBroadcastReceiver, new IntentFilter("com.netflix.falkor.ACTION_NOTIFY_OF_RATINGS_CHANGE"));
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(this.updateCapabilityBadges, new IntentFilter("com.netflix.mediaclient.intent.action.UPDATE_CAPABILITIES_BADGES"));
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(this.ratingsUpdateBroadcastReceiver);
        LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(this.updateCapabilityBadges);
    }
    
    public void refreshImagesIfNecessary() {
        if (this.horzDispImg != null) {
            this.horzDispImg.refreshImageIfNecessary();
        }
    }
    
    public void removeActionBarDummyView() {
        if (this.actionBarDummyView != null) {
            this.removeView(this.actionBarDummyView);
            this.actionBarDummyView = null;
        }
    }
    
    public void setCopyright(final VideoDetails videoDetails) {
        if (this.copyright != null) {
            if (!StringUtils.isNotEmpty(videoDetails.getCopyright())) {
                this.copyright.setVisibility(8);
                return;
            }
            this.copyright.setVisibility(0);
            new CopyrightView(videoDetails, this.getContext(), this.copyright);
        }
    }
    
    public void setMyListVisibility(final int visibility) {
        if (this.addToMyListGroup != null) {
            this.addToMyListGroup.setVisibility(visibility);
            this.updateVideoActionsLayout();
        }
    }
    
    public void setVisibility(final int visibility) {
        if (Log.isLoggable()) {
            Log.v("VideoDetailsViewGroup", "setVisibility: " + ViewUtils.getVisibilityAsString(visibility));
        }
        super.setVisibility(visibility);
    }
    
    public void setupDownloadButton(final VideoDetails videoDetails) {
        final NetflixActivity netflixActivity = AndroidUtils.getContextAs(this.getContext(), NetflixActivity.class);
        if (netflixActivity == null || videoDetails == null || netflixActivity.getServiceManager() == null || !netflixActivity.getServiceManager().isReady() || AndroidUtils.isActivityFinishedOrDestroyed((Context)netflixActivity)) {
            ViewUtils.setVisibleOrInvisible((View)this.mMovieDownloadButton, false);
        }
        else if (this.mMovieDownloadButton != null) {
            if (netflixActivity.getServiceManager().isOfflineFeatureAvailable()) {
                if (videoDetails.getPlayable().isAvailableOffline() && videoDetails.getType() == VideoType.MOVIE) {
                    this.mMovieDownloadButton.setStateFromPlayable(videoDetails.getPlayable(), netflixActivity);
                    ViewUtils.setVisibleOrInvisible((View)this.mMovieDownloadButton, true);
                }
                else {
                    ViewUtils.setVisibleOrInvisible((View)this.mMovieDownloadButton, false);
                }
            }
            else {
                ViewUtils.setVisibleOrInvisible((View)this.mMovieDownloadButton, false);
            }
            this.updateVideoActionsLayout();
        }
    }
    
    protected void setupImageClicks(final VideoDetails videoDetails, final NetflixActivity netflixActivity) {
        if (videoDetails.isPreRelease()) {
            return;
        }
        this.horzDispImg.requestFocus();
        this.horzDispImg.setOnClickListener((View$OnClickListener)new PressedStateHandler$DelayedOnClickListener(this.horzDispImg.getPressedStateHandler(), this.onCWClickListener));
    }
    
    public void showRelatedTitle() {
        if (this.relatedTitle != null) {
            this.relatedTitle.setVisibility(0);
        }
    }
    
    protected void updateBadges(final VideoDetails videoDetails, final NetflixActivity netflixActivity) {
        final String s = "";
        final Iterator<Map.Entry<VideoDetailsViewGroup$SupportedCapabilities, Boolean>> iterator = this.createCapabilitiesMap(videoDetails, netflixActivity).entrySet().iterator();
        String string = s;
        while (iterator.hasNext()) {
            final Map.Entry<VideoDetailsViewGroup$SupportedCapabilities, Boolean> entry = iterator.next();
            if (entry.getValue()) {
                string = string + this.getResources().getString((int)VideoDetailsViewGroup.mCapabilityBadgesMap.get(entry.getKey())) + this.getInterBadgePadding();
            }
        }
        this.addIconFontBadges(string);
    }
    
    protected void updateBasicInfo(final VideoDetails videoDetails, final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        if (this.basicInfo != null && videoDetails != null && videoDetails.isAvailableToStream()) {
            this.basicInfo.setText(videoDetailsViewGroup$DetailsStringProvider.getBasicInfoString());
            KidsUtils.manageSectionTextColor(this.basicInfo, VideoDetailsViewGroup$Section.INFO);
        }
    }
    
    protected void updateCredits(final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        if (this.starring != null) {
            final CharSequence starringText = videoDetailsViewGroup$DetailsStringProvider.getStarringText();
            if (StringUtils.isEmpty(starringText)) {
                this.starring.setVisibility(8);
            }
            else {
                this.starring.setText(starringText);
                this.starring.setVisibility(0);
            }
            KidsUtils.manageSectionTextColor(this.starring, VideoDetailsViewGroup$Section.CREDITS);
        }
        if (this.creators != null) {
            final CharSequence creatorsText = videoDetailsViewGroup$DetailsStringProvider.getCreatorsText();
            if (StringUtils.isEmpty(creatorsText)) {
                this.creators.setVisibility(8);
            }
            else {
                this.creators.setText(creatorsText);
                this.creators.setVisibility(0);
            }
            KidsUtils.manageSectionTextColor(this.creators, VideoDetailsViewGroup$Section.CREDITS);
        }
    }
    
    public void updateDetails(final VideoDetails details, final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        boolean b = true;
        this.videoId = details.getId();
        this.details = details;
        this.createActionButtons(details.getType() == VideoType.MOVIE);
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        this.updateImage(details, netflixActivity, String.format(this.getResources().getString(2131296447), details.getTitle()));
        this.updateRelatedTitle(details);
        this.updateTitle(details);
        this.updateBasicInfo(details, videoDetailsViewGroup$DetailsStringProvider);
        this.updateBadges(details, netflixActivity);
        this.updateRating(details);
        this.updateSynopsis(details);
        this.updateCredits(videoDetailsViewGroup$DetailsStringProvider);
        this.updatePlayButton(details);
        if (this.episodeTitle == null || this.episodeBadge == null || this.supplemental == null || this.starring == null || this.creators == null) {
            b = false;
        }
        if (this.showCurrentEpisodeDetails(details) && b) {
            this.updateNSREFields((ShowDetails)details);
        }
        else {
            if (this.episodeBadge != null) {
                this.episodeBadge.setVisibility(8);
            }
            if (this.episodeTitle != null) {
                this.episodeTitle.setVisibility(8);
            }
            if (this.supplemental != null) {
                this.supplemental.setVisibility(8);
            }
        }
    }
    
    public void updateDownloadButtonIfExists(final VideoDetails videoDetails) {
        final NetflixActivity netflixActivity = AndroidUtils.getContextAs(this.getContext(), NetflixActivity.class);
        if (netflixActivity != null && videoDetails != null && netflixActivity.getServiceManager() != null && netflixActivity.getServiceManager().isReady() && !AndroidUtils.isActivityFinishedOrDestroyed((Context)netflixActivity) && this.mMovieDownloadButton != null && netflixActivity.getServiceManager().isOfflineFeatureAvailable()) {
            this.mMovieDownloadButton.setVisibility(0);
            this.mMovieDownloadButton.setEnabled(true);
            this.mMovieDownloadButton.setStateFromPlayable(videoDetails.getPlayable(), netflixActivity);
            this.updateVideoActionsLayout();
        }
    }
    
    protected void updateImage(final VideoDetails videoDetails, final NetflixActivity netflixActivity, final String s) {
        String s2;
        if (DeviceUtils.isTabletByContext((Context)netflixActivity)) {
            s2 = videoDetails.getHorzDispUrl();
        }
        else {
            s2 = videoDetails.getStoryUrl();
        }
        if (this.showCurrentEpisodeDetails(videoDetails)) {
            s2 = this.getIfValidOrFallback(((ShowDetails)videoDetails).getCurrentEpisodeHorzDispUrl(), s2);
        }
        NetflixActivity.getImageLoader((Context)netflixActivity).showImg((AdvancedImageView)this.horzDispImg, s2, IClientLogging$AssetType.boxArt, s, BrowseExperience.getImageLoaderConfig(), true, 1);
        this.setupImageClicks(videoDetails, netflixActivity);
    }
    
    public void updateNSREFields(final ShowDetails showDetails) {
        final String currentEpisodeTitle = showDetails.getCurrentEpisodeTitle();
        LoMoUtils.toggleEpisodeBadge((List)showDetails.getCurrentEpisodeBadges(), this.episodeBadge);
        if (currentEpisodeTitle != null) {
            this.episodeTitle.setText((CharSequence)this.getResources().getString(2131296646, new Object[] { currentEpisodeTitle }));
            this.supplemental.setText((CharSequence)showDetails.getSupplementalMessage());
            this.supplemental.setVisibility(0);
            this.starring.setVisibility(8);
            this.creators.setVisibility(8);
        }
        else {
            this.episodeTitle.setText((CharSequence)showDetails.getSupplementalMessage());
            this.supplemental.setVisibility(8);
            this.starring.setVisibility(0);
            this.creators.setVisibility(0);
        }
        KidsUtils.setTextColorIfApplicable(this.episodeTitle);
        KidsUtils.setTextColorIfApplicable(this.supplemental);
    }
    
    protected void updateRelatedTitle(final VideoDetails videoDetails) {
        if (this.relatedTitle != null) {
            this.relatedTitle.setText((CharSequence)this.relatedTitle.getResources().getString(2131296797, new Object[] { videoDetails.getTitle() }));
            KidsUtils.manageSectionTextColor(this.relatedTitle, VideoDetailsViewGroup$Section.TITLE);
        }
    }
    
    public void updateSynopsis(final VideoDetails videoDetails) {
        final String synopsis = videoDetails.getSynopsis();
        if (this.synopsis != null) {
            String ifValidOrFallback = synopsis;
            if (this.showCurrentEpisodeDetails(videoDetails)) {
                ifValidOrFallback = this.getIfValidOrFallback(((ShowDetails)videoDetails).getCurrentEpisodeSynopsis(), synopsis);
            }
            final TextView synopsis2 = this.synopsis;
            Object fromHtml;
            if (StringUtils.isEmpty(ifValidOrFallback)) {
                fromHtml = "";
            }
            else {
                fromHtml = Html.fromHtml(ifValidOrFallback);
            }
            synopsis2.setText((CharSequence)fromHtml);
            KidsUtils.manageSectionTextColor(this.synopsis, VideoDetailsViewGroup$Section.SYNOPSIS);
        }
    }
    
    protected void updateTitle(final VideoDetails videoDetails) {
        if (this.title != null) {
            this.title.setText((CharSequence)videoDetails.getTitle());
            KidsUtils.manageSectionTextColor(this.title, VideoDetailsViewGroup$Section.TITLE);
        }
    }
}
