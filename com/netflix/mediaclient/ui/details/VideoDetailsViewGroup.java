// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.text.Html;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import java.util.Iterator;
import java.util.Map;
import android.view.View$OnClickListener;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import java.util.List;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.view.ViewGroup$LayoutParams;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.service.webclient.model.leafs.FriendProfile;
import android.widget.LinearLayout$LayoutParams;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.FriendProfilesProvider;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.LayoutInflater;
import com.netflix.mediaclient.ui.mdx.MdxTargetCapabilities;
import com.netflix.mediaclient.servicemgr.interface_.FeatureEnabledProvider;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.MdxUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.util.StringUtils;
import android.util.AttributeSet;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Button;
import android.view.View;
import java.util.EnumMap;
import android.widget.LinearLayout;

public class VideoDetailsViewGroup extends LinearLayout
{
    private static final int MAX_SOCIAL_BITMAP_IMGS_LANDSCAPE = 4;
    private static final int MAX_SOCIAL_BITMAP_IMGS_PORTRAIT = 3;
    private static final String TAG = "VideoDetailsViewGroup";
    private static final EnumMap<VideoDetailsViewGroup$SupportedCapabilities, Integer> mCapabilityBadgesMap;
    private View actionBarDummyView;
    private Button addToMyList;
    private ImageView backgroundImg;
    private int badgesPadding;
    protected TextView basicInfo;
    protected TextView basicInfoBadges;
    private TextView creators;
    private ViewGroup footerViewGroup;
    protected AdvancedImageView horzDispImg;
    private ViewGroup imgGroup;
    protected NetflixRatingBar ratingBar;
    private final BroadcastReceiver ratingsUpdateBroadcastReceiver;
    private Button recommend;
    protected TextView relatedTitle;
    private LinearLayout socialGroup;
    private TextView socialTitle;
    private TextView starring;
    protected TextView synopsis;
    protected TextView title;
    private String videoId;
    
    static {
        mCapabilityBadgesMap = new VideoDetailsViewGroup$1(VideoDetailsViewGroup$SupportedCapabilities.class);
    }
    
    public VideoDetailsViewGroup(final Context context) {
        super(context);
        this.ratingsUpdateBroadcastReceiver = new VideoDetailsViewGroup$4(this);
        this.init();
    }
    
    public VideoDetailsViewGroup(final Context context, final AttributeSet set) {
        super(context, set);
        this.ratingsUpdateBroadcastReceiver = new VideoDetailsViewGroup$4(this);
        this.init();
    }
    
    public VideoDetailsViewGroup(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.ratingsUpdateBroadcastReceiver = new VideoDetailsViewGroup$4(this);
        this.init();
    }
    
