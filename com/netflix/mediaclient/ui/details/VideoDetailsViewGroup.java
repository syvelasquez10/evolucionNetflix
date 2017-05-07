// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.text.Html;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import java.util.Iterator;
import java.util.Map;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ViewUtils;
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
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.view.View;
import java.util.EnumMap;
import android.widget.LinearLayout;

public class VideoDetailsViewGroup extends LinearLayout
{
    private static final String TAG = "VideoDetailsViewGroup";
    public static final String UPDATE_CAPABILITIES_BADGES = "com.netflix.mediaclient.intent.action.UPDATE_CAPABILITIES_BADGES";
    private static final EnumMap<VideoDetailsViewGroup$SupportedCapabilities, Integer> mCapabilityBadgesMap;
    private View actionBarDummyView;
    private Button addToMyList;
    private ImageView backgroundImg;
    private int badgesPadding;
    protected TextView basicInfo;
    protected TextView basicInfoBadges;
    protected ViewGroup copyright;
    private TextView creators;
    protected VideoDetails details;
    private ViewGroup footerViewGroup;
    protected AdvancedImageView horzDispImg;
    private ViewGroup imgGroup;
    protected NetflixRatingBar ratingBar;
    private final BroadcastReceiver ratingsUpdateBroadcastReceiver;
    private Button recommend;
    protected TextView relatedTitle;
    private TextView starring;
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
            Label_0123: {
                if (!MdxUtils.isCurrentMdxTargetAvailable(netflixActivity.getServiceManager())) {
                    break Label_0123;
                }
                final MdxTargetCapabilities currentTargetCapabilities = netflixActivity.getServiceManager().getMdx().getCurrentTargetCapabilities();
                if (currentTargetCapabilities == null) {
                    break Label_0123;
                }
                DeviceCapabilityProvider localCapabilities;
                if ((localCapabilities = currentTargetCapabilities) == null) {
                    localCapabilities = DeviceUtils.getLocalCapabilities(netflixActivity.getServiceManager());
                }
                final EnumMap<VideoDetailsViewGroup$SupportedCapabilities, Boolean> enumMap = new EnumMap<VideoDetailsViewGroup$SupportedCapabilities, Boolean>(VideoDetailsViewGroup$SupportedCapabilities.class);
                enumMap.put(VideoDetailsViewGroup$SupportedCapabilities.HD, Boolean.valueOf(DeviceUtils.shouldShowHdIcon(localCapabilities, videoDetails)));
                enumMap.put(VideoDetailsViewGroup$SupportedCapabilities._3D, Boolean.valueOf(DeviceUtils.shouldShow3DIcon(localCapabilities, videoDetails)));
                enumMap.put(VideoDetailsViewGroup$SupportedCapabilities._5dot1, Boolean.valueOf(DeviceUtils.shouldShow5dot1Icon(localCapabilities, videoDetails)));
                enumMap.put(VideoDetailsViewGroup$SupportedCapabilities.UHD, Boolean.valueOf(DeviceUtils.shouldShowUhdIcon(localCapabilities, videoDetails)));
                return enumMap;
            }
            final MdxTargetCapabilities currentTargetCapabilities = null;
            continue;
        }
    }
    
    private int getBadgesPadding() {
        return this.getResources().getDimensionPixelSize(2131296485);
    }
    
    private String getInterBadgePadding() {
        return "  ";
    }
    
    private void init() {
        LayoutInflater.from(this.getContext()).inflate(this.getlayoutId(), (ViewGroup)this, true);
        this.setOrientation(1);
        this.addView(this.actionBarDummyView = ViewUtils.createActionBarDummyView((NetflixActivity)this.getContext()), 0);
        this.findViews();
        this.badgesPadding = this.getBadgesPadding();
        this.setImgLayoutListener();
    }
    
    private void setImgLayoutListener() {
        if (Log.isLoggable()) {
            Log.v("VideoDetailsViewGroup", "setImgLayoutListener()");
        }
        this.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new VideoDetailsViewGroup$2(this));
    }
    
    private void updateRating(final VideoDetails videoDetails) {
        if (this.ratingBar != null) {
            this.ratingBar.update(ViewUtils.getRatingBarDataProviderSafely((View)this), videoDetails);
        }
    }
    
    protected void alignViews() {
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
        this.ratingBar = (NetflixRatingBar)this.findViewById(2131624216);
        this.addToMyList = (Button)this.findViewById(2131624561);
        this.basicInfo = (TextView)this.findViewById(2131624441);
        this.recommend = (Button)this.findViewById(2131624560);
        this.synopsis = (TextView)this.findViewById(2131624285);
        this.starring = (TextView)this.findViewById(2131624275);
        this.creators = (TextView)this.findViewById(2131624276);
        this.horzDispImg = (AdvancedImageView)this.findViewById(2131624206);
        this.title = (TextView)this.findViewById(2131624210);
        this.imgGroup = (ViewGroup)this.findViewById(2131624569);
        this.backgroundImg = (ImageView)this.findViewById(2131624203);
        this.relatedTitle = (TextView)this.findViewById(2131624212);
        this.basicInfoBadges = (TextView)this.findViewById(2131624568);
        this.footerViewGroup = (ViewGroup)this.findViewById(2131624211);
        this.copyright = (ViewGroup)this.findViewById(2131624562);
    }
    
    public TextView getAddToMyListButton() {
        return (TextView)this.addToMyList;
    }
    
    public TextView getAddToMyListButtonLabel() {
        return null;
    }
    
    public ImageView getBackgroundImage() {
        return this.backgroundImg;
    }
    
    public ViewGroup getFooterViewGroup() {
        return this.footerViewGroup;
    }
    
    public AdvancedImageView getHeroImage() {
        return this.horzDispImg;
    }
    
    public TextView getRecommendButton() {
        return (TextView)this.recommend;
    }
    
    public TextView getRecommendButtonLabel() {
        return null;
    }
    
    protected int getlayoutId() {
        return 2130903251;
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
        this.horzDispImg.requestFocus();
        this.horzDispImg.setOnClickListener((View$OnClickListener)new PressedStateHandler$DelayedOnClickListener(this.horzDispImg.getPressedStateHandler(), (View$OnClickListener)new VideoDetailsViewGroup$3(this, netflixActivity, videoDetails)));
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
    
    protected void updateBasicInfo(final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        if (this.basicInfo != null) {
            this.basicInfo.setText(videoDetailsViewGroup$DetailsStringProvider.getBasicInfoString());
        }
    }
    
    protected void updateCredits(final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        if (this.starring != null) {
            this.starring.setText(videoDetailsViewGroup$DetailsStringProvider.getStarringText());
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
        this.updateImage(details, netflixActivity, String.format(this.getResources().getString(2131165355), details.getTitle()));
        this.updateRelatedTitle(details);
        this.updateTitle(details);
        this.updateBasicInfo(videoDetailsViewGroup$DetailsStringProvider);
        this.updateBadges(details, netflixActivity);
        this.updateRating(details);
        this.updateSynopsis(details);
        this.updateCredits(videoDetailsViewGroup$DetailsStringProvider);
    }
    
    protected void updateImage(final VideoDetails videoDetails, final NetflixActivity netflixActivity, final String s) {
        final ImageLoader imageLoader = NetflixActivity.getImageLoader((Context)netflixActivity);
        final AdvancedImageView horzDispImg = this.horzDispImg;
        String s2;
        if (DeviceUtils.isTabletByContext((Context)netflixActivity)) {
            s2 = videoDetails.getHorzDispUrl();
        }
        else {
            s2 = videoDetails.getStoryUrl();
        }
        imageLoader.showImg(horzDispImg, s2, IClientLogging$AssetType.boxArt, s, BrowseExperience.getImageLoaderConfig(), true);
        this.setupImageClicks(videoDetails, netflixActivity);
    }
    
    protected void updateRelatedTitle(final VideoDetails videoDetails) {
        if (this.relatedTitle != null) {
            this.relatedTitle.setText((CharSequence)this.relatedTitle.getResources().getString(2131165536, new Object[] { videoDetails.getTitle() }));
        }
    }
    
    public void updateSynopsis(final VideoDetails videoDetails) {
        final String synopsis = videoDetails.getSynopsis();
        if (this.synopsis != null) {
            final TextView synopsis2 = this.synopsis;
            Object fromHtml;
            if (StringUtils.isEmpty(synopsis)) {
                fromHtml = "";
            }
            else {
                fromHtml = Html.fromHtml(videoDetails.getSynopsis());
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
