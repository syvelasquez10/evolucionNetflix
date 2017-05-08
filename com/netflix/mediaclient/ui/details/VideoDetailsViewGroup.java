// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.text.Html;
import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import java.util.Iterator;
import java.util.Map;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import android.view.LayoutInflater;
import com.netflix.mediaclient.ui.mdx.MdxTargetCapabilities;
import com.netflix.mediaclient.servicemgr.interface_.FeatureEnabledProvider;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.StringUtils;
import android.util.AttributeSet;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.view.View$OnClickListener;
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
    private DownloadButton mMovieDownloadButton;
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
    private String videoId;
    
    static {
        mCapabilityBadgesMap = new VideoDetailsViewGroup$1(VideoDetailsViewGroup$SupportedCapabilities.class);
    }
    
    public VideoDetailsViewGroup(final Context context) {
        super(context);
        this.ratingsUpdateBroadcastReceiver = new VideoDetailsViewGroup$4(this);
        this.updateCapabilityBadges = new VideoDetailsViewGroup$5(this);
        this.init();
    }
    
    public VideoDetailsViewGroup(final Context context, final AttributeSet set) {
        super(context, set);
        this.ratingsUpdateBroadcastReceiver = new VideoDetailsViewGroup$4(this);
        this.updateCapabilityBadges = new VideoDetailsViewGroup$5(this);
        this.init();
    }
    
    public VideoDetailsViewGroup(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.ratingsUpdateBroadcastReceiver = new VideoDetailsViewGroup$4(this);
        this.updateCapabilityBadges = new VideoDetailsViewGroup$5(this);
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
        }
    }
    
    private EnumMap<VideoDetailsViewGroup$SupportedCapabilities, Boolean> createCapabilitiesMap(final VideoDetails videoDetails, final NetflixActivity netflixActivity) {
        while (true) {
            Label_0185: {
                if (!MdxUtils.isCurrentMdxTargetAvailable(netflixActivity.getServiceManager())) {
                    break Label_0185;
                }
                final MdxTargetCapabilities currentTargetCapabilities = netflixActivity.getServiceManager().getMdx().getCurrentTargetCapabilities();
                if (currentTargetCapabilities == null) {
                    break Label_0185;
                }
                DeviceCapabilityProvider localCapabilities;
                if ((localCapabilities = currentTargetCapabilities) == null) {
                    localCapabilities = DeviceUtils.getLocalCapabilities(netflixActivity.getServiceManager());
                }
                final EnumMap<VideoDetailsViewGroup$SupportedCapabilities, Boolean> enumMap = new EnumMap<VideoDetailsViewGroup$SupportedCapabilities, Boolean>(VideoDetailsViewGroup$SupportedCapabilities.class);
                enumMap.put(VideoDetailsViewGroup$SupportedCapabilities._5dot1, Boolean.valueOf(DeviceUtils.shouldShow5dot1Icon(localCapabilities, videoDetails)));
                if (DeviceUtils.shouldShowDolbyVisionIcon(localCapabilities, videoDetails)) {
                    enumMap.put(VideoDetailsViewGroup$SupportedCapabilities.DOLBY_VISION, Boolean.valueOf(true));
                }
                else {
                    if (DeviceUtils.shouldShowHdr10Icon(localCapabilities, videoDetails)) {
                        enumMap.put(VideoDetailsViewGroup$SupportedCapabilities.HDR10, Boolean.valueOf(true));
                        return enumMap;
                    }
                    if (DeviceUtils.shouldShowUhdIcon(localCapabilities, videoDetails)) {
                        enumMap.put(VideoDetailsViewGroup$SupportedCapabilities.UHD, Boolean.valueOf(true));
                        return enumMap;
                    }
                    if (DeviceUtils.shouldShowHdIcon(localCapabilities, videoDetails)) {
                        enumMap.put(VideoDetailsViewGroup$SupportedCapabilities.HD, Boolean.valueOf(true));
                        return enumMap;
                    }
                    if (DeviceUtils.shouldShow3DIcon(localCapabilities, videoDetails)) {
                        enumMap.put(VideoDetailsViewGroup$SupportedCapabilities._3D, Boolean.valueOf(true));
                        return enumMap;
                    }
                }
                return enumMap;
            }
            final MdxTargetCapabilities currentTargetCapabilities = null;
            continue;
        }
    }
    
    private int getBadgesPadding() {
        return this.getResources().getDimensionPixelSize(2131427700);
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
        LocalizationUtils.setLayoutDirection((View)this);
        this.addView(this.actionBarDummyView = ViewUtils.createActionBarDummyView((NetflixActivity)this.getContext()), 0);
        this.findViews();
        this.badgesPadding = this.getBadgesPadding();
        this.setImgLayoutListener();
        this.onCWClickListener = (View$OnClickListener)new VideoDetailsViewGroup$2(this);
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
    
    private boolean showCurrentEpisodeDetails(final VideoDetails videoDetails) {
        return this.isNSREShow(videoDetails) && this.hasWatched(videoDetails);
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
        if (this.ratingBar != null) {
            this.ratingBar.update(ViewUtils.getRatingBarDataProviderSafely((View)this), videoDetails);
        }
    }
    
    protected void alignViews() {
        if (Coppola1Utils.isCoppolaContext(this.getContext())) {
            final View viewById = this.findViewById(2131755309);
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
        this.ratingBar = (NetflixRatingBar)this.findViewById(2131755167);
        this.addToMyListGroup = this.findViewById(2131755189);
        this.addToMyList = (TextView)this.findViewById(2131755312);
        this.addToMyListLabel = (TextView)this.findViewById(2131755597);
        this.mMovieDownloadButton = (DownloadButton)this.findViewById(2131755190);
        this.basicInfo = (TextView)this.findViewById(2131755310);
        this.episodeBadge = (TextView)this.findViewById(2131755193);
        this.episodeTitle = (TextView)this.findViewById(2131755194);
        this.supplemental = (TextView)this.findViewById(2131755208);
        this.synopsis = (TextView)this.findViewById(2131755210);
        this.starring = (TextView)this.findViewById(2131755479);
        this.creators = (TextView)this.findViewById(2131755480);
        this.horzDispImg = (TopCropImageView)this.findViewById(2131755200);
        this.title = (TextView)this.findViewById(2131755215);
        this.imgGroup = (ViewGroup)this.findViewById(2131755313);
        this.backgroundImg = (ImageView)this.findViewById(2131755417);
        this.relatedTitle = (TextView)this.findViewById(2131755218);
        this.basicInfoBadges = (TextView)this.findViewById(2131755311);
        this.footerViewGroup = (ViewGroup)this.findViewById(2131755217);
        this.copyright = (ViewGroup)this.findViewById(2131755948);
        this.play = this.findViewById(2131755216);
        LocalizationUtils.setLayoutDirection((View)this.ratingBar);
        LocalizationUtils.setLayoutDirection((View)this.addToMyList);
        LocalizationUtils.setLayoutDirection((View)this.mMovieDownloadButton);
        LocalizationUtils.setLayoutDirection((View)this.basicInfo);
        LocalizationUtils.setLayoutDirection((View)this.synopsis);
        LocalizationUtils.setLayoutDirection((View)this.starring);
        LocalizationUtils.setLayoutDirection((View)this.creators);
        LocalizationUtils.setLayoutDirection((View)this.title);
        LocalizationUtils.setLayoutDirection((View)this.horzDispImg);
        LocalizationUtils.setLayoutDirection((View)this.imgGroup);
        LocalizationUtils.setLayoutDirection((View)this.relatedTitle);
        LocalizationUtils.setLayoutDirection((View)this.footerViewGroup);
        LocalizationUtils.setLayoutDirection((View)this.copyright);
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
    
    public View getMyListGroup() {
        return this.addToMyListGroup;
    }
    
    protected int getlayoutId() {
        return 2130903333;
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
    
    public void setVisibility(final int visibility) {
        if (Log.isLoggable()) {
            Log.v("VideoDetailsViewGroup", "setVisibility: " + ViewUtils.getVisibilityAsString(visibility));
        }
        super.setVisibility(visibility);
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
        }
        if (this.creators != null) {
            final CharSequence creatorsText = videoDetailsViewGroup$DetailsStringProvider.getCreatorsText();
            if (!StringUtils.isEmpty(creatorsText)) {
                this.creators.setText(creatorsText);
                this.creators.setVisibility(0);
                return;
            }
            this.creators.setVisibility(8);
        }
    }
    
    public void updateDetails(final VideoDetails details, final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        this.videoId = details.getId();
        this.details = details;
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
        boolean b;
        if (this.episodeTitle != null && this.episodeBadge != null && this.supplemental != null && this.starring != null && this.creators != null) {
            b = true;
        }
        else {
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
        NetflixActivity.getImageLoader((Context)netflixActivity).showImg(this.horzDispImg, s2, IClientLogging$AssetType.boxArt, s, BrowseExperience.getImageLoaderConfig(), true, 1);
        this.setupImageClicks(videoDetails, netflixActivity);
    }
    
    public void updateNSREFields(final ShowDetails showDetails) {
        final String currentEpisodeTitle = showDetails.getCurrentEpisodeTitle();
        LoMoUtils.toggleEpisodeBadge(showDetails.getCurrentEpisodeBadges(), this.episodeBadge);
        if (currentEpisodeTitle != null) {
            this.episodeTitle.setText((CharSequence)this.getResources().getString(2131296645, new Object[] { currentEpisodeTitle }));
            this.supplemental.setText((CharSequence)showDetails.getSupplementalMessage());
            this.supplemental.setVisibility(0);
            this.starring.setVisibility(8);
            this.creators.setVisibility(8);
            return;
        }
        this.episodeTitle.setText((CharSequence)showDetails.getSupplementalMessage());
        this.supplemental.setVisibility(8);
        this.starring.setVisibility(0);
        this.creators.setVisibility(0);
    }
    
    protected void updateRelatedTitle(final VideoDetails videoDetails) {
        if (this.relatedTitle != null) {
            this.relatedTitle.setText((CharSequence)this.relatedTitle.getResources().getString(2131296795, new Object[] { videoDetails.getTitle() }));
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
        }
    }
    
    protected void updateTitle(final VideoDetails videoDetails) {
        if (this.title != null) {
            this.title.setText((CharSequence)videoDetails.getTitle());
        }
    }
}