    private void addIconFontBadges(final String text) {
        if (!StringUtils.isEmpty(text)) {
            this.basicInfoBadges.setPadding(this.badgesPadding, 0, 0, 0);
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
                enumMap.put(VideoDetailsViewGroup$SupportedCapabilities._3D, Boolean.valueOf(DeviceUtils.shouldShowUhdIcon(localCapabilities, videoDetails)));
                enumMap.put(VideoDetailsViewGroup$SupportedCapabilities._5dot1, Boolean.valueOf(DeviceUtils.shouldShow5dot1Icon(localCapabilities, videoDetails)));
                enumMap.put(VideoDetailsViewGroup$SupportedCapabilities.UHD, Boolean.valueOf(DeviceUtils.shouldShow3DIcon(localCapabilities, videoDetails)));
                return enumMap;
            }
            final MdxTargetCapabilities currentTargetCapabilities = null;
            continue;
        }
    }
    
    private int getBadgesPadding() {
        return this.getResources().getDimensionPixelSize(2131296343);
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
        this.getViewTreeObserver().addOnGlobalLayoutListener((ViewTreeObserver$OnGlobalLayoutListener)new VideoDetailsViewGroup$2(this));
    }
    
    private void updateRating(final VideoDetails video) {
        if (this.ratingBar != null) {
            this.ratingBar.setVideo(video);
        }
    }
    
    private void updateSocialGroup(final VideoDetails videoDetails) {
        final int n = 8;
        if (this.socialGroup != null) {
            List<FriendProfile> facebookFriends;
            if (videoDetails instanceof FriendProfilesProvider) {
                facebookFriends = ((FriendProfilesProvider)videoDetails).getFacebookFriends();
            }
            else {
                facebookFriends = null;
            }
            boolean b;
            if (facebookFriends == null || facebookFriends.size() <= 0) {
                b = true;
            }
            else {
                b = false;
            }
            final LinearLayout socialGroup = this.socialGroup;
            int visibility;
            if (b) {
                visibility = 8;
            }
            else {
                visibility = 0;
            }
            socialGroup.setVisibility(visibility);
            final TextView socialTitle = this.socialTitle;
            int visibility2;
            if (b) {
                visibility2 = n;
            }
            else {
                visibility2 = 0;
            }
            socialTitle.setVisibility(visibility2);
            if (b) {
                Log.v("VideoDetailsViewGroup", "No friends available for this detail view");
                return;
            }
            this.socialTitle.setText(2131493218);
            final int dimensionPixelOffset = this.getResources().getDimensionPixelOffset(2131296387);
            final LinearLayout$LayoutParams linearLayout$LayoutParams = new LinearLayout$LayoutParams(dimensionPixelOffset, dimensionPixelOffset);
            final int dimensionPixelOffset2 = this.getResources().getDimensionPixelOffset(2131296388);
            linearLayout$LayoutParams.topMargin = dimensionPixelOffset2;
            linearLayout$LayoutParams.bottomMargin = dimensionPixelOffset2;
            final int n2 = dimensionPixelOffset2 / 2;
            linearLayout$LayoutParams.leftMargin = n2;
            linearLayout$LayoutParams.rightMargin = n2;
            this.socialGroup.removeAllViews();
            final int size = facebookFriends.size();
            int n3;
            if (DeviceUtils.isPortrait(this.getContext())) {
                n3 = 3;
            }
            else {
                n3 = 4;
            }
            for (int min = Math.min(size, n3), i = 0; i < min; ++i) {
                final FriendProfile friendProfile = facebookFriends.get(i);
                if (Log.isLoggable()) {
                    Log.v("VideoDetailsViewGroup", "Updating img for friend: " + friendProfile.getFirstName() + ", url: " + friendProfile.getImageUrl());
                }
                final AdvancedImageView advancedImageView = new AdvancedImageView(this.getContext());
                advancedImageView.setAdjustViewBounds(true);
                advancedImageView.setScaleType(ImageView$ScaleType.FIT_CENTER);
                this.socialGroup.addView((View)advancedImageView, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
                NetflixActivity.getImageLoader(this.getContext()).showImg(advancedImageView, friendProfile.getImageUrl(), IClientLogging$AssetType.profileAvatar, friendProfile.getFullName(), false, true);
            }
        }
    }
    
    protected void alignViews() {
        final ViewGroup$LayoutParams layoutParams = this.horzDispImg.getLayoutParams();
        layoutParams.height = this.calculateImageHeight();
        this.horzDispImg.setLayoutParams(layoutParams);
    }
    
    protected int calculateImageHeight() {
        int n;
        if ((n = this.imgGroup.getMeasuredWidth()) <= 0) {
            n = DeviceUtils.getScreenWidthInPixels(this.getContext());
        }
        return (int)(n * 0.5625f);
    }
    
    protected void findViews() {
        this.ratingBar = (NetflixRatingBar)this.findViewById(2131427574);
        this.socialGroup = (LinearLayout)this.findViewById(2131427856);
        this.socialTitle = (TextView)this.findViewById(2131427855);
        this.addToMyList = (Button)this.findViewById(2131427850);
        this.basicInfo = (TextView)this.findViewById(2131427759);
        this.recommend = (Button)this.findViewById(2131427849);
        this.synopsis = (TextView)this.findViewById(2131427620);
        this.starring = (TextView)this.findViewById(2131427609);
        this.creators = (TextView)this.findViewById(2131427610);
        this.horzDispImg = (AdvancedImageView)this.findViewById(2131427540);
        this.title = (TextView)this.findViewById(2131427539);
        this.imgGroup = (ViewGroup)this.findViewById(2131427857);
        this.backgroundImg = (ImageView)this.findViewById(2131427537);
        this.relatedTitle = (TextView)this.findViewById(2131427592);
        this.basicInfoBadges = (TextView)this.findViewById(2131427854);
        this.footerViewGroup = (ViewGroup)this.findViewById(2131427591);
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
        return 2130903215;
    }
    
    public void hideRelatedTitle() {
        if (this.relatedTitle != null) {
            this.relatedTitle.setVisibility(4);
        }
    }
    
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(this.ratingsUpdateBroadcastReceiver, new IntentFilter("com.netflix.falkor.ACTION_NOTIFY_OF_RATINGS_CHANGE"));
    }
    
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(this.ratingsUpdateBroadcastReceiver);
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
    
    public void removeRelatedTitle() {
        if (this.relatedTitle != null) {
            this.relatedTitle.setVisibility(8);
        }
    }
    
    protected void setupImageClicks(final VideoDetails videoDetails, final NetflixActivity netflixActivity) {
        this.horzDispImg.requestFocus();
        this.horzDispImg.setOnClickListener((View$OnClickListener)new VideoDetailsViewGroup$3(this, netflixActivity, videoDetails));
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
    
    public void updateDetails(final VideoDetails videoDetails, final VideoDetailsViewGroup$DetailsStringProvider videoDetailsViewGroup$DetailsStringProvider) {
        this.videoId = videoDetails.getId();
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        this.updateImage(videoDetails, netflixActivity, String.format(this.getResources().getString(2131493182), videoDetails.getTitle()));
        this.updateRelatedTitle(videoDetails);
        this.updateTitle(videoDetails);
        this.updateBasicInfo(videoDetailsViewGroup$DetailsStringProvider);
        this.updateBadges(videoDetails, netflixActivity);
        this.updateRating(videoDetails);
        this.updateSynopsis(videoDetails);
        this.updateCredits(videoDetailsViewGroup$DetailsStringProvider);
        this.updateSocialGroup(videoDetails);
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
        imageLoader.showImg(horzDispImg, s2, IClientLogging$AssetType.boxArt, s, true, true);
        this.setupImageClicks(videoDetails, netflixActivity);
    }
    
    protected void updateRelatedTitle(final VideoDetails videoDetails) {
        if (this.relatedTitle != null) {
            this.relatedTitle.setText((CharSequence)this.relatedTitle.getResources().getString(2131493167, new Object[] { videoDetails.getTitle() }));
        }
    }
    
    protected void updateSynopsis(final VideoDetails videoDetails) {
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
